package com.rest.study.board.dto;

import com.rest.study.board.entity.Board;
import com.rest.study.user.entity.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class BoardUpdateDto {

    private String boardUserId;
    @NotBlank(message = "제목은 필수입니다.")
    private String boardTitle;
    @NotBlank(message = "내용은 필수입니다.")
    private String boardContent;

    public Board toEntity(Board board, User user) {
        board.setBoardTitle(boardTitle);
        board.setBoardContent(boardContent);
        board.setUser(user);
        return board;
    }
}
