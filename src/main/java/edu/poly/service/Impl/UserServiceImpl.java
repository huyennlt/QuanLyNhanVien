package edu.poly.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.poly.model.User;
import edu.poly.repository.UserRepository;
import edu.poly.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User save(User entity) {
		return userRepository.save(entity);
	}

	@Override
	public List<User> saveAll(List<User> entities) {
		return (List<User>) userRepository.saveAll(entities);
	}

	@Override
	public Optional<User> findById(String id) {
		return userRepository.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		return userRepository.existsById(id);
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public List<User> findAllById(List<String> ids) {
		return (List<User>) userRepository.findAllById(ids);
	}

	@Override
	public long count() {
		return userRepository.count();
	}

	@Override
	public void deleteById(String id) {
		userRepository.deleteById(id);
	}

	@Override
	public void delete(User entity) {
		userRepository.delete(entity);
	}

	@Override
	public void deleteAllById(List<String> ids) {
		userRepository.deleteAllById(ids);
	}

	@Override
	public void deleteAll(List<User> entities) {
		userRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
	@Override
	public boolean checkLogin(String username, String password) {
		Optional<User> optionalUser = findById(username);
		if (optionalUser.isPresent()&& optionalUser.get().getPassword().equals(password)) {
			return true;
		}
		return false;
	}
    
    
}
