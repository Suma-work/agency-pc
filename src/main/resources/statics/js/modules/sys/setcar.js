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
            { label: '店铺编号', name: 'shopId',index: "shopId", width: 50,hidden:true},
            { label: '所属店铺', name: 'shopName',sortable:false, width: 80 },
            { label: '汽车编号', name: 'fvcid', index: "fvcid",width: 50,hidden:true},
            { label: '汽车品牌', name: 'bandName', index: "carName",width: 50 },
            { label: '车型编号', name: 'secid', index: "secid",width: 50,hidden:true },
			{ label: '汽车车型', name: 'carName', index: "carName",width: 120 },
            { label: '车型名称', name: 'carDetName', index: "carDetName", width: 50 },
            { label: '当前售价', name: 'sellPrice', index: 'sellPrice', width:50},
            { label: '原始售价', name: 'orginPrice', index: 'orginPrice', width:50},
			{ label: '发动机', name: 'carEngine', index: "carEngine",width: 50 },
			{ label: '变速箱', name: 'gearbox',index: "gearbox", width: 50 },
			{ label: '车体结构', name: 'carType',index: "carType", width: 50 },
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
function getschoolList(fid,secid) {//获取下拉列表
	$("#bandName").find("option").remove();
	$("#carName").find("option").remove(); 
//	var fids=fid;
//	console.log(fid+"*********"+secid);
    $.ajax({
        url: baseURL + "vehic/getFvc",//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
        	$.each(data.data, function (i) {
                $('#bandName.selectpicker').append("<option id='fcv' value=" + data.data[i].id + ">" + data.data[i].name + "</option>");
            });
//            $('#carName').selectpicker('val',fid);
		    if(fid==undefined){
//		    	console.log(fids);
		    	$('#bandName').selectpicker('val',1);
		    	document.getElementById("bandNames").value=data.data[0].name;
		    }else{
		    	if(fid==1){
		    		$('#bandName').selectpicker('val',1);
		    		document.getElementById("bandNames").value=data.data[0].name;
		    	}else{
		    		$('#bandName').selectpicker('val',fid);
		    		document.getElementById("bandNames").value=data.data[fid-1].name;
		    	}
		    }
		    $("#bandName").selectpicker('refresh');
//            console.log("getschoolList->>>"+fid);
            
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
    var fids=undefined;
    if(fid==undefined){//如果是新增的时候默认为第一个	
    	fids=1;
    }else{//修改的时候默认获取传递进来的值
    	fids=fid;
    }
    $.ajax({
        url: baseURL + "vehic/getClvl?parentid="+fids,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
            $.each(data.data, function (i) {
                $('#carName.selectpicker').append("<option id='clvl' value=" + data.data[i].id + ">" + data.data[i].name + "</option>");
                if(secid==undefined){
//		    	console.log(fids);
                	document.getElementById("carNames").value=data.data[0].name;
                }else if(secid==data.data[i].id){
//                	console.log("secid:"+secid+"---data.data[i].id:"+data.data[i].id+"-----name:"+data.data[i].name)
                	$('#carName').selectpicker('val',secid);
                	document.getElementById("carNames").value=data.data[i].name;
//                	console.log(document.getElementById("carNames").value);
//                	document.getElementById("carNames").value=data.data[fid-1].name;
                }
            });
//            $('#carName').selectpicker('val',fid);
            $('#carName').selectpicker('refresh');
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
}


function selectBandName(obj){
	document.getElementById("bandNames").value=obj.options[obj.selectedIndex].text;
	var value = obj.options[obj.selectedIndex].value;
//	console.log(value);
	$("#carName").find("option").remove(); 
	$.ajax({
        url: baseURL + "vehic/getClvl?parentid="+value,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
            $.each(data.data, function (i) {
                $('#carName.selectpicker').append("<option id='clvl' value=" + data.data[i].id + ">" + data.data[i].name + "</option>");
            });
            document.getElementById("carNames").value=data.data[0].name;
            $('#carName').selectpicker('refresh');
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
	document.getElementById("bandNames").value=obj.options[obj.selectedIndex].text;
}
function selectCarName(obj){ 
	var value = obj.options[obj.selectedIndex].value;
//	console.log(value);
//	document.getElementById("bandNames").value=obj.options[obj.selectedIndex].text;
	document.getElementById("carNames").value=obj.options[obj.selectedIndex].text;
}
function intOnly(){
	  var codeNum=event.keyCode;
	  console.log(codeNum);
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
//            var shopid=JSON.parse(localStorage.getItem("user")).shopid;
//            console.log(shopid);
//            if(shopid==null){
//            	alert("只能商家发布新车！");
//            	vm.showList = true;
//            }else{
//            	vm.showList = false;
//            	vm.title = "新增";
//            	getschoolList();
//            }
            vm.showList = false;
        	vm.title = "新增";
        	getschoolList();
        },
        update: function () {
        	var id = getSelectedRow();//根据点击行获得点击行的id（id为jsonReader: {id: "id" },)
        	var rowData = $("#jqGrid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
        	var shopId = getSelectedRow();
//        	console.log(shopId);
        	vm.showList = false;
        	vm.title = "修改";
        	//品牌编号
        	var fvcid=rowData.fvcid;
        	//车型编号
        	var secid=rowData.secid;
        	vm.getuser(shopId);
//        	console.log(fvcid+"-------"+secid);
        	this.getRoleList();
        	getschoolList(fvcid,secid);
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
//            console.log("carId:"+vm.vehicaledet.carId+"----url:"+url);
            //取值汽车品牌
            vm.vehicaledet.bandName=document.getElementById("bandNames").value;
            //取值汽车车型
            vm.vehicaledet.carName=document.getElementById("carNames").value;
            
            var imgs=vm.vehicaledet.imgs;
            console.log(imgs);
            console.log("imgs->>>>>>>>>>>"+imgs);
            var idsel=$("#idsel").find("option:selected").text();
            //追加发动机
            vm.vehicaledet.carEngine=vm.vehicaledet.carEngine+idsel;
            if(vm.vehicaledet.sellPrice==null){
            	alert("当前售价不能为空！");
            }else if(vm.vehicaledet.carDetName==null){
            	alert("车型名称不能为空！");
            }else if(vm.vehicaledet.orginPrice==null){
            	alert("原始价格不能为空！");
            }else if(vm.vehicaledet.carEngine==null){
            	alert("发动机不能为空！");
            }else if(vm.vehicaledet.gearbox==null){
            	alert("变速箱不能为空！");
            }else if(vm.vehicaledet.carType==null){
            	alert("车体结构不能为空！");
            }else if(imgs.length<0){
            	alert("至少上传一张展示图片！");
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
        getuser: function(shopId){
            $.get(baseURL + "vehi/getVehi?carId="+shopId, function(r){
            	console.log(r);
                vm.vehicaledet = r.data;
                imgslength = vm.vehicaledet.imgs.length;
                if(imgslength>0){
                	var imgs = [];
                	for(var i=0;i<imgslength;i++){
                		imgs.push(vm.vehicaledet.imgs[i]);
                	}
                	vm.vehicaledet.imgs = imgs
                }
                //去除最后一位
                var carEn=vm.vehicaledet.carEngine;
                vm.vehicaledet.carEngine=carEn.slice(0,carEn.length-1);
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