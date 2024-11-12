import request from '@/utils/request'

// 查询健康报告列表
export function healthReportList(query) {
  return request({
    url: '/app/zhyl/healthReport/list',
    method: 'get',
    params: query
  })
}

// 查询健康报告详细
export function getHealthReport(healthReportId) {
  return request({
    url: '/app/zhyl/healthReport/' + healthReportId,
    method: 'get'
  })
}



