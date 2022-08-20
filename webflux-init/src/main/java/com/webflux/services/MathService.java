package com.webflux.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.webflux.Utils.SleepUtils;
import com.webflux.dto.Response;

@Service
public class MathService {
	public Response findSquare(int number) {
		return new Response(number*number);
	}
	
	public List<Response> getMultiplicationTable(int number){
		return IntStream.rangeClosed(1, 10)
				.peek(i->SleepUtils.sleepSeconds(1))
				.peek(i->System.out.println("Math-service processing :"+i))
				.mapToObj(i->new Response(i*number))
				.collect(Collectors.toList());
	}
}
