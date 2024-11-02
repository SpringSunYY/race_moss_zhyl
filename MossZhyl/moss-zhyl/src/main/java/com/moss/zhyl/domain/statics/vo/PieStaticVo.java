package com.moss.zhyl.domain.statics.vo;

import java.util.List;

/**
 * @Description 饼图
 * @ClassName PieStaticVo
 * @Path com.yy.supermarket.domain
 * @Author YY
 * @Date 2024/4/1 20:12
 * @Version 1.0
 */
public class PieStaticVo {
    private List<String> names;

    private List<Date> dates;
    public static class Date
    {
        private String name;
        private Long value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<Date> getDates() {
        return dates;
    }

    public void setDates(List<Date> dates) {
        this.dates = dates;
    }


}
