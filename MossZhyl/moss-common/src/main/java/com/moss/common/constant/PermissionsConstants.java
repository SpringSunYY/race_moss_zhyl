package com.moss.common.constant;

/**
 * @Project: MossZhyl
 * @Package: com.moss.common.constant
 * @Author: YY
 * @CreateTime: 2024-10-24  22:27
 * @Description: PermissionsConstants
 * 权限
 * @Version: 1.0
 */
public class PermissionsConstants {
    /**
     * 修改用户密码权限
     */
    public static final String ZHYL_USERINFO_UPDATE_PASSWORD="zhyl:userInfo:updatePassword";

    /**
     * 查看用户删除
     */
    public static final String ZHYL_USER_INFO_LOOK_DELETE="zhyl:userInfo:lookDelete";
    /**
     * 查看设备删除
     */
    public static final String ZHYL_DEVICE_LOOK_DELETE = "zhyl:device:lookDelete";
    /**
     * 查看绑定设备删除
     */
    public static final String ZHYL_ELDERLY_DEVICE_BINDING_LOOK_DELETE = "zhyl:device:lookDelete";
    /**
     * 查看sos预警处理删除
     */
    public static final String ZHYL_DEVICE_SOS_ALARM_DISPOSE_LOOK_DELETE="zhyl:deviceSosAlarmDispose:lookDelete";
    /**
     * 查看设备上传数据删除
     */
    public static final String ZHYL_DEVICE_UPLOADING_DATA_LOOK_DELETE="zhyl:deviceUploadingData:lookDelete";
    /**
     * 查看健康数据删除
     */
    public static final String ZHYL_HEALTH_REPORT_LOOK_DELETE = "zhyl:healthReport:lookDelete";
}
