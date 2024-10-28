<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--地址数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input
            v-model="addressName"
            placeholder="请输入地址名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="head-container"
             style="position: sticky; top: 0; z-index: 10; background-color: white; overflow-x: auto; white-space: nowrap;"
        >
          <div class="infinite-list-wrapper" style="max-height: 75vh; overflow-y: auto;">
            <div style="min-width: fit-content;"> <!-- 设定最小宽度以便于滑动 -->
              <el-tree
                :data="addressOptions"
                :props="defaultProps"
                :expand-on-click-node="false"
                :filter-node-method="filterNode"
                ref="tree"
                node-key="id"
                default-expand-all
                highlight-current
                @node-click="handleNodeClick"
                :render-content="renderContent"
              />
            </div>
          </div>
        </div>
      </el-col>
      <el-col :span="20" :xs="24">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                 label-width="68px">
          <el-form-item label="姓名" prop="userInfoName">
            <el-input
              v-model="queryParams.userInfoName"
              placeholder="请输入家属姓名"
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
          <el-form-item label="角色" prop="userInfoRole">
            <el-select v-model="queryParams.userInfoRole" placeholder="请选择角色" clearable>
              <el-option
                v-for="dict in dict.type.yl_user_info_role"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="性别" prop="sex">
            <el-select v-model="queryParams.sex" placeholder="请选择性别" clearable>
              <el-option
                v-for="dict in dict.type.sys_user_sex"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="学历" prop="education">
            <el-select v-model="queryParams.education" placeholder="请选择学历" clearable>
              <el-option
                v-for="dict in dict.type.yl_education"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="职业" prop="occupation">
            <el-select v-model="queryParams.occupation" placeholder="请选择职业" clearable>
              <el-option
                v-for="dict in dict.type.yl_occupation"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <!--          <el-form-item label="地址" prop="addressId">-->
          <!--            <el-input-->
          <!--              v-model="queryParams.addressId"-->
          <!--              placeholder="请输入地址"-->
          <!--              clearable-->
          <!--              @keyup.enter.native="handleQuery"-->
          <!--            />-->
          <!--          </el-form-item>-->
          <!--      <el-form-item label="微信openid" prop="wxOpenid">-->
          <!--        <el-input-->
          <!--          v-model="queryParams.wxOpenid"-->
          <!--          placeholder="请输入微信openid"-->
          <!--          clearable-->
          <!--          @keyup.enter.native="handleQuery"-->
          <!--        />-->
          <!--      </el-form-item>-->
          <!--      <el-form-item label="小程序openid" prop="miniOpenid">-->
          <!--        <el-input-->
          <!--          v-model="queryParams.miniOpenid"-->
          <!--          placeholder="请输入小程序openid"-->
          <!--          clearable-->
          <!--          @keyup.enter.native="handleQuery"-->
          <!--        />-->
          <!--      </el-form-item>-->
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
          <!--          <el-form-item label="删除" prop="delFlag">-->
          <!--            <el-select v-model="queryParams.delFlag" placeholder="请选择删除" clearable>-->
          <!--              <el-option-->
          <!--                v-for="dict in dict.type.yl_del_flag"-->
          <!--                :key="dict.value"-->
          <!--                :label="dict.label"-->
          <!--                :value="dict.value"-->
          <!--              />-->
          <!--            </el-select>-->
          <!--          </el-form-item>-->
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
              v-hasPermi="['zhyl:userInfo:add']"
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
              v-hasPermi="['zhyl:userInfo:edit']"
            >修改
            </el-button>
          </el-col>
          <!--      <el-col :span="1.5">-->
          <!--        <el-button-->
          <!--          type="danger"-->
          <!--          plain-->
          <!--          icon="el-icon-delete"-->
          <!--          size="mini"-->
          <!--          :disabled="multiple"-->
          <!--          @click="handleDelete"-->
          <!--          v-hasPermi="['zhyl:userInfo:remove']"-->
          <!--        >删除-->
          <!--        </el-button>-->
          <!--      </el-col>-->
          <el-col :span="1.5">
            <el-button
              type="warning"
              plain
              icon="el-icon-download"
              size="mini"
              @click="handleExport"
              v-hasPermi="['zhyl:userInfo:export']"
            >导出
            </el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
        </el-row>
        <el-table v-loading="loading" :data="userInfoList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center"/>
          <el-table-column label="编号" align="center" v-if="columns[0].visible" prop="userInfoId"/>
          <el-table-column label="家属姓名" :show-overflow-tooltip="true" align="center" v-if="columns[1].visible"
                           prop="userInfoName"/>
          <el-table-column label="联系手机" :show-overflow-tooltip="true" align="center" v-if="columns[2].visible"
                           prop="contactPhone"/>
          <el-table-column label="用户头像" align="center" v-if="columns[3].visible" prop="userInfoProfile" width="100">
            <template slot-scope="scope">
              <image-preview :src="scope.row.userInfoProfile" :width="50" :height="50"/>
            </template>
          </el-table-column>
          <el-table-column label="角色" align="center" v-if="columns[4].visible" prop="userInfoRole">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.yl_user_info_role" :value="scope.row.userInfoRole"/>
            </template>
          </el-table-column>
          <el-table-column label="性别" align="center" v-if="columns[5].visible" prop="sex">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.sex"/>
            </template>
          </el-table-column>
          <el-table-column label="身份证" :show-overflow-tooltip="true" align="center" v-if="columns[6].visible"
                           prop="idCard"/>
          <el-table-column label="学历" align="center" v-if="columns[7].visible" prop="education">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.yl_education" :value="scope.row.education"/>
            </template>
          </el-table-column>
          <el-table-column label="职业" align="center" v-if="columns[8].visible" prop="occupation">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.yl_occupation" :value="scope.row.occupation"/>
            </template>
          </el-table-column>
          <el-table-column label="地址" :show-overflow-tooltip="true" align="center" v-if="columns[9].visible"
                           prop="addressId"/>
          <el-table-column label="详细地址" :show-overflow-tooltip="true" align="center" v-if="columns[10].visible"
                           prop="areaDetail"/>
          <el-table-column label="微信openid" :show-overflow-tooltip="true" align="center" v-if="columns[11].visible"
                           prop="wxOpenid"/>
          <el-table-column label="小程序openid" :show-overflow-tooltip="true" align="center" v-if="columns[12].visible"
                           prop="miniOpenid"/>
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
          <el-table-column label="操作" align="center" width="200" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['zhyl:userInfo:edit']"
              >修改
              </el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['zhyl:userInfo:remove']"
              >删除
              </el-button>
              <el-dropdown size="mini" @command="(command) => handleCommand(command, scope.row)"
                           v-hasPermi="['zhyl:userInfo:updatePassword']">
                <el-button size="mini" type="text" icon="el-icon-d-arrow-right">更多</el-button>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="handleResetPwd" icon="el-icon-key"
                                    v-hasPermi="['zhyl:userInfo:updatePassword']">重置密码
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
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
      </el-col>
    </el-row>

    <!-- 添加或修改用户信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-row :gutter="20">
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-col :span="6">
            <el-form-item label="姓名" prop="userInfoName">
              <el-input v-model="form.userInfoName" placeholder="请输入家属姓名"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="联系手机" prop="contactPhone">
              <el-input v-model="form.contactPhone" placeholder="请输入联系手机"/>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="角色" prop="userInfoRole">
              <el-select v-model="form.userInfoRole" placeholder="请选择角色">
                <el-option
                  v-for="dict in dict.type.yl_user_info_role"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="身份证" prop="idCard">
              <el-input v-model="form.idCard" placeholder="请输入身份证"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="性别" prop="sex">
              <el-select v-model="form.sex" placeholder="请选择性别">
                <el-option
                  v-for="dict in dict.type.sys_user_sex"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="学历" prop="education">
              <el-select v-model="form.education" placeholder="请选择学历">
                <el-option
                  v-for="dict in dict.type.yl_education"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="职业" prop="occupation">
              <el-select v-model="form.occupation" placeholder="请选择职业">
                <el-option
                  v-for="dict in dict.type.yl_occupation"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item v-if="this.form.userInfoRole==='elderly'" label="失能情况" prop="disabilityStatus">
              <el-select v-model="form.disabilityStatus" placeholder="请选择失能情况">
                <el-option
                  v-for="dict in dict.type.yl_disability_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item v-if="this.form.userInfoRole==='elderly'" label="居住情况" prop="livingCondition">
              <el-select v-model="form.livingCondition" placeholder="请选择居住情况">
                <el-option
                  v-for="dict in dict.type.yl_living_condition"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item v-if="this.form.userInfoRole==='elderly'" label="登记时间" prop="registrationTime">
              <el-date-picker clearable
                              v-model="form.registrationTime"
                              type="datetime"
                              value-format="yyyy-MM-dd HH:mm:ss"
                              placeholder="请选择登记时间">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item v-if="this.form.userInfoRole==='elderly'" label="残疾情况" prop="disabilityCondition">
              <el-input v-model="form.disabilityCondition" placeholder="请输入残疾情况"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="地址" prop="addressId">
              <treeselect v-model="form.addressId" :options="addressOptions" :show-count="true"
                          placeholder="请选择地址信息"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="详细地址" prop="areaDetail">
              <el-input v-model="form.areaDetail" placeholder="请输入详细地址"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item
              v-for="(domain, index) in form.elderlyFamilyList"
              :label="'家属' + (index + 1)"
              :key="index"
              :prop="'elderlyFamilyList.' + index + '.value'"
            >
              <el-col :span="7">
                <el-form-item label="用户"
                              :prop="'elderlyFamilyList.' + index + '.userInfoId'"
                              :rules="[
                                      { required: true, message: '请选择用户', trigger: 'blur' }
                              ]">
                  <el-select
                    v-model="form.elderlyFamilyList[index].userInfoElderlyId"
                    filterable
                    remote
                    reserve-keyword
                    placeholder="请输入手机号码"
                    :remote-method="remoteUserInfoFamilyMethod"
                    :loading="userInfoFamilyLoading">
                    <el-option
                      v-for="item in userInfoFamilyList"
                      :key="item.userInfoId"
                      :label="item.userInfoName"
                      :value="item.userInfoId">
                    </el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="7">
                <el-form-item label="关系"
                              :prop="'elderlyFamilyList.' + index + '.relationshipType'"
                              :rules="[
                                      { required: true, message: '请选择关系', trigger: 'blur' }
                              ]">
                  <el-select v-model="form.elderlyFamilyList[index].relationshipType" placeholder="请选择关系">
                    <el-option
                      v-for="dict in dict.type.yl_relationship_type"
                      :key="dict.value"
                      :label="dict.label"
                      :value="dict.value"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </el-col>
              <el-col :span="7">
                <el-form-item label="备注" :prop="'elderlyFamilyList.' + index + '.areaDetail'">
                  <el-input v-model="form.elderlyFamilyList[index].remark" placeholder="请输入备注"/>
                </el-form-item>
              </el-col>
              <el-col :span="3">
                <el-button @click.prevent="removeElderlyFamily(domain)">删除</el-button>
              </el-col>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="用户头像" prop="userInfoProfile">
              <image-upload v-model="form.userInfoProfile" :limit="1"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" type="textarea" placeholder="请输入备注"/>
            </el-form-item>
          </el-col>
          <!--          <el-form-item label="微信openid" prop="wxOpenid">-->
          <!--            <el-input v-model="form.wxOpenid" placeholder="请输入微信openid"/>-->
          <!--          </el-form-item>-->
          <!--          <el-form-item label="小程序openid" prop="miniOpenid">-->
          <!--            <el-input v-model="form.miniOpenid" placeholder="请输入小程序openid"/>-->
          <!--          </el-form-item>-->
        </el-form>
      </el-row>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="addElderlyFamilyList">新增家属</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listUserInfo, getUserInfo, delUserInfo, addUserInfo, updateUserInfo, resetUserPwd} from "@/api/zhyl/userInfo";
