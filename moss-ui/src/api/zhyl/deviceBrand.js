import request from '@/utils/request'

// 查询设备品牌列表
export function listDeviceBrand(query) {
  return request({
    url: '/zhyl/deviceBrand/list',
    method: 'get',
    params: query
  })
}

// 查询设备品牌详细
export function getDeviceBrand(brandId) {
  return request({
    url: '/zhyl/deviceBrand/' + brandId,
    method: 'get'
  })
}

// 新增设备品牌
export function addDeviceBrand(data) {
  return request({
    url: '/zhyl/deviceBrand',
    method: 'post',
    data: data
  })
}

// 修改设备品牌
export function updateDeviceBrand(data) {
  return request({
    url: '/zhyl/deviceBrand',
    method: 'put',
    data: data
  })
}

// 删除设备品牌
export function delDeviceBrand(brandId) {
  return request({
    url: '/zhyl/deviceBrand/' + brandId,
    method: 'delete'
  })
}
