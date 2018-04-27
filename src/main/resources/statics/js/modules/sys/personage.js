$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'cust/getCusto',
        datatype: "json",
        colModel: [			
			{ label: '客户id', name: 'cusId', index: "cusId", width: 45, key: true },
			{ label: '客户昵称', name: 'nickName', index: "nickName",width: 75 },
            { label: '客户真实名称', name: 'realName', index: "realName", width: 50 },
            { label: '客户身份证号码', name: 'idNumber', index: 'idNumber', width:80},
            { label: '性别', name: 'sex', width: 60, formatter: function(value, options, row){
            	if(value==""){
            		return value;
            	}else{
            		return value === "0" ?'女' :'男';
            	}
			}},
//			{ label: '用户头像', name: 'email', width: 90 },
			{ label: '客户手机号码', name: 'cellPhone', width: 100 },
			{ label: '创建时间', name: 'createTime', index: "createTime", width: 85}
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
var ztree;

function intOnly(){
	  var codeNum=event.keyCode;
	  if(codeNum==8||codeNum==37||codeNum==39||(codeNum>=48&&codeNum<=57)){
	    event.returnValue=codeNum;
	  }else{
	    event.returnValue=false;
	  }
}
function checkseralize()
{
	isPoneAvailable();
}
function isPoneAvailable() {  
    var myreg=/^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/;  
    if (!myreg.test($("#mobile").val())) {  
    	alert("手机号码不正确！");
        return false;  
    } else {  
        return true;  
    }  
} 
function selectOnchang(obj){ 
//	var value = obj.options[obj.selectedIndex].value;
	document.getElementById("shopids").value=obj.options[obj.selectedIndex].value;
	document.getElementById("shopnames").value=obj.options[obj.selectedIndex].text;
}
var vm = new Vue({
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
        }
    },
    methods: {
        query: function () {
            vm.reload();
        },
        add: function(){
            vm.showList = false;
            vm.title = "新增";
//            vm.user = {deptName:"商家", deptId:3, status:1, roleIdList:[]};


            /*vm.getDept();*/
//            getschoolList();
        },
//        getDept: function(){
//            //加载部门树
//            $.get(baseURL + "sys/dept/list", function(r){
//                ztree = $.fn.zTree.init($("#deptTree"), setting, r);
//                var node = ztree.getNodeByParam("deptId", vm.user.deptId);
//                if(node != null){
//                    ztree.selectNode(node);
//
//                    vm.user.deptName = node.name;
//                }
//            })
//        },
        update: function () {
        	var id = getSelectedRow();//根据点击行获得点击行的id（id为jsonReader: {id: "id" },）
        	var rowData = $("#jqGrid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
//        	var fcvid= rowData.shopid;
//        	var deptName= rowData.deptName;
//        	var shopname= rowData.shopname;
	        	
//	        	getschoolList(fcvid,shopname);
	        	var userId = getSelectedRow();
	        	if(userId == null){
	        		return ;
	        	}
	        	
	        	vm.showList = false;
	        	vm.title = "修改";
	        	
	        	vm.getUser(userId);
        },
        del: function () {
            var userIds = getSelectedRows();
            var id = getSelectedRow();//根据点击行获得点击行的id（id为jsonReader: {id: "id" },）
        	var rowData = $("#jqGrid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
        	var deptName= rowData.deptName;
        	if(deptName!="商家"){
        		alert("只能删除商家资料");
        	}else{
        		if(userIds == null){
        			return ;
        		}
        		
        		confirm('确定要删除选中的记录？', function(){
        			$.ajax({
        				type: "POST",
        				url: baseURL + "sys/user/delete",
        				contentType: "application/json",
        				data: JSON.stringify(userIds),
        				success: function(r){
        					if(r.code == 0){
        						alert('操作成功', function(){
        							vm.reload();
        						});
        					}else{
        						alert(r.msg);
        					}
        				}
        			});
        		});
        	}
        },
        saveOrUpdate: function () {
            var url = vm.user.userId == null ? "sys/user/save" : "cust/setCust?cusId="+vm.user.userId;
            if(vm.user.cellPhone==null){
            	alert("手机号不能为空！");
            }else if(vm.user.passWord==null){
            	alert("密码不能为空！");
            }else{
            	var myreg=/^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/;  
            	if(!myreg.test($("#mobile").val())){
            	}else{
            		$.ajax({
            			type: "POST",
            			url: baseURL + "cust/setCust?cusId="+vm.user.userId,
            			contentType: "application/json",
            			data: JSON.stringify(vm.user),
            			success: function(r){
            				if(r.code === 0){
            					alert('操作成功', function(){
            						vm.reload();
            					});
            				}else{
            					alert(r.msg);
            				}
            			}
            		});
            	}
            }
        },
        getUser: function(userId){
            $.get(baseURL + "cust/getCustoMap?cusId="+userId, function(r){
                vm.user = r.data;
                vm.user.passWord = null;

//                vm.getDept();
            });
        },
        deptTree: function(){
            layer.open({
                type: 1,
                offset: '50px',
                skin: 'layui-layer-molv',
                title: "选择部门",
                area: ['300px', '450px'],
                shade: 0,
                shadeClose: false,
                content: jQuery("#deptLayer"),
                btn: ['确定', '取消'],
                btn1: function (index) {
                    var node = ztree.getSelectedNodes();
                    //选择上级部门
                    vm.user.deptId = node[0].deptId;
                    vm.user.deptName = node[0].name;

                    layer.close(index);
                }
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