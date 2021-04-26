package com.array.access.account.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.array.access.account.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

  User findById(long id);
  List<User> findByEmail(String email);
}