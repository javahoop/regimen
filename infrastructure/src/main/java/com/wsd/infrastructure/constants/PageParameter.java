package com.wsd.infrastructure.constants;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * API请求分页参数封装类
 * 需要请求列表的API参数继承此类即可携带分页参数
 * @author wsd
 * @date 2021/6/6
 */
@Schema(description = "API请求分页参数")
public class PageParameter {
    /**
     * 起始页码
     */
    @Parameter(description = "起始页码,从1开始,最小页码为1", example = "1")
    private int page = 1;
    /**
     * 每页返回条目数
     */
    @Parameter(description = "每页返回条目数,最多100条", example = "10")
    private int perPage;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        // 最小页码为1
        this.page = Math.max(page, 1);
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        // 每页最多返回100条
        this.perPage = Math.min(perPage, 100);
    }
}
