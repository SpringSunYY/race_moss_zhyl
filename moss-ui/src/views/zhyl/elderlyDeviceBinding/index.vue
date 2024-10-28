<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
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
      <el-form-item label="设备" prop="deviceId">
        <el-select
          v-model="queryParams.deviceId"
          filterable
          remote
          reserve-keyword
          placeholder="请输入设备型号"
          :remote-method="remoteDeviceBrandMethod"
          clearable
          @keyup.enter.native="handleQuery"
          :loading="deviceLoading">
          <el-option
            v-for="item in deviceList"
            :key="item.deviceId"
            :label="item.deviceModel"
            :value="item.deviceId">
          </el-option>
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
      <el-form-item label="IMEI号" prop="deviceImei">
        <el-input
          v-model="queryParams.deviceImei"
          placeholder="请输入设备IMEI号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备状态" prop="deviceStatus">
        <el-select v-model="queryParams.deviceStatus" placeholder="请选择设备状态" clearable>
          <el-option
            v-for="dict in dict.type.yl_device_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="绑定时间">
        <el-date-picker
          v-model="daterangeBindTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="解绑时间">
        <el-date-picker
          v-model="daterangeUnbindTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="绑定状态" prop="bindingStatus">
        <el-select v-model="queryParams.bindingStatus" placeholder="请选择绑定状态" clearable>
          <el-option
            v-for="dict in dict.type.yl_binding_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="服务人员" prop="userId">
        <el-select
          v-model="queryParams.userId"
          filterable
          remote
          reserve-keyword
          placeholder="请输入用户账号"
          :remote-method="selectUserServiceList"
          :loading="userServiceLoading"
        >
          <el-option
            v-for="item in userServiceList"
            :key="item.userId"
            :label="item.userName"
            :value="item.userId"
          >
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
      <el-form-item label="修改人" prop="updateBy">
        <el-input
          v-model="queryParams.updateBy"
          placeholder="请输入修改人"
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
          v-hasPermi="['zhyl:elderlyDeviceBinding:add']"
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
          v-hasPermi="['zhyl:elderlyDeviceBinding:edit']"
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
          v-hasPermi="['zhyl:elderlyDeviceBinding:remove']"
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
          v-hasPermi="['zhyl:elderlyDeviceBinding:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="elderlyDeviceBindingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="编号" align="center" v-if="columns[0].visible" prop="bindingId"/>
      <el-table-column label="长者" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                       prop="userInfoName"/>
      <el-table-column label="设备" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible"
                       prop="deviceName"/>
      <el-table-column label="设备类型" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible"
                       prop="deviceTypeName"/>
      <el-table-column label="设备IMEI号" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible"
                       prop="deviceImei"/>
      <el-table-column label="设备状态" align="center" v-if="columns[5].visible" prop="deviceStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_device_status" :value="scope.row.deviceStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="绑定时间" align="center" v-if="columns[6].visible" prop="bindTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.bindTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="解绑时间" align="center" v-if="columns[7].visible" prop="unbindTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.unbindTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="绑定状态" align="center" v-if="columns[8].visible" prop="bindingStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_binding_status" :value="scope.row.bindingStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="服务人员" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible"
                       prop="userName"/>
      <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible"
                       prop="remark"/>
      <el-table-column label="创建人" :show-overflow-tooltip="true" align="center" v-if="columns[11].visible"
                       prop="createBy"/>
      <el-table-column label="修改人" :show-overflow-tooltip="true" align="center" v-if="columns[12].visible"
                       prop="updateBy"/>
      <el-table-column label="创建时间" align="center" v-if="columns[13].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" v-if="columns[14].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="删除" align="center" v-if="columns[15].visible" prop="delFlag">
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
            v-hasPermi="['zhyl:elderlyDeviceBinding:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhyl:elderlyDeviceBinding:remove']"
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

    <!-- 添加或修改长者设备绑定对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="长者" prop="userInfoId">
          <el-select
            v-model="form.userInfoId"
            filterable
            remote
            reserve-keyword
            :disabled="this.form.bindingId!=null"
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
        <el-form-item label="设备" prop="deviceId">
          <el-select
            v-model="form.deviceId"
            filterable
            remote
            reserve-keyword
            :disabled="this.form.bindingId!=null"
            placeholder="请输入设备型号"
            :remote-method="remoteDeviceBrandMethod"
            :loading="deviceLoading">
            <el-option
              v-for="item in deviceList"
              :key="item.deviceId"
              :label="item.deviceModel"
              :value="item.deviceId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="IMEI号" prop="deviceImei">
          <el-input v-model="form.deviceImei" placeholder="请输入设备IMEI号"/>
        </el-form-item>
        <el-form-item label="设备状态" prop="deviceStatus">
          <el-select v-model="form.deviceStatus" placeholder="请选择设备状态">
            <el-option
              v-for="dict in dict.type.yl_device_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="绑定时间" prop="bindTime">
          <el-date-picker clearable
                          v-model="form.bindTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择绑定时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="解绑时间" prop="unbindTime">
          <el-date-picker clearable
                          v-model="form.unbindTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择解绑时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="绑定状态" prop="bindingStatus">
          <el-select v-model="form.bindingStatus" placeholder="请选择绑定状态">
            <el-option
              v-for="dict in dict.type.yl_binding_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="服务人员" prop="userId">
          <el-select
            v-model="form.userId"
            filterable
            remote
            reserve-keyword
            placeholder="请输入用户账号"
            :remote-method="selectUserServiceList"
            :loading="userServiceLoading"
          >
            <el-option
              v-for="item in userServiceList"
              :key="item.userId"
              :label="item.userName"
              :value="item.userId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" />
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
  listElderlyDeviceBinding,
  getElderlyDeviceBinding,
  delElderlyDeviceBinding,
  addElderlyDeviceBinding,
  updateElderlyDeviceBinding
} from "@/api/zhyl/elderlyDeviceBinding";
import {listUserInfo} from "@/api/zhyl/userInfo";
import {listDevice} from "@/api/zhyl/device";
import {allocatedUserList} from "@/api/system/role";

