<template>
  <div class="dashboard-editor-container">

    <panel-group @handleSetLineChartData="handleSetLineChartData"/>

    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <my-line-chart :chart-data="lineChartData" :name="lineChartName"/>
    </el-row>

    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <raddar-chart/>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart/>
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <bar-chart/>
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
  getBindingStaticByCreateTimeCounts, getUploadingDateStaticByCreateTimeCounts,
  getUserinfoElderlyStaticByCreateTimeCounts,
  getUserinfoFamilyStaticByCreateTimeCounts
} from "@/api/zhyl/statics";

export default {
  name: 'Index',
  components: {
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
      lineChartName: '长者信息统计'
    }
  },
  created() {
    this.getFamilyLineChartData()
    this.getSosLineChartData()
    this.getBindingLineChartData()
    this.getElderlyLineChartData()
  },
  methods: {
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
</style>
