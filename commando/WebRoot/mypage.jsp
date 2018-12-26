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
	
	
	<link href="css/style.css" rel="stylesheet" type="text/css" />
		<script language="JavaScript" src="js/jquery.js"></script>
	<script language="javascript">
	$(document).ready(function(){

		//退出功能
		$("#out").click(function(){
			var flag=window.confirm("你真的要退出吗?");
			if(flag){
				window.top.location.href="secede";
			}
		});
		
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
  
	<c:if test="${myinfo==null }">
	     你还没有发布问答！
	</c:if>
	<c:if test="${myinfo!=null }">
	      <c:forEach items="${myinfo}" var="qu">
    <div class="main">
        <!--遍历问题 -->
     <div class="topic">
          <div class="photo"><img src="files/${qu.users.photofile}" ></div>
          <div class="username">
                <div class="us1"><span>${qu.users.username}</span></div>
                <div class="us2"><span>发布了想法</span></div>          
          </div>
          <div class="title">
              <a href="comment?qid=${qu.questions.qid}">${qu.questions.question }</a>
          </div>
          <div class="del">
              <a href="delete?qid=${qu.questions.qid}">删除该问答</a> 
          </div>
      </div>
      
      <div class="answer"><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${qu.questions.subanswer}</span></div> 
  </div>
   </c:forEach>
	</c:if>
  </body>
</html>
