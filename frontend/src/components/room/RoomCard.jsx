import React from "react";
import { useNavigate } from "react-router-dom";
import tmpImg from "@images/room.png";
import "./RoomCard.scss";

function RoomCard({
  roomId
}) {
  const navigate = useNavigate();
  const onClickThumbnail = () => {
    navigate(`/room/detail/${roomId}`);
  };
  return (
    <button type="button" onClick={onClickThumbnail}>
      <div className="roomCard flex">
        <div className="roomCard_img">
          <img src={tmpImg} alt="방 사진" />
        </div>
        <div className="roomCard_contents flex">
          <div className="roomCard_contents_address shBold fs-24 flex">광주광역시 서구 치평동</div>
          <div className="roomCard_contents_contract shBold fs-28 flex">월세 5000 / 100</div>
          <div className="roomCard_contents_detail flex shBold fs-24">
            <div className="roomCard_contents_detail_room">투룸 19.83㎡</div>
            <div className="roomCard_contents_detail_floor">5층</div>
          </div>
        </div>
      </div>
    </button>
  );
}
export default RoomCard;