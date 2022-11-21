/* eslint-disable no-shadow */
import React, { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "@mui/material";
import img from "@images/netflix.png";
import PeopleIcon from "@mui/icons-material/People";
import PaymentIcon from "@mui/icons-material/Payment";
import "./PayItem.scss";

function PayItem() {
  const [roomList, setroomList] = useState([]);
  const [size, setSize] = useState(10);
  const [page, setPage] = useState(1);
  const handleChange = (event, value) => {
    setPage(value);
  };
  // setSize = 10;
  const navigateToReg = () => {
    navigate("/test");
  };
  return (
    <div className="payItem">
      <div className="payItem_img flex">
        <img src={img} alt="" />
      </div>
      <div className="payItem_detail">
        <div className="payItem_detail_price flex">
          <div className="payItem_detail_price_icon">
            <PaymentIcon sx={{ fontSize: 40 }}></PaymentIcon>
          </div>
          <div className="payItem_detail_price_num notoBold fs-16">14000원 </div>
        </div>
        <div className="payItem_detail_member flex">
          <div className="payItem_detail_member_icon">
            <PeopleIcon sx={{ fontSize: 40 }}></PeopleIcon>
          </div>
          <div className="payItem_detail_member_curr notoBold fs-16">3/4명</div>
        </div>
      </div>
    </div>
  );
}

export default PayItem;
