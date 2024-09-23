package com.ecommerce.api.api.dto.comment;

import com.ecommerce.api.api.dto.user.UserCommentOutputDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CommentOutputDTO {

    private Long idComment;
    private String comment;
    private UserCommentOutputDTO user;

}
