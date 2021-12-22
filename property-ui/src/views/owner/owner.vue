<template>
  <div class="app-container">
    <div class="filter-container">
      <el-form :inline="true" class="demo-form-inline">
        <el-form-item label="业主姓名">
          <el-input prefix-icon="el-icon-search" placeholder="请输入楼栋名称" v-model="ownerNameText" clearable
                    style="width: 250px">
          </el-input>
        </el-form-item>
        <el-form-item label="业主类型">
          <el-select v-model="typeSelect" placeholder="请选择楼型">
            <el-option label="全部" value="-1"></el-option>
            <el-option label="房东" value="0"></el-option>
            <el-option label="租客" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" @click="queryOwnerNameAndTypeList">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-circle-plus-outline" @click="showCreate" v-permission="'role:add'"
                     plain>添加业主</el-button>
        </el-form-item>
      </el-form>
    </div>
    <el-table :data="list" v-loading="listLoading" border fit highlight-current-row>
      <el-table-column align="center" label="序号" width="50">
        <template slot-scope="scope">
          <span v-text="getIndex(scope.$index)"> </span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="name" label="姓名" width="120"></el-table-column>
      <el-table-column align="center" prop="sex" label="性别" :formatter="sexFormat" width="80"/>
      <el-table-column align="center" prop="idCard" label="身份证号" width="200"/>
      <el-table-column align="center" prop="type" label="业主类型" :formatter="ownerTypeFormat" width="100"/>
      <el-table-column align="center" prop="profession" label="职业" :formatter="professionalFormat" width="120"/>
      <el-table-column align="center" prop="birthday" label="出身日期" width="200"/>
      <el-table-column align="center" prop="telephone" label="联系方式" width="200"/>
      <el-table-column align="center" prop="gmtCreate" label="创建时间" width="200"/>
      <el-table-column align="center" prop="gmtModified" label="最近修改时间" width="200"/>
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
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="listQuery.pageNum"
      :page-size="listQuery.pageRow"
      :total="totalCount"
      :page-sizes="[10, 20, 30, 50]"
      layout="total, sizes, prev, pager, next, jumper"
      style="margin-top: 16px;">
    </el-pagination>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="small-space" :model="tempOwner" label-position="left" label-width="80px"
               style='width: 100%; margin-left: 48px'>
        <el-form-item label="业主姓名" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" v-model="tempOwner.name" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="身份证号" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" v-model="tempOwner.idCard" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item label="性别" v-show="dialogStatus !== 'remove'" required>
          <el-select v-model="tempOwner.sex" placeholder="请选择性别" style="width: 30%;">
            <el-option
              v-for="sex in sexList"
              :key="sex.sexValue"
              :label="sex.sexLabel"
              :value="sex.sexValue">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="住户类型" v-show="dialogStatus !== 'remove'" required>
          <el-select v-model="tempOwner.type" placeholder="请选择住户类型" style="width: 30%;">
            <el-option
              v-for="type in typeList"
              :key="type.typeValue"
              :label="type.typeLabel"
              :value="type.typeValue">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="职业" v-show="dialogStatus !== 'remove'" required>
          <el-select v-model="tempOwner.profession" placeholder="请选择职业" style="width: 30%;">
            <el-option
              v-for="profession in professionList"
              :key="profession.professionValue"
              :label="profession.professionLabel"
              :value="profession.professionValue">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="出身日期" v-show="dialogStatus !== 'remove'" required>
          <el-date-picker
            v-model="tempOwner.birthday"
            type="date"
            placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="联系方式" v-show="dialogStatus !== 'remove'" required>
          <el-input type="text" v-model="tempOwner.telephone" style="width: 50%;">
          </el-input>
        </el-form-item>
        <el-form-item v-show="dialogStatus === 'remove'" required>
          <span style="font-size: 16px">确定要删除业主 {{tempOwner.name}} 吗？</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="success" v-if="dialogStatus==='create'" @click="createOwner">创 建</el-button>
        <el-button type="primary" v-if="dialogStatus==='update'" @click="updateOwner">修 改</el-button>
        <el-button type="primary" v-if="dialogStatus==='remove'" @click="removeOwner">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {isPoneAvailable, identityIDCard} from "../../utils/validation";

