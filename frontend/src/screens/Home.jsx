import React, { useState, useRef, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";
import RoomSearch from "@components/room/RoomSearch";
import CloseRoundedIcon from '@mui/icons-material/CloseRounded';
import moneyIcon from "@images/extra/money.png";
import searchIcon from "@images/extra/search.png";
import homeIcon from "@images/extra/house.png";
import roomIcon from "@images/room.png";
import "./Home.scss";

function Home() {
  const [option, setOption] = useState(false);
  const { sessionStorage } = window;
  console.log(sessionStorage);
  const handleOption = (event) => {
    setOption(!option);
  };
  const navigate = useNavigate();
  const toRegister = () => {
    navigate("/room/register");
  };
  const user = useSelector((state) => state.user);
  console.log(user);
  const [address, setAddress] = useState("");
  const [popup, setPopup] = useState(false);
  const addRef = useRef();
  const AT = sessionStorage.getItem("accessToken");
  const checkAt = () => {
    if (AT === null) {
      // window.alert("로그인을 진행해주세요");
      navigate("/login");
    }
  };
  useEffect(() => {
    checkAt();
  }, [AT]);
  return (
    <div className="container">
      <div className="home_back  flex">
        {/* <img src={mainImg} alt="main"> */}
        <div className="home">
          <div className="home_title fs-56 shBold">
            내 방,
            <div className="home_title_down flex">
              잘 사고 잘 팔았다
              <img src={moneyIcon} alt="" />
            </div>
          </div>
          <div className="home_contents flex">
            {!option && (
              <div className="home_contents_box">
                <div className="home_contents_box_select flex">
                  <div className="home_contents_box_select_btn_sel flex justify-center fs-40 shBold">
                    팔아줘
                  </div>
                  <button
                    type="button"
                    onClick={handleOption}
                    className="home_contents_box_select_btn flex justify-center fs-40 shBold"
                  >
                    구해줘
                  </button>
                </div>
                <div className="home_contents_box_bot flex">
                  <div className="home_contents_box_bot_title flex fs-48 shBold">
                    방이 어디에 있나요?
                    <img src={homeIcon} alt="" />
                  </div>
                  <input
                    type="text"
                    className="home_contents_box_bot_input shBold fs-32"
                    placeholder="예시) 상무공원로10"
                    ref={addRef}
                  />
                  <button
                    type="button"
                    className="home_contents_box_bot_btn shBold fs-32"
                    onClick={() => {
                      setPopup(!popup);
                    }}
                  >
                    등록해줘
                  </button>
                  {popup && (
                    <>
                      <button className="closebtn" type="button" title="닫기" onClick={() => setPopup(false)}><CloseRoundedIcon fontSize="large"/></button> 
                      <RoomSearch address={address} setAddress={setAddress} location={addRef.current.value}/>
                    </>
                  )}
                </div>
              </div>
            )}
            {option && (
              <div className="home_contents_box">
                <div className="home_contents_box_select flex">
                  <button
                    type="button"
                    onClick={handleOption}
                    className="home_contents_box_select_btn flex justify-center fs-40 shBold"
                  >
                    팔아줘
                  </button>
                  <div className="home_contents_box_select_btn_sel flex justify-center fs-40 shBold">
                    구해줘
                  </div>
                </div>
                <div className="home_contents_box_bot flex">
                  <div className="home_contents_box_bot_title flex fs-48 shBold">
                    어떤 방을 찾으세요?
                    <img src={searchIcon} alt="" />
                  </div>
                  <input
                    type="text"
                    className="home_contents_box_bot_input shBold fs-32"
                    placeholder="예시) 광주 서구 치평동"
                  />
                  <button
                    type="button"
                    className="home_contents_box_bot_btn shBold fs-32"
                  >
                    찾기
                  </button>
                </div>
              </div>
            )}
            <div className="home_contents_ads flex">
              <div className="home_contents_ads_top">
                <div className="home_contents_ads_top_title flex justify-center fs-28 shBold">
                  니방보기
                </div>
                <div className="home_contents_ads_top_cont flex">
                  <div className="home_contents_ads_top_cont_txt">
                    <div className="home_contents_ads_top_cont_txt_up fs-28 shBold">
                      간단한 문구
                      <br />
                      들어가게 하기
                    </div>
                    <div className="home_contents_ads_top_cont_txt_down fs-26 shBold">
                      설명을 또 해주기
                    </div>
                  </div>
                  <div className="home_contents_ads_top_cont_img">
                    <img src={roomIcon} alt="집 이미지" />
                  </div>
                </div>
              </div>
              <div className="home_contents_ads_down flex fs-28 shBold">
                <div className="home_contents_ads_down_left">
                  <div className="home_contents_ads_down_left_title flex justify-center">
                    니방보기
                  </div>
                  간단한 문구
                  <br />
                  들어가게 하기
                </div>
                <div className="home_contents_ads_down_right">
                  <div className="home_contents_ads_down_right_title flex justify-center">
                    니방보기
                  </div>
                  간단한 문구
                  <br />
                  들어가게 하기
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Home;
