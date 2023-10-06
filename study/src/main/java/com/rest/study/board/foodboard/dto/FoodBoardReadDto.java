package com.rest.study.board.foodboard.dto;

import com.rest.study.board.foodboard.entity.FoodBoard;
import com.rest.study.image.foodimage.dto.ImageDto;
import com.rest.study.user.entity.User;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class FoodBoardReadDto {
    private Long foodId;
    private String userId;
    private String foodTitle;
    private String foodContent;
    private Timestamp foodCreatedAt;
    private ImageDto images;

    public void setImages(ImageDto images) {
        if (images != null) {
            this.images = images;
        } else {
            this.images = null;
        }
    }
    public static FoodBoardReadDto toDto(FoodBoard foodBoard) {
        return FoodBoardReadDto.builder()
                .foodId(foodBoard.getFoodId())
                .foodContent(foodBoard.getFoodContent())
                .foodTitle(foodBoard.getFoodTitle())
                .foodCreatedAt(foodBoard.getFoodCreatedAt())
                .userId(foodBoard.getUser().getUserId())
                .build();
    }

    public FoodBoard toEntity(FoodBoardReadDto foodBoard, User user) {
        return FoodBoard.builder()
                .foodId(foodBoard.getFoodId())
                .foodContent(foodBoard.getFoodContent())
                .foodTitle(foodBoard.getFoodTitle())
                .foodCreatedAt(foodBoard.getFoodCreatedAt())
                .user(user)
                .build();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FoodBoardReadDto{");
        sb.append("foodId=").append(foodId);
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", foodTitle='").append(foodTitle).append('\'');
        sb.append(", foodContent='").append(foodContent).append('\'');
        sb.append(", foodCreatedAt=").append(foodCreatedAt);
        sb.append(", images=[").append(images.getUniqueName()).append(", ");
        sb.append("]}");
        return sb.toString();
    }
}
