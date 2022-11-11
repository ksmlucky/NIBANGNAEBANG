/* eslint-disable no-shadow */
import React, { useState, useRef } from "react";
import BoastRoomCard from "../../components/room/BoastRoomCard";
import { useNavigate } from "react-router-dom";
import { Button } from "@mui/material";
import Pagination from "@mui/material/Pagination";

function BoastRoom(){
    const [roomList,setroomList] = useState([]);
    const [size, setSize] = useState(10);
    const [page, setPage] = useState(1);
    const handleChange = (event, value) => {
        setPage(value);
    };
    // setSize = 10;
    const navigateToReg = () => {
        navigate("/test");
      };
    return(
        <div className = "container flex">
            <div className = "BoastList">
                <div className = "BoastList_title notoBold fs-32">방 자랑하기</div>
                <div className = "BoastList_regBtn">
                    <Button onclick={navigateToReg}> 방 자랑하기 </Button>
                </div>
                <div className = "BoastList_line1 flex">
                    <BoastRoomCard></BoastRoomCard>
                    <BoastRoomCard></BoastRoomCard>
                    <BoastRoomCard></BoastRoomCard>
                    <BoastRoomCard></BoastRoomCard>
                </div>
                <div className = "BoastList_line2 flex">
                    <BoastRoomCard></BoastRoomCard>
                    <BoastRoomCard></BoastRoomCard>
                    <BoastRoomCard></BoastRoomCard>
                    <BoastRoomCard></BoastRoomCard>
                </div>
                <div className = "BoastList_tmp flex"></div> 
                <div className = "BoastList_page"> 
                    
                </div>
            </div>
        </div>
    );
}

export default BoastRoom;