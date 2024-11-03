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
//获取年龄分布
export function getUserInfoElderlyAge(query) {
  return request({
    url: '/zhyl/statics/getUserInfoElderlyAge',
    method: 'get',
    params: query
  })
}
//获取各种设备类型总数
export function getDeviceByDeviceTypeCount(query) {
  return request({
    url: '/zhyl/statics/getDeviceByDeviceTypeCount',
    method: 'get',
    params: query
  })
}