import Treeselect from '@riophae/vue-treeselect'
import '@riophae/vue-treeselect/dist/vue-treeselect.css'
import {listAddressTreeInfo} from "@/api/zhyl/addressInfo";

export default {
  name: "UserInfo",
  components: {Treeselect},
  dicts: ['yl_del_flag', 'yl_user_info_role', 'yl_education', 'yl_occupation', 'sys_user_sex', 'yl_disability_status', 'yl_living_condition', 'yl_relationship_type'],
  data() {
    return {
      //用户新增显示数组 是长者查询家属 是家属查询长者
      userInfoFamilyList: [],
      userInfoFamilyLoading: false,
      queryUserInfoFamilyParams: {
        pageNum: 1,
        pageSize: 10,
        contactPhone: '',
        userInfoId: '',
        userInfoRole: 'elderly',  //长者
      },
      //地址树选项
      addressOptions: undefined,
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      //地址名称
      addressName: '',
      //表格展示列
      columns: [
        {key: 0, label: '编号', visible: false},
        {key: 1, label: '姓名', visible: true},
        {key: 2, label: '联系手机', visible: true},
        {key: 3, label: '用户头像', visible: true},
        {key: 4, label: '角色', visible: true},
        {key: 5, label: '性别', visible: true},
        {key: 6, label: '身份证', visible: true},
        {key: 7, label: '学历', visible: true},
        {key: 8, label: '职业', visible: true},
        {key: 9, label: '地址', visible: false},
        {key: 10, label: '详细地址', visible: false},
        {key: 11, label: '微信openid', visible: false},
        {key: 12, label: '小程序openid', visible: false},
        {key: 13, label: '备注', visible: false},
        {key: 14, label: '创建人', visible: false},
        {key: 15, label: '修改人', visible: false},
        {key: 16, label: '创建时间', visible: false},
        {key: 17, label: '修改时间', visible: false},
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
      // 用户信息表格数据
      userInfoList: [],
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
        userInfoName: null,
        contactPhone: null,
        userInfoProfile: null,
        userInfoRole: null,
        sex: null,
        idCard: null,
        education: null,
        occupation: null,
        addressId: null,
        createBy: null,
        updateBy: null,
        createTime: null,
        updateTime: null,
      },
      // 表单参数
      form: {
        elderlyFamilyList: []
      },
      // 表单校验
      rules: {
        userInfoName: [
          {required: true, message: "家属姓名不能为空", trigger: "blur"},
          {min: 2, max: 20, message: '用户名称长度必须介于 2 和 20 之间', trigger: 'blur'}
        ],
        userInfoRole: [
          {required: true, message: "角色不能为空", trigger: "change"}
        ],
        createBy: [
          {required: true, message: "创建人不能为空", trigger: "blur"}
        ],
        createTime: [
          {required: true, message: "创建时间不能为空", trigger: "blur"}
        ],
        delFlag: [
          {required: true, message: "删除不能为空", trigger: "change"}
        ],
        contactPhone: [
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getAddressTree()
  },
  methods: {
    remoteUserInfoFamilyMethod(query) {
      if (query !== '') {
        this.userInfoFamilyLoading = true
        this.queryUserInfoFamilyParams.contactPhone = query
        setTimeout(() => {
          // 使用箭头函数，确保 this 仍然指向 Vue 实例
          this.getUserInfoFamilyList()
        }, 200)
      } else {
        this.userInfoFamilyList = []
      }
    },
    getUserInfoFamilyList() {
      if (this.form.userInfoRole === 'elderly') {
        this.queryUserInfoFamilyParams.userInfoRole = 'elderly_family'
        //如果是第一次查询，并且是没有使用搜索
        if (this.queryUserInfoFamilyParams.contactPhone === '') {
          this.queryUserInfoFamilyParams.userInfoIds = this.form.elderlyFamilyList.map(item => item.userInfoElderlyId)
        }
      } else {
        this.queryUserInfoFamilyParams.userInfoRole = 'elderly'
        //如果是第一次查询，并且是没有使用搜索
        if (this.queryUserInfoFamilyParams.contactPhone === '') {
          this.queryUserInfoFamilyParams.userInfoIds = this.form.elderlyFamilyList.map(item => item.userInfo)
        }
      }
      listUserInfo(this.queryUserInfoFamilyParams).then(response => {
        this.userInfoFamilyList = response.rows
        this.userInfoFamilyLoading = false
      })
    },
    //添加家属
    addElderlyFamilyList() {
      try {
        console.log('当前 form:', this.form);
        if (!this.form.elderlyFamilyList) {
          this.$set(this.form, 'elderlyFamilyList', []);
        }
        this.form.elderlyFamilyList.push({
          value: '',
          key: Date.now(),
          relationshipType: '',
          remark: '',
          userInfoId: this.form.userInfoId,
          userInfoElderlyId: null,
        });
        console.log(this.form.elderlyFamilyList);
      } catch (error) {
        console.error('添加家属时发生错误:', error);
      }
    },

    //删除家属
    removeElderlyFamily(item) {
      var index = this.form.elderlyFamilyList.indexOf(item)
      if (index !== -1) {
        this.form.elderlyFamilyList.splice(index, 1)
      }
    },

    // 更多操作触发
    handleCommand(command, row) {
      switch (command) {
        case "handleResetPwd":
          this.handleResetPwd(row);
          break;
        default:
          break;
      }
    },
    /** 重置密码按钮操作 */
    handleResetPwd(row) {
      this.$prompt('请输入"' + row.userInfoName + '"的新密码', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        closeOnClickModal: false,
        inputPattern: /^.{5,20}$/,
        inputErrorMessage: "用户密码长度必须介于 5 和 20 之间",
        inputValidator: (value) => {
          if (/<|>|"|'|\||\\/.test(value)) {
            return "不能包含非法字符：< > \" ' \\\ |"
          }
        },
      }).then(({value}) => {
        resetUserPwd(row.userInfoId, value).then(response => {
          this.$modal.msgSuccess("修改成功，新密码是：" + value);
        });
      }).catch(() => {
      });
    },
    /**
     * 查询地址树
     */
    getAddressTree() {
      listAddressTreeInfo().then(res => {
        this.addressOptions = res.data
      })
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.addressId = data.id
      this.handleQuery()
    },
    renderContent(h, {node, data}) {
      return (
        <el-tooltip content={data.label} placement="top">
          <span>{data.label}</span>
        </el-tooltip>
      )
    },
    /** 查询用户信息列表 */
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
      listUserInfo(this.queryParams).then(response => {
        this.userInfoList = response.rows;
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
        userInfoId: null,
        userInfoName: null,
        contactPhone: null,
        userInfoProfile: null,
        userInfoRole: null,
        sex: null,
        password: null,
        idCard: null,
        education: null,
        occupation: null,
        addressId: null,
        areaDetail: null,
        wxOpenid: null,
        miniOpenid: null,
        createBy: null,
        updateBy: null,
        createTime: null,
        updateTime: null,
        delFlag: null,
        remark: null,
        disabilityStatus: null,
        livingCondition: null,
        registrationTime: null,
        disabilityCondition: null,
        elderlyFamilyList: []
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
      this.queryParams.addressId = null
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userInfoId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const userInfoId = row.userInfoId || this.ids
      getUserInfo(userInfoId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户信息";
        this.getUserInfoFamilyList()
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.userInfoId != null) {
            updateUserInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUserInfo(this.form).then(response => {
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
      const userInfoIds = row.userInfoId || this.ids;
      this.$modal.confirm('是否确认删除用户信息编号为"' + userInfoIds + '"的数据项？').then(function () {
        return delUserInfo(userInfoIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('zhyl/userInfo/export', {
        ...this.queryParams
      }, `userInfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
