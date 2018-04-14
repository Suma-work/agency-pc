$(function () {//加载数据
	var deptid=JSON.parse(localStorage.getItem("user")).deptId;
	if(deptid==null){
		deptid="";
	}else if(deptid=="1"||deptid=="2"){
		deptid="";
	}
    $("#jqGrid").jqGrid({
        url: baseURL + 'shop/getShopList?loginDeptid='+deptid,
        datatype: "json",
        colModel: [			
            { label: '店铺编号', name: 'shopId',index: "shopId", width: 150,key: true,height:500,align:"center"},
            { label: '店铺名称', name: 'shopName', index: "shopName",width: 80,height:500},
            { label: '大区编号', name: 'dept',sortable:false, width: 50,hidden:true,height:500},
            { label: '所属大区', name: 'name',sortable:false, width: 80,height:500},
            { label: '店铺电话', name: 'shopPhone', index: "shopPhone",width: 50,height:500},
            { label: '店铺地址', name: 'address', index: "address",width: 120,height:500},
            { label: '经度', name: 'lon', index: "lon", width: 50,hidden:true,height:500},
            { label: '纬度', name: 'lat', index: 'lat', width:50,hidden:true,height:500}
        ],
		viewrecords: true,
        height: 800,
        rowNum: 5,
		rowList : [5,30,50],
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
                grid.setRowData ( ids[i], false, {height:135+i*2} );
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

function clickSelectDeptList(obj){ 
	var id = obj.options[obj.selectedIndex].id;
	document.getElementById("depts").value=obj.options[obj.selectedIndex].value;
}

function upperCase(value){//获取地理位置
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
        	   vm.vehicaledet.address=data.data.formatted_address;//具体位置
        	   vm.vehicaledet.prvncnm=data.data.province;//省
        	   vm.vehicaledet.citynm=data.data.city;//市
        	   vm.vehicaledet.distnm=data.data.district;//区
        	   vm.vehicaledet.areacd=data.data.adcode;//地区编号
        	   vm.vehicaledet.lon=data.data.log;//经度
        	   vm.vehicaledet.lat=data.data.lat;//纬度
           }
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
}

function selectDeptList(deptid){//所属大区
	$("#dept").find("option").remove();
	$.ajax({
        url: baseURL + "shop/getDeptList?loginDeptid="+deptid,//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
        	if(data.data.length == 0){
        		alert("暂无大区信息！");
        	}else{
        		var id;
        		$.each(data.data, function (i) {
        			if(i==0){
        				id=data.data[i].id;
        			}
        			$('#dept.selectpicker').append("<option value=" + data.data[i].id + ">" + data.data[i].value + "</option>");
        		});
        		console.log(deptid);
        		if(deptid==1||deptid==2){//说明是管理员登录
        			$('#dept').selectpicker('val',id);
        			document.getElementById("depts").value=id;
        		}else{
        			if(deptid==id){
        				$('#dept').selectpicker('val',id);
        				document.getElementById("depts").value=id;
        			}else{
        				$('#dept').selectpicker('val',deptid);
        				document.getElementById("depts").value=deptid;
        			}
        		}
        		$("#dept").selectpicker('refresh');
        	}
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
        	var rowData = $("#jqGrid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
        	var shopId = getSelectedRow();
        	vm.showList = false;
        	vm.title = "修改";
        	var deptid = rowData.dept;
        	selectDeptList(deptid);
        	vm.getuser(shopId);
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
            	console.log(vm.vehicaledet);
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
            $.get(baseURL + "shop/getShopMap?shopId="+shopId, function(r){
                vm.vehicaledet = r.data;
            	imgslength = vm.vehicaledet.imgs.length;
                if(imgslength>0){
                	var imgs = [];
                	for(var i=0;i<imgslength;i++){
                		imgs.push(vm.vehicaledet.imgs[i]);
                	}
                	vm.vehicaledet.imgs = imgs
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