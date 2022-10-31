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
// mypage
// import Drop from "@screens/mypage/Drop";
// import DropFinish from "@screens/mypage/DropFinish";
// import InfoEdit from "@screens/mypage/InfoEdit";
// import PwCh from "@screens/mypage/PwCh";
// import PwEdit from "@screens/mypage/PwEdit";
// import MyFeed from "@screens/mypage/MyFeed";

// others
import NotFound from "@screens/NotFound";

// room
import RoomRegister from "@screens/room/RoomRegister";
import RoomShow from "@screens/room/RoomShow";

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
          <Route path="register" element={<RoomRegister />} />
          <Route path="showre" element={<RoomShow />} />
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
        <Route path="/mypage/*">
          <Route path="*" element={<NotFound />} />
        </Route>

        <Route path="*" element={<NotFound />} />
      </Routes>
    </>
  );
}
export default Router;
