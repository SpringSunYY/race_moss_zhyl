import request from '@/utils/request'

// 查询预约列表
export function listAppointment(query) {
  return request({
    url: '/zhyl/appointment/list',
    method: 'get',
    params: query
  })
}

// 查询预约详细
export function getAppointment(appointmentId) {
  return request({
    url: '/zhyl/appointment/' + appointmentId,
    method: 'get'
  })
}

// 新增预约
export function addAppointment(data) {
  return request({
    url: '/zhyl/appointment',
    method: 'post',
    data: data
  })
}

// 修改预约
export function updateAppointment(data) {
  return request({
    url: '/zhyl/appointment',
    method: 'put',
    data: data
  })
}

// 删除预约
export function delAppointment(appointmentId) {
  return request({
    url: '/zhyl/appointment/' + appointmentId,
    method: 'delete'
  })
}
