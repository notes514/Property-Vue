<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="房屋编码">
          <el-input prefix-icon="el-icon-search" placeholder="请输入房屋编码" v-model="houseCodeText" clearable
                    style="width: 250px">
          </el-input>
        </el-form-item>
<!--        <el-form-item label="房屋名称">-->
<!--          <el-input prefix-icon="el-icon-search" placeholder="请输入房屋名称" v-model="houseNameText" clearable-->
<!--                    style="width: 250px">-->
<!--          </el-input>-->
<!--        </el-form-item>-->
        <el-form-item label="房屋状态">
          <el-select v-model="typeSelect" placeholder="请选择房屋状态">
            <el-option label="全部" value="-1"></el-option>
            <el-option label="未销售" value="0"></el-option>
            <el-option label="已入住" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="getNameAndTypeList">查询</el-button>
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
      <el-table-column align="center" prop="code" label="门牌号" width="120"></el-table-column>
      <el-table-column align="center" prop="ownerName" label="业主" width="120"/>
      <el-table-column align="center" prop="area" label="面积" width="120"/>
      <el-table-column align="center" prop="roomNum" label="房间数" width="120"/>
      <el-table-column align="center" prop="floor" label="楼层" width="120"/>
      <el-table-column align="center" prop="telephone" label="联系方式" width="120"/>
      <el-table-column align="center" prop="status" label="房屋状态" :formatter="typeFormat" width="120"/>
      <el-table-column align="center" prop="refinedDecoration" label="精装修" :formatter="refinedDecorationFormat" width="120"/>
      <el-table-column align="center" prop="liveTime" label="入住时间" width="180"/>
      <el-table-column align="center" prop="gmtCreate" label="创建时间" width="180"/>
      <el-table-column align="center" prop="gmtModified" label="最近修改时间" width="180"/>
      <el-table-column align="center" label="管理" width="180">
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
      <el-form class="small-space" :model="tempHouse" label-position="left" label-width="80px"
               style='width: 100%; margin-left: 48px'>
        <el-form-item label="门牌号" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" v-model="tempHouse.code" style="width: 30%;">
          </el-input>
        </el-form-item>
        <el-form-item label="业主姓名" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" v-model="tempHouse.ownerName" style="width: 30%;">
          </el-input>
        </el-form-item>
        <el-form-item label="面积" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" v-model="tempHouse.area" style="width: 30%;">
          </el-input>
        </el-form-item>
        <el-form-item label="房间数" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" v-model="tempHouse.roomNum" style="width: 30%;">
          </el-input>
        </el-form-item>
        <el-form-item label="楼层" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" v-model="tempHouse.floor" style="width: 30%;">
          </el-input>
        </el-form-item>
        <el-form-item label="房屋状态" v-show="dialogStatus !== 'remove'" required>
          <template>
            <el-radio-group v-model="tempHouse.status">
              <el-radio :label="'0'">待销售</el-radio>
              <el-radio :label="'1'">已入住</el-radio>
            </el-radio-group>
          </template>
        </el-form-item>
        <el-form-item label="精装修" v-show="dialogStatus !== 'remove'" required>
          <template>
            <el-radio-group v-model="tempHouse.refinedDecoration">
              <el-radio :label="'1'">是</el-radio>
              <el-radio :label="'0'">否</el-radio>
            </el-radio-group>
          </template>
        </el-form-item>
        <el-form-item label="入住时间" v-show="dialogStatus !== 'remove'" required>
          <el-date-picker
            v-model="tempHouse.liveTime"
            type="date"
            placeholder="请选择活动开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="联系方式" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" v-model="tempHouse.telephone" style="width: 30%;">
          </el-input>
        </el-form-item>
        <el-form-item label="房屋描述" v-show="dialogStatus !== 'remove'" required>
          <el-input type="textarea" :rows="3" placeholder="请输入房屋描述" v-model="tempHouse.description"
                    style="width: 50%;" maxlength="120" show-word-limit>
          </el-input>
        </el-form-item>

        <el-form-item v-show="dialogStatus === 'remove'" required>
          <span style="font-size: 16px">确定要删除 {{tempHouse.name}} 吗？</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="success" v-show="dialogStatus==='create'" @click="createHouse">创 建</el-button>
        <el-button type="primary" v-show="dialogStatus==='update'" @click="updateHouse">修 改</el-button>
        <el-button type="primary" v-show="dialogStatus==='remove'" @click="removeHouse">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "house",
  data() {
    return {
      // 房屋编码内容
      houseCodeText: '',
      // 搜索内容数据
      houseNameText: '',
      // 类型选择
      typeSelect: '',
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
      },
      dialogStatus: 'create',
      dialogFormVisible: false,
      dialogVisible: false,
      textMap: {
        create: '创建房屋',
        update: '编辑房屋',
        remove: '提示'
      },
      tempHouse: {
        id: '',
        code: '',
        name: '',
        ownerName: '',
        area: '',
        roomNum: '',
        floor: '',
        telephone: '',
        status: '',
        refinedDecoration: '',
        liveTime: '',
        description: ''
      },
      typeList: [
        {
          typeValue: '0',
          typeLabel: '楼梯房'
        }, {
          typeValue: '1',
          typeLabel: '电梯房'
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
        url: "/house/listHouse",
        method: "get",
        params: this.listQuery
      }).then(data => {
        this.listLoading = false;
        this.list = data.list;
        this.totalCount = data.totalCount;
      })
    },
    reset() {
      if (this.houseNameText.trim().length !== 0 || this.typeSelect.trim().length !== 0) {
        this.getList()
      }
      this.houseNameText = '';
      this.typeSelect = '';

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
      this.tempHouse.code = "";
      this.tempHouse.name = "";
      this.tempHouse.ownerName = "";
      this.tempHouse.area = "";
      this.tempHouse.roomNum = "";
      this.tempHouse.floor = "";
      this.tempHouse.telephone = "";
      this.tempHouse.status = "";
      this.tempHouse.refinedDecoration = "";
      this.tempHouse.description = "";
      this.tempHouse.liveTime = "";
      this.dialogStatus = "create"
      this.dialogFormVisible = true
    },
    showUpdate($index) {
      let house = this.list[$index];
      this.tempHouse.code = house.code;
      this.tempHouse.name = house.name;
      this.tempHouse.ownerName = house.ownerName;
      this.tempHouse.area = house.area;
      this.tempHouse.roomNum = house.roomNum;
      this.tempHouse.floor = house.floor;
      this.tempHouse.telephone = house.telephone;
      this.tempHouse.status = house.status;
      this.tempHouse.refinedDecoration = house.refinedDecoration;
      this.tempHouse.description = house.description;
      this.tempHouse.liveTime = house.liveTime;
      this.dialogStatus = "update"
      this.dialogFormVisible = true
    },
    showRemove($index) {
      let house = this.list[$index];
      this.tempHouse.id = house.id;
      this.tempHouse.name = house.name;
      this.dialogStatus = "remove"
      this.dialogFormVisible = true
    },
    /**
     * 显示类型
     *
     * @param house 房屋实体
     * @returns {string} 字符串
     */
    typeFormat(house) {
      return house.status === '0' ? '楼梯房' : '电梯房';
    },
    refinedDecorationFormat(house) {
      return house.refinedDecoration === '0' ? '否' : '是';
    },
    validate(isCreate) {
      let house = this.tempHouse
      if (isCreate && house.code.trim().length === 0) {
        this.$message.warning('请填写房屋编码')
        return false
      }
      if (isCreate && house.name.trim().length === 0) {
        this.$message.warning('请填写房屋名称')
        return false
      }
      if (isCreate && house.ownerName.length === 0) {
        this.$message.warning('请填写业主姓名')
        return false
      }
      if (isCreate && house.area.length === 0) {
        this.$message.warning('请填写房屋面积')
        return false
      }
      if (isCreate && house.roomNum.length === 0) {
        this.$message.warning('请填写房间数')
        return false
      }
      if (isCreate && house.floor.length === 0) {
        this.$message.warning('请填写楼层')
        return false
      }
      if (isCreate && house.telephone.length === 0) {
        this.$message.warning('请填写联系方式')
        return false
      }
      if (isCreate && house.layer.length === 0) {
        this.$message.warning('请填写房屋总用户数')
        return false
      }
      if (house.status.trim().length === 0) {
        this.$message.warning('请选择房屋状态')
        return false
      }
      if (house.refinedDecoration.trim().length === 0) {
        this.$message.warning('请选择精装修')
        return false
      }
      if (house.description.trim().length === 0) {
        this.$message.warning('请输入房屋描述')
        return false
      }
      return true
    },
    queryValidate(isCreate) {
      if (isCreate && this.houseNameText.trim().length === 0 && this.typeSelect.trim().length === 0) {
        this.$message.warning('请输入或选择您的查询条件！')
        return false
      }
      return true
    },
    /**
     * 根据房屋名称和类型进行模糊搜索
     */
    getNameAndTypeList() {
      if (!this.queryValidate(true)) return
      this.listLoading = true;
      this.api({
        url: "/house/likeHouse",
        method: "get",
        params: {
          "pageNum" : this.listQuery.pageNum,
          "pageRow" : this.listQuery.pageRow,
          "name" : this.houseNameText.trim(),
          "type" : this.typeSelect === '-1' ? '' : this.typeSelect.trim(),
        }
      }).then(data => {
        this.listLoading = false;
        this.list = data.list;
        this.totalCount = data.totalCount;
      })
    },
    createHouse() {
      if (!this.validate(true)) return
      // 保存新房屋
      this.api({
        url: "/house/addHouse",
        method: "post",
        data: this.tempHouse
      }).then(() => {
        this.$message.success('添加成功！')
        this.getList();
        this.dialogFormVisible = false
      })
    },
    updateHouse() {
      if (!this.validate(false)) return
      // 修改房屋
      this.api({
        url: "/house/updateHouse",
        method: "post",
        data: this.tempHouse
      }).then(() => {
        this.$message.success('修改成功！')
        this.getList();
        this.dialogFormVisible = false
      });
    },
    removeHouse() {
      // 删除房屋
      this.api({
        url: "/house/deleteHouse",
        method: "post",
        params: {
          "id" : this.tempHouse.id
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
