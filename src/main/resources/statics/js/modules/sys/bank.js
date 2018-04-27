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
    obj.value = obj.value.replace(/^([1-9]\d{0,1}(\.\d{1,2})|100)$/,"");//只能输入两个小数  
    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
        obj.value= parseFloat(obj.value); 
    } 
//	var reg=/^([1-9]\d{0,1}(\.\d{1,2})|100)$/;
//	if(reg.test(obj.value)){
//	   	console.log("success");
//	}else{
//	   	console.log("fail");
//	}
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
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
        	vm.title = "新增";
        	vm.vehicaledet = {};
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
            	vm.getuser(bankId);
        	}
        },
        del: function () {
            var userIds = getSelectedRows();
            var id = getSelectedRow();//根据点击行获得点击行的id（id为jsonReader: {id: "id" },）
        	var rowData = $("#jqGrid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
        	var bankId= rowData.bankId;
    		if(userIds == null){
    			return ;
    		}
    		confirm('确定要删除选中的记录？', function(){
    			$.ajax({
    				type: "GET",
    				url: baseURL + "bank/delectBank?bankId="+bankId,
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
            var url = vm.vehicaledet.bankId == null ? "bank/addBank" : "bank/updateBank?bankId="+vm.vehicaledet.bankId;
            if(vm.vehicaledet.bankName==null){
            	alert("首付银行不能为空！");
            }else if(vm.vehicaledet.bankRatio==null){
            	alert("首付比率不能为空！");
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
        }
    }
});