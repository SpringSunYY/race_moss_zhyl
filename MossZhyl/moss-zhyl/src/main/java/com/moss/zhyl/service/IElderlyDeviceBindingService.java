package com.moss.zhyl.service;

import java.util.List;
import com.moss.zhyl.domain.ElderlyDeviceBinding;

/**
 * 长者设备绑定Service接口
 * 
 * @author YY
 * @date 2024-10-23
 */
public interface IElderlyDeviceBindingService 
{
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
     * 批量删除长者设备绑定
     * 
     * @param bindingIds 需要删除的长者设备绑定主键集合
     * @return 结果
     */
    public int deleteElderlyDeviceBindingByBindingIds(Long[] bindingIds);

    /**
     * 删除长者设备绑定信息
     * 
     * @param bindingId 长者设备绑定主键
     * @return 结果
     */
    public int deleteElderlyDeviceBindingByBindingId(Long bindingId);
}
