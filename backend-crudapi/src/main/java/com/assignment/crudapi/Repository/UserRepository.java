package com.assignment.crudapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.crudapi.Model.UserModel;


public interface UserRepository extends JpaRepository<UserModel,String> {

}
