import request from '@/utils/request'

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
