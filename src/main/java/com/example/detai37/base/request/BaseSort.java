package com.example.detai37.base.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BaseSort {
    private String key;
    private Boolean asc = true;
}
