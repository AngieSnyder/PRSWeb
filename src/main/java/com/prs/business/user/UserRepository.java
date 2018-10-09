package com.prs.business.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Integer>{

	User findByUserNameAndPassword(String userName, String password);
	

}
