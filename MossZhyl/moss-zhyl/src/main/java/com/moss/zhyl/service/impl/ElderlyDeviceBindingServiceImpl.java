package com.moss.zhyl.service.impl;

import java.util.List;
import com.moss.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.ElderlyDeviceBindingMapper;
import com.moss.zhyl.domain.ElderlyDeviceBinding;
import com.moss.zhyl.service.IElderlyDeviceBindingService;

/**
 * 长者设备绑定Service业务层处理
 * 
 * @author YY
 * @date 2024-10-23
 */
@Service
public class ElderlyDeviceBindingServiceImpl implements IElderlyDeviceBindingService 
{
    @Autowired
    private ElderlyDeviceBindingMapper elderlyDeviceBindingMapper;

    /**
     * 查询长者设备绑定
     * 
     * @param bindingId 长者设备绑定主键
     * @return 长者设备绑定
     */
    @Override
    public ElderlyDeviceBinding selectElderlyDeviceBindingByBindingId(Long bindingId)
    {
        return elderlyDeviceBindingMapper.selectElderlyDeviceBindingByBindingId(bindingId);
    }

    /**
     * 查询长者设备绑定列表
     * 
     * @param elderlyDeviceBinding 长者设备绑定
     * @return 长者设备绑定
     */
    @Override
    public List<ElderlyDeviceBinding> selectElderlyDeviceBindingList(ElderlyDeviceBinding elderlyDeviceBinding)
    {
        return elderlyDeviceBindingMapper.selectElderlyDeviceBindingList(elderlyDeviceBinding);
    }

    /**
     * 新增长者设备绑定
     * 
     * @param elderlyDeviceBinding 长者设备绑定
     * @return 结果
     */
    @Override
    public int insertElderlyDeviceBinding(ElderlyDeviceBinding elderlyDeviceBinding)
    {
        elderlyDeviceBinding.setCreateTime(DateUtils.getNowDate());
        return elderlyDeviceBindingMapper.insertElderlyDeviceBinding(elderlyDeviceBinding);
    }

    /**
     * 修改长者设备绑定
     * 
     * @param elderlyDeviceBinding 长者设备绑定
     * @return 结果
     */
    @Override
    public int updateElderlyDeviceBinding(ElderlyDeviceBinding elderlyDeviceBinding)
    {
        elderlyDeviceBinding.setUpdateTime(DateUtils.getNowDate());
        return elderlyDeviceBindingMapper.updateElderlyDeviceBinding(elderlyDeviceBinding);
    }

    /**
     * 批量删除长者设备绑定
     * 
     * @param bindingIds 需要删除的长者设备绑定主键
     * @return 结果
     */
    @Override
    public int deleteElderlyDeviceBindingByBindingIds(Long[] bindingIds)
    {
        return elderlyDeviceBindingMapper.deleteElderlyDeviceBindingByBindingIds(bindingIds);
    }

    /**
     * 删除长者设备绑定信息
     * 
     * @param bindingId 长者设备绑定主键
     * @return 结果
     */
    @Override
    public int deleteElderlyDeviceBindingByBindingId(Long bindingId)
    {
        return elderlyDeviceBindingMapper.deleteElderlyDeviceBindingByBindingId(bindingId);
    }
}
