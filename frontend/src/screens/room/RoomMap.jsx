import React, { useState, useRef, useEffect } from "react";
import { getRoomMap } from "../../apis/room";
import MapList from "../../components/room/MapList";
import SearchIcon from "@mui/icons-material/Search";
import "./RoomMap.scss";

function RoomMap() {
  const adRef = useRef();
  const [add, setAdd] = useState("");
  const [roomList, setRoomList] = useState([]);
  const getMapList = async () => {
    const res = await getRoomMap(add);
    // console.log(res.data.articles);
    if (res.message === "성공") {
      if (res.data.articles.length > 0) {
        // setRoomList([]);
        for (let i = 0; i < res.data.articles.length; i++) {
          roomList.push({roomId: res.data.articles[i].id, address: res.data.articles[i].categoryDetail.transfer.address, name: res.data.articles[i].categoryDetail.transfer.roomType+" "+res.data.articles[i].categoryDetail.transfer.contractType});
        }
        setAdd(res.data.articles[res.data.articles.length-1].categoryDetail.transfer.address)
      } else {
        window.alert("해당 지역에 양도 매물이 없어요!");
      }
    }
  };
  useEffect(() => {
    getMapList();
  }, [add]);
  const onClickSearch = async () => {
    // setRoomList([]);
    // console.log(adRef.current.value);
    setAdd(adRef.current.value);
    getMapList();
  };
  const handleOnKeyPress = (e) => {
    if (e.key === "Enter") {
      onClickSearch();
    }
  };
  return (
    <div className="container">
      <div className="top flex">
        <div className="top_input flex">
          <input
            onKeyPress={handleOnKeyPress}
            ref={adRef}
            type="text"
            className="top_input_content shBold fs-24"
            placeholder="찾으려는 주소를 입력해주세요"
          />
            <button
              type="button"
              onClick={onClickSearch}
              className="top_input_btn"
            >
              <SearchIcon sx={{ fontSize: 40 }} />
            </button>
        </div>
      </div>
      {roomList.length !== 0 && 
        <MapList add={{ add }} loc={{ roomList }} />
      }
    </div>
  );
}
export default RoomMap;