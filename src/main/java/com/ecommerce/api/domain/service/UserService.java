package com.ecommerce.api.domain.service;

import com.ecommerce.api.api.dto.password.NewPasswordInputDTO;
import com.ecommerce.api.domain.domainException.BusinessRulesException;
import com.ecommerce.api.domain.enumerateds.Role;
import com.ecommerce.api.domain.model.User;
import com.ecommerce.api.domain.repository.UserRepository;
import com.ecommerce.api.security.jwt.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final int MINUTES_TO_RETRY = 1;

    @Transactional(readOnly = false)
    public User save(User user) {

        if (repository.findUserByEmail(user.getEmail()).isPresent())
            throw new BusinessRulesException("Já existe um usuário cadastrado com esse email!");

        if (repository.findUserByUsername(user.getUsername()).isPresent())
            throw new BusinessRulesException("Já existe um usuário cadastrado com esse nome de usuário!");

        if (repository.findUserByTel(user.getPhoneAreaCode().concat(user.getPhoneNumber())).isPresent())
            throw new BusinessRulesException("Já existe um usuário cadastrado com esse telefone!");

        // empty user
        String firstTokenUser = JwtToken.generateTokenJWT(user);
        user.setToken(firstTokenUser);
        user.setStatus(false);
        user.setRole(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);

    }

    @Transactional(readOnly = false)
    public User saveUserAfterConfirmedAccountByEmail(String token) {
        User user = repository.findUserByToken(token).orElseThrow(() -> new BusinessRulesException("Erro ao confirmar conta!"));
        // token exist from email confirmation
        user.setStatus(true);
        return repository.save(user);
    }

    @Transactional(readOnly = false)
    public User login(User user) {
        user.setToken(JwtToken.generateTokenJWT(user));
        user.setAttempts(0);
        user.setReleaseLogin(null);
        return repository.save(user);
    }

    @Transactional(readOnly = false)
    public User loginWithGoogle(User user) {
        User userLoginWithGoogle = repository.findAccountGoogleByEmail(user.getEmail());
        if (userLoginWithGoogle == null) {
            // empty user
            String firstTokenUser = JwtToken.generateTokenJWT(user);
            user.setToken(firstTokenUser);
            user.setStatus(true);
            user.setRole(Role.ROLE_USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return repository.save(user);
        } else if (passwordEncoder.matches(user.getPassword(), userLoginWithGoogle.getPassword())) {
            // exist user. Update Token
            userLoginWithGoogle.setToken(JwtToken.generateTokenJWT(userLoginWithGoogle));
            return repository.save(userLoginWithGoogle);
        }
        throw new BusinessRulesException("Erro ao realizar login com Google!");
    }

    @Transactional(readOnly = true)
    public User findUser(Long idUser) {
        Optional<User> userOptional = repository.findById(idUser);
        return userOptional.orElseThrow(() -> new BusinessRulesException("Não existe usuário com id " + idUser));
    }

    @Transactional(readOnly = true)
    public Boolean findUser(String username) {
        return repository.findUserByUsername(username).isPresent();
    }

    @Transactional(readOnly = false)
    public int updateAttempts(String username) {
        int attempts = repository.attemptsUser(username) + 1;
        repository.updateAttemptsUser(attempts, username);
        return repository.attemptsUser(username);
    }

    @Transactional(readOnly = true)
    public int attemptsUser(String username) {
        return repository.attemptsUser(username);
    }

    @Transactional(readOnly = false)
    public Date releaseLogin(String username) {
        // get current date and time
        LocalDateTime now = LocalDateTime.now();
        // Add minutes
        LocalDateTime minutes = now.plusMinutes(MINUTES_TO_RETRY);
        // release date
        Date releaseDate = Date.from(minutes.toInstant(ZoneOffset.of("-03:00")));
        repository.updateReleaseDate(releaseDate, username);
        return releaseDate;
    }

    @Transactional(readOnly = true)
    public Date getDateReleaseLogin(String username) {
        return repository.getDateReleaseLogin(username);
    }

    @Transactional(readOnly = true)
    public Boolean verifyReleaseDateLogin(String username) {
        return repository.getDateReleaseLogin(username) != null;
    }

    @Transactional(readOnly = false)
    public void resetAttemptsAndReleaseLogin(String username) {
        repository.resetAttemptsAndReleaseLogin(username);
    }

    @Transactional(readOnly = false)
    public User updatePassword(NewPasswordInputDTO newPasswordInputDTO) {
        User user = repository.findUserByToken(newPasswordInputDTO.getToken()).orElseThrow(() -> new BusinessRulesException("Erro ao realizar mudança de senha!"));
        user.setPassword(passwordEncoder.encode(newPasswordInputDTO.getNewpassword()));
        return repository.save(user);
    }

    @Transactional(readOnly = false)
    public User findUserByUsername(String username) {
        return repository.findUserByUsername(username).orElseThrow(() -> {
            throw new BusinessRulesException("Usuário não encontrado!");
        });
    }
}

