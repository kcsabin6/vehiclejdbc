package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ProfileDao;
import com.dao.ProfileDaoImpl;
import com.servlet.dto.ProfileDTO;

@WebServlet("/auth")
public class AuthServlet  extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String pusername=req.getParameter("username");
			String ppassword=req.getParameter("password");
			
			ProfileDao profileDao = new ProfileDaoImpl();
			ProfileDTO profileDTO=profileDao.authUser(pusername,ppassword);
			   //adding profileDTO object inside request scope with name magic
			if(profileDTO!=null) {
					//page->request->session->application
				HttpSession session=req.getSession(true);//makes new session
				session.setAttribute("userData",profileDTO);
				
				//req.setAttribute("magic",profileDTO);
				req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
				
			}else {//user is not there
				req.setAttribute("hmmmm","Sorry, username and password are not correct");
				req.getRequestDispatcher("login.jsp").forward(req, resp);
			}
				
		
	}

}
