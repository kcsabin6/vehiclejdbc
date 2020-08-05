package com.profile.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.profile.ProfileDTO;




@Repository // @Service, @Component, @Controller
public class ProfileDaoImpl implements ProfileDao{
	
	@Autowired
	@Qualifier("pdataSource")
	private DataSource datasource;
	
	JdbcTemplate jdbcTemplate=null;
	
	@PostConstruct //this annotation ensures this method will be called for sure only once after all the beans creation
	public void magicMethod(){
		//because of this line JdbcTemplate can talk to database
		jdbcTemplate = new JdbcTemplate(datasource);
	}
	

	@Override
	public ProfileDTO findByEmail(String pemail) {
		
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl where email=?";
		Object [] data={pemail};
		ProfileDTO profileDTO=jdbcTemplate.queryForObject(sql,data,new BeanPropertyRowMapper<>(ProfileDTO.class));
		return profileDTO;
		/*List<ProfileDTO> profileDTOs=jdbcTemplate.query(sql,data,new BeanPropertyRowMapper(ProfileDTO.class));
		return profileDTOs.get(0);*/
	}

	@Override
	public List<ProfileDTO> sortProfiles(String sort) {
		
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl order by email "
				+ sort;
		List<ProfileDTO> profileDTOs=jdbcTemplate.query(sql,new BeanPropertyRowMapper<ProfileDTO>(ProfileDTO.class));
		
		return profileDTOs;
	}

	@Override
	public List<ProfileDTO> searchProfiles(String search) {
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl where name like '%"
				+ search + "%' or qualification like '%" + search + "%'";

		List<ProfileDTO> profileDTOs=jdbcTemplate.query(sql, new BeanPropertyRowMapper<ProfileDTO>(ProfileDTO.class));
		
		
		return profileDTOs;
	}

	@Override
	public List<ProfileDTO> findAll() {
	
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl";
		
		List<ProfileDTO> profileDTOs=jdbcTemplate.query(sql, new BeanPropertyRowMapper<ProfileDTO>(ProfileDTO.class));
		
		return profileDTOs;
	}

	@Override
	public List<ProfileDTO> filterProfiles(String filterText) {
		
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl  where qualification = ?";
		Object [] data={filterText};
		List<ProfileDTO> profileDTOs=jdbcTemplate.query(sql,data,new BeanPropertyRowMapper<ProfileDTO>(ProfileDTO.class));
		
		return profileDTOs;
	}

	@Override
	public List<String> findAllQualification() {
		//List<String> qualifications = new ArrayList<>();
		String sql = "select distinct qualification from user_login_tbl ";
		
		List<String> qualifications=jdbcTemplate.query(sql, new BeanPropertyRowMapper<String>(String.class));
		
		return qualifications;
	}

	@Override
	public ProfileDTO findByUsername(String pusername) {
		//ProfileDTO profileDTO = null;
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl where username=?";
		Object [] data={pusername};
		
		ProfileDTO profileDTO=jdbcTemplate.queryForObject(sql, data,new BeanPropertyRowMapper<>(ProfileDTO.class));
		return profileDTO;
	
	}

	@Override
	public String createSignup(ProfileDTO profileDTO) {
		String sql = "insert into  user_login_tbl(username,password,name,email,qualification,mobile,photo,gender,createdate) values(?,?,?,?,?,?,?,?,?)";
		Object [] data={profileDTO.getUsername(),profileDTO.getPassword(),profileDTO.getName(),profileDTO.getEmail(),profileDTO.getQualification(),
				profileDTO.getMobile(),profileDTO.getPhoto(),profileDTO.getGender(),new Timestamp(new Date().getTime())};
		
		jdbcTemplate.update(sql, data);
		
		
		return "success";
	}

	@Override
	public String updateSignup(ProfileDTO profileDTO) {
		String sql = "update user_login_tbl set name=?,email=?,qualification=?,mobile=?,photo=?,gender=? where username=?";
		
		Object [] data ={profileDTO.getName(),profileDTO.getEmail(),profileDTO.getQualification(),profileDTO.getMobile()
				,profileDTO.getPhoto(),profileDTO.getGender(),profileDTO.getUsername()};
		
		jdbcTemplate.update(sql,data);
				
		return "success";

	}

	@Override
	public void deleteByUsername(String pusername) {
		String sql = "delete from user_login_tbl where username=?";
		Object [] data={pusername};
		
		jdbcTemplate.update(sql, data);
	}

	@Override
	public ProfileDTO authUser(String pusername, String ppassword) {
		//ProfileDTO profileDTO = null;
		
		Object [] data={pusername,ppassword};
		String sql = "select username,password,name,email,qualification,mobile,photo,gender,createdate from user_login_tbl where username=? and password=?";
		
		ProfileDTO profileDTO=(ProfileDTO) jdbcTemplate.queryForObject(sql,data, new BeanPropertyRowMapper<>());
		
		return profileDTO;
	}


	
	
	
	
	
	
	@Override
	public void show(){
		try{Connection conn= datasource.getConnection();
		System.out.println("connetion made... "+conn);}
		catch (SQLException e){e.printStackTrace();}

	}
	

}
