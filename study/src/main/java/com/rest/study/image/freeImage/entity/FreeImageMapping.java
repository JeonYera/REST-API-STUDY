package com.rest.study.image.freeImage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@SequenceGenerator(name = "SEQ_MAPPING_ID", sequenceName = "SEQ_MAPPING_ID", initialValue = 1, allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class FreeImageMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MAPPING_ID")
    private Long mappingId;

    @Column(nullable = false)
    private Long imageId;

    @Column(nullable = false)
    private String refTable;

    @Column(nullable = false)
    private Long refId;

}
