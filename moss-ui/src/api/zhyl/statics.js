import request from '@/utils/request'

// 获取统计信息总数
export function getCounts() {
  return request({
    url: '/zhyl/statics/getCounts',
    method: 'get',
  })
}
