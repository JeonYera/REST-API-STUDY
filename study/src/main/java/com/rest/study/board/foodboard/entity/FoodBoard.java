package com.rest.study.board.foodboard.entity;

import com.rest.study.image.foodimage.entity.FoodImageAttachment;
import com.rest.study.user.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Builder
public class FoodBoard {
    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "SEQ_FOOD_BOARD_ID")
    private Long foodId; // null을 확인하기 위해서 참조형으로

//    private String foodMemberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;


    @Column(nullable = false, length = 500)
    private String foodTitle;

    @Column(nullable = false, length = 4000)
    private String foodContent;

    @OneToMany(mappedBy = "foodBoard", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<FoodImageAttachment> images;

// TemporalType.TIMESTAMP : date + time 의 timestamp(datetime) 타입
// @Column(nullable = false, columnDefinition = "date default systimestamp")
    @CreationTimestamp
    private Timestamp foodCreatedAt;
}
