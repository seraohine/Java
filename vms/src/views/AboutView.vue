<script setup>
import {useRouter} from "vue-router";
import { ref } from 'vue'
const router = useRouter();
const accname = ref("");
accname.value = window.localStorage.getItem("accname");
if(accname.value==undefined || accname.value ==""){
  router.push("/");
}
function logout(){
  accname.value = "";
  window.localStorage.clear();
  router.push("/");
}
</script>



<template>
  <div class="common-layout">
    <el-container>
      <el-header>智能仓储管理平台,欢迎你，{{accname}}<button @click="logout">退出</button></el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu default-active="2" class="el-menu-vertical-demo" router>
            <el-sub-menu index="1">
              <template #title>
                <el-icon>
                  <location />
                </el-icon>
                <span>系统管理</span>
              </template>
              <el-menu-item index="/customer">用户信息管理</el-menu-item>
              <el-menu-item index="/Company">客户公司管理</el-menu-item>
              <!-- <el-sub-menu index="1-4">
            <template #title>
              item four
            </template>
            <el-menu-item index="1-4-1">item one</el-menu-item>
          </el-sub-menu> -->
            </el-sub-menu>

            <el-sub-menu index="2">
              <template #title>
                <el-icon>
                  <location />
                </el-icon>
                <span>物料管理</span>
              </template>
              <el-menu-item index="/good">商品管理</el-menu-item>
              <el-menu-item index="1-1">仓库管理</el-menu-item>
            </el-sub-menu>
            <!-- <el-menu-item index="3" disabled>
          <el-icon><document /></el-icon>
          <span>Navigator Three</span>
        </el-menu-item> -->
          </el-menu>
        </el-aside>
        <el-main>
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
.common-layout {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
}

.el-container {
  height: 100%;
}

.el-header,
.el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}
</style>
