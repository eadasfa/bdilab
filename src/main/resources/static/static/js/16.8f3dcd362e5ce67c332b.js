webpackJsonp([16],{CHDj:function(e,r,t){"use strict";Object.defineProperty(r,"__esModule",{value:!0});var l={data:function(){return{ruleForm:{oldPwd:"",newPwd:"",newPwdConfirm:""},rules:{oldPwd:[{required:!0,message:"原密码不能为空",trigger:"blur"}],newPwd:[{required:!0,message:"新密码不能为空",trigger:"blur"}],newPwdConfirm:[{required:!0,message:"确认密码不能为空",trigger:"blur"}]}}},methods:{submitForm:function(e){var r=this;this.$refs[e].validate(function(e){if(!e)return console.log("error submit!!"),!1;localStorage.setItem("ms_username",r.ruleForm.username)});var t=this.ruleForm;t.newPwd,t.oldPwd;this.$router.push("/")}}},o={render:function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("div",{staticClass:"login-wrap"},[t("div",{staticClass:"ms-login"},[t("div",{staticClass:"ms-title"},[e._v("修改密码")]),e._v(" "),t("el-form",{ref:"ruleForm",staticClass:"ms-content",attrs:{model:e.ruleForm,rules:e.rules,"label-width":"0px"}},[t("el-form-item",{attrs:{prop:"oldPwd"}},[t("el-input",{attrs:{placeholder:"输入原来的密码"},model:{value:e.ruleForm.oldPwd,callback:function(r){e.$set(e.ruleForm,"oldPwd",r)},expression:"ruleForm.oldPwd"}})],1),e._v(" "),t("el-form-item",{attrs:{prop:"newPwd"}},[t("el-input",{attrs:{placeholder:"输入新密码"},model:{value:e.ruleForm.newPwd,callback:function(r){e.$set(e.ruleForm,"newPwd",r)},expression:"ruleForm.newPwd"}})],1),e._v(" "),t("el-form-item",{attrs:{prop:"newPwdConfirm"}},[t("el-input",{attrs:{placeholder:"再次输入新密码"},model:{value:e.ruleForm.newPwdConfirm,callback:function(r){e.$set(e.ruleForm,"newPwdConfirm",r)},expression:"ruleForm.newPwdConfirm"}})],1),e._v(" "),t("div",{staticClass:"login-btn"},[t("el-button",{attrs:{type:"primary"},on:{click:function(r){e.submitForm("ruleForm")}}},[e._v("确认")])],1)],1)],1)])},staticRenderFns:[]};var s=t("VU/8")(l,o,!1,function(e){t("es+C")},"data-v-5a218c1a",null);r.default=s.exports},"es+C":function(e,r){}});