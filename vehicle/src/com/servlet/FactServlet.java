package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.servlet.dto.AppResponse;

@WebServlet("/fact")
public class FactServlet  extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		String num=req.getParameter("num");
		int n=Integer.parseInt(num);
		int sum=1;
		for(int x=2; x<=n; x++) {
			sum=sum*x;
		}
		resp.setContentType("application/json");//json type object
		AppResponse appResponse= new AppResponse();
		appResponse.setMessage("The Factorial of "+num+" is "+sum);
		appResponse.setResult(sum);
		appResponse.setNum(n);
		
		//convert object into json type object from Gson library
		Gson gson = new Gson();
		String jsonData=gson.toJson(appResponse);
		System.out.println(jsonData);
		resp.getWriter().println(jsonData);
		
			
	}

}
