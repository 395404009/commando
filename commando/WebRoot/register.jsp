<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>道乎</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/style.css">
	    <script language="JavaScript" src="js/jquery.js"></script>
    <script language="javascript">
    $(document).ready(function(){
		//我的页面
		$("#mypage").click(function(){
			if(${users==null}){
	          alert("未登录,请先登录！");
			}
			if(${users!=null}){
	           window.top.location.href="myquestion";
			}
		});
		//退出功能
		$("#out").click(function(){
			var flag=window.confirm("你真的要退出吗?");
			if(flag){
				window.top.location.href="secede";
			}
		});
		//判断注册
		if(${reg==0}){
			alert("该用户名已被注册！");
		}else if(${reg==1}){
			alert("注册成功！");
		}
	});

    </script>
  </head>
  
  <body>
     <div class="top" >
     <div class="logo">
        <img src="images/logo.png" >
     </div>
     <div class="theme">道乎</div>
     <div class="bar">
      <span><a href="AllQuestions">首页</a></span>
       <span><a href="quesandanswer.jsp">发布问答</a></span>
       <span><a href="javascript:void(0)" id="mypage">关于我的</a></span>
     </div>
     <div class="login"> 
         <a href="login.jsp">登录 |</a><a href="register.jsp"> 注册 |</a><a href="javascript:void(0)" id="out"> 退出</a>
     </div>
      <div class="userphoto">
        <c:if test="${users!=null}">
          <img src="files/${users.photofile}" >
        </c:if>
        <c:if test="${users==null}">
             <!--   没有登录默认 -->
           <img src="files/pretermit.jpg" >
       </c:if>
     </div>
     <div class="log">
       <c:if test="${users!=null}">
                  欢迎${users.getUsername()}登录
       </c:if>
       <c:if test="${users==null}">
                   未登录
       </c:if>
     </div>
  </div>
  <form action="register" method="post" class="main_register"  enctype="multipart/form-data">
	 <fieldset>
        <span>用户注册</span>
        <ol>
            <li>
                <label >用户名：</label>
                <input name="username" type="text" required><span></span>
            </li>
             <li>
                <label >密码：</label>
                <input name="pwd" type="password" required><span></span>
            </li>
             <li>
                <fieldset>
                    
                    <ol>
                        <label>性别：</label>
                        <li>
                            <input  name="usex" type="radio" value="男" checked="checked">
                            <label >男</label>
                        </li>
                        <li>
                            <input name="usex" value="女" type="radio">
                            <label>女</label>
                        </li>
                    </ol>
                </fieldset>
            </li>
            <li>
                <label >头像：</label>
                <input name="file" type="file" required><span></span>
            </li>
        </ol>
    </fieldset>
    <fieldset>
       <input type="submit"  value="提交注册">
    </fieldset>
   </form>
 </body>
</html>
