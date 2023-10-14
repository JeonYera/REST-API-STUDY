package com.rest.study.image.entity;

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

    @Column(nullable = false)
    private String uniqueName;

    @Column(nullable = false)
    private Long imageFileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="BOARD_ID")
    private Board board;

}
