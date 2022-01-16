<template>
  <div class="app-container">

    <el-row :gutter="50" class="card-row">
      <el-col :span="6">
        <el-card shadow="always" class="el-card__body" style="background: lightblue">
          <el-row>
            <el-col :span="12">
              <div>
                <h2 style="color: white">89</h2>
                <h4 style="color: white">业主</h4>
              </div>
            </el-col>
            <el-col :span="12">
              <div style="margin: 0 auto; padding-top: 22px; padding-left: 60%">
                <el-image class="el-image__inner" src="/src/icons/png/report_owner.png"></el-image>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="always" class="el-card__body">
          <el-row>
            <el-col :span="12">
              <div>
                <h2 style="color: white">99</h2>
                <h5 style="color: white">通知公告</h5>
              </div>
            </el-col>
            <el-col :span="12">
              <div style="margin: 0 auto; padding-top: 22px; padding-left: 60%">
                <el-image class="el-image__inner" src="/src/icons/png/report_notice.png"></el-image>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="always" class="el-card__body" style="background: deepskyblue">
          <el-row>
            <el-col :span="12">
              <div>
                <h2 style="color: white">59</h2>
                <h5 style="color: white">用户报修</h5>
              </div>
            </el-col>
            <el-col :span="12">
              <div style="margin: 0 auto; padding-top: 22px; padding-left: 60%">
                <el-image class="el-image__inner" src="/src/icons/png/report_repair.png"></el-image>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="always" class="el-card__body" style="background: lightgreen">
          <el-row>
            <el-col :span="12">
              <div>
                <h2 style="color: white">49</h2>
                <h5 style="color: white">用户投诉</h5>
              </div>
            </el-col>
            <el-col :span="12">
              <div style="margin: 0 auto; padding-top: 22px; padding-left: 60%">
                <el-image class="el-image__inner" src="/src/icons/png/report_complaint.png"></el-image>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="50" class="card-row">
      <el-col :span="6">
        <el-card style="text-align: center" v-loading="gaugeLoading">
          <div slot="header" class="card-header">
            <span class="card-header-title">楼栋统计</span>
            <el-button round>刷新</el-button>
          </div>
          <el-row :span="16">
            <el-col>
              <echarts-gauge
                id="gauge"
                style="width: 100%; height: 300px;"
                :option="gaugeOption"/>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card v-loading="gaugeLoading">
          <div slot="header" class="card-header">
            <span class="card-header-title">房屋统计</span>
            <el-button round>刷新</el-button>
          </div>
          <el-row :span="16">
            <el-col>
              <echarts-gauge
                id="gauge4"
                style="width: 100%; height: 300px;"
                :option="gauge2Option"
              />
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card v-loading="gaugeLoading">
          <div slot="header" class="card-header">
            <span class="card-header-title">缴费统计</span>
            <el-button round>刷新</el-button>
          </div>
          <el-row :span="16">
            <el-col>
              <echarts-gauge
                id="gauge7"
                style="width: 100%; height: 300px;"
                :option="gauge3Option"
              />
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card v-loading="gaugeLoading">
          <div slot="header" class="card-header">
            <span class="card-header-title">业主供求统计</span>
            <el-button round>刷新</el-button>
          </div>
          <el-row :span="16">
            <el-col>
              <echarts-gauge
                id="gauge8"
                style="width: 100%; height: 300px;"
                :option="gauge3Option"
              />
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="40" class="card-row">
      <el-col :span="8">
        <el-card style="text-align: left" v-loading="gaugeLoading">
          <div slot="header" class="card-header">
            <span class="card-header-title">最新活动</span>
            <el-button round>刷新</el-button>
          </div>
          <div v-for="o in 5" :key="o" class="text item">
            {{ ' 五四青年节活动 ' + o }}
            <span style="float: right; color: #1482f0; font-weight: bold">未开始</span>
            <span style="float: right; margin-right: 64px">2021-12-28 08:52:44</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card v-loading="gaugeLoading">
          <div slot="header" class="card-header">
            <span class="card-header-title">最新交易</span>
            <el-button round>刷新</el-button>
          </div>
          <div v-for="o in 5" :key="o" class="text item">
            <span style="font-weight: bold">{{ ' 从家里带来50瓶野生蜂蜜 ' + o }}</span>
            <span style="float: right; color: #1482f0; font-weight: bold">未开始</span>
            <span style="float: right; margin-right: 64px">2021-12-28 08:52:44</span>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card v-loading="gaugeLoading">
          <div slot="header" class="card-header">
            <span class="card-header-title">最新报修</span>
            <el-button round>刷新</el-button>
          </div>
          <div v-for="o in 5" :key="o" class="text item">
            {{ ' 13楼摄像头维修 ' + o }}
            <span style="float: right; color: #1482f0; font-weight: bold">未开始</span>
            <span style="float: right; margin-right: 64px">2021-12-28 08:52:44</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import Gauge from '@/components/Echarts/Gauge'

