package com.example.detai37.entity;

import lombok.*;

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
    private String userType;
}
