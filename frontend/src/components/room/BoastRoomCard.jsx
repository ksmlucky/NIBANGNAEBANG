import React, { useState, userRef } from "react";
import "./BoastRoomCard.scss";
import img from "@images/extra/room3.jpg";


function BoastRoomCard() {
    
    return (
        <div className="BoastCard">
            <div className="BoastCard_image">
                <img src={img} alt="" />
            </div>
            <div className = "BoastCard_desc flex">
                <div className = "BoastCard_desc_title notoBold fs-20">랜선 집들이</div>
                <div className = "BoastCard_desc_detail flex">
                    <div className = "BoastCard_desc_detail_writer">작성자</div>
                    <div className = "BoastCard_desc_detail_date">2022.11.02</div>
                </div>
            </div>
        </div>
    );
}

export default BoastRoomCard;