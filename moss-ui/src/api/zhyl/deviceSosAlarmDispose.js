import request from '@/utils/request'

// 查询设备预警处理列表
export function listDeviceSosAlarmDispose(query) {
  return request({
    url: '/zhyl/deviceSosAlarmDispose/list',
    method: 'get',
    params: query
  })
}

// 查询设备预警处理详细
export function getDeviceSosAlarmDispose(disposeId) {
  return request({
    url: '/zhyl/deviceSosAlarmDispose/' + disposeId,
    method: 'get'
  })
}

// 新增设备预警处理
export function addDeviceSosAlarmDispose(data) {
  return request({
    url: '/zhyl/deviceSosAlarmDispose',
    method: 'post',
    data: data
  })
}

// 修改设备预警处理
export function updateDeviceSosAlarmDispose(data) {
  return request({
    url: '/zhyl/deviceSosAlarmDispose',
    method: 'put',
    data: data
  })
}

// 删除设备预警处理
export function delDeviceSosAlarmDispose(disposeId) {
  return request({
    url: '/zhyl/deviceSosAlarmDispose/' + disposeId,
    method: 'delete'
  })
}
