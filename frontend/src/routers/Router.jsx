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
import Oauth from "@screens/oauth/Oauth";
import JoinName from "@screens/oauth/JoinName";
import JoinNickname from "@screens/oauth/JoinNickname";
import KakaoLogin from "@screens/oauth/KakaoLogin";
import GoogleLogin from "@screens/oauth/GoogleLogin";
import Join from "@screens/oauth/Join";
import MyPage from "@screens/MyPage";
import ChangeNickname from "@screens/oauth/ChangeNickname";

import ShowCard from "@components/room/ShowCard";

// others
import NotFound from "@screens/NotFound";

// room
import RoomRegister from "@screens/room/RoomRegister";
import RoomDetail from "@screens/room/RoomDetail";
import MyRoomList from "@screens/room/MyRoomList";
import RoomList from "@screens/room/RoomList";
import RoomTrs from "@screens/room/RoomTrs";
import TrsList from "@screens/room/TrsList";
import RoomShow from "@screens/room/RoomShow";
import ShowList from "@screens/room/ShowList";
import BoastRoom from "@screens/room/BoastRoom";
import BoastReg from "@screens/room/BoastReg";
import RoomMap from "@screens/room/RoomMap";

// Community
import Payment from "@screens/Payment";
import PayItem from "@components/PayItem";
// import Community from "./Community";

function Router() {
  return (
    <>
      <MainNavBar />
      <Routes>
        {/* main */}
        <Route path="/" element={<Home />} />
        {/* oauth */}
        <Route path="/oauth" element={<Oauth />} />
        {/* login */}
        <Route path="/login/*">
          <Route path="" element={<Login />} />
          <Route path="join" element={<Join />} />
          <Route path="joinname" element={<JoinName />} />
          <Route path="joinnickname" element={<JoinNickname />} />
        </Route>
        <Route path="/changenickname" element={<ChangeNickname />} />
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
          <Route path="register" element={<RoomRegister />} />
          <Route path="detail/:id" element={<RoomDetail />} />
          <Route path="mine" element={<MyRoomList />} />
          <Route path="roomlist" element={<RoomList />} />
          <Route path="roomtrs" element={<RoomTrs />} />
          <Route path="trslist" element={<TrsList />} />
          <Route path="showre" element={<RoomShow />} />
          <Route path="showlist" element={<ShowList />} />
          <Route path="showcard" element={<ShowCard />} />
          <Route path="boastReg" element={<BoastReg />} />
          <Route path="boastlist" element={<BoastRoom />} />
          <Route path="map" element={<RoomMap/>} />
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

        <Route path="/payment/*">
          <Route path="*" element={<Payment />}></Route>
        </Route>
        <Route path="*" element={<NotFound />} />
      </Routes>
    </>
  );
}
export default Router;
