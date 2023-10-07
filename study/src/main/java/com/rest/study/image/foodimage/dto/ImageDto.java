package com.rest.study.image.foodimage.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageDto {
    private String originName;
    private String uniqueName;
}
