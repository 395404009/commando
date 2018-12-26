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
	 * ��¼
	 * @param username
	 * @param pwd
	 * @return
	 */
	@RequestMapping("login")
	private String UserLogin(String username,String pwd,HttpSession session){
		//����service��
		//System.out.println(username+" "+pwd);
		Users users = userServiceImpl.UserLogin(username, pwd);
		//Users users = userServiceImpl.UserLogin("С��","123");
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
	 * �û�ע��
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
		//����ͼƬ
		users.setPhotofile(fileName);
		
		//�û�ע��
		int index = userServiceImpl.UserRegister(users);
		if(index==1){
			//ע��ɹ�
			session.setAttribute("reg", 1);
		}else if(index==0){
			//���û��ѱ�ע��
			session.setAttribute("reg", 0);
		}
		return"redirect:/register.jsp";
	}
	/**
	 * ��ȡ�����û������������Լ���
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
	 * ������������
	 * @param session
	 * @param qid
	 * @return
	 */
	@RequestMapping("comment")
	private String UserComment(HttpSession session,String qid){
		ArrayList<Userques> list = (ArrayList<Userques>) session.getAttribute("list");
		//����qid
		session.setAttribute("qid", qid);
		Userques useruq=null;
		for (Userques userques : list) { 
		    if(userques.getQuestions().getQid().equals(qid)){
		    	useruq=userques;
		    	break;
		    }
		}
		//����ָ���������������
		ArrayList<Queseval> ques = quesevalService.UserComment(qid);
		//��������
		session.setAttribute("comment", ques);
		//�����	����������Ϣ
		session.setAttribute("pageuq", useruq);
		return "redirect:/comment.jsp";
	}
	/**
	 * д����
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
		//�ж����۵ĳ���
		if(context.length()>25){
			//������ȴ���30ѡȡǰ30
			context=context.substring(0,25)+"...";
		}
		//��ȡ�����������id
	    String qid = (String) session.getAttribute("qid");
		//˫��session�е�qid
	    //session.removeAttribute("qid");
	    Users users = null;
		//��ȡ�û�id
		String userid = null;
		users = (Users) session.getAttribute("users");
		if(users==null||users.equals("")){
			//û�е�¼,��ת����¼ҳ��
			return "redirect:/comment.jsp?qid="+qid+"&falg="+0;
		}else{
			//��������
			userid = users.getUserid();
			int index = quesevalService.WriteComment(qid,context,userid);
			if(index==3){
				//���۳ɹ�
				return "redirect:/comment?qid="+qid;
			}else{
				//����ʧ��
				return "redirect:/commentError.jsp";
			}
		}
	}
	/**
	 * �˳���ǰ�û�
	 * @param session
	 * @return
	 */
	@RequestMapping("secede")
	private String Secede(HttpSession session){
		session.removeAttribute("users");
		return "redirect:/AllQuestions";
	}
	/**
	 * ����ʴ�
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
		//�ж�����
		if(question.length()>25){
			question=question.substring(0, 25)+"...";
		}
		//�жϴ�
		if(answer.length()>590){
			answer=answer.substring(0, 590);
		}
		
		//��ȡ�û���Ϣ
		Users users=null;
		users = (Users) session.getAttribute("users");
		//�ѵ�¼	
		int index= userquesService.RelQuestion(users,question,answer);
	    if(index==2){
			//�ɹ�
			return "redirect:/AllQuestions";
		}else{
			//ʧ��
			return "";
		}
		
	}
	@RequestMapping("myquestion")
	private String MyQuestion(HttpSession session){
		ArrayList<Userques> userqu = null;
		//��ȡ��¼�û�
		Users users = (Users) session.getAttribute("users");
		if(users==null||users.equals("")){
			//û�е�¼�����ܲ鿴�ҵ���Ϣ
			return "/index.jsp?falg="+0;
		}else{
			//���鿴�ҵ���Ϣ
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
	 * ɾ���û��Լ����ʴ�
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
