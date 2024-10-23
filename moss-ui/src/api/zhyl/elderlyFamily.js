import request from '@/utils/request'

// 查询家属信息列表
export function listElderlyFamily(query) {
  return request({
    url: '/zhyl/elderlyFamily/list',
    method: 'get',
    params: query
  })
}

// 查询家属信息详细
export function getElderlyFamily(elderlyFamilyId) {
  return request({
    url: '/zhyl/elderlyFamily/' + elderlyFamilyId,
    method: 'get'
  })
}

// 新增家属信息
export function addElderlyFamily(data) {
  return request({
    url: '/zhyl/elderlyFamily',
    method: 'post',
    data: data
  })
}

// 修改家属信息
export function updateElderlyFamily(data) {
  return request({
    url: '/zhyl/elderlyFamily',
    method: 'put',
    data: data
  })
}

// 删除家属信息
export function delElderlyFamily(elderlyFamilyId) {
  return request({
    url: '/zhyl/elderlyFamily/' + elderlyFamilyId,
    method: 'delete'
  })
}
