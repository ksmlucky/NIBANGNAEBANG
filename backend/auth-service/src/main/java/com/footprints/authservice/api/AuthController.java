package com.footprints.authservice.api;

import com.footprints.authservice.global.common.DataResponse;
import com.footprints.authservice.global.common.MessageResponse;
import com.footprints.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "auth", description = "인증 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "리프레쉬 토큰 재발급", description = "리프레쉬 토큰을 재발급합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "리프레쉬 토큰 재발급 성공"),
            @ApiResponse(responseCode = "401", description = "유효하지않은 리프레쉬 토큰입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/reissuance/{refreshToken}")
    public ResponseEntity reissueRefreshToken(@RequestHeader("X-Authorization-Id") String memberId, @PathVariable String refreshToken){

        String newRefreshToken = authService.reissueRefreshToken(memberId, refreshToken);

        if(newRefreshToken == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("유효하지 않은 리프레쉬 토큰입니다."));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new DataResponse("리프레쉬 토큰이 발급되었습니다.", newRefreshToken));


    }
}
