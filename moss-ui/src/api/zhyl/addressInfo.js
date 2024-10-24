import request from '@/utils/request'

// 查询地址信息列表
export function listAddressInfo(query) {
  return request({
    url: '/zhyl/addressInfo/list',
    method: 'get',
    params: query
  })
}
// 查询地址信息列表 树形结构
export function listAddressTreeInfo(query) {
  return request({
    url: '/zhyl/addressInfo/addressTree',
    method: 'get',
    params: query
  })
}

// 查询地址信息详细
export function getAddressInfo(addressId) {
  return request({
    url: '/zhyl/addressInfo/' + addressId,
    method: 'get'
  })
}

// 新增地址信息
export function addAddressInfo(data) {
  return request({
    url: '/zhyl/addressInfo',
    method: 'post',
    data: data
  })
}

// 修改地址信息
export function updateAddressInfo(data) {
  return request({
    url: '/zhyl/addressInfo',
    method: 'put',
    data: data
  })
}

// 删除地址信息
export function delAddressInfo(addressId) {
  return request({
    url: '/zhyl/addressInfo/' + addressId,
    method: 'delete'
  })
}
