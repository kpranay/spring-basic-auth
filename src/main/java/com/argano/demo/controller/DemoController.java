package com.argano.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

	@GetMapping("/ping")
	public String ping() {
		return "Ping success";
	}
	
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	@GetMapping("/user-ping")
	public String userPing() {
		return "User Ping success";
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin-ping")
	public String adminPing() {
		return "Admin Ping success";
	}
}
