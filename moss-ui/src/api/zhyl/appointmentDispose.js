import request from '@/utils/request'

// 查询预约处理列表
export function listAppointmentDispose(query) {
  return request({
    url: '/zhyl/appointmentDispose/list',
    method: 'get',
    params: query
  })
}

// 查询预约处理详细
export function getAppointmentDispose(disposeId) {
  return request({
    url: '/zhyl/appointmentDispose/' + disposeId,
    method: 'get'
  })
}

// 新增预约处理
export function addAppointmentDispose(data) {
  return request({
    url: '/zhyl/appointmentDispose',
    method: 'post',
    data: data
  })
}

// 修改预约处理
export function updateAppointmentDispose(data) {
  return request({
    url: '/zhyl/appointmentDispose',
    method: 'put',
    data: data
  })
}

// 删除预约处理
export function delAppointmentDispose(disposeId) {
  return request({
    url: '/zhyl/appointmentDispose/' + disposeId,
    method: 'delete'
  })
}
