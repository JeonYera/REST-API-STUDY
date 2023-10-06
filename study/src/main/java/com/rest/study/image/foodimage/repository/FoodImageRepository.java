package com.rest.study.image.foodimage.repository;

import com.rest.study.image.foodimage.entity.FoodImageAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodImageRepository extends JpaRepository<FoodImageAttachment, Long> {



    List<FoodImageAttachment> findByFoodBoard_foodId(Long id);

    FoodImageAttachment findByUniqueName(String uniqueName);
}
