package com.rest.study.board.freeboard.dto;

import com.rest.study.board.freeboard.entity.FreeBoard;
import com.rest.study.image.freeImage.entity.FreeImageAttachment;
import com.rest.study.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class FreeBoardReadDto {
    private Long freeId;
    private String userId;
    private String freeTitle;
    private String freeContent;
    private Timestamp freeCreatedAt;

    private List<FreeImageAttachment> images;

    public static FreeBoardReadDto toDto(FreeBoard freeBoard) {
        return FreeBoardReadDto.builder()
                .freeId(freeBoard.getFreeId())
                .freeContent(freeBoard.getFreeContent())
                .freeTitle(freeBoard.getFreeTitle())
                .freeCreatedAt(freeBoard.getFreeCreatedAt())
                .userId(freeBoard.getUser().getUserId())
                .images(freeBoard.getImages())
                .build();
    }

    public FreeBoard toEntity(FreeBoardReadDto freeBoard, User user) {
        return FreeBoard.builder()
                .freeId(freeBoard.getFreeId())
                .freeContent(freeBoard.getFreeContent())
                .freeTitle(freeBoard.getFreeTitle())
                .freeCreatedAt(freeBoard.getFreeCreatedAt())
                .user(user)
                .build();
    }
}
