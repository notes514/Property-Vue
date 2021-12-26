<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="投诉内容">
          <el-input prefix-icon="el-icon-search" placeholder="请输入楼栋名称" v-model="complaintTypeText" clearable
                    style="width: 250px">
          </el-input>
        </el-form-item>
        <el-form-item label="处理状态">
          <el-select v-model="statusSelect" placeholder="请选择楼型">
            <el-option label="全部" value="-1"></el-option>
            <el-option label="未受理" value="0"></el-option>
            <el-option label="已受理" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="getcomplaintTypeAndStatusList" plain>查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button icon="el-icon-refresh" @click="reset" v-permission="'role:add'" plain>重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="list" v-loading="listLoading" border :header-cell-style="{background:'#f5f7fa',color:'#606266'}">
      <el-table-column align="center" label="序号" width="50">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"> </span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="complaintType" label="投诉类型" width="250" ></el-table-column>
      <el-table-column align="center" prop="complaintContent" label="投诉内容" width="300"/>
      <el-table-column align="center" prop="ownerName" label="投诉人员" width="150" ></el-table-column>
      <el-table-column align="center" prop="status" label="处理状态" :formatter="statusFormat" width="120" />
      <el-table-column align="center" prop="complaintTime" label="投诉时间" width="160" />
      <el-table-column align="center" prop="handler" label="处理人" width="120" />
      <el-table-column align="center" prop="gmtCreate" label="创建时间" width="160" />
      <el-table-column align="center" prop="gmtModified" label="最近修改时间" width="160" />
      <el-table-column align="center" label="管理" width="228">
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
    <el-dialog :complaintType="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempComplaint" label-position="left" label-width="80px"
               style='width: 100%; margin-left: 48px'>
        <el-form-item label="投诉类型" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" :disabled="true" v-model="tempComplaint.complaintType" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="投诉内容" v-show="dialogStatus !== 'remove'" required>
          <el-input type="textarea" :disabled="true" :rows="4" v-model="tempComplaint.complaintContent" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="投诉人员" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" :disabled="true" v-model="tempComplaint.ownerName" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="处理状态" v-show="dialogStatus !== 'remove'" required>
          <template>
            <el-radio-group v-model="tempComplaint.status">
              <el-radio :label="'0'">待受理</el-radio>
              <el-radio :label="'1'">已受理</el-radio>
            </el-radio-group>
          </template>
        </el-form-item>
        <el-form-item label="投诉时间"  v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" :disabled="true" v-model="tempComplaint.complaintTime" style="width: 50%;">
          </el-input>
        </el-form-item>

        <el-form-item v-show="dialogStatus === 'remove'" required>
          <span style="font-size: 16px">确定要删除该投诉吗？</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" v-show="dialogStatus==='update'" @click="updateComplaint">修 改</el-button>
        <el-button type="primary" v-show="dialogStatus==='remove'" @click="removeComplaint">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "Complaint",
  data() {
    return {
      // 搜索投书类型
      complaintTypeText: '',
      // 类型选择
      statusSelect: '',
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
        update: '编辑投诉',
        remove: '提示'
      },
      tempComplaint: {
        id: '',
        ownerName: '',
        complaintType: '',
        complaintContent: '',
        status: '',
        handler: '',
        complaintTime: ''
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
        url: "/complaint/listComplaint",
        method: "get",
        params: this.listQuery
      }).then(data => {
        this.listLoading = false;
        this.list = data.list;
        this.totalCount = data.totalCount;
      })
    },
    reset() {
      if (this.complaintTypeText.trim().length !== 0 || this.statusSelect.trim().length !== 0) {
        this.getList()
      }
      this.complaintTypeText = '';
      this.statusSelect = '';

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
    showUpdate($index) {
      let complaint = this.list[$index];
      this.tempComplaint.id = complaint.id;
      this.tempComplaint.ownerName = complaint.ownerName;
      this.tempComplaint.complaintType = complaint.complaintType;
      this.tempComplaint.complaintContent = complaint.complaintContent;
      this.tempComplaint.status = complaint.status;
      this.tempComplaint.handler = complaint.handler;
      this.tempComplaint.complaintTime = complaint.complaintTime;
      this.dialogStatus = "update"
      this.dialogFormVisible = true
    },
    showRemove($index) {
      let complaint = this.list[$index];
      this.tempComplaint.id = complaint.id;
      this.dialogStatus = "remove"
      this.dialogFormVisible = true
    },
    statusFormat(notice) {
      return notice.status === '0' ? '未受理' : '已受理';
    },
    validate() {
      if (this.tempComplaint.status.trim().length === 0) {
        this.$message.warning('请选择处理状态')
        return false
      }

      return true
    },
    queryValidate(isCreate) {
      if (isCreate && this.complaintTypeText.trim().length === 0 && this.statusSelect.trim().length === 0) {
        this.$message.warning('请输入或选择您的查询条件！')
        return false
      }
      return true
    },
    /**
     * 根据投诉类型和受理状态进行模糊搜索
     */
    getcomplaintTypeAndStatusList() {
      if (!this.queryValidate(true)) return
      this.listLoading = true;
      this.api({
        url: "/complaint/likeComplaint",
        method: "get",
        params: {
          "pageNum" : this.listQuery.pageNum,
          "pageRow" : this.listQuery.pageRow,
          "complaintType" : this.complaintTypeText.trim(),
          "status" : this.statusSelect === '-1' ? '' : this.statusSelect.trim(),
        }
      }).then(data => {
        this.listLoading = false;
        this.list = data.list;
        this.totalCount = data.totalCount;
      })
    },
    updateComplaint() {
      if (!this.validate(false)) return
      // 修改投诉
      this.api({
        url: "/complaint/updateComplaint",
        method: "post",
        data: this.tempComplaint
      }).then(() => {
        this.$message.success('修改成功！')
        this.getList();
        this.dialogFormVisible = false
      });
    },
    removeComplaint() {
      // 删除投诉
      this.api({
        url: "/complaint/deleteComplaint",
        method: "post",
        params: {
          "id" : this.tempComplaint.id
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
