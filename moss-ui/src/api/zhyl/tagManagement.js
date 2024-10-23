import request from '@/utils/request'

// 查询标签列表
export function listTagManagement(query) {
  return request({
    url: '/zhyl/tagManagement/list',
    method: 'get',
    params: query
  })
}

// 查询标签详细
export function getTagManagement(tagId) {
  return request({
    url: '/zhyl/tagManagement/' + tagId,
    method: 'get'
  })
}

// 新增标签
export function addTagManagement(data) {
  return request({
    url: '/zhyl/tagManagement',
    method: 'post',
    data: data
  })
}

// 修改标签
export function updateTagManagement(data) {
  return request({
    url: '/zhyl/tagManagement',
    method: 'put',
    data: data
  })
}

// 删除标签
export function delTagManagement(tagId) {
  return request({
    url: '/zhyl/tagManagement/' + tagId,
    method: 'delete'
  })
}
