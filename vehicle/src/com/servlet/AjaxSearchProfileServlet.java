package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ProfileDao;
import com.dao.ProfileDaoImpl;
import com.google.gson.Gson;
import com.servlet.dto.ProfileDTO;

@WebServlet("/ajaxSearchProfileServlet")
public class AjaxSearchProfileServlet  extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email=req.getParameter("email");
		 ProfileDao profileDao=new ProfileDaoImpl();
		ProfileDTO profileDTO= profileDao.findByEmail(email);
		
		//set content type
		resp.setContentType("application/json");//MIME type
		
		//convert object into json
		Gson gson=new Gson();
		String jsonData=gson.toJson(profileDTO);
		System.out.println(jsonData);
		//writing JSON String data in response
		//and sending back to the caller
		resp.getWriter().println(jsonData);
		
			
	}	

}
