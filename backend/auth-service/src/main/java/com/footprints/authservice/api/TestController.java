package com.footprints.authservice.api;


import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "보이냐?";
    }

    @GetMapping("/AT/header")
    public String header(@RequestHeader("X-Authorization-Id") String memberId, HttpServletRequest request){
        log.info(request.toString());
        log.info("===================================");
        log.info(String.valueOf(request.getHeaderNames()));
        log.info("===================================");
        log.info(memberId);
        return "보이냐?";
    }
}
