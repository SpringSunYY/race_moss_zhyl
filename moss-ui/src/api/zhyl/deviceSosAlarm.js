import request from '@/utils/request'

// 查询设备预警列表
export function listDeviceSosAlarm(query) {
  return request({
    url: '/zhyl/deviceSosAlarm/list',
    method: 'get',
    params: query
  })
}

// 查询设备预警详细
export function getDeviceSosAlarm(sosAlarmId) {
  return request({
    url: '/zhyl/deviceSosAlarm/' + sosAlarmId,
    method: 'get'
  })
}

// 新增设备预警
export function addDeviceSosAlarm(data) {
  return request({
    url: '/zhyl/deviceSosAlarm',
    method: 'post',
    data: data
  })
}

// 修改设备预警
export function updateDeviceSosAlarm(data) {
  return request({
    url: '/zhyl/deviceSosAlarm',
    method: 'put',
    data: data
  })
}

// 删除设备预警
export function delDeviceSosAlarm(sosAlarmId) {
  return request({
    url: '/zhyl/deviceSosAlarm/' + sosAlarmId,
    method: 'delete'
  })
}
