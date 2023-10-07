package com.rest.study.board.freeboard.dto;

import com.rest.study.board.freeboard.entity.FreeBoard;
import com.rest.study.user.entity.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class FreeBoardUpdateDto {

    private String freeUserId;
    @NotBlank(message = "제목은 필수입니다.")
    private String freeTitle;
    @NotBlank(message = "내용은 필수입니다.")
    private String freeContent;

    public FreeBoard toEntity(FreeBoard freeBoard, User user) {
        freeBoard.setFreeTitle(freeTitle);
        freeBoard.setFreeContent(freeContent);
        freeBoard.setUser(user);
        return freeBoard;
    }
}
