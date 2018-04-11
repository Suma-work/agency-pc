$(function () {
	var deptid=JSON.parse(localStorage.getItem("user")).deptId;
//	console.log(deptid);
	if(deptid==null){
		deptid="";
	}else if(deptid=="1"||deptid=="2"){
		deptid="";
	}
//	console.log(deptid);
    $("#jqGrid").jqGrid({
        url: baseURL + 'shop/getShopList?loginDeptid='+deptid,
        datatype: "json",
        colModel: [			
            { label: '店铺编号', name: 'shopId',index: "shopId", width: 150,key: true},
            { label: '店铺名称', name: 'shopName', index: "shopName",width: 80},
            { label: '大区编号', name: 'dept',sortable:false, width: 50,hidden:true },
            { label: '所属大区', name: 'name',sortable:false, width: 80 },
            { label: '店铺电话', name: 'shopPhone', index: "shopPhone",width: 50 },
            { label: '店铺地址', name: 'address', index: "address",width: 120},
            { label: '经度', name: 'lon', index: "lon", width: 50,hidden:true  },
            { label: '纬度', name: 'lat', index: 'lat', width:50,hidden:true },
            { label: '店铺类型', name: 'classify', width: 35, formatter: function(value, options, row){
            	if(value=="1"){
            		return '4S店';
            	}else if(value=="2"){
            		return '维保店';
            	}else if(value=="3"){
            		return '二手车机构店铺';
            	}
			}},
			{ label: '精品商品服务', name: 'refEl', width: 35, formatter: function(value, options, row){
            	/*if(value==""){
            		return value;
            	}else{*/
            		return value === 0 ?
            		'<span class="label label-success">拥有</span>':
            		'<span class="label label-danger">不拥有</span>';
//            	}
			}},
			{ label: '保养服务', name: 'upkeepEl', width: 35, formatter: function(value, options, row){
//            	if(value==""){
//            		return value;
//            	}else{
            		return value === 0 ?
            		'<span class="label label-success">拥有</span>':
                	'<span class="label label-danger">不拥有</span>';
//            	}
			}},
			{ label: '轮胎服务', name: 'tyreEl', width: 35, formatter: function(value, options, row){
            		return value === 0 ?
            		'<span class="label label-success">拥有</span>':
                    '<span class="label label-danger">不拥有</span>';
			}},
			{ label: '改装服务', name: 'refitEl', width: 35, formatter: function(value, options, row){
            		return value === 0 ?
            		'<span class="label label-success">拥有</span>':
                    '<span class="label label-danger">不拥有</span>';
			}},
			{ label: '安装服务', name: 'installEl', width: 35, formatter: function(value, options, row){
            		return value === 0 ?
            		'<span class="label label-success">拥有</span>':
                    '<span class="label label-danger">不拥有</span>';
			}},
			{ label: '废弃标志', name: 'delfg', width: 35, formatter: function(value, options, row){
            		return value === 0 ?
            		'<span class="label label-success">正常</span>':
                    '<span class="label label-danger">废弃</span>';
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

function upperCase(value){
	$.ajax({
        url: baseURL + "shop/getDistance?address="+value,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
           if(data.messageCode=="400"){
        	   alert(data.messageStr);  
        	   document.getElementById('address').value='';
           }else{
        	   document.getElementById('lon').value=data.data.log;
        	   document.getElementById('lat').value=data.data.lat;
        	   vm.shop.log=data.data.log;
        	   vm.shop.lat=data.data.lat;
           }
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
}

function selectDeptList(deptid){
	$("#dept").find("option").remove();
	$.ajax({
        url: baseURL + "shop/getDeptList?loginDeptid="+deptid,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
           $.each(data.data, function (i) {
//        	    console.log(data.data[i].id+"----"+data.data[i].value)
                $('#dept.selectpicker').append("<option value=" + data.data[i].id + ">" + data.data[i].value + "</option>");
//                console.log(data.data[i]);
            });
           	if(deptid==undefined){
//		    	console.log(fids);
		    	$('#dept').selectpicker('val',1);
		    	document.getElementById("depts").value=data.data[0].value;
		    }else{
		    	if(deptid==1){
		    		$('#dept').selectpicker('val',1);
		    		document.getElementById("depts").value=data.data[0].value;
		    	}else{
		    		$('#dept').selectpicker('val',deptid);
		    		document.getElementById("depts").value=data.data.value;
		    	}
		    }
           	$("#dept").selectpicker('refresh');
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
	var deptids=undefined;
    if(deptids==undefined){//如果是新增的时候默认为第一个	
    	deptids=1;
    }else{//修改的时候默认获取传递进来的值
    	deptids=deptid;
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
        shop:{
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
        },
        update: function () {
        	var id = getSelectedRow();//根据点击行获得点击行的id（id为jsonReader: {id: "id" },)
        	var rowData = $("#jqGrid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
        	var shopId = getSelectedRow();
//        	console.log(shopId);
        	vm.showList = false;
        	vm.title = "修改";
        	var deptid = rowData.dept;
        	selectDeptList(deptid);
        	vm.getuser(shopId);
//        	this.getRoleList();
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
            var url = vm.shop.carId == null ? "vehi/setVehi" : "uplo/uploadImages?carId="+vm.shop.carId;
//            console.log("carId:"+vm.shop.carId+"----url:"+url);
            
            var imgs=vm.shop.imgs;
//            console.log("imgs->>>>>>>>>>>"+imgs);
            if(vm.shop.sellPrice==null){
            	alert("当前售价不能为空！");
            }else if(vm.shop.carDetName==null){
            	alert("车型名称不能为空！");
            }else if(vm.shop.orginPrice==null){
            	alert("原始价格不能为空！");
            }else if(vm.shop.carEngine==null){
            	alert("发动机不能为空！");
            }else if(vm.shop.gearbox==null){
            	alert("变速箱不能为空！");
            }else if(vm.shop.carType==null){
            	alert("车体结构不能为空！");
            }else if(imgs.length<0){
            	alert("至少上传一张展示图片！");
            }else{
            		$.ajax({
            			type: "POST",
            			url: baseURL + url,
            			contentType: "application/json",
            			data: JSON.stringify(vm.shop),
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
            $.get(baseURL + "shop/getShopMap?shopId="+shopId, function(r){
            	console.log(r)
                vm.shop = r.data;
            
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