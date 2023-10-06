package com.rest.study.board.freeboard.dto;

import com.rest.study.image.freeImage.entity.FreeImageAttachment;
import com.rest.study.user.entity.User;
import com.rest.study.board.freeboard.entity.FreeBoard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoardDto {

    private String freeUserId;
    @NotBlank(message = "제목은 필수입니다.")
    private String freeTitle;
    @NotBlank(message = "내용은 필수입니다.")
    private String freeContent;

    private List<FreeImageAttachment> images;

    public FreeBoard toFreeBoardDto(FreeBoard freeBoard, User user) {
                freeBoard.setFreeTitle(freeTitle);
                freeBoard.setFreeContent(freeContent);
                freeBoard.setUser(user);
                return freeBoard;
    }

    public FreeBoard toFreeBoardDto(User user, List<FreeImageAttachment> images) {
        return FreeBoard.builder()
                .user(user)
                .freeTitle(freeTitle)
                .freeContent(freeContent)
                .images(images)
                .build();
    }
}

