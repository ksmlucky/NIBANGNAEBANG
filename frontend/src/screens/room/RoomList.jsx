import React, { useState, useRef, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import SearchIcon from "@mui/icons-material/Search";
import Pagination from "@mui/material/Pagination";
import { getRoomList, getRoomListAd } from "../../apis/room";
import RoomCardList from "@components/room/RoomCardList";
import { v4 } from "uuid";
import RoomCard from "@components/room/RoomCard";
import "./RoomList.scss";

function RoomList() {
  // 보증금 순으로하려면 transfer.deposit으로 할 것
  // 방의 다른 정보 활용하려면 마찬가지로 transfer.{}로 할 수 있음(roomSize, startDate -> 바로 계약할 수 있는 케이스 등)
  const adRef = useRef();
  const [size, setSize] = useState(5);
  const [page, setPage] = useState(1);
  const [roomSort, setRoomSort] = useState("createdAt");
  const [roomOrder, setRoomOrder] = useState("desc");
  const [roomAddress, setRoomAddress] = useState("");
  const [roomList, setRoomList] = useState([]);
  const [params, setParams] = useState({
    roomPage: page - 1,
    roomSort: roomSort,
    roomOrder: roomOrder,
    roomAddress: roomAddress,
  });
  const getList = async () => {
    const res = await getRoomListAd(params);
    // console.log(res.data);
    setSize(Math.ceil(res.data.count / 4));
    setRoomList(res.data.articles);
    // console.log(res.data);
    // console.log(res.data.article);
    // console.log(roomList.length);
    // console.log(res);
  };
  const handleChange = async (event, value) => {
    setPage(value);
    setParams((preState) => ({
      ...preState,
      roomPage: value - 1,
    }));
  };
  const toNew = async () => {
    setRoomSort("createdAt");
    setRoomOrder("desc");
    setParams((preState) => ({
      ...preState,
      roomSort: "createdAt",
      roomOrder: "desc",
    }));
  };
  const toLow = async () => {
    setRoomSort("hits");
    setRoomOrder("desc");
    setParams((preState) => ({
      ...preState,
      roomSort: "hits",
      roomOrder: "desc",
    }));
  };
  useEffect(() => {
    getList();
  }, [page]);
  useEffect(() => {
    getList();
  }, [roomSort]);
  useEffect(() => {
    getList();
  }, [roomAddress]);
  const onClickSearch = async () => {
    setRoomAddress(adRef.current.value);
    setParams((preState) => ({
      ...preState,
      roomAddress: adRef.current.value,
    }));
  };
  const handleOnKeyPress = (e) => {
    if (e.key === "Enter") {
      onClickSearch();
    }
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
          {roomSort === "createdAt" && (
            <div className="roomList_options_btns flex">
              <button
                className="roomList_options_btns_new notoBold fs-24"
                type="button"
                // onClick={toNew}
              >
                최신순
              </button>
              <button
                className="roomList_options_btns_price notoReg fs-24"
                type="button"
                onClick={toLow}
              >
                조회순
              </button>
            </div>
          )}
          {roomSort === "hits" && (
            <div className="roomList_options_btns flex">
              <button
                className="roomList_options_btns_new notoReg fs-24"
                type="button"
                onClick={toNew}
              >
                최신순
              </button>
              <button
                className="roomList_options_btns_price notoBold fs-24"
                type="button"
                // onClick={toLow}
              >
                조회순
              </button>
            </div>
          )}
        </div>
        <div className="roomList_roomCards">
          {roomList.length !== 0 && 
            roomList.map(({id, categoryDetail, image }) => (
              <RoomCard
                key={v4()}
                id={id}
                categoryDetail={categoryDetail}
                image={image}
              />
            ))
          }
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
