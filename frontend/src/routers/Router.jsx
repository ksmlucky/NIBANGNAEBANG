import React from "react";
import { Route, Routes } from "react-router-dom";
import MainNavBar from "@components/common/MainNavBar";

import Home from "@screens/Home";

// user
// import Login from "@screens/user/Login";
// import Join from "@screens/user/Join";
// import JoinFinish from "@screens/user/JoinFinish";
// import FindId from "@screens/user/FindId";
// import FindIdFinish from "@screens/user/FindIdFinish";
// import FindPw from "@screens/user/FindPw";
// import FindPwCh from "@screens/user/FindPwCh";
// import FindPwFinish from "@screens/user/FindPwFinish";
import KakaoLogin from "@screens/oauth/KakaoLogin";
// import NaverLogin from "@screens/oauth/NaverLogin";
import GoogleLogin from "@screens/oauth/GoogleLogin";
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
        <Route path="/kakao" element={<KakaoLogin />} />
        <Route path="/google" element={<GoogleLogin />} />
        {/* join */}
        <Route path="/join/*">
          <Route path="*" element={<NotFound />} />
        </Route>
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
