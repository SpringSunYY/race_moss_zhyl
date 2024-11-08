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
        <view class="title">HI, 王某某</view>
        <view class="extra">查看详细数据 ></view>
      </view>

      <!-- 卡片内容 -->
      <view class="card-body">
        <view class="left-section">
          <view class="data-item">
            <text class="label">血压</text>
            <u-row justify="flex-start">
              <u-col span="3" style="margin-left: 0px">
                <text class="normal" style="font-weight: bold;">正常</text>
              </u-col>
              <u-col span="9">
                <text class="details">收缩压: 105 舒张压: 75</text>
              </u-col>
            </u-row>
          </view>
          <u-row>
            <u-col span="6">
              <view class="data-item">
                <text class="label">血糖</text>
                <text class="status normal">正常</text>
                <text class="details">4.5mmol/L</text>
              </view>
            </u-col>
            <u-col span="6">
              <view class="data-item">
                <text class="label">心率</text>
                <text class="status abnormal">异常</text>
                <text class="details">120 bpm</text>
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
            url="/pages/componentsB/tag/tag"
        ></u-cell>
      </view>
    </uni-card>
    <uni-card class="custom-card my-card" margin="0">
      <u-notice-bar icon="bell-fill" text="这里是荔枝软件开发工作，你有一份尾款需要支付"></u-notice-bar>
    </uni-card>
    <view class="myDevice">
      <u-cell
          title="我的设备"
          isLink
          title-style="font-size:24px;font-weight:bold"
          :border="false"
          url="/pages/componentsB/tag/tag"
      >
        <text
            slot="value"
            class="u-slot-value"
            style="font-size: 16px; font-weight: bold; color: rgba(0,0,0,0.42);"
        >全部设备
        </text>
      </u-cell>
      <u-row>
        <view class="myDevice-card-container" v-for="i in 2" :key="i" >
          <u-col span="12">
            <uni-card class="myDevice-info my-card" margin="5" padding="5px 0px">
              <u-row>
                <u-col span="10">
                  <u--image :showLoading="true" src="/static/logo.png" width="120rpx" height="120rpx"
                            @click="clickDeviceImage"></u--image>
                  <u--text type="primary" size="24" text="智能手表"></u--text>
                  <u--text type="info" size="12" text="佩戴 | 剩余电量 95%"></u--text>
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
  </view>
</template>

<script>
export default {
  options: {styleIsolation: 'shared'},
  data() {
    return {
      globalConfig: getApp().globalData.config,
    }
  },
  onLoad: function () {
  },
  methods: {
    searchInfo(value) {
      console.log("搜索:" + value)
    },
    clickDeviceImage() {
      console.log("点击设备图片")
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
  margin-top: 10rpx;
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
}

.status {
  margin-left: 8px;
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

    .myDevice-info {
      width: 100%;
      margin: 10rpx auto;
    }
  }
}
</style>
