import request from '@/utils/request'

// 查询设备通信记录列表
export function listDeviceCommunication(query) {
  return request({
    url: '/zhyl/deviceCommunication/list',
    method: 'get',
    params: query
  })
}

// 查询设备通信记录详细
export function getDeviceCommunication(deviceCommunicationId) {
  return request({
    url: '/zhyl/deviceCommunication/' + deviceCommunicationId,
    method: 'get'
  })
}

// 新增设备通信记录
export function addDeviceCommunication(data) {
  return request({
    url: '/zhyl/deviceCommunication',
    method: 'post',
    data: data
  })
}

// 修改设备通信记录
export function updateDeviceCommunication(data) {
  return request({
    url: '/zhyl/deviceCommunication',
    method: 'put',
    data: data
  })
}

// 删除设备通信记录
export function delDeviceCommunication(deviceCommunicationId) {
  return request({
    url: '/zhyl/deviceCommunication/' + deviceCommunicationId,
    method: 'delete'
  })
}
