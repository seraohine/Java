<template>
  <div class="container">
    <!-- 背景层 -->
    <div class="background"></div>

    <!-- 登录表单 -->
    <div class="front">
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
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const loginFormRef = ref(null);

// 登录验证规则
const loginRules = ref({
  accname: [{ required: true, message: "请输入用户名", trigger: 'blur' }],
  accpass: [{ required: true, message: "请输入密码", trigger: 'blur' }],
});

// 登录表单数据
const form = ref({
  accname: '',
  accpass: '',
});

// 表单提交逻辑
const onSubmit = () => {
  console.log(form.value);

  loginFormRef.value.validate((valid) => {
    if (valid) {
      axios
        .post("http://127.0.0.1:8080/account/login", form.value)
        .then((response) => {
          console.log(response.data);
          if (response.data.code === 200) {
            ElMessage({
              message: '登录成功',
              type: 'success',
            });
            window.localStorage.setItem("accname",form.value.accname);
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

<style scoped>
/* 页面容器 */
.container {
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  overflow: hidden;
}

/* 背景层样式 */
.background {
  width: 100%;
  height: 100%;
  position: fixed; 
  top: 0;
  left: 0;
  background: url('/images/miside.jpg') no-repeat; 
  background-size: cover; /* 背景图片覆盖 */
  background-position: center; /* 居中显示 */
  z-index: -1; /* 确保背景层在表单之下 */
  pointer-events: none; /* 禁止背景层拦截鼠标事件 */
}

/* 登录表单层样式 */
.front {
  z-index: 1; /* 保证表单在背景层之上 */
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: rgba(255, 255, 255, 0.8); /* 半透明白色背景 */
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}
</style>
