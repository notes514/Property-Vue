<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="活动内容">
          <el-input prefix-icon="el-icon-search" placeholder="请输入楼栋名称" v-model="activityTitleText" clearable
                    style="width: 250px">
          </el-input>
        </el-form-item>
        <el-form-item label="活动状态">
          <el-select v-model="statusSelect" placeholder="请选择楼型">
            <el-option label="全部" value="-1"></el-option>
            <el-option label="未开始" value="0"></el-option>
            <el-option label="进行中" value="1"></el-option>
            <el-option label="已结束" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="queryOwnerNameAndTypeList">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-circle-plus-outline" @click="showCreate" v-permission="'role:add'"
                     plain>添加活动</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="list" v-loading="listLoading" border fit highlight-current-row>
      <el-table-column align="center" label="序号" width="50">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"> </span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="title" label="活动内容" width="220"></el-table-column>
      <el-table-column align="center" prop="address" label="活动地点" width="180"/>
      <el-table-column align="center" prop="organizer" label="举办单位" width="180"/>
      <el-table-column align="center" prop="status" label="活动状态" :formatter="statusFormat" width="120"/>
      <el-table-column align="center" prop="startTime" label="开始时间" width="180"/>
      <el-table-column align="center" prop="endTime" label="结束时间" width="180"/>
      <el-table-column align="center" prop="gmtCreate" label="创建时间" width="180"/>
      <el-table-column align="center" prop="gmtModified" label="最近修改时间" width="180"/>
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
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempActivity" label-position="left" label-width="80px"
               style='width: 100%; margin-left: 48px'>
        <el-form-item label="活动内容" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" v-model="tempActivity.title" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="活动地点" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" v-model="tempActivity.address" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="举办单位" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" v-model="tempActivity.organizer" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="活动状态" v-show="dialogStatus !== 'remove'" required>
          <el-select v-model="tempActivity.status" placeholder="请选择活动状态" style="width: 30%;">
            <el-option
              v-for="status in statusList"
              :key="status.statusValue"
              :label="status.statusLabel"
              :value="status.statusValue">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开始时间" v-show="dialogStatus !== 'remove'" required>
          <el-date-picker
            v-model="tempActivity.startTime"
            type="datetime"
            placeholder="请选择活动开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" v-show="dialogStatus !== 'remove'" required>
          <el-date-picker
            v-model="tempActivity.endTime"
            type="datetime"
            placeholder="请选择活动结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item v-show="dialogStatus === 'remove'" required>
          <span style="font-size: 16px">确定要删除该活动吗？</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="success" v-if="dialogStatus==='create'" @click="createActivity">创 建</el-button>
        <el-button type="primary" v-if="dialogStatus==='update'" @click="updateActivity">修 改</el-button>
        <el-button type="primary" v-if="dialogStatus==='remove'" @click="removeActivity">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "activity",
  data() {
    return {
      // 搜索内容数据
      activityTitleText: '',
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
        create: '新增活动',
        update: '修改活动',
        remove: '提示'
      },
      tempActivity: {
        id: '',
        title: '',
        address: '',
        organizer: '',
        status: '',
        startTime: '',
        endTime: '',
      },
      statusList: [
        {
          statusValue: '0',
          statusLabel: '活动未开始'
        }, {
          statusValue: '1',
          statusLabel: '活动进行中'
        }, {
          statusValue: '2',
          statusLabel: '活动已结束'
        }
      ]
    }
  },
  created() {
    this.getList();
  },

  methods: {
    getList() {
      this.listLoading = true;
      this.api({
        url: "/activity/listActivity",
        method: "get",
        params: this.listQuery
      }).then(data => {
        this.listLoading = false;
        this.list = data.list;
        this.totalCount = data.totalCount;
      })
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
      this.tempActivity.title = "";
      this.tempActivity.address = "";
      this.tempActivity.organizer = "";
      this.tempActivity.status = "";
      this.tempActivity.startTime = "";
      this.tempActivity.endTime = "";
      this.dialogStatus = "create"
      this.dialogFormVisible = true
    },
    showUpdate($index) {
      let owner = this.list[$index];
      this.tempActivity.id = owner.id;
      this.tempActivity.title = owner.title;
      this.tempActivity.address = owner.address;
      this.tempActivity.organizer = owner.organizer;
      this.tempActivity.status = owner.status;
      this.tempActivity.startTime = owner.startTime;
      this.tempActivity.endTime = owner.endTime;
      this.dialogStatus = "update"
      this.dialogFormVisible = true
    },
    showRemove($index) {
      let owner = this.list[$index];
      this.tempActivity.id = owner.id;
      this.tempActivity.title = owner.title;
      this.dialogStatus = "remove"
      this.dialogFormVisible = true
    },
    statusFormat(owner) {
      let index = owner.status;
      return this.statusList[index].statusLabel;
    },
    validate(isCreate) {
      let owner = this.tempActivity
      if (isCreate && owner.title.trim().length === 0) {
        this.$message.warning('请输入活动内容')
        return false
      }
      if (isCreate && owner.address.trim().length === 0) {
        this.$message.warning('请输入活动地点')
        return false
      }
      if (isCreate && owner.organizer.trim().length === 0) {
        this.$message.warning('请输入活动举办单位')
        return false
      }
      if (owner.status.trim().length === 0) {
        this.$message.warning('请选择活动状态')
        return false
      }
      if (owner.startTime === null || owner.startTime === '') {
        this.$message.warning('请选择活动开始日期')
        return false
      }
      if (owner.endTime === null || owner.endTime === '') {
        this.$message.warning('请选择活动结束日期')
        return false
      }

      return true
    },
    queryValidate(isCreate) {
      if (isCreate && this.activityTitleText.trim().length === 0 && this.statusSelect.trim().length === 0) {
        this.$message.warning('请输入或选择您的查询条件！')
        return false
      }
      return true
    },
    /**
     * 根据楼栋名称和类型进行模糊搜索
     */
    queryOwnerNameAndTypeList() {
      if (!this.queryValidate(true)) return
      this.listLoading = true;
      this.api({
        url: "/activity/likeActivity",
        method: "get",
        params: {
          "pageNum" : this.listQuery.pageNum,
          "pageRow" : this.listQuery.pageRow,
          "title" : this.activityTitleText.trim(),
          "status" : this.statusSelect === '-1' ? '' : this.statusSelect.trim(),
        }
      }).then(data => {
        this.listLoading = false;
        this.list = data.list;
        this.totalCount = data.totalCount;
      })
    },
    createActivity() {
      if (!this.validate(true)) return
      // 保存新活动
      this.api({
        url: "/activity/addActivity",
        method: "post",
        data: this.tempActivity
      }).then(() => {
        this.$message.success('添加成功！')
        this.getList();
        this.dialogFormVisible = false
      })
    },
    updateActivity() {
      if (!this.validate(false)) return
      // 修改活动
      this.api({
        url: "/activity/updateActivity",
        method: "post",
        data: this.tempActivity
      }).then(() => {
        this.$message.success('修改成功！')
        this.getList();
        this.dialogFormVisible = false
      });
    },
    removeActivity() {
      // 删除活动
      this.api({
        url: "/activity/deleteActivity",
        method: "post",
        params: {
          "id" : this.tempActivity.id
        }
      }).then(() => {
        this.$message.success('删除成功！')
        this.getList();
        this.dialogFormVisible = false
      })
    },
  }
}
</script>

<style scoped>

.el-pagination {
  float: right;
  margin-top: 16px;
}
</style>
