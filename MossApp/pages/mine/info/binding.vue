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

        <uni-forms-item label="手机号码" name="phoneNumber">
          <uni-easyinput v-model="user.phoneNumber" placeholder="请输入手机号码"/>
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
import {binding, getCode} from "../../../api/zhyl/userInfo";

export default {
  data() {
    return {
      user: {
        idCard: "",
        phoneNumber: "",
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
        phoneNumber: {
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
    // this.$refs.form.setRules(this.rules)
  },
  methods: {
    codeChange(text) {
      this.codeValue = text;
    },
    getCode() {
      this.$refs.form.validateField('phoneNumber') // 校验手机号码字段
          .then(() => {
            // 如果校验通过，调用发送验证码接口
            getCode(this.user.phoneNumber)
                .then(response => {
                  if (response.code === 200) {
                    this.$refs.uCode.start(); // 开始倒计时
                    this.$modal.msgSuccess("验证码已发送");
                  } else {
                    this.$modal.msgError(response.msg);
                  }
                })
                .catch(() => {
                  this.$modal.msgError("验证码发送失败，请重试");
                });
          })
          .catch(error => {
            // 如果校验不通过，显示错误信息
            this.$modal.msgError(error.message || "手机号校验失败");
          });
    },
    change(e) {
      console.log('change', e);
    },
    submit() {
      binding(this.user.phoneNumber, this.user.code, this.user.idCard).then(() => {
        this.$modal.msgSuccess("绑定成功，请重新登录")
        this.$store.dispatch('LogOut').then(() => {
          this.$tab.reLaunch('/pages/index')
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
