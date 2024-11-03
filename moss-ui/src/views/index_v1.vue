<template>
  <div class="dashboard-editor-container">

    <panel-group @handleSetLineChartData="handleSetLineChartData"/>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <my-line-chart :chart-data="lineChartData" :name="lineChartName"/>
    </el-row>

    <el-row :gutter="32" style="margin-bottom: 32px">
      <el-col :xs="24" :sm="24" :lg="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span style="font-size: 24px">预警消息</span>
            <el-button style="float: right; padding: 3px 0" type="text">
              <router-link :to="'/alarm/warning'" class="link-type">
                <span>查看更多</span>
              </router-link>
            </el-button>
          </div>
          <ul class="infinite-list" style="overflow: auto; max-height: 300px">
            <div style="position: sticky; top: 0; background: white; z-index: 1;">
              <el-row :gutter="30"
                      style="width: 100%; font-weight: bold; font-size: 12px; color: #606266; text-align: center;">
                <el-col :span="3">
                  <h3 style="font-weight: bold">序号</h3>
                </el-col>
                <el-col :span="7">
                  <h3 style="font-weight: bold">长者</h3>
                </el-col>
                <el-col :span="7">
                  <h3 style="font-weight: bold">设备</h3>
                </el-col>
                <el-col :span="7">
                  <h3 style="font-weight: bold">时间</h3>
                </el-col>
              </el-row>
            </div>
            <div v-for="(sos, index) in sosList" :key="index" class="infinite-list-item">
              <el-row :gutter="24" style="width: 100%;text-align: center;">
                <el-col :span="3">
                  <h4>
                    <span>{{ index + 1 }}</span>
                  </h4>
                </el-col>
                <el-col :span="7">
                  <h4>{{ sos.userInfoName }}</h4>
                </el-col>
                <el-col :span="7">
                  <h4>{{ sos.deviceId }}</h4>
                </el-col>
                <el-col :span="7">
                  <h4>{{ sos.createTime }}</h4>
                </el-col>
              </el-row>
            </div>
          </ul>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span style="font-size: 24px">长者年纪分布</span>
            <el-button style="float: right; padding: 3px 0" type="text">
              <router-link :to="'/userInfoManage/userInfo'" class="link-type">
                <span>查看更多</span>
              </router-link>
            </el-button>
          </div>
          <div class="chart-wrapper">
            <my-only-bar-chart bar-name="长者年龄分布" :bar-data="ageDistributionChartData"/>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="24">
        <div class="chart-wrapper" style="height: 500px ">
          <my-pie-chart :pie-data="deviceTypeChartData" pie-name="设备类型" style="padding-top: 30px"/>
        </div>
      </el-col>
    </el-row>


  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'
import LineChart from './dashboard/LineChart'
import RaddarChart from './dashboard/RaddarChart'
import PieChart from './dashboard/PieChart'
import BarChart from './dashboard/BarChart'
import MyLineChart from "@/views/dashboard/MyLineChart.vue";
import {
  getBindingStaticByCreateTimeCounts,
  getDeviceByDeviceTypeCount,
  getUploadingDateStaticByCreateTimeCounts,
  getUserInfoElderlyAge,
  getUserinfoElderlyStaticByCreateTimeCounts,
  getUserinfoFamilyStaticByCreateTimeCounts
} from "@/api/zhyl/statics";
import MyPieChart from "@/views/dashboard/MyPieChart.vue";
import {listDeviceUploadingData} from "@/api/zhyl/deviceUploadingData";
import MyOnlyBarChart from "@/views/dashboard/MyOnlyBarChart.vue";

export default {
  name: 'Index',
  components: {
    MyOnlyBarChart,
    MyPieChart,
    MyLineChart,
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart
  },
  data() {
    const today = new Date();
    const sevenDaysAgo = new Date();
    sevenDaysAgo.setDate(today.getDate() - 7);

    const formatDate = (date) => {
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');
      return `${year}-${month}-${day}`;
    };
    return {
      //折线图统计
      lineChartData: {},
      queryParam: {
        params: {
          endCreateTime: formatDate(today),
          beginCreateTime: formatDate(sevenDaysAgo)
        }
      },
      familyLineChartData: {},
      elderlyLineChartData: {},
      sosLineChartData: {},
      deviceLineChartData: {},
      lineChartName: '长者信息统计',
      //预警
      // 查询参数
      sosQueryParams: {
        pageNum: 1,
        pageSize: 100,
        dataId: null,
        deviceId: null,
        type: null,
        command: 'warning',
        deviceType: null,
        userInfoId: null,
        warningType: null,
        processingStatus: '0',
        createTime: null,
        updateTime: null,
        delFlag: '0'
      },
      sosList: [],
      // 年龄分布
      ageDistributionChartData: {},
      //设备类型
      deviceTypeChartData: []
    }
  },
  created() {
    this.getFamilyLineChartData()
    this.getSosLineChartData()
    this.getBindingLineChartData()
    this.getElderlyLineChartData()
    this.getSosList()
    this.getAgeDistributionChartData()
    this.getDeviceTypeChartData()
  },
  methods: {
    getDeviceTypeChartData() {
      getDeviceByDeviceTypeCount().then(res=>{
        this.deviceTypeChartData = res.data.datas
      })
    },
    getAgeDistributionChartData() {
      getUserInfoElderlyAge().then(res => {
        this.ageDistributionChartData = res.data
      })
    },
    getSosList() {
      listDeviceUploadingData(this.sosQueryParams).then(response => {
        this.sosList = response.rows;
      });
    },
    // region
    handleSetLineChartData(type) {
      if (type === 'familyLineChartData') {
        this.lineChartData = this.familyLineChartData
        this.lineChartName = '家属信息统计'
      } else if (type === 'elderlyLineChartData') {
        this.lineChartData = this.elderlyLineChartData
        this.lineChartName = '长者信息统计'
      } else if (type === 'sosLineChartData') {
        this.lineChartData = this.sosLineChartData
        this.lineChartName = '预警信息统计'
      } else if (type === 'deviceLineChartData') {
        this.lineChartData = this.deviceLineChartData
        this.lineChartName = '设备信息统计'
      } else {
        this.lineChartData = this.elderlyLineChartData
        this.lineChartName = '长者信息统计'
      }
    },
    getFamilyLineChartData() {
      getUserinfoFamilyStaticByCreateTimeCounts(this.queryParam).then(res => {
        this.familyLineChartData = res.data
        console.log(this.familyLineChartData)
      })
    },
    getElderlyLineChartData() {
      getUserinfoElderlyStaticByCreateTimeCounts(this.queryParam).then(res => {
        this.elderlyLineChartData = res.data
        this.lineChartData = this.elderlyLineChartData
      })
    },
    getBindingLineChartData() {
      getBindingStaticByCreateTimeCounts(this.queryParam).then(res => {
        this.deviceLineChartData = res.data
      })
    },
    getSosLineChartData() {
      getUploadingDateStaticByCreateTimeCounts(this.queryParam).then(res => {
        this.sosLineChartData = res.data
      })
    }
    // endregion
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}

::-webkit-scrollbar {
  width: 8px;
}

::-webkit-scrollbar-thumb {
  position: absolute;
  margin-left: 46px;
  background-color: #2272d5;
  border-radius: 3px;
}

/* Firefox 独有的滚动条圆角处理 */
@-moz-document url-prefix() {
  .infinite-list {
    scrollbar-width: thin;
    scrollbar-color: #2272d5 #f1f1f1;
    border-radius: 3px;
  }
}

.box-card {
  height: 400px;
}
</style>
