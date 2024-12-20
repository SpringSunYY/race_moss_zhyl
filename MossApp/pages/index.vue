<template>
  <view class="content">
    <uni-nav-bar dark :fixed="true" shadow background-color="#007AFF" status-bar
                 title="moss智慧养老"/>
    <view style="margin-top: 15rpx;width: 85%">
      <u-row>
        <u-col span="2">
          <image style="width: 70rpx;height: 70rpx;margin-left: -40rpx" :src="globalConfig.appInfo.logo"
                 mode="widthFix"></image>
        </u-col>
        <u-col span="10">
          <u-search inputAlign="center" color="#606266" borderColor="#aaaaaa" height="40" style="width: 100%"
                    placeholder="请输入搜索内容" searchIcon="scan" :show-action="true" @search="searchInfo"
                    @custom="searchInfo"/>
        </u-col>
      </u-row>
    </view>
    <uni-card class="custom-card my-card" margin="0" style="margin-top: 20rpx">
      <!-- 卡片头部 -->
      <view class="card-header">
        <view class="title">HI, {{ userInfo.name }}</view>
        <view class="extra" @click="toDeviceUploadingDataList">查看详细数据
          <uni-icons type="forward" color="rgba(0,0,0,0.42)" size="16"></uni-icons>
        </view>
      </view>

      <!-- 卡片内容 -->
      <view class="card-body">
        <view class="left-section">
          <view class="data-item">
            <text class="label">血压</text>
            <u-row justify="flex-start">
              <u-col span="3" style="margin-left: 0px">
                <text class="status normal"
                      v-if="healthData.sdp<=120&&healthData.sbp>=90&&healthData.dbp<=80&&healthData.dbp>=60">正常
                </text>
                <text class="status abnormal" v-else>异常</text>
              </u-col>
              <u-col span="9">
                <text class="details">高压: {{ healthData.sdp }} 低压: {{ healthData.dbp }}</text>
              </u-col>
            </u-row>
          </view>
          <u-row>
            <u-col span="6">
              <view class="data-item">
                <text class="label">血糖</text>
                <text class="status normal" v-if="healthData.oxygen<=100&&healthData.oxygen>=95">正常</text>
                <text class="status abnormal" v-else>异常</text>
                <text class="details">{{ healthData.oxygen }}mmol/L</text>
              </view>
            </u-col>
            <u-col span="6">
              <view class="data-item">
                <text class="label">心率</text>
                <text class="status normal" v-if="healthData.heartRate<=100&&healthData.heartRate>=60">正常</text>
                <text class="status abnormal" v-else>异常</text>
                <text class="details">{{ healthData.heartRate }} bpm</text>
              </view>
            </u-col>
          </u-row>
          <view class="data-item">
            <text class="label">睡眠质量</text>
            <text class="status deep-sleep">深度睡眠</text>
          </view>
        </view>

        <!-- 右侧图片 -->
        <image class="right-section" src="/static/logo.png" mode="aspectFill"/>
      </view>

      <!-- 卡片底部 -->
      <view class="card-footer">
        <u-cell
            title="查看我的健康报告"
            isLink
            :border="false"
            url="/pages/zhyl/healthReport/list"
            customStyle="padding: 0 0px; font-size: 16px; font-weight: bold; color: rgba(0,0,0,0.42);"
        ></u-cell>
      </view>
    </uni-card>
    <uni-card class="custom-card my-card" v-if="warningText!==''" style="margin-top:10rpx " margin="0">
      <u-notice-bar icon="bell-fill" :text="warningText"></u-notice-bar>
    </uni-card>
    <view class="myDevice">
      <u-cell
          title="我的设备"
          isLink
          title-style="font-size:24px;font-weight:bold"
          :border="false"
          url="/pages/zhyl/device/list"
      >
        <text
            slot="value"
            class="u-slot-value"
            style="font-size: 16px; font-weight: bold; color: rgba(0,0,0,0.42);"
        >全部设备
        </text>
      </u-cell>
      <u-row>
        <view class="myDevice-card-container" v-for="item in deviceList" :key="item.bindingId">
          <u-col span="12" justify="space-between" align="center">
            <uni-card class="myDevice-info my-card" margin="5" padding="5px 0px">
              <u-row>
                <u-col span="10">
                  <u--image :showLoading="true" :src="baseUrl+item.deviceImageUrl" width="120rpx" height="120rpx"
                            @click="clickDeviceImage"></u--image>
                  <u--text type="primary" size="24" :text="item.deviceName"></u--text>
                  <u--text type="info" size="12" :text="item.deviceInfo"></u--text>
                </u-col>
                <u-col span="2">
                  <u-icon name="arrow-right" color="#2979ff" size="40"></u-icon>
                </u-col>
              </u-row>
            </uni-card>
          </u-col>
        </view>
      </u-row>
    </view>
    <view class="consult">
      <!--      <u-cell-->
      <!--          title="防诈咨询"-->
      <!--          isLink-->
      <!--          title-style="font-size:24px;font-weight:bold"-->
      <!--          :border="false"-->
      <!--          url="/pages/componentsB/tag/tag"-->
      <!--      >-->
      <!--        <text-->
      <!--            slot="value"-->
      <!--            class="u-slot-value"-->
      <!--            style="font-size: 16px; font-weight: bold; color: rgba(0,0,0,0.42);"-->
      <!--        >查看跟多咨询-->
      <!--        </text>-->
      <!--      </u-cell>-->
      <uni-section title="咨询" titleFontSize="24px" type="line">
        <template v-slot:right>
          <navigator url="/pages/tabBar/extUI/extUI" open-type="switchTab" hover-class="other-navigator-hover">
            查看更多
          </navigator>
        </template>
        <uni-card padding="0" margin="0" spacing="0">
          <template v-slot:cover>
            <view class="custom-cover">
              <image class="cover-image" mode="center" :src="baseUrl+consultInfo.consultImage">
              </image>
              <view class="cover-content">
                <text class="uni-subtitle uni-white">今日新闻热点</text>
              </view>
            </view>
          </template>
          <uni-list>
            <uni-list-item title="今日新闻" showArrow></uni-list-item>
            <uni-list-item title="今日新闻" showArrow></uni-list-item>
          </uni-list>
          <view slot="actions" class="card-actions no-border">
            <view class="card-actions-item" @click="actionsClick('分享')">
              <uni-icons type="pengyouquan" size="18" color="#999"></uni-icons>
              <text class="card-actions-item-text">分享</text>
            </view>
            <view class="card-actions-item" @click="actionsClick('点赞')">
              <uni-icons type="heart" size="18" color="#999"></uni-icons>
              <text class="card-actions-item-text">点赞</text>
            </view>
            <view class="card-actions-item" @click="actionsClick('评论')">
              <uni-icons type="chatbubble" size="18" color="#999"></uni-icons>
              <text class="card-actions-item-text">评论</text>
            </view>
          </view>
        </uni-card>
      </uni-section>
    </view>
  </view>
