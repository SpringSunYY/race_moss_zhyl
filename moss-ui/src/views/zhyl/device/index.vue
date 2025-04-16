<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!--      <el-form-item label="编号" prop="deviceId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.deviceId"-->
      <!--          placeholder="请输入设备ID"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <el-form-item label="设备品牌" prop="brandId">
        <el-select
          v-model="queryParams.brandId"
          filterable
          remote
          reserve-keyword
          placeholder="请输入品牌名称"
          :remote-method="remoteDeviceBrandMethod"
          @keyup.enter.native="handleQuery"
          :loading="deviceBrandLoading">
          <el-option
            v-for="item in deviceBrandList"
            :key="item.brandId"
            :label="item.brandName"
            :value="item.brandId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="设备类型" prop="deviceType">
        <el-select
          v-model="queryParams.deviceType"
          filterable
          remote
          reserve-keyword
          placeholder="请输入类型名称"
          :remote-method="remoteDeviceTypeMethod"
          :loading="deviceTypeLoading">
          <el-option
            v-for="item in deviceTypeList"
            :key="item.typeId"
            :label="item.deviceType"
            :value="item.deviceType">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="设备型号" prop="deviceModel">
        <el-input
          v-model="queryParams.deviceModel"
          placeholder="请输入设备型号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="通信协议" prop="communicationProtocol">
        <el-input
          v-model="queryParams.communicationProtocol"
          placeholder="请输入通信协议"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联网方式" prop="networkMode">
        <el-select v-model="queryParams.networkMode" placeholder="请选择联网方式" clearable>
          <el-option
            v-for="dict in dict.type.yl_network_mode"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="供电方式" prop="powerSupplyMode">
        <el-select v-model="queryParams.powerSupplyMode" placeholder="请选择供电方式" clearable>
          <el-option
            v-for="dict in dict.type.yl_power_supply_mode"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="安装方式" prop="installationMode">
        <el-select v-model="queryParams.installationMode" placeholder="请选择安装方式" clearable>
          <el-option
            v-for="dict in dict.type.yl_installation_mode"
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
      <!--      <el-form-item label="删除标记" prop="delFlag">-->
      <!--        <el-select v-model="queryParams.delFlag" placeholder="请选择删除标记" clearable>-->
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
          v-hasPermi="['zhyl:device:add']"
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
          v-hasPermi="['zhyl:device:edit']"
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
          v-hasPermi="['zhyl:device:remove']"
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
          v-hasPermi="['zhyl:device:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="deviceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="编号" align="center" v-if="columns[0].visible" prop="deviceId"/>
      <el-table-column label="设备品牌" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                       prop="brandName"/>
      <el-table-column label="设备类型" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible"
                       prop="deviceTypeName"/>
      <el-table-column label="设备型号" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible"
                       prop="deviceModel"/>
      <el-table-column label="通信协议" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible"
                       prop="communicationProtocol"/>
      <el-table-column label="联网方式" align="center" v-if="columns[5].visible" prop="networkMode">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_network_mode" :value="scope.row.networkMode"/>
        </template>
      </el-table-column>
      <el-table-column label="供电方式" align="center" v-if="columns[6].visible" prop="powerSupplyMode">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_power_supply_mode" :value="scope.row.powerSupplyMode"/>
        </template>
      </el-table-column>
      <el-table-column label="安装方式" align="center" v-if="columns[7].visible" prop="installationMode">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_installation_mode" :value="scope.row.installationMode"/>
        </template>
      </el-table-column>
      <el-table-column label="设备价格" :show-overflow-tooltip="true" align="center" v-if="columns[8].visible"
                       prop="devicePrice"/>
      <el-table-column label="质保时间" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible"
                       prop="warrantyPeriod"/>
      <el-table-column label="设备功能" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible"
                       prop="deviceFunction"/>
      <el-table-column label="设备参数" :show-overflow-tooltip="true" align="center" v-if="columns[11].visible"
                       prop="deviceParameters"/>
      <el-table-column label="设备图片" align="center" v-if="columns[12].visible" prop="deviceImageUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.deviceImageUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[13].visible"
                       prop="remark"/>
      <el-table-column label="创建人" :show-overflow-tooltip="true" align="center" v-if="columns[14].visible"
                       prop="createBy"/>
      <el-table-column label="修改人" :show-overflow-tooltip="true" align="center" v-if="columns[15].visible"
                       prop="updateBy"/>
      <el-table-column label="创建时间" align="center" v-if="columns[16].visible" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" v-if="columns[17].visible" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="删除标记" align="center" v-if="columns[18].visible" prop="delFlag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_del_flag" :value="scope.row.delFlag"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['zhyl:device:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleBinding(scope.row)"
            v-hasPermi="['zhyl:elderlyDeviceBinding:add']"
          >绑定设备
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhyl:device:remove']"
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

    <!-- 添加或修改设备对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-row :gutter="24">
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-col :span="8">
            <el-form-item label="设备品牌" prop="brandId">
              <el-select
                v-model="form.brandId"
                filterable
                remote
                reserve-keyword
                placeholder="请输入品牌名称"
                :remote-method="remoteDeviceBrandMethod"
                :loading="deviceBrandLoading">
                <el-option
                  v-for="item in deviceBrandList"
                  :key="item.brandId"
                  :label="item.brandName"
                  :value="item.brandId">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="设备类型" prop="deviceType">
              <el-select
                v-model="form.deviceType"
                filterable
                remote
                reserve-keyword
                placeholder="请输入类型名称"
                :remote-method="remoteDeviceTypeMethod"
                :loading="deviceTypeLoading">
                <el-option
                  v-for="item in deviceTypeList"
                  :key="item.typeId"
                  :label="item.deviceType"
                  :value="item.deviceType">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="联网方式" prop="networkMode">
              <el-select v-model="form.networkMode" placeholder="请选择联网方式">
                <el-option
                  v-for="dict in dict.type.yl_network_mode"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="供电方式" prop="powerSupplyMode">
              <el-select v-model="form.powerSupplyMode" placeholder="请选择供电方式">
                <el-option
                  v-for="dict in dict.type.yl_power_supply_mode"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="安装方式" prop="installationMode">
              <el-select v-model="form.installationMode" placeholder="请选择安装方式">
                <el-option
                  v-for="dict in dict.type.yl_installation_mode"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="通信协议" prop="communicationProtocol">
              <el-input :maxlength="2" v-model="form.communicationProtocol" placeholder="请输入通信协议"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="设备型号" prop="deviceModel">
              <el-input v-model="form.deviceModel" placeholder="请输入设备型号"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="设备价格" prop="devicePrice">
              <el-input v-model="form.devicePrice" placeholder="请输入设备价格"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="质保时间" prop="warrantyPeriod">
              <el-input v-model="form.warrantyPeriod" placeholder="请输入质保时间"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="设备功能" prop="deviceFunction">
              <el-input v-model="form.deviceFunction" type="textarea" placeholder="请输入内容"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="设备参数" prop="deviceParameters">
              <el-input v-model="form.deviceParameters" type="textarea" placeholder="请输入内容"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="设备图片" prop="deviceImageUrl">
              <image-upload v-model="form.deviceImageUrl" :limit="9"/>
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改长者设备绑定对话框 -->
    <el-dialog :title="title" :visible.sync="bindingDeviceOpen" width="500px" append-to-body>
      <el-form ref="form" :model="bindingDeviceInfo" :rules="rules" label-width="80px">
        <el-form-item label="长者" prop="userInfoId">
          <el-select
            v-model="bindingDeviceInfo.userInfoId"
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
        <el-form-item label="IMEI号" prop="deviceImei">
          <el-input v-model="bindingDeviceInfo.deviceImei" placeholder="请输入设备IMEI号"/>
        </el-form-item>
        <el-form-item label="绑定状态" prop="bindingStatus">
          <el-select v-model="bindingDeviceInfo.bindingStatus" placeholder="请选择绑定状态">
            <el-option
              v-for="dict in dict.type.yl_binding_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="绑定时间" prop="bindTime">
          <el-date-picker clearable
                          v-model="bindingDeviceInfo.bindTime"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="请选择绑定时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="服务人员" prop="userId">
          <el-select
            v-model="bindingDeviceInfo.userId"
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
          <el-input v-model="bindingDeviceInfo.remark" type="textarea" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormBinding">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {addDevice, delDevice, getDevice, listDevice, updateDevice} from "@/api/zhyl/device";
