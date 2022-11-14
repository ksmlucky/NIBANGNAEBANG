import React, { useRef, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import Link from "@mui/material/Link";
import Typography from "@mui/material/Typography";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import CheckIcon from "@mui/icons-material/Check";
import Profile from "@images/extra/profile.png";
import { checkNickname, checkJoin } from "../../apis/user";
import { setNickname } from "@store/user";
import "./JoinNickname.scss";

const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
const REDIRECT_URI = "http://j7c105.p.ssafy.io/oauth/kakao";
const KAKAO_AUTH_URI = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;

function JoinNickname() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const nicknameInput = useRef();
  const [passNickname, setPassNickname] = useState(false);
  const pvd = useSelector((state) => state.user);
  console.log(pvd);
  // const currentNickname = nicknameInput.current.value;
  const checkNick = async () => {
    if (nicknameInput.current.value !== "") {
      const res = await checkNickname({
        nickname: nicknameInput.current.value,
      });
      // setPassNickname(true);
      console.log(res);
    } else {
      window.alert("닉네임을 입력해주세요");
    }
  };
  const joinFinish = async () => {
    dispatch(setNickname(nicknameInput.current.value));
    const userDi = pvd.di;
    const userProvider = pvd.provider;
    const userProviderId = pvd.providerId;
    const userNickname = nicknameInput.current.value;
    const res = await checkJoin({
      di: userDi,
      provider: userProvider,
      providerId: userProviderId,
      nickname: userNickname,
    });
    if (res.message === "성공") {
      console.log(res.data);
      navigate("/");
    }
  };
  return (
    <div className="container flex">
      <div className="joinNickname flex">
        <div className="joinNickname_title shBold fs-56">
          새 방 같은 헌 방을 구할 때
        </div>
        <div className="joinNickname_box flex">
          {/* <HandshakeIcon sx={{ fontSize: 80}} className="joinNickname_box_join"/> */}
          <div className="joinNickname_box_img">
            <img src={Profile} alt="" />
          </div>
          <div className="joinNickname_box_txt shBold fs-32">
            닉네임을 설정해주세요
          </div>
          <div className="joinNickname_box_input flex">
            <input
              type="text"
              className="joinNickname_box_input_nickname shBold fs-24"
              placeholder="닉네임"
              ref={nicknameInput}
            />
            {passNickname === true && (
              <input
                type="text"
                className="joinNickname_box_input_nickname shBold fs-24"
                placeholder="닉네임"
                ref={nicknameInput}
                disabled
              />
            )}
            <button
              className="joinNickname_box_input_btn"
              type="button"
              onClick={checkNick}
            >
              <div className="joinNickname_box_input_btn_txt flex shBold fs-16">
                중복확인
              </div>
            </button>
          </div>
          <button
            className="joinNickname_box_btn"
            type="button"
            onClick={joinFinish}
          >
            <CheckIcon
              sx={{ color: "#909090", fontSize: 52 }}
              className="joinNickname=box_btn_next"
            />
          </button>
        </div>
      </div>
    </div>
  );
}

export default JoinNickname;
