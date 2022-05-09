package com.example.detai37.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SaleStaff {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(columnDefinition = "NVarchar(255)")
    private String firstName;
    @Column(columnDefinition = "NVarchar(255)")
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String urlImage;
    private Integer status;
}
