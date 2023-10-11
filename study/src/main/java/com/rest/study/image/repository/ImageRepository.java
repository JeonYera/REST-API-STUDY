package com.rest.study.image.repository;

import com.rest.study.image.entity.ImageAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageAttachment, Long> {
}
