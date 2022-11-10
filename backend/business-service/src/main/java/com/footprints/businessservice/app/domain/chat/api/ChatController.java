package com.footprints.businessservice.app.domain.chat.api;

import com.footprints.businessservice.app.domain.chat.dto.ChatRoomInfoRes;
import com.footprints.businessservice.app.domain.chat.dto.ChatRoomReq;
import com.footprints.businessservice.app.domain.chat.dto.ChatRoomRes;
import com.footprints.businessservice.app.domain.chat.service.ChatMessageService;
import com.footprints.businessservice.app.domain.chat.service.ChatRoomService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Api(value = "채팅 API", tags = {"Chat"})
@RestController
@RequestMapping("/api/chat")
public class ChatController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    ChatRoomService chatRoomService;

    @Autowired
    ChatMessageService chatMessageService;

    /** 채팅룸 생성 **/
    @PostMapping("/register")
//    @ApiOperation(value = "채팅룸 등록", notes = "<strong>채팅 룸 정보</strong>를 생성한다." +
//            "생성하거나 기존에 있는 경우 해당 채팅룸아이디(roomId)를 리턴한다. ")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 401, message = "인증 실패"),
//            @ApiResponse(code = 404, message = "사용자 없음"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
    public ResponseEntity<String> registerChatRoom(
            @RequestBody @ApiParam(value="채팅룸 정보", required = true) ChatRoomReq chatRoomReq) {

        boolean result = false;
        String roomId = "";
        try {
            roomId = chatRoomService.findChatRoom(chatRoomReq);
            if(roomId.length()==0) {    // (길이가 0이면 존재 X, 길이가 0이 아니면 존재 O)
                roomId = chatRoomService.registerChatRoom(chatRoomReq);
            }
        } catch (Exception E) {
            E.printStackTrace();
            ResponseEntity.status(400).body(BaseResponseBody.of(500, "DB Transaction Failed"));
        }
        return new ResponseEntity<String>(roomId, HttpStatus.OK);
    }

    /** (유저의) 채팅방 전체 조회 **/
    @GetMapping("/findAll")
//    @ApiOperation(value ="채팅방 전체 조회(token)", notes = "<strong>유저가 속한 모든 채팅방</strong>을 조회한다.")
//    @ApiResponses({ @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 401, message = "인증 실패"),
//            @ApiResponse(code = 404, message = "사용자 없음"),
//            @ApiResponse(code = 500, message = "서버 오류") })
    public ResponseEntity<List<ChatRoomRes>> findAllChatRoom(@ApiIgnore Authentication authentication) {
        HanZoomUserDetails userDetails = (HanZoomUserDetails) authentication.getDetails();
        String userEmail = userDetails.getUsername();

        List<ChatRoomRes> chatRoomResList = chatRoomService.findAllChatRoom(userEmail);
        return new ResponseEntity<List<ChatRoomRes>>(chatRoomResList, HttpStatus.OK);
    }

    /** 선택한 채팅방 상세 정보 조회 **/
    @GetMapping("/find/{roomId}")
//    @ApiOperation(value ="선택 채팅방 상세 조회", notes = "<strong>선택한 채팅방의 정보</strong>를 조회한다.")
//    @ApiResponses({ @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 401, message = "인증 실패"),
//            @ApiResponse(code = 404, message = "사용자 없음"),
//            @ApiResponse(code = 500, message = "서버 오류") })
    public ResponseEntity<ChatRoomInfoRes> findChatroomInfo(@PathVariable("roomId") String roomId) {
        ChatRoomInfoRes chatRoomInfoRes = null;
        try {
            chatRoomInfoRes = chatRoomService.findChatRoomInfoByRoomId(roomId);
        } catch (Exception E) {
            E.printStackTrace();
            ResponseEntity.status(400).body(BaseResponseBody.of(500, "DB Transaction Failed"));
        }
        return new ResponseEntity<ChatRoomInfoRes>(chatRoomInfoRes, HttpStatus.OK);
    }

    /** 채팅방 삭제 **/
    @DeleteMapping("/remove")
    @ApiOperation(value = "채팅방에서 유저 정보 삭제(token)", notes = "<strong>채팅방의 해당 유저의 참가 정보</strong>를 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<String> deleteUserIngredient(@RequestParam("id") String id, @ApiIgnore Authentication authentication) {
        HanZoomUserDetails userDetails = (HanZoomUserDetails) authentication.getDetails();
        String userEmail = userDetails.getUsername();

        try {
            chatRoomService.deleteUserInfo(id, userEmail);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Bad Request");
        }
        return ResponseEntity.status(200).body("Success");
    }
}
