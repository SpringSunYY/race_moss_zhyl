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
      <el-form-item label="用户" prop="userInfoId">
        <el-input
          v-model="queryParams.userInfoId"
          placeholder="请输入用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系手机" prop="contactPhone">
        <el-input
          v-model="queryParams.contactPhone"
          placeholder="请输入联系手机"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约人" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入预约人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约时间">
        <el-date-picker
          v-model="daterangeAppointmentTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="处理状态" prop="appointmentStatus">
        <el-select v-model="queryParams.appointmentStatus" placeholder="请选择处理状态" clearable>
          <el-option
            v-for="dict in dict.type.yl_processing_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
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
          v-hasPermi="['zhyl:appointment:add']"
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
          v-hasPermi="['zhyl:appointment:edit']"
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
          v-hasPermi="['zhyl:appointment:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['zhyl:appointment:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="appointmentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" v-if="columns[0].visible" prop="appointmentId" />
        <el-table-column label="标题" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible" prop="reportTitle" />
        <el-table-column label="用户" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible" prop="userInfoId" />
        <el-table-column label="预约内容" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible" prop="appointmentContent" />
        <el-table-column label="联系手机" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible" prop="contactPhone" />
        <el-table-column label="预约地址" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible" prop="appointAddress" />
        <el-table-column label="预约人" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible" prop="userId" />
        <el-table-column label="预约时间" align="center" v-if="columns[7].visible" prop="appointmentTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.appointmentTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
        <el-table-column label="处理状态" align="center" v-if="columns[8].visible" prop="appointmentStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_processing_status" :value="scope.row.appointmentStatus"/>
        </template>
      </el-table-column>
        <el-table-column label="创建人" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible" prop="createBy" />
        <el-table-column label="创建时间" align="center" v-if="columns[10].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['zhyl:appointment:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhyl:appointment:remove']"
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

    <!-- 添加或修改预约对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="reportTitle">
          <el-input v-model="form.reportTitle" placeholder="请输入标题" />
        </el-form-item>
        <el-form-item label="用户" prop="userInfoId">
          <el-input v-model="form.userInfoId" placeholder="请输入用户" />
        </el-form-item>
        <el-form-item label="预约内容">
          <editor v-model="form.appointmentContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="联系手机" prop="contactPhone">
          <el-input v-model="form.contactPhone" placeholder="请输入联系手机" />
        </el-form-item>
        <el-form-item label="预约地址" prop="appointAddress">
          <el-input v-model="form.appointAddress" placeholder="请输入预约地址" />
        </el-form-item>
        <el-form-item label="预约人" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入预约人" />
        </el-form-item>
        <el-form-item label="预约时间" prop="appointmentTime">
          <el-date-picker clearable
            v-model="form.appointmentTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预约时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="处理状态" prop="appointmentStatus">
          <el-select v-model="form.appointmentStatus" placeholder="请选择处理状态">
            <el-option
              v-for="dict in dict.type.yl_processing_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="删除" prop="delFlag">
          <el-select v-model="form.delFlag" placeholder="请选择删除">
            <el-option
              v-for="dict in dict.type.yl_del_flag"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
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
import { listAppointment, getAppointment, delAppointment, addAppointment, updateAppointment } from "@/api/zhyl/appointment";

export default {
  name: "Appointment",
  dicts: ['yl_processing_status', 'yl_del_flag'],
  data() {
    return {
      //表格展示列
      columns: [
        { key: 0, label: '编号', visible: true },
          { key: 1, label: '标题', visible: true },
          { key: 2, label: '用户', visible: true },
          { key: 3, label: '预约内容', visible: true },
          { key: 4, label: '联系手机', visible: true },
          { key: 5, label: '预约地址', visible: true },
          { key: 6, label: '预约人', visible: true },
          { key: 7, label: '预约时间', visible: true },
          { key: 8, label: '处理状态', visible: true },
          { key: 9, label: '创建人', visible: true },
          { key: 10, label: '创建时间', visible: true },
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
      // 预约表格数据
      appointmentList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 删除时间范围
      daterangeAppointmentTime: [],
      // 删除时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        reportTitle: null,
        userInfoId: null,
        contactPhone: null,
        userId: null,
        appointmentTime: null,
        appointmentStatus: null,
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
        userInfoId: [
          { required: true, message: "用户不能为空", trigger: "blur" }
        ],
        contactPhone: [
          { required: true, message: "联系手机不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "预约人不能为空", trigger: "blur" }
        ],
        appointmentTime: [
          { required: true, message: "预约时间不能为空", trigger: "blur" }
        ],
        appointmentStatus: [
          { required: true, message: "处理状态不能为空", trigger: "change" }
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
    /** 查询预约列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeAppointmentTime && '' != this.daterangeAppointmentTime) {
        this.queryParams.params["beginAppointmentTime"] = this.daterangeAppointmentTime[0];
        this.queryParams.params["endAppointmentTime"] = this.daterangeAppointmentTime[1];
      }
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      listAppointment(this.queryParams).then(response => {
        this.appointmentList = response.rows;
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
        appointmentId: null,
        reportTitle: null,
        userInfoId: null,
        appointmentContent: null,
        contactPhone: null,
        appointAddress: null,
        userId: null,
        appointmentTime: null,
        appointmentStatus: null,
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
      this.daterangeAppointmentTime = [];
      this.daterangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.appointmentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加预约";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const appointmentId = row.appointmentId || this.ids
      getAppointment(appointmentId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改预约";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.appointmentId != null) {
            updateAppointment(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAppointment(this.form).then(response => {
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
      const appointmentIds = row.appointmentId || this.ids;
      this.$modal.confirm('是否确认删除预约编号为"' + appointmentIds + '"的数据项？').then(function() {
        return delAppointment(appointmentIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhyl/appointment/export', {
        ...this.queryParams
      }, `appointment_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
