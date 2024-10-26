import request from '@/utils/request'

// 查询设备上传数据列表
export function listDeviceUploadingData(query) {
  return request({
    url: '/zhyl/deviceUploadingData/list',
    method: 'get',
    params: query
  })
}

// 查询设备上传数据详细
export function getDeviceUploadingData(dataId) {
  return request({
    url: '/zhyl/deviceUploadingData/' + dataId,
    method: 'get'
  })
}

// 新增设备上传数据
export function addDeviceUploadingData(data) {
  return request({
    url: '/zhyl/deviceUploadingData',
    method: 'post',
    data: data
  })
}

// 修改设备上传数据
export function updateDeviceUploadingData(data) {
  return request({
    url: '/zhyl/deviceUploadingData',
    method: 'put',
    data: data
  })
}

// 删除设备上传数据
export function delDeviceUploadingData(dataId) {
  return request({
    url: '/zhyl/deviceUploadingData/' + dataId,
    method: 'delete'
  })
}
