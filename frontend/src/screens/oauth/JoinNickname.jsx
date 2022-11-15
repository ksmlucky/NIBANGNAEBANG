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
  const { sessionStorage } = window;
  const nicknameInput = useRef();
  const [passNickname, setPassNickname] = useState(false);
  const [userNickname, setUserNickname] = useState("");
  const pvd = useSelector((state) => state.user);
  const [alert, setAlert] = useState(false);
  const [message, setMessage] = useState("");
  console.log(pvd);
  // console.log(sessionStorage);
  // console.log(alert);
  // const currentNickname = nicknameInput.current.value;
  const checkNick = async () => {
    if (nicknameInput.current.value !== "") {
      try {
        const res = await checkNickname({
          nickname: nicknameInput.current.value,
        });
        if (res.message === "닉네임이 중복되지 않습니다.") {
          setPassNickname(true);
          setAlert(false);
          setUserNickname(nicknameInput.current.value);
        }
        // console.log(res);
      } catch (err) {
        if (err.message === "Request failed with status code 409") {
          setMessage("닉네임이 중복됩니다.");
          setAlert(true);
          setPassNickname(false);
        } else {
          console.log(err);
        }
      }
    } else {
      setMessage("닉네임을 입력해주세요.");
      setPassNickname(false);
      setAlert(true);
    }
  };
  const joinFinish = async () => {
    if (passNickname === true) {
      dispatch(setNickname(nicknameInput.current.value));
      const userDi = pvd.di;
      const userProvider = pvd.provider;
      const userProviderId = pvd.providerId;
      const nickname = userNickname;
      const res = await checkJoin({
        di: userDi,
        provider: userProvider,
        providerId: userProviderId,
        nickname: nickname,
      });
      if (res.message === "성공") {
        // console.log(res.data);
        // console.log(res.data.refreshToken);
        sessionStorage.setItem("refreshToken", res.data.refreshToken);
        sessionStorage.setItem("accessToken", res.data.accessToken);
        sessionStorage.setItem("userNickname", nickname);
        navigate("/");
      }
      // console.log(res);
    } else {
      setMessage("중복확인을 진행해주세요.");
      setAlert(true);
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
          <div className="joinNickname_box_alert flex">
            {alert === true && (
              <div className="joinNickname_box_alert_txt shBold fs-22">
                {message}
              </div>
            )}
          </div>
          <div className="joinNickname_box_input flex">
            <input
              type="text"
              className="joinNickname_box_input_nickname shBold fs-24"
              placeholder="닉네임"
              ref={nicknameInput}
            />
            <div className="joinNickname_box_input_accept flex">
              <div className="joinNickname_box_input_accept_yes flex">
                {passNickname === true && (
                  <div className="joinNickname_box_input_accept_yes_txt shBold fs-22">
                    중복확인 완료
                  </div>
                )}
              </div>
              <button
                className="joinNickname_box_input_accept_btn"
                type="button"
                onClick={checkNick}
              >
                <div className="joinNickname_box_input_accept_btn_txt flex shBold fs-22">
                  중복확인
                </div>
              </button>
            </div>
          </div>
          {/* {passNickname === true && (
            <div className="joinNickname_box_input flex">
              <input
                type="text"
                className="joinNickname_box_input_nickname shBold fs-24"
                placeholder="닉네임"
                ref={nicknameInput}
                disabled
              />
              <button
                className="joinNickname_box_input_btn"
                type="button"
                // onClick={checkNick}
                disabled
              >
                <div className="joinNickname_box_input_btn_txt flex shBold fs-16">
                  중복확인
                </div>
              </button>
            </div>
          )} */}
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
