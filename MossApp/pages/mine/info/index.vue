<template>
  <view class="container">
    <uni-list>
      <uni-list-item showExtraIcon="true" :extraIcon="{type: 'person-filled'}" title="昵称"
                     :rightText="user.userInfoName"/>
      <uni-list-item showExtraIcon="true" :extraIcon="{type: 'phone-filled'}" title="手机号码"
                     :rightText="user.contactPhone"/>
      <uni-list-item showExtraIcon="true" :extraIcon="{type: 'person-filled'}" title="性别"
                     :rightText="user.sex==='0'?'男':'女'"/>
<!--      <uni-list-item showExtraIcon="true" :extraIcon="{type: 'auth-filled'}" title="岗位" :rightText="1"/>-->
      <uni-list-item showExtraIcon="true" :extraIcon="{type: 'staff-filled'}" title="角色"
                     :rightText="user.userInfoRole"/>
      <uni-list-item showExtraIcon="true" :extraIcon="{type: 'calendar-filled'}" title="创建日期"
                     :rightText="user.createTime"/>
    </uni-list>
  </view>
</template>

<script>
import {getInfo} from "@/api/login"

export default {
  data() {
    return {
      user: {},
      roleGroup: "",
      postGroup: ""
    }
  },
  onLoad() {
    this.getUser()
  },
  methods: {
    getUser() {
      getInfo().then(response => {
        this.user = response.userInfo
        this.roleGroup = response.roleGroup
        this.postGroup = response.postGroup
        console.log(this.user)
      })
    }
  }
}
</script>

<style lang="scss">
page {
  background-color: #ffffff;
}
</style>
