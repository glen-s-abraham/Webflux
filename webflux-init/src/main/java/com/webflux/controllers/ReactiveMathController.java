package com.webflux.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webflux.dto.MultiplicationRequestDto;
import com.webflux.dto.Response;
import com.webflux.services.ReactiveMathService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactiveMath")
public class ReactiveMathController {
	@Autowired
	ReactiveMathService mathService;
	
	@GetMapping("square/{number}")
	public Mono<Response> findSquare(@PathVariable int number) {
		return this.mathService.findSquare(number);
	}
	
	@GetMapping("multiplicationTable/{number}")
	public Flux<Response> findMultiplicationTable(@PathVariable int number) {
		return this.mathService.getMultiplicationTable(number);
	}
	
	@GetMapping(value="multiplicationTable/stream/{number}", produces=MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Response> findMultiplicationTableAsStream(@PathVariable int number) {
		return this.mathService.getMultiplicationTable(number);
	}
	
	@PostMapping("multiply")
	public Mono<Response> multiply(@RequestBody Mono<MultiplicationRequestDto> dto){
		return this.mathService.multiply(dto);
	}
}
