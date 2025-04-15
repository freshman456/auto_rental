package com.rental.auto_rental.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author YinHang
 * @project IntelliJ IDEA
 * @date 2025/4/14
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouteVo {
    private String path;
    private String component;
    private String name;
    private Boolean alwaysShow;
    private List<RouteVo> children;
    private Meta meta;
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    public class Meta{
        private String title;
        private String icon;
        private Object[] roles;
    }
}
