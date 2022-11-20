import React, { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import Link from "@mui/material/Link";
import Typography from "@mui/material/Typography";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import HandshakeIcon from "@mui/icons-material/Handshake";
import ArrowForwardIcon from "@mui/icons-material/ArrowForward";
import queryString from "query-string";
import { setName, setBirth, setDi } from "@store/user";
import { sendDi } from "../../apis/user";
import "./JoinName.scss";

const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
const REDIRECT_URI = "http://k7c109.p.ssafy.io/oauth/kakao";
const KAKAO_AUTH_URI = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;

function JoinName() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  // const [userName, setUserName] = useState("");
  const nameInput = useRef();
  const birthInput = useRef();
  const pvd = useSelector((state) => state.user);
  // console.log(pvd);
  const [birthError, setBirthError] = useState(true);
  const checkBirth = (e) => {
    const regBirth = /^(?:\d{6})$/;
    if (regBirth.test(e.target.value) === false) {
      setBirthError(true);
    } else {
      setBirthError(false);
    }
  };
  const toNickname = async () => {
    // console.log(birthError);
    // console.log(pvd);
    const userName = nameInput.current.value;
    const birthday = birthInput.current.value;
    if (birthError === false && userName !== "") {
      dispatch(setName(userName));
      dispatch(setBirth(birthday));
      const res = await sendDi({
        name: userName,
        birth: birthday,
        provider: pvd.provider,
        providerId: pvd.providerId,
      });
      if (res.message === "본인 인증 완료") {
        dispatch(setDi(res.data.di));
        navigate("/login/joinnickname");
      }
      // .then(navigate("/login/joinnickname"));
      // navigate("/login/joinnickname");
      // }, 500);
    } else {
      window.alert("입력정보를 확인해주세요");
    }
  };
  return (
    <div className="container flex">
      <div className="joinName flex">
        <div className="joinName_title shBold fs-56">
          1주일을 살 때도, 1년을 살 때도
        </div>
        <div className="joinName_box flex">
          <HandshakeIcon sx={{ fontSize: 80 }} className="joinName_box_join" />
          <div className="joinName_box_txt1 shBold fs-32">
            이름과 생년월일을
          </div>
          <div className="joinName_box_txt2 shBold fs-32">확인할게요</div>
          <input
            type="text"
            className="joinName_box_name shBold fs-24"
            placeholder="이름 (ex. 홍길동)"
            ref={nameInput}
          />
          <input
            type="text"
            className="joinName_box_day shBold fs-24"
            placeholder="생년월일 (ex. 990101)"
            ref={birthInput}
            onChange={checkBirth}
          />
          <button
            className="joinName_box_btn"
            type="button"
            onClick={toNickname}
          >
            <ArrowForwardIcon
              sx={{ color: "#909090", fontSize: 52 }}
              className="joinName_box_btn_next"
            />
          </button>
        </div>
      </div>
    </div>
  );
}

export default JoinName;
