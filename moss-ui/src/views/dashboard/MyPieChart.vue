<template>
  <div :class="className" :style="{ height: height, width: width }"/>
</template>

<script>
import * as echarts from 'echarts'

require('echarts/theme/macarons') // 引入ECharts主题
import resize from './mixins/resize' // 引入resize混入

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '500px'
    },
    pieData: {
      type: Array,
      required: true
    },
    pieName: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      chart: null
    }
  },
  watch: {
    pieData: {
      deep: true,
      handler(val) {
        this.initChart()
      }
    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
      window.addEventListener('resize', this.resizeChart)
    })
  },
  beforeDestroy() {
    if (this.chart) {
      this.chart.dispose()
      this.chart = null
    }
    window.removeEventListener('resize', this.resizeChart)
  },
  methods: {
    initChart() {
      this.chart = echarts.init(this.$el, 'macarons')
      this.updateChartOptions()
    },
    resizeChart() {
      if (this.chart) {
        this.chart.resize()
        this.updateChartOptions() // 在调整大小时重新设置图例位置
      }
    },
    updateChartOptions() {
      const option = {
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: '60%',            // 使用百分比动态定位
          top: '40%',             // 使用百分比动态定位
          orient: 'horizontal',
          itemWidth: 14,
          itemHeight: 14,
          itemGap: 10,
          width: 'auto',
          padding: [5, 10, 5, 10],
          formatter: function (name) {
            return echarts.format.truncateText(name, 100, '14px Microsoft Yahei', '…')
          },
          tooltip: {
            show: true
          }
        },
        toolbox: {
          show: true,
          feature: {
            mark: {show: true},
            dataView: {show: true, readOnly: false},
            restore: {show: true},
            saveAsImage: {show: true}
          }
        },
        series: [
          {
            name: this.pieName,
            type: 'pie',
            radius: [20, 150],
            center: ['40%', '50%'], // 饼图的位置同样使用百分比
            roseType: 'area',
            itemStyle: {
              borderRadius: 1
            },
            labelLine: {
              show: true,
              length: 20,
              length2: 10,
              smooth: 0.2,
              lineStyle: {
                color: '#333',
                width: 2
              }
            },
            label: {
              show: true,
              position: 'outside',
              formatter: '{b}: {c} ({d}%)'
            },
            data:this.pieData
          }
        ]
      }
      this.chart.setOption(option)
    }
  }
}
</script>
