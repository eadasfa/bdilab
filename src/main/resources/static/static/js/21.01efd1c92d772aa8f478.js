webpackJsonp([21],{"+zbD":function(e,t,a){var n=a("zzuL");"string"==typeof n&&(n=[[e.i,n,""]]),n.locals&&(e.exports=n.locals);a("rjj0")("a1235ca4",n,!0)},Iywn:function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-row",{attrs:{gutter:24}},[a("el-col",{attrs:{span:12}},[a("el-col",{attrs:{span:8}},[a("el-input",{attrs:{placeholder:"任务制定人"},model:{value:e.searchForm.taskSettingPerson,callback:function(t){e.$set(e.searchForm,"taskSettingPerson",t)},expression:"searchForm.taskSettingPerson"}})],1),e._v(" "),a("el-col",{attrs:{span:8}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"任务类型",clearable:""},model:{value:e.searchForm.checkTaskType,callback:function(t){e.$set(e.searchForm,"checkTaskType",t)},expression:"searchForm.checkTaskType"}},e._l(e.checkTaskTypeOptions,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1),e._v(" "),a("el-col",{attrs:{span:8}},[a("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"任务状态",clearable:""},model:{value:e.searchForm.taskStatus,callback:function(t){e.$set(e.searchForm,"taskStatus",t)},expression:"searchForm.taskStatus"}},e._l(e.taskStatusOptions,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1)],1),e._v(" "),a("el-col",{attrs:{span:12}},[a("el-button",{attrs:{loading:e.loading,icon:"el-icon-search"},on:{click:e.query}},[e._v("搜索")]),e._v(" "),a("el-button",{staticStyle:{"margin-left":"10px"},attrs:{type:"primary",icon:"el-icon-plus"},on:{click:e.handleCreate}},[e._v("添加")]),e._v(" "),a("el-button",{staticStyle:{"margin-bottom":"20px"},attrs:{type:"warning",icon:"el-icon-download"},on:{click:e.handleDownload}},[e._v("导出excel")])],1)],1),e._v(" "),a("el-table",{attrs:{data:e.inspectionTasks,"element-loading-text":"给我一点时间",border:"",fit:"","highlight-current-row":""}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"70",type:"index"}}),e._v(" "),a("el-table-column",{attrs:{"min-width":"100",align:"center",label:"项目编号"},scopedSlots:e._u([{key:"default",fn:function(t){return[t.row.bridge.project?a("span",[e._v(e._s(t.row.bridge.project.projectNo))]):e._e()]}}])}),e._v(" "),a("el-table-column",{attrs:{"min-width":"100px",align:"center",label:"桥梁编号"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.bridge.bridgeId))])]}}])}),e._v(" "),a("el-table-column",{attrs:{"min-width":"100px",align:"center",label:"桥梁名称"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.bridge.bridgeName))])]}}])}),e._v(" "),a("el-table-column",{attrs:{"min-width":"120px",align:"center",label:"检查任务类型"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.checkTaskType))])]}}])}),e._v(" "),a("el-table-column",{attrs:{"min-width":"160px",align:"center",label:"计划完成时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e._f("dateTimeFormat")(t.row.planCompleteTime)))])]}}])}),e._v(" "),a("el-table-column",{attrs:{"min-width":"160",align:"center",label:"任务制定时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e._f("dateTimeFormat")(t.row.taskSettingTime)))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"任务制定人","min-width":"140px"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.taskSettingPerson))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作",width:"80",fixed:"right"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text"},on:{click:function(a){e.handleUpdate(t.row)}}},[a("i",{staticClass:"el-icon-edit"})]),e._v(" "),a("el-button",{staticStyle:{color:"red"},attrs:{type:"text"},on:{click:function(a){e.openHandleDelete(t.row)}}},[a("i",{staticClass:"el-icon-delete"})])]}}])})],1),e._v(" "),a("el-pagination",{staticStyle:{margin:"20px 0"},attrs:{"current-page":e.inspectionTaskPagination.number,"page-size":e.inspectionTaskPagination.size,total:e.inspectionTaskPagination.totalElements,"page-sizes":[20,50,100],layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.handlePaginationSizeChange,"current-change":e.handleCurrentPageChange}}),e._v(" "),a("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"taskForm",staticClass:"small-space",staticStyle:{width:"400px","margin-left":"50px"},attrs:{model:e.formData,"label-position":"right","label-width":"120px"}},[a("el-form-item",{attrs:{label:"桥梁",prop:"bridge.id",rules:[{required:!0,message:"桥梁不能为空"}]}},[a("el-select",{attrs:{placeholder:"请选择",filterable:""},model:{value:e.formData.bridge.id,callback:function(t){e.$set(e.formData.bridge,"id",t)},expression:"formData.bridge.id"}},e._l(e.menus,function(t){return a("el-option-group",{key:t.id,attrs:{label:t.projectName}},e._l(t.children,function(e){return a("el-option",{key:e.id,attrs:{label:e.bridgeName,value:e.id}})}))}))],1),e._v(" "),a("el-form-item",{attrs:{label:"任务制定人",prop:"taskSettingPerson",rules:[{required:!0,message:"任务制定人不能为空"}]}},[a("el-input",{model:{value:e.formData.taskSettingPerson,callback:function(t){e.$set(e.formData,"taskSettingPerson",t)},expression:"formData.taskSettingPerson"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"任务检查类型",prop:"checkTaskType",rules:[{required:!0,message:"任务检查类型不能为空"}]}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.formData.checkTaskType,callback:function(t){e.$set(e.formData,"checkTaskType",t)},expression:"formData.checkTaskType"}},e._l(e.checkTaskTypeOptions,function(e){return a("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"任务状态",prop:"taskStatus",rules:[{required:!0,message:"任务状态不能为空"}]}},[a("el-select",{attrs:{placeholder:"请选择"},model:{value:e.formData.taskStatus,callback:function(t){e.$set(e.formData,"taskStatus",t)},expression:"formData.taskStatus"}},e._l(e.taskStatusOptions,function(e){return a("el-option",{key:e.value,attrs:{label:e.value,value:e.value}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"计划完成时间",prop:"planCompleteTime",rules:[{required:!0,message:"计划完成时间不能为空"}]}},[a("el-date-picker",{attrs:{type:"date",placeholder:"选择日期",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",editable:""},model:{value:e.formData.planCompleteTime,callback:function(t){e.$set(e.formData,"planCompleteTime",t)},expression:"formData.planCompleteTime"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"任务制定时间",prop:"taskSettingTime",rules:[{required:!0,message:"任务制定时间不能为空"}]}},[a("el-date-picker",{attrs:{type:"date",placeholder:"选择日期",format:"yyyy-MM-dd","value-format":"yyyy-MM-dd",editable:""},model:{value:e.formData.taskSettingTime,callback:function(t){e.$set(e.formData,"taskSettingTime",t)},expression:"formData.taskSettingTime"}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取消")]),e._v(" "),"create"==e.dialogStatus?a("el-button",{attrs:{type:"primary",loading:e.loading},on:{click:e.create}},[e._v("确定")]):a("el-button",{attrs:{type:"primary",loading:e.loading},on:{click:e.update}},[e._v("确定")])],1)],1)],1)},i=[],l={render:n,staticRenderFns:i};t.a=l},XN92:function(e,t,a){"use strict";var n=a("+6Bu"),i=a.n(n),l=a("Dd8w"),o=a.n(l),s=a("NYxO"),r=a("0xDb"),c={id:0,bridge:{id:""},checkTaskType:"日常巡查",planCompleteTime:"",taskSettingTime:"",taskSettingPerson:"",taskStatus:"未完成"},u={page:0,size:20,taskStatus:"未完成",taskSettingPerson:"",checkTaskType:""},p=[{value:"日常巡查",label:"日常巡查"},{value:"经常检查",label:"经常检查"},{value:"定期检查",label:"定期检查"},{value:"专项检查",label:"专项检查"}],d=[{value:"未完成"},{value:"已完成"}];t.a={data:function(){return{loading:!1,dialogFormVisible:!1,dialogStatus:"",textMap:{update:"编辑",create:"创建"},checkTaskTypeOptions:p,taskStatusOptions:d,formData:a.i(r.c)(c),searchForm:a.i(r.c)(u)}},computed:o()({},a.i(s.b)(["inspectionTasks","inspectionTaskPagination","menus"])),mounted:function(){this.query()},methods:o()({},a.i(s.c)(["queryAllInspectionTasks","createInspectionTask","updateInspectionTask","deleteInspectionTask"]),{query:function(){var e=this;this.loading=!0,this.queryAllInspectionTasks(this.searchForm).then(function(){e.loading=!1}).catch(function(t){console.log(t),e.loading=!1})},handleCreate:function(){this.formData=a.i(r.c)(c),this.dialogStatus="create",this.dialogFormVisible=!0},handleUpdate:function(e){console.log("ee",e,a.i(r.a)(e.planCompleteTime)),e.planCompleteTime=a.i(r.a)(e.planCompleteTime),e.taskSettingTime=a.i(r.a)(e.taskSettingTime),this.formData=a.i(r.c)(e),this.dialogStatus="update",this.dialogFormVisible=!0},openHandleDelete:function(e){var t=this;this.$confirm("此操作将永久删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){t.deleteInspectionTask(e).then(function(){a.i(r.d)(),t.query()}).catch(function(e){a.i(r.d)("",{type:"error"})})}).catch(function(){a.i(r.d)("已取消删除",{type:"info"})})},create:function(){var e=this;this.$refs.taskForm.validate(function(t){t&&(e.loading=!0,e.createInspectionTask(e.handledFormData()).then(function(){a.i(r.d)(),e.query(),e.dialogFormVisible=!1}).catch(function(e){a.i(r.d)("",{type:"error"})}).then(function(){e.loading=!1}))})},update:function(){var e=this;this.$refs.taskForm.validate(function(t){t&&(e.loading=!0,e.updateInspectionTask(e.handledFormData()).then(function(){a.i(r.d)(),e.query(),e.dialogFormVisible=!1}).catch(function(e){a.i(r.d)("",{type:"error"})}).then(function(){e.loading=!1}))})},handledFormData:function(){var e=this.formData,t=e.planCompleteTime,a=e.taskSettingTime,n=i()(e,["planCompleteTime","taskSettingTime"]);return o()({planCompleteTime:new Date(t).getTime(),taskSettingTime:new Date(a).getTime()},n)},handleDownload:function(){var e=this;this.$confirm("您确定要导出Excel吗?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then(function(){e.exportExcel(),a.i(r.d)("已成功导出Excel!")}).catch(function(){a.i(r.d)("已取消",{type:"info"})})},exportExcel:function(){var e=this;Promise.all([a.e(36),a.e(35)]).then(function(){(0,a("zWO4").export_json_to_excel)(["任务序号","项目编号","桥梁编号","桥梁名称","检查任务类型","计划完成时间","任务制定时间","任务制定人","任务状态"],e.formatJson(),"制定检查任务单")}.bind(null,a)).catch(a.oe)},formatJson:function(){return this.inspectionTasks.map(function(e){var t=e.id,n=e.bridge,i=e.planCompleteTime,l=e.taskSettingTime,o=e.checkTaskType,s=e.taskSettingPerson,c=e.taskStatus;return[t,n.project.projectNo,n.bridgeId,n.bridgeName,o,a.i(r.a)(i),a.i(r.a)(l),s,c]})},handlePaginationSizeChange:function(e){this.searchForm.size=e,this.query()},handleCurrentPageChange:function(e){this.searchForm.page=e-1,this.query()}})}},ayqX:function(e,t,a){"use strict";function n(e){a("+zbD")}Object.defineProperty(t,"__esModule",{value:!0});var i=a("XN92"),l=a("Iywn"),o=a("VU/8"),s=n,r=o(i.a,l.a,s,"data-v-8be2e64a",null);t.default=r.exports},zzuL:function(e,t,a){t=e.exports=a("FZ+f")(!1),t.push([e.i,"",""])}});