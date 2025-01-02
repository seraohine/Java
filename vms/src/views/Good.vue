<script setup>
import { ref } from 'vue';
import axios from "axios";
import { ElMessage, ElMessageBox } from "element-plus";

// 自定义验证器：确保输入是正数
const validatePositiveNumber = (rule, value, callback) => {
  if (!value) {
    callback(new Error("This field is required"));
  } else if (isNaN(value) || value <= 0) { 
    callback(new Error("Please enter a positive number"));
  } else {
    callback();
  }
};

const loginFormRef = ref(null);

// 表单验证规则
const loginRules = ref({
  pname: [
    { required: true, message: "Please enter product name", trigger: 'blur' }
  ],
  unit: [
    { required: true, message: "Please enter unit", trigger: 'blur' }
  ],
  weight: [
    { required: true, message: "Please enter weight", trigger: 'blur' },
    { validator: validatePositiveNumber, trigger: 'blur' }
  ],
  volume: [
    { required: true, message: "Please enter volume", trigger: 'blur' },
    { validator: validatePositiveNumber, trigger: 'blur' }
  ]
});

// 商品信息
const tableData = ref([]);
const companysData = ref([]);
const title = ref("添加商品");
const dialogFormVisible = ref(false);
const formLabelWidth = "140px";
const formInline = ref({
    pname: undefined,
    unit: undefined,
    companyId: undefined,
});
const form = ref({
    pname: undefined,
    unit: undefined,
    weight: undefined,
    volume: undefined,
    companyId: undefined,
    id: undefined,
});

// 查询所有商品和公司
queryProducts();
queryCompanys();

function queryProducts() {
  axios.post("http://127.0.0.1:8080/product/queryProducts", formInline.value)
    .then((res) => {
        tableData.value = res.data.data;
    });
}

function queryCompanys() {
  axios.get("http://127.0.0.1:8080/company/queryCompanys", formInline.value)
    .then((res) => {
        companysData.value = res.data.data;
    });
}

// 提交商品信息（添加/修改）
function modifyGood() {
  loginFormRef.value.validate((valid) => {
    if (!valid) {
      ElMessage.error('表单验证失败，请检查输入内容');
      return; // 验证失败，终止后续操作
    }

    if (form.value.id == undefined) {
      // 添加商品
      axios.post('http://127.0.0.1:8080/product/saveProduct', form.value)
        .then((res) => {
          if (res.data.code == 200) {
            ElMessage.success('添加成功');
            form.value = {}; // 清空表单
            queryProducts(); // 刷新商品列表
            dialogFormVisible.value = false; // 关闭对话框
          } else {
            ElMessage.error('添加失败');
          }
        });
    } else {
      // 更新商品
      axios.put('http://127.0.0.1:8080/product/updateProduct', form.value)
        .then((res) => {
          if (res.data.code == 200) {
            ElMessage.success('修改成功');
            form.value = {}; // 清空表单
            queryProducts(); // 刷新商品列表
            dialogFormVisible.value = false; // 关闭对话框
            title.value = '添加商品'; // 重置标题
          } else {
            ElMessage.error(res.data.message || '修改失败');
          }
        });
    }
  });
}

// 编辑商品信息
function handleEdit(id) {
  axios.get('http://127.0.0.1:8080/product/queryById/' + id)
    .then((res) => {
        dialogFormVisible.value = true;
        form.value = res.data.data;
        title.value = '修改商品';
    })
    .catch((err) => {
        ElMessage.error('修改失败');
    });
}

// 删除商品
function handleDelete(id) {
  ElMessageBox.confirm('此操作将永久删除该商品, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    axios.delete('http://127.0.0.1:8080/product/deleteProduct/' + id)
      .then((res) => {
        if (res.data.code == 200) {
          ElMessage.success('删除成功');
          queryProducts();
        } else {
          ElMessage.error('删除失败');
        }
      });
  });
}

// 处理负数输入，确保输入为正数
const handlePositiveNumberInput = (field) => {
  if (form.value[field] && form.value[field] < 0) {
    form.value[field] = ''; // 清空负数输入
    ElMessage.error('输入值必须为正数');
  }
};
</script>



<template>
    <div>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="物料名称">
                <el-input v-model="formInline.pname" placeholder="请输入物料名称" clearable />
            </el-form-item>
            <el-form-item label="物料单位">
                <el-input v-model="formInline.unit" placeholder="请输入物料单位" clearable />
            </el-form-item>
            <el-form-item label="客户公司名称">
                <el-select v-model="formInline.companyId" placeholder="请选择公司名称" size="large" style="width: 240px">
                    <el-option label="" value="" />
                    <el-option v-for="company in companysData" :key="company.id" :label="company.comName"
                        :value="company.id" />
                </el-select>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="queryProducts">查询</el-button>
                <el-button type="primary" @click="dialogFormVisible = true">添加物料</el-button>
            </el-form-item>
        </el-form>

        <!-- 修改对话框 -->
        <el-dialog v-model="dialogFormVisible" :title="title" width="500">
        <el-form
        ref="loginFormRef"
        :rules="loginRules"
        :model="form"
        >
                <el-form-item label="客户公司名称" :label-width="formLabelWidth">
                    <el-select v-model="form.companyId" autocomplete="off" placeholder="请选择公司名称" size="large"
                        style="width: 240px;">
                        <el-option v-for="company in companysData" :key="company.id" :label="company.comName"
                            :value="company.id" />
                    </el-select>
                </el-form-item>

                <el-form-item label="商品名称" :label-width="formLabelWidth" prop = "pname">
                    <el-input v-model="form.pname" placeholder="请输入商品名称" />
                </el-form-item>

                <el-form-item label="单位" :label-width="formLabelWidth" prop="unit">
                    <el-input v-model="form.unit" placeholder="请输入单位" />
                </el-form-item>

                <el-form-item label="重量" :label-width="formLabelWidth" prop="weight">
                    <el-input type = "number" v-model="form.weight" placeholder="请输入重量" />
                </el-form-item>

                <el-form-item label="体积" :label-width="formLabelWidth" prop="volume">
                    <el-input type = "number" v-model="form.volume" autocomplete="off" placeholder="请输入体积" />
                </el-form-item>

            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">
                        取消
                    </el-button>
                    <el-button type="primary" @click="modifyGood">
                        提交
                    </el-button>
                </span>
            </template>
        </el-dialog>

        <el-table :data="tableData" border style="width: 100%">
            <el-table-column prop="id" label="编号" width="180" />
            <el-table-column prop="comName" label="客户公司名称" />
            <el-table-column prop="pname" label="物料名称" width="180" />
            <el-table-column prop="unit" label="单位" />
            <el-table-column prop="weight" label="重量" />
            <el-table-column prop="volume" label="体积" />
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button size="small" @click="handleEdit(scope.row.id)">编辑</el-button>
                    <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

    </div>
</template>
