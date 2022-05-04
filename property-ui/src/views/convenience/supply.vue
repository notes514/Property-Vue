<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="供求标题">
          <el-input prefix-icon="el-icon-search" placeholder="请输入楼栋名称" v-model="supplyTitleText" clearable
                    style="width: 250px">
          </el-input>
        </el-form-item>
        <el-form-item label="供求状态">
          <el-select v-model="statusSelect" placeholder="请选择楼型">
            <el-option label="全部" value="-1"></el-option>
            <el-option label="未受理" value="0"></el-option>
            <el-option label="已受理" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="getTitleAndStatusList" plain>查询</el-button>
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
      <el-table-column align="center" prop="title" label="供求标题" width="200"></el-table-column>
      <el-table-column align="center" prop="content" label="供求标题" width="300"></el-table-column>
      <el-table-column align="center" prop="address" label="供求地点" width="120"/>
      <el-table-column align="center" prop="ownerName" label="供求人员" width="120"></el-table-column>
      <el-table-column align="center" prop="status" label="供求状态" :formatter="statusFormat" width="110"/>
      <el-table-column align="center" prop="startTime" label="开始时间" width="160"/>
      <el-table-column align="center" prop="endTime" label="结束时间" width="160"/>
      <el-table-column align="center" prop="gmtCreate" label="创建时间" width="160"/>
      <el-table-column align="center" prop="gmtModified" label="最近修改时间" width="160"/>
      <el-table-column align="center" label="管理" width="228">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="edit" @click="showUpdate(scope.$index)"
                     v-permission="'role:update'">修改
          </el-button>
          <el-button type="danger" size="mini" icon="edit" @click="showRemove(scope.$index)"
                     v-permission="'role:delete'">删除
          </el-button>
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
      <el-form class="small-space" :model="tempSupply" label-position="left" label-width="80px"
               style='width: 100%; margin-left: 48px'>
        <el-form-item label="供求标题" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" :disabled="true" v-model="tempSupply.title" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="供求内容" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" :disabled="true" v-model="tempSupply.content" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="供求地址" v-show="dialogStatus !== 'remove'" required>
          <el-input type="textarea" :disabled="true" :rows="4" v-model="tempSupply.address" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="供求人员" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" :disabled="true" v-model="tempSupply.ownerName" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="供求状态" v-show="dialogStatus !== 'remove'" required>
          <template>
            <el-radio-group v-model="tempSupply.status">
              <el-radio :label="'0'">未开始</el-radio>
              <el-radio :label="'1'">进行中</el-radio>
              <el-radio :label="'2'">已结束</el-radio>
            </el-radio-group>
          </template>
        </el-form-item>
        <el-form-item label="结束时间" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" :disabled="true" v-model="tempSupply.endTime" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="结束时间" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" :disabled="true" v-model="tempSupply.endTime" style="width: 50%;">
          </el-input>
        </el-form-item>

        <el-form-item v-show="dialogStatus === 'remove'" required>
          <span style="font-size: 16px">确定要删除该供求吗？</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" v-show="dialogStatus==='update'" @click="updateSupply">修 改</el-button>
        <el-button type="primary" v-show="dialogStatus==='remove'" @click="removeSupply">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "Supply",
  data() {
    return {
      // 搜索供求标题
      supplyTitleText: '',
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
        update: '编辑供求',
        remove: '提示'
      },
      tempSupply: {
        id: '',
        ownerName: '',
        title: '',
        content: '',
        address: '',
        status: '',
        startTime: '',
        endTime: ''
      },
      statusList: [
        '未开始',
        '进行中',
        '已结束'
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
        url: "/supply/listSupply",
        method: "get",
        params: this.listQuery
      }).then(data => {
        this.listLoading = false;
        this.list = data.list;
        this.totalCount = data.totalCount;
      })
    },
    reset() {
      if (this.supplyTitleText.trim().length !== 0 || this.statusSelect.trim().length !== 0) {
        this.getList()
      }
      this.supplyTitleText = '';
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
      let supply = this.list[$index];
      this.tempSupply.id = supply.id;
      this.tempSupply.ownerName = supply.ownerName;
      this.tempSupply.title = supply.title;
      this.tempSupply.content = supply.content;
      this.tempSupply.address = supply.address;
      this.tempSupply.status = supply.status;
      this.tempSupply.startTime = supply.startTime;
      this.tempSupply.endTime = supply.endTime;
      this.dialogStatus = "update"
      this.dialogFormVisible = true
    },
    showRemove($index) {
      let supply = this.list[$index];
      this.tempSupply.id = supply.id;
      this.dialogStatus = "remove"
      this.dialogFormVisible = true
    },
    statusFormat(notice) {
      return this.statusList[notice.status];
    },
    validate() {
      if (this.tempSupply.status.trim().length === 0) {
        this.$message.warning('请选择供求状态')
        return false
      }

      return true
    },
    queryValidate(isCreate) {
      if (isCreate && this.supplyTitleText.trim().length === 0 && this.statusSelect.trim().length === 0) {
        this.$message.warning('请输入或选择您的查询条件！')
        return false
      }
      return true
    },
    /**
     * 根据供求类型和受理状态进行模糊搜索
     */
    getTitleAndStatusList() {
      if (!this.queryValidate(true)) return
      this.listLoading = true;
      this.api({
        url: "/supply/likeSupply",
        method: "get",
        params: {
          "pageNum": this.listQuery.pageNum,
          "pageRow": this.listQuery.pageRow,
          "title": this.supplyTitleText.trim(),
          "status": this.statusSelect === '-1' ? '' : this.statusSelect.trim(),
        }
      }).then(data => {
        this.listLoading = false;
        this.list = data.list;
        this.totalCount = data.totalCount;
      })
    },
    updateSupply() {
      if (!this.validate(false)) return
      // 修改供求
      this.api({
        url: "/supply/updateSupply",
        method: "post",
        data: this.tempSupply
      }).then(() => {
        this.$message.success('修改成功！')
        this.getList();
        this.dialogFormVisible = false
      });
    },
    removeSupply() {
      // 删除供求
      this.api({
        url: "/supply/deleteSupply",
        method: "post",
        params: {
          "id": this.tempSupply.id
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
