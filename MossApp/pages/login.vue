<template>
  <view class="normal-login-container">
    <view class="logo-content align-center justify-center flex">
      <image style="width: 100rpx;height: 100rpx;" :src="globalConfig.appInfo.logo" mode="widthFix"></image>
      <text class="title">moss-智慧养老登录</text>
    </view>
    <view class="login-form-content" v-if="!isWxLogin">
      <view class="input-item flex align-center">
        <view class="iconfont icon-user icon"></view>
        <input v-model="loginForm.username" class="input" type="text" placeholder="请输入账号" maxlength="30"/>
      </view>
      <view class="input-item flex align-center">
        <view class="iconfont icon-password icon"></view>
        <input v-model="loginForm.password" type="password" class="input" placeholder="请输入密码" maxlength="20"/>
      </view>
      <view class="input-item flex align-center" style="width: 60%;margin: 0px;" v-if="captchaEnabled">
        <view class="iconfont icon-code icon"></view>
        <input v-model="loginForm.code" type="number" class="input" placeholder="请输入验证码" maxlength="4"/>
        <view class="login-code">
          <image :src="codeUrl" @click="getCode" class="login-code-img"></image>
        </view>
      </view>
      <view class="action-btn">
        <button @click="handleLogin" class="login-btn cu-btn block bg-blue lg round">登录</button>
      </view>
      <view class="reg text-center" v-if="register">
        <text class="text-grey1">没有账号？</text>
        <text @click="handleUserRegister" class="text-blue">立即注册</text>
      </view>
    </view>

    <view class="userinfo" v-if="isWxLogin">
      <block>
        <u-button v-if="userInfo.nickName===undefined||userInfo.nickName===null" open-type="getUserInfo"
                  @click="getUserInfo" shape="circle"
                  size="large"
                  :loading="isGetWxLoading"
                  loadingText="获取中"
                  type="primary" text="获取头像昵称"
                  class="custom-button"></u-button>
      </block>
      <block v-if="userInfo.nickName!=null">
        <image class="userinfo-avatar" :src="userInfo.avatarUrl" mode="cover"></image>
        <text class="userinfo-nickname">用户名：{{ userInfo.nickName }}</text>
        <button @click="wxLogin"> 登录</button>
      </block>
      <view class="reg text-center">
        <text @click="handleUserRegister" class="text-blue">账号登录</text>
      </view>
    </view>

    <view class="footer">
      <text class="text-grey1">登录即代表同意</text>
      <text @click="handleUserAgrement" class="text-blue">《用户协议》</text>
      <text @click="handlePrivacy" class="text-blue">《隐私协议》</text>
    </view>
  </view>
</template>

<script>
import {getCodeImg} from '@/api/login'
import {miniProgramLogin} from "../api/login";

