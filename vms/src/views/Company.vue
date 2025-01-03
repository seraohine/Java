<script setup>
import { ref } from 'vue'
import axios from 'axios';
import { EditPen, Delete } from '@element-plus/icons-vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import 'element-plus/dist/index.css';
import { Lock, Unlock } from '@element-plus/icons-vue';

const  loginFormRef = ref(null);

const loginRules = ref({
  comName: [
    { required: true, message: "please enter your company", trigger: 'blur' }
  ],
  comAddress: [
    { required: true, message: "please enter your password", trigger: 'blur' }
  ],
  comName: [
    { required: true, message: "please enter your company", trigger: 'blur' }
  ],
});

const title = ref('添加客户公司信息')
const tableData = ref([]);
const dialogFormVisible = ref(false);
const formLabelWidth = "140px";      
const form = ref({
  id: undefined,
  comName: undefined,
  comAddress: undefined,
  comTel: undefined,
});

queryCompanys();

function queryCompanys() {
  // axios.get('http://60.205.214.35:8080/company/queryCompanys').then(res => {
  axios.get('http://127.0.0.1:8080/company/queryCompanys').then(res => {
    tableData.value = res.data.data;
  });
}

function handleDelete(id) {
  ElMessageBox.confirm('确认删除该数据?', 'Warnning', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    axios
      .delete('http://127.0.0.1:8080/company/deleteCompany/' + id)
      .then((res) => {
        if (res.data.code == 1000) {
          ElMessage.success('删除成功');
          this.queryCompanys();
        } else {
          ElMessage.error('删除失败');
        }
      })
  })
    .catch(() => {
      ElMessage.info('已取消删除');
    });
}

function modifycompany() {
  loginFormRef.value.validate((valid) => {
    if (!valid) {
      ElMessage.error('表单验证失败，请检查输入内容');
      return; // 验证失败，终止后续操作
    }

    if (form.value.id == undefined) {
      axios
        .post('http://127.0.0.1:8080/company/saveCompany', form.value)
        .then((res) => {
          if (res.data.code == 1000) {
            ElMessage.success('添加成功');
            queryCompanys();
            dialogFormVisible.value = false;
          } else {
            ElMessage.error(res.data.message);
          }
        });
    } else {
      axios
        .put('http://127.0.0.1:8080/company/updateCompany', form.value)
        .then((res) => {
          if (res.data.code == 1000) {
            ElMessage.success('修改成功');
            form.value = {};
            queryCompanys();
            dialogFormVisible.value = false;
            title.value = '添加客户公司信息';
          } else {
            ElMessage.error('公司重复,添加失败');
          }
        });
    }
  });
}


function handleEdit(id) {
  axios
    .get('http://127.0.0.1:8080/company/queryById/' + id)        /* ????????? */
    .then((res) => {
      dialogFormVisible.value = true;
      form.value = res.data.data;
      title.value = '修改客户公司信息';
    })
    .catch((err) => {
      ElMessage.error(response.data.message);
    })
}

</script>

<template>
  <div>
    <!-- 添加用户信息按钮 -->
    <el-button plain @click="dialogFormVisible = true">
      添加公司信息
    </el-button>
    <el-dialog v-model="dialogFormVisible" :title="title" width="500">
    <el-form
    ref="loginFormRef"
    :rules="loginRules"
    :model="form"
  >

        <el-form-item label="公司名称" :label-width="formLabelWidth" prop = "comName">
          <el-input v-model="form.comName" autocomplete="off" placeholder="请输入客户公司名称" />
        </el-form-item>

        <el-form-item label="公司地址" :label-width="formLabelWidth" prop = "comAddress">
          <el-input v-model="form.comAddress" autocomplete="off" placeholder="请输入客户公司地址" />
        </el-form-item>

        <el-form-item label="联系方式" :label-width="formLabelWidth" prop = "comTel">
          <el-input v-model="form.comTel" autocomplete="off" placeholder="请输入联系方式" />
        </el-form-item>


      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogFormVisible = false">
            取消
          </el-button>
          <el-button type="primary" @click="modifycompany">
            提交
          </el-button>
        </div>
      </template>
    </el-dialog>

    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="id" label="编号" width="180" />
      <el-table-column prop="comName" label="公司名称" width="180" />
      <el-table-column prop="comAddress" label="公司地址" />
      <el-table-column prop="comTel" label="联系方式">
      </el-table-column>

      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row.id)">
            <el-icon>
              <EditPen />
            </el-icon>
          </el-button>
          <el-button size="small" @click="handleDelete(scope.row.id)">
            <el-icon>
              <Delete />
            </el-icon>
          </el-button>

        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
