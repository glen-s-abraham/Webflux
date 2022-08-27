package com.webflux.config;

import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.webflux.dto.InputFailedValidationResponse;
import com.webflux.exception.InputValidationException;

import reactor.core.publisher.Mono;

@Configuration
public class RouterConfig {
	
	@Autowired
	RequestHandler requestHandler;
	
	@Bean
	public RouterFunction<ServerResponse>routerPathFunction(){
		return RouterFunctions
				.route()
				.path("router", this::serverResponseRouterFunction)
				.build();
	}				
	
	
	private RouterFunction<ServerResponse>serverResponseRouterFunction(){
		return RouterFunctions.route()
				.GET("square/{number}",RequestPredicates.path("*/1?*"),requestHandler::getSquareHandler)
				.GET("square/{number}",req->ServerResponse.badRequest().bodyValue("Only 10-19 allowed"))
				.GET("table/{number}",requestHandler::getMulTableHandler)
				.GET("table/stream/{number}",requestHandler::getMulTableStreamHandler)
				.POST("multiply",requestHandler::getProductHandler)
				.GET("validate/square/{number}",requestHandler::getSquareHandlerWithValidations)
				.onError(InputValidationException.class, exceptionHandler())
				.build();
	}
	
	private BiFunction<Throwable,ServerRequest,Mono<ServerResponse>> exceptionHandler(){
		return (err,req)->{
			InputValidationException ex = (InputValidationException)err;
			InputFailedValidationResponse res = new InputFailedValidationResponse();
			res.setInput(ex.getInput());
			res.setErrorCode(ex.getErrorcode());
			res.setMessage(ex.getMessage());
			return ServerResponse.badRequest().bodyValue(res);
		};
	}
}
