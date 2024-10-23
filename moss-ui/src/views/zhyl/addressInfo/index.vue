<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="父节点" prop="parentId">
        <el-input
          v-model="queryParams.parentId"
          placeholder="请输入父节点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="编码" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入编码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="级别" prop="level">
        <el-select v-model="queryParams.level" placeholder="请选择级别" clearable>
          <el-option
            v-for="dict in dict.type.yl_address_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="经纬度" prop="center">
        <el-input
          v-model="queryParams.center"
          placeholder="请输入经纬度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排序" prop="orderNum">
        <el-input
          v-model="queryParams.orderNum"
          placeholder="请输入排序"
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
          v-hasPermi="['zhyl:addressInfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-sort"
          size="mini"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="addressInfoList"
      row-key="addressId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="父节点" :show-overflow-tooltip="true" v-if="columns[0].visible" prop="parentId" />
        <el-table-column label="编码" align="center" :show-overflow-tooltip="true" v-if="columns[1].visible" prop="code" />
        <el-table-column label="名称" align="center" :show-overflow-tooltip="true" v-if="columns[2].visible" prop="name" />
        <el-table-column label="级别" align="center" v-if="columns[3].visible" prop="level">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.yl_address_type" :value="scope.row.level"/>
        </template>
      </el-table-column>
        <el-table-column label="经纬度" align="center" :show-overflow-tooltip="true" v-if="columns[4].visible" prop="center" />
        <el-table-column label="备注" align="center" :show-overflow-tooltip="true" v-if="columns[5].visible" prop="remark" />
        <el-table-column label="排序" align="center" :show-overflow-tooltip="true" v-if="columns[6].visible" prop="orderNum" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['zhyl:addressInfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['zhyl:addressInfo:add']"
          >新增</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['zhyl:addressInfo:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改地址信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="父节点" prop="parentId">
          <treeselect v-model="form.parentId" :options="addressInfoOptions" :normalizer="normalizer" placeholder="请选择父节点" />
        </el-form-item>
        <el-form-item label="编码" prop="code">
          <el-input v-model="form.code" placeholder="请输入编码" />
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="级别" prop="level">
          <el-select v-model="form.level" placeholder="请选择级别">
            <el-option
              v-for="dict in dict.type.yl_address_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="经纬度" prop="center">
          <el-input v-model="form.center" placeholder="请输入经纬度" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="排序" prop="orderNum">
          <el-input v-model="form.orderNum" placeholder="请输入排序" />
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
import { listAddressInfo, getAddressInfo, delAddressInfo, addAddressInfo, updateAddressInfo } from "@/api/zhyl/addressInfo";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "AddressInfo",
  dicts: ['yl_address_type'],
  components: {
    Treeselect
  },
  data() {
    return {
      //表格展示列
      columns: [
          { key: 0, label: '父节点', visible: true },
            { key: 1, label: '编码', visible: true },
            { key: 2, label: '名称', visible: true },
            { key: 3, label: '级别', visible: true },
            { key: 4, label: '经纬度', visible: true },
            { key: 5, label: '备注', visible: true },
            { key: 6, label: '排序', visible: true },
        ],
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 地址信息表格数据
      addressInfoList: [],
      // 地址信息树选项
      addressInfoOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
        parentId: null,
        code: null,
        name: null,
        level: null,
        center: null,
        orderNum: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        parentId: [
          { required: true, message: "父节点不能为空", trigger: "blur" }
        ],
        code: [
          { required: true, message: "编码不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        level: [
          { required: true, message: "级别不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询地址信息列表 */
    getList() {
      this.loading = true;
      listAddressInfo(this.queryParams).then(response => {
        this.addressInfoList = this.handleTree(response.data, "addressId", "parentId");
        this.loading = false;
      });
    },
    /** 转换地址信息数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.addressId,
        label: node.name,
        children: node.children
      };
    },
	/** 查询地址信息下拉树结构 */
    getTreeselect() {
      listAddressInfo().then(response => {
        this.addressInfoOptions = [];
        const data = { addressId: 0, name: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "addressId", "parentId");
        this.addressInfoOptions.push(data);
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
        addressId: null,
        parentId: null,
        code: null,
        name: null,
        level: null,
        center: null,
        remark: null,
        orderNum: null,
        ancestors: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.addressId) {
        this.form.parentId = row.addressId;
      } else {
        this.form.parentId = 0;
      }
      this.open = true;
      this.title = "添加地址信息";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.parentId = row.parentId;
      }
      getAddressInfo(row.addressId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改地址信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.addressId != null) {
            updateAddressInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAddressInfo(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除地址信息编号为"' + row.addressId + '"的数据项？').then(function() {
        return delAddressInfo(row.addressId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
