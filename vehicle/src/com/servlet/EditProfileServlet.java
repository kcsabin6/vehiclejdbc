package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ProfileDao;
import com.dao.ProfileDaoImpl;
import com.servlet.dto.ProfileDTO;
import com.servlet.utils.DBUtils;

@WebServlet("/editProfile")
public class EditProfileServlet  extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String pusername=req.getParameter("username");  //<a href="editProfile?username=${profileDTO.username}">
			
			ProfileDao profileDao=new ProfileDaoImpl();
			ProfileDTO profileDTO=profileDao.findByUsername(pusername);
			req.setAttribute("profileDTO", profileDTO);
		
			req.getRequestDispatcher("esignup.jsp").forward(req, resp);
			
	}
}
