<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!--      <el-form-item label="编号" prop="elderlyFamilyId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.elderlyFamilyId"-->
      <!--          placeholder="请输入编号"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="家属" prop="userInfoId">
        <el-select
          v-model="queryParams.userInfoId"
          filterable
          remote
          reserve-keyword
          placeholder="请输入手机号码"
          :remote-method="remoteElderlyFamilyUserInfoMethod"
          :loading="elderlyFamilyUserInfoLoading">
          <el-option
            v-for="item in elderlyFamilyUserInfoList"
            :key="item.userInfoId"
            :label="item.userInfoName"
            :value="item.userInfoId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="长者" prop="userInfoElderlyId">
        <el-select
          v-model="queryParams.userInfoElderlyId"
          filterable
          remote
          reserve-keyword
          placeholder="请输入手机号码"
          :remote-method="remoteElderlyUserInfoMethod"
          :loading="elderlyUserInfoLoading">
          <el-option
            v-for="item in elderlyUserInfoList"
            :key="item.userInfoId"
            :label="item.userInfoName"
            :value="item.userInfoId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="关系" prop="relationshipType">
        <el-select v-model="queryParams.relationshipType" placeholder="请选择关系" clearable>
          <el-option
            v-for="dict in dict.type.yl_relationship_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['zhyl:elderlyFamily:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['zhyl:elderlyFamily:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['zhyl:elderlyFamily:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['zhyl:elderlyFamily:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="elderlyFamilyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="编号" align="center" v-if="columns[0].visible" prop="elderlyFamilyId"/>
      <el-table-column label="长者" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                       prop="userInfoElderlyName"/>
      <el-table-column label="家属" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible"
                       prop="userInfoName"/>
      <el-table-column label="关系" align="center" v-if="columns[3].visible" prop="relationshipType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_relationship_type" :value="scope.row.relationshipType"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible"
                       prop="remark"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['zhyl:elderlyFamily:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhyl:elderlyFamily:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改家属信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="长者" prop="userInfoElderlyId">
          <el-select
            v-model="form.userInfoElderlyId"
            filterable
            remote
            reserve-keyword
            placeholder="请输入手机号码"
            :remote-method="remoteElderlyUserInfoMethod"
            :loading="elderlyUserInfoLoading">
            <el-option
              v-for="item in elderlyUserInfoList"
              :key="item.userInfoId"
              :label="item.userInfoName"
              :value="item.userInfoId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="家属" prop="userInfoId">
          <el-select
            v-model="form.userInfoId"
            filterable
            remote
            reserve-keyword
            placeholder="请输入手机号码"
            :remote-method="remoteElderlyFamilyUserInfoMethod"
            :loading="elderlyFamilyUserInfoLoading">
            <el-option
              v-for="item in elderlyFamilyUserInfoList"
              :key="item.userInfoId"
              :label="item.userInfoName"
              :value="item.userInfoId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="关系" prop="relationshipType">
          <el-select v-model="form.relationshipType" placeholder="请选择关系">
            <el-option
              v-for="dict in dict.type.yl_relationship_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listElderlyFamily,
  getElderlyFamily,
  delElderlyFamily,
  addElderlyFamily,
  updateElderlyFamily
} from "@/api/zhyl/elderlyFamily";
import {listUserInfo} from "@/api/zhyl/userInfo";

export default {
  name: "ElderlyFamily",
  dicts: ['yl_relationship_type'],
  data() {
    return {
      // 长者家属查询参数
      queryParamsElderlyFamily: {
        pageNum: 1,
        pageSize: 10,
        contactPhone: '',
        userInfoId: '',
        userInfoRole: 'elderly_family',
      },
      // 长者家属信息表格数据
      elderlyFamilyUserInfoList: [],
      elderlyFamilyUserInfoLoading: true,
      // 长者查询参数
      queryParamsElderly: {
        pageNum: 1,
        pageSize: 10,
        contactPhone: '',
        userInfoId: '',
        userInfoRole: 'elderly',
      },
      // 长者信息表格数据
      elderlyUserInfoList: [],
      elderlyUserInfoLoading: true,
      //表格展示列
      columns: [
        {key: 0, label: '编号', visible: false},
        {key: 1, label: '长者', visible: true},
        {key: 2, label: '家属', visible: true},
        {key: 3, label: '关系', visible: true},
        {key: 4, label: '备注', visible: true},
      ],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 家属信息表格数据
      elderlyFamilyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        elderlyFamilyId: null,
        userInfoId: null,
        userInfoElderlyId: null,
        relationshipType: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userInfoId: [
          {required: true, message: "家属不能为空", trigger: "blur"}
        ],
        userInfoElderlyId: [
          {required: true, message: "长者不能为空", trigger: "blur"}
        ],
        relationshipType: [
          {required: true, message: "关系不能为空", trigger: "change"}
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getElderlyUserInfoList()
    this.getElderlyFamilyUserInfoList()
  },
  methods: {
    remoteElderlyFamilyUserInfoMethod(query) {
      if (query !== '') {
        this.elderlyFamilyUserInfoLoading = true
        this.queryParamsElderlyFamily.contactPhone = query
        setTimeout(() => {
          this.getElderlyFamilyUserInfoList()
        }, 200)
      } else {
        this.elderlyFamilyUserInfoList = []
      }
    },
    getElderlyFamilyUserInfoList() {
      if (this.form.userInfoId !== null && this.form.userInfoId !== '') {
        this.queryParamsElderlyFamily.userInfoId = this.form.userInfoId
      }
      if (this.queryParamsElderlyFamily.contactPhone !== '') {
        this.queryParamsElderlyFamily.userInfoId = null
      }
      listUserInfo(this.queryParamsElderlyFamily).then(response => {
        this.elderlyFamilyUserInfoList = response.rows
        this.elderlyFamilyUserInfoLoading = false
      })
    },
    remoteElderlyUserInfoMethod(query) {
      if (query !== '') {
        this.elderlyUserInfoLoading = true
        this.queryParamsElderly.contactPhone = query
        setTimeout(() => {
          this.getElderlyUserInfoList()
        }, 200)
      } else {
        this.elderlyUserInfoList = []
      }
    },

    getElderlyUserInfoList() {
      if (this.form.userInfoId !== null && this.form.userInfoId !== '') {
        this.queryParamsElderly.userInfoId = this.form.userInfoId
      }
      if (this.queryParamsElderly.contactPhone !== '') {
        this.queryParamsElderly.userInfoId = null
      }
      listUserInfo(this.queryParamsElderly).then(response => {
        this.elderlyUserInfoList = response.rows
        this.elderlyUserInfoLoading = false
      })
    },
    /** 查询家属信息列表 */
    getList() {
      this.loading = true;
      listElderlyFamily(this.queryParams).then(response => {
        this.elderlyFamilyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        elderlyFamilyId: null,
        userInfoId: null,
        userInfoElderlyId: null,
        relationshipType: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.elderlyFamilyId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加家属信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const elderlyFamilyId = row.elderlyFamilyId || this.ids
      getElderlyFamily(elderlyFamilyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改家属信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.elderlyFamilyId != null) {
            updateElderlyFamily(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addElderlyFamily(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const elderlyFamilyIds = row.elderlyFamilyId || this.ids;
      this.$modal.confirm('是否确认删除家属信息编号为"' + elderlyFamilyIds + '"的数据项？').then(function () {
        return delElderlyFamily(elderlyFamilyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhyl/elderlyFamily/export', {
        ...this.queryParams
      }, `elderlyFamily_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
