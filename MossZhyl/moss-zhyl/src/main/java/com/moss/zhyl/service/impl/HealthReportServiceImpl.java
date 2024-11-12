package com.moss.zhyl.service.impl;

import java.util.List;

import com.moss.common.utils.DateUtils;
import com.moss.common.utils.SecurityUtils;
import com.moss.common.utils.StringUtils;
import com.moss.common.core.domain.entity.UserInfo;
import com.moss.zhyl.domain.enums.DelFlagEnum;
import com.moss.zhyl.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.moss.zhyl.mapper.HealthReportMapper;
import com.moss.zhyl.domain.healthReport.HealthReport;
import com.moss.zhyl.service.IHealthReportService;

import static com.moss.common.constant.PermissionsConstants.ZHYL_HEALTH_REPORT_LOOK_DELETE;

/**
 * 健康报告Service业务层处理
 *
 * @author YY
 * @date 2024-10-23
 */
@Service
public class HealthReportServiceImpl implements IHealthReportService {
    @Autowired
    private HealthReportMapper healthReportMapper;

    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 查询健康报告
     *
     * @param healthReportId 健康报告主键
     * @return 健康报告
     */
    @Override
    public HealthReport selectHealthReportByHealthReportId(Long healthReportId) {
        return healthReportMapper.selectHealthReportByHealthReportId(healthReportId);
    }

    /**
     * 查询健康报告列表
     *
     * @param healthReport 健康报告
     * @return 健康报告
     */
    @Override
    public List<HealthReport> selectHealthReportList(HealthReport healthReport) {
        if (!SecurityUtils.hasPermi(ZHYL_HEALTH_REPORT_LOOK_DELETE)) {
            healthReport.setDelFlag(DelFlagEnum.DEL_FLAG_0.getValue());
        }
        List<HealthReport> healthReportList = healthReportMapper.selectHealthReportList(healthReport);
        for (HealthReport info : healthReportList) {
            UserInfo userInfo = userInfoService.selectUserInfoByUserInfoIdResultUserInfo(info.getUserInfoId());
            if (StringUtils.isNotNull(userInfo)) {
                info.setUserInfoName(userInfo.getUserInfoName());
            }
        }
        return healthReportList;
    }

    /**
     * 新增健康报告
     *
     * @param healthReport 健康报告
     * @return 结果
     */
    @Override
    public int insertHealthReport(HealthReport healthReport) {
        healthReport.setCreateTime(DateUtils.getNowDate());
        return healthReportMapper.insertHealthReport(healthReport);
    }

    /**
     * 修改健康报告
     *
     * @param healthReport 健康报告
     * @return 结果
     */
    @Override
    public int updateHealthReport(HealthReport healthReport) {
        return healthReportMapper.updateHealthReport(healthReport);
    }

    /**
     * 批量删除健康报告
     *
     * @param healthReportIds 需要删除的健康报告主键
     * @return 结果
     */
    @Override
    public int deleteHealthReportByHealthReportIds(Long[] healthReportIds) {
        return healthReportMapper.deleteHealthReportByHealthReportIds(healthReportIds);
    }

    /**
     * 删除健康报告信息
     *
     * @param healthReportId 健康报告主键
     * @return 结果
     */
    @Override
    public int deleteHealthReportByHealthReportId(Long healthReportId) {
        return healthReportMapper.deleteHealthReportByHealthReportId(healthReportId);
    }
}
