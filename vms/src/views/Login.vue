<script setup>
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()
const loginFormRef = ref(null);

const loginRules = ref({
  accname: [
    { required: true, message: "please enter your account", trigger: 'blur' }
  ],
  accpass: [
    { required: true, message: "please enter your password", trigger: 'blur' }
  ]
});

const form = ref({
  accname: '',
  accpass: '',
})

const onSubmit = () => {
    console.log(form.value);

    loginFormRef.value.validate((valid) => {
      if (valid) {
        axios
          .post("http://127.0.0.1:8080/account/login", form.value)
          .then((response) => {
            console.log(response.data);
            if (response.data.code == 200) {
                ElMessage({
                    message: '登录成功',
                    type: 'success'
                })
                router.push('/about');
            } else {
                ElMessage.error('登录失败');
            }
          })
          .catch((error) => {
            ElMessage.error('请求失败');
            console.log(error);
          });
      }
    });
};
</script>

<template>

  <el-form
    ref="loginFormRef"
    :rules="loginRules"
    :model="form"
    label-width="auto"
    style="max-width: 600px"
  >
    <el-form-item label="登录账号" prop="accname">
      <el-input
        v-model="form.accname"
        style="width: 180px"
        placeholder="请输入用户名"
      />
    </el-form-item>
    <el-form-item label="登录密码" prop="accpass">
      <el-input
        v-model="form.accpass"
        style="width: 180px"
        placeholder="请输入密码"
        show-password
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">登录</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
</template>

