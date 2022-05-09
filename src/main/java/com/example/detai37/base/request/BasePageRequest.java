package com.example.detai37.base.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasePageRequest {
    private Integer pageNumber = 0;

    private Integer pageSize = 10;
}
