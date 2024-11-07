import config from '@/config'
import storage from '@/utils/storage'
import constant from '@/utils/constant'
import {login, logout, getInfo} from '@/api/login'
import {getToken, setToken, removeToken} from '@/utils/auth'
import {miniProgramLogin} from "../../api/login";
import code from "uview-ui/libs/config/props/code";

const baseUrl = config.baseUrl

const user = {
    state: {
        token: getToken(),
        name: storage.get(constant.name),
        avatar: storage.get(constant.avatar),
        roles: storage.get(constant.roles),
        role: storage.get(constant.role),
        permissions: storage.get(constant.permissions)
    },

    mutations: {
        SET_TOKEN: (state, token) => {
            state.token = token
        },
        SET_NAME: (state, name) => {
            state.name = name
            storage.set(constant.name, name)
        },
        SET_AVATAR: (state, avatar) => {
            state.avatar = avatar
            storage.set(constant.avatar, avatar)
        },
        SET_ROLES: (state, roles) => {
            state.roles = roles
            storage.set(constant.roles, roles)
        },
        SET_ROLE: (state, roles) => {
            state.role = roles
            storage.set(constant.role, roles)
        },
        SET_PERMISSIONS: (state, permissions) => {
            state.permissions = permissions
            storage.set(constant.permissions, permissions)
        }
    },

    actions: {
        // 登录
        Login({commit}, userInfo) {
            const username = userInfo.username.trim()
            const password = userInfo.password
            // const code = userInfo.code
            // const uuid = userInfo.uuid
            return new Promise((resolve, reject) => {
                login(username, password).then(res => {
                    setToken(res.token)
                    commit('SET_TOKEN', res.token)
                    resolve()
                }).catch(error => {
                    reject(error)
                })
            })
        },

        MiniProgramLogin({commit}, code) {
            const wxCode = code
            return new Promise((resolve, reject) => {
                miniProgramLogin(wxCode).then(res => {
                    setToken(res.token)
                    commit('SET_TOKEN', res.token)
                    resolve()
                }).catch(error =>
                    reject(error))
            })
        },

        // 获取用户信息
        GetInfo({commit, state}) {
            return new Promise((resolve, reject) => {
                getInfo().then(res => {
                    const user = res.userInfo
                    console.log('user', user)
                    const avatar = (user == null || user.userInfoProfile === "" || user.userInfoProfile == null) ? require("@/static/images/profile.jpg") : baseUrl + user.userInfoProfile
                    const username = (user == null || user.userInfoName === "" || user.userInfoName == null) ? "" : user.userInfoName
                    if (user.userInfoRole && user.userInfoRole !== "") {
                        //app之后只会有一种角色
                        commit('SET_ROLE', user.userInfoRole)
                        commit('SET_PERMISSIONS', res.permissions)
                    } else {
                        commit('SET_ROLES', ['ROLE_DEFAULT'])
                        commit('SET_ROLE', 'ROLE_DEFAULT')
                    }
                    commit('SET_NAME', username)
                    commit('SET_AVATAR', avatar)
                    resolve(res)
                }).catch(error => {
                    reject(error)
                })
            })
        },

        // 退出系统
        LogOut({commit, state}) {
            return new Promise((resolve, reject) => {
                logout(state.token).then(() => {
                    commit('SET_TOKEN', '')
                    commit('SET_ROLES', [])
                    commit('SET_ROLE', [])
                    commit('SET_PERMISSIONS', [])
                    removeToken()
                    storage.clean()
                    resolve()
                }).catch(error => {
                    reject(error)
                })
            })
        }
    }
}

export default user
