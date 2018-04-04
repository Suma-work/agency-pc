//选择分类
function SeleItem(Index, ObjDom){
	if($(ObjDom).children("i").hasClass("active")){
		$(ObjDom).removeClass("active");
		$(ObjDom).children("i").removeClass("active");
		$(ObjDom).children("div.name").removeClass("active");
	}else{
		$(ObjDom).addClass("active");
		$(ObjDom).children("i").addClass("active");
		$(ObjDom).children("div.name").addClass("active");
	}
	if(($("li.apple").hasClass("active"))&&($("li.android").hasClass("active"))){
		$(ObjDom).parent("ul").addClass("active");
	}else{
		$(ObjDom).parent("ul").removeClass("active");
	}
};
/*var vm = new Vue({
    el:'#rrapp',
    data:{
    	showList: true,
    },
    methods: {
    	send: function () {
           cosole.log("11111111111111");
        }
    }
});	  */  

function send(){
	var pushTitle =$("#pushTitle option:selected").val();//文章标题
	var pushAtitle =$("#pushAtitle").val();//文章标题
	var pushMsg =$("#pushMsg").val();//内容
	var insterm = "";
	if($("li.android").hasClass("active")){//安卓
		insterm = 1;
	}else if($("li.apple").hasClass("active")){//苹果
		insterm = 2;
	}
	if($(".target-plattform ul").hasClass("active")){//二个端
		insterm = 3;
	}
	if(insterm == "" ){
		alert("请选择发送目标")
	}
	console.log("推送标题："+pushTitle);
	console.log("推送文章标题："+pushAtitle);
	console.log("推送内容:"+pushMsg);
	console.log("推送类型："+insterm);
	var push={};
	push.pushTitle=pushTitle;
	push.pushAtitle=pushAtitle;
	push.pushMsg=pushMsg;
	push.insterm=insterm;
	console.log(push);
	$.ajax({
		type: "POST",
		url: baseURL + 'push/getPush',
		contentType: "application/json",
		data: JSON.stringify(push),
		success: function(r){
			if(r.messageCode === "0"){
				alert('操作成功');
			}else{
				alert(r.messageStr);
			}
		}
	});
}