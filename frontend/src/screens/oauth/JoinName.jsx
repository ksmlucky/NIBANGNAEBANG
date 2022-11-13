import React, { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import Link from "@mui/material/Link";
import Typography from "@mui/material/Typography";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import HandshakeIcon from '@mui/icons-material/Handshake';
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';
import queryString from 'query-string';
import { setName, setBirth } from '@store/user';
import "./JoinName.scss";

const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
const REDIRECT_URI = "http://j7c105.p.ssafy.io/oauth/kakao";
const KAKAO_AUTH_URI = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;

function JoinName() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  // const [userName, setUserName] = useState("");
  const nameInput = useRef();
  const birthInput = useRef();
  const pvd = useSelector(state => state.user);
  // console.log(pvd);
  const toNickname = () => {
    // setUserName(nameInput.current.value);
    dispatch(setName(nameInput.current.value));
    dispatch(setBirth(birthInput.current.value));
    setTimeout(() => {
      console.log(pvd);
      navigate("/login/joinnickname");
    }, 500);
  };
  return (
    <div className="container flex">
      <div className="joinName flex">
        <div className="joinName_title shBold fs-56">1주일을 살 때도, 1년을 살 때도</div>
        <div className="joinName_box flex">
          <HandshakeIcon sx={{ fontSize: 80}} className="joinName_box_join"/>
          <div className="joinName_box_txt1 shBold fs-32">이름과 생년월일을</div>
          <div className="joinName_box_txt2 shBold fs-32">확인할게요</div>
          <input type="text" className="joinName_box_name shBold fs-24" placeholder="이름 (ex. 홍길동)" ref={nameInput} />
          <input type="text" className="joinName_box_day shBold fs-24" placeholder="생년월일 (ex. 990101)" ref={birthInput} />
          <button className="joinName_box_btn" type="button" onClick={toNickname}>
            <ArrowForwardIcon sx={{ color: "#909090", fontSize: 52}} className="joinName_box_btn_next" />
          </button>
        </div>
      </div>
    </div>
  );
}

export default JoinName;