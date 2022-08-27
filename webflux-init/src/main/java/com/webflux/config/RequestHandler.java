package com.webflux.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webflux.dto.InputFailedValidationResponse;
import com.webflux.dto.MultiplicationRequestDto;
import com.webflux.dto.Response;
import com.webflux.exception.InputValidationException;
import com.webflux.services.ReactiveMathService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RequestHandler {
	
	@Autowired
	private ReactiveMathService mathService;
	
	
	public Mono<ServerResponse> getSquareHandler(ServerRequest req){
		Mono<Response> res= this.mathService.findSquare(Integer.valueOf(req.pathVariable("number")));
		return ServerResponse.ok().body(res,Response.class);
	}
	
	public Mono<ServerResponse> getMulTableHandler(ServerRequest req){
		Flux<Response> res= this.mathService.getMultiplicationTable(Integer.valueOf(req.pathVariable("number")));
		return ServerResponse.ok().body(res,Response.class);
	}
	
	public Mono<ServerResponse> getMulTableStreamHandler(ServerRequest req){
		Flux<Response> res= this.mathService.getMultiplicationTable(Integer.valueOf(req.pathVariable("number")));
		return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(res,Response.class);
	}
	
	public Mono<ServerResponse> getProductHandler(ServerRequest req){
		Mono<MultiplicationRequestDto> mulReq=req.bodyToMono(MultiplicationRequestDto.class);
		Mono<Response> res= this.mathService.multiply(mulReq);
		return ServerResponse.ok().body(res,Response.class);
	}
	
	public Mono<ServerResponse> getSquareHandlerWithValidations(ServerRequest req){
		int number =Integer.valueOf(req.pathVariable("number"));
		if(number<10||number>20)
			//return ServerResponse.badRequest().bodyValue(new InputFailedValidationResponse(number));
			return Mono.error(new InputValidationException(number));
		Mono<Response> res= this.mathService.findSquare(number);
		return ServerResponse.ok().body(res,Response.class);
	}
}
