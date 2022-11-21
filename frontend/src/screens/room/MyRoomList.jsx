import React, { useState, useRef, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import SearchIcon from "@mui/icons-material/Search";
import Pagination from "@mui/material/Pagination";
import { getRoomListMy, getRoomListAd } from "../../apis/room";
import RoomCardList from "@components/room/RoomCardList";
import { v4 } from "uuid";
import RoomCard from "@components/room/RoomCard";
import "./MyRoomList.scss";

function MyRoomList() {
  // 보증금 순으로하려면 transfer.deposit으로 할 것
  // 방의 다른 정보 활용하려면 마찬가지로 transfer.{}로 할 수 있음(roomSize, startDate -> 바로 계약할 수 있는 케이스 등)
  const navigate = useNavigate();
  const [roomList, setRoomList] = useState([]);
  const getList = async () => {
    const res = await getRoomListMy();
    console.log(res.data);
    setRoomList(res.data);
    // setRoomList(res.data.articles);
    // console.log(roomList.length);
    // console.log(res);
  };
  useEffect(() => {
    getList();
  }, []);
  const toRegister = () => {
    navigate("/room/register");
  };
  return (
    <div className="container flex">
    <div className="myRoomList flex">
      <div className="myRoomList_title shBold fs-56">내가 내놓은 방</div>
      <div className="myRoomList_optionTitle shBold fs-40">한 번에 모아보기</div>
      {/* <div className="myRoomList_options flex">
        <div className="myRoomList_options_input flex">
          <input
            onKeyPress={handleOnKeyPress}
            ref={adRef}
            type="text"
            className="myRoomList_options_input_content shBold fs-24"
            placeholder="찾으려는 주소를 입력해주세요"
          />
          <button
            type="button"
            onClick={onClickSearch}
            className="myRoomList_options_input_btn"
          >
            <SearchIcon sx={{ fontSize: 40 }} />
          </button>
        </div>
        {roomSort === "createdAt" && (
          <div className="myRoomList_options_btns flex">
            <button
              className="myRoomList_options_btns_new notoBold fs-24"
              type="button"
              // onClick={toNew}
            >
              최신순
            </button>
            <button
              className="myRoomList_options_btns_price notoReg fs-24"
              type="button"
              onClick={toLow}
            >
              조회순
            </button>
          </div>
        )}
        {roomSort === "hits" && (
          <div className="myRoomList_options_btns flex">
            <button
              className="myRoomList_options_btns_new notoReg fs-24"
              type="button"
              onClick={toNew}
            >
              최신순
            </button>
            <button
              className="myRoomList_options_btns_price notoBold fs-24"
              type="button"
              // onClick={toLow}
            >
              조회순
            </button>
          </div>
        )}
      </div> */}
      <div className="myRoomList_roomCards">
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
        {roomList.length === 0 &&
          <div className="myRoomList_roomCards_none flex">
            <div className="myRoomList_roomCards_none_txt shBold fs-56">등록된 양도글이 없어요!</div>
            <button type="button" className="myRoomList_roomCards_none_btn shBold fs-48" onClick={toRegister}>양도글 등록하러 가기</button>
          </div>
        }
      </div>
      {/* <div className="myRoomList_page flex justify-center">
        <Pagination
        count={size}
        page={page}
        onChange={handleChange}
        hidePrevButton
        hideNextButton
        size="large"
        />
      </div> */}
    </div>
  </div>
  );
}
export default MyRoomList;
