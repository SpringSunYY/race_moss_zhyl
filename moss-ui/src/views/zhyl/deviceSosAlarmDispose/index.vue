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
      <el-form-item label="预警" prop="dataId">
        <el-input
          v-model="queryParams.dataId"
          placeholder="请输入预警"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处理地址" prop="addressId">
        <el-input
          v-model="queryParams.addressId"
          placeholder="请输入处理地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处理人" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入处理人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处理时间">
        <el-date-picker
          v-model="daterangeDisposeTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="处理状态" prop="disposeStatus">
        <el-select v-model="queryParams.disposeStatus" placeholder="请选择处理状态" clearable>
          <el-option
            v-for="dict in dict.type.yl_dispose_status"
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
        <el-input
          v-model="queryParams.delFlag"
          placeholder="请输入删除"
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
          v-hasPermi="['zhyl:deviceSosAlarmDispose:add']"
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
          v-hasPermi="['zhyl:deviceSosAlarmDispose:edit']"
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
          v-hasPermi="['zhyl:deviceSosAlarmDispose:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['zhyl:deviceSosAlarmDispose:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deviceSosAlarmDisposeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" v-if="columns[0].visible" prop="disposeId" />
      <el-table-column label="长者" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible" prop="userInfoId" />
      <el-table-column label="预警" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible" prop="dataId" />
      <el-table-column label="处理凭证" align="center" v-if="columns[3].visible" prop="disposeVoucher" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.disposeVoucher" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="处理描述" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible" prop="disposeDescribe" />
      <el-table-column label="处理地址" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible" prop="addressId" />
      <el-table-column label="详细地址" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible" prop="addressDetail" />
      <el-table-column label="处理人" :show-overflow-tooltip="true" align="center" v-if="columns[7].visible" prop="userId" />
      <el-table-column label="处理时间" align="center" v-if="columns[8].visible" prop="disposeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.disposeTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="处理状态" align="center" v-if="columns[9].visible" prop="disposeStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_dispose_status" :value="scope.row.disposeStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible" prop="remark" />
      <el-table-column label="创建人" :show-overflow-tooltip="true" align="center" v-if="columns[11].visible" prop="createBy" />
      <el-table-column label="创建时间" align="center" v-if="columns[12].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="删除" align="center" v-if="columns[13].visible" prop="delFlag">
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
            v-hasPermi="['zhyl:deviceSosAlarmDispose:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhyl:deviceSosAlarmDispose:remove']"
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

    <!-- 添加或修改设备预警处理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="长者" prop="userInfoId">
          <el-input v-model="form.userInfoId" placeholder="请输入长者" />
        </el-form-item>
        <el-form-item label="预警" prop="dataId">
          <el-input v-model="form.dataId" placeholder="请输入预警" />
        </el-form-item>
        <el-form-item label="处理凭证" prop="disposeVoucher">
          <image-upload v-model="form.disposeVoucher"/>
        </el-form-item>
        <el-form-item label="处理描述" prop="disposeDescribe">
          <el-input v-model="form.disposeDescribe" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="处理地址" prop="addressId">
          <el-input v-model="form.addressId" placeholder="请输入处理地址" />
        </el-form-item>
        <el-form-item label="详细地址" prop="addressDetail">
          <el-input v-model="form.addressDetail" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="处理人" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入处理人" />
        </el-form-item>
        <el-form-item label="处理时间" prop="disposeTime">
          <el-date-picker clearable
                          v-model="form.disposeTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择处理时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="处理状态" prop="disposeStatus">
          <el-radio-group v-model="form.disposeStatus">
            <el-radio
              v-for="dict in dict.type.yl_dispose_status"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
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
import { listDeviceSosAlarmDispose, getDeviceSosAlarmDispose, delDeviceSosAlarmDispose, addDeviceSosAlarmDispose, updateDeviceSosAlarmDispose } from "@/api/zhyl/deviceSosAlarmDispose";

export default {
  name: "DeviceSosAlarmDispose",
  dicts: ['yl_dispose_status'],
  data() {
    return {
      //表格展示列
      columns: [
        { key: 0, label: '编号', visible: true },
        { key: 1, label: '长者', visible: true },
        { key: 2, label: '预警', visible: true },
        { key: 3, label: '处理凭证', visible: true },
        { key: 4, label: '处理描述', visible: true },
        { key: 5, label: '处理地址', visible: true },
        { key: 6, label: '详细地址', visible: true },
        { key: 7, label: '处理人', visible: true },
        { key: 8, label: '处理时间', visible: true },
        { key: 9, label: '处理状态', visible: true },
        { key: 10, label: '备注', visible: true },
        { key: 11, label: '创建人', visible: true },
        { key: 12, label: '创建时间', visible: true },
        { key: 13, label: '删除', visible: true },
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
      // 设备预警处理表格数据
      deviceSosAlarmDisposeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 删除时间范围
      daterangeDisposeTime: [],
      // 删除时间范围
      daterangeCreateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userInfoId: null,
        dataId: null,
        addressId: null,
        userId: null,
        disposeTime: null,
        disposeStatus: null,
        createBy: null,
        createTime: null,
        delFlag: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userInfoId: [
          { required: true, message: "长者不能为空", trigger: "blur" }
        ],
        dataId: [
          { required: true, message: "预警不能为空", trigger: "blur" }
        ],
        disposeVoucher: [
          { required: true, message: "处理凭证不能为空", trigger: "blur" }
        ],
        disposeDescribe: [
          { required: true, message: "处理描述不能为空", trigger: "blur" }
        ],
        addressId: [
          { required: true, message: "处理地址不能为空", trigger: "blur" }
        ],
        userId: [
          { required: true, message: "处理人不能为空", trigger: "blur" }
        ],
        disposeTime: [
          { required: true, message: "处理时间不能为空", trigger: "blur" }
        ],
        disposeStatus: [
          { required: true, message: "处理状态不能为空", trigger: "change" }
        ],
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
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
    /** 查询设备预警处理列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeDisposeTime && '' != this.daterangeDisposeTime) {
        this.queryParams.params["beginDisposeTime"] = this.daterangeDisposeTime[0];
        this.queryParams.params["endDisposeTime"] = this.daterangeDisposeTime[1];
      }
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      listDeviceSosAlarmDispose(this.queryParams).then(response => {
        this.deviceSosAlarmDisposeList = response.rows;
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
        disposeId: null,
        userInfoId: null,
        dataId: null,
        disposeVoucher: null,
        disposeDescribe: null,
        addressId: null,
        addressDetail: null,
        userId: null,
        disposeTime: null,
        disposeStatus: null,
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
      this.daterangeDisposeTime = [];
      this.daterangeCreateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.disposeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备预警处理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const disposeId = row.disposeId || this.ids
      getDeviceSosAlarmDispose(disposeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备预警处理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.disposeId != null) {
            updateDeviceSosAlarmDispose(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDeviceSosAlarmDispose(this.form).then(response => {
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
      const disposeIds = row.disposeId || this.ids;
      this.$modal.confirm('是否确认删除设备预警处理编号为"' + disposeIds + '"的数据项？').then(function() {
        return delDeviceSosAlarmDispose(disposeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhyl/deviceSosAlarmDispose/export', {
        ...this.queryParams
      }, `deviceSosAlarmDispose_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
