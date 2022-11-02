import { Avatar } from "@mui/material";
import React from "react";
import "./MyPage.scss";

function MyPage(){
    return(
        <div className ="container flex justify-center">
            <div className = "editInfo">
                <div className="editInfo_title flex justify-center notoBold fs-32">내 정보 수정</div>
                <div className = "editInfo_nickName">
                    <div className = "editInfo_nickName_title Bold fs-20">닉네임</div>
                    <div className = "editInfo_nickName_input">
                        <input 
                            type="text" placeholder="닉네임 입력"
                            className = "editInfo_nickName_inputBox"
                        />
                        <button
                            type="button"
                            className = "deifInfo_nickName_checkBox">중복 확인
                        </button>
                    </div>
                </div>
                <div className = "editInfo_address">
                    <div className = "editInfo_address_title">주소</div>
                    <div className = "editInfo_address_input">
                        <input
                            type="text" placeholder="주소 입력"
                            className = "editInfo_address_input_box"/>
                    </div>
                </div>
                <div className = "editInfo_profile">
                    <div className = "editInfo_profile_title">프로필 이미지</div>
                    <Avatar className = "editInfo_profile_input flex">

                    </Avatar>
                </div>
                <div className = "editInfo_update flex justify-center">
                    <button
                        type="button"
                        className = "editInfo_update_button">수정하기
                    </button>
                </div>
                <div className = "editInfo_secession flex justify-center">
                    <button
                        type="button"
                        className = "editInfo_secession_button">회원탈퇴
                    </button>
                </div>
                {/* <div className = "editInfo_delete">
                    <button
                            type="button"
                            className = "editInfo_delete_button">삭제하기
                        </button>
                </div> */}
            </div>
            
        </div>
    );
}

export default MyPage;