import request from '@/utils/request'

// 查询长者设备绑定列表
export function listElderlyDeviceBinding(query) {
  return request({
    url: '/app/zhyl/elderlyDeviceBinding/list',
    method: 'get',
    params: query
  })
}

// 查询长者设备绑定详细
export function getElderlyDeviceBinding(bindingId) {
  return request({
    url: '/zhyl/elderlyDeviceBinding/' + bindingId,
    method: 'get'
  })
}

// 新增长者设备绑定
export function addElderlyDeviceBinding(data) {
  return request({
    url: '/zhyl/elderlyDeviceBinding',
    method: 'post',
    data: data
  })
}

// 修改长者设备绑定
export function updateElderlyDeviceBinding(data) {
  return request({
    url: '/zhyl/elderlyDeviceBinding',
    method: 'put',
    data: data
  })
}

// 删除长者设备绑定
export function delElderlyDeviceBinding(bindingId) {
  return request({
    url: '/zhyl/elderlyDeviceBinding/' + bindingId,
    method: 'delete'
  })
}
