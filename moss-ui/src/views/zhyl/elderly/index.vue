<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="长者" prop="userInfoId">
        <el-input
          v-model="queryParams.userInfoId"
          placeholder="请输入长者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="失能情况" prop="disabilityStatus">
        <el-select v-model="queryParams.disabilityStatus" placeholder="请选择失能情况" clearable>
          <el-option
            v-for="dict in dict.type.yl_disability_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="居住情况" prop="livingCondition">
        <el-select v-model="queryParams.livingCondition" placeholder="请选择居住情况" clearable>
          <el-option
            v-for="dict in dict.type.yl_living_condition"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="登记时间">
        <el-date-picker
          v-model="daterangeRegistrationTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="残疾情况" prop="disabilityCondition">
        <el-input
          v-model="queryParams.disabilityCondition"
          placeholder="请输入残疾情况"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间">
        <el-date-picker
          v-model="daterangeCreateTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="修改时间">
        <el-date-picker
          v-model="daterangeUpdateTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
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
          v-hasPermi="['zhyl:elderly:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['zhyl:elderly:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['zhyl:elderly:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['zhyl:elderly:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="elderlyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" v-if="columns[0].visible" prop="elderlyId" />
        <el-table-column label="长者" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible" prop="userInfoId" />
        <el-table-column label="失能情况" align="center" v-if="columns[2].visible" prop="disabilityStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_disability_status" :value="scope.row.disabilityStatus"/>
        </template>
      </el-table-column>
        <el-table-column label="居住情况" align="center" v-if="columns[3].visible" prop="livingCondition">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_living_condition" :value="scope.row.livingCondition"/>
        </template>
      </el-table-column>
        <el-table-column label="登记时间" align="center" v-if="columns[4].visible" prop="registrationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.registrationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
        <el-table-column label="残疾情况" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible" prop="disabilityCondition" />
        <el-table-column label="创建人" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible" prop="createBy" />
        <el-table-column label="创建时间" align="center" v-if="columns[7].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
        <el-table-column label="修改时间" align="center" v-if="columns[8].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['zhyl:elderly:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhyl:elderly:remove']"
          >删除</el-button>
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

    <!-- 添加或修改长者信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="长者" prop="userInfoId">
          <el-input v-model="form.userInfoId" placeholder="请输入长者" />
        </el-form-item>
        <el-form-item label="失能情况" prop="disabilityStatus">
          <el-select v-model="form.disabilityStatus" placeholder="请选择失能情况">
            <el-option
              v-for="dict in dict.type.yl_disability_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="居住情况" prop="livingCondition">
          <el-select v-model="form.livingCondition" placeholder="请选择居住情况">
            <el-option
              v-for="dict in dict.type.yl_living_condition"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="登记时间" prop="registrationTime">
          <el-date-picker clearable
            v-model="form.registrationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择登记时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="残疾情况" prop="disabilityCondition">
          <el-input v-model="form.disabilityCondition" placeholder="请输入残疾情况" />
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
import { listElderly, getElderly, delElderly, addElderly, updateElderly } from "@/api/zhyl/elderly";

export default {
  name: "Elderly",
  dicts: ['yl_disability_status', 'yl_living_condition'],
  data() {
    return {
      //表格展示列
      columns: [
        { key: 0, label: '编号', visible: true },
          { key: 1, label: '长者', visible: true },
          { key: 2, label: '失能情况', visible: true },
          { key: 3, label: '居住情况', visible: true },
          { key: 4, label: '登记时间', visible: true },
          { key: 5, label: '残疾情况', visible: true },
          { key: 6, label: '创建人', visible: true },
          { key: 7, label: '创建时间', visible: true },
          { key: 8, label: '修改时间', visible: true },
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
      // 长者信息表格数据
      elderlyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 修改时间时间范围
      daterangeRegistrationTime: [],
      // 修改时间时间范围
      daterangeCreateTime: [],
      // 修改时间时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userInfoId: null,
        disabilityStatus: null,
        livingCondition: null,
        registrationTime: null,
        disabilityCondition: null,
        createBy: null,
        createTime: null,
        updateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userInfoId: [
          { required: true, message: "长者不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询长者信息列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeRegistrationTime && '' != this.daterangeRegistrationTime) {
        this.queryParams.params["beginRegistrationTime"] = this.daterangeRegistrationTime[0];
        this.queryParams.params["endRegistrationTime"] = this.daterangeRegistrationTime[1];
      }
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params["beginUpdateTime"] = this.daterangeUpdateTime[0];
        this.queryParams.params["endUpdateTime"] = this.daterangeUpdateTime[1];
      }
      listElderly(this.queryParams).then(response => {
        this.elderlyList = response.rows;
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
        elderlyId: null,
        userInfoId: null,
        disabilityStatus: null,
        livingCondition: null,
        registrationTime: null,
        disabilityCondition: null,
        createBy: null,
        createTime: null,
        updateTime: null
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
      this.daterangeRegistrationTime = [];
      this.daterangeCreateTime = [];
      this.daterangeUpdateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.elderlyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加长者信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const elderlyId = row.elderlyId || this.ids
      getElderly(elderlyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改长者信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.elderlyId != null) {
            updateElderly(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addElderly(this.form).then(response => {
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
      const elderlyIds = row.elderlyId || this.ids;
      this.$modal.confirm('是否确认删除长者信息编号为"' + elderlyIds + '"的数据项？').then(function() {
        return delElderly(elderlyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhyl/elderly/export', {
        ...this.queryParams
      }, `elderly_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
