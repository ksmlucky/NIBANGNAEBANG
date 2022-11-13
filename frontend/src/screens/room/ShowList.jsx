import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Pagination from "@mui/material/Pagination";
import CreateIcon from "@mui/icons-material/Create";
import ShowCardList from "@components/room/ShowCardList";
import "./ShowList.scss";

function ShowList() {
  const navigate = useNavigate();
  const [size, setSize] = useState(10);
  const [page, setPage] = useState(1);
  const handleChange = (event, value) => {
    setPage(value);
  };
  const writeShow = () => {
    navigate(`/room/showre`);
  };
  return (
    <div className="container flex">
      <div className="showList flex">
        <div className="showList_title flex">
          <div className="showList_title_txt notoBold fs-44 flex">
            방 자랑하기
          </div>
          <button
            type="button"
            onClick={writeShow}
            className="showList_title_btn flex notoBold fs-20"
          >
            <CreateIcon
              sx={{ color: "white", fontSize: 24 }}
              className="showList_title_btn_icon"
            />
            <div className="showList_title_btn_txt">자랑하기</div>
          </button>
        </div>
        <div className="showList_comps notoReg fs-16">
          <ShowCardList />
        </div>
        <div className="showList_pageNum flex justify-center">
          <Pagination
            count={size}
            page={page}
            onChange={handleChange}
            hidePrevButton
            hideNextButton
            size="large"
          />
        </div>
      </div>
    </div>
  );
}
export default ShowList;
