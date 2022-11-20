import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import tmpImg from "@images/room.png";
import "./RoomCard.scss";

function RoomCard({ id, categoryDetail, image }) {
  const [titleImage, setTitleImage] = useState("");
  const navigate = useNavigate();
  const onClickThumbnail = () => {
    navigate(`/room/detail/${id}`);
  };
  // setTitleImage(image.url);
  // console.log(image);
  // if (image !== undefined) {
  //   console.log(image.url);
  //   setTitleImage(image);
  // }
  return (
    <button type="button" onClick={onClickThumbnail}>
      <div className="roomCard flex">
        <div className="roomCard_img">
          {image !== undefined &&
            <img src={image.url} alt="방사진" />
          }
          {image === undefined &&
            <img src={tmpImg} alt="방 사진" />
          }
        </div>
        <div className="roomCard_contents flex">
          <div className="roomCard_contents_address shBold fs-24 flex">
            {categoryDetail.transfer.address}
          </div>
          {categoryDetail.transfer.contractType === "월세" &&
            <div className="roomCard_contents_contract shBold fs-28 flex">
              월세 {categoryDetail.transfer.deposit} / {categoryDetail.transfer.monthlyRent}
            </div>
          }
          {categoryDetail.transfer.contractType === "전세" &&
            <div className="roomCard_contents_contract shBold fs-28 flex">
              전세 {categoryDetail.transfer.deposit}
            </div>
          }
          <div className="roomCard_contents_detail flex shBold fs-24">
            <div className="roomCard_contents_detail_room">{categoryDetail.transfer.roomType} {categoryDetail.transfer.roomSize}평</div>
            <div className="roomCard_contents_detail_floor">{categoryDetail.transfer.floor}층</div>
          </div>
        </div>
      </div>
    </button>
  );
}
export default RoomCard;
