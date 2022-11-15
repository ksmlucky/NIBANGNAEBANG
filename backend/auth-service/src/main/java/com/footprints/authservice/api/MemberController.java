package com.footprints.authservice.api;

import com.footprints.authservice.domain.Member;
import com.footprints.authservice.dto.request.MemberInfoForDiRequest;
import com.footprints.authservice.dto.request.MemberInfoRequest;
import com.footprints.authservice.dto.request.NicknameRequest;
import com.footprints.authservice.dto.response.SelectNicknameResponse;
import com.footprints.authservice.dto.response.diResponse;
import com.footprints.authservice.global.common.CountDataResponse;
import com.footprints.authservice.global.common.CountResponse;
import com.footprints.authservice.global.common.DataResponse;
import com.footprints.authservice.global.common.MessageResponse;
import com.footprints.authservice.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

@Tag(name = "member", description = "회원 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

//    @Operation(summary = "닉네임 추가하기", description = "닉네임을 추가합니다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "닉네임 설정 완료"),
//            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
//            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원입니다."),
//            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
//    })
//    @PutMapping("/signup/{memberId}/nickname")
//    public ResponseEntity insertNickname(@PathVariable Long memberId, @RequestBody NicknameRequest nicknameRequest){
//        memberService.insertNickname(memberId, nicknameRequest);
//        return new ResponseEntity<>(new MessageResponse("닉네임 설정 완료"), HttpStatus.OK);
//    }

    @Operation(summary = "닉네임 중복 체크하기", description = "사용하려는 닉네임 중복 체크를 진행합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "총 회원 수 조회 성공"),
            @ApiResponse(responseCode = "400", description = "닉네임이 중복되지 않습니다."),
            @ApiResponse(responseCode = "409", description = "닉네임 중복입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @PostMapping("/nickname/duplicate")
    public ResponseEntity checkNickname(@RequestBody NicknameRequest nicknameRequest){

        if(memberService.checkNickname(nicknameRequest)){
            // 닉네임 중복일때
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new MessageResponse("닉네임 중복입니다."));
        }else{
            // 닉네임 중복 아닐때
            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("닉네임이 중복되지 않습니다."));
        }
    }

    @Operation(summary = "이름, 생년월일로 DI 발급", description = "해당 회원의 이름, 생년월일로 DI를 발급합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "DI 발급 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @PostMapping("/di")
    public ResponseEntity getDi(@RequestBody MemberInfoForDiRequest memberInfoForDiRequest) throws NoSuchAlgorithmException {
        String di = memberService.getDi(memberInfoForDiRequest);

        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>("본인 인증 완료", new diResponse(di)));
    }

    @Operation(summary = "회원 정보 추가하기", description = "회원의 닉네임, provider, providerId, di를 기반으로 소셜로그인 회원가입/통합처리/로그인을 진행한 후 jwt를 발급합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @PostMapping("/signup")
    public ResponseEntity signUpAndSignInMember(@RequestBody MemberInfoRequest memberInfoRequest){

        //회원가입
        Member findMember = memberService.signUpMember(memberInfoRequest);

        //로그인
        Map<String, String> tokens = memberService.signInMember(findMember.getId());

        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>("성공", tokens));
    }

    @Operation(summary = "닉네임 수정하기", description = "해당 회원의 닉네임을 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "닉네임 변경 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @PutMapping("/AT/nickname")
    public ResponseEntity updateNickname(@RequestHeader("X-Authorization-Id") String memberId, @RequestBody NicknameRequest nicknameRequest){
        memberService.updateNickname(Long.parseLong(memberId), nicknameRequest);

        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse<>("닉네임 변경 완료"));
    }

//    @Operation(summary = "총 회원 수 조회하기", description = "현재 총 회원 수를 조회합니다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "총 회원 수 조회 성공"),
//            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
//            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원입니다."),
//            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
//    })
//    @GetMapping("/members/count")
//    public ResponseEntity selectMemberCount(){
//        int memberCount = memberService.selectMemberCount();
//        return ResponseEntity.status(HttpStatus.OK).body(new CountResponse("총 회원 수 조회 성공", memberCount));
//    }
//
//    @Operation(summary = "모든 회원 id 값 조회하기", description = "현재 가입되어있는 회원의 id값을 모두 조회합니다.")
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "총 회원 수 조회 성공"),
//            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
//            @ApiResponse(responseCode = "404", description = "그림이 존재하지 않습니다."),
//            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
//    })
//    @GetMapping("/members/id")
//    public ResponseEntity selectAllMemberId(){
//        List<Long> membersId = memberService.selectAllMemberId();
//        return ResponseEntity.status(HttpStatus.OK).body(new CountDataResponse<>("모든 회원 id 값 조회 성공", membersId, membersId.size()));
//    }

    @Operation(summary = "회원 탈퇴하기", description = "해당 회원을 탈퇴처리합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "회원탈퇴가 완료되었습니다."),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @DeleteMapping("/AT/withdrawal")
    public ResponseEntity memberWithdrawal(@RequestHeader("X-Authorization-Id") String memberId){
        memberService.memberWithdrawal(Long.parseLong(memberId));

        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse<>("회원탈퇴가 완료되었습니다."));
    }

    @Operation(summary = "닉네임 조회하기", description = "해당 회원의 닉네임을 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "닉네임 조회 완료"),
            @ApiResponse(responseCode = "400", description = "잘못된 접근입니다."),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 회원입니다."),
            @ApiResponse(responseCode = "500", description = "서버 에러입니다.")
    })
    @GetMapping("/{memberId}/nickname")
    public ResponseEntity selectNickname(@PathVariable Long memberId){
        String selectNickname = memberService.selectNickname(memberId);
        SelectNicknameResponse selectNicknameResponse = new SelectNicknameResponse(selectNickname);

        return ResponseEntity.status(HttpStatus.OK).body(selectNicknameResponse);
    }


}
