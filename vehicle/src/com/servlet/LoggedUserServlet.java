package com.servlet;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ProfileDao;
import com.dao.ProfileDaoImpl;
import com.servlet.dto.ProfileDTO;

@WebServlet("/LoggedUser")
public class LoggedUserServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Set<ProfileDTO> loggedUsers = ProfileDTO.loggedInUser(); 
		request.setAttribute("profileDTOs",loggedUsers);
		request.getRequestDispatcher("loggedUsers.jsp").forward(request, response);
		
		//String pusername=request.getParameter("username");
		//ProfileDao profileDao = new ProfileDaoImpl();
		//profileDao.deleteByUsername(pusername);
		//request.getRequestDispatcher("profiles").forward(request, response);
		
	}

}
