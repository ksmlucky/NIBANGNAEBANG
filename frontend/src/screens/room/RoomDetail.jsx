import React from "react";
import tmpImg from "@images/room.png";
import "./RoomDetail.scss";

function RoomDetail() {
  return (
    <div className="container flex">
      <div className="roomDetail flex">
        <div className="roomDetail_photo flex">
          <img src={tmpImg} alt="방 이미지" />
        </div>
        <div className="roomDetail_contents flex">
          <div className="roomDetail_contents_top flex">
            <div className="roomDetail_contents_top_contract shBold fs-36">월세 1100 / 32</div>
            <div className="roomDetail_contents_top_loc shBold fs-32">광주 서구 치평동</div>
            <div className="roomDetail_contents_top_area shBold fs-32">원룸 8평</div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default RoomDetail;