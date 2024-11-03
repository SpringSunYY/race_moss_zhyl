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

    private List<Data> datas;
    public static class Data
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

    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }
}
