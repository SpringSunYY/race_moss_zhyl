import request from '@/utils/request'

// 登录方法
export function login(username, password) {
    const data = {
        username,
        password,
        // code,
        // uuid
    }
    return request({
        'url': '/app/login',
        headers: {
            isToken: false
        },
        'method': 'post',
        'data': data
    })
}

// 注册方法
export function register(data) {
    return request({
        url: '/register',
        headers: {
            isToken: false
        },
        method: 'post',
        data: data
    })
}

// 获取用户详细信息
export function getInfo() {
    return request({
        'url': '/app/userInfo',
        'method': 'get'
    })
}

// 退出方法
export function logout() {
    return request({
        'url': '/logout',
        'method': 'post'
    })
}

// 获取验证码
export function getCodeImg() {
    return request({
        'url': '/captchaImage',
        headers: {
            isToken: false
        },
        method: 'get',
        timeout: 20000
    })
}

//小程序微信登录
export function miniProgramLogin(code) {
    return request({
        'url': '/app/login/miniProgramLogin',
        'method': 'get',
        headers: {
            isToken: false
        },
        params: {code}
    })
}
