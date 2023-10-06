package com.rest.study.board.travelboard.dto;

import com.rest.study.board.travelboard.entity.TravelBoard;
import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class TravelBoardDto {

    private String travelMemberId;
    @NotBlank(message = "제목은 필수입니다.")
    private String travelTitle;
    @NotBlank(message = "내용은 필수입니다.")
    private String travelContent;

    public TravelBoard toTravel(TravelBoard travelBoard) {
        travelBoard.setTravelMemberId(travelMemberId);
        travelBoard.setTravelTitle(travelTitle);
        travelBoard.setTravelContent(travelContent);
        return travelBoard;
    }

    public TravelBoard toTravel(){
        return TravelBoard.builder()
                .travelMemberId(travelMemberId)
                .travelTitle(travelTitle)
                .travelContent(travelContent)
                .build();
    }
}
