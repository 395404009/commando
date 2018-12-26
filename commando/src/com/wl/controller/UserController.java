package com.wl.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.wl.pojo.Queseval;
import com.wl.pojo.Userques;
import com.wl.pojo.Users;
import com.wl.service.QuesevalService;
import com.wl.service.QuestionsService;
import com.wl.service.UserService;
import com.wl.service.UserquesService;

@Controller
public class UserController {
	@Resource
	private UserService userServiceImpl;
	@Resource
	private UserquesService userquesService;
	@Resource
	private QuesevalService quesevalService;
	@Resource
	private QuestionsService questionsService;
	/**
	 * 登录
	 * @param username
	 * @param pwd
	 * @return
	 */
	@RequestMapping("login")
	private String UserLogin(String username,String pwd,HttpSession session){
		//请求service层
		//System.out.println(username+" "+pwd);
		Users users = userServiceImpl.UserLogin(username, pwd);
		//Users users = userServiceImpl.UserLogin("小明","123");
		//System.out.println(users);
		if(users!=null&&!users.equals(null)){
			session.setAttribute("users", users);
			return"redirect:/AllQuestions";
		}else{
			session.setAttribute("login",0);
			return"redirect:/login.jsp";
		}
		
	}
	/**
	 * 用户注册
	 * @param session
	 * @param username
	 * @param pwd
	 * @param sex
	 * @param photofile
	 * @return
	 */
	@RequestMapping("register")
	private String Register(HttpSession session,Users users,MultipartFile file,HttpServletRequest req){
		String fileName = UUID.randomUUID().toString()+file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String path = req.getServletContext().getRealPath("files")+"/"+fileName;
		try {
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//保存图片
		users.setPhotofile(fileName);
		
		//用户注册
		int index = userServiceImpl.UserRegister(users);
		if(index==1){
			//注册成功
			session.setAttribute("reg", 1);
		}else if(index==0){
			//该用户已被注册
			session.setAttribute("reg", 0);
		}
		return"redirect:/register.jsp";
	}
	/**
	 * 获取所有用户的所有问题以及答案
	 * @return
	 */
	@RequestMapping("AllQuestions")
	private String AllQuestions(HttpSession session){
		ArrayList<Userques> list=userquesService.AllQuestions();
		for(Userques userques:list){
			int str=userques.getQuestions().getAnswer().length();
			if(str>120){
				userques.getQuestions().setSubanswer(userques.getQuestions().getAnswer().substring(0, 120)+"......");
			}else{
				userques.getQuestions().setSubanswer(userques.getQuestions().getAnswer());
			}
		}
		session.setAttribute("list", list);
		return "redirect:/index.jsp";
	}
    
	/**
	 * 查找所有评论
	 * @param session
	 * @param qid
	 * @return
	 */
	@RequestMapping("comment")
	private String UserComment(HttpSession session,String qid){
		ArrayList<Userques> list = (ArrayList<Userques>) session.getAttribute("list");
		//保存qid
		session.setAttribute("qid", qid);
		Userques useruq=null;
		for (Userques userques : list) { 
		    if(userques.getQuestions().getQid().equals(qid)){
		    	useruq=userques;
		    	break;
		    }
		}
		//查找指定问题的所有评价
		ArrayList<Queseval> ques = quesevalService.UserComment(qid);
		//所有评价
		session.setAttribute("comment", ques);
		//问题的	其它所有信息
		session.setAttribute("pageuq", useruq);
		return "redirect:/comment.jsp";
	}
	/**
	 * 写评论
	 * @param context
	 * @param session
	 * @return
	 */
	@RequestMapping("writecomment")
	private String WriteComment(String context,HttpSession session){
		//System.out.println(context);
		try {
			context= java.net.URLDecoder.decode(context, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//判断评论的长度
		if(context.length()>25){
			//如果长度大于30选取前30
			context=context.substring(0,25)+"...";
		}
		//获取所评价问题的id
	    String qid = (String) session.getAttribute("qid");
		//双方session中的qid
	    //session.removeAttribute("qid");
	    Users users = null;
		//获取用户id
		String userid = null;
		users = (Users) session.getAttribute("users");
		if(users==null||users.equals("")){
			//没有登录,跳转到登录页面
			return "redirect:/comment.jsp?qid="+qid+"&falg="+0;
		}else{
			//进行评论
			userid = users.getUserid();
			int index = quesevalService.WriteComment(qid,context,userid);
			if(index==3){
				//评价成功
				return "redirect:/comment?qid="+qid;
			}else{
				//评价失败
				return "redirect:/commentError.jsp";
			}
		}
	}
	/**
	 * 退出当前用户
	 * @param session
	 * @return
	 */
	@RequestMapping("secede")
	private String Secede(HttpSession session){
		session.removeAttribute("users");
		return "redirect:/AllQuestions";
	}
	/**
	 * 提出问答
	 * @param session
	 * @return
	 */
	@RequestMapping("quesandanswer")
	private String QuesAndAnswer(HttpSession session,String question,String answer){
		try {
			question= java.net.URLDecoder.decode(question, "UTF-8");
			answer= java.net.URLDecoder.decode(answer, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//判断问题
		if(question.length()>25){
			question=question.substring(0, 25)+"...";
		}
		//判断答案
		if(answer.length()>590){
			answer=answer.substring(0, 590);
		}
		
		//获取用户信息
		Users users=null;
		users = (Users) session.getAttribute("users");
		//已登录	
		int index= userquesService.RelQuestion(users,question,answer);
	    if(index==2){
			//成功
			return "redirect:/AllQuestions";
		}else{
			//失败
			return "";
		}
		
	}
	@RequestMapping("myquestion")
	private String MyQuestion(HttpSession session){
		ArrayList<Userques> userqu = null;
		//获取登录用户
		Users users = (Users) session.getAttribute("users");
		if(users==null||users.equals("")){
			//没有登录，不能查看我的信息
			return "/index.jsp?falg="+0;
		}else{
			//看查看我的信息
			userqu = userquesService.myQuestion(users);
			for(Userques userques:userqu){
				int str=userques.getQuestions().getAnswer().length();
				if(str>120){
					userques.getQuestions().setSubanswer(userques.getQuestions().getAnswer().substring(0, 120)+"......");
				}else{
					userques.getQuestions().setSubanswer(userques.getQuestions().getAnswer());
				}
			}
			session.setAttribute("myinfo", userqu);
			return "/mypage.jsp";
		}
	}
	/**
	 * 删除用户自己的问答
	 * @param session
	 * @return
	 */
	@RequestMapping("delete")
	private String deleteQues(HttpSession session,String qid){
		//System.out.println(qid);
		int index = questionsService.delUserQuestions(qid);
		
		
		return"redirect:/AllQuestions";
	}
}
