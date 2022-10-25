import React from "react";
import "./Home.scss";

function Home() {
  return (
    <div className="container">
      <div className="home_back  flex justify-center">
        {/* <img src={mainImg} alt="main"> */}
        <div className="home">
          <div className="home_title fs-60 notoBold">
            어떤 방을 찾고 있나요?
          </div>
          <div className="home_sub fs-24 notoBold">
            최근 검색한 치평동 추천 매물
          </div>
        </div>
      </div>
    </div>
  );
}

export default Home;
