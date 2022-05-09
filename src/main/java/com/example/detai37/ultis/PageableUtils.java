package com.example.detai37.ultis;

import com.example.detai37.base.request.BaseSort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PageableUtils {
    public static Pageable convertPageableAndSort(Integer pageNumber, Integer pageSize, List<BaseSort> sorts) {
        Sort sort = null;
        Pageable pageable;
        if (sorts != null && sorts.size() > 0) {
            for (BaseSort item : sorts) {
                if (!item.getAsc()) {
                    sort = (sort == null) ? Sort.by(item.getKey()).descending() : sort.and(Sort.by(item.getKey()).descending());
                } else {
                    sort = (sort == null) ? Sort.by(item.getKey()) : sort.and(Sort.by(item.getKey()));
                }
            }
            pageable = PageRequest.of(pageNumber, pageSize, sort);
        } else {
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return pageable;
    }
}