</template>

<script>
import {newHealthData, warningData} from '@/api/zhyl/deviceUploadingData'
import {listElderlyDeviceBinding} from '@/api/zhyl/elderlyDeviceBinding'
import {listConsult} from '@/api/zhyl/consult'
import {baseUrl} from "@/config";

export default {
  options: {styleIsolation: 'shared'},
  data() {
    return {
      baseUrl: baseUrl,
      globalConfig: getApp().globalData.config,
      cover: 'https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/shuijiao.jpg',
      avatar: 'https://qiniu-web-assets.dcloud.net.cn/unidoc/zh/unicloudlogo.png',
      extraIcon: {
        color: '#4cd964',
        size: '22',
        type: 'gear-filled'
      },
      userInfo: this.$store.state.user,
      healthData: {
        heartRate: 0,
        dbp: 0,
        sbp: 0,
        oxygen: 0,
        bloodSugar: 0,
      },
      warningText: "",
      deviceList: [],
      deviceQueryParam: {
        pageNum: 1,
        pageSize: 2,
      },
      consultInfo: {},
      consultQueryParam: {
        pageNum: 1,
        pageSize: 1,
      }
    }
  },
  onLoad: function () {
    // console.log(this.$store.state.user)
  },
  created() {
    this.getHealthData()
    this.getWarningData()
    this.getDeviceList()
    this.getConsultInfo()
  },
  methods: {
    getConsultInfo() {
      listConsult(this.consultQueryParam).then(res => {
        this.consultInfo = res.rows[0]
      })
    },
    getDeviceList() {
      listElderlyDeviceBinding(this.deviceQueryParam).then(res => {
        this.deviceList = res.rows.map(item => ({
          deviceName: item.deviceName,
          deviceImageUrl: item.deviceImageUrl,
          deviceInfo: `${item.installationMode ?? ""} | ${item.tmBattery ?? ""}`
        }));
        // console.log(this.deviceList)
      })
    },
    getWarningData() {
      warningData().then(res => {
        if (res.total <= 0) {
          console.log(res.total)
          return
        }
        this.warningText = "您有" + res.total + "条预警信息，最新一条时间为" + res.rows[0].createTime + "请联系相关服务人员解决。"
        // console.log(this.warningText)
      })
    },
    toDeviceUploadingDataList() {
      this.$tab.navigateTo('/pages/zhyl/deviceUploadingData/list')
    },
    getHealthData() {
      newHealthData().then(res => {
        this.healthData = res.data.argumentData
        // console.log(this.healthData)
      })
    },
    searchInfo(value) {
      console.log("搜索:" + value)
    },
    clickDeviceImage() {
      console.log("点击设备图片")
    },
    onClick(e) {
      console.log(e)
    },
    actionsClick(text) {
      uni.showToast({
        title: text,
        icon: 'none'
      })
    }
  },
}
</script>

