package com.rest.study.board.freeboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.study.image.freeImage.entity.FreeImageAttachment;
import com.rest.study.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@SequenceGenerator(name = "SEQ_FREE_BOARD_ID", sequenceName = "SEQ_FREE_BOARD_ID", initialValue = 1, allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FreeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_FREE_BOARD_ID")
    private Long freeId;

    @Column(nullable = false, length = 100)
    private String freeTitle;

    @Column(nullable = false)
    private String freeContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FREE_USER_ID", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @CreationTimestamp
    private Timestamp freeCreatedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "freeBoard", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<FreeImageAttachment> images;
}
