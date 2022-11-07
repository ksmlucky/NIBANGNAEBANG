import React from "react";
import Link from "@mui/material/Link";
import Typography from "@mui/material/Typography";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import HandshakeIcon from '@mui/icons-material/Handshake';
import ArrowForwardIcon from '@mui/icons-material/ArrowForward';
import "./JoinName.scss";

const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
const REDIRECT_URI = "http://j7c105.p.ssafy.io/oauth/kakao";
const KAKAO_AUTH_URI = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;

function Copyright(props) {
  return (
    <Typography
      variant="body2"
      color="text.secondary"
      align="center"
      {...props}
    >
      {"Copyright © "}
      <Link color="inherit" href="">
        니방내방
      </Link>{" "}
      {new Date().getFullYear()}
      {"."}
    </Typography>
  );
}

const theme = createTheme();

function JoinName() {
  return (
    <div className="container flex">
      <div className="joinName flex">
        <div className="joinName_title shBold fs-56">1주일을 살 때도, 1년을 살 때도</div>
        <div className="joinName_box flex">
          <HandshakeIcon sx={{ fontSize: 80}} className="joinName_box_join"/>
          <div className="joinName_box_txt1 shBold fs-32">이름과 생년월일을</div>
          <div className="joinName_box_txt2 shBold fs-32">확인할게요</div>
          <input type="text" className="joinName_box_name shBold fs-24" placeholder="이름 (ex. 홍길동)" />
          <input type="text" className="joinName_box_day shBold fs-24" placeholder="생년월일 (ex. 990101)" />
          <button className="joinName_box_btn" type="button">
            <ArrowForwardIcon sx={{ color: "#909090", fontSize: 52}} className="joinName=box_btn_next" />
          </button>
        </div>
      </div>
    </div>
  );
}

export default JoinName;