import {listDeviceBrand} from "@/api/zhyl/deviceBrand";
import {listUserInfo} from "@/api/zhyl/userInfo";
import {addElderlyDeviceBinding} from "@/api/zhyl/elderlyDeviceBinding";
import {listDeviceType} from "@/api/zhyl/deviceType";
import {allocatedUserList} from "@/api/system/role";

export default {
  name: "Device",
  dicts: ['yl_installation_mode', 'yl_device_type', 'yl_network_mode', 'yl_del_flag', 'yl_power_supply_mode',
    'yl_device_status', 'yl_device_binding_status', 'yl_binding_status'],
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
      //绑定设备信息
      bindingDeviceInfo: {},
      // 遮罩层
      bindingDeviceOpen: false,
      // 设备类型
      deviceTypeList: [],
      deviceTypeLoading: false,
      deviceTypeParams: {
        pageNum: 1,
        pageSize: 10,
        name: ''
      },
      // 设备品牌
      deviceBrandList: [],
      deviceBrandLoading: false,
      deviceBrandParams: {
        pageNum: 1,
        pageSize: 10,
        brandName: ''
      },
      // 长者查询参数
      queryParamsElderly: {
        pageNum: 1,
        pageSize: 10,
        contactPhone: null,
        userInfoId: null,
        userInfoRole: 'elderly',
      },
      // 长者信息表格数据
      elderlyUserInfoList: [],
      elderlyUserInfoLoading: true,
      //表格展示列
      columns: [
        {key: 0, label: '编号', visible: false},
        {key: 1, label: '设备品牌', visible: true},
        {key: 2, label: '设备类型', visible: true},
        {key: 3, label: '设备型号', visible: true},
        {key: 4, label: '通信协议', visible: false},
        {key: 5, label: '联网方式', visible: true},
        {key: 6, label: '供电方式', visible: true},
        {key: 7, label: '安装方式', visible: true},
        {key: 8, label: '设备价格', visible: true},
        {key: 9, label: '质保时间', visible: false},
        {key: 10, label: '设备功能', visible: false},
        {key: 11, label: '设备参数', visible: false},
        {key: 12, label: '设备图片', visible: true},
        {key: 13, label: '备注', visible: false},
        {key: 14, label: '创建人', visible: true},
        {key: 15, label: '修改人', visible: false},
        {key: 16, label: '创建时间', visible: false},
        {key: 17, label: '修改时间', visible: false},
        {key: 18, label: '删除标记', visible: false},
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
      // 设备表格数据
      deviceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 删除标记时间范围
      daterangeCreateTime: [],
      // 删除标记时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        deviceId: null,
        brandId: null,
        deviceType: null,
        deviceModel: null,
        networkMode: null,
        powerSupplyMode: null,
        installationMode: null,
        deviceStatus: null,
        deviceFunction: null,
        deviceParameters: null,
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
        brandId: [
          {required: true, message: "设备品牌不能为空", trigger: "blur"}
        ],
        deviceType: [
          {required: true, message: "设备类型不能为空", trigger: "blur"}
        ],
        deviceModel: [
          {required: true, message: "设备型号不能为空", trigger: "blur"}
        ],
        createBy: [
          {required: true, message: "创建人不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDeviceBrandList()
    this.getDeviceTypeList()
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
      listUserInfo(this.queryParamsElderly).then(response => {
        this.elderlyUserInfoList = response.rows
        this.elderlyUserInfoLoading = false
      })
    },
    //绑定设备
    handleBinding(row) {
      this.bindingDeviceOpen = true
      this.title = "绑定设备--" + row.deviceModel
      this.bindingDeviceInfo.deviceId = row.deviceId
    },
    //提交绑定信息
    submitFormBinding() {
      addElderlyDeviceBinding(this.bindingDeviceInfo).then(response => {
        this.$modal.msgSuccess("绑定成功");
        this.bindingDeviceOpen = false;
        this.bindingDeviceInfo = {}
        this.getList();
      })
    },
    // 设备类型
    remoteDeviceTypeMethod(query) {
      if (query !== '') {
        this.deviceTypeLoading = true
        this.deviceTypeParams.brandName = query
        setTimeout(() => {
          // 使用箭头函数，确保 this 仍然指向 Vue 实例
          this.getDeviceTypeList()
        }, 200)
      } else {
        this.deviceTypeList = []
      }
    },
    getDeviceTypeList() {
      if (this.form.deviceType != null) {
        this.deviceTypeParams.deviceType = this.form.deviceType
      }
      if (this.deviceTypeParams.name !== '') {
        this.deviceTypeParams.deviceType = null
      }
      listDeviceType(this.deviceTypeParams).then(response => {
        this.deviceTypeList = response.rows
        this.deviceTypeLoading = false
      })
    },
    // 设备品牌
    remoteDeviceBrandMethod(query) {
      if (query !== '') {
        this.deviceBrandLoading = true
        this.deviceBrandParams.brandName = query
        setTimeout(() => {
          // 使用箭头函数，确保 this 仍然指向 Vue 实例
          this.getDeviceBrandList()
        }, 200)
      } else {
        this.deviceBrandList = []
      }
    },
    getDeviceBrandList() {
      if (this.form.brandId != null) {
        this.deviceBrandParams.brandId = this.form.brandId
      }
      listDeviceBrand(this.deviceBrandParams).then(response => {
        this.deviceBrandList = response.rows
        this.deviceBrandLoading = false
      })
    },
    /** 查询设备列表 */
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
      listDevice(this.queryParams).then(response => {
        this.deviceList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.bindingDeviceOpen = false
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        deviceId: null,
        brandId: null,
        deviceType: null,
        deviceModel: null,
        communicationProtocol: null,
        networkMode: null,
        powerSupplyMode: null,
        installationMode: null,
        devicePrice: null,
        warrantyPeriod: null,
        deviceFunction: null,
        deviceParameters: null,
        deviceImageUrl: null,
        remark: null,
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
      this.daterangeCreateTime = [];
      this.daterangeUpdateTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.deviceId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const deviceId = row.deviceId || this.ids
      getDevice(deviceId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.deviceId != null) {
            updateDevice(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDevice(this.form).then(response => {
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
      const deviceIds = row.deviceId || this.ids;
      this.$modal.confirm('是否确认删除设备编号为"' + deviceIds + '"的数据项？').then(function () {
        return delDevice(deviceIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhyl/device/export', {
        ...this.queryParams
      }, `device_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
