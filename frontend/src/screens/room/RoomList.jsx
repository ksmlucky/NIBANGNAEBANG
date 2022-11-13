import React, { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import SearchIcon from '@mui/icons-material/Search';
import Pagination from "@mui/material/Pagination";
import RoomCardList from "@components/room/RoomCardList";
import "./RoomList.scss";

function RoomList() {
  const [flip, setFlip] = useState("최신순");
  const newclick = () => {
    setFlip("최신순");
  };
  const priceclick = () => {
    setFlip("낮은가격순");
  };
  const searchRef = useRef();
  const searchTown = () => {
    console.log(searchRef.current.value);
  };
  const handleOnKeyPress = e => {
    if (e.key === "Enter") {
      searchTown();
    }
  };
  const navigate = useNavigate();
  const [size, setSize] = useState(10);
  const [page, setPage] = useState(1);
  const handleChange = (event, value) => {
    setPage(value);
  };
  return (
    <div className="container flex">
      <div className="roomList flex">
        <div className="roomList_title shBold fs-56">내방사기</div>
        <div className="roomList_optionTitle shBold fs-24">동네를 검색하기</div>
        <div className="roomList_options flex">
          <div className="roomList_options_input flex">
            <input 
              onKeyPress={handleOnKeyPress}
              ref={searchRef}
              type="text"
              className="roomList_options_input_content shBold fs-24"
              placeholder="찾으려는 주소를 입력해주세요"
            />
            <button type="button" onClick={searchTown} className="roomList_options_input_btn">
              <SearchIcon sx={{ fontSize: 40}} />
            </button>
          </div>
          {flip === "최신순" && (
            <div className="roomList_options_btns flex">
              <button className="roomList_options_btns_new notoBold fs-24" type="button" onClick={newclick}>최신순</button>
              <button className="roomList_options_btns_price notoReg fs-24" type="button" onClick={priceclick}>낮은가격순</button>
            </div>
            )}
          {flip === "낮은가격순" && (
            <div className="roomList_options_btns flex">
              <button className="roomList_options_btns_new notoReg fs-24" type="button" onClick={newclick}>최신순</button>
              <button className="roomList_options_btns_price notoBold fs-24" type="button" onClick={priceclick}>낮은가격순</button>
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