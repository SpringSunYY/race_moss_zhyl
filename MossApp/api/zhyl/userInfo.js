import request from '@/utils/request'
import upload from '@/utils/upload'
// 获取验证码
export function getCode(phoneNumber) {
    return request({
        'url': '/app/zhyl/userInfo/code',
        method: 'get',
        timeout: 20000,
        params: {
            phoneNumber: phoneNumber
        }
    })
}

export function binding(phoneNumber, captcha, idCard) {
    return request({
        'url': '/app/zhyl/userInfo/binding',
        method: 'get',
        timeout: 20000,
        params: {
            phoneNumber: phoneNumber,
            captcha: captcha,
            idCard: idCard
        }
    })
}

// 用户密码重置
export function updateUserPwd(oldPassword, newPassword) {
    const data = {
        oldPassword,
        newPassword
    }
    return request({
        url: '/app/zhyl/userInfo/updatePwd',
        method: 'put',
        params: data
    })
}

// 用户头像上传
export function uploadAvatar(data) {
    return upload({
        url: '/app/zhyl/userInfo/avatar',
        name: data.name,
        filePath: data.filePath
    })
}
