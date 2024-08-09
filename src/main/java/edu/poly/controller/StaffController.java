package edu.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/staffs")
public class StaffController {
	@RequestMapping("/list")
  public String listStaff() {
	  return "view-staffs";
  }
}
 