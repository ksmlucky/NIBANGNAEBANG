import React, { useState } from 'react';
import {useNavigate} from "react-router-dom";
import {useRecoilState} from "recoil";
import CheckIcon from '@mui/icons-material/Check';
import thinkIcon from "@images/extra/think.png";
import contractIcon from "@images/extra/contract.png";
import keyboardIcon from "@images/extra/keyboard.png";
import "./RoomRegister.scss";

function Questions(props) {
  const {id, getId} = props;
  const ques = [
    {subtitle: '간편하게 양도 신청하세요', title: '내방 상세정보 입력하기', img: keyboardIcon},
    {subtitle: '거의 다 왔어요!', title: '내방 상세정보 입력하기', img: contractIcon},
    {subtitle: '거래가 쉬워질 거에요', title: '내방 추가정보 입력하기', img: thinkIcon}
  ];
  const onClick = () => {
    getId(id + 1);
  };
  return (
    <div className="roomRegister flex">
      <div className="roomRegister_subtitle shBold fs-40">{ques[id].subtitle}</div>
      <div className="roomRegister_title flex shBold fs-56">
        {ques[id].title}
        <img src={ques[id].img} alt="" />
      </div>
      <div className="roomRegister_box flex justify-center">
        <button type="button" className="roomRegister_box_btn" onClick={onClick}>
          <CheckIcon sx={{ color: "#909090", fontSize: 52}} />
        </button>
      </div>
    </div>
  );
}
export default Questions;