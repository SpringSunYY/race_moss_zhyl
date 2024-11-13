<template>
  <view>
    <u-list
    >
      <u-list-item
          v-for="(item, index) in deviceList"
          :key="index"
      >
        <uni-card class="myDevice-info my-card" margin="5" padding="5px 0px">
          <u-row>
            <u-col span="6">
              <u--image :showLoading="true" :src="baseUrl+item.deviceImageUrl" width="240rpx" height="240rpx"></u--image>
            </u-col>
            <u-col span="5">
              <u--text type="primary" size="24" :text="item.deviceName"></u--text>
              <u--text type="info" size="12" :text="item.deviceInfo"></u--text>
              <u--text type="info" size="10" :text="'绑定时间:'+item.bindTime"></u--text>
            </u-col>
            <u-col span="1">
              <u-icon name="arrow-right" color="#2979ff" size="40"></u-icon>
            </u-col>
          </u-row>
        </uni-card>
      </u-list-item>
      <u-loadmore @loadmore="loadmore" :status="status"/>
    </u-list>
  </view>
</template>
<script>
import {listElderlyDeviceBinding} from '@/api/zhyl/elderlyDeviceBinding'
import {baseUrl} from "@/config";

export default {
  options: {styleIsolation: 'shared'},
  data() {
    return {
      baseUrl: baseUrl,
      userInfo: this.$store.state.user,
      deviceList: [],
      deviceQueryParam: {
        pageNum: 1,
        pageSize: 10,
      },
      status: 'loadmore',
      show: false,
      total: 0,
    }
  },
  onLoad: function () {
    // console.log(this.$store.state.user)
  },
  created() {
    this.getDeviceList()
  },
  methods: {
    getDeviceList() {
      listElderlyDeviceBinding(this.deviceQueryParam).then(res => {
        this.deviceList = res.rows.map(item => ({
          deviceName: item.deviceName,
          deviceImageUrl: item.deviceImageUrl,
          deviceInfo: `${item.installationMode ?? ""} | ${item.tmBattery ?? ""}`,
          bindTime: item.bindTime
        }));
        // console.log(this.deviceList)
      })
    },
    scrolltolower() {
      this.loadmore()
    },
    loadmore() {
      if (this.deviceList.length >= this.total) {
        this.status = 'nomore'
        return
      }
      this.status = 'loading';
      this.queryParams.pageNum += 1
      this.getDeviceList()
    },
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

}
</style>
