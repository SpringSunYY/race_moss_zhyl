import request from '@/utils/request'

// 查询健康报告列表
export function healthReportList(query) {
  return request({
    url: '/app/zhyl/healthReport/list',
    method: 'get',
    params: query
  })
}

