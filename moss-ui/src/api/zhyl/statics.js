import request from '@/utils/request'

// 获取统计信息总数
export function getCounts() {
  return request({
    url: '/zhyl/statics/getCounts',
    method: 'get',
  })
}

export function getUserinfoFamilyStaticByCreateTimeCounts(query) {
  return request({
    url: '/zhyl/statics/getUserinfoFamilyStaticByCreateTimeCounts',
    method: 'get',
    params: query
  })
}export function getUserinfoElderlyStaticByCreateTimeCounts(query) {
  return request({
    url: '/zhyl/statics/getUserinfoElderlyStaticByCreateTimeCounts',
    method: 'get',
    params: query
  })
}export function getUploadingDateStaticByCreateTimeCounts(query) {
  return request({
    url: '/zhyl/statics/getUploadingDateStaticByCreateTimeCounts',
    method: 'get',
    params: query
  })
}

export function getBindingStaticByCreateTimeCounts(query) {
  return request({
    url: '/zhyl/statics/getBindingStaticByCreateTimeCounts',
    method: 'get',
    params: query
  })
}
