<template>
  <view class="deviceUploadingData-list-content">
    <u-list
    >
      <u-list-item
          v-for="(item, index) in healthReportList"
          :key="index"
      >
        <u-cell-group>
          <u-cell
              isLink
              :title="item.reportTitle"
              :url="`/pages/zhyl/healthReport/info?healthReportId=${item.healthReportId}`"
              titleStyle="font-size: 14px"

          >
            <u-avatar
                slot="icon"
                shape="square"
                size="35"
                :src="`https://cdn.uviewui.com/uview/album/1.jpg`"
                customStyle="margin: -3px 5px -3px 0"
            ></u-avatar>
          </u-cell>
        </u-cell-group>
      </u-list-item>
      <u-loadmore @loadmore="loadmore" :status="status"/>
    </u-list>
  </view>
</template>

<script>
import {healthReportList} from '@/api/zhyl/healthReport'

export default {
  data() {
    return {
      healthReportList: [],
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
      healthReport: {}
    }
  },

  onLoad() {
    this.getHealthReportList()
  },
  methods: {
    getHealthReportList() {
      healthReportList(this.queryParams).then(res => {
        this.healthReportList.push(...res.rows);
        this.total = res.total
        this.status = 'loadmore';
      })
    },
    scrolltolower() {
      this.loadmore()
    },
    loadmore() {
      if (this.healthReportList.length >= this.total) {
        this.status = 'nomore'
        return
      }
      this.status = 'loading';
      this.queryParams.pageNum += 1
      this.getHealthReportList()
    },
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
    margin-top: 10px;
    /*  justify-content: space-between;
      align-items: center;*/
  }

  .title {
    font-size: 36rpx;
    color: #8f8f94;
  }
}
</style>
