package com.example.mdc.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {
    public String mdcTest() {
        log.info("Start Service [TraceId] -> {}", MDC.get("traceId"));

        return "MDC Logging Test";
    }
}
