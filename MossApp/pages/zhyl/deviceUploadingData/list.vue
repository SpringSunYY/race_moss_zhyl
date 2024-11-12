<template>
  <view class="deviceUploadingData-list-content">
    <u-list
    >
      <u-list-item
          v-for="(item, index) in healthDataList"
          :key="index"
      >
        <u-button @click="openData(item)"> {{ item.createTime }}</u-button>
      </u-list-item>
      <u-loadmore @loadmore="loadmore" :status="status"/>
    </u-list>
    <u-popup :show="show" :round="10" customStyle="width:95%;" mode="center" @close="close" @open="open"
             :closeable="true">
      <view class="showHealthInfo">
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
        </view>
      </view>
    </u-popup>
  </view>
</template>

<script>
import {healthData} from '@/api/zhyl/deviceUploadingData'

export default {
  data() {
    return {
      healthDataList: [],
      total: 0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        dataId: null,
        deviceId: null,
        type: null,
        command: null,
        deviceType: null,
        userInfoId: null,
        warningType: null,
        processingStatus: null,
        createTime: null,
        updateTime: null,
        delFlag: null
      },
      status: 'loadmore',
      show: false,
      healthData: {
        heartRate: 0,
        dbp: 0,
        sbp: 0,
        oxygen: 0,
        bloodSugar: 0,
      }
    }
  },

  onLoad() {
    this.getHealthDataList()
  },
  methods: {
    getHealthDataList() {
      healthData(this.queryParams).then(res => {
        this.healthDataList.push(...res.rows);
        this.total = res.total
        this.status = 'loadmore';
      })
    },
    scrolltolower() {
      this.loadmore()
    },
    loadmore() {
      if (this.healthDataList.length >= this.total) {
        this.status = 'nomore'
        return
      }
      this.status = 'loading';
      this.queryParams.pageNum += 1
      this.getHealthDataList()
      // for (let i = 0; i < 30 && this.healthDataList.length <= 120; i++) {
      //   this.healthDataList.push({
      //     show: false,
      //     url: this.urls[uni.$u.random(0, this.urls.length - 1)]
      //   })
      // }
    },
    openData(data) {
      this.healthData = {}
      this.healthData = data.argumentData
      this.show = true
    },
    open() {
      // console.log('open');
    },
    close() {
      this.show = false
      // console.log('close');
    }
  },
}
</script>
<style lang="scss" scoped>
.deviceUploadingData-list-content {
  background-color: #ffffff;
}

.showHealthInfo {
  width: 95%;
  margin: 10rpx auto;
  background-color: #ffffff;
  padding: 10px 10px;
  //text {
  //  font-size: 30px;
  //  font-weight: bold;
  //  color: #333333;
  //}
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
    //margin-left: 10px;
    border-radius: 8px;
  }

  .card-footer {
    width: 100%;
    padding: 0;
    /*margin-top: 10px;*/
    /*  justify-content: space-between;
      align-items: center;*/
  }

  .title {
    font-size: 36rpx;
    color: #8f8f94;
  }
}
</style>
