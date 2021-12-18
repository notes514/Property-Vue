<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form>
        <el-form-item>
          <el-button type="primary" icon="plus" @click="showCreate" v-permission="'role:add'">添加
          </el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="list" v-loading="listLoading" border fit
              highlight-current-row>
      <el-table-column align="center" label="序号" width="80">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"> </span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="buildingName" label="楼栋" width="200"></el-table-column>
      <el-table-column align="center" prop="totalHouseholds" label="总户数" width="200"/>
      <el-table-column align="center" prop="type" label="类型" width="200"/>
      <el-table-column align="center" prop="description" label="描述" style="width: 40px"/>
      <el-table-column align="center" prop="createTime" label="创建时间" width="200"/>
      <el-table-column align="center" prop="updateTime" label="最近修改时间" width="200"/>
      <el-table-column align="center" label="管理" width="200">
        <template slot-scope="scope">
          <el-button type="primary" icon="edit" @click="showUpdate(scope.$index)" v-permission="'role:update'">修改
          </el-button>
          <el-button type="primary" icon="edit" @click="showRemove(scope.$index)" v-permission="'role:delete'">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="listQuery.pageNum"
      :page-size="listQuery.pageRow"
      :total="totalCount"
      :page-sizes="[3, 6, 9, 12]"
      layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempBuilding" label-position="left" label-width="80px"
               style='width: 100%; margin-left: 48px'>
        <el-form-item label="楼栋名称" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" v-model="tempBuilding.buildingName" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="总户数" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" v-model="tempBuilding.totalHouseholds" style="width: 20%;">
          </el-input>
        </el-form-item>
        <el-form-item label="楼栋类型" v-show="dialogStatus !== 'remove'" required>
          <el-select v-model="tempBuilding.type" placeholder="请选择" style="width: 20%;">
            <el-option
              v-for="item in typeList"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="楼栋描述" v-show="dialogStatus !== 'remove'" required>
          <el-input type="textarea" placeholder="请输入楼栋描述" v-model="tempBuilding.description" style="width: 50%;"
                    maxlength="120" show-word-limit>
          </el-input>
        </el-form-item>

        <el-form-item v-show="dialogStatus === 'remove'" required>
          <span>确定要删除该楼栋吗？</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="success" v-if="dialogStatus==='create'" @click="createBuilding">创 建</el-button>
        <el-button type="primary" v-if="dialogStatus==='update'" v-else @click="updateBuilding">修 改</el-button>
        <el-button type="primary" v-if="dialogStatus==='remove'" @click="removeBuilding">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "building",
  data() {
    return {
      totalCount: 0, //分页组件--数据总条数
      list: [],//表格的数据
      listLoading: false,//数据加载等待动画
      listQuery: {
        pageNum: 1,//页码
        pageRow: 3,//每页条数
        name: ''
      },
      dialogStatus: 'create',
      dialogFormVisible: false,
      dialogVisible: false,
      textMap: {
        create: '创建楼栋',
        update: '编辑楼栋',
        remove: '提示'
      },
      tempBuilding: {
        id: '',
        buildingName: '',
        totalHouseholds: '',
        type: '',
        description: '',
        createTime: '',
        updateTime: ''
      },
      typeList: [
        {
          value: '0',
          label: '电梯房'
        }, {
          value: '1',
          label: '楼梯房'
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
        url: "/building/listBuilding",
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
      this.tempBuilding.buildingName = "";
      this.tempBuilding.totalHouseholds = "";
      this.tempBuilding.type = "";
      this.tempBuilding.description = "";
      this.dialogStatus = "create"
      this.dialogFormVisible = true
    },
    showUpdate($index) {
      let building = this.list[$index];
      this.tempBuilding.id = building.id;
      this.tempBuilding.buildingName = building.buildingName;
      this.tempBuilding.totalHouseholds = building.totalHouseholds;
      this.tempBuilding.type = building.type;
      this.tempBuilding.description = building.description;
      this.dialogStatus = "update"
      this.dialogFormVisible = true
    },
    showRemove($index) {
      let building = this.list[$index];
      this.tempBuilding.id = building.id;
      this.dialogStatus = "remove"
      this.dialogFormVisible = true
    },
    validate(isCreate) {
      let building = this.tempBuilding
      if (isCreate && building.buildingName.trim().length === 0) {
        this.$message.warning('请填写楼栋名称')
        return false
      }
      if (isCreate && building.totalHouseholds.length === 0) {
        this.$message.warning('请填写楼栋总用户数')
        return false
      }
      console.log(building.type.trim())
      if (building.type.trim().length === 0) {
        this.$message.warning('请选择楼栋类型')
        return false
      }
      if (building.description.trim().length === 0) {
        this.$message.warning('请输入楼栋描述')
        return false
      }
      return true
    },
    createBuilding() {
      if (!this.validate(true)) return
      // 保存新楼栋
      this.api({
        url: "/building/addBuilding",
        method: "post",
        data: this.tempBuilding
      }).then(() => {
        this.$message.success('添加成功！')
        this.getList();
        this.dialogFormVisible = false
      })
    },
    updateBuilding() {
      if (!this.validate(false)) return
      // 修改楼栋
      this.api({
        url: "/building/updateBuilding",
        method: "post",
        data: this.tempBuilding
      }).then(() => {
        this.$message.success('修改成功！')
        this.getList();
        this.dialogFormVisible = false
      })
    },
    removeBuilding() {
      // 删除楼栋
      this.api({
        url: "/building/deleteBuilding",
        method: "post",
        data: this.tempBuilding
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

</style>
