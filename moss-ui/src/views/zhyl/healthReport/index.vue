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
      <el-form-item label="报告类型" prop="reportType">
        <el-select v-model="queryParams.reportType" placeholder="请选择报告类型" clearable>
          <el-option
            v-for="dict in dict.type.health_report_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="使用模型" prop="useModel">
        <el-input
          v-model="queryParams.useModel"
          placeholder="请输入使用模型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="任务状态" prop="taskStatus">
        <el-select v-model="queryParams.taskStatus" placeholder="请选择任务状态" clearable>
          <el-option
            v-for="dict in dict.type.health_report_task_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="长者" prop="userInfoId">
        <el-select
          v-model="queryParams.userInfoId"
          filterable
          remote
          reserve-keyword
          :disabled="this.form.elderlyId!=null"
          placeholder="请输入手机号码"
          :remote-method="remoteElderlyUserInfoMethod"
          @keyup.enter.native="handleQuery"
          :loading="elderlyUserInfoLoading">
          <el-option
            v-for="item in elderlyUserInfoList"
            :key="item.userInfoId"
            :label="item.userInfoName"
            :value="item.userInfoId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="创建人" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="完成时间">
        <el-date-picker
          v-model="daterangeAccomplishTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <!--      <el-form-item label="修改时间">-->
      <!--        <el-date-picker-->
      <!--          v-model="daterangeUpdateTime"-->
      <!--          style="width: 240px"-->
      <!--          value-format="yyyy-MM-dd"-->
      <!--          type="daterange"-->
      <!--          range-separator="-"-->
      <!--          start-placeholder="开始日期"-->
      <!--          end-placeholder="结束日期"-->
      <!--        ></el-date-picker>-->
      <!--      </el-form-item>-->
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
      <!--      <el-form-item label="删除" prop="delFlag">-->
      <!--        <el-select v-model="queryParams.delFlag" placeholder="请选择删除" clearable>-->
      <!--          <el-option-->
      <!--            v-for="dict in dict.type.yl_del_flag"-->
      <!--            :key="dict.value"-->
      <!--            :label="dict.label"-->
      <!--            :value="dict.value"-->
      <!--          />-->
      <!--        </el-select>-->
      <!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="primary"-->
      <!--          plain-->
      <!--          icon="el-icon-plus"-->
      <!--          size="mini"-->
      <!--          @click="handleAdd"-->
      <!--          v-hasPermi="['zhyl:healthReport:add']"-->
      <!--        >新增</el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['zhyl:healthReport:edit']"
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
          v-hasPermi="['zhyl:healthReport:remove']"
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
          v-hasPermi="['zhyl:healthReport:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="healthReportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="编号" align="center" v-if="columns[0].visible" prop="healthReportId"/>
      <el-table-column label="标题" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                       prop="reportTitle"/>
      <el-table-column label="报告类型" align="center" v-if="columns[2].visible" prop="reportType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.health_report_type" :value="scope.row.reportType"/>
        </template>
      </el-table-column>
      <el-table-column label="使用模型" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible"
                       prop="useModel"/>
      <el-table-column label="封面" align="center" v-if="columns[4].visible" prop="reportImage" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.reportImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="请求内容" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible"
                       prop="reportContent"/>
      <el-table-column label="报告返回" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible"
                       prop="reportReturn"/>
      <el-table-column label="任务ID" :show-overflow-tooltip="true" align="center" v-if="columns[7].visible"
                       prop="taskId"/>
      <el-table-column label="任务状态" align="center" v-if="columns[8].visible" prop="taskStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.health_report_task_status" :value="scope.row.taskStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="长者" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible"
                       prop="userInfoName"/>
      <el-table-column label="tokens" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible"
                       prop="useTokens"/>
      <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[11].visible"
                       prop="remark"/>
      <el-table-column label="创建人" :show-overflow-tooltip="true" align="center" v-if="columns[12].visible"
                       prop="createBy"/>
      <el-table-column label="完成时间" align="center" v-if="columns[13].visible" prop="accomplishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.accomplishTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" v-if="columns[14].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" v-if="columns[15].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="删除" align="center" v-if="columns[16].visible" prop="delFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_del_flag" :value="scope.row.delFlag"/>
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
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhyl:healthReport:remove']"
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

    <!-- 添加或修改健康报告对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="reportTitle">
          <el-input v-model="form.reportTitle" :readonly="form.healthReportId!=null" placeholder="请输入标题"/>
        </el-form-item>
        <el-form-item label="报告类型" prop="reportType">
          <el-select v-model="form.reportType"
                     :disabled="form.healthReportId!=null"
                     placeholder="请选择报告类型">
            <el-option
              v-for="dict in dict.type.health_report_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="使用模型" prop="useModel">
          <el-input v-model="form.useModel" :readonly="form.healthReportId!=null" type="textarea"
                    placeholder="请输入使用模型"/>
        </el-form-item>
        <el-form-item label="封面" prop="reportImage">
          <image-upload v-model="form.reportImage"/>
        </el-form-item>
        <el-form-item label="请求内容">
          <el-input v-model="form.reportContent" :readonly="form.healthReportId!=null" type="textarea"/>
        </el-form-item>
        <el-form-item label="报告返回" prop="reportReturn">
          <el-input v-model="form.reportReturn" :readonly="form.healthReportId!=null" type="textarea"
                    placeholder="请输入内容"/>
        </el-form-item>
        <el-form-item label="任务ID" prop="taskId">
          <el-input v-model="form.taskId" :readonly="this.form.healthReportId!=null" placeholder="请输入任务ID"/>
        </el-form-item>
        <el-form-item label="任务状态" prop="taskStatus">
          <el-select v-model="form.taskStatus" placeholder="请选择任务状态">
            <el-option
              v-for="dict in dict.type.health_report_task_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="长者" prop="userInfoId">
          <el-select
            v-model="form.userInfoId"
            filterable
            remote
            reserve-keyword
            :disabled="this.form.healthReportId!=null"
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
        <el-form-item label="tokens" prop="useTokens">
          <el-input v-model="form.useTokens" :readonly="this.form.healthReportId!=null" placeholder="请输入tokens"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注"/>
        </el-form-item>
        <!--        <el-form-item label="完成时间" prop="accomplishTime">-->
        <!--          <el-date-picker clearable-->
        <!--                          v-model="form.accomplishTime"-->
        <!--                          type="date"-->
        <!--                          value-format="yyyy-MM-dd"-->
        <!--                          placeholder="请选择完成时间">-->
        <!--          </el-date-picker>-->
        <!--        </el-form-item>-->
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
  listHealthReport,
  getHealthReport,
  delHealthReport,
  addHealthReport,
  updateHealthReport
} from "@/api/zhyl/healthReport";
import {listUserInfo} from "@/api/zhyl/userInfo";

