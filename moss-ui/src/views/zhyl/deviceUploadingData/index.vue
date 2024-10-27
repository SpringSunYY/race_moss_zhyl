<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="编号" prop="dataId">
        <el-input
          v-model="queryParams.dataId"
          placeholder="请输入编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备编号" prop="deviceId">
        <el-input
          v-model="queryParams.deviceId"
          placeholder="请输入设备编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="命令" prop="command">
        <el-select v-model="queryParams.command" placeholder="请选择命令" clearable>
          <el-option
            v-for="dict in dict.type.yl_device_uploading_data_command"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择类型" clearable>
          <el-option
            v-for="dict in dict.type.yl_device_uploading_command_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="设备类型" prop="deviceType">
        <el-input
          v-model="queryParams.deviceType"
          placeholder="请输入设备类型"
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
      <el-form-item label="报警类型" prop="warningType">
        <el-select v-model="queryParams.warningType" placeholder="请选择报警类型" clearable>
          <el-option
            v-for="dict in dict.type.yl_warning_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="processingStatus">
        <el-select v-model="queryParams.processingStatus" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.yl_processing_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['zhyl:deviceUploadingData:add']"
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
          v-hasPermi="['zhyl:deviceUploadingData:edit']"
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
          v-hasPermi="['zhyl:deviceUploadingData:remove']"
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
          v-hasPermi="['zhyl:deviceUploadingData:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deviceUploadingDataList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="编号" align="center" v-if="columns[0].visible" prop="dataId"/>
      <el-table-column label="设备编号" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                       prop="deviceId"/>
      <el-table-column label="类型" align="center" v-if="columns[2].visible" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_device_uploading_command_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="命令" align="center" v-if="columns[3].visible" prop="command">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_device_uploading_data_command" :value="scope.row.command"/>
        </template>
      </el-table-column>
      <el-table-column label="设备类型" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible"
                       prop="deviceType"/>
      <el-table-column label="长者" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible"
                       prop="userInfoId"/>
      <el-table-column label="对象参数" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible"
                       prop="argument"/>
      <el-table-column label="报警类型" align="center" v-if="columns[7].visible" prop="warningType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_warning_type" :value="scope.row.warningType"/>
        </template>
      </el-table-column>
      <el-table-column label="经度" :show-overflow-tooltip="true" align="center" v-if="columns[8].visible" prop="lon"/>
      <el-table-column label="纬度" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible" prop="lat"/>
      <el-table-column label="状态" align="center" v-if="columns[10].visible" prop="processingStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_processing_status" :value="scope.row.processingStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" v-if="columns[11].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" v-if="columns[12].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
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
            v-hasPermi="['zhyl:deviceUploadingData:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhyl:deviceUploadingData:remove']"
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

    <!-- 添加或修改设备上传数据对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="设备编号" prop="deviceId">
          <el-input v-model="form.deviceId" placeholder="请输入设备编号"/>
        </el-form-item>
        <el-form-item label="类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择类型">
            <el-option
              v-for="dict in dict.type.yl_device_uploading_command_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="命令" prop="command">
          <el-select v-model="form.command" placeholder="请选择命令">
            <el-option
              v-for="dict in dict.type.yl_device_uploading_data_command"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="设备类型" prop="deviceType">
          <el-input v-model="form.deviceType" placeholder="请输入设备类型"/>
        </el-form-item>
        <!--        <el-form-item label="长者" prop="userInfoId">-->
        <!--          <el-input v-model="form.userInfoId" placeholder="请输入长者" />-->
        <!--        </el-form-item>-->
        <el-form-item label="对象参数" prop="argument">
          <el-input v-model="form.argument" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
        <!--        <el-form-item label="报警类型" prop="warningType">-->
        <!--          <el-select v-model="form.warningType" placeholder="请选择报警类型">-->
        <!--            <el-option-->
        <!--              v-for="dict in dict.type.yl_warning_type"-->
        <!--              :key="dict.value"-->
        <!--              :label="dict.label"-->
        <!--              :value="dict.value"-->
        <!--            ></el-option>-->
        <!--          </el-select>-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="经度" prop="lon">-->
        <!--          <el-input v-model="form.lon" placeholder="请输入经度" />-->
        <!--        </el-form-item>-->
        <!--        <el-form-item label="纬度" prop="lat">-->
        <!--          <el-input v-model="form.lat" placeholder="请输入纬度" />-->
        <!--        </el-form-item>-->
        <el-form-item label="状态" prop="processingStatus" v-if="form.command==='warning'">
          <el-select v-model="form.processingStatus" placeholder="请选择状态">
            <el-option
              v-for="dict in dict.type.yl_processing_status"
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
import {
  listDeviceUploadingData,
  getDeviceUploadingData,
  delDeviceUploadingData,
  addDeviceUploadingData,
  updateDeviceUploadingData
} from "@/api/zhyl/deviceUploadingData";

export default {
  name: "DeviceUploadingData",
  dicts: ['yl_processing_status', 'yl_del_flag', 'yl_device_uploading_command_type', 'yl_device_uploading_data_command', 'yl_warning_type'],
  data() {
    return {
      //表格展示列
      columns: [
        {key: 0, label: '编号', visible: true},
        {key: 1, label: '设备编号', visible: true},
        {key: 2, label: '类型', visible: true},
        {key: 3, label: '命令', visible: true},
        {key: 4, label: '设备类型', visible: true},
        {key: 5, label: '长者', visible: true},
        {key: 6, label: '对象参数', visible: true},
        {key: 7, label: '报警类型', visible: true},
        {key: 8, label: '经度', visible: true},
        {key: 9, label: '纬度', visible: true},
        {key: 10, label: '状态', visible: true},
        {key: 11, label: '创建时间', visible: true},
        {key: 12, label: '修改时间', visible: true},
        {key: 13, label: '删除', visible: true},
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
      // 设备上传数据表格数据
      deviceUploadingDataList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 删除时间范围
      daterangeCreateTime: [],
      // 删除时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        dataId: null,
        deviceId: null,
        type: null,
        command: null,
        deviceType: null,
        userInfoId: null,
        warningType: null,
        processingStatus: null,
        createTime: null,
        updateTime: null,
        delFlag: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        deviceId: [
          {required: true, message: "设备编号不能为空", trigger: "blur"}
        ],
        createTime: [
          {required: true, message: "创建时间不能为空", trigger: "blur"}
        ],
        delFlag: [
          {required: true, message: "删除不能为空", trigger: "blur"}
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询设备上传数据列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params["beginUpdateTime"] = this.daterangeUpdateTime[0];
        this.queryParams.params["endUpdateTime"] = this.daterangeUpdateTime[1];
      }
      listDeviceUploadingData(this.queryParams).then(response => {
        this.deviceUploadingDataList = response.rows;
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
        dataId: null,
        deviceId: null,
        type: null,
        command: null,
        deviceType: null,
        userInfoId: null,
        argument: null,
        warningType: null,
        lon: null,
        lat: null,
        processingStatus: null,
        createTime: null,
        updateTime: null,
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
      this.daterangeUpdateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.dataId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备上传数据";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const dataId = row.dataId || this.ids
      getDeviceUploadingData(dataId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备上传数据";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.dataId != null) {
            updateDeviceUploadingData(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDeviceUploadingData(this.form).then(response => {
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
      const dataIds = row.dataId || this.ids;
      this.$modal.confirm('是否确认删除设备上传数据编号为"' + dataIds + '"的数据项？').then(function () {
        return delDeviceUploadingData(dataIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhyl/deviceUploadingData/export', {
        ...this.queryParams
      }, `deviceUploadingData_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
