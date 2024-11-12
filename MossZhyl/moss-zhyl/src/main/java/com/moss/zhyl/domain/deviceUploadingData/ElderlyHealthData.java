package com.moss.zhyl.domain.deviceUploadingData;

/**
 * @Project: MossZhyl
 * @Package: com.moss.zhyl.domain.DeviceUploadingData
 * @Author: YY
 * @CreateTime: 2024-10-31  17:01
 * @Description: ElderlyHealthData
 * ai生成参数类
 * @Version: 1.0
 */
public class ElderlyHealthData {
    private String name; // 姓名
    private String sex; // 性别
    private int age; // 年龄
    private String livingCondition; // 生活状况
    private String disabilityStatus; // 残疾状态
    private String disabilityCondition; // 残疾情况描述
    private String data; // 数据日期

    private VitalSigns avg; // 平均生命体征
    private VitalSigns max; // 最大生命体征
    private VitalSigns lower; // 最小生命体征
    private VitalSigns mode; // 常见生命体征

    /**
     * 内部类，表示生命体征
     */
    public static class VitalSigns {
        private int heartRate; // 心率
        private int dbp; // 舒张压
        private int sdp; // 收缩压
        private int oxygen; // 血氧饱和度
        private String bloodSugar; // 血糖
        private String strTemperature; // 体温

        // Getters and Setters

        public int getHeartRate() {
            return heartRate;
        }

        public void setHeartRate(int heartRate) {
            this.heartRate = heartRate;
        }

        public int getDbp() {
            return dbp;
        }

        public void setDbp(int dbp) {
            this.dbp = dbp;
        }

        public int getSdp() {
            return sdp;
        }

        public void setSdp(int sdp) {
            this.sdp = sdp;
        }

        public int getOxygen() {
            return oxygen;
        }

        public void setOxygen(int oxygen) {
            this.oxygen = oxygen;
        }

        public String getBloodSugar() {
            return bloodSugar;
        }

        public void setBloodSugar(String bloodSugar) {
            this.bloodSugar = bloodSugar;
        }

        public String getStrTemperature() {
            return strTemperature;
        }

        public void setStrTemperature(String strTemperature) {
            this.strTemperature = strTemperature;
        }

        @Override
        public String toString() {
            return "VitalSigns{" +
                    "heartRate=" + heartRate +
                    ", dbp=" + dbp +
                    ", sdp=" + sdp +
                    ", oxygen=" + oxygen +
                    ", bloodSugar='" + bloodSugar + '\'' +
                    ", strTemperature='" + strTemperature + '\'' +
                    '}';
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLivingCondition() {
        return livingCondition;
    }

    public void setLivingCondition(String livingCondition) {
        this.livingCondition = livingCondition;
    }

    public String getDisabilityStatus() {
        return disabilityStatus;
    }

    public void setDisabilityStatus(String disabilityStatus) {
        this.disabilityStatus = disabilityStatus;
    }

    public String getDisabilityCondition() {
        return disabilityCondition;
    }

    public void setDisabilityCondition(String disabilityCondition) {
        this.disabilityCondition = disabilityCondition;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public VitalSigns getAvg() {
        return avg;
    }

    public void setAvg(VitalSigns avg) {
        this.avg = avg;
    }

    public VitalSigns getMax() {
        return max;
    }

    public void setMax(VitalSigns max) {
        this.max = max;
    }

    public VitalSigns getLower() {
        return lower;
    }

    public void setLower(VitalSigns lower) {
        this.lower = lower;
    }

    public VitalSigns getMode() {
        return mode;
    }

    public void setMode(VitalSigns mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", livingCondition='" + livingCondition + '\'' +
                ", disabilityStatus='" + disabilityStatus + '\'' +
                ", disabilityCondition='" + disabilityCondition + '\'' +
                ", data='" + data + '\'' +
                ", avg=" + avg +
                ", max=" + max +
                ", lower=" + lower +
                ", mode=" + mode +
                '}';
    }
}