export default {
  name: "HealthReport",
  dicts: ['health_report_type', 'health_report_task_status', 'yl_del_flag'],
  data() {
    return {
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
        {key: 1, label: '标题', visible: true},
        {key: 2, label: '报告类型', visible: true},
        {key: 3, label: '使用模型', visible: true},
        {key: 4, label: '封面', visible: false},
        {key: 5, label: '请求内容', visible: false},
        {key: 6, label: '报告返回', visible: false},
        {key: 7, label: '任务ID', visible: false},
        {key: 8, label: '任务状态', visible: true},
        {key: 9, label: '长者', visible: true},
        {key: 10, label: 'tokens', visible: true},
        {key: 11, label: '备注', visible: false},
        {key: 12, label: '创建人', visible: false},
        {key: 13, label: '完成时间', visible: false},
        {key: 14, label: '修改时间', visible: false},
        {key: 15, label: '创建时间', visible: true},
        {key: 16, label: '删除', visible: false},
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
      daterangeAccomplishTime: [],
      // 删除时间范围
      daterangeUpdateTime: [],
      // 删除时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        reportTitle: null,
        reportType: null,
        useModel: null,
        taskStatus: null,
        userInfoId: null,
        createBy: null,
        accomplishTime: null,
        updateTime: null,
        createTime: null,
        delFlag: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        reportTitle: [
          {required: true, message: "标题不能为空", trigger: "blur"}
        ],
        reportType: [
          {required: true, message: "报告类型不能为空", trigger: "change"}
        ],
        useModel: [
          {required: true, message: "使用模型不能为空", trigger: "blur"}
        ],
        reportImage: [
          {required: true, message: "封面不能为空", trigger: "blur"}
        ],
        reportContent: [
          {required: true, message: "请求内容不能为空", trigger: "blur"}
        ],
        reportReturn: [
          {required: true, message: "报告返回不能为空", trigger: "blur"}
        ],
        taskId: [
          {required: true, message: "任务ID不能为空", trigger: "blur"}
        ],
        taskStatus: [
          {required: true, message: "任务状态不能为空", trigger: "change"}
        ],
        userInfoId: [
          {required: true, message: "长者不能为空", trigger: "blur"}
        ],
        createTime: [
          {required: true, message: "创建时间不能为空", trigger: "blur"}
        ],
        delFlag: [
          {required: true, message: "删除不能为空", trigger: "change"}
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getElderlyUserInfoList()
  },
  methods: {
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
    /** 查询健康报告列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeAccomplishTime && '' != this.daterangeAccomplishTime) {
        this.queryParams.params["beginAccomplishTime"] = this.daterangeAccomplishTime[0];
        this.queryParams.params["endAccomplishTime"] = this.daterangeAccomplishTime[1];
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params["beginUpdateTime"] = this.daterangeUpdateTime[0];
        this.queryParams.params["endUpdateTime"] = this.daterangeUpdateTime[1];
      }
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
        reportType: null,
        useModel: null,
        reportImage: null,
        reportContent: null,
        reportReturn: null,
        taskId: null,
        taskStatus: null,
        userInfoId: null,
        useTokens: null,
        remark: null,
        createBy: null,
        accomplishTime: null,
        updateTime: null,
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
      this.daterangeAccomplishTime = [];
      this.daterangeUpdateTime = [];
      this.daterangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.healthReportId)
      this.single = selection.length !== 1
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
      this.$modal.confirm('是否确认删除健康报告编号为"' + healthReportIds + '"的数据项？').then(function () {
        return delHealthReport(healthReportIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
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
