$(function () {
	var deptid=JSON.parse(localStorage.getItem("user")).deptId;
	if(deptid==null){
		deptid="";
	}else if(deptid=="1"||deptid=="2"){
		deptid="";
	}
	var username=JSON.parse(localStorage.getItem("user")).username;
    $("#jqGrid").jqGrid({
        url: baseURL + 'vehi/getVehiList?loginDeptid='+deptid+"&username="+username,
        datatype: "json",
        colModel: [			
            { label: '汽车主键', name: 'carId', index: "cusId", width: 35, key: true },
            { label: '店铺编号', name: 'shopid',index: "shopid", width: 50,hidden:true},
            { label: '所属店铺', name: 'shopName',sortable:false, width: 80 },
            { label: '汽车编号', name: 'fvcid', index: "fvcid",width: 50,hidden:true},
            { label: '汽车品牌', name: 'bandName', index: "carName",width: 50 },
            { label: '车型编号', name: 'secid', index: "secid",width: 50,hidden:true },
			{ label: '汽车车型', name: 'carName', index: "carName",width: 120 },
            { label: '车型名称', name: 'carDetName', index: "carDetName", width: 50 },
            { label: '当前售价', name: 'sellPrice', index: 'sellPrice', width:50},
            { label: '原始售价', name: 'orginPrice', index: 'orginPrice', width:50},
			{ label: '发动机', name: 'ceid', index: "ceid",width: 50 },
			{ label: '变速箱', name: 'gbid',index: "gbid", width: 50 },
			{ label: '车体结构', name: 'ctid',index: "ctid", width: 50 },
			{ label: '是否上牌', name: 'isLicence', width: 35, formatter: function(value, options, row){
            	if(value==""){
            		return value;
            	}else{
            		return value === "0" ?'否' :'是';
            	}
			}},
			{ label: '创建时间', name: 'createTime', index: "createTime", width: 85}
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
//限制只能输入小数
function num(obj) {  
    obj.value = obj.value.replace(/[^\d.]/g,""); //清除"数字"和"."以外的字符  
    obj.value = obj.value.replace(/^\./g,""); //验证第一个字符是数字而不是  
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");  
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数  

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
function getschoolList(fvcid,secid,ceid,gbid,ctid) {//获取下拉列表
	var shopid=JSON.parse(localStorage.getItem("user")).shopid;
	$("#bandName").selectpicker({  
        noneSelectedText : '请选择'  
    }); 
	$("#carName").selectpicker({  
        noneSelectedText : '请选择'  
    });
	$("#carEngine").selectpicker({  
        noneSelectedText : '请选择'  
    });
	$("#gearbox").selectpicker({  
        noneSelectedText : '请选择'  
    });
	$("#carType").selectpicker({  
        noneSelectedText : '请选择'  
    });
    $.ajax({
        url: baseURL + "vehi/getBanList?shopid="+shopid,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        async: false,
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
        	if(data.data.length == 0){
        		alert("暂无汽车品牌！");
        	}else{
        		$.each(data.data, function (i) {
        			$('#bandName.selectpicker').append("<option id='fcv' value=" + data.data[i].id + ">" + data.data[i].name + "</option>");
        			if(fvcid==undefined){
        				
        			}
        			$('#bandName').selectpicker('val',data.data[0].id);
    		    	document.getElementById("bandNames").value=data.data[0].name;
        		});
        		$("#bandName").selectpicker('refresh');
        	}
        },
        error: function (data) {
            alert("查询失败" + data); 
        }
    });
    var banname = document.getElementById("bandNames").value;
    if(fvcid==undefined){//如果是新增的时候默认为第一个	
    	bandName=banname;
    }else{//修改的时候默认获取传递进来的值
    	bandName=fvcid;
    }
    $.ajax({
        url: baseURL + "vehi/getCarList?bandName="+bandName+"&shopid="+shopid+"&carName="+carName,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        async: false,
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
        	if(data.data.length == 0){
        		alert("暂无汽车车型！");
        	}else{
        		$.each(data.data, function (i) {
        			$('#carName.selectpicker').append("<option id='clvl' value=" + data.data[i].id + ">" + data.data[i].name + "</option>");
        			if(secid==undefined){
        				document.getElementById("carNames").value=data.data[0].name;
        				carName=document.getElementById("carNames").value;
        			}else if(secid==data.data[i].id){
        				$('#carName').selectpicker('val',secid);
        				document.getElementById("carNames").value=data.data[i].name;
        				carName = document.getElementById("carNames").value;
        			}
        		});
        		$('#carName').selectpicker('refresh');
        		var carName = document.getElementById("carNames").value;
        		
        	}
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
    var carrname = document.getElementById("carNames").value;
    if(secid==undefined){//如果是新增的时候默认为第一个	
    	carName=carrname;
    }else{//修改的时候默认获取传递进来的值
    	carName=secid;
    }
    $.ajax({//获取发动机
   	 url: baseURL + "vehi/getCarEnList?shopid="+shopid+"&bandName="+bandName+"&carName="+carName,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        async: false,
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
        	$.each(data.data,function(j){
        		$('#carEngine.selectpicker').append("<option id='clvl' value=" + data.data[j].id + ">" + data.data[j].name + "</option>");
        		if(gbid==undefined){
        			document.getElementById("carEngines").value=data.data[0].name;
        		}else if(ceid==data.data[j].id){
    				$('#carEngine').selectpicker('val',ceid);
    				document.getElementById("carEngines").value=data.data[j].name;
    			}
        	})
        	$('#carEngine').selectpicker('refresh');
        },
        error: function (data) {
            alert("查询失败" + data);
        }
   });
    $.ajax({//获取变速箱
        url: baseURL + "vehi/getCarGearList?shopid="+shopid+"&bandName="+bandName+"&carName="+carName,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        async: false,
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
        	$.each(data.data,function(k){
        		$('#gearbox.selectpicker').append("<option id='clvl' value=" + data.data[k].id + ">" + data.data[k].name + "</option>");
        		if(gbid==undefined){
        			document.getElementById("gearboxs").value=data.data[0].name;
        		}else if(gbid==data.data[j].id){
    				$('#gearbox').selectpicker('val',gbid);
    				document.getElementById("gearboxs").value=data.data[j].name;
    			}
        	})
        	$('#gearbox').selectpicker('refresh');
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
    $.ajax({// 获取车型结构
        url: baseURL + "vehi/getCarTypeList?shopid="+shopid+"&bandName="+bandName+"&carName="+carName,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        async: false,
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
        	$.each(data.data,function(h){
        		$('#carType.selectpicker').append("<option id='clvl' value=" + data.data[h].id + ">" + data.data[h].name + "</option>");
        		if(ctid==undefined){
        			document.getElementById("carTypes").value=data.data[0].name;
        		}else if(ctid==data.data[h].id){
    				$('#carType').selectpicker('val',ctid);
    				document.getElementById("carTypes").value=data.data[h].name;
    			}
        	})
        	$('#carType').selectpicker('refresh');
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
}

//选择汽车品牌
function selectBandName(obj){
	document.getElementById("bandNames").value=obj.options[obj.selectedIndex].text;
	var bandName = obj.options[obj.selectedIndex].text;
	var shopid=JSON.parse(localStorage.getItem("user")).shopid;
	$("#carName").find("option").remove();
	$("#carEngine").find("option").remove();
	$("#gearbox").find("option").remove();
	$("#carType").find("option").remove();
	$.ajax({
        url: baseURL + "vehi/getCarList?shopid="+shopid+"&bandName="+bandName,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        async: false,
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
        	if(data.data.length == 0){
        		alert("暂无汽车车型！");
        	}else{
        		$.each(data.data, function (i) {
        			$('#carName.selectpicker').append("<option id='clvl' value=" + data.data[i].id + ">" + data.data[i].name + "</option>");
        		});
        		document.getElementById("carNames").value=data.data[0].name;
        		$('#carName').selectpicker('refresh');
        	}
        	var carName = document.getElementById("carNames").value;
        	$.ajax({//获取发动机
                url: baseURL + "vehi/getCarEnList?shopid="+shopid+"&bandName="+bandName+"&carName="+carName,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
                type: "get",//数据发送方式
                dataType: "json",//接受数据格式
                async: false,
                data: 'data',//要传递的数据
                success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
                	if(data.data.length == 0){
                		alert("暂无发动机型号！");
                		$('#carEngine').selectpicker('refresh');
                		$("#gearbox").selectpicker('refresh');
                		$("#carType").selectpicker('refresh');
                	}else{
                		$.each(data.data, function (j) {
                			$('#carEngine.selectpicker').append("<option id='clvl' value=" + data.data[j].id + ">" + data.data[j].name + "</option>");
                		});
                		document.getElementById("carEngines").value=data.data[0].name;
                		$('#carEngine').selectpicker('refresh');
                	}
                	$.ajax({//获取变速箱
                        url: baseURL + "vehi/getCarGearList?shopid="+shopid+"&bandName="+bandName+"&carName="+carName,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
                        type: "get",//数据发送方式
                        dataType: "json",//接受数据格式
                        async: false,
                        data: 'data',//要传递的数据
                        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
                        	if(data.data.length == 0){
                        		alert("暂无变速箱型号！");
                        		$("#gearbox").selectpicker('refresh');
                        		$("#carType").selectpicker('refresh');
                        	}else{
                        		$.each(data.data, function (k) {
                        			$('#gearbox.selectpicker').append("<option id='clvl' value=" + data.data[k].id + ">" + data.data[k].name + "</option>");
                        		});
                        		document.getElementById("gearboxs").value=data.data[0].name;
                        		$('#gearbox').selectpicker('refresh');
                        	}
                        	$.ajax({//获取车体结构
                                url: baseURL + "vehi/getCarTypeList?shopid="+shopid+"&bandName="+bandName+"&carName="+carName,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
                                type: "get",//数据发送方式
                                dataType: "json",//接受数据格式
                                async: false,
                                data: 'data',//要传递的数据
                                success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
                                	if(data.data.length == 0){
                                		alert("暂无车体结构型号！");
                                		$("#carType").selectpicker('refresh');
                                	}else{
                                		$.each(data.data, function (h) {
                                			$('#carType.selectpicker').append("<option id='clvl' value=" + data.data[h].id + ">" + data.data[h].name + "</option>");
                                		});
                                		document.getElementById("carTypes").value=data.data[0].name;
                                		$('#carType').selectpicker('refresh');
                                	}
                                	
                                },
                                error: function (data) {
                                    alert("查询失败" + data);
                                }
                            });
                        },
                        error: function (data) {
                            alert("查询失败" + data);
                        }
                    });
                },
                error: function (data) {
                    alert("查询失败" + data);
                }
            });
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
	document.getElementById("bandNames").value=obj.options[obj.selectedIndex].text;
}
//选择汽车车型
function selectCarName(obj){
	var bandName = document.getElementById("bandNames").value;
	document.getElementById("carNames").value=obj.options[obj.selectedIndex].text;
	var carName = obj.options[obj.selectedIndex].text;
	var shopid=JSON.parse(localStorage.getItem("user")).shopid;
	$("#carEngine").find("option").remove();
	$("#gearbox").find("option").remove();
	$("#carType").find("option").remove();
	$.ajax({
        url: baseURL + "vehi/getCarEnList?shopid="+shopid+"&bandName="+bandName+"&carName="+carName,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        async: false,
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
        	if(data.data.length == 0){
        		alert("暂无发动机型号！");
        		$('#carEngine').selectpicker('refresh');
        		$("#gearbox").selectpicker('refresh');
        		$("#carType").selectpicker('refresh');
        	}else{
        		$.each(data.data, function (j) {
        			$('#carEngine.selectpicker').append("<option id='clvl' value=" + data.data[j].id + ">" + data.data[j].name + "</option>");
        		});
        		document.getElementById("carEngines").value=data.data[0].name;
        		$('#carEngine').selectpicker('refresh');
        	}
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
	document.getElementById("carNames").value=obj.options[obj.selectedIndex].text;
}
//选择发动机
function selectcarEngine(obj){
	var value = obj.options[obj.selectedIndex].value;
	document.getElementById("carEngines").value=obj.options[obj.selectedIndex].text;
	var carEngine = obj.options[obj.selectedIndex].text;
	document.getElementById("carEngines").value=obj.options[obj.selectedIndex].text;
}
//选择变速箱
function selectgearbox(obj){
	var value = obj.options[obj.selectedIndex].value;
	document.getElementById("gearboxs").value=obj.options[obj.selectedIndex].text;
	var carEngine = obj.options[obj.selectedIndex].text;
	document.getElementById("gearboxs").value=obj.options[obj.selectedIndex].text;
}
//选择车体结构
function selectcarType(obj){
	var value = obj.options[obj.selectedIndex].value;
	document.getElementById("carTypes").value=obj.options[obj.selectedIndex].text;
	var carEngine = obj.options[obj.selectedIndex].text;
	document.getElementById("carTypes").value=obj.options[obj.selectedIndex].text;
}
//只能输入数字
function intOnly(){
	  var codeNum=event.keyCode;
	  if(codeNum==8||codeNum==37||codeNum==39||(codeNum>=48&&codeNum<=57)||codeNum==110){
	    event.returnValue=codeNum;
	  }else{
	    event.returnValue=false;
	  }
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
        	getschoolList();
        	vehicaledet:{imgs:[]};
        },
        update: function () {
        	var id = getSelectedRow();//根据点击行获得点击行的id（id为jsonReader: {id: "id" },)
        	var rowData = $("#jqGrid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
        	var shopid = getSelectedRow();
        	vm.showList = false;
        	vm.title = "修改";
        	//品牌编号
        	var fvcid=rowData.fvcid;
        	//车型编号
        	var secid=rowData.secid;
        	//发动机
        	var ceid = rowData.ceid;
        	//变速器
        	var gbid = rowData.gbid;
        	//车体结构
        	var ctid = rowData.ctid;
        	vm.getuser(shopid);
        	this.getRoleList();
        	getschoolList(fvcid,secid,ceid,gbid,ctid);
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
            var url = vm.vehicaledet.carId == null ? "vehi/setVehi" : "vehi/modiVehi?carId="+vm.vehicaledet.carId;
            //取值汽车品牌
            vm.vehicaledet.bandName=document.getElementById("bandNames").value;
            //取值汽车车型
            vm.vehicaledet.carName=document.getElementById("carNames").value;
            //取值发动机
            vm.vehicaledet.carEngine=document.getElementById("carEngines").value;
            //取值变速箱
            vm.vehicaledet.gearbox=document.getElementById("gearboxs").value;
            //取值车型结构
            vm.vehicaledet.carType=document.getElementById("carTypes").value;
            if(vm.vehicaledet.shopid==null){
            	alert("所属店铺不能为空！");
            }else
        	if(vm.vehicaledet.carDetName==null){
            	alert("车型名称不能为空！");
            }else
        	if(vm.vehicaledet.sellPrice==null){
             	alert("当前售价不能为空！");
            }else
        	if(vm.vehicaledet.orginPrice==null){
            	alert("原始价格不能为空！");
            }else{
        		$.ajax({
        			type: "POST",
        			url: baseURL + url,
        			cache: false,  
        		    contentType: false,  
        		    processData: false,
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
        getuser: function(shopid){
            $.get(baseURL + "vehi/getVehi?carId="+shopid, function(r){
                vm.vehicaledet = r.data;
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