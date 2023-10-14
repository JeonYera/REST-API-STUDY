package com.rest.study.S3.entity;

import com.rest.study.board.entity.Board;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = "board")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @Column(nullable = false)
    private String originName;

    @Column(name = "s3_url", nullable = false, length = 1000)
    private String s3Url;

    @Column(name = "image_file_size", nullable = false)
    private Long imageFileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", referencedColumnName = "board_id")
    private Board board;

}
