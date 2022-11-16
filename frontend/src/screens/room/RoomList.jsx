import React, { useState, useRef, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import SearchIcon from "@mui/icons-material/Search";
import Pagination from "@mui/material/Pagination";
import { getRoomList } from "../../apis/room";
import RoomCardList from "@components/room/RoomCardList";
import "./RoomList.scss";

function RoomList() {
  const [roomOrder, setRoomOrder] = useState("createdAt");
  const [roomSort, setRoomSort] = useState("desc");
  const [roomPage, setRoomPage] = useState(0);
  const [size, setSize] = useState(10);
  const [page, setPage] = useState(1);
  const handleChange = (event, value) => {
    setPage(value);
    setRoomPage(value - 1);
  };
  const adRef = useRef();
  const [roomAd, setRoomAd] = useState("");
  const [toAd, setToAd] = useState(false);
  const [params, setParams] = useState({
    roomPage: roomPage,
    roomSort: roomSort,
    roomOrder: roomOrder,
  });
  const aparams = () => {
    setParams({
      roomPage: roomPage,
      roomSort: roomSort,
      roomOrder: roomOrder,
    });
  };
  const bparams = () => {
    setParams({
      roomPage: roomPage,
      roomSort: roomSort,
      roomOrder: roomOrder,
      roomAddress: roomAd,
    });
  };
  const newclick = async () => {
    setRoomOrder("createdAt");
    setRoomSort("desc");
    const res = await getRoomList(params);
    console.log(res);
  };
  const priceclick = async () => {
    setRoomOrder("deposit");
    setRoomSort("desc");
    const res = await getRoomList(params);
    console.log(res);
  };
  const onClickSearch = async () => {
    // setToAd(true);
    setRoomAd(adRef.current.value);
    bparams();
    const res = await getRoomList(params);
    console.log(res);
  };
  const handleOnKeyPress = (e) => {
    if (e.key === "Enter") {
      onClickSearch();
    }
  };
  const navigate = useNavigate();
  // useEffect(() => {
  //   fuckoff();
  // }, []);
  return (
    <div className="container flex">
      <div className="roomList flex">
        <div className="roomList_title shBold fs-56">내방사기</div>
        <div className="roomList_optionTitle shBold fs-24">동네를 검색하기</div>
        <div className="roomList_options flex">
          <div className="roomList_options_input flex">
            <input
              onKeyPress={handleOnKeyPress}
              ref={adRef}
              type="text"
              className="roomList_options_input_content shBold fs-24"
              placeholder="찾으려는 주소를 입력해주세요"
            />
            <button
              type="button"
              onClick={onClickSearch}
              className="roomList_options_input_btn"
            >
              <SearchIcon sx={{ fontSize: 40 }} />
            </button>
          </div>
          {roomOrder === "createdAt" && (
            <div className="roomList_options_btns flex">
              <button
                className="roomList_options_btns_new notoBold fs-24"
                type="button"
                onClick={newclick}
              >
                최신순
              </button>
              <button
                className="roomList_options_btns_price notoReg fs-24"
                type="button"
                onClick={priceclick}
              >
                낮은가격순
              </button>
            </div>
          )}
          {roomOrder === "deposit" && (
            <div className="roomList_options_btns flex">
              <button
                className="roomList_options_btns_new notoReg fs-24"
                type="button"
                onClick={newclick}
              >
                최신순
              </button>
              <button
                className="roomList_options_btns_price notoBold fs-24"
                type="button"
                onClick={priceclick}
              >
                낮은가격순
              </button>
            </div>
          )}
        </div>
        <div className="roomList_roomCards">
          <RoomCardList />
        </div>
        <div className="roomList_page flex justify-center">
          <Pagination
            count={size}
            page={page}
            onChange={handleChange}
            hidePrevButton
            hideNextButton
            size="large"
          />
        </div>
      </div>
    </div>
  );
}
export default RoomList;
