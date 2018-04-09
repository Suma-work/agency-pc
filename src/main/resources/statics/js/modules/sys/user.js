$(function () {
	var deptid=JSON.parse(localStorage.getItem("user")).deptId;
	if(deptid==null){
		deptid="";
	}else if(deptid=="1"||deptid=="2"){
		deptid="";
	}
//	console.log("------------"+deptid);
    $("#jqGrid").jqGrid({
        url: baseURL + 'user/getUserList?deptid='+deptid,
        datatype: "json",
        colModel: [			
			{ label: '用户ID', name: 'userid', index: "userid", width: 30, key: true },
			{ label: '登录账户', name: 'username', width: 50 },
			{ label: '用户名', name: 'uname', width: 50 },
			{ label: '所属部门编号', name: 'udeptid', sortable: false, width: 45,hidden:true},
			{ label: '角色编号', name: 'roleid', sortable: false, width: 35 },
			{ label: '所属角色', name: 'rolename', sortable: false, width: 75 },
            { label: '所属部门', name: 'deptname', sortable: false, width: 75 },
            { label: '店铺编号', name: 'shopid', index: 'shopid', width: 60,hidden:true},
            { label: '所属店铺', name: 'shopname', width: 75 },
			{ label: '邮箱', name: 'email', width: 90 },
			{ label: '手机号', name: 'mobile', width: 100 },
			{ label: '状态', name: 'status', width: 60, formatter: function(value, options, row){
				return value === 0 ? 
					'<span class="label label-danger">禁用</span>' : 
					'<span class="label label-success">正常</span>';
			}},
			{ label: '创建时间', name: 'createtime', index: "createtime", width: 85}
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
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

function getschoolList(fid,shopn) {//获取下拉列表
	$("#shopname").find("option").remove();
	var fids=fid;
    $.ajax({
        url: baseURL + "vehic/getshop",//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
        	$.each(data.data, function (i) {
                $('#shopname.selectpicker').append("<option id='shop' value=" + data.data[i].shopid + ">" + data.data[i].shopname + "</option>");
            });
//            $('#carName').selectpicker('val',fid);
		    if(fids==undefined){
		    	$('#shopname').selectpicker('val',data.data[0].shopid);
		    	document.getElementById("shopids").value=data.data[0].shopid;
		    	document.getElementById("shopnames").value=data.data[0].shopname;
		    }else{
		    	$('#shopname').selectpicker('val',fids);
//		    	console.log(fids);
		    	document.getElementById("shopids").value=fids;
		    	document.getElementById("shopnames").value=shopn;
		    }
		    $("#shopname").selectpicker('refresh');
//		    console.log($("#shopnames").val());
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
}

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
//	console.log(value);
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
        roleList:{},
        user:{
            status:1,
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
            vm.roleList = {};
//            vm.user = {deptName:"上海苏马信息科技有限公司", deptId:1, status:1, roleIdList:[]};
//            $("#shop").hide();
//            //获取角色信息
//            this.getRoleList();
//            vm.getDept();
            vm.user = {deptName:null, deptId:null, status:1, roleIdList:[]};

            //获取角色信息
            $("#shop").hide();
            this.getRoleList();

            vm.getDept();
            
        },
        getDept: function(){
            //加载部门树
            $.get(baseURL + "sys/dept/list", function(r){
                ztree = $.fn.zTree.init($("#deptTree"), setting, r);
                var node = ztree.getNodeByParam("deptId", vm.user.deptId);
                if(node != null){
                    ztree.selectNode(node);

                    vm.user.deptName = node.name;
                }
            })
        },
        update: function () {
        	var id = getSelectedRow();//根据点击行获得点击行的id（id为jsonReader: {id: "id" },）
        	var rowData = $("#jqGrid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
        	var fcvid= rowData.shopid;
        	var udeptid= rowData.udeptid;
        	var roleid= rowData.roleid;
        	console.log(roleid);
        	var shopname= rowData.shopname;
        	var deptid=JSON.parse(localStorage.getItem("user")).deptId;
        	if(udeptid==1||udeptid==2){
        		alert("不能修改公司信息和管理员信息！");
        	}else if(udeptid==deptid){
        		alert("不能修改自己同级的资料！");
        	}else{
        		if(roleid==1||roleid==2||roleid==3){
        			$("#shop").hide();
        			$("#shopname").find("option").remove();
        		}else{
        			$("#shop").show();
        			getschoolList(fcvid,shopname);
        		}
        		var userId = getSelectedRow();
        		if(userId == null){
        			return ;
        		}
        		vm.showList = false;
        		vm.title = "修改";
        		vm.getUser(userId);
        		//获取角色信息
        		this.getRoleList();
        	}
        },
        del: function () {
            var userIds = getSelectedRows();
            var id = getSelectedRow();//根据点击行获得点击行的id（id为jsonReader: {id: "id" },）
        	var rowData = $("#jqGrid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
        	var deptid=JSON.parse(localStorage.getItem("user")).deptId;//自己的级别
        	var udeptid= rowData.udeptid;
        	console.log(udeptid);
        	if(udeptid==1||udeptid==2){
        		alert("只能删除商家资料");
        	}else if(udeptid==deptid){
        		alert("不能修改自己同级的资料！");
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
            var url = vm.user.userId == null ? "sys/user/save" : "sys/user/update";
//            console.log(vm.user.deptName);
            if(vm.user.deptName==null){
            	alert("请选择所属部门");
            }else if(vm.user.mobile==null){
            	alert("手机号不能为空！");
            }else if(vm.user.username==null){
            	alert("用户名不能为空！");
            }else if(vm.user.password==null){
            	alert("密码不能为空！");
            }else{
            	var myreg=/^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$/;  
            	if(!myreg.test($("#mobile").val())){
            	}else{
            		console.log(vm.user.deptName.substring(0,4));
            		if(vm.user.deptName=='上海苏马信息科技有限公司'||vm.user.deptName=='平台管理员'||vm.user.deptName.substring(0,4)=='集团老总'||vm.user.deptName.substring(0,4)=='大区经理'){
            			vm.user.shopid='';
            			vm.user.shopname='';
            		}else{
            			vm.user.shopid=$("#shopids").val();
            			vm.user.shopname=$("#shopnames").val();
            		}
//            		vm.user.deptId=vm.user.deptIds;
//            		vm.user.deptIds='';
            		$.ajax({
            			type: "POST",
            			url: baseURL + url,
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
            $.get(baseURL + "sys/user/info/"+userId, function(r){
                vm.user = r.user;
                vm.user.password = null;

                vm.getDept();
            });
        },
        getRoleList: function(){
            $.get(baseURL + "sys/role/select", function(r){
                vm.roleList = r.list;
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
//                    vm.user.deptIds= node[0].deptId;
                    vm.user.deptName = node[0].name;
                    if(vm.user.deptName=='上海苏马信息科技有限公司'||vm.user.deptName=='平台管理员'||vm.user.deptName.substring(0,4)=='集团老总'||vm.user.deptName.substring(0,4)=='大区经理'){
                    	$("#shop").hide();
//                    	console.log(vm.user+"-----------");
                    	$("#shopname").find("option").remove();
                    }else{
                    	$("#shop").show();
                    	getschoolList();
//                    	console.log(vm.user);
                    }
                    
                    layer.close(index);
                }
            });
        },
        reload: function () {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam','page');
            $("#jqGrid").jqGrid('setGridParam',{
                postData:{'keyword': document.getElementById("keyword").value},
                page:page
            }).trigger("reloadGrid");
        }
    }
});