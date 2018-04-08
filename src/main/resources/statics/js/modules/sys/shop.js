$(function () {
	var deptid=JSON.parse(localStorage.getItem("user")).deptId;
	console.log(deptid);
	if(deptid==null){
		deptid="";
	}/*else if(deptid=="1"||deptid=="2"){
		deptid="";
	}*/
	console.log(deptid);
    $("#jqGrid").jqGrid({
        url: baseURL + 'vehi/getVehiList?deptid='+deptid,
        datatype: "json",
        colModel: [			
            { label: '店铺主键', name: 'id', index: "id", width: 35, key: true },
            { label: '店铺编号', name: 'shopId',index: "shopId", width: 50},
            { label: '所属大区', name: 'dept',sortable:false, width: 80 },
            { label: '店铺名称', name: 'shopName', index: "shopName",width: 50},
            { label: '店铺电话', name: 'shopPhone', index: "shopPhone",width: 50 },
            { label: '店铺地址', name: 'address', index: "address",width: 50},
            { label: '经度', name: 'lon', index: "lon", width: 50 },
            { label: '纬度', name: 'lat', index: 'lat', width:50},
            { label: '店铺类型', name: 'classify', index: 'classify', width:50},
			{ label: '精品商品服务', name: 'refEl', width: 35, formatter: function(value, options, row){
            	if(value==""){
            		return value;
            	}else{
            		return value === "0" ?'不拥有' :'拥有';
            	}
			}},
			{ label: '保养服务', name: 'upkeepEl', width: 35, formatter: function(value, options, row){
            	if(value==""){
            		return value;
            	}else{
            		return value === "0" ?'不拥有' :'拥有';
            	}
			}},
			{ label: '轮胎服务', name: 'tyreEl', width: 35, formatter: function(value, options, row){
            	if(value==""){
            		return value;
            	}else{
            		return value === "0" ?'不拥有' :'拥有';
            	}
			}},
			{ label: '改装服务', name: 'refitEl', width: 35, formatter: function(value, options, row){
            	if(value==""){
            		return value;
            	}else{
            		return value === "0" ?'不拥有' :'拥有';
            	}
			}},
			{ label: '安装服务', name: 'installEl', width: 35, formatter: function(value, options, row){
            	if(value==""){
            		return value;
            	}else{
            		return value === "0" ?'不拥有' :'拥有';
            	}
			}},
			{ label: '废弃标志', name: 'delfg', width: 35, formatter: function(value, options, row){
            	if(value==""){
            		return value;
            	}else{
            		return value === "0" ?'废弃' :'正常';
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
        },
        update: function () {
        	var id = getSelectedRow();//根据点击行获得点击行的id（id为jsonReader: {id: "id" },)
        	var rowData = $("#jqGrid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
        	console.log(rowData);
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
            var url = vm.vehicaledet.carId == null ? "vehi/setVehi" : "uplo/uploadImages?carId="+vm.vehicaledet.carId;
//            console.log("carId:"+vm.vehicaledet.carId+"----url:"+url);
            
            var imgs=vm.vehicaledet.imgs;
            console.log("imgs->>>>>>>>>>>"+imgs);
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
                vm.vehicaledet = r.data;
            
            });
        },
        getRoleList: function(){
            $.get(baseURL + "sys/role/select", function(r){
                vm.roleList = r.list;
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