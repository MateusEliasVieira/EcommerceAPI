package com.ecommerce.api.domain.model;


import com.ecommerce.api.domain.enumerateds.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String name;
    private String surname;
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String picture;
    private Date dateOfBirth;
    private String phoneAreaCode;  // Código de área do telefone
    private String phoneNumber;    // Número de telefone
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    private String password;
    private String cpf;

    private String city;
    private String district;  // Bairro
    private String streetName;    // Nome da rua
    private String streetNumber;  // Número da rua
    private String zipCode;       // CEP

    @Enumerated(EnumType.STRING)
    private Role role;
    private int attempts;
    private Date releaseLogin;
    private String token;
    private boolean status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Buy> buys;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Avaliation> avaliation;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Comment> comment;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == Role.ROLE_ADMIN) {
            // se esse usuário tiver uma role de admin, retornamos os tipos de acesso que ele pode ter no sistema, nesse caso admin e user
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        }

        if (this.role == Role.ROLE_USER) {
            // se esse usuário tiver uma role de user, retornamos o tipo de acesso que ele pode ter no sistema, nesse caso apenas user
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return null;
        }
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
