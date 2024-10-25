<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="站点名称" prop="stationName">
        <el-input
          v-model="queryParams.stationName"
          placeholder="请输入站点名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人" prop="contactPerson">
        <el-input
          v-model="queryParams.contactPerson"
          placeholder="请输入联系人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系电话" prop="contactPhone">
        <el-input
          v-model="queryParams.contactPhone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地区" prop="addressId">
        <el-input
          v-model="queryParams.addressId"
          placeholder="请输入地区"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="详细地址" prop="areaDetail">
        <el-input
          v-model="queryParams.areaDetail"
          placeholder="请输入详细地址"
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
      <el-form-item label="管理员" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入管理员"
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
          v-hasPermi="['zhyl:serviceStation:add']"
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
          v-hasPermi="['zhyl:serviceStation:edit']"
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
          v-hasPermi="['zhyl:serviceStation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['zhyl:serviceStation:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="serviceStationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" v-if="columns[0].visible" prop="serviceStationId" />
        <el-table-column label="站点名称" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible" prop="stationName" />
        <el-table-column label="联系人" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible" prop="contactPerson" />
        <el-table-column label="联系电话" :show-overflow-tooltip="true" align="center" v-if="columns[3].visible" prop="contactPhone" />
        <el-table-column label="服务时间" :show-overflow-tooltip="true" align="center" v-if="columns[4].visible" prop="serviceTime" />
        <el-table-column label="地区" :show-overflow-tooltip="true" align="center" v-if="columns[5].visible" prop="addressId" />
        <el-table-column label="详细地址" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible" prop="areaDetail" />
        <el-table-column label="站点相册" align="center" v-if="columns[7].visible" prop="albumUrl" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.albumUrl" :width="50" :height="50"/>
        </template>
      </el-table-column>
        <el-table-column label="创建人" :show-overflow-tooltip="true" align="center" v-if="columns[8].visible" prop="createBy" />
        <el-table-column label="修改人" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible" prop="updateBy" />
        <el-table-column label="备注" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible" prop="remark" />
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
        <el-table-column label="管理员" :show-overflow-tooltip="true" align="center" v-if="columns[13].visible" prop="userId" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['zhyl:serviceStation:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhyl:serviceStation:remove']"
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

    <!-- 添加或修改服务站信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="站点名称" prop="stationName">
          <el-input v-model="form.stationName" placeholder="请输入站点名称" />
        </el-form-item>
        <el-form-item label="联系人" prop="contactPerson">
          <el-input v-model="form.contactPerson" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="联系电话" prop="contactPhone">
          <el-input v-model="form.contactPhone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="地区" prop="addressId">
          <el-input v-model="form.addressId" placeholder="请输入地区" />
        </el-form-item>
        <el-form-item label="详细地址" prop="areaDetail">
          <el-input v-model="form.areaDetail" placeholder="请输入详细地址" />
        </el-form-item>
        <el-form-item label="站点相册" prop="albumUrl">
          <image-upload v-model="form.albumUrl"/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="管理员" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入管理员" />
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
import { listServiceStation, getServiceStation, delServiceStation, addServiceStation, updateServiceStation } from "@/api/zhyl/serviceStation";

export default {
  name: "ServiceStation",
  dicts: ['yl_del_flag'],
  data() {
    return {
      //表格展示列
      columns: [
        { key: 0, label: '编号', visible: true },
          { key: 1, label: '站点名称', visible: true },
          { key: 2, label: '联系人', visible: true },
          { key: 3, label: '联系电话', visible: true },
          { key: 4, label: '服务时间', visible: true },
          { key: 5, label: '地区', visible: true },
          { key: 6, label: '详细地址', visible: true },
          { key: 7, label: '站点相册', visible: true },
          { key: 8, label: '创建人', visible: true },
          { key: 9, label: '修改人', visible: true },
          { key: 10, label: '备注', visible: true },
          { key: 11, label: '创建时间', visible: true },
          { key: 12, label: '修改时间', visible: true },
          { key: 13, label: '管理员', visible: true },
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
      // 服务站信息表格数据
      serviceStationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 管理员时间范围
      daterangeCreateTime: [],
      // 管理员时间范围
      daterangeUpdateTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        stationName: null,
        contactPerson: null,
        contactPhone: null,
        addressId: null,
        areaDetail: null,
        createBy: null,
        updateBy: null,
        createTime: null,
        updateTime: null,
        delFlag: null,
        userId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        stationName: [
          { required: true, message: "站点名称不能为空", trigger: "blur" }
        ],
        contactPerson: [
          { required: true, message: "联系人不能为空", trigger: "blur" }
        ],
        contactPhone: [
          { required: true, message: "联系电话不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        delFlag: [
          { required: true, message: "删除不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询服务站信息列表 */
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
      listServiceStation(this.queryParams).then(response => {
        this.serviceStationList = response.rows;
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
        serviceStationId: null,
        stationName: null,
        contactPerson: null,
        contactPhone: null,
        serviceTime: null,
        addressId: null,
        areaDetail: null,
        albumUrl: null,
        createBy: null,
        updateBy: null,
        remark: null,
        createTime: null,
        updateTime: null,
        delFlag: null,
        userId: null
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
      this.ids = selection.map(item => item.serviceStationId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加服务站信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const serviceStationId = row.serviceStationId || this.ids
      getServiceStation(serviceStationId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改服务站信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.serviceStationId != null) {
            updateServiceStation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addServiceStation(this.form).then(response => {
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
      const serviceStationIds = row.serviceStationId || this.ids;
      this.$modal.confirm('是否确认删除服务站信息编号为"' + serviceStationIds + '"的数据项？').then(function() {
        return delServiceStation(serviceStationIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhyl/serviceStation/export', {
        ...this.queryParams
      }, `serviceStation_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
