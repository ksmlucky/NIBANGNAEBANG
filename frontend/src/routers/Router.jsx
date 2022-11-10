import React from "react";
import { Route, Routes } from "react-router-dom";
import MainNavBar from "@components/common/MainNavBar";

import Home from "@screens/Home";

// user
// import FindId from "@screens/user/FindId";
// import FindIdFinish from "@screens/user/FindIdFinish";
// import FindPw from "@screens/user/FindPw";
// import FindPwCh from "@screens/user/FindPwCh";
// import FindPwFinish from "@screens/user/FindPwFinish";
import Login from "@screens/oauth/Login";
import KakaoLogin from "@screens/oauth/KakaoLogin";
import GoogleLogin from "@screens/oauth/GoogleLogin";
import Join from "@screens/oauth/Join";
import MyPage from "@screens/MyPage";

import ShowCard from "@components/room/ShowCard";
// mypage
// import Drop from "@screens/mypage/Drop";
// import DropFinish from "@screens/mypage/DropFinish";
// import InfoEdit from "@screens/mypage/InfoEdit";
// import PwCh from "@screens/mypage/PwCh";
// import PwEdit from "@screens/mypage/PwEdit";
// import MyFeed from "@screens/mypage/MyFeed";

// others
import NotFound from "@screens/NotFound";
import Test from "@screens/room/Test";
// room
import RoomTrs from "@screens/room/RoomTrs";
import TrsList from "@screens/room/TrsList";
import RoomShow from "@screens/room/RoomShow";
import ShowList from "@screens/room/ShowList";
import RoomCard from "@components/room/RoomCard";
// import BoastRoom from "@screens/room/BoastRoom";
// import BoastReg from "@screens/room/BoastReg";

// Community
// import Community from "./Community";

function Router() {
  return (
    <>
      <MainNavBar />
      <Routes>
        {/* main */}
        <Route path="/" element={<Home />} />
        {/* login */}
        <Route path="/login/*">
          <Route path="" element={<Login />} />
          <Route path="join" element={<Join />} />
        </Route>
        <Route path="/kakao" element={<KakaoLogin />} />
        <Route path="/google" element={<GoogleLogin />} />
        {/* join */}
        {/* <Route path="/join/*">
          <Route path="*" element={<NotFound />} />
        </Route> */}
        {/* find ID/PW  */}
        <Route path="/findid/*">
          <Route path="*" element={<NotFound />} />
        </Route>
        <Route path="/findpw/*">
          <Route path="*" element={<NotFound />} />
        </Route>
        {/* info edit */}
        <Route path="/infoedit/*">
          <Route path="*" element={<NotFound />} />
        </Route>

        {/* Drop */}
        <Route path="/drop/*">
          <Route path="*" element={<NotFound />} />
        </Route>

        {/* Room */}
        <Route path="/room/*">
          <Route path="roomtrs" element={<RoomTrs />} />
          <Route path="trslist" element={<TrsList />} />
          <Route path="showre" element={<RoomShow />} />
          <Route path="showlist" element={<ShowList />} />
          <Route path="showcard" element={<ShowCard />} />
          <Route path="boastReg" element={<RoomTrs />} />
          <Route path="boastlist" element={<RoomTrs/>} />
        </Route>

        {/* camping */}
        <Route path="/camping/*">
          <Route path="*" element={<NotFound />} />
        </Route>
        {/* plan */}
        <Route path="/plan/*">
          <Route path="*" element={<NotFound />} />
        </Route>

        {/* mypage */}
        <Route path="/test/*">
          <Route path="*" element={<RoomTrs />} />
        </Route>

        <Route path="*" element={<NotFound />} />
      </Routes>
    </>
  );
}
export default Router;
