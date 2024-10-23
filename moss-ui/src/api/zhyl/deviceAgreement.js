import request from '@/utils/request'

// 查询设备协议列表
export function listDeviceAgreement(query) {
  return request({
    url: '/zhyl/deviceAgreement/list',
    method: 'get',
    params: query
  })
}

// 查询设备协议详细
export function getDeviceAgreement(agreementId) {
  return request({
    url: '/zhyl/deviceAgreement/' + agreementId,
    method: 'get'
  })
}

// 新增设备协议
export function addDeviceAgreement(data) {
  return request({
    url: '/zhyl/deviceAgreement',
    method: 'post',
    data: data
  })
}

// 修改设备协议
export function updateDeviceAgreement(data) {
  return request({
    url: '/zhyl/deviceAgreement',
    method: 'put',
    data: data
  })
}

// 删除设备协议
export function delDeviceAgreement(agreementId) {
  return request({
    url: '/zhyl/deviceAgreement/' + agreementId,
    method: 'delete'
  })
}
