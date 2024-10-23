<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="reportTitle">
        <el-input
          v-model="queryParams.reportTitle"
          placeholder="请输入标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="长者" prop="userInfoId">
        <el-input
          v-model="queryParams.userInfoId"
          placeholder="请输入长者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="tokens" prop="useTokens">
        <el-input
          v-model="queryParams.useTokens"
          placeholder="请输入tokens"
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
      <el-form-item label="删除" prop="delFlag">
        <el-select v-model="queryParams.delFlag" placeholder="请选择删除" clearable>
          <el-option
            v-for="dict in dict.type.yl_del_flag"
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
          v-hasPermi="['zhyl:healthReport:add']"
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
          v-hasPermi="['zhyl:healthReport:edit']"
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
          v-hasPermi="['zhyl:healthReport:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['zhyl:healthReport:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="healthReportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" v-if="columns[0].visible" prop="healthReportId" />
        <el-table-column label="标题" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible" prop="reportTitle" />
        <el-table-column label="封面" align="center" v-if="columns[2].visible" prop="reportImage" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.reportImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
        <el-table-column label="报告内容" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible" prop="reportContent" />
        <el-table-column label="长者" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible" prop="userInfoId" />
        <el-table-column label="tokens" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible" prop="useTokens" />
        <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible" prop="remark" />
        <el-table-column label="创建人" :show-overflow-tooltip="true" align="center" v-if="columns[7].visible" prop="createBy" />
        <el-table-column label="创建时间" align="center" v-if="columns[8].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['zhyl:healthReport:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhyl:healthReport:remove']"
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

    <!-- 添加或修改健康报告对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="reportTitle">
          <el-input v-model="form.reportTitle" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="封面" prop="reportImage">
          <image-upload v-model="form.reportImage"/>
        </el-form-item>
        <el-form-item label="报告内容">
          <editor v-model="form.reportContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="长者" prop="userInfoId">
          <el-input v-model="form.userInfoId" placeholder="请输入长者" />
        </el-form-item>
        <el-form-item label="tokens" prop="useTokens">
          <el-input v-model="form.useTokens" placeholder="请输入tokens" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
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
import { listHealthReport, getHealthReport, delHealthReport, addHealthReport, updateHealthReport } from "@/api/zhyl/healthReport";

export default {
  name: "HealthReport",
  dicts: ['yl_del_flag'],
  data() {
    return {
      //表格展示列
      columns: [
        { key: 0, label: '编号', visible: true },
          { key: 1, label: '标题', visible: true },
          { key: 2, label: '封面', visible: true },
          { key: 3, label: '报告内容', visible: true },
          { key: 4, label: '长者', visible: true },
          { key: 5, label: 'tokens', visible: true },
          { key: 6, label: '备注', visible: true },
          { key: 7, label: '创建人', visible: true },
          { key: 8, label: '创建时间', visible: true },
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
      // 健康报告表格数据
      healthReportList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 删除时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        reportTitle: null,
        userInfoId: null,
        useTokens: null,
        createBy: null,
        createTime: null,
        delFlag: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        reportTitle: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        reportImage: [
          { required: true, message: "封面不能为空", trigger: "blur" }
        ],
        reportContent: [
          { required: true, message: "报告内容不能为空", trigger: "blur" }
        ],
        userInfoId: [
          { required: true, message: "长者不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        delFlag: [
          { required: true, message: "删除不能为空", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询健康报告列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      listHealthReport(this.queryParams).then(response => {
        this.healthReportList = response.rows;
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
        healthReportId: null,
        reportTitle: null,
        reportImage: null,
        reportContent: null,
        userInfoId: null,
        useTokens: null,
        remark: null,
        createBy: null,
        createTime: null,
        delFlag: null
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
      this.daterangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.healthReportId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加健康报告";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const healthReportId = row.healthReportId || this.ids
      getHealthReport(healthReportId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改健康报告";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.healthReportId != null) {
            updateHealthReport(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHealthReport(this.form).then(response => {
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
      const healthReportIds = row.healthReportId || this.ids;
      this.$modal.confirm('是否确认删除健康报告编号为"' + healthReportIds + '"的数据项？').then(function() {
        return delHealthReport(healthReportIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhyl/healthReport/export', {
        ...this.queryParams
      }, `healthReport_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
