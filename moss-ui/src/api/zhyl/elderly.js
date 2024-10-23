import request from '@/utils/request'

// 查询长者信息列表
export function listElderly(query) {
  return request({
    url: '/zhyl/elderly/list',
    method: 'get',
    params: query
  })
}

// 查询长者信息详细
export function getElderly(elderlyId) {
  return request({
    url: '/zhyl/elderly/' + elderlyId,
    method: 'get'
  })
}

// 新增长者信息
export function addElderly(data) {
  return request({
    url: '/zhyl/elderly',
    method: 'post',
    data: data
  })
}

// 修改长者信息
export function updateElderly(data) {
  return request({
    url: '/zhyl/elderly',
    method: 'put',
    data: data
  })
}

// 删除长者信息
export function delElderly(elderlyId) {
  return request({
    url: '/zhyl/elderly/' + elderlyId,
    method: 'delete'
  })
}
