package edu.poly.repository;

import org.springframework.data.repository.CrudRepository;

import edu.poly.model.User;

public interface UserRepository extends CrudRepository<User, String> {

}
