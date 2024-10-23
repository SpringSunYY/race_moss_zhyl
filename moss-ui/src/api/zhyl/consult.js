import request from '@/utils/request'

// 查询咨询列表
export function listConsult(query) {
  return request({
    url: '/zhyl/consult/list',
    method: 'get',
    params: query
  })
}

// 查询咨询详细
export function getConsult(consultId) {
  return request({
    url: '/zhyl/consult/' + consultId,
    method: 'get'
  })
}

// 新增咨询
export function addConsult(data) {
  return request({
    url: '/zhyl/consult',
    method: 'post',
    data: data
  })
}

// 修改咨询
export function updateConsult(data) {
  return request({
    url: '/zhyl/consult',
    method: 'put',
    data: data
  })
}

// 删除咨询
export function delConsult(consultId) {
  return request({
    url: '/zhyl/consult/' + consultId,
    method: 'delete'
  })
}
