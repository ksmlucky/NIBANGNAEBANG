package com.footprints.businessservice.app.domain.board.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class MainController {

    private final Environment env;

    @GetMapping("/at/health_check")
    public String check() {
        log.info("Server port={}", env.getProperty("local.server.port"));
        return String.format("This Service port is %s", env.getProperty("local.server.port"));
    }
}
