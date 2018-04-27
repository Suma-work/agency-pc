$(function () {//加载数据
    $("#jqGrid").jqGrid({
        url: baseURL + 'selcar/getSellCarList',
        datatype: "json",
        colModel: [			
            { label: '热销编号', name: 'hotid',index: "hotid", width: 90,key: true},
            { label: '店铺编号', name: 'shopId', index: "shopId",width: 30,hidden:true},
            { label: '店铺名称', name: 'shopName', index: "shopName",width: 60},
            { label: '车型编号', name: 'hotcarid', index: "hotcarid",width: 30,hidden:true},
            { label: '车型名称', name: 'hotcar', index: "hotcar",width: 80},
            { label: '车型类型', name: 'hottype',sortable:false, width: 50,hidden:true},
            { label: '起始售价', name: 'sellPrice', width:30, formatter: function(value, options, row){
        		return '<span class="label label-danger">¥ '+value+'</span>';
			}},
//            { label: '起始售价', name: 'sellPrice',sortable:false, width: 80},
            { label: '创建时间', name: 'createTime',sortable:false, width: 80},
            { label: '废弃标志', name: 'deflg', width: 20, formatter: function(value, options, row){
        		return value ===0 ?
        			'<span class="label label-success">正常</span>':
            		'<span class="label label-danger">废弃</span>';
            }},
            { label: '轮播图片', name: 'picAddress', width:60,align:"center", formatter: function(value, options, row){
        		return '<img class="img-responsive"style="width:130px;height:100px" src="'+value+'">';
			}}
        ],
		viewrecords: true,
        height: 700,
        rowNum: 5,
//		rowList : [5,30,50],
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
        },
        loadComplete:function(){//定义jqGrid高度，放置图片
            var grid = $("#jqGrid");
            var ids = grid.getDataIDs();
            for (var i = 0; i < ids.length; i++) {
                grid.setRowData ( ids[i], false, {height:67+i*2} );
            }}
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

function clickSelectDeptList(obj){//点击车型
	document.getElementById("vhid").value=obj.options[obj.selectedIndex].value;
	document.getElementById("vhname").value=obj.options[obj.selectedIndex].text;
}

function clickSelectShopList(obj){//点击店铺
	document.getElementById("shops").value=obj.options[obj.selectedIndex].value;
	var shopId = obj.options[obj.selectedIndex].value;
	$("#vhcicar").find("option").remove();
	$("#vhcicar").selectpicker({  
        noneSelectedText : '请选择'  
    }); 
	$("#vhcicar").selectpicker('refresh');
	console.log($("#vhcicar").text());
	$.ajax({
        url: baseURL + "selcar/getVhciCarList?shopId="+shopId,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
        	if(data.data.length == 0){
        		alert('暂无车型信息！', function(){
        			$("#vhcicar").find("option").remove();
        			document.getElementById("vhid").value='';
            		document.getElementById("vhname").value='';
				});
        	}else{
        		var id;
        		$.each(data.data, function (i) {
        			if(i==0){
        				id=data.data[i].id;
        				name=data.data[i].value;
        			}
        			$('#vhcicar.selectpicker').append("<option value=" + data.data[i].id + ">" + data.data[i].value + "</option>");
        		});
        		$('#vhcicar').selectpicker('val',id);
        		document.getElementById("vhid").value=id;
        		document.getElementById("vhname").value=name;
        		$("#vhcicar").selectpicker('refresh');
        	}
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
}

function selectShopList(shopId,hotcarid){//所有的店铺
	$("#shop").find("option").remove();
	$("#vhcicar").find("option").remove();
	$("#shop").selectpicker({  
        noneSelectedText : '请选择'  
    });
	$("#vhcicar").selectpicker({  
        noneSelectedText : '请选择'  
    });
	$("#shop").selectpicker('refresh');
	$("#vhcicar").selectpicker('refresh');
	$.ajax({
        url: baseURL + "selcar/getShopList",//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        async: false,
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
        	if(data.data.length == 0){
        		alert('暂无店铺信息！', function(){
        			$("#shop").find("option").remove();
				});
        	}else{
        		var id;
        		$.each(data.data, function (i) {
        			if(i==0){
        				id=data.data[i].id;
        			}
        			$('#shop.selectpicker').append("<option value=" + data.data[i].id + ">" + data.data[i].value + "</option>");
        		});
        		$('#shop').selectpicker('val',shopId);
        		document.getElementById("shops").value=shopId;
        		$("#shop").selectpicker('refresh');
        	}
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
	
	$.ajax({
        url: baseURL + "selcar/getVhciCarList?shopId="+shopId,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
        	if(data.data.length == 0){
        		alert('暂无车型信息！', function(){
        			$("#vhcicar").find("option").remove();
				});
        	}else{
        		var id;
        		$.each(data.data, function (i) {
        			if(i==0){
        				id=data.data[i].id;
        			}
        			if(hotcarid=data.data[i].id){
        				document.getElementById("vhname").value=data.data[i].value;
        			}
        			$('#vhcicar.selectpicker').append("<option value=" + data.data[i].id + ">" + data.data[i].value + "</option>");
        		});
        		$('#vhcicar').selectpicker('val',hotcarid);
        		document.getElementById("vhid").value=hotcarid;
        		$("#vhcicar").selectpicker('refresh');
        	}
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
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
            	var hotid = getSelectedRow();
            	vm.showList = false;
            	vm.title = "修改";
            	var shopId=rowData.shopId;
            	var hotcarid=rowData.hotcarid;
            	selectShopList(shopId,hotcarid);
//            	selectVhciCarList(hotcarid);
            	vm.getuser(hotid);
        	}
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
            var url = vm.vehicaledet.hotid == null ? "shop/setShop" : "selcar/updateHote?hotid="+vm.vehicaledet.hotid;
            //取值汽车车型
            vm.vehicaledet.shopId=document.getElementById("shops").value;
            vm.vehicaledet.hotcarid=document.getElementById("vhid").value;
            vm.vehicaledet.hotcar=document.getElementById("vhname").value;
            console.log(vm.vehicaledet);
            if(vm.vehicaledet.hotcar==""||vm.vehicaledet.hotcar==null){
            	alert("汽车车型不能为空！");
            }else if(vm.vehicaledet.hotcarid==""||vm.vehicaledet.hotcarid==null){
            	alert("汽车车型不能为空！");
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
        getuser: function(hotid){
            $.get(baseURL + "selcar/getSellCarMap?hotid="+hotid, function(r){
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