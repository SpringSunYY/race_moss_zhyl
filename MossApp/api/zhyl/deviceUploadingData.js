import request from '@/utils/request'

// 查询设备上传数据列表
export function newHealthData(query) {
    return request({
        url: '/app/zhyl/deviceUploadingData/newHealthData',
        method: 'get',
        params: query
    })
}

// 查询设备上传数据列表
export function healthData(query) {
    return request({
        url: '/app/zhyl/deviceUploadingData/healthData',
        method: 'get',
        params: query
    })
}
