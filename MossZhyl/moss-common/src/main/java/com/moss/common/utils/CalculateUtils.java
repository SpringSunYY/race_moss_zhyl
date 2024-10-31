package com.moss.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

/**
 * @Project: MossZhyl
 * @Package: com.moss.common.utils
 * @Author: YY
 * @CreateTime: 2024-10-31  19:05
 * @Description: CalculateUtils
 * 计算工具类
 * @Version: 1.0
 */
public class CalculateUtils {

    public static final String ID_CARD_DATE_FORMAT="yyyyMMdd";
    public static final String BIRTHDAY_DATE_FORMAT="yyyy-MM-dd";

    /**
     * 根据身份证获取年龄
     * @param idCard 身份证
     * @return
     */
    public static int calculateAgeByIdCard(String idCard){
        if (StringUtils.isEmpty(idCard)||idCard.length()<18) {
            return -1;
        }
        //生日信息
        String birthDateStr = idCard.substring(6, 14);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ID_CARD_DATE_FORMAT);
        LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
        LocalDate localDate = LocalDate.now();
        //返回年龄
        return (int) ChronoUnit.YEARS.between(birthDate,localDate);
    }

    /**
     * 根据身份证获取生日
     *
     * @param idCard 身份证
     * @return
     */
    public static String calculateBirthdayByIdCard(String idCard) {
        if (StringUtils.isEmpty(idCard) || idCard.length() < 18) {
            return "";
        }
        //生日信息
        String birthDateStr = idCard.substring(6, 14);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ID_CARD_DATE_FORMAT);
        LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
        DateTimeFormatter birthDateF = DateTimeFormatter.ofPattern(BIRTHDAY_DATE_FORMAT);
        //返回生日
        return birthDate.format(birthDateF);
    }

    /**
     * @description: 计算众数
     * @author: YY
     * @method: getMode
     * @date: 2024/10/31 21:45
     * @param:
     * @param: countMap
     * @return: T
     **/
    public static  <T> T getMode(Map<T, Integer> countMap) {
        T mode = null;
        int maxCount = 0;
        for (Map.Entry<T, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mode = entry.getKey();
            }
        }
        return mode;
    }
}
