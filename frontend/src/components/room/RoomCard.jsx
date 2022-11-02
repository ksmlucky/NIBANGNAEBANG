import React from "react";
import "./RoomCard.scss";
import tmpImg from "@images/room.png";
function RoomCard(){


    return (
            <div className = "roomCard">
                <img src={tmpImg} alt="방 사진" />  
                <div className = "roomCard_detail">
                    <div className = "roomdCard_detail_line1 flex notoBold fs-20">
                        <div className = "roomCard_detail_line1_price">300/25</div>
                        <div className = "roomCard_detail_line1_shape">원룸</div>
                    </div>
                    <div className = "roomdCard_detail_line2 flex Bold fs-16">
                        <div className = "roomCard_detail_line2_region">광주 치평동</div>
                        <div className = "roomCard_detail_line2_floor">3층</div>
                        <div className = "roomCard_detail_line2_area">19.83m2</div>
                    </div>
                    <div className = "roomdCard_detail_line3 flex Bold fs-16">
                        <div className = "roomCard_detail_line3_adminCost">관리비 5만원</div>
                        <div className = "roomCard_detail_line3_elevator">엘리베이터 O</div>
                    </div>
                    
                </div>
            </div>
    );
}

export default RoomCard;