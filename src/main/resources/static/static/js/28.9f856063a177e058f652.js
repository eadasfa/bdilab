webpackJsonp([28],{"24+z":function(e,t,a){"use strict";var n=a("Dd8w"),o=a.n(n),l=a("NYxO"),r=a("0xDb"),i={id:0,companyName:"",companyType:"",companyNo:"",companyConnector:"",companyAddress:"",companyTelephone:"",priority:1,createTime:(new Date).getTime()},c=[{value:"管养单位"},{value:"监督单位"},{value:"检查单位"},{value:"设计单位"}],s={page:0,size:20,sort:"id,desc",companyName:"",companyNo:"",companyConnector:""};t.a={data:function(){return{loading:!1,companyTypeOptions:c,dialogFormVisible:!1,dialogStatus:"",textMap:{update:"编辑",create:"创建"},formData:a.i(r.c)(i),searchForm:a.i(r.c)(s)}},computed:o()({},a.i(l.b)(["companys","companysPagination"])),mounted:function(){this.query()},methods:o()({},a.i(l.c)(["queryAllCompanys","createCompany","updateCompany","deleteCompany"]),{query:function(){var e=this;this.loading=!0,this.queryAllCompanys(this.searchForm).then(function(){e.loading=!1}).catch(function(t){e.loading=!1})},handleCreate:function(){this.formData=a.i(r.c)(i),this.dialogStatus="create",this.dialogFormVisible=!0},handleUpdate:function(e){this.formData=a.i(r.c)(e),this.dialogStatus="update",this.dialogFormVisible=!0},handleDelete:function(e){var t=this;this.$confirm("确定删除此单位吗？","提示",{confirmButtonText:"确认",cancelButtonText:"取消",type:"error"}).then(function(){t.deleteCompany(e).then(function(){a.i(r.d)(),t.query()}).catch(function(e){a.i(r.d)("",{type:"error"})})}).catch(function(){})},create:function(){var e=this;this.$refs.companyForm.validate(function(t){t&&(e.loading=!0,e.createCompany(e.formData).then(function(){e.dialogFormVisible=!1,e.loading=!1,a.i(r.d)(),e.query()}).catch(function(t){a.i(r.d)("",{type:"error"}),e.loading=!1}))})},update:function(){var e=this;this.$refs.companyForm.validate(function(t){t&&(e.loading=!0,e.updateCompany(e.formData).then(function(){e.dialogFormVisible=!1,a.i(r.d)(),e.query(),e.loading=!1}).catch(function(t){a.i(r.d)("",{type:"error"}),e.loading=!1}))})},handlePaginationSizeChange:function(e){this.searchForm.size=e,this.query()},handleCurrentPageChange:function(e){this.searchForm.page=e-1,this.query()},handleDownload:function(){var e=this;Promise.all([a.e(36),a.e(35)]).then(function(){(0,a("zWO4").export_json_to_excel)(["序号","单位名称","单位类型","单位编号","单位地址","联系人","联系电话","注册日期"],e.formatJson(),"单位管理数据")}.bind(null,a)).catch(a.oe)},formatJson:function(){return this.companys.map(function(e){var t=e.id,n=e.companyName,o=e.companyType,l=e.companyNo,i=e.companyAddress,c=e.companyConnector,s=e.companyTelephone,p=e.createTime;return[t,n,o,l,i,c,s,p,a.i(r.a)(p)]})}})}},"61SF":function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"app-container"},[a("el-row",{attrs:{gutter:24}},[a("el-col",{attrs:{span:4}},[a("el-input",{attrs:{placeholder:"单位名称"},model:{value:e.searchForm.companyName,callback:function(t){e.$set(e.searchForm,"companyName",t)},expression:"searchForm.companyName"}})],1),e._v(" "),a("el-col",{attrs:{span:4}},[a("el-input",{attrs:{placeholder:"单位编号"},model:{value:e.searchForm.companyNo,callback:function(t){e.$set(e.searchForm,"companyNo",t)},expression:"searchForm.companyNo"}})],1),e._v(" "),a("el-col",{attrs:{span:4}},[a("el-input",{attrs:{placeholder:"联系人"},model:{value:e.searchForm.companyConnector,callback:function(t){e.$set(e.searchForm,"companyConnector",t)},expression:"searchForm.companyConnector"}})],1),e._v(" "),a("el-col",{attrs:{span:2}},[a("el-button",{attrs:{icon:"el-icon-search",loading:e.loading},on:{click:e.query}},[e._v("搜索")])],1),e._v(" "),a("el-col",{attrs:{span:2}},[a("el-button",{attrs:{type:"primary",icon:"el-icon-plus"},on:{click:e.handleCreate}},[e._v("添加")])],1),e._v(" "),a("el-col",{attrs:{span:2}},[a("el-button",{attrs:{type:"warning",icon:"el-icon-download"},on:{click:e.handleDownload}},[e._v("导出")])],1)],1),e._v(" "),a("el-table",{staticStyle:{"margin-top":"20px"},attrs:{border:"",fit:"","highlight-current-row":"",data:e.companys,"element-loading-text":"加载中..."}},[a("el-table-column",{attrs:{align:"center",label:"序号",width:"60",type:"index"}}),e._v(" "),a("el-table-column",{attrs:{"min-width":"80px",align:"center",label:"单位名称"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.companyName))])]}}])}),e._v(" "),a("el-table-column",{attrs:{"min-width":"80px",align:"center",label:"单位类型"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.companyType))])]}}])}),e._v(" "),a("el-table-column",{attrs:{"min-width":"110px",align:"center",label:"单位编号"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.companyNo))])]}}])}),e._v(" "),a("el-table-column",{attrs:{"min-width":"110px",align:"center",label:"单位地址"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.companyAddress))])]}}])}),e._v(" "),a("el-table-column",{attrs:{"min-width":"110px",align:"center",label:"联系人"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.companyConnector))])]}}])}),e._v(" "),a("el-table-column",{attrs:{"min-width":"110px",align:"center",label:"联系电话"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.companyTelephone))])]}}])}),e._v(" "),a("el-table-column",{attrs:{"min-width":"100px",align:"center",label:"创建日期"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(e._f("dateTimeFormat")(t.row.createTime)))])]}}])}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作",width:"80",fixed:"right"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{type:"text"},on:{click:function(a){e.handleUpdate(t.row)}}},[a("i",{staticClass:"el-icon-edit"})]),e._v(" "),a("el-button",{staticStyle:{color:"red"},attrs:{type:"text"},on:{click:function(a){e.handleDelete(t.row)}}},[a("i",{staticClass:"el-icon-delete"})])]}}])})],1),e._v(" "),a("el-pagination",{staticStyle:{margin:"20px 0"},attrs:{"current-page":e.companysPagination.number,"page-size":e.companysPagination.size,total:e.companysPagination.totalElements,"page-sizes":[20,50,100],layout:"total, sizes, prev, pager, next, jumper"},on:{"size-change":e.handlePaginationSizeChange,"current-change":e.handleCurrentPageChange}}),e._v(" "),a("el-dialog",{attrs:{title:e.textMap[e.dialogStatus],visible:e.dialogFormVisible},on:{"update:visible":function(t){e.dialogFormVisible=t}}},[a("el-form",{ref:"companyForm",staticClass:"small-space",staticStyle:{width:"400px","margin-left":"50px"},attrs:{model:e.formData,"label-position":"right","label-width":"110px"}},[a("el-form-item",{attrs:{label:"单位名称",prop:"companyName",rules:[{required:!0,message:"单位名称不能为空"}]}},[a("el-input",{model:{value:e.formData.companyName,callback:function(t){e.$set(e.formData,"companyName",t)},expression:"formData.companyName"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"单位类型",prop:"companyType",rules:[{required:!0,message:"单位类型不能为空"}]}},[a("el-select",{attrs:{placeholder:"请选择单位类型"},model:{value:e.formData.companyType,callback:function(t){e.$set(e.formData,"companyType",t)},expression:"formData.companyType"}},e._l(e.companyTypeOptions,function(e){return a("el-option",{key:e.value,attrs:{value:e.value}})}))],1),e._v(" "),a("el-form-item",{attrs:{label:"单位编号"}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.formData.companyNo,callback:function(t){e.$set(e.formData,"companyNo",t)},expression:"formData.companyNo"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"单位地址"}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.formData.companyAddress,callback:function(t){e.$set(e.formData,"companyAddress",t)},expression:"formData.companyAddress"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"联系人"}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.formData.companyConnector,callback:function(t){e.$set(e.formData,"companyConnector",t)},expression:"formData.companyConnector"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"联系电话"}},[a("el-input",{attrs:{placeholder:"请输入内容"},model:{value:e.formData.companyTelephone,callback:function(t){e.$set(e.formData,"companyTelephone",t)},expression:"formData.companyTelephone"}})],1)],1),e._v(" "),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(t){e.dialogFormVisible=!1}}},[e._v("取消")]),e._v(" "),"create"==e.dialogStatus?a("el-button",{attrs:{type:"primary",loading:e.loading},on:{click:e.create}},[e._v("确定")]):a("el-button",{attrs:{type:"primary",loading:e.loading},on:{click:e.update}},[e._v("确定")])],1)],1)],1)},o=[],l={render:n,staticRenderFns:o};t.a=l},rFYz:function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var n=a("24+z"),o=a("61SF"),l=a("VU/8"),r=l(n.a,o.a,null,null,null);t.default=r.exports}});