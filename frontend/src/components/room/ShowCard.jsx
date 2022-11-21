import React, { useState } from "react";
import FavoriteBorderIcon from "@mui/icons-material/FavoriteBorder";
import FavoriteIcon from "@mui/icons-material/Favorite";
import tmpImg from "@images/room.png";
import tmpProfile from "@images/extra/default.png";
import "./ShowCard.scss";
function ShowCard() {
  const [isLike, setIsLike] = useState(0);
  const [likeNum, setLikeNum] = useState(20);
  function yesLike() {
    setLikeNum(likeNum + 1);
    setIsLike(1);
  }
  function noLike() {
    setLikeNum(likeNum - 1);
    setIsLike(0);
  }
  return (
    <div className="showCard">
      <div className="showCard_img">
        <img src={tmpImg} alt="방 사진" />
        {isLike === 0 && (
          <button type="button" className="showCard_img_btn" onClick={yesLike}>
            <FavoriteBorderIcon
              className="showCard_img_btn_like"
              sx={{ color: "red", fontSize: 24 }}
            />
          </button>
        )}
        {isLike === 1 && (
          <button type="button" className="showCard_img_btn" onClick={noLike}>
            <FavoriteIcon
              className="showCard_img_btn_like"
              sx={{ color: "red", fontSize: 24 }}
            />
          </button>
        )}
      </div>
      <div className="showCard_detail flex">
        <div className="showCard_detail_title flex justify-center notoBold fs-26">
          이사하면서 새로 꾸민 내 방
        </div>
        <div className="showCard_detail_info flex">
          <div className="showCard_detail_info_profile flex">
            <div className="showCard_detail_info_profile_img">
              <img src={tmpProfile} alt="프로필 이미지" />
            </div>
            <div className="showCard_detail_info_profile_nickname notoReg fs-18">
              닉네임엄청길면어떻게
            </div>
          </div>
          <div className="showCard_detail_info_like flex">
            <FavoriteIcon sx={{ fontSize: 32, color: "red" }} />
            <div className="showCard_detail_info_like_count notoReg fs-18">
              {likeNum}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default ShowCard;
