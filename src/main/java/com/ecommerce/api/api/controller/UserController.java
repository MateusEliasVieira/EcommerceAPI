package com.ecommerce.api.api.controller;

import com.ecommerce.api.api.dto.others.Message;
import com.ecommerce.api.api.dto.user.UserInputDTO;
import com.ecommerce.api.api.mapper.UserMapper;
import com.ecommerce.api.domain.model.User;
import com.ecommerce.api.domain.service.EmailSenderService;
import com.ecommerce.api.domain.service.UserService;
import com.ecommerce.api.utils.StrongPassword;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", produces = {"application/json"})
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Message> newUser(@RequestBody @Valid UserInputDTO userInputDto) {
        StrongPassword.isStrong(userInputDto.getPassword());
        User user = service.save(userMapper.mapperUserInputDTOToUser(userInputDto));
        emailSenderService.sendEmail(user.getEmail(), user.getToken());
        return new ResponseEntity<Message>(new Message("Email de confirmação enviado com sucesso para " + user.getEmail()), HttpStatus.CREATED);
    }

    @GetMapping("/find/{idUser}")
    public ResponseEntity<?> findUser(@PathVariable Long idUser) {
        return ResponseEntity.ok(userMapper.mapperUserToUserOutputDTO(service.findUser(idUser)));
    }
}
