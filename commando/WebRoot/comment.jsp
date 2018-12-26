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
		//进行评价
		$("#comment").click(function(){
			if(${users==null}){
				alert("未登录，请先登录！");
			}
			if(${users!=null}){
				var context=$("input[name='context']").val();
				if(!context){
				   alert("评论不能为空！");
				}else{
					window.top.location.href="writecomment?context="+ encodeURI(encodeURI(context));
				}
			}	
		});
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
   <div class="maincomment">
        <!--遍历问题 -->
     <div class="topic">
          <div class="photo"><img src="files/${pageuq.users.photofile}" ></div>
          <div class="username">
                 <div class="us1"><span>${pageuq.users.username}</span></div>
                <div class="us2"><span>发布了想法</span></div>          
          </div>
          <div class="title">
              <a href="comment?qid=${pageuq.questions.qid}">${pageuq.questions.question }</a>
           </div>
      </div>
       <div class="answer"><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ${pageuq.questions.answer}</br></br></br></span></div>

  </div>
  <div class="commentinput">
        <div class="conTitle"> 评论</div>
<!--         <form action="writecomment" method="post"> -->
        <div class="con"><input type="text" name="context" placeholder="发布评论" ></div>
        <button class="sub" id="comment"><span>发布评论</span></button>
        </form>
  </div>
  
  <div class="allCon">
  <c:forEach items="${comment}" var="comment">
  <div class="comContext">
         <div class="photo"><img src="files/${comment.users.photofile}" ></div>
          <div class="username">
                 <div class="us1"><span>${comment.users.username}</span></div>
                <div class="us2"><span>发布了评论</span></div>          
          </div>
          <div class="title">
              ${comment.evaluates.context}
          </div>
         <div class="time">
              ${comment.evaluates.time}
         </div>
   </div>
   </c:forEach>
 </div>
  </body>
</html>
