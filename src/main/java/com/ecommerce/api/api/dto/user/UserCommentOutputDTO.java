package com.ecommerce.api.api.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCommentOutputDTO {

    private Long idUser;
    private String name;
    private String surname;


}
