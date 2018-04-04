$(function () {
	var shopid=JSON.parse(localStorage.getItem("user")).shopid;
	if(shopid==null){
		shopid="";
	}
    $("#jqGrid").jqGrid({
        url: baseURL + 'vehic/getVeMainList?shopId=' + shopid,
        datatype: "json",
        colModel: [			
			{ label: '主键编号', name: 'typeId', index: 'typeId', width: 25 },
			{ label: '品牌编号', name: 'uqId', index: 'uqId', width: 50 },	
			{ label: '店铺编号', name: 'shopId', index: 'shopId', width: 60,hidden:true},
			{ label: '所属店铺', name: 'shopName', index: 'shopName', width: 60},
			{ label: '汽车品牌', name: 'bandName', index: 'bandName', width:30}, 	
			{ label: '汽车品牌编号', name: 'fcvid', index: 'fcvid', width: 50,hidden:true}, 	
			{ label: '汽车车型', name: 'carName', index: 'carName', width: 80 }, 			
			{ label: '级别', name: 'rank', index: 'rank', width: 30 }, 			
			{ label: '变速箱', name: 'gearbox', index: 'gearbox', width: 30 },
			{ label: '车型结构', name: 'carType', index: 'carType', width: 30 },
			{ label: '发动机', name: 'carEngine', index: 'carEngine', width: 50 },
			{ label: '是否是热销', name: 'isHot', width: 30, formatter: function(value, options, row){
				return value === '0' ? 
					'<span class="label label-danger">不热销</span>' : 
					'<span class="label label-success">热销</span>';
			}},
			{ label: '车辆图片', name: 'picAddress', index: 'picAddress', width: 80 },
			{ label: '创建时间', name: 'createTime', index: 'createTime', width: 50 }
		],
		viewrecords: true,
//		loadonce:true,//客户端排序
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "data.dataList",//包含实际数据的数组
            page: "data.currPage",//当前页
//            page: 1,
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
function getschoolList(fid) {//获取下拉列表
	$("#bandName").find("option").remove();
	$("#carName").find("option").remove(); 
	var fids=fid;
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
		    if(fids==undefined){
		    	$('#bandName').selectpicker('val',1);
		    }else{
		    	$('#bandName').selectpicker('val',fids);
		    }
		    $("#bandName").selectpicker('refresh');
//            console.log("getschoolList->>>"+fid);
            
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
    if(fids==undefined){//如果是新增的时候默认为第一个
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
            });
//            $('#carName').selectpicker('val',fid);
            $('#carName').selectpicker('refresh');
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
}

function selectOnchang(obj){ 
	var value = obj.options[obj.selectedIndex].value;
	console.log(value);
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
//            $('#carName').selectpicker('val',fid);
            $('#carName').selectpicker('refresh');
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
}
var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
//            cusId: null
        	uqId: null
        },
		showList: true,
		title: null,
		vehiclemain: {},
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
	        vm.title = "新增";
	        vm.vehiclemain = {};
	        getschoolList();
//			vm.getVeMain();
		},
		update: function (event) {
//			var id = getSelectedRow();
			var id = getSelectedRow();//根据点击行获得点击行的id（id为jsonReader: {id: "id" },）
	        var rowData = $("#jqGrid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
	        var val= rowData.uqId; //获得制定列的值 （keyword 为colModel的name）
			if(val == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            vm.getInfo(val);
            var fcvid= rowData.fcvid;
//            console.log("update->>>>>>>"+fcvid);
            getschoolList(fcvid);
		},
		saveOrUpdate: function (event) {
			var url = vm.vehiclemain.id == null ? "sys/vehiclemain/save" : "sys/vehiclemain/update";
			$.ajax({
				type: "POST",
			    url: baseURL + url,
                contentType: "application/json",
			    data: JSON.stringify(vm.vehiclemain),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
						});
					}else{
						alert(r.msg);
					}
				}
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: baseURL + "sys/vehiclemain/delete",
                    contentType: "application/json",
				    data: JSON.stringify(ids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.msg);
						}
					}
				});
			});
		},
		getInfo: function(uqId){
			$.get(baseURL + "vehic/getVeMap?uqId="+uqId, function(r){
//				console.log(r.data);
                vm.vehiclemain = r.data;
//                vm.fcvid=vm.vehiclemain.fcvid;
//                fcvid=vm.vehiclemain.fcvid;
//                console.log("getInfo->>>>>>"+vm.fcvid+"->>>>>"+fcvid);
            });
		},
		reload: function (event) {//模糊查询
			console.log("返回");
			vm.showList = true;
//			console.log("reload->>>>"+document.getElementById("keyword").value);
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'keyword': document.getElementById("keyword").value},
                page:page
            }).trigger("reloadGrid");
		}
	}
});