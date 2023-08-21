package com.example.authenticationService.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PageRequestUtil {

    @Value("${spring.data.rest.default-page-size}")
    private static int defaultPageSize;

    public static PageRequest createPageRequest(int page, Map<String, String> columnsAndOrder) {
        Sort sort = Sort.unsorted();

        for (String column : columnsAndOrder.keySet()) {
            if (columnsAndOrder.get(column).equals("asc")) {
                sort = sort.and(Sort.by(column).ascending());
            } else {
                sort = sort.and(Sort.by(column).descending());
            }
        }
        defaultPageSize = 5;
        return PageRequest.of(page, defaultPageSize, sort);
    }
}
