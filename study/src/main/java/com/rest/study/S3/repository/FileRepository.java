package com.rest.study.S3.repository;

import com.rest.study.S3.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Image, Long> {
}
