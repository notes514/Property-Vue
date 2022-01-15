<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="项目名称">
          <el-input prefix-icon="el-icon-search" placeholder="请输入项目名称" v-model="chargeNameText" clearable
                    style="width: 250px">
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="queryChargeNameList" plain>查询</el-button>
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
      <el-table-column align="center" prop="chargeName" label="项目名称" ></el-table-column>
      <el-table-column align="center" prop="chargeStandard" label="收费标准"/>
      <el-table-column align="center" prop="gmtCreate" label="创建时间" />
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
    <el-dialog :chargeName="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempCharge" label-position="left" label-width="80px"
               style='width: 100%; margin-left: 48px'>
        <el-form-item label="项目名称" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" v-model="tempCharge.chargeName" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="收费标准" v-show="dialogStatus !== 'remove'" required>
          <el-input  type="text" :rows="4" v-model="tempCharge.chargeStandard" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item v-show="dialogStatus === 'remove'" required>
          <span style="font-size: 16px">确定要删除该公告吗？</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="success" v-if="dialogStatus==='create'" @click="createCharge">创 建</el-button>
        <el-button type="primary" v-if="dialogStatus==='update'" @click="updateCharge">修 改</el-button>
        <el-button type="primary" v-if="dialogStatus==='remove'" @click="removeCharge">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "Charge",
  data() {
    return {
      // 搜索内容数据
      chargeNameText: '',
      //分页组件--数据总条数
      totalCount: 0,
      //表格的数据
      list: [],
      //数据加载等待动画
      listLoading: false,
      listQuery: {
        //页码
        pageNum: 1,
        //每页条数
        pageRow: 10,
        name: ''
      },
      dialogStatus: 'create',
      dialogFormVisible: false,
      dialogVisible: false,
      textMap: {
        create: '新增费用类型',
        update: '编辑费用类型',
        remove: '提示'
      },
      tempCharge: {
        id: '',
        chargeName: '',
        chargeStandard: ''
      }
    }
  },
  created() {
    this.getList();
  },

  methods: {
    getList() {
      this.listLoading = true;
      this.api({
        url: "/charge/listCharge",
        method: "get",
        params: this.listQuery
      }).then(data => {
        this.listLoading = false;
        this.list = data.list;
        this.totalCount = data.totalCount;
      })
    },
    reset() {
      if (this.chargeNameText.trim().length !== 0) {
        this.getList()
      }
      this.chargeNameText = '';
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
      this.tempCharge.chargeName = "";
      this.tempCharge.chargeStandard = "";
      this.dialogStatus = "create"
      this.dialogFormVisible = true
    },
    showUpdate($index) {
      let charge = this.list[$index];
      this.tempCharge.id = charge.id;
      this.tempCharge.chargeName = charge.chargeName;
      this.tempCharge.chargeStandard = charge.chargeStandard;
      this.dialogStatus = "update"
      this.dialogFormVisible = true
    },
    showRemove($index) {
      let charge = this.list[$index];
      this.tempCharge.id = charge.id;
      this.dialogStatus = "remove"
      this.dialogFormVisible = true
    },
    validate(isCreate) {
      let charge = this.tempCharge
      if (isCreate && charge.chargeName.trim().length === 0) {
        this.$message.warning('请输入项目名称')
        return false
      }
      if (isCreate && charge.chargeStandard.trim().length === 0) {
        this.$message.warning('请输入收费标准')
        return false
      }

      return true
    },
    queryValidate(isCreate) {
      if (isCreate && this.chargeNameText.trim().length === 0) {
        this.$message.warning('请输入您的查询条件！')
        return false
      }
      return true
    },

    /**
     * 根据楼栋名称和类型进行模糊搜索
     */
    queryChargeNameList() {
      if (!this.queryValidate(true)) return
      this.listLoading = true;
      this.api({
        url: "/charge/likeChargeName",
        method: "get",
        params: {
          "pageNum" : this.listQuery.pageNum,
          "pageRow" : this.listQuery.pageRow,
          "chargeName" : this.chargeNameText.trim()
        }
      }).then(data => {
        this.listLoading = false;
        this.list = data.list;
        this.totalCount = data.totalCount;
      })
    },
    createCharge() {
      if (!this.validate(true)) return
      // 保存新公告
      this.api({
        url: "/charge/addCharge",
        method: "post",
        data: this.tempCharge
      }).then(() => {
        this.$message.success('添加成功！')
        this.getList();
        this.dialogFormVisible = false
      })
    },
    updateCharge() {
      if (!this.validate(false)) return
      // 修改公告
      this.api({
        url: "/charge/updateCharge",
        method: "post",
        data: this.tempCharge
      }).then(() => {
        this.$message.success('修改成功！')
        this.getList();
        this.dialogFormVisible = false
      });
    },
    removeCharge() {
      // 删除公告
      this.api({
        url: "/charge/deleteCharge",
        method: "post",
        params: {
          "id" : this.tempCharge.id
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
