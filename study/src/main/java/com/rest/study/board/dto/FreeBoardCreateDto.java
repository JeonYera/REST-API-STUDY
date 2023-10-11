package com.rest.study.board.dto;

import com.rest.study.user.entity.User;
import com.rest.study.board.entity.FreeBoard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoardCreateDto {

    private int freeId;
    private String freeUserId;
    @NotBlank(message = "제목은 필수입니다.")
    private String freeTitle;
    @NotBlank(message = "내용은 필수입니다.")
    private String freeContent;

    private Timestamp freeCreatedAt;

    private MultipartFile images;

    public FreeBoard toFreeBoardDto(FreeBoard freeBoard, User user) {
                freeBoard.setFreeTitle(freeTitle);
                freeBoard.setFreeContent(freeContent);
                freeBoard.setUser(user);
                return freeBoard;
    }

    public FreeBoard toFreeBoardDto(User user) {
        return FreeBoard.builder()
                .user(user)
                .freeTitle(freeTitle)
                .freeContent(freeContent)
                .freeCreatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }
}