<style lang="scss" scoped>
.content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.my-card {
  background-color: rgb(255, 255, 255);
  border-radius: 8px;
  border-color: rgba(255, 255, 255, 0.87);
}

.custom-card {
  /*border: 1px solid #e0e0e0;*/
  margin: 10rpx;
  width: 95%; /* 调整宽度，使卡片更宽 */
  max-width: 800px; /* 限制最大宽度 */
}


.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 12px;
}

.card-body {
  display: flex;
  justify-content: space-between;
}

.left-section {
  flex: 2;
}

.data-item {
  margin-bottom: 12px;
  font-size: 14px;
}

.label {
  font-weight: bold;
  margin-right: 8px;
}

.status {
  //margin-left: 8px;
  font-weight: bold;
}

.normal {
  color: #4caf50; /* 绿色 */
}

.abnormal {
  color: #f44336; /* 红色 */
}

.deep-sleep {
  color: #03a9f4; /* 蓝色 */
}

.details {
  display: block;
  font-size: 12px;
  color: #666;
}

.right-section {
  width: 100px;
  height: 100px;
  margin-left: 10px;
  border-radius: 8px;
}

.card-footer {
  margin-top: 10px;
  /*  justify-content: space-between;
    align-items: center;*/
}

.title {
  font-size: 36rpx;
  color: #8f8f94;
}

.myDevice {
  width: 100%;
  margin: 10px auto;

  .myDevice-card-container {
    width: 100%;
    //margin: 5rpx auto;

    .myDevice-info {
      width: 90%;
    }
  }
}

.consult {
  width: 95%;
  margin: 0 10rpx;

  .container {
    overflow: hidden;
  }

  .custom-cover {
    flex: 1;
    flex-direction: row;
    position: relative;
  }

  .cover-content {
    position: absolute;
    bottom: 0;
    left: 0;
    right: 0;
    height: 40px;
    width: 100%;
    background-color: rgba($color: #000000, $alpha: 0.4);
    display: flex;
    flex-direction: row;
    align-items: center;
    padding-left: 15px;
    font-size: 14px;
    color: #fff;
  }

  .card-actions {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    align-items: center;
    height: 45px;
    border-top: 1px #eee solid;
  }

  .card-actions-item {
    display: flex;
    flex-direction: row;
    align-items: center;
  }

  .card-actions-item-text {
    font-size: 12px;
    color: #666;
    margin-left: 5px;
  }

  .cover-image {
    flex: 1;
    width: 100%;
    height: 150px;
  }

  .no-border {
    border-width: 0;
  }
}
</style>
