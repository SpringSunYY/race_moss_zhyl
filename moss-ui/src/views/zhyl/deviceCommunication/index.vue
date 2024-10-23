<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备IMEI" prop="deviceImei">
        <el-input
          v-model="queryParams.deviceImei"
          placeholder="请输入设备IMEI"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="通讯方式" prop="communicationMode">
        <el-select v-model="queryParams.communicationMode" placeholder="请选择通讯方式" clearable>
          <el-option
            v-for="dict in dict.type.yl_communication_mode"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="用户" prop="userInfoId">
        <el-input
          v-model="queryParams.userInfoId"
          placeholder="请输入用户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="事件类型" prop="eventType">
        <el-select v-model="queryParams.eventType" placeholder="请选择事件类型" clearable>
          <el-option
            v-for="dict in dict.type.yl_event_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="重要程度" prop="importanceLevel">
        <el-select v-model="queryParams.importanceLevel" placeholder="请选择重要程度" clearable>
          <el-option
            v-for="dict in dict.type.yl_importance_level"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="通信结果" prop="communicationResult">
        <el-input
          v-model="queryParams.communicationResult"
          placeholder="请输入通信结果"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="通信时间">
        <el-date-picker
          v-model="daterangeCommunicationTime"
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
          v-hasPermi="['zhyl:deviceCommunication:add']"
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
          v-hasPermi="['zhyl:deviceCommunication:edit']"
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
          v-hasPermi="['zhyl:deviceCommunication:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['zhyl:deviceCommunication:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deviceCommunicationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" v-if="columns[0].visible" prop="deviceCommunicationId" />
        <el-table-column label="设备IMEI" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible" prop="deviceImei" />
        <el-table-column label="通讯方式" align="center" v-if="columns[2].visible" prop="communicationMode">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_communication_mode" :value="scope.row.communicationMode"/>
        </template>
      </el-table-column>
        <el-table-column label="用户" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible" prop="userInfoId" />
        <el-table-column label="事件类型" align="center" v-if="columns[4].visible" prop="eventType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_event_type" :value="scope.row.eventType"/>
        </template>
      </el-table-column>
        <el-table-column label="通信内容" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible" prop="communicationContent" />
        <el-table-column label="重要程度" align="center" v-if="columns[6].visible" prop="importanceLevel">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_importance_level" :value="scope.row.importanceLevel"/>
        </template>
      </el-table-column>
        <el-table-column label="通信结果" align="center" v-if="columns[7].visible" prop="communicationResult">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_communication_result" :value="scope.row.communicationResult"/>
        </template>
      </el-table-column>
        <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[8].visible" prop="remark" />
        <el-table-column label="通信时间" align="center" v-if="columns[9].visible" prop="communicationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.communicationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['zhyl:deviceCommunication:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhyl:deviceCommunication:remove']"
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

    <!-- 添加或修改设备通信记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备IMEI" prop="deviceImei">
          <el-input v-model="form.deviceImei" placeholder="请输入设备IMEI" />
        </el-form-item>
        <el-form-item label="通讯方式" prop="communicationMode">
          <el-select v-model="form.communicationMode" placeholder="请选择通讯方式">
            <el-option
              v-for="dict in dict.type.yl_communication_mode"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户" prop="userInfoId">
          <el-input v-model="form.userInfoId" placeholder="请输入用户" />
        </el-form-item>
        <el-form-item label="事件类型" prop="eventType">
          <el-select v-model="form.eventType" placeholder="请选择事件类型">
            <el-option
              v-for="dict in dict.type.yl_event_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="通信内容">
          <editor v-model="form.communicationContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="重要程度" prop="importanceLevel">
          <el-select v-model="form.importanceLevel" placeholder="请选择重要程度">
            <el-option
              v-for="dict in dict.type.yl_importance_level"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="通信结果" prop="communicationResult">
          <el-input v-model="form.communicationResult" placeholder="请输入通信结果" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="通信时间" prop="communicationTime">
          <el-date-picker clearable
            v-model="form.communicationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择通信时间">
          </el-date-picker>
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
import { listDeviceCommunication, getDeviceCommunication, delDeviceCommunication, addDeviceCommunication, updateDeviceCommunication } from "@/api/zhyl/deviceCommunication";

export default {
  name: "DeviceCommunication",
  dicts: ['yl_importance_level', 'yl_communication_mode', 'yl_event_type', 'yl_del_flag'],
  data() {
    return {
      //表格展示列
      columns: [
        { key: 0, label: '编号', visible: true },
          { key: 1, label: '设备IMEI', visible: true },
          { key: 2, label: '通讯方式', visible: true },
          { key: 3, label: '用户', visible: true },
          { key: 4, label: '事件类型', visible: true },
          { key: 5, label: '通信内容', visible: true },
          { key: 6, label: '重要程度', visible: true },
          { key: 7, label: '通信结果', visible: true },
          { key: 8, label: '备注', visible: true },
          { key: 9, label: '通信时间', visible: true },
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
      // 设备通信记录表格数据
      deviceCommunicationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 删除时间范围
      daterangeCommunicationTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deviceImei: null,
        communicationMode: null,
        userInfoId: null,
        eventType: null,
        communicationContent: null,
        importanceLevel: null,
        communicationResult: null,
        communicationTime: null,
        delFlag: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        deviceImei: [
          { required: true, message: "设备IMEI不能为空", trigger: "blur" }
        ],
        communicationMode: [
          { required: true, message: "通讯方式不能为空", trigger: "change" }
        ],
        importanceLevel: [
          { required: true, message: "重要程度不能为空", trigger: "change" }
        ],
        communicationResult: [
          { required: true, message: "通信结果不能为空", trigger: "blur" }
        ],
        communicationTime: [
          { required: true, message: "通信时间不能为空", trigger: "blur" }
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
    /** 查询设备通信记录列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCommunicationTime && '' != this.daterangeCommunicationTime) {
        this.queryParams.params["beginCommunicationTime"] = this.daterangeCommunicationTime[0];
        this.queryParams.params["endCommunicationTime"] = this.daterangeCommunicationTime[1];
      }
      listDeviceCommunication(this.queryParams).then(response => {
        this.deviceCommunicationList = response.rows;
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
        deviceCommunicationId: null,
        deviceImei: null,
        communicationMode: null,
        userInfoId: null,
        eventType: null,
        communicationContent: null,
        importanceLevel: null,
        communicationResult: null,
        remark: null,
        communicationTime: null,
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
      this.daterangeCommunicationTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.deviceCommunicationId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备通信记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const deviceCommunicationId = row.deviceCommunicationId || this.ids
      getDeviceCommunication(deviceCommunicationId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备通信记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.deviceCommunicationId != null) {
            updateDeviceCommunication(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDeviceCommunication(this.form).then(response => {
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
      const deviceCommunicationIds = row.deviceCommunicationId || this.ids;
      this.$modal.confirm('是否确认删除设备通信记录编号为"' + deviceCommunicationIds + '"的数据项？').then(function() {
        return delDeviceCommunication(deviceCommunicationIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhyl/deviceCommunication/export', {
        ...this.queryParams
      }, `deviceCommunication_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
