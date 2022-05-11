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
public class Account {
    @Id
    private String userName;
    private String passWord;
    private String userId;
    @Column(columnDefinition = "NVarchar(50)")
    private String userType;
}