export default {
  name: "ElderlyDeviceBinding",
  dicts: ['yl_del_flag', 'yl_binding_status', 'yl_device_status'],
  data() {
    return {
      //系统客服角色信息
      userServiceList: [],
      userServiceLoading: false,
      userServiceQueryParams: {
        userName: '',
        roleId: 100,
        pageNum: 1,
        pageSize: 10
      },
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
      // 长者查询参数
      queryParamsDevice: {
        pageNum: 1,
        pageSize: 10,
        deviceImei: '',
        deviceId: null
      },
      // 长者信息表格数据
      deviceList: [],
      deviceLoading: true,
      //表格展示列
      columns: [
        {key: 0, label: '编号', visible: false},
        {key: 1, label: '长者', visible: true},
        {key: 2, label: '设备', visible: true},
        {key: 3, label: '设备类型', visible: true},
        {key: 4, label: '设备IMEI号', visible: true},
        {key: 5, label: '设备状态', visible: true},
        {key: 6, label: '绑定时间', visible: true},
        {key: 7, label: '解绑时间', visible: false},
        {key: 8, label: '绑定状态', visible: true},
        {key: 9, label: '服务人员', visible: true},
        {key: 10, label: '备注', visible: false},
        {key: 11, label: '创建人', visible: false},
        {key: 12, label: '修改人', visible: false},
        {key: 13, label: '创建时间', visible: false},
        {key: 14, label: '修改时间', visible: false},
        {key: 15, label: '删除', visible: false},
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
      // 长者设备绑定表格数据
      elderlyDeviceBindingList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 删除时间范围
      daterangeBindTime: [],
      // 删除时间范围
      daterangeUnbindTime: [],
      // 删除时间范围
      daterangeCreateTime: [],
      // 删除时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userInfoId: null,
        deviceId: null,
        deviceImei: null,
        bindTime: null,
        unbindTime: null,
        bindingStatus: null,
        createBy: null,
        updateBy: null,
        createTime: null,
        updateTime: null,
        delFlag: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userInfoId: [
          {required: true, message: "长者不能为空", trigger: "blur"}
        ],
        deviceId: [
          {required: true, message: "设备不能为空", trigger: "blur"}
        ],
        deviceImei: [
          {required: true, message: "设备IMEI号不能为空", trigger: "blur"}
        ],
        bindTime: [
          {required: true, message: "绑定时间不能为空", trigger: "blur"}
        ],
        bindingStatus: [
          {required: true, message: "绑定状态不能为空", trigger: "change"}
        ],
        createBy: [
          {required: true, message: "创建人不能为空", trigger: "blur"}
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
    this.getDeviceList()
    this.getUserServiceList()
  },
  methods: {
    /**
     * 获取用户列表推荐
     * @param query
     */
    selectUserServiceList(query) {
      if (query !== '') {
        this.userServiceLoading = true
        this.userServiceQueryParams.userName = query
        setTimeout(() => {
          this.getUserServiceList()
        }, 200)
      } else {
        this.userServiceList = []
      }
    },
    /**
     * 获取用户信息列表
     */
    getUserServiceList() {
      //添加查询参数
      if (this.form.userId != null) {
        this.userServiceQueryParams.userId = this.form.userId
      } else {
        this.userServiceQueryParams.userId = null
      }
      if (this.userServiceQueryParams.userName != null) {
        this.userServiceQueryParams.userId = null
      }
      allocatedUserList(this.userServiceQueryParams).then(res => {
        this.userServiceList = res.rows
        this.userServiceLoading = false
      })
    },
    //远程调用搜索
    remoteDeviceBrandMethod(query) {
      if (query !== '') {
        this.deviceLoading = true
        this.queryParamsDevice.deviceModel = query
        setTimeout(() => {
          this.getDeviceList()
        }, 200)
      } else {
        this.deviceList = []
      }
    },
    getDeviceList() {
      if (this.form.deviceId !== null && this.form.deviceId !== '') {
        this.queryParamsDevice.deviceId = this.form.deviceId
      }
      if (this.queryParamsDevice.deviceModel !== '') {
        this.queryParamsDevice.deviceId = null
      }
      listDevice(this.queryParamsDevice).then(response => {
        this.deviceList = response.rows
        this.deviceLoading = false
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
    /** 查询长者设备绑定列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeBindTime && '' != this.daterangeBindTime) {
        this.queryParams.params["beginBindTime"] = this.daterangeBindTime[0];
        this.queryParams.params["endBindTime"] = this.daterangeBindTime[1];
      }
      if (null != this.daterangeUnbindTime && '' != this.daterangeUnbindTime) {
        this.queryParams.params["beginUnbindTime"] = this.daterangeUnbindTime[0];
        this.queryParams.params["endUnbindTime"] = this.daterangeUnbindTime[1];
      }
      if (null != this.daterangeCreateTime && '' != this.daterangeCreateTime) {
        this.queryParams.params["beginCreateTime"] = this.daterangeCreateTime[0];
        this.queryParams.params["endCreateTime"] = this.daterangeCreateTime[1];
      }
      if (null != this.daterangeUpdateTime && '' != this.daterangeUpdateTime) {
        this.queryParams.params["beginUpdateTime"] = this.daterangeUpdateTime[0];
        this.queryParams.params["endUpdateTime"] = this.daterangeUpdateTime[1];
      }
      listElderlyDeviceBinding(this.queryParams).then(response => {
        this.elderlyDeviceBindingList = response.rows;
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
        bindingId: null,
        userInfoId: null,
        deviceId: null,
        deviceImei: null,
        bindTime: null,
        unbindTime: null,
        bindingStatus: null,
        createBy: null,
        updateBy: null,
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
      this.daterangeBindTime = [];
      this.daterangeUnbindTime = [];
      this.daterangeCreateTime = [];
      this.daterangeUpdateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.bindingId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加长者设备绑定";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const bindingId = row.bindingId || this.ids
      getElderlyDeviceBinding(bindingId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改长者设备绑定";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.bindingId != null) {
            updateElderlyDeviceBinding(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addElderlyDeviceBinding(this.form).then(response => {
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
      const bindingIds = row.bindingId || this.ids;
      this.$modal.confirm('是否确认删除长者设备绑定编号为"' + bindingIds + '"的数据项？').then(function () {
        return delElderlyDeviceBinding(bindingIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhyl/elderlyDeviceBinding/export', {
        ...this.queryParams
      }, `elderlyDeviceBinding_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
