package com.rest.study.image.freeImage.entity;

import com.rest.study.board.freeboard.entity.FreeBoard;
import lombok.*;

import javax.persistence.*;

@SequenceGenerator(name = "SEQ_IMAGE_ID", sequenceName = "SEQ_IMAGE_ID", initialValue = 1, allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = "freeBoard")
@Table(name = "image_attachment")
public class FreeImageAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IMAGE_ID")
    private Long imageId;

    @Column(nullable = false)
    private String originName;

    @Column(nullable = false)
    private String uniqueName;

    @Column(nullable = false)
    private Long imageFileSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="FREE_ID")
    private FreeBoard freeBoard;

}
