package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ProfileDao;
import com.dao.ProfileDaoImpl;
import com.servlet.dto.ProfileDTO;
import com.servlet.utils.DBUtils;

@WebServlet("/sortProfile")
public class SortProfilesServlet  extends HttpServlet{
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String sort=req.getParameter("sort");
		
		ProfileDao profileDao=new ProfileDaoImpl();
		List<ProfileDTO> profileDTOs=profileDao.sortProfiles(sort);
		  
		   //adding profileDTO object inside request scope with namemagic
		req.setAttribute("listoptions", profileDao.findAllQualification()); 
		   req.setAttribute("profileDTOs", profileDTOs);
		   req.getRequestDispatcher("profiles.jsp").forward(req, resp);
	}	

}
