package com.eazybytes.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class FallbackController {
  
  @RequestMapping("/contactSupport")
  public Mono<String> contactSupport() { // mono는 비동기적으로 작업 결과를 처리하기 위해 사용된다.
    return Mono.just("An error occured. Please try after some time or contact supprot team!!!");
  }
}
