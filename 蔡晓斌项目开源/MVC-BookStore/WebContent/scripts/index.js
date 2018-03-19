//输入用户名密码登录验证
function login(){
	var inputlen = $("input[name=userName]").val().length;
	var spanlen = $("input[name=userName]").next("span").html().length;
	if(inputlen>0){
		//用户名正确
		if($("input[name=passWord]").val().length>0  && spanlen==0){
			//密码不为空
			return true;
		}else{
			$("input[name=passWord]").next("span").html("密码不能为空！");
			return false;
		}
	}else{
		$("input[name=userName]").next("span").html("用户名不能为空！");
		return false;
	}
}

//用户名输入框失焦事件，验证用户名是否已被注册
function checkName(flag){
	//alert(flag);
	//alert($("input[name=userName]").val().length);
	if($("input[name=userName]").val().length==0){
		//alert("用户名不能为空！");
		$("input[name=userName]").next("span").html("用户名不能为空！");
		return false;
	}else{
		$("input[name=userName]").next("span").html("");
		$.ajax({
			url:"/MVC-BookStore/user/checkUserName",
			type:"post",
			data:{"userName":$("input[name=userName]").val()},
			dataType:"text",
			success:function(data){
				//alert(data);
				//alert(data=="0");
				if(data=="0"){
					if(flag=="r"){
						$("input[name=userName]").next("span").html("用户名已经被占用！");
					}
					return false;
				}else{
					if(flag=="l"){
						$("input[name=userName]").next("span").html("用户未注册！");
					}
					//alert(1888);
					return true;
				}
			}
		});
	}
}
function checkPassword(){
	//alert(123);
	if($("input[name=passWord]").val().length==0){
		//alert("密码不能为空！");
		$("input[name=passWord]").next("span").html("密码不能为空！");
		return false;
	}else{
		var pwd = /^[a-zA-Z0-9_]{4,16}$/;
		if(pwd.test($("input[name=passWord]").val())){
			//满足正则表达式
			$("input[name=passWord]").next("span").html("");
			return true;
		}else{
			$("input[name=passWord]").next("span").html("密码不满足条件！");
			return false;
			//$(this).val("");
		}
	}
}
//确认密码是否与密码一致
function checkRePwd(){
	if($("input[name=rePassWord]").val()!=$("input[name=passWord]").val()){
		$("input[name=rePassWord]").next("span").html("两次输入的密码不一致！");
		return false;
	}else{
		$("input[name=rePassWord]").next("span").html("");
		return true;
	}
}
//邮箱输入是否正确
function checkEmail(){
	if($("input[name=email]").val().length==0){
		//alert("邮箱不能为空！");
		$("input[name=email]").next("span").html("邮箱不能为空！");
		return false;
	}else{
		//正则表达式验证
		var zz = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
		if(zz.test($("input[name=email]").val())){
			$("input[name=email]").next("span").html("");
			return true;
		}else{
			$("input[name=email]").next("span").html("邮箱格式不合法！");
			return false;
		}
	}
}
//用户点击注册按钮，验证表单信息是否正确
function sbmit(){
	if($("input[name=userName]").val()=="" || $("input[name=userName]").val()==null){
		//alert("name-->"+!checkName());
		$("input[name=userName]").next("span").html("用户名不能为空！");
		return false;
	}else if(!checkPassword()){
		//alert("pwd-->"+!checkPassword());
		//$("input[name=passWord]").next("span").html("密码不能为空！");
		return false;
	}else if(!checkRePwd()){
		//alert(!checkRePwd());
		//$("input[name=rePassWord]").next("span").html("两次输入的密码不一致！");
		return false;
	}else if(!checkEmail()){
		//alert(!checkEmail());
		//$("input[name=email]").next("span").html("邮箱不能为空！");
		return false;
	}else{
		//alert(111);
		return true;
	}
}
/*$(function(){
	//用户名输入框失焦事件，验证用户名是否已被注册
	$("input[name=userName]").blur(function(){
		if($(this).val().length==0){
			//alert("用户名不能为空！");
			$(this).next("span").html("用户名不能为空！");
		}else{
			$(this).next("span").html("");
			$.ajax({
				url:"user/checkUserName",
				type:"post",
				data:{"userName":$(this).val()},
				dataType:"text",
				success:function(data){
					//alert(data);
					//alert(data=="0");
					if(data=="0"){
						$("input[name=userName]").next("span").html("用户名已经被占用！");
					}
				}
			});
		}
	});
	//验证密码是否满足条件
	$("input[name=passWord]").blur(function(){
		//alert(123);
		if($(this).val().length==0){
			//alert("密码不能为空！");
			$(this).next("span").html("密码不能为空！");
		}else{
			var pwd = /^[a-zA-Z0-9_]{4,16}$/;
			if(pwd.test($(this).val())){
				//满足正则表达式
				$(this).next("span").html("");
			}else{
				$(this).next("span").html("密码不满足条件！");
				//$(this).val("");
			}
		}
	});
	//确认密码是否与密码一致
	$("input[name=rePassWord]").blur(function(){
		if($(this).val()!=$("input[name=passWord]").val()){
			$(this).next("span").html("两次输入的密码不一致！");
		}else{
			$(this).next("span").html("");
		}
	});
	//邮箱输入是否正确
	$("input[name=email]").blur(function(){
		if($(this).val().length==0){
			//alert("邮箱不能为空！");
			$(this).next("span").html("邮箱不能为空！");
		}else{
			//正则表达式验证
			var zz = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
			if(zz.test($(this).val())){
				$(this).next("span").html("");
			}else{
				$(this).next("span").html("邮箱格式不合法！");
			}
		}
	});
});*/