export default {
  name: "owner",
  data() {
    return {
      // 搜索内容数据
      ownerNameText: '',
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
        name: ''
      },
      dialogStatus: 'create',
      dialogFormVisible: false,
      dialogVisible: false,
      textMap: {
        create: '新增业主',
        update: '编辑业主',
        remove: '提示'
      },
      tempOwner: {
        id: '',
        name: '',
        idCard: '',
        sex: '',
        type: '',
        profession: '',
        birthday: '',
        telephone: '',
        picture: '',
        remark: ''
      },
      sexList: [
        {
          sexValue: '0',
          sexLabel: '男'
        }, {
          sexValue: '1',
          sexLabel: '女'
        }
      ],
      typeList: [
        {
          typeValue: '0',
          typeLabel: '房主'
        }, {
          typeValue: '1',
          typeLabel: '租客'
        }
      ],
      professionList: [
        {
          professionValue: '0',
          professionLabel: '公务员'
        }, {
          professionValue: '1',
          professionLabel: '教师'
        }, {
          professionValue: '2',
          professionLabel: '医生'
        }, {
          professionValue: '3',
          professionLabel: '护士'
        }, {
          professionValue: '4',
          professionLabel: '司机'
        }, {
          professionValue: '5',
          professionLabel: '军人'
        }, {
          professionValue: '6',
          professionLabel: '律师'
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
        url: "/owner/listOwner",
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
      this.tempOwner.name = "";
      this.tempOwner.sex = "";
      this.tempOwner.idCard = "";
      this.tempOwner.type = "";
      this.tempOwner.profession = "";
      this.tempOwner.birthday = "";
      this.tempOwner.telephone = "";
      this.tempOwner.picture = "";
      this.tempOwner.remark = "";
      this.dialogStatus = "create"
      this.dialogFormVisible = true
    },
    showUpdate($index) {
      let owner = this.list[$index];
      this.tempOwner.id = owner.id;
      this.tempOwner.name = owner.name;
      this.tempOwner.sex = owner.sex;
      this.tempOwner.idCard = owner.idCard;
      this.tempOwner.type = owner.type;
      this.tempOwner.profession = owner.profession;
      this.tempOwner.birthday = owner.birthday;
      this.tempOwner.telephone = owner.telephone;
      this.dialogStatus = "update"
      this.dialogFormVisible = true
    },
    showRemove($index) {
      let owner = this.list[$index];
      this.tempOwner.id = owner.id;
      this.tempOwner.name = owner.name;
      this.dialogStatus = "remove"
      this.dialogFormVisible = true
    },
    sexFormat(owner) {
      return owner.sex === '0' ? '男' : '女';
    },
    ownerTypeFormat(owner) {
      return owner.type === '0' ? '房主' : '租客';
    },
    professionalFormat(owner) {
      const index = owner.profession;
      return this.professionList[index].professionLabel;
    },
    validate(isCreate) {
      let owner = this.tempOwner
      if (isCreate && owner.name.trim().length === 0) {
        this.$message.warning('请输入业主姓名')
        return false
      }
      if (isCreate && owner.idCard.trim().length === 0) {
        this.$message.warning('请输入业主身份证号')
        return false
      }
      if (!identityIDCard(owner.idCard)) {
        this.$message.error('输入的身份证号码格式不正确')
        return false
      }
      if (owner.sex.trim().length === 0) {
        this.$message.warning('请选择性别')
        return false
      }
      if (owner.type.trim().length === 0) {
        this.$message.warning('请选择住户类型')
        return false
      }
      if (owner.birthday === null || owner.birthday === '') {
        this.$message.warning('请选择业主出生日期')
        return false
      }
      if (owner.telephone.trim().length === 0) {
        this.$message.warning('请输入电话号码')
        return false
      }
      if (!isPoneAvailable(owner.telephone)) {
        this.$message.error('输入的电话号码格式不正确')
        return false
      }

      return true
    },
    queryValidate(isCreate) {
      if (isCreate && this.ownerNameText.trim().length === 0 && this.typeSelect.trim().length === 0) {
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
        url: "/owner/likeOwner",
        method: "get",
        params: {
          "pageNum" : this.listQuery.pageNum,
          "pageRow" : this.listQuery.pageRow,
          "name" : this.ownerNameText.trim(),
          "type" : this.typeSelect === '-1' ? '' : this.typeSelect.trim(),
        }
      }).then(data => {
        this.listLoading = false;
        this.list = data.list;
        this.totalCount = data.totalCount;
      })
    },
    createOwner() {
      if (!this.validate(true)) return
      // 保存新业主
      this.api({
        url: "/owner/addOwner",
        method: "post",
        data: this.tempOwner
      }).then(() => {
        this.$message.success('添加成功！')
        this.getList();
        this.dialogFormVisible = false
      })
    },
    updateOwner() {
      if (!this.validate(false)) return
      // 修改业主
      this.api({
        url: "/owner/updateOwner",
        method: "post",
        data: this.tempOwner
      }).then(() => {
        this.$message.success('修改成功！')
        this.getList();
        this.dialogFormVisible = false
      });
    },
    removeOwner() {
      // 删除业主
      this.api({
        url: "/owner/deleteOwner",
        method: "post",
        params: {
          "id" : this.tempOwner.id
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

</style>
