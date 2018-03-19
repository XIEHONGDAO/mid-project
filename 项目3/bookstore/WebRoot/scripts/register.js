$(function(){

   //判断用户输入的 用户名是否存在
   
   //监听 input 的焦点事件
   $('#userName').blur(function(){
       
       //获取 input 的内容 判断 是否为空
       if($(this).val().length ==0 ){
           // 拿到 userName 下面的 span 进行 文本修改
           $(this).next().html('用户名不能为空');
       }else{
       
//    	   alert($('#userName').val());
          //拿到 内容  要进行ajax 和数据库中进行 判断
    	  $.ajax({
    		  type:'post',
    		  url:'http://localhost:8080/bookstore/user/checkname',
    		  data:{userName:$('#userName').val()},
    		  success:function(data){
    			  //没有这个用户
    			  //alert(data + (data == '0'));
    			  if(data == '0'){
    				  $('#userName').next().html('');
    			  }else{
    				  //存在这个用户
    				  $('#userName').next().html('这个名称已经存在');
    			  }
    		  }
    	  }); 
       }
       
   });

   //用户密码的输入
   $('#password').blur(function(){
	   
	   if($(this).val().length ==0 ){
           // 拿到 userName 下面的 span 进行 文本修改
           $(this).next().html('密码不能我空');
       }else{
    	   //密码的规则 6-20位字母数字组合：
    	   var reg = /^[A-Za-z0-9]{2,20}$/;
    	   if(reg.test($(this).val())){
    		   
    		   $(this).next().html('');
    		   
    	   }else{
    		   $(this).next().html('密码不符合规则');
    	   }
    	   
       }
   });
   
   //2次密码一致
  $('#rePassWord').blur(function(){
	   
	   if($(this).val().length ==0 ){
           // 拿到 userName 下面的 span 进行 文本修改
           $(this).next().html('密码不能我空');
       }else{
    	
    	   //2 次密码一致 
    	   if($(this).val()== $('#password').val()){
    		   
    		   $(this).next().html('');
    	   }else{
    		   $(this).next().html('2次密码不一致');
    	   }
    	   
       }
   });
  
  //邮箱的验证
  $('#email').blur(function(){
	   
	   if($(this).val().length ==0 ){
          // 拿到 userName 下面的 span 进行 文本修改
          $(this).next().html('邮箱不能为空');
      }else{
   	
       //邮箱的匹配
       var szReg=/^[A-Za-zd0-9]+([-_.][A-Za-zd0-9]+)*@+[A-Za-zd]{2,5}$/; 
   	   if(szReg.test($(this).val())){
   		   
   		   $(this).next().html('');
   		   
   	   }else{
   		   $(this).next().html('邮箱不符合规则');
   	   }
      }
  });
   
});