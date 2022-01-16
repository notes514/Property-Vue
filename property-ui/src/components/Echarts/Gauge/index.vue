<template>
  <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
  <div :id="id" />
</template>

<script>
// 引入 echarts 核心模块，核心模块提供了 echarts 使用必须要的接口。
import * as echarts from 'echarts/core'

// 引入仪表盘图表，图表后缀都为 Chart
import {
  GaugeChart
} from 'echarts/charts'

// 引入组件，组件后缀都为 Component
import { GraphicComponent } from 'echarts/components'

// 引入 Canvas 渲染器，注意引入 CanvasRenderer 或者 SVGRenderer 是必须的一步
import {
  CanvasRenderer
} from 'echarts/renderers'

// 注册必须的组件
echarts.use(
  [
    GaugeChart,
    CanvasRenderer,
    GraphicComponent
  ]
)

// lodash库中，递归合并对象属性的方法
import merge from 'lodash.merge'

function getDefaultBaseOption() {
  return {
    series: [
      {
        type: 'gauge',
        startAngle: 90,
        endAngle: -270,
        pointer: {
          show: false
        },
        title: {
          show: false
        },
        splitLine: {
          show: false
        },
        axisLine: {
          lineStyle: {
            width: 12
          }
        },
        axisTick: {
          show: false
        },
        axisLabel: {
          show: false
        },
        detail: {
          show: false
        },
        progress: {
          show: true,
          overlap: false,
          roundCap: true,
          clip: false
          // itemStyle: {
          //   borderWidth: 1,
          //   borderColor: '#464646'
          // }
        }
      }
    ],
    graphic: {
      type: 'text',
      left: 'center',
      top: 'middle',
      style: {
        text: '',
        font: 'bolder 24px monospace'
        // fill: 'rgba(100, 100, 100, 0.25)'
      },
      z: 100
    }
  }
}

export default {
  // 仪表盘-得分环图
  name: 'EchartsGauge',
  props: {
    id: {
      type: String,
      required: true
    },
    option: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      chartInstance: null,
      baseOption: getDefaultBaseOption()
    }
  },
  watch: {
    option: {
      handler(newValue) {
        this.setOption()
      },
      deep: true
    }
  },
  mounted() {
    this.init()
    this.setOption()
  },
  beforeDestroy() {
    // 由于window.onresize是全局事件，在其他页面改变界面时也会执行，这样可能会出现问题，
    // 需要在离开这个界面时注销window.onresize事件。
    window.removeEventListener('resize', () => this.resize())
    // 如果实例沒有被销毁，则销毁
    if (!this.chartInstance.isDisposed()) {
      // 销毁实例，销毁后实例无法再被使用
      this.chartInstance.dispose()
    }
  },
  methods: {
    resize() {
      this.chartInstance.resize()
    },
    init() {
      const chartDom = document.getElementById(this.id)
      // 基于准备好的dom，初始化echarts实例
      this.chartInstance = echarts.init(chartDom)
      window.addEventListener('resize', () => this.resize())
    },
    // 使用默认配置项和自定义配置项结合的方式渲染，如果配置项相同使用自定义配置项。
    setOption() {
      // 移除当前实例中所有的组件和图表,并设置新的配置项
      this.chartInstance.clear()
      // 因为merge方法会改变对象，所以重新设置默认属性
      const option = merge(this.baseOption, this.option)
      this.baseOption = getDefaultBaseOption()
      this.chartInstance.setOption(option)
    }
  }
}
</script>

<style scoped>

</style>
