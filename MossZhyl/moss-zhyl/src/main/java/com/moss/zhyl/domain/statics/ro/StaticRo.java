package com.moss.zhyl.domain.statics.ro;

/**
 * @Description 统计返回
 * @ClassName LineStaticRo
 * @Path com.yy.supermarket.domain.ro
 * @Author YY
 * @Date 2024/4/1 19:48
 * @Version 1.0
 */
public class StaticRo {
    private String name;
    private Long total;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "StaticRo{" +
                "name='" + name + '\'' +
                ", total=" + total +
                '}';
    }
}
