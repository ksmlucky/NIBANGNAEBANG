import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import Link from "@mui/material/Link";
import Typography from "@mui/material/Typography";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import CloseRoundedIcon from "@mui/icons-material/CloseRounded";
import CheckIcon from "@mui/icons-material/Check";
import Profile from "@images/extra/profile.png";
import "./ChangeNickname.scss";

const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
const REDIRECT_URI = "http://j7c105.p.ssafy.io/oauth/kakao";

function ChangeNickname() {
  const navigate = useNavigate();
  const cancelHome = () => {
    navigate("/");
  };
  const user = useSelector((state) => state.user);
  const [nick, setNick] = useState("");
  useEffect(() => {
    setNick(user.nickname);
  }, [nick]);
  return (
    <div className="container flex">
      <div className="changeNickname flex">
        <div className="changeNickname_title shBold fs-56">
          개인정보를 수정하고 싶을 때
        </div>
        <div className="changeNickname_box flex">
          {/* <HandshakeIcon sx={{ fontSize: 80}} className="changeNickname_box_join"/> */}
          <div className="changeNickname_box_img">
            <img src={Profile} alt="" />
          </div>
          <div className="changeNickname_box_txt shBold fs-32">
            새 닉네임을 설정해주세요
          </div>
          <div className="changeNickname_box_input flex">
            <input
              type="text"
              className="changeNickname_box_input_nickname shBold fs-24"
              defaultValue={nick}
            />
            <button className="changeNickname_box_input_btn" type="button">
              <div className="changeNickname_box_input_btn_txt flex shBold fs-16">
                중복확인
              </div>
            </button>
          </div>
          <div className="changeNickname_box_btns flex">
            <button className="changeNickname_box_btns_confirm" type="button">
              <CheckIcon
                sx={{ color: "#909090", fontSize: 52 }}
                className="changeNickname=box_btns_confirm_btn"
              />
            </button>
            <button className="changeNickname_box_btns_cancel" type="button">
              <CloseRoundedIcon
                sx={{ color: "#909090", fontSize: 52 }}
                className="changeNickname=box_btns_cancel_btn"
                onClick={cancelHome}
              />
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ChangeNickname;
