package com.rest.study.image.freeImage.repository;

import com.rest.study.image.freeImage.entity.FreeImageAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeImageRepository extends JpaRepository<FreeImageAttachment, Long> {
}
