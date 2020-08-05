
package com.profile.main;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.profile.ProfileDTO;
import com.profile.dao.ProfileDao;

public class ProfileMain {

	public static void main(String[] args) {
		
		//create the spring container
		ApplicationContext context = new ClassPathXmlApplicationContext("profile-dao.xml");
		
		//creating bean of the CarFactoryService
		//downcast to context(container reference) and get beans
		ProfileDao profileDao=(ProfileDao)context.getBean("profileDaoImpl");
		
		
		Scanner scanner =new Scanner(System.in);
		System.out.println("Enter the username");
		String pUsername=scanner.next();
		
		
		ProfileDTO profileDTO = new ProfileDTO();
		profileDTO.setEmail("a123@gmail.com");
		profileDTO.setGender("Male");
		profileDTO.setMobile("12345");
		profileDTO.setName("sabu");
		profileDTO.setPassword("test");
		profileDTO.setUsername(pUsername);
		profileDTO.setQualification("phd");
		profileDTO.setCreatedate(new Timestamp(new Date().getTime()));
		String status=profileDao.createSignup(profileDTO);
		System.out.println("Status"+status);
		
		
		List<ProfileDTO> profileDTOs= profileDao.findAll();
		for(ProfileDTO dto:profileDTOs){
		System.out.println(dto);
		}
		
		
	}

}


