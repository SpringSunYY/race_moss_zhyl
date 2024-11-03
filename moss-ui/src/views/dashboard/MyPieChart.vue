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
    }
  },
  data() {
    return {
      chart: null
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
            name: 'Nightingale Chart',
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
            data: [
              {value: 40, name: 'rose 1'},
              {value: 38, name: 'rose 2'},
              {value: 32, name: 'rose 3'},
              {value: 30, name: 'rose 4'},
              {value: 28, name: 'rose 5'},
              {value: 26, name: 'rose 6'},
              {value: 22, name: 'rose 7'},
              {value: 18, name: 'rose 8'},
              {value: 15, name: 'rose 9'},
              {value: 12, name: 'rose 10'},
              {value: 40, name: 'rose 11'},
              {value: 38, name: 'rose 12'},
              {value: 32, name: 'rose 13'},
              {value: 30, name: 'rose 14'},
              {value: 28, name: 'rose 15'},
              {value: 26, name: 'rose 16'},
              {value: 22, name: 'rose 17'},
              {value: 18, name: 'rose 18'},
              {value: 15, name: 'rose 19'},
              {value: 12, name: 'rose 110'}
            ]
          }
        ]
      }
      this.chart.setOption(option)
    }
  }
}
</script>
