<template>
  <view class="content">
    <view class="custom-card">
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
            <text class="status normal">正常</text>
            <text class="details">收缩压: 105 舒张压: 75</text>
          </view>
          <view class="data-item">
            <text class="label">血糖</text>
            <text class="status normal">正常</text>
            <text class="details">4.5mmol/L</text>
          </view>
          <view class="data-item">
            <text class="label">心率</text>
            <text class="status abnormal">异常</text>
            <text class="details">120 bpm</text>
          </view>
          <view class="data-item">
            <text class="label">睡眠质量</text>
            <text class="status deep-sleep">深度睡眠</text>
          </view>
        </view>

        <!-- 右侧图片 -->
        <image class="right-section" src="/static/logo.png" mode="aspectFill" />
      </view>

      <!-- 卡片底部 -->
      <view class="card-footer">
        <view class="button-group">
          <button class="report-button">生成专属健康报告</button>
        </view>
        <text class="footer-note">智能报告，提前预防疾病 ></text>
      </view>
    </view>
  </view>
</template>

<script>
export default {
  onLoad: function () {
  }
}
</script>

<style>
.content {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.custom-card {
  border: 1px solid #e0e0e0;
  border-radius: 8px;
  padding: 16px;
  background-color: #fff;
  width: 95%; /* 调整宽度，使卡片更宽 */
  max-width: 800px; /* 限制最大宽度 */
  margin: 500rpx 0; /* 减小上下的间距 */
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
  margin-left: 16px;
  border-radius: 8px;
}

.card-footer {
  margin-top: 16px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.button-group {
  flex: 1;
}

.report-button {
  background-color: #2196f3;
  color: #fff;
  padding: 10px;
  border: none;
  border-radius: 4px;
  font-size: 14px;
  width: 100%;
  text-align: center;
}

.footer-note {
  font-size: 12px;
  color: #999;
  margin-left: 8px;
}

.logo {
  height: 200rpx;
  width: 200rpx;
  margin-top: 200rpx;
  margin-left: auto;
  margin-right: auto;
  margin-bottom: 50rpx;
}

.text-area {
  display: flex;
  justify-content: center;
}

.title {
  font-size: 36rpx;
  color: #8f8f94;
}
</style>