export default {
  name: 'dashboard',
  components: {
    'echarts-gauge': Gauge
  },
  data() {
    return {
      src: 'https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg',
      gaugeTitle: '楼栋统计',
      gaugeLoading: false,
      gaugeOption: {
        series: [
          {
            data: [1],
            progress: {
              itemStyle: {
                color: ''
              }
            }
          }
        ],
        graphic: {
          style: {
            text: '65%'
          }
        }
      },
      gauge2Option: {
        series: [
          {
            data: [46],
            progress: {
              itemStyle: {
                color: ''
              }
            }
          }
        ],
        graphic: {
          style: {
            text: '46%'
          }
        }
      },
      gauge3Option: {
        series: [
          {
            data: [88],
            progress: {
              itemStyle: {
                color: ''
              }
            }
          }
        ],
        graphic: {
          style: {
            text: '88%'
          }
        }
      }
    }
  },
  created() {
    this.gaugeOption.series[0].progress.itemStyle.color = this.setGaugeColor(this.gaugeOption.series[0].data[0])
    this.gauge2Option.series[0].progress.itemStyle.color = this.setGaugeColor(this.gauge2Option.series[0].data[0])
    this.gauge3Option.series[0].progress.itemStyle.color = this.setGaugeColor(this.gauge3Option.series[0].data[0])
  },
  methods: {
    setGaugeColor(value) {
      if (value > 40 && value <= 80) {
        return '#4D9CFE'
      } else if (value > 80) {
        return '#3DE182'
      } else {
        return '#FFAB91'
      }
    },
  }
}
</script>

<style lang="scss" scoped>
.card-row {
  margin-bottom: 32px;

  &:last-child {
    margin-bottom: 0;
  }
}

.card-header {
  display: flex;
  align-items: center;
  justify-content: space-between;

  &-title {
    font-size: 18px;
    font-weight: bold;
  }
}

.scroll-table {
  padding: 0 20px;
  background-color: #0f375f;
  color: rgba(107, 230, 193, 0.8);
}

.scroll-table-header {
  height: 60px;
  line-height: 60px;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  border-bottom: 1px solid rgba(107, 230, 193, 0.5);
  font-weight: bold;
  font-size: 14px;
}

.scroll-wrapper {
  height: 360px;
  overflow: hidden;
  font-size: 14px;

  .scroll-table-row {
    padding: 16px 0;
    vertical-align: middle;
    //border-bottom: 1px solid #ccc;
    opacity: .8;
  }
}

.el-card__body {
  padding: 10px;
  background: darkturquoise;
}

.el-row::after {
  clear: both;
  vertical-align: center;
}

.card-owner {

}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.div-img {
  display: table-cell;
  vertical-align: middle;
  text-align: right;
}

.el-image__inner {
  max-width: 64px;
  max-height: 64px;
}

.text {
  font-size: 16px;
}

.item {
  padding: 10px 0;
}
</style>
