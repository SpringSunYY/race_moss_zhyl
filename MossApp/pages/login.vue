<template>
  <view class="normal-login-container">
    <!-- Logo和标题 -->
    <view class="logo-content align-center justify-center flex">
      <image style="width: 100rpx;height: 100rpx;" :src="globalConfig.appInfo.logo" mode="widthFix"></image>
      <text class="title">moss-智慧养老登录</text>
    </view>

    <!-- 登录表单 -->
    <view class="login-form-content" v-if="!isWxLogin || !showWxLogin" style="margin-top: 100rpx">
      <view class="input-item flex align-center">
        <view class="iconfont icon-user icon"></view>
        <input v-model="loginForm.username" class="input" type="text" placeholder="请输入账号" maxlength="30"/>
      </view>
      <view class="input-item flex align-center">
        <view class="iconfont icon-password icon"></view>
        <input v-model="loginForm.password" type="password" class="input" placeholder="请输入密码" maxlength="20"/>
      </view>

      <!-- 验证码输入框和图片 -->
      <view class="input-item flex align-center" style="width: 100%; margin-bottom: 20px;" v-if="captchaEnabled">
        <view class="iconfont icon-code icon"></view>
        <input v-model="loginForm.code" type="number" class="input captcha-input" placeholder="请输入验证码"
               maxlength="4"/>
        <view class="login-code" @click="getCode">
          <image :src="codeUrl" class="login-code-img"></image>
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

    <!-- 微信登录 -->
    <view class="login-form-content" v-if="isWxLogin && showWxLogin" style="padding-top: 400rpx">
      <view v-if="!userInfo.nickName" class="action-btn">
        <button @click="getUserInfo" class="login-btn cu-btn block bg-blue lg round">获取微信信息</button>
      </view>
      <block v-if="userInfo.nickName">
        <view class="userinfo-content">
          <image class="userinfo-avatar" :src="userInfo.avatarUrl" mode="cover"></image>
          <text class="userinfo-nickname">用户名：{{ userInfo.nickName }}</text>
        </view>
        <view class="action-btn">
          <button @click="wxLogin" class="login-btn cu-btn block bg-blue lg round">登录</button>
        </view>
      </block>
    </view>

    <!-- 切换登录方式 -->
    <view class="reg text-center" v-if="showWxLogin">
      <text @click="handleChangeLogin" class="text-blue">{{ isWxLogin ? '账号登录' : '微信登录' }}</text>
    </view>

    <!-- 底部协议 -->
    <view class="footer">
      <text class="text-grey1">登录即代表同意</text>
      <text @click="handleUserAgrement" class="text-blue">《用户协议》</text>
      <text @click="handlePrivacy" class="text-blue">《隐私协议》</text>
    </view>
  </view>
</template>
<script>

import {getCodeImg} from "@/api/login";

