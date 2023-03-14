package com.wsd.infrastructure.response;

import com.github.pagehelper.Page;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * API列表数据封装类
 * @author wsd
 * @date 2021/12/30
 */
@Schema(description = "列表数据响应结果")
public class ListResult<T> {
    /**
     * 数据总条目数
     */
    @Schema(description = "总条目数", example = "10")
    private long total;
    /**
     * 列表数据
     */
    @Schema(description = "列表数据")
    private List<T> item;

    /**
     * 构造列表数据
     * @param item 列表数据
     */
    public ListResult(List<T> item) {
        this.item = item;
        this.total = ((Page<T>) item).getTotal();
    }

    /**
     * 有参构造器
     *
     * @param total 条数
     * @param item 集合
     */
    public ListResult(long total, List<T> item) {
        this.item = item;
        this.total = total;
    }

    /**
     * 返回列表数据
     */
    public static <T> ListResult<T> set(List<T> item) {
        return new ListResult<>(item);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getItem() {
        return item;
    }

    public void setItem(List<T> item) {
        this.item = item;
    }
}
