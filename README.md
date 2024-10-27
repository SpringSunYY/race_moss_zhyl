#### 地址

##### 已完成

> 地址管理
>
> 祖级列表

#### 用户管理

##### 已完成

> 用户校验：身份证唯一校验、手机号码唯一校验
>
> 用户密码：密码加密、重置密码
>
> 用户表格
>
> 完成在用户页面创建、更新长者信息
>
> 根据用户信息查询、更新长者信息
>
> 完成长者和家属关联，多对多
>
> 完成长者和家属单个添加、批量添加：在用户管理批量添加，批量添加时会先删除所有数据，长者添加会根据长者删除所有和他有关联的，家属反之同理；单个添加在长者信息添加
>
> 页面优化，查询优化

#### 设备管理

> 设备协议
>
> 设备品牌
>
> 设备绑定，一个设备只可以绑定一个用户，只会有deviceIMEI，优化设备管理，提出设备公共信息，设备deviceIMEI在用户绑定处
>
> 新增设备类型
>
> 设备管理

#### 其他

> 所有枚举
>
> 权限Constants
>
> 参数Constants
>
> 排序为创建时间倒序

### 数据库

user_info和user的区别

> user_info:表示用户，长者或者家属
>
> user:表示管理员
>
> 因为在若依里面，他自己默认用户使用的是user，避免与若依的用户表的冲突



#### 地址表：yl_address_info

| 字段名       | 数据类型 | 长度 | 是否为空 | 外键 | 备注     |
| ------------ | -------- | ---- | -------- | ---- | -------- |
| `address_id` | bigint   |      | 否       |      | 编号     |
| `parent_id`  | bigint   |      | 否       |      | 父级     |
| `code`       | varchar  | 50   | 是       |      | 编码     |
| `name`       | varchar  | 20   | 否       |      | 名称     |
| `level`      | char     | 1    | 否       |      | 级别     |
| `center`     | varchar  | 50   | 是       |      | 经纬度   |
| `remark`     | varchar  | 255  | 是       |      | 备注     |
| `order_num`  | bigint   |      | 是       |      | 排序     |
| `ancestors`  | varchar  | 255  | 是       |      | 组极列表 |

地区级别：1:省份, 2:市, 3:区县, 4:街道

祖级列表：存地址信息父类，可用于后续的地址查询

城市中心点：经纬度坐标

直接使用地址表来为平台的所有的要关联的地址的信息，方便后续如果要使用地址的统计

```sql
DROP TABLE IF EXISTS `yl_address_info`;
CREATE TABLE `yl_address_info` (
    `address_id` BIGINT NOT NULL COMMENT '编号',
    `parent_id` BIGINT NOT NULL DEFAULT 0 COMMENT '父节点',
    `code` VARCHAR(50)  DEFAULT '0' COMMENT '编码',
    `name` VARCHAR(20) NOT NULL COMMENT '名称',
    `level` CHAR(1) NOT NULL COMMENT '级别',
    `center` VARCHAR(50) DEFAULT NULL COMMENT '经纬度',
    `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `order_num` BIGINT DEFAULT 0 COMMENT '排序',
    `ancestors` VARCHAR(255) DEFAULT NULL COMMENT '组极列表',
    PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='地址信息表';