export default {
  options: {styleIsolation: "shared"},
  data() {
    return {
      codeUrl: "",
      captchaEnabled: true,
      register: false,
      globalConfig: getApp().globalData.config,
      loginForm: {
        username: "52262419710321121X",
        password: "123456",
        code: "",
        uuid: "",
      },
      userInfo: {},
      isWxLogin: false,
      showWxLogin: false, // 控制是否显示微信登录
      isGetWxLoading: false,
    };
  },
  created() {
    this.getCode();
    this.checkEnvironment(); // 检查环境
  },
  methods: {
    checkEnvironment() {
      uni.getSystemInfo({
        success: (res) => {
          console.log(res);
          // 判断是否小程序环境
          this.showWxLogin = res.hostName === "WeChat";
          this.isWxLogin = this.showWxLogin; // 默认微信登录方式
        },
      });
    },
    handleChangeLogin() {
      this.isWxLogin = !this.isWxLogin;
    },
    getUserInfo() {
      this.$modal.loading("获取中，请耐心等待...")
      if (this.isGetWxLoading) {
        return
      }
      this.isGetWxLoading = true
      return new Promise((resolve, reject) => {
        uni.getUserProfile({
          lang: 'zh_CN',
          desc: '用户登录', // 声明获取用户个人信息后的用途
          success: (res) => {
            this.isGetWxLoading = false;
            uni.showToast({
              position: 'top',
              icon: 'none',
              title: '获取成功'
            })
            resolve(res.userInfo)
            console.log(res.userInfo)
            this.userInfo = res.userInfo
          },
          fail: (err) => {
            this.isGetWxLoading = false;
            uni.showToast({
              position: 'top',
              icon: 'none',
              title: '获取失败'
            })
            reject(err)
          }
        })
      })
    },

    getLogin() {
      return new Promise((resolve, reject) => {
        uni.login({
          success(res) {
            resolve(res)
          },
          fail: (err) => {
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
          if (~res.provider.indexOf('weixin')) {
            let loginRes = await that.getLogin();
            that.miniProgramLogin(loginRes.code)
          }
        },
        fail: function (err) {
          uni.showToast({
            icon: 'none',
            title: err
          })
        }
      })
    },
    async miniProgramLogin(code) {
      this.$store.dispatch('MiniProgramLogin', code).then(() => {
        this.$modal.closeLoading()
        this.loginSuccess()
      }).catch(() => {

      })
    },

    handleUserRegister() {
      this.$tab.redirectTo(`/pages/register`)
    },

    handlePrivacy() {
      let site = this.globalConfig.appInfo.agreements[0]
      this.$tab.navigateTo(`/pages/common/webview/index?title=${site.title}&url=${site.url}`)
    },

    handleUserAgrement() {
      let site = this.globalConfig.appInfo.agreements[1]
      this.$tab.navigateTo(`/pages/common/webview/index?title=${site.title}&url=${site.url}`)
    },

    getCode() {
      getCodeImg().then(res => {
        this.captchaEnabled = res.captchaEnabled === undefined ? true : res.captchaEnabled
        if (this.captchaEnabled) {
          this.codeUrl = 'data:image/gif;base64,' + res.img
          this.loginForm.uuid = res.uuid
        }
      })
    },

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

    loginSuccess(result) {
      this.$store.dispatch('GetInfo').then(res => {
        this.$tab.reLaunch('/pages/index')
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.normal-login-container {
  display: flex;
  flex-direction: column;
  height: 100vh;
  justify-content: space-between;
}

.logo-content {
  width: 100%;
  font-size: 21px;
  text-align: center;
  padding-top: 15%;

  image {
    border-radius: 4px;
  }

  .title {
    margin-left: 10px;
  }
}

.title {
  margin-top: 10px;
  font-size: 18px;
}

.login-form-content {
  width: 80%;
  margin: 0 auto;
}

.input-item {
  margin-bottom: 20px;
  background-color: #f5f6f7;
  height: 45px;
  border-radius: 20px;
  padding: 0 10px;
  display: flex;
  align-items: center;
}

.input {
  flex: 1;
  padding-left: 15px;
  font-size: 14px;
}

.captcha-input {
  width: 70%; /* 控制输入框的宽度 */
}

.login-code {
  margin-left: 10px;
  cursor: pointer;
}

.login-code-img {
  width: 120px;
  height: 45px;
  border-radius: 8px;
}

.login-btn {
  margin-top: 20px;
  height: 45px;
}

.reg {
  margin-top: 15px;
}

.userinfo-content {
  text-align: center;
  margin-bottom: 20rpx;
}

.userinfo-avatar {
  width: 100rpx;
  height: 100rpx;
  border-radius: 50%;
}

.userinfo-nickname {
  font-size: 16px;
  color: #333;
}

.footer {
  display: flex;
  justify-content: center;
  margin-top: auto;
  padding: 20px;
  font-size: 14px;
}

.text-blue {
  color: #409EFF;
}

.text-grey1 {
  color: #888;
}

.text-center {
  text-align: center;
}
</style>
