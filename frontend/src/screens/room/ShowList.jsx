import React, { useState } from "react";
import Pagination from "@mui/material/Pagination";
import "./ShowList.scss";
import RoomCard from "@components/room/RoomCard";

function ShowList() {
  const [size, setSize] = useState(10);
  const [page, setPage] = useState(1);
  const handleChange = (event, value) => {
    setPage(value);
  };
  return (
    <div className="container flex">
      <div className="showList flex">
        <div className="showList_title notoBold fs-52 flex">방 자랑하기</div>
        <div className="showList_comps notoReg fs-16">
          {/* 컴포넌트 완성되면 <br />
          컴포넌트 4*2로 투입 <br /> */}
          <RoomCard/>
          {page}
        </div>
        <div className="showList_pageNum flex justify-center">
          <Pagination
            count={size}
            page={page}
            onChange={handleChange}
            hidePrevButton
            hideNextButton
            // boundaryCount={2}
            size="large"
          />
        </div>
      </div>
    </div>
  );
}
export default ShowList;
