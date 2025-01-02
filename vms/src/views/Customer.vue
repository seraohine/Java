<script setup>
import { ref } from 'vue'
import axios from 'axios';
import { EditPen, Delete } from '@element-plus/icons-vue';
import { ElMessage, ElMessageBox } from 'element-plus';
import 'element-plus/dist/index.css';
import { Lock, Unlock } from '@element-plus/icons-vue';

const  loginFormRef = ref(null);

const loginRules = ref({
  accname: [
    { required: true, message: "please enter your account", trigger: 'blur' }
  ],
  accpass: [
    { required: true, message: "please enter your password", trigger: 'blur' }
  ]
});

const title = ref('添加用户信息')
const tableData = ref([]);
const dialogFormVisible = ref(false);
const formLabelWidth = "140px";
const form = ref({
  accid: undefined,
  accname: undefined,
  accpass: undefined,
  accstate: undefined,
});

queryAccount();

function queryAccount() {
  // axios.get('http://60.205.214.35:8080/account/queryAccounts').then(res => {
  axios.get('http://127.0.0.1:8080/account/queryAccounts').then(res => {
    tableData.value = res.data.data;
  });
}

function handleChangeState(accid,accstate){

  let message = (accstate == "1"?"禁用成功":"解禁成功");

  axios
      .put('http://127.0.0.1:8080/account/changeState/'+accid+"/"+accstate)
      .then((res) => {
        if (res.data.code == 1000) {
          ElMessage({
            message:message,
            type:"success",
          });
          queryAccount();
        }else{
          ElMessage.error(response.data.message);
        }
      }) 
}

function handleDelete(accid) {
  ElMessageBox.confirm('确认删除该数据?', 'Warnning', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    axios
      .delete('http://127.0.0.1:8080/account/deleteAccounts/' + accid)
      .then((res) => {
        if (res.data.code == 1000) {
          ElMessage.success('删除成功');
          this.queryAccount();
        } else {
          ElMessage.error('删除失败');
        }
      })
  })
    .catch(() => {
      ElMessage.info('已取消删除');
    });
}

function modifyAccount() {
  loginFormRef.value.validate((valid) => {
    if (!valid) {
      ElMessage.error('表单验证失败，请检查输入内容');
      return; // 验证失败，终止后续操作
    }

    if (form.value.accid == undefined) {
      axios
        .post('http://127.0.0.1:8080/account/saveAccount', form.value)
        .then((res) => {
          if (res.data.code == 1000) {
            ElMessage.success('添加成功');
            queryAccount();
            dialogFormVisible.value = false;
          } else {
            ElMessage.error(res.data.message);
          }
        });
    } else {
      axios
        .put('http://127.0.0.1:8080/account/abc', form.value)
        .then((res) => {
          if (res.data.code == 1000) {
            ElMessage.success('修改成功');
            form.value = {};
            queryAccount();
            dialogFormVisible.value = false;
            title.value = '添加用户信息';
          } else {
            ElMessage.error('账号重复,添加失败');
          }
        });
    }
  });
}


function handleEdit(accid) {
  axios
    .get('http://127.0.0.1:8080/account/queryById/' + accid)
    .then((res) => {
      dialogFormVisible.value = true;
      form.value = res.data.data;
      title.value = '修改用户信息';
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
      添加用户信息
    </el-button>
    <el-dialog v-model="dialogFormVisible" :title="title" width="500">
    <el-form
    ref="loginFormRef"
    :rules="loginRules"
    :model="form"
  >

        <el-form-item label="账户名称" :label-width="formLabelWidth" prop = "accname">
          <el-input v-model="form.accname" autocomplete="off" placeholder="请输入账户名称" />
        </el-form-item>

        <el-form-item label="账户密码" :label-width="formLabelWidth" prop = "accpass">
          <el-input v-model="form.accpass" placeholder="请输入账户密码" />
        </el-form-item>

      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogFormVisible = false">
            取消
          </el-button>
          <el-button type="primary" @click="modifyAccount">
            提交
          </el-button>
        </div>
      </template>
    </el-dialog>

    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="accid" label="编号" width="180" />
      <el-table-column prop="accname" label="账号名称" width="180" />
      <el-table-column prop="accpass" label="密码" />
      <el-table-column prop="accstate" label="状态">
        <template #default="{ row }">
          <el-tag :type="row.accstate == 1 ? 'success' : 'danger'">
            {{ row.accstate == 1 ? '启用' : '禁用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="small" @click="handleEdit(scope.row.accid)">
            <el-icon>
              <EditPen />
            </el-icon>
          </el-button>
          <el-button size="small" @click="handleDelete(scope.row.accid)">
            <el-icon>
              <Delete />
            </el-icon>
          </el-button>
          

          
          <el-button size="small" @click="handleChangeState(scope.row.accid,scope.row.accstate)">
            <el-icon v-if="scope.row.accstate == 1"><Lock /></el-icon>
            <el-icon v-if="scope.row.accstate == 0"><Unlock /></el-icon>
          </el-button> 

        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
