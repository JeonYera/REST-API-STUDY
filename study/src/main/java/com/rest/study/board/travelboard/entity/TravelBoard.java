package com.rest.study.board.travelboard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@SequenceGenerator(name = "SEQ_TRAVEL_BOARD_ID", sequenceName = "SEQ_TRAVEL_BOARD_ID", initialValue = 1, allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class TravelBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRAVEL_BOARD_ID")
    private Long travelId;

    @Column(nullable = false, length = 100)
    private String travelTitle;

    @Column(nullable = false)
    private String travelContent;

    @Column(nullable = false)
    private String travelMemberId;

    @CreationTimestamp
    private LocalDateTime travelCreatedAt;
}
