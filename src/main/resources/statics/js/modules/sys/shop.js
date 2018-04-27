$(function () {//加载数据
	var deptid=JSON.parse(localStorage.getItem("user")).deptId;
	var username=JSON.parse(localStorage.getItem("user")).username;
	console.log(username);
	if(deptid==null){
		deptid="";
	}else if(deptid=="1"||deptid=="2"){
		deptid="";
	}
    $("#jqGrid").jqGrid({
        url: baseURL + 'shop/getShopList?loginDeptid='+deptid+'&username='+username,
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
            		return value === 0 ?
            		'<span class="label label-success">拥有</span>':
            		'<span class="label label-danger">不拥有</span>';
			}},
			{ label: '保养服务', name: 'upkeepEl', width: 35, formatter: function(value, options, row){
            		return value === 0 ?
            		'<span class="label label-success">拥有</span>':
                	'<span class="label label-danger">不拥有</span>';
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
        	if(id == null){
        		return false;
        	}else{
        		var rowData = $("#jqGrid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
            	var shopId = getSelectedRow();
            	vm.showList = false;
            	vm.title = "修改";
            	var deptid = rowData.dept;
            	selectDeptList(deptid);
            	vm.getuser(shopId);
        	}
        },
        del: function () {
            var userIds = getSelectedRows();
            var id = getSelectedRow();//根据点击行获得点击行的id（id为jsonReader: {id: "id" },）
        	var rowData = $("#jqGrid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
        	var shopId= rowData.shopId;
    		if(userIds == null){
    			return ;
    		}
    		confirm('确定要删除选中的记录？', function(){
    			$.ajax({
    				type: "GET",
    				url: baseURL + "shop/delectShop?shopId="+shopId,
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
        },
        DelectImg: function (index,ObjDom){
        	this.i = index;
        	var i = this.i;
        	//反向截取最后一个/后的图片
        	var index = ObjDom .lastIndexOf("\/");
    		ObjDom  = ObjDom .substring(index + 1, ObjDom .length);
    		var imgArr = vm.vehicaledet.imgs;
        	$.ajax({
    			type:"get",
    			url: baseURL + "uplo/deleteImages?imgName="+ObjDom,
    			cache: false,  
    		    contentType: false,  
    		    processData: false,
    			contentType: "application/json",
//    			data: ObjDom,
    			success:function(data){
    				imgArr.splice($.inArray(index,imgArr),1);
    				vm.vehicaledet.imgs = imgArr;
    				console.log(vm.vehicaledet.imgs)
    			}
    		});
        }
    }
});