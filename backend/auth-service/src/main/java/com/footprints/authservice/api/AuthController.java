package com.footprints.authservice.api;

import com.footprints.authservice.dto.request.RefreshTokenRequest;
import com.footprints.authservice.global.common.DataResponse;
import com.footprints.authservice.global.common.MessageResponse;
import com.footprints.authservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "auth", description = "인증 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "액세스 토큰 재발급", description = "액세스 토큰을 재발급합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "액세스 토큰 재발급 성공"),
            @ApiResponse(responseCode = "401", description = "유효하지않은 리프레쉬 토큰입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @PostMapping("/reissuance")
    public ResponseEntity reissueAccessToken(@RequestHeader("X-Authorization-Id") String memberId,
                                              @RequestBody RefreshTokenRequest refreshTokenRequest) {

        String newAccessToken = authService.reissueAccessToken(memberId, refreshTokenRequest.getRefreshToken());

        if (newAccessToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageResponse("유효하지 않은 리프레쉬 토큰입니다."));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new DataResponse("액세스 토큰이 발급되었습니다.", newAccessToken));
    }
}