export default {
  options: {
    styleIsolation: 'shared'
  },
  data() {
    return {
      codeUrl: "",
      captchaEnabled: true,
      register: false,
      globalConfig: getApp().globalData.config,
      loginForm: {
        username: "admin",
        password: "admin123",
        code: "",
        uuid: ''
      },
      userInfo: {},
      hasUserInfo: false,
      canIUseGetUserProfile: false,
      //是否微信登录
      isWxLogin: true,
      isGetWxLoading: false
    }
  },
  created() {
    this.getCode()
  },
  methods: {
    getUserInfo() {
      this.isGetWxLoading = true;
      return new Promise((resolve, reject) => {
        uni.getUserProfile({
          lang: 'zh_CN',
          desc: '用户登录', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，
          success: (res) => {
            this.isGetWxLoading = false;
            this.$modal.showToast('获取成功')
            console.log(res.userInfo.avatarUrl, 'resss')
            resolve(res.userInfo)
            this.userInfo = res.userInfo
          },
          fail: (err) => {
            reject(err)
          }
        })
      })
    },

    getUserProfile() {
      // 推荐使用 wx.getUserProfile 获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认
      // 开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
      wx.getUserProfile({
        desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
        success: (res) => {
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    },

    async getLogin() {
      return new Promise((resolve, reject) => {
        uni.login({
          success(res) {
            console.log(res, '成功')
            resolve(res)
          },
          fail: (err) => {
            console.log(err, 'logoer')
            reject(err)
          }
        })
      })
    },

    wxLogin() {
      let that = this;
      uni.getProvider({
        service: 'oauth',
        success: async function (res) {
          //支持微信、qq和微博等
          if (~res.provider.indexOf('weixin')) {
            let loginRes = await that.getLogin();
            console.log('登录信息', loginRes)
            miniProgramLogin(loginRes.code).then(res => {
              uni.showToast({
                    icon: 'none',
                    title: '登录成功'
                  }
              )
              console.log(res, res)
            })
          }
        },
        fail: function (err) {
          uni.hideLoading();
          uni.showToast({
            icon: 'none',
            title: err
          })
        }
      })
    },
    // 用户注册
    handleUserRegister() {
      this.$tab.redirectTo(`/pages/register`)
    },
    // 隐私协议
    handlePrivacy() {
      let site = this.globalConfig.appInfo.agreements[0]
      this.$tab.navigateTo(`/pages/common/webview/index?title=${site.title}&url=${site.url}`)
    },
    // 用户协议
    handleUserAgrement() {
      let site = this.globalConfig.appInfo.agreements[1]
      this.$tab.navigateTo(`/pages/common/webview/index?title=${site.title}&url=${site.url}`)
    },
    // 获取图形验证码
    getCode() {
      getCodeImg().then(res => {
        this.captchaEnabled = res.captchaEnabled === undefined ? true : res.captchaEnabled
        if (this.captchaEnabled) {
          this.codeUrl = 'data:image/gif;base64,' + res.img
          this.loginForm.uuid = res.uuid
        }
      })
    },
    // 登录方法
    async handleLogin() {
      if (this.loginForm.username === "") {
        this.$modal.msgError("请输入您的账号")
      } else if (this.loginForm.password === "") {
        this.$modal.msgError("请输入您的密码")
      } else if (this.loginForm.code === "" && this.captchaEnabled) {
        this.$modal.msgError("请输入验证码")
      } else {
        this.$modal.loading("登录中，请耐心等待...")
        this.pwdLogin()
      }
    },
    // 密码登录
    async pwdLogin() {
      this.$store.dispatch('Login', this.loginForm).then(() => {
        this.$modal.closeLoading()
        this.loginSuccess()
      }).catch(() => {
        if (this.captchaEnabled) {
          this.getCode()
        }
      })
    },
    // 登录成功后，处理函数
    loginSuccess(result) {
      // 设置用户信息
      this.$store.dispatch('GetInfo').then(res => {
        this.$tab.reLaunch('/pages/index')
      })
    }
  }
}
</script>

<style lang="scss" scoped>
::v-deep .custom-button {
  width: 200px !important; /* 强制应用此样式 */
  height: 60px !important; /* 强制应用此样式 */
  font-size: 20px !important; /* 强制应用此样式 */
  padding: 0 !important; /* 强制去掉内边距 */
}

.normal-login-container {
  display: flex;
  flex-direction: column;
  height: 100vh; /* 设置容器高度为视口高度 */
}

.footer {
  margin-top: auto; /* 将 footer 推到容器底部 */
  text-align: center;
  color: #333;
  margin-bottom: 20rpx; /* 底部留白 */
}

.login-form-content {
  flex: 1; /* 填充剩余空间 */
  text-align: center;
  margin: 20px auto;
  margin-top: 15%;
  width: 80%;
}

.normal-login-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  background-color: #ffffff;
  padding: 20px;

  .logo-content {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-bottom: 30px;

    .logo {
      width: 100rpx;
      height: 100rpx;
      border-radius: 4px;
    }

    .title {
      font-size: 21px;
      margin-top: 10px;
      text-align: center;
    }
  }

  .login-form-content {
    width: 80%;
    text-align: center;

    .input-item {
      display: flex;
      align-items: center;
      background-color: #f5f6f7;
      height: 45px;
      border-radius: 20px;
      margin: 10px auto;

      .icon {
        font-size: 38rpx;
        color: #999;
        margin-left: 10px;
      }

      .input {
        width: 100%;
        font-size: 14px;
        padding-left: 15px;
      }

      .login-code {
        display: flex;
        align-items: center;
        margin-left: auto;

        .login-code-img {
          width: 100rpx;
          height: 38px;
          cursor: pointer;
        }
      }
    }

    .login-btn {
      margin-top: 20px;
      height: 45px;
      width: 100%;
    }

    .reg {
      margin-top: 15px;
    }

    .xieyi {
      color: #333;
      margin-top: 20px;
    }
  }

  .userinfo {
    text-align: center;
    margin-top: 300rpx;

    .userinfo-avatar {
      width: 100px;
      height: 100px;
      border-radius: 50%;
    }

    .userinfo-nickname {
      display: block;
      margin-top: 10px;
    }
  }
}
</style>
