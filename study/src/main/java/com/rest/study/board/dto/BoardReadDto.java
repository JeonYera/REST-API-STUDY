package com.rest.study.board.dto;

import com.rest.study.board.entity.Board;
import com.rest.study.image.entity.Image;
import com.rest.study.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class BoardReadDto {
    private Long boardId;
    private String userId;
    private String boardTitle;
    private String boardContent;
    private Timestamp boardCreatedAt;

    private List<Image> images;

    public static BoardReadDto toDto(Board board) {
        return BoardReadDto.builder()
                .boardId(board.getBoardId())
                .boardContent(board.getBoardContent())
                .boardTitle(board.getBoardTitle())
                .boardCreatedAt(board.getBoardCreatedAt())
                .userId(board.getUser().getUserId())
                .images(board.getImages())
                .build();
    }

    public Board toEntity(BoardReadDto board, User user) {
        return Board.builder()
                .boardId(board.getBoardId())
                .boardContent(board.getBoardContent())
                .boardTitle(board.getBoardTitle())
                .boardCreatedAt(board.getBoardCreatedAt())
                .user(user)
                .build();
    }
}
