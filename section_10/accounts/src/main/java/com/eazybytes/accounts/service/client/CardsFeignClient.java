package com.eazybytes.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.eazybytes.accounts.dto.CardsDto;

// feignclient 어노테이션 명시 시 ureka 서버가 cards에 접근해서 등록된 엔드포인트들의 데이터를 가지고 와 캐시에 넣는다
// 일정 기간 동안 해당 엔드포인트로 accounts내에서 요청이 일어나면 캐시에 저장된 데이터를 사용한다

@FeignClient(name = "cards", fallback = CardsFallback.class) // ureka dashboard에 등록된 서비스명으로 적어야됨
public interface CardsFeignClient {
 
  @GetMapping(value = "/api/fetch", consumes = "application/json")
  public ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader("eazybank-correlation-id") String correlationId, @RequestParam String mobileNumber);


}
