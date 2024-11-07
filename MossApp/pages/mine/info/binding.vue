<template>
  <view class="container">
    <view class="example">
      <uni-forms ref="form" :model="user" labelWidth="80px">
        <uni-forms-item label="身份证号" name="idCard">
          <u-input
              placeholder="请输入身份证号"
              border="surround"
              clearable
              v-model="user.idCard"
              type="idCard"
          ></u-input>
        </uni-forms-item>

        <uni-forms-item label="手机号码" name="phonenumber">
          <uni-easyinput v-model="user.phonenumber" placeholder="请输入手机号码"/>
        </uni-forms-item>

        <uni-forms-item label="验证码" name="code">
          <!-- #ifndef APP-NVUE -->
          <u-input placeholder="获取验证码" v-model="user.code">
            <!-- #endif -->
            <!-- #ifdef APP-NVUE -->
            <u--input placeholder="获取验证码" v-model="user.code">
              <!-- #endif -->
              <template slot="suffix">
                <u-code
                    ref="uCode"
                    @change="codeChange"
                    seconds="60"
                    changeText="X秒重新获取"
                ></u-code>
                <u-button
                    @tap="getCode"
                    :text="codeValue"
                    type="success"
                    size="small"
                ></u-button>
              </template>
              <!-- #ifndef APP-NVUE -->
          </u-input>
          <!-- #endif -->
          <!-- #ifdef APP-NVUE -->
          </u--input>
          <!-- #endif -->
        </uni-forms-item>
      </uni-forms>
      <button type="primary" @click="submit">提交</button>
    </view>
  </view>
</template>

<script>
import {updateUserProfile} from "@/api/system/user"

export default {
  data() {
    return {
      user: {
        idCard: "",
        phonenumber: "",
        code: "",
      },
      codeValue: '',
      rules: {
        idCard: {
          rules: [{
            required: true,
            errorMessage: '身份证号不能为空'
          }]
        },
        phonenumber: {
          rules: [{
            required: true,
            errorMessage: '手机号码不能为空'
          }, {
            pattern: /^1[3-9]\d{9}$/,
            errorMessage: '请输入正确的手机号码'
          }]
        },
        code: {
          rules: [{
            required: true,
            errorMessage: '验证码不能为空'
          }]
        }
      }
    }
  },
  onReady() {
    this.$refs.form.setRules(this.rules)
  },
  methods: {
    codeChange(text) {
      this.codeValue = text;
    },
    getCode() {
      if (this.$refs.uCode.canGetCode) {
        // 模拟向后端请求验证码
        uni.showLoading({
          title: '正在获取验证码'
        })
        setTimeout(() => {
          uni.hideLoading();
          // 这里此提示会被this.start()方法中的提示覆盖
          uni.$u.toast('验证码已发送');
          // 通知验证码组件内部开始倒计时
          this.$refs.uCode.start();
        }, 2000);
      } else {
        uni.$u.toast('倒计时结束后再发送');
      }
    },
    change(e) {
      console.log('change', e);
    },
    submit() {
      this.$refs.form.validate().then(() => {
        updateUserProfile(this.user).then(() => {
          this.$modal.msgSuccess("修改成功")
        })
      })
    }
  }
}
</script>

<style scoped lang="scss">
.container {
  background-color: #ffffff;
}

.example {
  padding: 15px;
  background-color: #fff;
}

.form-item {
  display: flex;
  align-items: center;
}

.button {
  display: flex;
  align-items: center;
  height: 35px;
  line-height: 35px;
  margin-left: 10px;
}
</style>
