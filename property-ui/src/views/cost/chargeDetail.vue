<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="项目名称">
          <el-select v-model="chargeNameText" placeholder="请选择">
            <el-option label="全部" value="-1"></el-option>
            <el-option v-for="item in chargeNameList" :key="item" :label="item" :value="item"/>
          </el-select>
        </el-form-item>
        <el-form-item label="缴费状态">
          <el-select v-model="payStatusSelect" placeholder="请选择">
            <el-option label="全部" value="-1"></el-option>
            <el-option label="未缴费" value="0"></el-option>
            <el-option label="已缴费" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="queryChargeNameAndStatusList" plain>查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button icon="el-icon-refresh" @click="reset" v-permission="'role:add'" plain>重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div style="margin-bottom: 16px">
      <el-button type="primary" icon="el-icon-circle-plus-outline" @click="showCreate" v-permission="'role:add'">
        添加</el-button>
    </div>
    <el-table :data="list" v-loading="listLoading" border :header-cell-style="{background:'#f5f7fa',color:'#606266'}">
      <el-table-column align="center" label="序号" width="50">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"> </span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="chargeName" label="项目名称"/>
      <el-table-column align="center" prop="ownerName" label="业主姓名"></el-table-column>
      <el-table-column align="center" prop="chargeStandard" label="应收金额"/>
      <el-table-column align="center" prop="payReal" label="实收金额"/>
      <el-table-column align="center" prop="payBalance" label="欠费金额"/>
      <el-table-column align="center" prop="payStatus" label="缴费状态" :formatter="chargeStandardFormat"/>
      <el-table-column align="center" prop="payTime" label="缴费时间"/>
      <el-table-column align="center" prop="gmtCreate" label="创建时间"/>
      <el-table-column align="center" prop="gmtModified" label="最近修改时间"/>
      <el-table-column align="center" label="管理">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="edit" @click="showUpdate(scope.$index)"
                     v-permission="'role:update'">修改</el-button>
          <el-button type="danger" size="mini" icon="edit" @click="showRemove(scope.$index)"
                     v-permission="'role:delete'">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="listQuery.pageNum"
      :page-size="listQuery.pageRow"
      :total="totalCount"
      :page-sizes="[10, 20, 30, 50]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <el-dialog :ownerName="textMap[dialogChargeStandard]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="chargeDetail" label-position="left" label-width="80px"
               style='width: 100%; margin-left: 48px'>
        <el-form-item label="项目名称" v-show="dialogChargeStandard !== 'remove'" required>
          <el-input type="text" v-model="chargeDetail.chargeName" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="业主姓名" v-show="dialogChargeStandard !== 'remove'" required>
          <el-input type="text" v-model="chargeDetail.ownerName" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="应收金额" v-show="dialogChargeStandard !== 'remove'" required>
          <el-input  type="text" v-model="chargeDetail.chargeStandard" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="实收金额" v-show="dialogChargeStandard !== 'remove'" required>
          <el-input  type="text" v-model="chargeDetail.payReal" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="欠费金额" v-show="dialogChargeStandard !== 'remove'" required>
          <el-input  type="text" v-model="chargeDetail.payBalance" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="缴费状态" v-show="dialogChargeStandard !== 'remove'" required>
          <template>
            <el-radio-group v-model="chargeDetail.payStatus">
              <el-radio :label="'0'">未缴费</el-radio>
              <el-radio :label="'1'">已缴费</el-radio>
            </el-radio-group>
          </template>
        </el-form-item>
        <el-form-item label="发布时间" v-show="dialogChargeStandard !== 'remove'" required>
          <el-date-picker
            v-model="chargeDetail.payReal"
            type="datetime"
            placeholder="请选择公告发布时间">
          </el-date-picker>
        </el-form-item>

        <el-form-item v-show="dialogChargeStandard === 'remove'" required>
          <span style="font-size: 16px">确定要删除该公告吗？</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="success" v-if="dialogChargeStandard==='create'" @click="createChargeDetail">创 建</el-button>
        <el-button type="primary" v-if="dialogChargeStandard==='update'" @click="updateChargeDetail">修 改</el-button>
        <el-button type="primary" v-if="dialogChargeStandard==='remove'" @click="removeChargeDetail">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "ChargeDetail",
  data() {
    return {
      // 搜索内容数据
      chargeNameText: '',
      // 类型选择
      payStatusSelect: '',
      //分页组件--数据总条数
      totalCount: 0,
      //表格的数据
      list: [],
      // 费用名称列表集合
      chargeNameList: [],
      //数据加载等待动画
      listLoading: false,
      listQuery: {
        //页码
        pageNum: 1,
        //每页条数
        pageRow: 10,
        name: ''
      },
      dialogChargeStandard: 'create',
      dialogFormVisible: false,
      dialogVisible: false,
      textMap: {
        create: '新增费用项目明细',
        update: '编辑费用项目明细',
        remove: '提示'
      },
      chargeDetail: {
        id: '',
        ownerName: '',
        chargeName: '',
        chargeStandard: '',
        payReal: '',
        payBalance: '',
        payStatus: '',
        payTime: '',
      }
    }
  },
  created() {
    this.getList();
    this.getCharNameList();
  },

  methods: {
    getList() {
      this.listLoading = true;
      this.api({
        url: "/charge/detail/listChargeDetail",
        method: "get",
        params: this.listQuery
      }).then(data => {
        this.listLoading = false;
        this.list = data.list;
        this.totalCount = data.totalCount;
      })
    },
    getCharNameList() {
      this.api({
        url: "/charge/listChargeName",
        method: "get"
      }).then(data => {
        this.chargeNameList = data;
      })
    },
    reset() {
      if (this.chargeNameText.trim().length !== 0 || this.payStatusSelect.trim().length !== 0) {
        this.getList()
      }
      this.chargeNameText = '';
      this.payStatusSelect = '';

    },
    handleSizeChange(val) {
      // 改变每页数量
      this.listQuery.pageRow = val
      this.handleFilter();
    },
    handleCurrentChange(val) {
      // 改变页码
      this.listQuery.pageNum = val
      this.getList();
    },
    handleFilter() {
      // 改变了查询条件,从第一页开始查询
      this.listQuery.pageNum = 1
      this.getList()
    },
    getIndex($index) {
      // 表格序号
      return (this.listQuery.pageNum - 1) * this.listQuery.pageRow + $index + 1
    },
    showCreate() {
      // 显示新增对话框
      this.chargeDetail.ownerName = "";
      this.chargeDetail.chargeName = "";
      this.chargeDetail.chargeStandard = "";
      this.chargeDetail.payReal = "";
      this.chargeDetail.payBalance = "";
      this.chargeDetail.payStatus = "";
      this.dialogChargeStandard = "create"
      this.dialogFormVisible = true
    },
    showUpdate($index) {
      let chargeDetail = this.list[$index];
      this.chargeDetail.id = chargeDetail.id;
      this.chargeDetail.ownerName = chargeDetail.ownerName;
      this.chargeDetail.chargeName = chargeDetail.chargeName;
      this.chargeDetail.chargeStandard = chargeDetail.chargeStandard;
      this.chargeDetail.payReal = chargeDetail.payReal;
      this.chargeDetail.payBalance = chargeDetail.payBalance;
      this.chargeDetail.payStatus = chargeDetail.payStatus;
      this.dialogChargeStandard = "update"
      this.dialogFormVisible = true
    },
    showRemove($index) {
      let chargeDetail = this.list[$index];
      this.chargeDetail.id = chargeDetail.id;
      this.dialogChargeStandard = "remove"
      this.dialogFormVisible = true
    },
    chargeStandardFormat(chargeDetail) {
      return chargeDetail.payStatus === '0' ? '未缴费' : '已缴费';
    },
    validate(isCreate) {
      let chargeDetail = this.chargeDetail
      if (isCreate && chargeDetail.ownerName.trim().length === 0) {
        this.$message.warning('请输入项目名称')
        return false
      }
      if (isCreate && chargeDetail.chargeName.trim().length === 0) {
        this.$message.warning('请输入公告地点')
        return false
      }
      if (chargeDetail.chargeStandard.trim().length === 0) {
        this.$message.warning('请选择缴费状态')
        return false
      }
      if (chargeDetail.payReal === null || chargeDetail.payReal === '') {
        this.$message.warning('请选择发布时间')
        return false
      }

      return true
    },
    queryValidate() {
      if (this.chargeNameText.trim().length === 0 && this.payStatusSelect.trim().length === 0) {
        this.$message.warning('请选择您的查询条件！')
        return false
      }
      return true
    },
    /**
     * 根据楼栋名称和类型进行模糊搜索
     */
    queryChargeNameAndStatusList() {
      if (!this.queryValidate()) return
      this.listLoading = true;
      this.api({
        url: "/charge/detail/likeChargeDetailNameAndStatus",
        method: "get",
        params: {
          "pageNum" : this.listQuery.pageNum,
          "pageRow" : this.listQuery.pageRow,
          "chargeName" : this.chargeNameText === '-1' ? '' : this.chargeNameText.trim(),
          "payStatus" : this.payStatusSelect === '-1' ? '' : this.payStatusSelect.trim(),
        }
      }).then(data => {
        this.listLoading = false;
        this.list = data.list;
        this.totalCount = data.totalCount;
      })
    },
    createChargeDetail() {
      if (!this.validate(true)) return
      // 保存新公告
      this.api({
        url: "/charge/detail/addChargeDetail",
        method: "post",
        data: this.chargeDetail
      }).then(() => {
        this.$message.success('添加成功！')
        this.getList();
        this.dialogFormVisible = false
      })
    },
    updateChargeDetail() {
      if (!this.validate(false)) return
      // 修改公告
      this.api({
        url: "/charge/detail/updateChargeDetail",
        method: "post",
        data: this.chargeDetail
      }).then(() => {
        this.$message.success('修改成功！')
        this.getList();
        this.dialogFormVisible = false
      });
    },
    removeChargeDetail() {
      // 删除公告
      this.api({
        url: "/charge/detail/deleteChargeDetail",
        method: "post",
        params: {
          "id" : this.chargeDetail.id
        }
      }).then(() => {
        this.$message.success('删除成功！')
        this.getList();
        this.dialogFormVisible = false
      })
    }
  }
}
</script>

<style scoped>

.el-pagination {
  float: right;
  margin-top: 16px;
}
</style>
