webpackJsonp([12],{AKrG:function(e,t,r){var a=r("ydFI");"string"==typeof a&&(a=[[e.i,a,""]]),a.locals&&(e.exports=a.locals);r("rjj0")("dd896ef4",a,!0)},I3Fs:function(e,t,r){"use strict";var a=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"app-container"},[r("el-tabs",{attrs:{type:"border-card"}},[r("el-tab-pane",{attrs:{label:"主引桥"}},[r("el-card",{staticStyle:{"margin-bottom":"20px"}},[r("div",{attrs:{slot:"header"},slot:"header"},[r("span",[e._v("主桥配置")]),e._v(" "),r("el-button",{staticStyle:{"margin-left":"20px"},attrs:{type:"text",loading:e.loading},on:{click:e.handleSaveMainBridgeConfig}},[e._v("保存")])],1),e._v(" "),r("el-row",{attrs:{gutter:24}},[r("el-form",{attrs:{"label-position":"right","label-width":"90px"}},[r("el-col",{attrs:{span:6}},[r("el-form-item",{attrs:{label:"桥梁类型"}},[r("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择"},model:{value:e.formData.bridgeType,callback:function(t){e.$set(e.formData,"bridgeType",t)},expression:"formData.bridgeType"}},e._l(e.bridgeTypes,function(e){return r("el-option",{key:e.value,attrs:{label:e.value,value:e.value}})}))],1)],1),e._v(" "),r("el-col",{attrs:{span:6}},[r("el-form-item",{attrs:{label:"桥型子目",title:"材料+受力形式+截面形式"}},[r("el-input",{model:{value:e.formData.subBridgeType,callback:function(t){e.$set(e.formData,"subBridgeType",t)},expression:"formData.subBridgeType"}})],1)],1),e._v(" "),r("el-col",{attrs:{span:6}},[r("el-form-item",{attrs:{label:"孔数"}},[r("el-input",{attrs:{type:"number"},model:{value:e.formData.holesNumber,callback:function(t){e.$set(e.formData,"holesNumber",t)},expression:"formData.holesNumber"}})],1)],1),e._v(" "),r("el-col",{attrs:{span:6}},[r("el-form-item",{attrs:{label:"起始孔数"}},[r("el-input",{attrs:{type:"number"},model:{value:e.formData.holeNumberStart,callback:function(t){e.$set(e.formData,"holeNumberStart",t)},expression:"formData.holeNumberStart"}})],1)],1)],1)],1)],1),e._v(" "),r("el-card",[r("div",{attrs:{slot:"header"},slot:"header"},[r("span",[e._v("引桥配置")]),e._v(" "),r("el-button",{staticStyle:{"margin-left":"20px"},attrs:{type:"text"},on:{click:e.handleCreateApproachOpen}},[e._v("添加引桥")])],1),e._v(" "),r("el-table",{attrs:{border:"",fit:"","highlight-current-row":"",data:e.approachTypes,"element-loading-text":"加载中"}},[r("el-table-column",{attrs:{align:"center",label:"序号",width:"60",type:"index"}}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"桥型"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n              "+e._s(t.row.bridgeType)+"\n            ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"桥型子目"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n              "+e._s(t.row.subBridgeType)+"\n            ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"孔数"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n              "+e._s(t.row.holesNumber)+"\n            ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"起始孔数"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n              "+e._s(t.row.holeNumberStart)+"\n            ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"操作",width:"80",fixed:"right"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-button",{attrs:{type:"text"},on:{click:function(r){e.handleCreateApproachOpen(t.row)}}},[r("i",{staticClass:"el-icon-edit"})]),e._v(" "),r("el-button",{staticStyle:{color:"red"},attrs:{type:"text"},on:{click:function(r){e.onApproachDelete(t.row)}}},[r("i",{staticClass:"el-icon-delete"})])]}}])})],1)],1),e._v(" "),r("el-dialog",{attrs:{title:(e.approachFormData.id?"修改":"添加")+"引桥",visible:e.dialogApproachFormVisible},on:{"update:visible":function(t){e.dialogApproachFormVisible=t}}},[r("el-form",{ref:"approachForm",staticStyle:{width:"400px","margin-left":"50px"},attrs:{model:e.approachFormData,"label-position":"right","label-width":"120px"}},[r("el-form-item",{attrs:{label:"桥梁类型",prop:"bridgeType",rules:[{required:!0,message:"桥梁类型不能为空"}]}},[r("el-select",{staticStyle:{width:"100%"},attrs:{placeholder:"请选择",clearable:""},model:{value:e.approachFormData.bridgeType,callback:function(t){e.$set(e.approachFormData,"bridgeType",t)},expression:"approachFormData.bridgeType"}},e._l(e.bridgeTypes,function(e){return r("el-option",{key:e.value,attrs:{label:e.value,value:e.value}})}))],1),e._v(" "),r("el-form-item",{attrs:{label:"桥型子目",prop:"subBridgeType",rules:[{required:!1}]}},[r("el-input",{model:{value:e.approachFormData.subBridgeType,callback:function(t){e.$set(e.approachFormData,"subBridgeType",t)},expression:"approachFormData.subBridgeType"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"孔数",prop:"holesNumber",rules:[{required:!0,message:"孔数不能为空"}]}},[r("el-input",{attrs:{type:"number"},model:{value:e.approachFormData.holesNumber,callback:function(t){e.$set(e.approachFormData,"holesNumber",t)},expression:"approachFormData.holesNumber"}})],1),e._v(" "),r("el-form-item",{attrs:{label:"起始孔数",prop:"holeNumberStart",rules:[{required:!0,message:"起始孔数不能为空"}]}},[r("el-input",{attrs:{type:"number"},model:{value:e.approachFormData.holeNumberStart,callback:function(t){e.$set(e.approachFormData,"holeNumberStart",t)},expression:"approachFormData.holeNumberStart"}})],1)],1),e._v(" "),r("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{on:{click:function(t){e.dialogApproachFormVisible=!1}}},[e._v("取消")]),e._v(" "),e.approachFormData.id?r("el-button",{attrs:{type:"primary",loading:e.loading},on:{click:e.handleUpdateApproach}},[e._v("确定")]):r("el-button",{attrs:{type:"primary",loading:e.loading},on:{click:e.handleCreateApproach}},[e._v("确定")])],1)],1)],1),e._v(" "),r("el-tab-pane",{attrs:{label:"总体"}},[r("el-card",[r("div",{attrs:{slot:"header"},slot:"header"},[r("el-button",{attrs:{type:"primary",icon:"el-icon-check",size:"small",loading:e.loading},on:{click:e.onOverallSave}},[e._v("保存")])],1),e._v(" "),r("el-form",{attrs:{model:e.formData,"label-width":"220px"}},e._l(e.overRallsFormStructure,function(t,a){return r("el-col",{key:t.key,attrs:{span:12}},[r("el-popover",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{trigger:"hover",placement:"top",content:t.hint}},[r("el-form-item",{attrs:{slot:"reference",label:t.label+"("+(a+1)+")",prop:t.key,rules:[{required:!!t.required}]},slot:"reference"},[t.type?e._e():r("el-input",{model:{value:e.formData[t.key],callback:function(r){e.$set(e.formData,t.key,r)},expression:"formData[item.key]"}}),e._v(" "),"disabled"===t.type?r("el-input",{attrs:{disabled:""},model:{value:e.formData[t.key],callback:function(r){e.$set(e.formData,t.key,r)},expression:"formData[item.key]"}}):e._e(),e._v(" "),"number"===t.type?r("el-input",{model:{value:e.formData[t.key],callback:function(r){e.$set(e.formData,t.key,r)},expression:"formData[item.key]"}}):e._e(),e._v(" "),"select"===t.type?r("el-select",{staticStyle:{width:"100%"},model:{value:e.formData[t.key],callback:function(r){e.$set(e.formData,t.key,r)},expression:"formData[item.key]"}},e._l(t.options,function(t){return r("el-option",{key:t.value,attrs:{value:t.value}},[e._v(e._s(t.value))])})):e._e(),e._v(" "),"array"===t.type?r("el-select",{attrs:{multiple:"",filterable:"","allow-create":"","default-first-option":"",placeholder:"请输入"},model:{value:e.formData[t.key],callback:function(r){e.$set(e.formData,t.key,r)},expression:"formData[item.key]"}}):e._e()],1)],1)],1)}))],1)],1),e._v(" "),r("el-tab-pane",{attrs:{label:"桥面系"}},[r("el-card",[r("div",{attrs:{slot:"header"},slot:"header"},[r("el-button",{attrs:{type:"primary",icon:"el-icon-check",size:"small",loading:e.loading},on:{click:e.onOverallSave}},[e._v("保存")])],1),e._v(" "),r("el-form",{attrs:{model:e.formData,"label-width":"150px"}},e._l(e.bridgeSurfaceSystemFormStructure,function(t,a){return r("el-col",{key:t.key,attrs:{span:12}},[r("el-popover",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{trigger:"hover",placement:"top",content:t.hint}},[r("el-form-item",{attrs:{slot:"reference",label:t.label+"("+(a+1)+")",prop:t.key,rules:[{required:!!t.required}]},slot:"reference"},[t.type?e._e():r("el-input",{model:{value:e.formData[t.key],callback:function(r){e.$set(e.formData,t.key,r)},expression:"formData[item.key]"}}),e._v(" "),"select"===t.type?r("el-select",{staticStyle:{width:"100%"},model:{value:e.formData[t.key],callback:function(r){e.$set(e.formData,t.key,r)},expression:"formData[item.key]"}},e._l(t.options,function(t){return r("el-option",{key:t.value,attrs:{value:t.value}},[e._v(e._s(t.value))])})):e._e()],1)],1)],1)}))],1)],1),e._v(" "),r("el-tab-pane",{attrs:{label:"上部结构"}},[r("el-row",{staticStyle:{"margin-bottom":"20px"},attrs:{gutter:20}},[r("el-col",{attrs:{span:8}},[r("el-button",{attrs:{type:"success",icon:"el-icon-plus"},on:{click:function(t){e.onSuperFormOpen("create")}}},[e._v("添加")])],1)],1),e._v(" "),r("el-table",{attrs:{border:"",fit:"","highlight-current-row":"",data:e.structuralTechnology.superStructures,"element-loading-text":"加载中"}},[r("el-table-column",{attrs:{align:"center",label:"序号",width:"60",type:"index"}}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"孔号"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n            "+e._s(t.row.holeNumber)+"\n          ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"形式"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n            "+e._s(t.row.architecture)+"\n          ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"跨径"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n            "+e._s(t.row.span)+"\n          ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"材料"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n            "+e._s(t.row.material)+"\n          ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"拱上建筑"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n            "+e._s(t.row.archonBuilding)+"\n          ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"桥塔"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n            "+e._s(t.row.bridgeTower)+"\n          ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"主揽"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n            "+e._s(t.row.mainCable)+"\n          ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"斜拉索（含索力）"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n            "+e._s(t.row.stayCable)+"\n          ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"吊杆（含索力）"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n            "+e._s(t.row.boom)+"\n          ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"系杆（含索力）"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n            "+e._s(t.row.tie)+"\n          ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"操作",width:"80",fixed:"right"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-button",{attrs:{type:"text"},on:{click:function(r){e.onSuperFormOpen("update",t.row)}}},[r("i",{staticClass:"el-icon-edit"})]),e._v(" "),r("el-button",{staticStyle:{color:"red"},attrs:{type:"text"},on:{click:function(r){e.onSuperDelete(t.row)}}},[r("i",{staticClass:"el-icon-delete"})])]}}])})],1),e._v(" "),r("el-dialog",{attrs:{title:e.dialogTitleMap[e.dialogStatus],visible:e.superDialogVisible},on:{"update:visible":function(t){e.superDialogVisible=t}}},[r("el-form",{attrs:{model:e.superStructureFormData,"label-width":"190px",inline:""}},e._l(e.superStructureFormStructure,function(t){return r("el-col",{key:t.key,attrs:{span:24}},[r("el-popover",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{trigger:"hover",placement:"right",content:t.hint}},[r("el-form-item",{attrs:{slot:"reference",label:t.label,prop:t.key,rules:[{required:!!t.required}]},slot:"reference"},[t.type?e._e():r("el-input",{model:{value:e.superStructureFormData[t.key],callback:function(r){e.$set(e.superStructureFormData,t.key,r)},expression:"superStructureFormData[item.key]"}})],1)],1)],1)})),e._v(" "),r("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{on:{click:function(t){e.superDialogVisible=!1}}},[e._v("取消")]),e._v(" "),"create"==e.dialogStatus?r("el-button",{attrs:{type:"primary",loading:e.loading},on:{click:e.onSuperAdd}},[e._v("确定")]):r("el-button",{attrs:{type:"primary",loading:e.loading},on:{click:e.onSuperUpdate}},[e._v("确定")])],1)],1)],1),e._v(" "),r("el-tab-pane",{attrs:{label:"下部结构"}},[r("el-row",{staticStyle:{"margin-bottom":"20px"},attrs:{gutter:20}},[r("el-col",{attrs:{span:8}},[r("el-button",{attrs:{type:"success",icon:"el-icon-plus"},on:{click:function(t){e.onLowerFormOpen("create")}}},[e._v("添加")])],1)],1),e._v(" "),r("el-table",{attrs:{border:"",fit:"","highlight-current-row":"",data:e.structuralTechnology.lowerStructures,"element-loading-text":"加载中"}},[r("el-table-column",{attrs:{align:"center",label:"序号",width:"60",type:"index"}}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"墩台号"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n            "+e._s(t.row.piersNumber)+"\n          ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"形式"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n            "+e._s(t.row.form)+"\n          ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"材料"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n            "+e._s(t.row.material)+"\n          ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"基础形式"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n            "+e._s(t.row.basicForm)+"\n          ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"锚碇"},scopedSlots:e._u([{key:"default",fn:function(t){return[e._v("\n            "+e._s(t.row.anchorage)+"\n          ")]}}])}),e._v(" "),r("el-table-column",{attrs:{align:"center",label:"操作",width:"80",fixed:"right"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-button",{attrs:{type:"text"},on:{click:function(r){e.onLowerFormOpen("update",t.row)}}},[r("i",{staticClass:"el-icon-edit"})]),e._v(" "),r("el-button",{staticStyle:{color:"red"},attrs:{type:"text"},on:{click:function(r){e.onLowerDelete(t.row)}}},[r("i",{staticClass:"el-icon-delete"})])]}}])})],1),e._v(" "),r("el-dialog",{attrs:{title:e.dialogTitleMap[e.dialogStatus],visible:e.lowerDialogVisible},on:{"update:visible":function(t){e.lowerDialogVisible=t}}},[r("el-form",{attrs:{model:e.lowerStructureFormData,"label-width":"190px",inline:""}},e._l(e.lowerStructureFormStructure,function(t){return r("el-col",{key:t.key,attrs:{span:24}},[r("el-popover",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{trigger:"hover",placement:"top",content:t.hint}},[r("el-form-item",{attrs:{slot:"reference",label:t.label,prop:t.key,rules:[{required:!!t.required}]},slot:"reference"},[t.type?e._e():r("el-input",{model:{value:e.lowerStructureFormData[t.key],callback:function(r){e.$set(e.lowerStructureFormData,t.key,r)},expression:"lowerStructureFormData[item.key]"}})],1)],1)],1)})),e._v(" "),r("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{on:{click:function(t){e.lowerDialogVisible=!1}}},[e._v("取消")]),e._v(" "),"create"==e.dialogStatus?r("el-button",{attrs:{type:"primary",loading:e.loading},on:{click:e.onLowerAdd}},[e._v("确定")]):r("el-button",{attrs:{type:"primary",loading:e.loading},on:{click:e.onLowerUpdate}},[e._v("确定")])],1)],1)],1),e._v(" "),r("el-tab-pane",{attrs:{label:"附属设施"}},[r("el-card",[r("div",{attrs:{slot:"header"},slot:"header"},[r("el-button",{attrs:{type:"primary",icon:"el-icon-check",size:"small",loading:e.loading},on:{click:e.onOverallSave}},[e._v("保存")])],1),e._v(" "),r("el-form",{attrs:{model:e.formData,"label-width":"150px"}},e._l(e.affiliatedFacilityFormStructure,function(t,a){return r("el-col",{key:t.key,attrs:{span:12}},[r("el-popover",{directives:[{name:"show",rawName:"v-show",value:!0,expression:"true"}],attrs:{trigger:"hover",placement:"top",content:t.hint}},[r("el-form-item",{attrs:{slot:"reference",label:t.label+"("+(a+1)+")",prop:t.key,rules:[{required:!!t.required}]},slot:"reference"},[r("el-input",{model:{value:e.formData[t.key],callback:function(r){e.$set(e.formData,t.key,r)},expression:"formData[item.key]"}})],1)],1)],1)}))],1)],1)],1)],1)},l=[],o={render:a,staticRenderFns:l};t.a=o},RZK5:function(e,t,r){"use strict";r.d(t,"a",function(){return a}),r.d(t,"b",function(){return l});var a=[{value:"混凝土梁桥"},{value:"钢梁桥"},{value:"钢-混凝土组合梁桥"},{value:"圬工拱桥"},{value:"钢筋混凝土板拱桥"},{value:"钢筋混凝土肋拱桥"},{value:"钢筋混凝土箱拱桥"},{value:"钢筋混凝土双曲拱桥"},{value:"钢筋混凝土刚架拱桥"},{value:"钢筋混凝土桁架拱桥"},{value:"钢-混凝土组合拱桥"},{value:"钢拱桥"},{value:"预应力混凝土加劲梁悬索桥"},{value:"钢桁架加劲梁悬索桥"},{value:"钢箱架加劲梁悬索桥"},{value:"钢-混凝土组合加劲梁悬索桥"},{value:"预应力混凝土主梁斜拉桥"},{value:"钢桁架主梁斜拉桥"},{value:"钢箱架主梁斜拉桥"},{value:"钢-混凝土组合主梁斜拉桥"}],l=[{value:"公路-Ⅰ级",text:"公路-Ⅰ级"},{value:"公路-Ⅱ级",text:"公路-Ⅱ级"},{value:"汽-超20,挂120",text:"汽-超20,挂120"},{value:"汽-20,挂100",text:"汽-20,挂100"},{value:"汽-15",text:"汽-15"},{value:"汽-10",text:"汽-10"}]},ki5T:function(e,t,r){"use strict";var a=r("+6Bu"),l=r.n(a),o=r("Dd8w"),n=r.n(o),i=r("NYxO"),u=r("0xDb"),c=r("RZK5"),s=[{label:"桥长(m)",key:"bridgeLength",hint:"包含桥台长度、桥跨长度的总长度，以设计图为准"},{label:"桥型",key:"bridgeType",hint:"梁桥、拱桥、斜拉桥、悬索桥、自定义",type:"disabled"},{label:"桥型细分",key:"subBridgeType",hint:"材料+受力形式+截面形式"},{label:"孔数(个)",key:"holesNumber",hint:"跨数"},{label:"桥跨组合(m)",key:"bridgeCrossCombination",hint:"XXm+XXm+XXm=XXm"},{label:"最大跨径(m)",key:"maxSpan",hint:"最大跨跨径"},{label:"桥面总宽(m)",key:"bridgeWidth",hint:"桥梁宽度"},{label:"桥宽组合(m)",key:"bridgeWidthCombine",hint:"XXm+XXm+XXm=XXm"},{label:"车行道宽(m)",key:"drivewayWide",hint:"XXm+XXm……"},{label:"人行道宽(m)",key:"sidewalkWide",hint:"XXm"},{label:"护栏或防撞护栏高度(m)",key:"guardrailHeight",hint:"XXm"},{label:"中央分隔带宽度(m)",key:"centralDividerWidth",hint:"XXm"},{label:"桥面标准净空(m)",key:"deckStandardClearance",hint:"设计桥面的宽度和桥上的净空高度"},{label:"桥上净高",key:"actualDeckClearance",hint:"实际桥面的宽度和桥上的净空高度"},{label:"桥下净高",key:"clearanceUnderBridge",hint:"设计洪水位、计算通航水位和桥下线路路面至桥跨结构最下缘之间的距离"},{label:"桥上限高",key:"bridgeUpperLimitHighList",hint:"交通管制限高"},{label:"桥面标高",key:"deckElevation",hint:"1/2桥长处桥面纵向竖曲线顶点处行车道中心点的高程，精确至0.01m"},{label:"斜交角(度)",key:"skewAngle",hint:"桥梁轴线与支承轴线垂线的夹角"},{label:"平曲线半径(m)",key:"flatCurveRadius",hint:"圆曲线半径"},{label:"桥面纵坡(%)",key:"deckSlope",hint:"沿路线方向的坡度"},{label:"年日均交通量",key:"annualAverageTrafficVolume",hint:"XX辆"},{label:"设计时速(km/h)",key:"designSpeed",hint:"桥梁设计时速，XXkm/h"},{label:"引道总宽(m)",key:"approachTotalWidth",hint:"引道总宽度，包含路肩，路基等"},{label:"引道路面宽(m)",key:"approachRoadWide",hint:"引道路面宽度"},{label:"引道线形",key:"roadRoute",hint:"直线、缓和曲线、圆曲线"},{label:"设计洪水频率",key:"designFloodFrequency",hint:"见设计文件，如1/100"},{label:"常水位",key:"generalWaterLevel",hint:"或若干年中，有50%的水位等于或超过该水位的高程值，称为常水位"},{label:"设计水位",key:"designWaterLevel",hint:"设计洪水频率对应的洪水水位作为设计水位，见设计文件"},{label:"历史洪水位",key:"historicalFloodLevel",hint:"见水文资料"},{label:"通航等级",key:"navigationLevel",hint:"一级航道～七级航道",type:"select",options:[{value:"一级航道",text:"一级航道"},{value:"二级航道",text:"二级航道"},{value:"三级航道",text:"三级航道"},{value:"四级航道",text:"四级航道"},{value:"五级航道",text:"五级航道"},{value:"六级航道",text:"六级航道"},{value:"七级航道",text:"七级航道"}]},{label:"设计地震动峰值加速度系数",key:"peakAccelerationGroundMotion",hint:"见设计文件，如0.10g"}],p=[{label:"伸缩缝类型",key:"expansionJointType",hint:"如模数式、梳齿板式、橡胶式、异型钢式等"},{label:"桥面铺装",key:"deckPavement",hint:"选择桥面铺装类型。一般为沥青混凝土、水泥混凝土",type:"select",options:[{value:"沥青混凝土",text:"沥青混凝土"},{value:"水泥混凝土",text:"水泥混凝土"}]},{label:"栏杆护栏",key:"railing",hint:"根据部位输入类型、材料等信息"},{label:"排水设施",key:"drainageFacilities",hint:"排水设施类型、方式等"},{label:"照明标志",key:"lightingSigns",hint:"照明设备类型"},{label:"人行道",key:"sideWalk",hint:"人行道铺装类型"},{label:"支座形式",key:"bearingForm",hint:"支座类型"}],d=[{label:"孔号",key:"holeNumber",hint:"孔号编号"},{label:"形式",key:"architecture",hint:"梁桥、拱桥、斜拉桥、悬索桥、自定义"},{label:"跨径",key:"span",hint:"该孔跨径"},{label:"材料",key:"material",hint:"钢、钢筋混凝土、预应力混凝土、钢-混凝土组合"},{label:"拱上建筑",key:"archonBuilding",hint:"仅针对拱桥，输入拱上建筑类型、材料等"},{label:"桥（索）塔",key:"bridgeTower",hint:"桥塔类型、材料等"},{label:"主缆",key:"mainCable",hint:"仅针对悬索桥，输入缆索材料、尺寸等"},{label:"斜拉索（含索力）",key:"stayCable",hint:"斜拉索材料、尺寸、索力等，根据图纸编号输入"},{label:"吊杆（含索力",key:"boom",hint:"吊杆材料、尺寸、索力等，根据图纸编号输入"},{label:"系杆（含索力）",key:"tie",hint:"仅针对系杆拱桥，输入系杆材料、尺寸、索力等，根据图纸编号输入"}],h=[{label:"墩台",key:"piersNumber",hint:"墩台"},{label:"形式",key:"form",hint:"墩台类型等"},{label:"材料",key:"material",hint:"墩台材料"},{label:"基础形式",key:"basicForm",hint:"基础类型、材料"},{label:"锚碇",key:"anchorage",hint:"仅针对悬索桥，输入类型、尺寸等信息"}],m=[{label:"桥台护坡",key:"bridgeSlope",hint:"XXX"},{label:"护墩体",key:"piersBody",hint:"仅针对具有护墩体桥梁，输入护墩体类型、材料等"},{label:"调治构造物",key:"conditioningTheStructure",hint:"调治桥梁附近水流的构造物。如：导流堤、梨形坝、长堤、丁坝、顺坝、截水坝等"},{label:"翼墙、耳墙",key:"wingWall",hint:"翼墙耳墙材料、尺寸等"},{label:"航标",key:"beacon",hint:"航标位置或类型"}],b={id:0,holeNumber:"",architecture:"",span:"",material:"",maineam:"",minArchring:"",archonBuilding:"",bridgeTower:"",mainCable:"",stayCable:"",boom:"",tie:""},f={id:0,piersNumber:"",form:"",material:"",basicForm:"",coneSlope:"",wingWall:"",anchorage:"",piersBody:""},y={id:0,conditioningTheStructure:"",beacon:""},v={id:0,bridgeType:"",subBridgeType:"",holesNumber:1,holeNumberStart:1};t.a={data:function(){return{superDialogVisible:!1,lowerDialogVisible:!1,loading:!1,dialogTitleMap:{create:"添加",update:"编辑"},dialogStatus:"create",bridge:{},formData:{},overRallsFormStructure:s,bridgeSurfaceSystemFormStructure:p,superStructureFormStructure:d,lowerStructureFormStructure:h,affiliatedFacilityFormStructure:m,superStructureFormData:r.i(u.c)(b),lowerStructureFormData:r.i(u.c)(f),affiliatedFacilityFormData:r.i(u.c)(y),bridgeTypes:c.a,dialogApproachFormVisible:!1,approaches:[],approachTypes:[],approachFormData:r.i(u.c)(v)}},computed:n()({},r.i(i.b)(["structuralTechnology","menus","selectedBridge","componentTreeData","categoryTreeData","constructTree","constructTreeMainBridgeType"])),watch:{menus:function(){var e=this;if(!this.bridge.id){var t=this.$route.query.id;this.menus.forEach(function(r){var a=r.children.filter(function(e){return e.id===parseInt(t)});a.length&&(e.bridge=a[0],e.selectBridge(e.bridge),e.query())})}},structuralTechnology:function(){this.formData.id||(this.formData=r.i(u.c)(this.structuralTechnology))},constructTree:function(){var e=this.constructTree,t=e.approaches,r=e.approachTypes;this.approaches=t,this.approachTypes=r}},mounted:function(){if(!this.$route.query.id&&!this.selectedBridge.id)return void r.i(u.d)("未选择桥梁，无法进行操作！",{type:"error"});this.selectedBridge.id&&(this.bridge=this.selectedBridge,this.query())},methods:n()({},r.i(i.c)(["selectBridge","queryStructuralTechnology","createStructuralTechnology","updateStructuralTechnology","createSuperStructure","updateSuperStructure","deleteSuperStructure","createLowerStructure","updateLowerStructure","deleteLowerStructure","queryConstructTree","createConstructTree","updateConstructTree","generateConstructTreeData","generateConstructComponentTreeData"]),{query:function(){var e=this;this.loading=!0,this.queryStructuralTechnology(this.bridge).then(function(){return e.queryComponentTree()}).catch(function(t){if(t&&t.response&&t.response.status){switch(t.response.status){case 404:e.createStructuralTechnology({bridge:e.bridge});break;default:console.log(t)}}}).then(function(){e.loading=!1})},queryComponentTree:function(){var e=this,t=this.bridge;this.queryConstructTree(t).then(function(){}).catch(function(r){if(r&&r.response&&r.response.status){switch(r.response.status){case 404:e.createConstructTree({bridge:t});break;default:console.log(r)}}})},onOverallSave:function(){var e=this;this.loading=!0;var t=this.formData,a=(t.superStructures,t.lowerStructures,t.affiliatedFacilities,l()(t,["superStructures","lowerStructures","affiliatedFacilities"]));this.updateStructuralTechnology(a).then(function(){r.i(u.d)(),e.loading=!1}).catch(function(t){r.i(u.d)("",{type:"error"}),e.loading=!1})},onSuperFormOpen:function(e,t){this.superDialogVisible=!0,this.dialogStatus=e,"create"===e?this.superStructureFormData.id=0:"update"===e&&(this.superStructureFormData=r.i(u.c)(t))},onSuperDelete:function(e){var t=this;this.$confirm("此操作将永久删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"error"}).then(function(){t.deleteSuperStructure(e).then(function(){r.i(u.d)(),t.loading=!1,t.query()}).catch(function(e){r.i(u.d)("",{type:"error"}),t.loading=!1})}).catch(function(){r.i(u.d)("已取消删除",{type:"info"})})},onSuperAdd:function(){var e=this,t=this.superStructureFormData,a="";for(var l in t)"id"!==l&&(a+=t[l]);if(!a)return void r.i(u.d)("请填写至少一个字段",{type:"info"});this.loading=!0,this.createSuperStructure(t).then(function(t){var r=e.structuralTechnology,a=r.id,l=r.superStructures,o=l.map(function(e){return"superStructures/"+e.id});return o.push("superStructures/"+t.id),e.updateStructuralTechnology({id:a,superStructures:o})}).then(function(){r.i(u.d)(),e.loading=!1,e.superDialogVisible=!1,e.query()}).catch(function(t){r.i(u.d)("",{type:"error"}),e.loading=!1})},onSuperUpdate:function(){var e=this;this.loading=!0,this.updateSuperStructure(this.superStructureFormData).then(function(){r.i(u.d)(),e.loading=!1,e.superDialogVisible=!1,e.query()}).catch(function(t){r.i(u.d)("",{type:"error"}),e.loading=!1})},onLowerFormOpen:function(e,t){this.lowerDialogVisible=!0,this.dialogStatus=e,"create"===e?this.lowerStructureFormData.id=0:"update"===e&&(this.lowerStructureFormData=r.i(u.c)(t))},onLowerDelete:function(e){var t=this;this.$confirm("此操作将永久删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"error"}).then(function(){t.deleteLowerStructure(e).then(function(){r.i(u.d)(),t.loading=!1,t.query()}).catch(function(e){r.i(u.d)("",{type:"error"}),t.loading=!1})}).catch(function(){r.i(u.d)("已取消删除",{type:"info"})})},onLowerAdd:function(){var e=this,t=this.lowerStructureFormData,a="";for(var l in t)"id"!==l&&(a+=t[l]);if(!a)return void r.i(u.d)("请填写至少一个字段",{type:"info"});this.loading=!0,this.createLowerStructure(t).then(function(t){var r=e.structuralTechnology,a=r.id,l=r.lowerStructures,o=l.map(function(e){return"lowerStructures/"+e.id});return o.push("lowerStructures/"+t.id),e.updateStructuralTechnology({id:a,lowerStructures:o})}).then(function(){r.i(u.d)(),e.loading=!1,e.lowerDialogVisible=!1,e.query()}).catch(function(t){console.log(t),r.i(u.d)("",{type:"error"}),e.loading=!1})},onLowerUpdate:function(){var e=this;this.loading=!0,this.updateLowerStructure(this.lowerStructureFormData).then(function(){r.i(u.d)(),e.loading=!1,e.lowerDialogVisible=!1,e.query()}).catch(function(t){r.i(u.d)("",{type:"error"}),e.loading=!1})},handleCreateApproachOpen:function(e){this.dialogApproachFormVisible=!0,e instanceof MouseEvent?this.approachFormData=r.i(u.c)(v):this.approachFormData=r.i(u.c)(e)},handleCreateApproach:function(){var e=this;this.$refs.approachForm.validate(function(t){if(t){var a=e.approachTypes.length+1,l=n()({},e.approachFormData,{unit:"N"+a});e.generateConstructTreeData(l).then(function(t){e.approachFormData.id=a,e.approachTypes.push(e.approachFormData),e.approaches.push(t);var r=e.constructTree.id,l={id:r,approaches:e.approaches,approachTypes:e.approachTypes};return e.updateConstructTree(l)}).then(function(){r.i(u.d)(),e.dialogApproachFormVisible=!1}).catch(function(e){r.i(u.d)("添加引桥失败！",{type:"error"})})}})},handleUpdateApproach:function(){var e=this;this.$refs.approachForm.validate(function(t){if(t){for(var a=e.approachFormData.id,l=-1,o=0,i=e.approachTypes.length;o<i;o++)if(e.approachTypes[o].id===a){l=o;break}if(l>-1){var c=n()({},e.approachFormData,{unit:"N"+a});e.generateConstructTreeData(c).then(function(t){e.approachTypes.splice(l,1,e.approachFormData),e.approaches.splice(l,1,t);var r=e.constructTree.id,a={id:r,approaches:e.approaches,approachTypes:e.approachTypes};return e.updateConstructTree(a)}).then(function(){r.i(u.d)(),e.dialogApproachFormVisible=!1}).catch(function(e){r.i(u.d)("修改引桥失败！",{type:"error"})})}}})},onApproachDelete:function(e){var t=this;e&&e.id&&this.$confirm("此操作将永久删除该数据, 是否继续?","提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"error"}).then(function(){for(var a=e.id,l=-1,o=0,n=t.approachTypes.length;o<n;o++)if(t.approachTypes[o].id===a){l=o;break}try{if(l>-1){t.approachTypes.splice(l,1),t.approaches.splice(l,1),t.approachTypes.forEach(function(e,t){e.id=t+1});var i=t.constructTree.id,c={id:i,approaches:t.approaches,approachTypes:t.approachTypes};t.updateConstructTree(c).then(function(){r.i(u.d)()}).catch(function(e){r.i(u.d)("删除引桥失败！",{type:"error"})})}}catch(e){r.i(u.d)("删除引桥失败！",{type:"error"})}}).catch(function(){r.i(u.d)("已取消删除",{type:"info"})})},handleSaveMainBridgeConfig:function(){var e=this,t=this.formData,a=t.id,l=t.bridgeType,o=t.subBridgeType,n=t.holesNumber,i=t.holeNumberStart,c={id:a,bridgeType:l,subBridgeType:o,holesNumber:n,holeNumberStart:i};if(!l||!n)return void r.i(u.d)("桥梁类型和孔数必填！",{type:"warning"});this.loading=!0;var s=!0;this.queryStructuralTechnology(this.bridge).then(function(){var t=e.structuralTechnology,r=t.bridgeType,a=t.holesNumber,l=t.holeNumberStart;c.bridgeType===r&&c.holesNumber===a&&c.holeNumberStart===l&&(s=!1)}).catch(function(e){console.log(e)}),this.updateStructuralTechnology(c).then(function(){if(e.loading=!1,c.unit="M",s)return e.generateConstructTreeData(c)}).then(function(t){if(s){var r=e.constructTree.id,a={id:r,main:t};return e.updateConstructTree(a)}}).then(function(){r.i(u.d)()}).catch(function(t){r.i(u.d)("",{type:"error"}),e.loading=!1})}})}},ydFI:function(e,t,r){t=e.exports=r("FZ+f")(!1),t.push([e.i,".el-table .main-row[data-v-4a67eaf8]{background:oldlace}",""])},zCoW:function(e,t,r){"use strict";function a(e){r("AKrG")}Object.defineProperty(t,"__esModule",{value:!0});var l=r("ki5T"),o=r("I3Fs"),n=r("VU/8"),i=a,u=n(l.a,o.a,i,"data-v-4a67eaf8",null);t.default=u.exports}});