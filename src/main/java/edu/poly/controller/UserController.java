package edu.poly.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.poly.model.User;
import edu.poly.service.UserService;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UserController {
	
   @Autowired
	UserService userService;
   @Autowired
   User userBean;
   
   @GetMapping("/register")
   public String addOrEdit(ModelMap model) {
	   User u = new User();
	   model.addAttribute("USER",u);
	   model.addAttribute("ACTION","saveOrUpdate");
	   return "register-user";
   }
   
   @PostMapping("/saveOrUpdate")
   public String saveOrUpdate(ModelMap model, @ModelAttribute("USER") User user) {
	  userService.save(user);
	   return "register-user";
   }
   
   @RequestMapping("/list")
   public String list(ModelMap model) {
	   model.addAttribute("USER", userService.findAll());
	   return "view-user";
   }
   
   @RequestMapping("/edit/{username}")
   public String edit(ModelMap model,
		   @PathVariable(name = "username") String username) {
	   Optional<User> u = userService.findById(username);
	   if (u.isPresent()) {
		model.addAttribute("USER", u.get());
	} else {
        model.addAttribute("USER", new User());
	}
	   model.addAttribute("ACTION", "/saveOrUpdate");
	   return "register-user";
   }
   
   @RequestMapping("/delete/{username}")
   public String delete(ModelMap model,
		   @PathVariable(name = "username") String username) {
	   userService.deleteById(username);
	   model.addAttribute("USER", userService.findAll());
	   return "view-user";
   }
   
   @RequestMapping("/login")
   public String showLogin() {
	   return "login";
   }
   
   @PostMapping("checkLogin")
   public String checkLogin(ModelMap model,@RequestParam("username")String username,
		   @RequestParam("password")String password, HttpSession session) {
	   if (username == null || username.trim().isEmpty() ||
	            password == null || password.trim().isEmpty()) {
	            model.addAttribute("ERROR", "Username and password cannot be empty");
	            return "login"; // The name of your login page view
	        }
	   if (userService.checkLogin(username, password)) {
		   session.setAttribute("USERNAME", username);
		model.addAttribute("USER", userService.findAll());
		   return "view-user";
	} else {
        model.addAttribute("ERROR", "Username or password not exist");
	}
	   return "login";
   }
   
   public String logout(HttpSession session) {
	   session.removeAttribute("USERNAME");
	   return "redirect:/login";
   }
}
