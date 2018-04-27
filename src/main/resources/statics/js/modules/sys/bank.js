$(function () {//加载数据
    $("#jqGrid").jqGrid({
        url: baseURL + 'bank/getBankList',
        datatype: "json",
        colModel: [			
            { label: '金融服务编号', name: 'bankId',index: "bankId", width: 100,key: true},
            { label: '首付银行', name: 'bankName', index: "bankName",width: 80},
            { label: '首付款比率', name: 'bankRatio', width:30, formatter: function(value, options, row){
        		return '<span class="label label-danger">'+value+'%</span>';
			}},
            { label: '创建时间', name: 'createTime',index: "createTime", width: 80 },
        ],
		viewrecords: true,
        height: 700,
        rowNum: 20,
		rowList : [20,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        multiselect: true,  
        multiboxonly:true,  
        gridComplete: hideSelectAll,
        beforeSelectRow: beforeSelectRow,
        pager: "#jqGridPager",
        jsonReader : {
        	 root: "data.dataList",//包含实际数据的数组
             page: "data.currPage",//当前页
//             page: 1,
             total: "data.totalPage",//总的页数
             records: "data.totalCount"//总的记录数（查出来的总条数）
        },
        prmNames : {
            page:"pageno", 
            rows:"pagesize", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});
function hideSelectAll() {  
    $("#jqGrid").hide();  
    return(true);  
} 
function beforeSelectRow() {  
    $("#jqGrid").jqGrid('resetSelection');  
    return(true);  
} 
function clearNoNum(obj){ 
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
//    obj.value = obj.value.replace(/^(\d?\d(\.\d*)?|100)$/g,"");//禁止录入整数部分两位以上，但首位为0
    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
        obj.value= parseFloat(obj.value); 
    } 
} 

var setting = {
    data: {
        simpleData: {
            enable: true,
            idKey: "deptId",
            pIdKey: "parentId",
            rootPId: -1
        },
        key: {
            url:"nourl"
        }
    }
};
var vm = new Vue({//vue 初始值
    el:'#rrapp',
    data:{
        q:{
            username: null
        },
        showList: true,
        title:null,
        user:{
//            status:1,
            deptId:null,
            deptName:null,
            roleIdList:[]
        },
        vehicaledet:{
        	imgs:[]
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
        	vm.title = "新增";
        	var deptid=JSON.parse(localStorage.getItem("user")).deptId;
        	selectDeptList(deptid);
        	vm.vehicaledet = {imgs:[]};
        },
        update: function () {
        	var id = getSelectedRow();//根据点击行获得点击行的id（id为jsonReader: {id: "id" },)
        	if(id == null){
        		return false;
        	}else{
        		var rowData = $("#jqGrid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
            	var bankId = getSelectedRow();
            	vm.showList = false;
            	vm.title = "修改";
            	console.log(bankId);
            	vm.getuser(bankId);
        	}
        },
        del: function () {
            var userIds = getSelectedRows();
            var id = getSelectedRow();//根据点击行获得点击行的id（id为jsonReader: {id: "id" },）
        	var rowData = $("#jqGrid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
        	var shopId= rowData.shopId;
    		if(userIds == null){
    			return ;
    		}
    		confirm('确定要删除选中的记录？', function(){
    			$.ajax({
    				type: "GET",
    				url: baseURL + "shop/delectShop?shopId="+shopId,
    				contentType: "application/json",
//    				data: JSON.stringify(userIds),
    				success: function(r){
    					if(r.messageCode == 0){
    						alert('操作成功', function(){
    							vm.reload();
    						});
    					}else{
    						alert(r.messageStr);
    					}
    				}
    			});
    		});
        },
        saveOrUpdate: function () {
            var url = vm.vehicaledet.shopId == null ? "shop/setShop" : "shop/update?shopId="+vm.vehicaledet.shopId;
            var imgs=vm.vehicaledet.imgs;
          //取值汽车品牌
            vm.vehicaledet.dept=document.getElementById("depts").value;
            /*if(vm.vehicaledet.depts==null){
            	alert("所属大区不能为空！");
            }else */if(vm.vehicaledet.shopName==null){
            	alert("店铺名称不能为空！");
            }else if(vm.vehicaledet.shopPhone==null){
            	alert("店铺电话不能为空！");
            }else if(vm.vehicaledet.address==null){
            	alert("店铺地址不能为空！");
            }else if(vm.vehicaledet.lon==null){
            	alert("经度不能为空！");
            }else if(vm.vehicaledet.lat==null){
            	alert("纬度不能为空！");
            }else if(vm.vehicaledet.classify==null){
            	alert("请选择店铺类型！");
            }else if(vm.vehicaledet.refEl==null){
            	alert("请选择精品商品服务！");
            }else if(vm.vehicaledet.upkeepEl==null){
            	alert("请选择保养服务！");
            }else if(vm.vehicaledet.tyreEl==null){
            	alert("请选择轮胎服务！");
            }else if(vm.vehicaledet.installEl==null){
            	alert("请选择安装服务！");
            }else if(vm.vehicaledet.delfg==null){
            	alert("请选择废弃标志！");
            }else if(imgs.length<0){
            	alert("至少上传一张展示图片！");
            }else{
            		$.ajax({
            			type: "POST",
            			url: baseURL + url,
            			contentType: "application/json",
            			data: JSON.stringify(vm.vehicaledet),
            			success: function(r){
            				if(r.messageCode === "0"){
            					alert('操作成功', function(){
            						vm.reload();
            					});
            				}else{
            					alert(r.msg);
            				}
            			}
            		});
            }
        },
        getuser: function(bankId){
            $.get(baseURL + "bank/getBankMap?bankId="+bankId, function(r){
                vm.vehicaledet = r.data;
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'keyword':document.getElementById("keyword").value},
                page:page
            }).trigger("reloadGrid");
        },
        DelectImg: function (index,ObjDom){
        	this.i = index;
        	var i = this.i;
        	//反向截取最后一个/后的图片
        	var index = ObjDom .lastIndexOf("\/");
    		ObjDom  = ObjDom .substring(index + 1, ObjDom .length);
    		var imgArr = vm.vehicaledet.imgs;
        	$.ajax({
    			type:"get",
    			url: baseURL + "uplo/deleteImages?imgName="+ObjDom,
    			cache: false,  
    		    contentType: false,  
    		    processData: false,
    			contentType: "application/json",
//    			data: ObjDom,
    			success:function(data){
    				imgArr.splice($.inArray(index,imgArr),1);
    				vm.vehicaledet.imgs = imgArr;
    				console.log(vm.vehicaledet.imgs)
    			}
    		});
        }
    }
});