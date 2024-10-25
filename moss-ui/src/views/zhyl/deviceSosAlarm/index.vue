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
      <el-form-item label="用户名" prop="userInfoName">
        <el-input
          v-model="queryParams.userInfoName"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="contactNumber">
        <el-input
          v-model="queryParams.contactNumber"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="警报时间">
        <el-date-picker
          v-model="daterangeAlarmTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="设备型号" prop="deviceModel">
        <el-input
          v-model="queryParams.deviceModel"
          placeholder="请输入设备型号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处理状态" prop="processingStatus">
        <el-select v-model="queryParams.processingStatus" placeholder="请选择处理状态" clearable>
          <el-option
            v-for="dict in dict.type.yl_processing_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="处理结果" prop="processingResult">
        <el-select v-model="queryParams.processingResult" placeholder="请选择处理结果" clearable>
          <el-option
            v-for="dict in dict.type.yl_dispose_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="设备IMEI号" prop="deviceImei">
        <el-input
          v-model="queryParams.deviceImei"
          placeholder="请输入设备IMEI号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="警报地址" prop="alarmAddress">
        <el-input
          v-model="queryParams.alarmAddress"
          placeholder="请输入警报地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['zhyl:deviceSosAlarm:add']"
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
          v-hasPermi="['zhyl:deviceSosAlarm:edit']"
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
          v-hasPermi="['zhyl:deviceSosAlarm:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['zhyl:deviceSosAlarm:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deviceSosAlarmList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" v-if="columns[0].visible" prop="sosAlarmId" />
        <el-table-column label="长者" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible" prop="userInfoId" />
        <el-table-column label="用户名" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible" prop="userInfoName" />
        <el-table-column label="联系电话" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible" prop="contactNumber" />
        <el-table-column label="警报时间" align="center" v-if="columns[4].visible" prop="alarmTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.alarmTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
        <el-table-column label="设备型号" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible" prop="deviceModel" />
        <el-table-column label="处理状态" align="center" v-if="columns[6].visible" prop="processingStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_processing_status" :value="scope.row.processingStatus"/>
        </template>
      </el-table-column>
        <el-table-column label="处理结果" align="center" v-if="columns[7].visible" prop="processingResult">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_dispose_status" :value="scope.row.processingResult"/>
        </template>
      </el-table-column>
        <el-table-column label="设备IMEI号" :show-overflow-tooltip="true" align="center" v-if="columns[8].visible" prop="deviceImei" />
        <el-table-column label="警报内容" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible" prop="alarmContent" />
        <el-table-column label="警报地址" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible" prop="alarmAddress" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['zhyl:deviceSosAlarm:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhyl:deviceSosAlarm:remove']"
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

    <!-- 添加或修改设备预警对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="长者" prop="userInfoId">
          <el-input v-model="form.userInfoId" placeholder="请输入长者" />
        </el-form-item>
        <el-form-item label="用户名" prop="userInfoName">
          <el-input v-model="form.userInfoName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="联系电话" prop="contactNumber">
          <el-input v-model="form.contactNumber" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="警报时间" prop="alarmTime">
          <el-date-picker clearable
            v-model="form.alarmTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择警报时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="设备型号" prop="deviceModel">
          <el-input v-model="form.deviceModel" placeholder="请输入设备型号" />
        </el-form-item>
        <el-form-item label="处理状态" prop="processingStatus">
          <el-select v-model="form.processingStatus" placeholder="请选择处理状态">
            <el-option
              v-for="dict in dict.type.yl_processing_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="处理结果" prop="processingResult">
          <el-select v-model="form.processingResult" placeholder="请选择处理结果">
            <el-option
              v-for="dict in dict.type.yl_dispose_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="设备IMEI号" prop="deviceImei">
          <el-input v-model="form.deviceImei" placeholder="请输入设备IMEI号" />
        </el-form-item>
        <el-form-item label="警报内容">
          <editor v-model="form.alarmContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="警报地址" prop="alarmAddress">
          <el-input v-model="form.alarmAddress" placeholder="请输入警报地址" />
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
import { listDeviceSosAlarm, getDeviceSosAlarm, delDeviceSosAlarm, addDeviceSosAlarm, updateDeviceSosAlarm } from "@/api/zhyl/deviceSosAlarm";

export default {
  name: "DeviceSosAlarm",
  dicts: ['yl_dispose_status', 'yl_processing_status'],
  data() {
    return {
      //表格展示列
      columns: [
        { key: 0, label: '编号', visible: true },
          { key: 1, label: '长者', visible: true },
          { key: 2, label: '用户名', visible: true },
          { key: 3, label: '联系电话', visible: true },
          { key: 4, label: '警报时间', visible: true },
          { key: 5, label: '设备型号', visible: true },
          { key: 6, label: '处理状态', visible: true },
          { key: 7, label: '处理结果', visible: true },
          { key: 8, label: '设备IMEI号', visible: true },
          { key: 9, label: '警报内容', visible: true },
          { key: 10, label: '警报地址', visible: true },
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
      // 设备预警表格数据
      deviceSosAlarmList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 删除时间范围
      daterangeAlarmTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userInfoId: null,
        userInfoName: null,
        contactNumber: null,
        alarmTime: null,
        deviceModel: null,
        processingStatus: null,
        processingResult: null,
        deviceImei: null,
        alarmAddress: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userInfoId: [
          { required: true, message: "长者不能为空", trigger: "blur" }
        ],
        deviceImei: [
          { required: true, message: "设备IMEI号不能为空", trigger: "blur" }
        ],
        delFlag: [
          { required: true, message: "删除不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询设备预警列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeAlarmTime && '' != this.daterangeAlarmTime) {
        this.queryParams.params["beginAlarmTime"] = this.daterangeAlarmTime[0];
        this.queryParams.params["endAlarmTime"] = this.daterangeAlarmTime[1];
      }
      listDeviceSosAlarm(this.queryParams).then(response => {
        this.deviceSosAlarmList = response.rows;
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
        sosAlarmId: null,
        userInfoId: null,
        userInfoName: null,
        contactNumber: null,
        alarmTime: null,
        deviceModel: null,
        processingStatus: null,
        processingResult: null,
        deviceImei: null,
        alarmContent: null,
        alarmAddress: null,
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
      this.daterangeAlarmTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.sosAlarmId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备预警";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const sosAlarmId = row.sosAlarmId || this.ids
      getDeviceSosAlarm(sosAlarmId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备预警";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.sosAlarmId != null) {
            updateDeviceSosAlarm(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDeviceSosAlarm(this.form).then(response => {
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
      const sosAlarmIds = row.sosAlarmId || this.ids;
      this.$modal.confirm('是否确认删除设备预警编号为"' + sosAlarmIds + '"的数据项？').then(function() {
        return delDeviceSosAlarm(sosAlarmIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhyl/deviceSosAlarm/export', {
        ...this.queryParams
      }, `deviceSosAlarm_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
