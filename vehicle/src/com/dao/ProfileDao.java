package com.dao;

import java.util.List;

import com.servlet.dto.ProfileDTO;

public interface ProfileDao {

	void deleteByUsername(String pusername);//

	String updateSignup(ProfileDTO profileDTO);//

	String createSignup(ProfileDTO profileDTO);//

	ProfileDTO findByUsername(String pusername);//

	List<String> findAllQualification();//

	List<ProfileDTO> filterProfiles(String filterText);//

	List<ProfileDTO> findAll();//

	List<ProfileDTO> searchProfiles(String search);//

	List<ProfileDTO> sortProfiles(String sort);//

	ProfileDTO authUser(String pusername, String ppassword);//

	ProfileDTO findByEmail(String pemail);

}
