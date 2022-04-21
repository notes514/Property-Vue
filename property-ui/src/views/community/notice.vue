<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="公告内容">
          <el-input prefix-icon="el-icon-search" placeholder="请输入楼栋名称" v-model="noticeTitleText" clearable
                    style="width: 250px">
          </el-input>
        </el-form-item>
        <el-form-item label="公告状态">
          <el-select v-model="statusSelect" placeholder="请选择楼型">
            <el-option label="全部" value="-1"></el-option>
            <el-option label="关闭" value="0"></el-option>
            <el-option label="开启" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="queryOwnerNameAndTypeList" plain>查询</el-button>
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
      <el-table-column align="center" prop="title" label="公告标题" width="380" ></el-table-column>
      <el-table-column align="center" prop="content" label="公告内容" width="400"/>
      <el-table-column align="center" prop="status" label="公告状态" :formatter="statusFormat" width="100" />
      <el-table-column align="center" prop="releaseTime" label="发布时间" width="180" />
      <el-table-column align="center" prop="gmtCreate" label="创建时间" width="180" />
      <el-table-column align="center" prop="gmtModified" label="最近修改时间" width="180" />
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
      :page-sizes="[5, 10, 20, 30]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempNotice" label-position="left" label-width="80px"
               style='width: 100%; margin-left: 48px'>
        <el-form-item label="公告标题" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" v-model="tempNotice.title" style="width: 50%;">
          </el-input>
        </el-form-item>

        <el-form-item label="公告内容" v-show="dialogStatus !== 'remove'" required>
          <el-input  type="textarea" :rows="4" v-model="tempNotice.content" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="公告状态" v-show="dialogStatus !== 'remove'" required>
          <template>
            <el-radio-group v-model="tempNotice.status">
              <el-radio :label="'1'">开启</el-radio>
              <el-radio :label="'0'">关闭</el-radio>
            </el-radio-group>
          </template>
        </el-form-item>
        <el-form-item label="发布时间" v-show="dialogStatus !== 'remove'" required>
          <el-date-picker
            v-model="tempNotice.releaseTime"
            type="datetime"
            placeholder="请选择公告发布时间">
          </el-date-picker>
        </el-form-item>

        <el-form-item v-show="dialogStatus === 'remove'" required>
          <span style="font-size: 16px">确定要删除该公告吗？</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="success" v-if="dialogStatus==='create'" @click="createNotice">创 建</el-button>
        <el-button type="primary" v-if="dialogStatus==='update'" @click="updateNotice">修 改</el-button>
        <el-button type="primary" v-if="dialogStatus==='remove'" @click="removeNotice">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "notice",
  data() {
    return {
      // 搜索内容数据
      noticeTitleText: '',
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
        create: '新增公告',
        update: '编辑公告',
        remove: '提示'
      },
      tempNotice: {
        id: '',
        title: '',
        content: '',
        status: '',
        releaseTime: ''
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
        url: "/notice/listNotice",
        method: "get",
        params: this.listQuery
      }).then(data => {
        this.listLoading = false;
        this.list = data.list;
        this.totalCount = data.totalCount;
      })
    },
    reset() {
      if (this.noticeTitleText.trim().length !== 0 || this.statusSelect.trim().length !== 0) {
        this.getList()
      }
      this.noticeTitleText = '';
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
    showCreate() {
      // 显示新增对话框
      this.tempNotice.title = "";
      this.tempNotice.content = "";
      this.tempNotice.status = "1";
      this.tempNotice.releaseTime = "";
      this.dialogStatus = "create"
      this.dialogFormVisible = true
    },
    showUpdate($index) {
      let owner = this.list[$index];
      this.tempNotice.id = owner.id;
      this.tempNotice.title = owner.title;
      this.tempNotice.content = owner.content;
      this.tempNotice.status = owner.status;
      this.tempNotice.releaseTime = owner.releaseTime;
      this.dialogStatus = "update"
      this.dialogFormVisible = true
    },
    showRemove($index) {
      let owner = this.list[$index];
      this.tempNotice.id = owner.id;
      this.dialogStatus = "remove"
      this.dialogFormVisible = true
    },
    statusFormat(notice) {
      return notice.status === '1' ? '开启' : '关闭';
    },
    validate(isCreate) {
      let owner = this.tempNotice
      if (isCreate && owner.title.trim().length === 0) {
        this.$message.warning('请输入公告内容')
        return false
      }
      if (isCreate && owner.content.trim().length === 0) {
        this.$message.warning('请输入公告地点')
        return false
      }
      if (owner.status.trim().length === 0) {
        this.$message.warning('请选择公告状态')
        return false
      }
      if (owner.releaseTime === null || owner.releaseTime === '') {
        this.$message.warning('请选择发布时间')
        return false
      }

      return true
    },
    queryValidate(isCreate) {
      if (isCreate && this.noticeTitleText.trim().length === 0 && this.statusSelect.trim().length === 0) {
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
        url: "/notice/likeNotice",
        method: "get",
        params: {
          "pageNum" : this.listQuery.pageNum,
          "pageRow" : this.listQuery.pageRow,
          "title" : this.noticeTitleText.trim(),
          "status" : this.statusSelect === '-1' ? '' : this.statusSelect.trim(),
        }
      }).then(data => {
        this.listLoading = false;
        this.list = data.list;
        this.totalCount = data.totalCount;
      })
    },
    createNotice() {
      if (!this.validate(true)) return
      // 保存新公告
      this.api({
        url: "/notice/addNotice",
        method: "post",
        data: this.tempNotice
      }).then(() => {
        this.$message.success('添加成功！')
        this.getList();
        this.dialogFormVisible = false
      })
    },
    updateNotice() {
      if (!this.validate(false)) return
      // 修改公告
      this.api({
        url: "/notice/updateNotice",
        method: "post",
        data: this.tempNotice
      }).then(() => {
        this.$message.success('修改成功！')
        this.getList();
        this.dialogFormVisible = false
      });
    },
    removeNotice() {
      // 删除公告
      this.api({
        url: "/notice/deleteNotice",
        method: "post",
        params: {
          "id" : this.tempNotice.id
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
