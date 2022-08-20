package com.webflux.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.dto.Response;
import com.webflux.services.MathService;

@RestController
@RequestMapping("math")
public class MathController {
	@Autowired
	private MathService mathService;
	
	@GetMapping("square/{number}")
	public Response findSquare(@PathVariable int number) {
		return this.mathService.findSquare(number);
	}
	
	@GetMapping("multiplicationTable/{number}")
	public List<Response> findMultiplicationTable(@PathVariable int number) {
		return this.mathService.getMultiplicationTable(number);
	}

}
