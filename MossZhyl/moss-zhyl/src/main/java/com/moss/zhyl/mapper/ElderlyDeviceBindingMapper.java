package com.moss.zhyl.mapper;

import java.util.List;

import com.moss.zhyl.domain.DeviceUploadingData.DeviceUploadingData;
import com.moss.zhyl.domain.ElderlyDeviceBinding;
import com.moss.zhyl.domain.statics.ro.StaticRo;
import org.apache.ibatis.annotations.Param;

/**
 * 长者设备绑定Mapper接口
 *
 * @author YY
 * @date 2024-10-23
 */
public interface ElderlyDeviceBindingMapper {
    /**
     * 查询长者设备绑定
     *
     * @param bindingId 长者设备绑定主键
     * @return 长者设备绑定
     */
    public ElderlyDeviceBinding selectElderlyDeviceBindingByBindingId(Long bindingId);

    /**
     * 查询长者设备绑定列表
     *
     * @param elderlyDeviceBinding 长者设备绑定
     * @return 长者设备绑定集合
     */
    public List<ElderlyDeviceBinding> selectElderlyDeviceBindingList(ElderlyDeviceBinding elderlyDeviceBinding);

    /**
     * 新增长者设备绑定
     *
     * @param elderlyDeviceBinding 长者设备绑定
     * @return 结果
     */
    public int insertElderlyDeviceBinding(ElderlyDeviceBinding elderlyDeviceBinding);

    /**
     * 修改长者设备绑定
     *
     * @param elderlyDeviceBinding 长者设备绑定
     * @return 结果
     */
    public int updateElderlyDeviceBinding(ElderlyDeviceBinding elderlyDeviceBinding);

    /**
     * 删除长者设备绑定
     *
     * @param bindingId 长者设备绑定主键
     * @return 结果
     */
    public int deleteElderlyDeviceBindingByBindingId(Long bindingId);

    /**
     * 批量删除长者设备绑定
     *
     * @param bindingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteElderlyDeviceBindingByBindingIds(Long[] bindingIds);

    /**
     * @description: 根据IMEI查询
     * @author: YY
     * @method: selectElderlyDeviceBindingByDeviceIMEI
     * @date: 2024/10/26 23:35
     * @param:
     * @param: deviceImei
     * @return: com.moss.zhyl.domain.ElderlyDeviceBinding
     **/
    ElderlyDeviceBinding selectElderlyDeviceBindingByDeviceIMEI(@Param("deviceImei") String deviceImei);

    /**
     * description:  查询设备总数
     * author: YY
     * method: selectElderlyDeviceBindingCounts
     * date: 2024/11/1 22:53
     * param:
     *
     * @param: binding
     * return: java.lang.Long
     **/
    Long selectElderlyDeviceBindingCounts(ElderlyDeviceBinding binding);


    /**
     * description: 根据创建时间查询每日创建总数
     * author: YY
     * method: staticByCreateTimeCounts
     * date: 2024/11/2 14:57
     * param:
     * @param: elderlyDeviceBinding
     * return: java.util.List<com.moss.zhyl.domain.statics.ro.StaticRo>
     **/
    List<StaticRo> staticByCreateTimeCounts(ElderlyDeviceBinding elderlyDeviceBinding);
}
