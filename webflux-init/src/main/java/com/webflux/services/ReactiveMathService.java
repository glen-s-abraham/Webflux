package com.webflux.services;

import org.springframework.stereotype.Service;

import com.webflux.Utils.SleepUtils;
import com.webflux.dto.Response;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveMathService {
	public Mono<Response> findSquare(int number){
		return Mono.fromSupplier(()->number*number).map(v->new Response(v));
	}
	
	public Flux<Response> getMultiplicationTable(int number){
		return Flux.range(1, 10)
				.doOnNext(i->SleepUtils.sleepSeconds(1))
				.doOnNext(i->System.out.println("Reactive math service pocssing: "+i))
				.map(i->new Response(i*number));
				
	}
}