```



#### 用户表：yl_user_info

| 字段名              | 数据类型 | 长度 | 是否为空 | 外键         | 备注                    |
| ------------------- | -------- | ---- | -------- | ------------ | ----------------------- |
| `user_info_id`      | bigint   |      | 否       |              | 编号                    |
| `user_info_name`    | varchar  | 64   | 否       |              | 家属姓名                |
| `contact_phone`     | varchar  | 20   | 是       |              | 联系手机                |
| `user_info_profile` | varchar  | 255  | 是       |              | 用户头像                |
| `user_info_role`    | varchar  | 32   | 否       |              | 角色                    |
| `id_card`           | varchar  | 32   | 是       |              | 身份证                  |
| `sex`               | char     | 1    | 是       |              | 性别                    |
| `password`          | varchar  | 64   | 否       |              | 密码                    |
| `education`         | tinyint  |      | 是       |              | 学历                    |
| `occupation`        | varchar  | 50   | 是       |              | 职业                    |
| `address_id`        | bigint   |      | 是       | `address_id` | 地址(地址表:address_id) |
| `area_detail`       | varchar  |      | 是       |              | 详细地址                |
| `wx_openid`         | varchar  | 32   | 是       |              | 微信openid              |
| `mini_openid`       | varchar  | 32   | 是       |              | 小程序openid            |
| `remark`            | varchar  | 255  | 是       |              | 备注                    |
| `create_by`         | varchar  | 50   | 否       |              | 创建人                  |
| `update_by`         | varchar  | 50   | 是       |              | 修改人                  |
| `create_time`       | datetime |      | 否       |              | 创建时间                |
| `update_time`       | datetime |      | 是       |              | 修改时间                |
| `del_flag`          | char     | 1    | 否       | 0            | 删除                    |

学历：本科0，大专1，硕士2，高中3，初中4，职高5，其他6

职业：0其他

角色：elderly长者，elderly_family家属，用于鉴权

删除：0正常1删除

密码：手机号+密码

统一使用用户表来管理老者和家属，只使用一个用户表来登录，判断他们的角色，登录小程序

```sql
DROP TABLE IF EXISTS `yl_user_info`;
CREATE TABLE `yl_user_info` (
    `user_info_id` BIGINT NOT NULL COMMENT '编号',
    `user_info_name` VARCHAR(64) NOT NULL COMMENT '家属姓名',
    `contact_phone` VARCHAR(20) DEFAULT NULL COMMENT '联系手机',
    `user_info_profile` VARCHAR(255) DEFAULT NULL COMMENT '用户头像',
    `user_info_role` VARCHAR(32) NOT NULL COMMENT '角色',
        sex               char             null comment '性别',
    password          varchar(64)       null comment '密码',
    `id_card` VARCHAR(32) DEFAULT NULL COMMENT '身份证',
    `education` TINYINT DEFAULT NULL COMMENT '学历',
    `occupation` VARCHAR(50) DEFAULT NULL COMMENT '职业',
    `address_id` BIGINT DEFAULT NULL COMMENT '地址',
    `area_detail` VARCHAR(255) DEFAULT NULL COMMENT '详细地址',
    `wx_openid` VARCHAR(32) DEFAULT NULL COMMENT '微信openid',
    `mini_openid` VARCHAR(32) DEFAULT NULL COMMENT '小程序openid',
    `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `create_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    `update_by` VARCHAR(50) DEFAULT NULL COMMENT '修改人',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
    `del_flag` CHAR(1) NOT NULL DEFAULT '0' COMMENT '删除',
    PRIMARY KEY (`user_info_id`),
    FOREIGN KEY (`address_id`) REFERENCES `yl_address_info`(`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

```



#### 长者信息表：yl_elderly

| 字段名                 | 数据类型 | 长度 | 是否为空 | 外键         | 备注                       |
| ---------------------- | -------- | ---- | -------- | ------------ | -------------------------- |
| `elderly_id`           | bigint   |      | 否       |              | 编号                       |
| `user_info_id`         | bigint   |      | 否       | user_info_id | 长者（用户表user_info_id） |
| `disability_status`    | char     | 1    | 是       |              | 失能情况                   |
| `living_condition`     | char     | 1    | 是       |              | 居住情况                   |
| `registration_time`    | datetime |      | 是       |              | 登记时间                   |
| `disability_condition` | varchar  | 255  | 是       |              | 残疾情况                   |
| `create_by`            | varchar  | 50   | 否       |              | 创建人                     |
| `create_time`          | datetime |      | 否       |              | 创建时间                   |
| `update_time`          | datetime |      | 是       |              | 修改时间                   |

失能情况：0能力完好，1轻度，2中度，3重度

居住情况：0其他，1独自居住，2与配偶居住，3与子女居住，4与亲戚居住



记录长者更多信息

```sql
DROP TABLE IF EXISTS `yl_elderly`;
CREATE TABLE `yl_elderly` (
    `elderly_id` BIGINT NOT NULL COMMENT '编号',
    `user_info_id` BIGINT NOT NULL COMMENT '长者',
    `disability_status` CHAR(1) DEFAULT NULL COMMENT '失能情况',
    `living_condition` CHAR(1) DEFAULT NULL COMMENT '居住情况',
    `registration_time` DATETIME DEFAULT NULL COMMENT '登记时间',
    `disability_condition` varcahr(255) DEFAULT NULL COMMENT '残疾情况',
    `create_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`elderly_id`),
    UNIQUE KEY `unique_elderly_user` (`elderly_id`, `user_info_id`),
    FOREIGN KEY (`user_info_id`) REFERENCES `yl_user_info`(`user_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='长者信息表';

```



#### 家属信息表：yl_elderly_family

| 字段名                 | 数据类型 | 长度         | 是否为空 | 外键           | 备注                       |
| ---------------------- | -------- | ------------ | -------- | -------------- | -------------------------- |
| `elderly_family_id`    | bigint   |              | 否       |                | 编号                       |
| `user_info_id`         | bigint   | user_info_id | 否       | `user_info_id` | 家属（用户表user_info_id） |
| `user_info_elderly_id` | bigint   |              | 否       | `user_info_id` | 长者（用户表user_info_id） |
| `relationship_type`    | char     | 1            | 否       |                | 关系                       |
| `remark`               | varchar  | 255          | 是       |                | 备注                       |

长者：这里的长者id也是用户表角色为长者的用户

关系：0其他、1配偶、2、子女、3子孙

记录家属和长者的关联信息

```sql
DROP TABLE IF EXISTS `yl_elderly_family`;
CREATE TABLE `yl_elderly_family` (
    `elderly_family_id` BIGINT NOT NULL COMMENT '编号',
    `user_info_id` BIGINT NOT NULL COMMENT '家属',
    `user_info_elderly_id` BIGINT NOT NULL COMMENT '长者',
    `relationship_type` char(1) not null comment '关系'
    `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`elderly_family_id`),
    UNIQUE KEY `unique_family_elderly` (`user_info_id`, `user_info_elderly_id`),
    FOREIGN KEY (`user_info_id`) REFERENCES `yl_user_info`(`user_info_id`),
    FOREIGN KEY (`user_info_elderly_id`) REFERENCES `yl_user_info`(`user_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='家属信息表';

```



#### 设备品牌表：yl_device_brand

| 字段名               | 数据类型 | 长度 | 是否为空 | 外键 | 备注     |
| -------------------- | -------- | ---- | -------- | ---- | -------- |
| `brand_id`           | bigint   |      | 否       |      | 编号     |
| `brand_name`         | varchar  | 255  | 否       |      | 品牌名称 |
| `description`        | varchar  | 255  | 是       |      | 品牌描述 |
| `contact_info`       | varchar  | 255  | 是       |      | 联系方式 |
| `contract_content`   | text     |      | 是       |      | 内容     |
| `contract_sign_time` | datetime |      | 是       |      | 签订时间 |
| `create_by`          | varchar  | 50   | 否       |      | 创建人   |
| `create_time`        | datetime |      | 是       |      | 创建时间 |
| `update_time`        | datetime |      | 是       |      | 更新时间 |

```sql
DROP TABLE IF EXISTS `yl_device_brand`;
CREATE TABLE `yl_device_brand` (
    `brand_id` BIGINT NOT NULL COMMENT '编号',
    `brand_name` VARCHAR(255) NOT NULL COMMENT '品牌名称',
    `description` VARCHAR(255) DEFAULT NULL COMMENT '品牌描述',
    `contact_info` VARCHAR(255) DEFAULT NULL COMMENT '联系方式',
    `contract_content` TEXT DEFAULT NULL COMMENT '内容',
    `contract_sign_time` DATETIME DEFAULT NULL COMMENT '签订时间',
    `create_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备品牌表';

```



#### 设备协议表：yl_device_agreement

| 字段名             | 数据类型 | 长度 | 是否为空 | 外键 | 备注     |
| ------------------ | -------- | ---- | -------- | ---- | -------- |
| `agreement_id`     | bigint   |      | 否       |      | 编号     |
| `report_name`      | varchar  | 255  | 否       |      | 协议名称 |
| `report_content`   | text     |      | 否       |      | 协议内容 |
| `agreement_status` | char     | 1    | 否       |      | 协议状态 |
| `remark`           | varchar  | 255  | 是       |      | 备注     |
| `create_by`        | varchar  | 50   | 否       |      | 创建人   |
| `create_time`      | datetime |      | 是       |      | 创建时间 |
| `update_time`      | datetime |      | 是       |      | 更新时间 |

协议状态：0启用，1关闭

```sql
DROP TABLE IF EXISTS `yl_device_agreement`;
CREATE TABLE `yl_device_agreement` (
    `agreement_id` BIGINT NOT NULL COMMENT '编号',
    `report_name` VARCHAR(255) NOT NULL COMMENT '协议名称',
    `report_content` TEXT NOT NULL COMMENT '协议内容',
    `agreement_status` CHAR(1) NOT NULL COMMENT '协议状态',
    `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `create_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`agreement_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备协议表';

```

#### 设备类型表：yl_device_type

| 字段名        | 数据类型 | 长度 | 是否为空 | 外键 | 备注           |
| ------------- | -------- | ---- | -------- | ---- | -------------- |
| `type_id`     | bigint   |      | 否       |      | 编号           |
| `name`        | varchar  | 64   | 否       |      | 名称           |
| `device_type` | varchar  | 64   | 否       |      | 设备类型(唯一) |
| `remark`      | varchar  | 255  | 是       |      | 备注           |
| `create_by`   | varchar  | 50   | 否       |      | 创建人         |
| `create_time` | datetime |      | 是       |      | 创建时间       |
| `update_time` | datetime |      | 是       |      | 修改时间       |

```sql
CREATE TABLE `yl_device_type` (
    `type_id` bigint NOT NULL COMMENT '编号',
    `name` varchar(64) NOT NULL COMMENT '名称',
    `device_type` varchar(64) NOT NULL UNIQUE COMMENT '设备类型',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    `create_by` varchar(50) NOT NULL COMMENT '创建人',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    PRIMARY KEY (`type_id`)
) COMMENT='设备类型表';

```

#### 设备表：yl_device

| 字段名                   | 数据类型 | 长度 | 是否为空 | 外键          | 备注                                |
| ------------------------ | -------- | ---- | -------- | ------------- | ----------------------------------- |
| `device_id`              | bigint   |      | 否       |               | 编号                                |
| `brand_id`               | bigint   |      | 否       | `brand_id`    | 设备品牌（设备品牌表brand_id）      |
| `device_type`            | varchar  | 64   | 否       | `device_type` | 设备类型（设备类型表：device_type） |
| `device_model`           | varchar  | 100  | 否       |               | 设备型号                            |
| `communication_protocol` | tinyint  |      | 是       |               | 通信协议                            |
| `network_mode`           | tinyint  |      | 是       |               | 联网方式                            |
| `power_supply_mode`      | tinyint  |      | 是       |               | 供电方式                            |
| `installation_mode`      | tinyint  |      | 是       |               | 安装方式                            |
| `device_price`           | float    |      | 是       |               | 设备价格                            |
| `warranty_period`        | int      |      | 是       |               | 质保时间                            |
| `device_function`        | text     |      | 是       |               | 设备功能                            |
| `device_parameters`      | text     |      | 是       |               | 设备参数                            |
| `device_image_url`       | varchar  | 1024 | 是       |               | 设备图片                            |
| `remark`                 | varchar  | 255  | 是       |               | 备注                                |
| `create_by`              | varchar  | 50   | 否       |               | 创建人                              |
| `update_by`              | varchar  | 50   | 是       |               | 修改人                              |
| `create_time`            | datetime |      | 是       |               | 创建时间                            |
| `update_time`            | datetime |      | 是       |               | 修改时间                            |
| `del_flag`               | char     | 1    | 是       |               | 删除标记                            |

联网方式：其他0，4G 1，WiFi 2

供电方式：其他0，铝电池1，适配器2

安装方式：其他0，挂脖1，手腕佩戴2，吸顶3，挂壁4

绑定状态：0未绑定、1绑定

删除：0正常1删除

```sql
CREATE TABLE `yl_device` (
    `device_id` bigint NOT NULL COMMENT '编号',
    `brand_id` bigint NOT NULL COMMENT '设备品牌',
    `device_type` varchar(64) NOT NULL COMMENT '设备类型',
    `device_model` varchar(100) NOT NULL COMMENT '设备型号',
    `communication_protocol` tinyint DEFAULT NULL COMMENT '通信协议',
    `network_mode` tinyint DEFAULT NULL COMMENT '联网方式',
    `power_supply_mode` tinyint DEFAULT NULL COMMENT '供电方式',
    `installation_mode` tinyint DEFAULT NULL COMMENT '安装方式',
    `device_price` float DEFAULT NULL COMMENT '设备价格',
    `warranty_period` int DEFAULT NULL COMMENT '质保时间',
    `device_function` text DEFAULT NULL COMMENT '设备功能',
    `device_parameters` text DEFAULT NULL COMMENT '设备参数',
    `device_image_url` varchar(1024) DEFAULT NULL COMMENT '设备图片',
    `remark` varchar(255) DEFAULT NULL COMMENT '备注',
    `create_by` varchar(50) NOT NULL COMMENT '创建人',
    `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
    `create_time` datetime DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    `del_flag` char(1) DEFAULT NULL COMMENT '删除标记',
    PRIMARY KEY (`device_id`),
    FOREIGN KEY (`brand_id`) REFERENCES `yl_device_brand`(`brand_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`device_type`) REFERENCES `yl_device_type`(`device_type`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='设备表';

```



#### 长者设备绑定表：yl_elderly_device_binding

| 字段名           | 数据类型 | 长度 | 是否为空 | 外键           | 备注                     |
| ---------------- | -------- | ---- | -------- | -------------- | ------------------------ |
| `binding_id`     | bigint   |      | 否       |                | 编号                     |
| `user_info_id`   | bigint   |      | 否       | `user_info_id` | 长者(用户表user_info_id) |
| `device_id`      | bigint   |      | 否       | `device_id`    | 设备（设备表device_id）  |
| `device_type`    | varchar  | 64   | 否       | `device_type`  | 设备类型                 |
| `device_imei`    | varchar  | 255  | 否       |                | 设备IMEI号               |
| `device_status`  | char     | 1    | 否       |                | 设备状态                 |
| `bind_time`      | datetime |      | 否       |                | 绑定时间                 |
| `unbind_time`    | datetime |      | 是       |                | 解绑时间                 |
| `binding_status` | char     | 1    | 否       |                | 绑定状态                 |
| `user_id`        | bigint   |      | 否       |                | 服务人员                 |
| `create_by`      | varchar  | 50   | 否       |                | 创建人                   |
| `update_by`      | varchar  | 50   | 是       |                | 修改人                   |
| `create_time`    | datetime |      | 否       |                | 创建时间                 |
| `update_time`    | datetime |      | 是       |                | 修改时间                 |
| `del_flag`       | char     | 1    | 否       |                | 删除                     |

绑定状态：0绑定，1解绑

管理人员：如果告警，发送信息给管理人员

删除：0正常1删除

```sql
CREATE TABLE `yl_elderly_device_binding` (
    `binding_id` bigint NOT NULL COMMENT '编号',
    `user_info_id` bigint NOT NULL COMMENT '长者(用户表user_info_id)',
    `device_id` bigint NOT NULL COMMENT '设备（设备表device_id）',
    `device_type` varchar(64) NOT NULL COMMENT '设备类型',
    `device_imei` varchar(255) NOT NULL COMMENT '设备IMEI号',
    `device_status` char(1)  NOT NULL COMMENT '设备状态'
    `bind_time` datetime NOT NULL COMMENT '绑定时间',
    `unbind_time` datetime DEFAULT NULL COMMENT '解绑时间',
    `binding_status` char(1) NOT NULL COMMENT '绑定状态',
    `user_id` BIGINT NOT NULL COMMENT '服务人员（sys_user:user_id）',
    `create_by` varchar(50) NOT NULL COMMENT '创建人',
    `update_by` varchar(50) DEFAULT NULL COMMENT '修改人',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    `del_flag` char(1) NOT NULL COMMENT '删除',
    PRIMARY KEY (`binding_id`),
    FOREIGN KEY (`user_info_id`) REFERENCES `yl_user_info`(`user_info_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`device_id`) REFERENCES `yl_device`(`device_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`user_info_id`) REFERENCES `yl_user_info`(`user_info_id`)
) COMMENT='长者设备绑定表';

```



#### 设备通信记录表：yl_device_communication TODO:后续要设计

| 字段名                    | 数据类型 | 长度 | 是否为空 | 外键          | 备注                                  |
| ------------------------- | -------- | ---- | -------- | ------------- | ------------------------------------- |
| `device_communication_id` | bigint   |      | 否       |               | 编号                                  |
| `device_imei`             | varchar  | 255  | 否       | `device_imei` | 设备IMEI（长者设备绑定表device_imei） |
| `user_info_id`            | bigint   |      | 是       | user_info_id  | 用户（用户表user_info_id）            |
| `communication_mode`      | tinyint  |      | 否       |               | 通讯方式                              |
| `event_type`              | tinyint  |      | 是       |               | 事件类型                              |
| `communication_content`   | text     |      | 是       |               | 通信内容                              |
| `importance_level`        | tinyint  |      | 否       |               | 重要程度                              |
| `communication_result`    | tinyint  |      | 否       |               | 通信结果                              |
| `remark`                  | text     |      | 是       |               | 备注                                  |
| `communication_time`      | datetime |      | 否       |               | 通信时间                              |
| `del_flag`                | char     | 1    | 否       |               | 删除                                  |

通讯方式：0上传，1下发

事件类型：0其他

重要程度：0一般，1重要

通信结果：0成功1失败

删除：0正常1删除

```sql
DROP TABLE IF EXISTS `yl_device_communication`;
CREATE TABLE `yl_device_communication` (
    `device_communication_id` BIGINT NOT NULL COMMENT '编号',
    `device_imei` VARCHAR(255) NOT NULL UNIQUE  COMMENT '设备IMEI',
    `communication_mode` TINYINT NOT NULL COMMENT '通讯方式',
     `user_info_id` BIGINT  COMMENT '用户（用户表user_info_id）',
    `event_type` TINYINT DEFAULT NULL COMMENT '事件类型',
    `communication_content` TEXT DEFAULT NULL COMMENT '通信内容',
    `importance_level` TINYINT NOT NULL COMMENT '重要程度',
    `communication_result` TINYINT NOT NULL COMMENT '通信结果',
    `remark` TEXT DEFAULT NULL COMMENT '备注',
    `communication_time` DATETIME NOT NULL COMMENT '通信时间',
    `del_flag` CHAR(1) NOT NULL COMMENT '删除',
    PRIMARY KEY (`device_communication_id`),
    FOREIGN KEY (`device_imei`) REFERENCES `yl_elderly_device_binding`(`device_imei`),
    FOREIGN KEY (`user_info_id`) REFERENCES `yl_user_info` (`user_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备通信记录表';

```





#### 设备上传数据表：yl_device_uploading_data

| 字段名              | 数据类型 | 长度 | 是否为空 | 外键           | 备注                       |
| ------------------- | -------- | ---- | -------- | -------------- | -------------------------- |
| `data_id`           | bigint   |      | 否       |                | 编号                       |
| `device_id`         | varchar  | 64   | 否       |                | 设备编号                   |
| `type`              | varcahr  | 16   | 是       |                | 类型                       |
| `command`           | varchar  | 16   | 是       |                | 命令                       |
| `device_type`       | varchar  | 64   | 是       |                | 设备类型                   |
| `user_info_id`      | bigint   |      | 是       | `user_info_id` | 长者（用户表user_info_id） |
| `argument`          | varchar  | 2048 | 是       |                | 对象参数                   |
| `warning_type`      | varchar  | 8    | 是       |                | 报警类型                   |
| `lon`               | varchar  | 32   | 是       |                | 经度                       |
| `lat`               | varchar  | 32   | 是       |                | 纬度                       |
| `processing_status` | varchar  | 1    | 是       |                | 状态                       |
| `create_time`       | datetime |      | 否       |                | 创建时间                   |
| `update_time`       | datetime |      | 是       |                | 修改时间                   |
| `del_flag`          | char     | 1    | 否       |                | 删除                       |

设备上传数据表，设备上传的所有数据，也就是命令的这几种类型的数据

命令： disconnected设备离线、connected 设备上线、location定位信息、warning预警信息、health健康信息、beatHeart运动情况

设备编号：如果设备编号为空把他作为脏数据，其实是设备IMEI

长者：因为报警信息没有长者信息，这个需要重设备id去获取

状态：1待处理、2处理完成、3未处理、4处理失败

对象参数：从报警信息

报警类型、地址、经纬度:从对象参数中获取，为较为重要的数据，把他们提出来好做为后续的统计

报警类型：为string,“1” sos 预警 、“2” 跌倒预警 、“3” 脱手预警 、“4” 久坐预警 、“5” 低电预警（无,已经弃用） 、“6” 家庭医生 、“7” 房颤预警 、“8” 司法手表拆卸预警 、“9” 红外预警、 “101” sos报警恢复

这里功能实现使用策略模式

这里的长者家属信息后台直接查

```sql
CREATE TABLE `yl_device_uploading_data` (
    `data_id` bigint NOT NULL COMMENT '编号',
    `device_id` varchar(64) NOT NULL COMMENT '设备编号',
    `type` varchar(16) DEFAULT NULL COMMENT '类型',
    `command` varchar(16) DEFAULT NULL COMMENT '命令',
    `device_type` varchar(64) DEFAULT NULL COMMENT '设备类型',
    `user_info_id` bigint DEFAULT NULL COMMENT '长者',
    `argument` varchar(2048) DEFAULT NULL COMMENT '对象参数',
    `warning_type` varchar(8) DEFAULT NULL COMMENT '报警类型',
    `lon` varchar(32) DEFAULT NULL COMMENT '经度',
    `lat` varchar(32) DEFAULT NULL COMMENT '纬度',
    `processing_status` char(1) DEFAULT NULL COMMENT '状态',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime DEFAULT NULL COMMENT '修改时间',
    `del_flag` char(1) NOT NULL COMMENT '删除',
    PRIMARY KEY (`data_id`),
    FOREIGN KEY (`user_info_id`) REFERENCES `yl_user_info`(`user_info_id`)
) COMMENT='设备上传数据表';

```



#### 设备预警处理表：yl_device_sos_alarm_dispose

| 字段名             | 数据类型 | 长度 | 是否为空 | 外键           | 备注                             |
| ------------------ | -------- | ---- | -------- | -------------- | -------------------------------- |
| `dispose_id`       | bigint   |      | 否       |                | 编号                             |
| `user_info_id`     | bigint   |      | 否       | `user_info_id` | 长者（用户表user_info_id）       |
| `sos_alarm_id`     | bigint   |      | 否       | `sos_alarm_id` | 预警（设备预警表：sos_alarm_id） |
| `dispose_voucher`  | varchar  | 1024 | 否       |                | 处理凭证                         |
| `dispose_describe` | text     |      | 否       |                | 处理描述                         |
| `dispose_address`  | bigint   |      | 否       |                | 处理地址                         |
| `address_detail`   | varchar  | 255  | 是       |                | 详细地址                         |
| `dispose_status`   | char     | 1    | 否       |                | 处理状态                         |
| `user_id`          | bigint   |      | 否       | user_id        | 处理人（sys_user:user_id）       |
| `dispose_time`     | datetime |      | 否       |                | 处理时间                         |
| `remark`           | varchar  | 255  | 是       |                | 备注                             |
| `create_by`        | varchar  | 50   | 否       |                | 创建人                           |
| `create_time`      | datetime |      | 否       |                | 创建时间                         |
| `del_flag`         | char     | 1    | 否       |                | 删除                             |

处理凭证：处理这个sos预警的凭证，拍照上传

处理状态：0处理成功、1处理失败、2无需处理

处理人：系统角色

```sql
DROP TABLE IF EXISTS `yl_device_sos_alarm_dispose`;
CREATE TABLE `yl_device_sos_alarm_dispose` (
    `dispose_id` BIGINT NOT NULL COMMENT '编号',
    `user_info_id` BIGINT NOT NULL COMMENT '长者（用户表user_info_id）',
    `data_id` BIGINT NOT NULL COMMENT '预警（设备预警表：sos_alarm_id）',
    `dispose_voucher` VARCHAR(1024) NOT NULL COMMENT '处理凭证',
    `dispose_describe` TEXT NOT NULL COMMENT '处理描述',
    `dispose_address` BIGINT NOT NULL COMMENT '处理地址',
    `address_detail` VARCHAR(255) DEFAULT NULL COMMENT '详细地址',
    `user_id` BIGINT NOT NULL COMMENT '处理人（sys_user:user_id）',
    `dispose_time` DATETIME NOT NULL COMMENT '处理时间',
    `dispose_status` CHAR(1) NOT NULL COMMENT '处理状态',
    `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `create_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `del_flag` CHAR(1) NOT NULL COMMENT '删除',
    PRIMARY KEY (`dispose_id`),
    FOREIGN KEY (`user_info_id`) REFERENCES `yl_user_info`(`user_info_id`),
    FOREIGN KEY (`sos_alarm_id`) REFERENCES `yl_device_uploading_data`(`data_id`),
    FOREIGN KEY (`user_id`) REFERENCES `sys_user`(`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='设备预警处理表';

```



#### 服务站信息表：yl_service_station

| 字段名               | 数据类型 | 长度 | 是否为空 | 外键         | 备注                       |
| -------------------- | -------- | ---- | -------- | ------------ | -------------------------- |
| `service_station_id` | bigint   |      | 否       |              | 编号                       |
| `station_name`       | varchar  | 255  | 否       |              | 站点名称                   |
| `contact_person`     | varchar  | 50   | 否       |              | 联系人                     |
| `contact_phone`      | varchar  | 20   | 否       |              | 联系电话                   |
| `service_time`       | json     |      | 是       |              | 服务时间                   |
| `address_id`         | bigint   |      | 是       | `address_id` | 地区（地址表：address_id） |
| `area_detail`        | varchar  |      | 是       |              | 详细地址                   |
| `album_url`          | text     |      | 是       |              | 站点相册                   |
| `create_by`          | varchar  | 50   | 否       |              | 创建人                     |
| `update_by`          | varchar  | 50   | 是       |              | 修改人                     |
| `remark`             | varchar  | 255  | 是       |              | 备注                       |
| `create_time`        | datetime |      | 否       |              | 创建时间                   |
| `update_time`        | datetime |      | 是       |              | 修改时间                   |
| `del_flag`           | char     | 1    | 否       |              | 删除                       |
| `user_id`            | bigint   |      | 否       |              | 管理员                     |

删除：0正常1删除

```sql
DROP TABLE IF EXISTS `yl_service_station`;
CREATE TABLE `yl_service_station` (
                                      `service_station_id` BIGINT NOT NULL COMMENT '编号',
                                      `station_name` VARCHAR(255) NOT NULL COMMENT '站点名称',
                                      `contact_person` VARCHAR(50) NOT NULL COMMENT '联系人',
                                      `contact_phone` VARCHAR(20) NOT NULL COMMENT '联系电话',
                                      `service_time` JSON DEFAULT NULL COMMENT '服务时间',
                                      `address_id` BIGINT DEFAULT NULL COMMENT '地区（地址表：address_id）',
                                      `area_detail` VARCHAR(255) DEFAULT NULL COMMENT '详细地址',
                                      `album_url` TEXT DEFAULT NULL COMMENT '站点相册',
                                      `create_by` VARCHAR(50) NOT NULL COMMENT '创建人',
                                      `update_by` VARCHAR(50) DEFAULT NULL COMMENT '修改人',
                                      `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
                                      `create_time` DATETIME NOT NULL COMMENT '创建时间',
                                      `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
                                      `del_flag` CHAR(1) NOT NULL COMMENT '删除',
                                      `user_id` BIGINT DEFAULT NULL COMMENT '管理员',
                                      PRIMARY KEY (`service_station_id`),
                                      FOREIGN KEY (`address_id`) REFERENCES `yl_address_info`(`address_id`),
                                      FOREIGN KEY (`user_id`) REFERENCES `sys_user`(`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='服务站信息表';

```



#### 标签表：yl_tag_management

| 字段名        | 数据类型 | 长度 | 是否为空 | 外键 | 备注              |
| ------------- | -------- | ---- | -------- | ---- | ----------------- |
| `tag_id`      | bigint   |      | 否       |      | 标签 ID，自增主键 |
| `tag_name`    | varchar  | 255  | 否       |      | 标签名称          |
| `remark`      | varchar  | 255  | 是       |      | 备注              |
| `create_time` | datetime |      | 否       |      | 创建时间          |
| `update_time` | datetime |      | 是       |      | 更新时间          |

```sql
DROP TABLE IF EXISTS `yl_tag_management`;
CREATE TABLE `yl_tag_management` (
    `tag_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '标签 ID，自增主键',
    `tag_name` VARCHAR(255) NOT NULL COMMENT '标签名称',
    `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签表';

```



#### 咨询表：yl_consult

| 字段名            | 数据类型 | 长度 | 是否为空 | 外键 | 备注          |
| ----------------- | -------- | ---- | -------- | ---- | ------------- |
| `consult_id`      | bigint   |      | 否       |      | 编号          |
| `consult_title`   | varchar  | 255  | 否       |      | 标题          |
| `consult_type`    | char     | 1    | 否       |      | 咨询类型      |
| `consult_content` | text     |      | 否       |      | 内容          |
| `sorted`          | int      |      | 否       |      | 排序（默认1） |
| `remark`          | varchar  | 255  | 是       |      | 备注          |
| `create_by`       | varchar  | 50   | 否       |      | 创建人        |
| `update_by`       | varchar  | 50   | 是       |      | 修改人        |
| `create_time`     | datetime |      | 否       |      | 创建时间      |
| `del_flag`        | char     | 1    | 否       |      | 删除          |

咨询类型：0公告，1防诈咨询、2通知，3其他等等

排序：排序规则为升序

```sql
DROP TABLE IF EXISTS `yl_consult`;
CREATE TABLE `yl_consult` (
    `consult_id` BIGINT NOT NULL COMMENT '编号',
    `consult_title` VARCHAR(255) NOT NULL COMMENT '标题',
    `consult_type` CHAR(1) NOT NULL COMMENT '咨询类型',
    `consult_content` TEXT NOT NULL COMMENT '内容',
    `sorted` INT NOT NULL DEFAULT 1 COMMENT '排序（默认1）',
    `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `create_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    `update_by` VARCHAR(50) DEFAULT NULL COMMENT '修改人',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `del_flag` CHAR(1) NOT NULL COMMENT '删除',
    PRIMARY KEY (`consult_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='咨询表';

```



#### 健康报告：yl_health_report

| 字段名             | 数据类型 | 长度 | 是否为空 | 外键           | 备注                         |
| ------------------ | -------- | ---- | -------- | -------------- | ---------------------------- |
| `health_report_id` | bigint   |      | 否       |                | 编号                         |
| `report_title`     | varchar  | 255  | 否       |                | 标题                         |
| `report_image`     | varchar  | 1024 | 否       |                | 封面                         |
| `report_content`   | text     |      | 否       |                | 报告内容                     |
| `user_info_id`     | bigint   |      | 否       | `user_info_id` | 长者（用户表：user_info_id） |
| `use_tokens`       | int      |      | 是       |                | tokens                       |
| `remark`           | varchar  | 255  | 是       |                | 备注                         |
| `create_by`        | varchar  | 50   | 否       |                | 创建人                       |
| `create_time`      | datetime |      | 否       |                | 创建时间                     |
| `del_flag`         | char     | 1    | 否       |                | 删除                         |

```sql
DROP TABLE IF EXISTS `yl_health_report`;
CREATE TABLE `yl_health_report` (
    `health_report_id` BIGINT NOT NULL COMMENT '编号',
    `report_title` VARCHAR(255) NOT NULL COMMENT '标题',
    `report_image` VARCHAR(1024) NOT NULL COMMENT '封面',
    `report_content` TEXT NOT NULL COMMENT '报告内容',
    `user_info_id` BIGINT NOT NULL COMMENT '长者（用户表：user_info_id）',
    `use_tokens` INT DEFAULT NULL COMMENT 'tokens',
    `remark` VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `create_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `del_flag` CHAR(1) NOT NULL COMMENT '删除',
    PRIMARY KEY (`health_report_id`),
	FOREIGN KEY (`user_info_id`) REFERENCES `yl_user_info`(`user_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='健康报告表';

```



#### 预约表：ly_appointment

| 字段名                | 数据类型 | 长度 | 是否为空 | 外键         | 备注                       |
| --------------------- | -------- | ---- | -------- | ------------ | -------------------------- |
| `appointment_id`      | bigint   |      | 否       |              | 编号                       |
| `report_title`        | varchar  | 255  | 否       |              | 标题                       |
| `user_info_id`        | bigint   |      | 否       | user_info_id | 用户（用户表user_info_id） |
| `appointment_content` | varchar  | 1024 | 是       |              | 预约内容                   |
| `contact_phone`       | varchar  | 20   | 否       |              | 联系手机                   |
| `appoint_address`     | varchar  | 255  | 是       |              | 预约地址                   |
| `user_id`             | bigint   |      | 否       | user_id      | 预约人（sys_user:user_id） |
| `appointment_time`    | datetime |      | 否       |              | 预约时间                   |
| `appointment_status`  | char     | 1    | 否       |              | 预约状态                   |
| `create_by`           | varchar  | 50   | 否       |              | 创建人                     |
| `create_time`         | datetime |      | 否       |              | 创建时间                   |
| `del_flag`            | char     | 1    | 否       |              | 删除                       |

user_info_id：用户，可以是家属也可以是用户

预约地址：用户可以填写预约地址，防止用户信息表的地址和预约的地址不符合，这里不获取地址表的地址，用户自己填写地址，当处理的时候，预约人需事先打电话去问用户

预约人：系统角色

处理状态：1待处理、2处理完成、3未处理

```sql
DROP TABLE IF EXISTS `yl_appointment`;
CREATE TABLE `yl_appointment` (
    `appointment_id` BIGINT NOT NULL COMMENT '编号',
    `report_title` VARCHAR(255) NOT NULL COMMENT '标题',
    `user_info_id` BIGINT NOT NULL COMMENT '用户（用户表user_info_id）',
    `appointment_content` VARCHAR(1024) DEFAULT NULL COMMENT '预约内容',
    `contact_phone` VARCHAR(20) NOT NULL COMMENT '联系手机',
    `appoint_address` VARCHAR(255) DEFAULT NULL COMMENT '预约地址',
    `user_id` BIGINT NOT NULL COMMENT '预约人（sys_user:user_id）',
    `appointment_time` DATETIME NOT NULL COMMENT '预约时间',
    `appointment_status` CHAR(1) NOT NULL COMMENT '处理状态',
    `create_by` VARCHAR(50) NOT NULL COMMENT '创建人',
    `create_time` DATETIME NOT NULL COMMENT '创建时间',
    `del_flag` CHAR(1) NOT NULL COMMENT '删除',
    PRIMARY KEY (`appointment_id`),
	FOREIGN KEY (`user_info_id`) REFERENCES `yl_user_info`(`user_info_id`),
    FOREIGN KEY (`user_id`) REFERENCES `sys_user`(`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='预约表';

```



#### 预约处理表：yl_appointment_dispose

| 字段名             | 数据类型 | 长度 | 是否为空 | 外键             | 备注                           |
| ------------------ | -------- | ---- | -------- | ---------------- | ------------------------------ |
| `dispose_id`       | bigint   |      | 否       |                  | 编号                           |
| `user_info_id`     | bigint   |      | 否       | `user_info_id`   | 长者（用户表user_info_id）     |
| `appointment_id`   | bigint   |      | 否       | `appointment_id` | 预约（预约表：appointment_id） |
| `dispose_voucher`  | varchar  | 1024 | 否       |                  | 处理凭证                       |
| `dispose_describe` | text     |      | 否       |                  | 处理描述                       |
| `dispose_address`  | bigint   |      | 否       |                  | 处理地址                       |
| `address_detail`   | varchar  | 255  | 是       |                  | 详细地址                       |
| `user_id`          | bigint   |      | 否       | user_id          | 处理人（sys_user:user_id）     |
| `dispose_time`     | datetime |      | 否       |                  | 处理时间                       |
| `dispose_status`   | char     | 1    | 否       |                  | 状态                           |
| `remark`           | varchar  | 255  | 是       |                  | 备注                           |
| `create_by`        | varchar  | 50   | 否       |                  | 创建人                         |
| `create_time`      | datetime |      | 否       |                  | 创建时间                       |
| `del_flag`         | char     | 1    | 否       |                  | 删除                           |

  处理情况：0处理成功、1处理失败

```sql
DROP TABLE IF EXISTS `yl_appointment_dispose`;
CREATE TABLE `yl_appointment_dispose`
(
    `dispose_id`       BIGINT        NOT NULL COMMENT '编号',
    `user_info_id`     BIGINT        NOT NULL COMMENT '长者（用户表user_info_id）',
    `appointment_id`   BIGINT        NOT NULL COMMENT '预约（预约表：appointment_id）',
    `dispose_voucher`  VARCHAR(1024) NOT NULL COMMENT '处理凭证',
    `dispose_describe` TEXT          NOT NULL COMMENT '处理描述',
    `dispose_address`  BIGINT        NOT NULL COMMENT '处理地址',
    `address_detail`   VARCHAR(255) DEFAULT NULL COMMENT '详细地址',
    `user_id`          BIGINT        NOT NULL COMMENT '处理人（sys_user:user_id）',
    `dispose_time`     DATETIME      NOT NULL COMMENT '处理时间',
    `dispose_status`   CHAR(1)       NOT NULL COMMENT '处理状态',
    `remark`           VARCHAR(255) DEFAULT NULL COMMENT '备注',
    `create_by`        VARCHAR(50)   NOT NULL COMMENT '创建人',
    `create_time`      DATETIME      NOT NULL COMMENT '创建时间',
    `del_flag`         CHAR(1)       NOT NULL COMMENT '删除',
    PRIMARY KEY (`dispose_id`),
    FOREIGN KEY (`user_info_id`) REFERENCES `yl_user_info` (`user_info_id`),
        FOREIGN KEY (`user_id`) REFERENCES `sys_user`(`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='预约处理表';
```

#### 所有建表语句





