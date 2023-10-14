package com.rest.study.board.dto;

import com.rest.study.user.entity.User;
import com.rest.study.board.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardCreateDto {

    private String boardUserId;
    @NotBlank(message = "제목은 필수입니다.")
    private String boardTitle;
    @NotBlank(message = "내용은 필수입니다.")
    private String boardContent;

    List<MultipartFile> images;

    public Board toBoardDto(Board board, User user) {
                board.setBoardTitle(boardTitle);
                board.setBoardContent(boardContent);
                board.setUser(user);
                return board;
    }

    public Board toBoardDto(User user) {
        return Board.builder()
                .user(user)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardCreatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }
}

