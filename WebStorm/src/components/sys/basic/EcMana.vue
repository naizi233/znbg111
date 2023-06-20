<template>
  <div>
	  <el-collapse v-model="activeName" accordion>
	    <el-collapse-item title="考勤管理规定" name="1">
	      <div>第一条 为加强公司职工考勤管理，特制定本规定。</div>
	      <div>第二条 本规定适用于公司总部，各下属全资或控股企业或参照执行或另行规定，各企业自定的考勤管理规定须由总公司规范化管理委员会审核签发。</div>
		  <div>第三条 员工正常工作时间为上午8时30分至10时，下午1时30分至5时，每周六下午不上班，因季节变化需调整工作时间时由总裁办公室另行通知。</div>
		  <div>第四条 公司职工一律实行上下班打卡登记制度。</div>
	    </el-collapse-item>
	    <el-collapse-item title="值勤细则" name="2">
	      <div>(一)本公司值勤分为值日与值夜两种，值日又分为星期例假值日与午休值班，悉依照本规则办理。</div>
	      <div>(二)本公司值日每次由职员一人，轮流值勤，值勤顺序每月由总务组揭示施行，但星期例假值日及午休值班，副经理以上人员均免值勤。</div>
		  <div>(三)星期例假值日时间，自上午9时起至下午17时止，午休值班时间以其中午休息时间为限。值夜时间以当日下午下班后至隔日上午上班前以内的时间为限，但需侯接替人接值后始可离值。</div>
		  <div>(四)值勤人员不得推诿免值或擅离职守，如因公出差或因病及其他事故，确实不能到值者，应事先自行委托本公司同职的人员代值，并向总务组报备，其一切责任则由代值人员负担。</div>
	    </el-collapse-item>
	  </el-collapse>
  </div>
</template>

<script>
export default {
  name: "EcMana",
   data() {
        return {
          activeName: '1'
        };
      }
};
</script>

<style>
</style>