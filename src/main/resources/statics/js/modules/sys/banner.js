$(function () {//加载数据	
	bannerImg();
    $("#jqGrid").jqGrid({
        url: baseURL + 'banner/getBanList',
        datatype: "json",
        colModel: [			
            { label: '轮播图编号', name: 'bannerid',index: "bannerid", width:100,key: true},
            { label: '轮播图标题', name: 'title', index: "title",width: 80},
            { label: '关联的网址', name: 'netUrl',sortable:false, width: 80},
            { label: '创建时间', name: 'createTime', index: "netUrl",width: 50},
            { label: '修改时间', name: 'modifyTime', index: "createTime",width: 50,hidden:true},
            { label: '废弃标志', name: 'deflg', width: 20, formatter: function(value, options, row){
            		return value ===0 ?
            			'<span class="label label-success">正常</span>':
                		'<span class="label label-danger">废弃</span>';
			}},
			{ label: '轮播图片', name: 'picUrl', width:60,align:"center", formatter: function(value, options, row){
        		return '<img class="img-responsive"style="width:130px;height:100px" src="'+value+'">';
			}}/*,
            { label: '平台首页的展示图片', name: 'picUrl',sortable:false, width:100},*/
        ],
		viewrecords: true,//是否要显示总记录数
        height: 450,
        rowNum: 6,
//		rowList : [6,30,50],
        rownumbers: true, //如果为ture则会在表格左边新增一列
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
function bannerImg(){
	$.ajax({
        url: baseURL + 'banner/getBanList?pagesize=6&pageno=1&order=asc',//写你自己的方法，返回map，我返回的map包含了两个属性：data：集合，total：集合记录数量，所以后边会有data.data的写法。。。
        type: "get",//数据发送方式
        dataType: "json",//接受数据格式
//        data: 'data',//要传递的数据
        success: function (data) {//回调函数，接受服务器端返回给客户端的值，即result值
//        	vm.bannerImg=data.data.dataList;
        	$.each(data.data.dataList, function (i) {
        		vm.bannerImg.push(data.data.dataList[i].picUrl);
        		//<img class="img-responsive" style="width:300px;height:225px;" src="http://52.80.16.16:8080/image/IMG_TGn7Gx8QsGqy6pw24gRIpCCLwVBY.jpg">
//        		console.log(data.data.dataList[i].picUrl);
        		if(i==0){
        			$('#inner').append("<div class='item active'><img style='width:200px;height:150px;margin:0 auto;' src='"+data.data.dataList[i].picUrl+"'></div>");
        		}else{
        			$('#inner').append("<div class='item'><img style='width:200px;height:150px;margin:0 auto;' src='"+data.data.dataList[i].picUrl+"'></div>");
        		}
        	});
//        	console.log(vm.bannerImg);
        },
        error: function (data) {
            alert("查询失败" + data);
        }
    });
}

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
        vehicaledet:{//轮播图对象
        	picUrl: ""  	
        },
        bannerImg:[]//轮播图集合
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
        	vm.vehicaledet = {imgs:{}};
        },
        update: function () {
        	var id = getSelectedRow();//根据点击行获得点击行的id（id为jsonReader: {id: "id" },)
        	if(id == null){
        		return false;
        	}else{
        		var rowData = $("#jqGrid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
            	var bannerid = getSelectedRow();
            	vm.showList = false;
            	vm.title = "修改";
            	vm.getuser(bannerid);
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
            var url = vm.vehicaledet.bannerid == null ? "shop/setShop" : "banner/upBan?bannerid="+vm.vehicaledet.bannerid;
            var imgs=vm.vehicaledet.imgs;
            if(vm.vehicaledet.deflg==null){
            	alert("废弃标志不能为空！");
            }/*else if(imgs.length<0){
            	alert("至少上传一张展示图片！");
            }*/else{
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
        getuser: function(bannerid){
            $.get(baseURL + "banner/getBanMap?bannerid="+bannerid, function(r){
                vm.vehicaledet = r.data;
                console.log(vm.vehicaledet)
                var imgContainer = $(".Bannerfile").parents(".z_photo"); //存放图片的父亲元素
                var input = $(".Bannerfile").parent();//文本框的父亲元素
            	numUp = imgContainer.find(".up-section").length;
                if(numUp>0){
                	$(".z_photo ").show();
                	$(".up-section ").show();
                	$(".bannerfl").hide();
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
        DelectImg: function (ObjDom){
        	//反向截取最后一个/后的图片
        	var index = ObjDom .lastIndexOf("\/");
    		ObjDom  = ObjDom .substring(index + 1, ObjDom .length);
//    		console.log(ObjDom)
    		var img = vm.vehicaledet.picUrl;
//    		console.log(img)
        	$.ajax({
    			type:"get",
    			url: baseURL + "uplo/deleteImages?imgName="+ObjDom,
    			cache: false,  
    		    contentType: false,  
    		    processData: false,
    			contentType: "application/json",
    			success:function(data){
    				vm.vehicaledet.picUrl= img.replace(img,"");
    				$(".up-section").hide();
    				$(".bannerfl").show();
    			}
    		});
        }
    }
});