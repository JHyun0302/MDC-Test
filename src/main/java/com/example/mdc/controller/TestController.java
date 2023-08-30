package com.example.mdc.controller;

import com.example.mdc.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;

    @GetMapping("/mdc")
    public String mdcTest() {
        log.info("Start Controller [TraceId] -> {}", MDC.get("traceId"));

        return testService.mdcTest();
    }
}
