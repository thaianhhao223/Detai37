package com.example.detai37.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ProductBrand {
    @Id
    private String id;
    @Column(columnDefinition = "NVarchar(255)")
    private String name;
}
