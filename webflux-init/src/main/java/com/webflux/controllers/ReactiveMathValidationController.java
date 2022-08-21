package com.webflux.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.dto.Response;
import com.webflux.exception.InputValidationException;
import com.webflux.services.ReactiveMathService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactiveMath")
public class ReactiveMathValidationController {
	@Autowired
	ReactiveMathService mathService;
	
	@GetMapping("square/{number}/throw")
	public Mono<Response> findSquare(@PathVariable int number) {
		if(number<10||number>20){
			throw new InputValidationException(number);
		}
		return this.mathService.findSquare(number);
	}
	
	@GetMapping("square/{number}/mono-error")
	public Mono<Response> monoError(@PathVariable int number) {
		return Mono.just(number)
				.handle((i,sink)->{
					if(i>=10 && i<=20) {
						sink.next(i);
					}
					else {
						sink.error(new InputValidationException(i));
					}
				}).cast(Integer.class)
				.flatMap(i->this.mathService.findSquare(i));
	}
}
