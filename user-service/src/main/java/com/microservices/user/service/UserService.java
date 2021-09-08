package com.microservices.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservices.user.VO.Department;
import com.microservices.user.VO.ResponseTemplateVO;
import com.microservices.user.entity.User;
import com.microservices.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	public ResponseTemplateVO getUserWithdepartment(Long userId) {
		
		ResponseTemplateVO vo = new ResponseTemplateVO();
		User user= userRepository.findById(userId).get();
		vo.setUser(user);
		
		Department dept = restTemplate.getForObject("http://DEPARTMENT-SERVICE/department/getDepartment/"+user.getDepartmentId(), Department.class );
		vo.setDepartment(dept);
		return vo;
		
	}
}
