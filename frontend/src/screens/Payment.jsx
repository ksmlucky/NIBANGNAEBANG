/* eslint-disable no-shadow */
import React, { useState, useRef } from "react";
import BoastRoomCard from "../components/room/BoastRoomCard";
import { useNavigate } from "react-router-dom";
import { Button } from "@mui/material";
import Pagination from "@mui/material/Pagination";
import PayItem from "@components/PayItem";

function Payment() {
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
    <div className="container flex">
      <div className="payment">
        <PayItem></PayItem>
      </div>
    </div>
  );
}

export default Payment;
