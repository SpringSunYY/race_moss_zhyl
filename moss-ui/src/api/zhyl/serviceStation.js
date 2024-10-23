import request from '@/utils/request'

// 查询服务站信息列表
export function listServiceStation(query) {
  return request({
    url: '/zhyl/serviceStation/list',
    method: 'get',
    params: query
  })
}

// 查询服务站信息详细
export function getServiceStation(serviceStationId) {
  return request({
    url: '/zhyl/serviceStation/' + serviceStationId,
    method: 'get'
  })
}

// 新增服务站信息
export function addServiceStation(data) {
  return request({
    url: '/zhyl/serviceStation',
    method: 'post',
    data: data
  })
}

// 修改服务站信息
export function updateServiceStation(data) {
  return request({
    url: '/zhyl/serviceStation',
    method: 'put',
    data: data
  })
}

// 删除服务站信息
export function delServiceStation(serviceStationId) {
  return request({
    url: '/zhyl/serviceStation/' + serviceStationId,
    method: 'delete'
  })
}
