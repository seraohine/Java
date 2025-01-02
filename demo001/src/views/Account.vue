<script setup>
import {ref} from "vue";
import axios from "axios";


axios.get("http://60.205.214.35:8080/account/queryAccounts")
    .then(res => {
      console.log(res.data);
    });

let num1 = ref(undefined);
let num2 = ref(undefined);
let num3 = ref(undefined);

function cal() {
  num3.value = parseInt(num1.value)+parseInt(num2.value);
  console.log(num3.value)
}
</script>

<template>
  <input type="text" name = "" id ="" v-model="num1">
  <input type="text" name = "" id ="" v-model="num2">
  <input type="button" name = "" id="" @click="cal">
  <input type="text" name="" id="" v-model="num3">
</template>

<style>

</style>