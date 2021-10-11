package com.dependra.restfullearning.restfullearning.dao;

import com.dependra.restfullearning.restfullearning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findById(int id);

}
