import React from "react";
import "./Home.scss";

function Home() {
  return (
    <div className="container">
      <div className="home_back  flex">
        {/* <img src={mainImg} alt="main"> */}
        <div className="home1">
          <div className="home1_title fs-60 notoBold">
            어떤 방을 찾고 있나요?
          </div>
          <div className="home1_sub fs-24 notoBold">
            최근 검색한 치평동 추천 매물
          </div>
        </div>
        <div className="home2 flex">
          <div className="home2_txt flex">
            <div className="home2_txt_title fs-60 notoBold">
              지금 어떤 글이 인기있나요?
            </div>
            <div className="home2_txt_sub fs-24 notoBold">실시간 인기글</div>
          </div>
        </div>
        <div className="home3" />
        <div className="home4" />
      </div>
    </div>
  );
}

export default Home;
