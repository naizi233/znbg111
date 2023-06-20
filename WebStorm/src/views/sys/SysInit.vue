<template>
  <div>
    <el-button
        type="success"
        :disabled="importDataDisabled"
        :icon="importDataBtnIcon"
    >
      {{ importDataBtnText }}</el-button
    >

  </div>
</template>

<script>
export default {
  name: "SysInit",
  data() {
    return {
      importDataBtnText: "导入数据",
      importDataBtnIcon: "el-icon-upload2",
      importDataDisabled: false
    };
  }

};
</script>

<style>
</style>