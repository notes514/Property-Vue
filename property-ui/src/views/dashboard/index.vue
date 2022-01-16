<template>
  <div class="app-container">
    <el-row :gutter="50" class="card-row">
      <el-col :span="6">
        <el-card shadow="always" class="el-card__body" style="background: #87cefa">
          <el-row>
            <el-col :span="12">
              <div>
                <h2 style="color: white">{{ this.ownerCount }}</h2>
                <h5 style="color: white">业主</h5>
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
                <h2 style="color: white">{{ this.noticeCount }}</h2>
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
        <el-card shadow="always" class="el-card__body" style="background: #00bfff">
          <el-row>
            <el-col :span="12">
              <div>
                <h2 style="color: white">{{ this.repairCount }}</h2>
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
        <el-card shadow="always" class="el-card__body" style="background: #90ee90">
          <el-row>
            <el-col :span="12">
              <div>
                <h2 style="color: white">{{ this.complaintCount }}</h2>
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
        <el-card style="text-align: center" v-loading="buildingGaugeLoading">
          <div slot="header" class="card-header">
            <span class="card-header-title">楼栋统计</span>
            <el-button round @click="refreshBuilding">刷新</el-button>
          </div>
          <el-row :span="6">
            <el-col>
              <echarts-gauge
                id="buildingGaugeOption"
                style="width: 100%; height: 180px;"
                :option="buildingGaugeOption"/>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div class="grid-content bg-purple">
                <span style="font-weight:bold; color: #87CEFA">{{this.stairsCount}}</span>
                <p/>
                <span style="font-weight:bold;">楼梯房</span>
              </div>
            </el-col>
            <el-col :span="12">
              <div>
                <span style="font-weight:bold; color: #87CEFA">{{this.elevatorCount}}</span>
                <p/>
                <span style="font-weight:bold;">电梯房</span>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="text-align: center" v-loading="houseGaugeLoading">
          <div slot="header" class="card-header">
            <span class="card-header-title">房屋统计</span>
            <el-button round @click="refreshHouse">刷新</el-button>
          </div>
          <el-row :span="6">
            <el-col>
              <echarts-gauge
                id="houseGaugeOption"
                style="width: 100%; height: 180px;"
                :option="houseGaugeOption"
              />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div>
                <span style="font-weight:bold; color: #00ced1">{{this.forSaleCount}}</span>
                <p/>
                <span style="font-weight:bold;">未销售</span>
              </div>
            </el-col>
            <el-col :span="12">
              <div>
                <span style="font-weight:bold; color: #00ced1">{{this.haveSalesCount}}</span>
                <p/>
                <span style="font-weight:bold;">已入住</span>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style="text-align: center" v-loading="chargeDetailGaugeLoading">
          <div slot="header" class="card-header">
            <span class="card-header-title">缴费统计</span>
            <el-button round @click="refreshChargeDetail">刷新</el-button>
          </div>
          <el-row :span="6">
            <el-col>
              <echarts-gauge
                id="chargeDetailGaugeOption"
                style="width: 100%; height: 180px;"
                :option="chargeDetailGaugeOption"
              />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div>
                <span style="font-weight:bold; color: #00BFFF">{{this.notPayCostCount}}</span>
                <p/>
                <span style="font-weight:bold;">未缴费</span>
              </div>
            </el-col>
            <el-col :span="12">
              <div>
                <span style="font-weight:bold; color: #00BFFF">{{this.payCostCount}}</span>
                <p/>
                <span style="font-weight:bold;">已缴费</span>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
      <el-col style="text-align: center" :span="6">
        <el-card v-loading="supplyGaugeLoading">
          <div slot="header" class="card-header">
            <span class="card-header-title">供求统计</span>
            <el-button round @click="refreshSupply">刷新</el-button>
          </div>
          <el-row :span="6">
            <el-col>
              <echarts-gauge
                id="supplyGaugeOption"
                style="width: 100%; height: 180px;"
                :option="supplyGaugeOption"
              />
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div>
                <span style="font-weight:bold; color: #90EE90">{{this.startCount}}</span>
                <p/>
                <span style="font-weight:bold;">未开始</span>
              </div>
            </el-col>
            <el-col :span="12">
              <div>
                <span style="font-weight:bold; color: #90EE90">{{this.endCount}}</span>
                <p/>
                <span style="font-weight:bold;">已结束</span>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="40" class="card-row">
      <el-col :span="8">
        <el-card v-loading="activityGaugeLoading" style="height: 300px;">
          <div slot="header" class="card-header">
            <span class="card-header-title">最新活动</span>
            <el-button round @click="refreshActivity">刷新</el-button>
          </div>
          <div v-show="activityDataList.length >= 0" v-for="activity in activityDataList" :key="activity.index"
               class="text item">
            <span>{{ activity.title | ellipsis }}</span>
            <span style="float: right;">{{ activity.gmtCreate }}</span>
          </div>
          <div v-show="activityDataList.length === 0">
            <div style="text-align: center">
              <el-image src="/src/icons/png/temporarily_no_data.png"></el-image>
            </div>
            <div style="text-align: center; padding-top: 10px">
              <span style="color: #bbbbbb;">暂无数据</span>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card v-loading="tradingGaugeLoading" style="height: 300px;">
          <div slot="header" class="card-header">
            <span class="card-header-title">最新交易</span>
            <el-button round @click="refreshTrading">刷新</el-button>
          </div>
          <div v-show="supplyDataList.length >= 0" v-for="supply in supplyDataList" :key="supply.index"
               class="text item">
            <span>{{ supply.title | ellipsis }}</span>
            <span style="float: right;">{{ supply.gmtCreate }}</span>
          </div>
          <div v-show="supplyDataList.length === 0">
            <div style="text-align: center">
              <el-image src="/src/icons/png/temporarily_no_data.png"></el-image>
            </div>
            <div style="text-align: center; padding-top: 10px">
              <span style="color: #bbbbbb;">暂无数据</span>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card v-loading="repairGaugeLoading" style="height: 300px;">
          <div slot="header" class="card-header">
            <span class="card-header-title">最新报修</span>
            <el-button round @click="refreshRepair">刷新</el-button>
          </div>
          <div v-show="repairDataList.length >= 0" v-for="repair in repairDataList" :key="repair.index"
               class="text item">
            <span>{{ repair.repairType | ellipsis }}</span>
            <span style="float: right;">{{ repair.gmtCreate }}</span>
          </div>
          <div v-show="repairDataList.length === 0">
            <div style="text-align: center">
              <el-image src="/src/icons/png/temporarily_no_data.png"></el-image>
            </div>
            <div style="text-align: center; padding-top: 10px">
              <span style="color: #bbbbbb;">暂无数据</span>
            </div>
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
      ownerCount: 0,
      noticeCount: 0,
      repairCount: 0,
      complaintCount: 0,
      buildingCount: 0,
      houseCount: 0,
      chargeDetailCount: 0,
      supplyCount: 0,
      buildingGaugeLoading: false,
      houseGaugeLoading: false,
      chargeDetailGaugeLoading: false,
      supplyGaugeLoading: false,
      activityGaugeLoading: false,
      tradingGaugeLoading: false,
      repairGaugeLoading: false,
      buildingGaugeOption: {
        series: [
          {
            data: [],
            progress: {
              itemStyle: {
                color: '#87CEFA'
              }
            }
          },
        ],
        graphic: {
          style: {
            text: ''
          }
        }
      },
      houseGaugeOption: {
        series: [
          {
            data: [],
            progress: {
              itemStyle: {
                color: '#00ced1'
              }
            }
          },
        ],
        graphic: {
          style: {
            text: ''
          }
        }
      },
      chargeDetailGaugeOption: {
        series: [
          {
            data: [],
            progress: {
              itemStyle: {
                color: '#00BFFF'
              }
            }
          },
        ],
        graphic: {
          style: {
            text: ''
          }
        }
      },
      supplyGaugeOption: {
        series: [
          {
            data: [],
            progress: {
              itemStyle: {
                color: '#90EE90'
              }
            }
          },
        ],
        graphic: {
          style: {
            text: ''
          }
        }
      },
      elevatorCount: 0,
      stairsCount: 0,
      haveSalesCount: 0,
      forSaleCount: 0,
      notPayCostCount: 0,
      payCostCount: 0,
      startCount: 2,
      endCount: 0,
      activityDataList: [0],
      supplyDataList: [0],
      repairDataList: [0],
    }
  },
  filters: {
    ellipsis(value) {
      if (!value) return ''
      if (value.length > 10) {
        return value.slice(0, 10) + '...'
      }
      return value
    }
  },
  created() {
    this.getOwnerCount();
    this.getNoticeCount();
    this.getRepairCount();
    this.getComplaintCount();
    this.getBuildingCount();
    this.getHouseCount();
    this.getChargeDetailCount();
    this.getSupplyCount();
    this.getLatestActivity();
    this.getLatestSupply();
    this.getLatestRepair();
  },
  methods: {
    getOwnerCount() {
      this.api({
        url: "/report/getOwnerCount",
        method: "get",
      }).then(data => {
        this.ownerCount = data;
      })
    },
    getNoticeCount() {
      this.api({
        url: "/report/getNoticeCount",
        method: "get",
      }).then(data => {
        this.noticeCount = data;
      })
    },
    getRepairCount() {
      this.api({
        url: "/report/getRepairCount",
        method: "get",
      }).then(data => {
        this.repairCount = data;
      })
    },
    getComplaintCount() {
      this.api({
        url: "/report/getComplaintCount",
        method: "get",
      }).then(data => {
        this.complaintCount = data;
      })
    },
    getBuildingCount() {
      this.api({
        url: "/report/getBuildingCount",
        method: "get",
      }).then(data => {
        this.buildingGaugeLoading = false;
        this.buildingCount = data.totalCount;
        this.buildingGaugeOption.series[0].data[0] = data.totalCount;
        this.buildingGaugeOption.graphic.style.text = data.totalCount;
        this.elevatorCount = data.elevatorCount;
        this.stairsCount = data.stairsCount;
      })
    },
    getHouseCount() {
      this.api({
        url: "/report/getHouseCount",
        method: "get",
      }).then(data => {
        this.houseGaugeLoading = false;
        this.houseCount = data.totalCount;
        this.houseGaugeOption.series[0].data[0] = data.totalCount;
        this.houseGaugeOption.graphic.style.text = data.totalCount;
        this.haveSalesCount = data.haveSalesCount;
        this.forSaleCount = data.forSaleCount;
      })
    },
    getChargeDetailCount() {
      this.api({
        url: "/report/getChargeDetailCount",
        method: "get",
      }).then(data => {
        this.chargeDetailGaugeLoading = false;
        this.chargeDetailCount = data.totalCount;
        this.chargeDetailGaugeOption.series[0].data[0] = data.totalCount;
        this.chargeDetailGaugeOption.graphic.style.text = data.totalCount;
        this.notPayCostCount = data.notPayCostCount;
        this.payCostCount = data.payCostCount;
      })
    },
    getSupplyCount() {
      this.api({
        url: "/report/getSupplyCount",
        method: "get",
      }).then(data => {
        this.supplyGaugeLoading = false;
        this.supplyCount = data.totalCount;
        this.supplyGaugeOption.series[0].data[0] = data.totalCount;
        this.supplyGaugeOption.graphic.style.text = data.totalCount;
        this.startCount = data.startCount;
        this.endCount = data.endCount;
      })
    },
    getLatestActivity() {
      this.api({
        url: "/report/getLatestActivity",
        method: "get",
      }).then(data => {
        this.activityGaugeLoading = false;
        this.activityDataList = data;
      })
    },
    getLatestSupply() {
      this.api({
        url: "/report/getLatestSupply",
        method: "get",
      }).then(data => {
        this.tradingGaugeLoading = false;
        this.supplyDataList = data;
      })
    },
    getLatestRepair() {
      this.api({
        url: "/report/getLatestRepair",
        method: "get",
      }).then(data => {
        this.repairGaugeLoading = false;
        this.repairDataList = data;
      })
    },
    refreshBuilding() {
      this.buildingGaugeLoading = true;
      setTimeout(() => {
        this.getBuildingCount();
      }, 300);
    },
    refreshHouse() {
      this.houseGaugeLoading = true;
      setTimeout(() => {
        this.getHouseCount();
      }, 300);
    },
    refreshChargeDetail() {
      this.chargeDetailGaugeLoading = true;
      setTimeout(() => {
        this.getChargeDetailCount();
      }, 300);
    },
    refreshSupply() {
      this.supplyGaugeLoading = true;
      setTimeout(() => {
        this.getSupplyCount();
      }, 300);
    },
    refreshActivity() {
      this.activityGaugeLoading = true;
      setTimeout(() => {
        this.getLatestActivity();
      }, 300);
    },
    refreshTrading() {
      this.tradingGaugeLoading = true;
      setTimeout(() => {
        this.getLatestSupply();
      }, 300);
    },
    refreshRepair() {
      this.repairGaugeLoading = true;
      setTimeout(() => {
        this.getLatestRepair();
      }, 300);
    }
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
  padding: 2px;
  background: #00ced1;
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
