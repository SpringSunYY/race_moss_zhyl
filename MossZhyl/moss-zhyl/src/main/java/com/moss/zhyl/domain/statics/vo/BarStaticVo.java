package com.moss.zhyl.domain.statics.vo;

import java.util.List;

/**
 * @Description BarStaticVo 柱形图
 * @ClassName LineStaticVo
 * @Path com.yy.supermarket.domain.vo
 * @Author YY
 * @Date 2024/4/1 19:41
 * @Version 1.0
 */
public class BarStaticVo {
   private List<String> names;

    private List<Long> totals;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<Long> getTotals() {
        return totals;
    }

    public void setTotals(List<Long> totals) {
        this.totals = totals;
    }
}
