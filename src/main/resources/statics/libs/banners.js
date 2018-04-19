$(function(){
	var delParent;
	var defaults = {
		fileType         : ["jpg","png","bmp","jpeg"],   // 上传文件的类型
		fileSize         : 1024 * 1024 * 10                  // 上传文件的大小 10M
	};
		/*点击图片的文本框*/
	$(".Bannerfile").change(function(){
		var idFile = $(this).attr("id");
		var file = document.getElementById(idFile);
		var imgContainer = $(this).parents(".z_photo"); //存放图片的父亲元素
		var fileList = file.files; //获取的图片文件
		var input = $(this).parent();//文本框的父亲元素
		var pic = $('#Bannerfile')[0].files[0];
	     var fd = new FormData();
	     fd.append('file', pic);
	     $.ajax({  
	 	    url: baseURL + "uplo/uploadImages",  
	 	    type:"post",  
	 	    // Form数据  
	 	    data: fd,  
	 	    cache: false,  
	 	    contentType: false,  
	 	    processData: false,  
	 	    success:function(data){  
	 	        console.log("the data is : {}",data.data.pictureUrl);
	 	        vm.vehicaledet.picUrl += data.data.pictureUrl;
	 	       $(".up-section ").show();
	 	    }  
	 	});
		//遍历得到的图片文件
		var numUp = imgContainer.find(".up-section").length;
		var totalNum = numUp + fileList.length;  //总的数量
		if(fileList.length > 1){
			alert("上传图片数目不可以超过1个，请重新选择");  //一次选择上传超过1个 或者是已经上传和这次上传的到的总数也不可以超过1个
		}
		else if(numUp < 1){
			 fileList = validateUp(fileList);
			 for(var i = 0;i < fileList.length; ++i){
			     var imgUrl = window.URL.createObjectURL(fileList[i]);
			 }
		}
		setTimeout(function(){
             $(".up-section").removeClass("loading");
		 	 $(".up-img").removeClass("up-opcity");
		 },450);
		 numUp = imgContainer.find(".up-section").length;
		if(numUp >= 0){
			$(this).parent().hide();
		}
		
		//input内容清空
		$(this).val("");
	});
		
	function validateUp(files){
		var arrFiles = [];//替换的文件数组
		for(var i = 0, file; file = files[i]; i++){
			//获取文件上传的后缀名
			var newStr = file.name.split("").reverse().join("");
			if(newStr.split(".")[0] != null){
					var type = newStr.split(".")[0].split("").reverse().join("");
					if(jQuery.inArray(type, defaults.fileType) > -1){
						// 类型符合，可以上传
						if (file.size >= defaults.fileSize) {
							alert(file.size);
							alert('您这个"'+ file.name +'"文件大小过大');	
						} else {
							// 在这里需要判断当前所有文件中
							arrFiles.push(file);	
						}
					}else{
						alert('您这个"'+ file.name +'"上传类型不符合');	
					}
				}else{
					alert('您这个"'+ file.name +'"没有类型, 无法识别');	
				}
		}
		return arrFiles;
	}
	
})
