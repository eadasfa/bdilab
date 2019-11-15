webpackJsonp([29],{KdUT:function(t,e,a){"use strict";var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"app-container"},[a("el-row",{attrs:{gutter:24}},[a("el-col",{attrs:{span:4}},[a("el-input",{attrs:{placeholder:"部门名称"},model:{value:t.searchForm.departName,callback:function(e){t.$set(t.searchForm,"departName",e)},expression:"searchForm.departName"}})],1),t._v(" "),a("el-col",{attrs:{span:2}},[a("el-button",{attrs:{icon:"el-icon-search",loading:t.loading},on:{click:t.query}},[t._v("搜索")])],1),t._v(" "),a("el-col",{attrs:{span:2}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-plus"},on:{click:t.handleCreate}},[t._v("添加")])],1),t._v(" "),a("el-col",{attrs:{span:2}},[a("el-button",{attrs:{type:"warning",icon:"el-icon-download"},on:{click:t.handleDownload}},[t._v("导出")])],1)],1),t._v(" "),a("el-table",{staticStyle:{"margin-top":"20px"},attrs:{border:"",fit:"","highlight-current-row":"",data:t.departs,"element-loading-text":"加载中..."}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"100",type:"index"}}),t._v(" "),a("el-table-column",{attrs:{"min-width":"80px",align:"center",label:"单位名称"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(t.companys.filter(function(t){return t.id===e.row.companyId})[0].companyName||""))])]}}])}),t._v(" "),a("el-table-column",{attrs:{"min-width":"80px",align:"center",label:"部门名称"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.departName))])]}}])}),t._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作",width:"80",fixed:"right"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"text"},on:{click:function(a){t.handleUpdate(e.row)}}},[a("i",{staticClass:"el-icon-edit"})]),t._v(" "),a("el-button",{staticStyle:{color:"red"},attrs:{type:"text"},on:{click:function(a){t.handleDelete(e.row)}}},[a("i",{staticClass:"el-icon-delete"})])]}}])})],1),t._v(" "),a("el-pagination",{staticStyle:{margin:"20px 0"},attrs:{"current-page":t.departsPagination.number,"page-size":t.departsPagination.size,total:t.departsPagination.totalElements,"page-sizes":[20,50,100],layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":t.handlePaginationSizeChange,"current-change":t.handleCurrentPageChange}}),t._v(" "),a("el-dialog",{attrs:{title:t.textMap[t.dialogStatus],visible:t.dialogFormVisible},on:{"update:visible":function(e){t.dialogFormVisible=e}}},[a("el-form",{ref:"departForm",staticClass:"small-space",staticStyle:{width:"400px","margin-left":"50px"},attrs:{model:t.formData,"label-position":"right","label-width":"110px"}},[a("el-form-item",{attrs:{label:"所属单位",prop:"companyId"}},[a("el-select",{staticStyle:{width:"100%"},attrs:{disabled:t.companySelectIsDisabled},model:{value:t.formData.companyId,callback:function(e){t.$set(t.formData,"companyId",e)},expression:"formData.companyId"}},t._l(t.companys,function(t){return a("el-option",{key:t.id,attrs:{value:t.id,label:t.companyName}})}))],1),t._v(" "),a("el-form-item",{attrs:{label:"部门名称",prop:"departName",rules:[{required:!0,message:"部门名称不能为空"}]}},[a("el-input",{model:{value:t.formData.departName,callback:function(e){t.$set(t.formData,"departName",e)},expression:"formData.departName"}})],1)],1),t._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogFormVisible=!1}}},[t._v("取消")]),t._v(" "),"create"==t.dialogStatus?a("el-button",{attrs:{type:"primary",loading:t.loading},on:{click:t.create}},[t._v("确定")]):a("el-button",{attrs:{type:"primary",loading:t.loading},on:{click:t.update}},[t._v("确定")])],1)],1)],1)},i=[],o={render:n,staticRenderFns:i};e.a=o},UuNf:function(t,e,a){"use strict";var n=a("Dd8w"),i=a.n(n),o=a("NYxO"),r=a("0xDb"),l={id:0,departName:"",companyId:""},s={page:0,size:20,sort:"id,desc",projection:"inline",departName:""};e.a={data:function(){return{loading:!1,dialogFormVisible:!1,companySelectIsDisabled:!0,dialogStatus:"",textMap:{update:"编辑",create:"创建"},formData:a.i(r.c)(l),searchForm:a.i(r.c)(s)}},computed:i()({},a.i(o.b)(["departs","departsPagination","loginUser","companys"])),mounted:function(){this.query(),this.handleCompanyNameChange()},methods:i()({},a.i(o.c)(["queryAllDeparts","queryAllCompanys","createDepart","updateDepart","deleteDepart"]),{query:function(){var t=this;this.loading=!0,this.queryAllDeparts(this.searchForm).then(function(){t.loading=!1}).catch(function(e){t.loading=!1})},handleCompanyNameChange:function(){var t=this;this.queryAllCompanys(this.searchForm).then(function(){t.loading=!1}).catch(function(e){t.loading=!1})},handleCreate:function(){this.dialogStatus="create";var t=this.loginUser,e=t.roleManager,n=t.company;this.formData=a.i(r.c)(l),"ADMIN"===e?this.companySelectIsDisabled=!1:this.formData=i()({},this.formData,{companyId:n.id}),this.dialogFormVisible=!0},handleUpdate:function(t){this.formData=a.i(r.c)(t),this.dialogStatus="update","ADMIN"===this.loginUser.roleManager&&(this.companySelectIsDisabled=!1),this.dialogFormVisible=!0},handleDelete:function(t){var e=this;this.$confirm("确定删除此部门吗？","提示",{confirmButtonText:"确认",cancelButtonText:"取消",type:"error"}).then(function(){e.deleteDepart(t).then(function(){a.i(r.d)(),e.query()}).catch(function(t){a.i(r.d)("",{type:"error"})})}).catch(function(){})},create:function(){var t=this;this.$refs.departForm.validate(function(e){e&&(t.loading=!0,t.createDepart(t.formData).then(function(){t.dialogFormVisible=!1,t.loading=!1,t.companySelectIsDisabled=!0,a.i(r.d)(),t.query(),t.handleCompanyNameChange()}).catch(function(e){a.i(r.d)("",{type:"error"}),t.loading=!1}))})},update:function(){var t=this;this.$refs.departForm.validate(function(e){e&&(t.loading=!0,t.updateDepart(t.formData).then(function(){t.dialogFormVisible=!1,a.i(r.d)(),t.query(),t.companySelectIsDisabled=!0,t.handleCompanyNameChange(),t.loading=!1}).catch(function(e){a.i(r.d)("",{type:"error"}),t.loading=!1}))})},handlePaginationSizeChange:function(t){this.searchForm.size=t,this.query()},handleCurrentPageChange:function(t){this.searchForm.page=t-1,this.query()},handleDownload:function(){var t=this;Promise.all([a.e(36),a.e(35)]).then(function(){(0,a("zWO4").export_json_to_excel)(["序号","部门名称"],t.formatJson(),"部门管理数据")}.bind(null,a)).catch(a.oe)},formatJson:function(){return this.departs.map(function(t){return[t.id,t.departName]})}})}},h6U0:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var n=a("UuNf"),i=a("KdUT"),o=a("VU/8"),r=o(n.a,i.a,null,null,null);e.default=r.exports}});