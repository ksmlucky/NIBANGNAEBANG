// import React, { useState, useEffect } from "react";
import React, { useState } from "react";
import { Link, NavLink, useLocation, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import "./MainNavBar.scss";
// import logoGreen from "@images/logo/logo_text_green.svg";
import logo from "@images/logo/logo.svg";
import dummyicon from "@images/icon/dummyicon.jpg";
// import logoWhite from "@images/logo/logo_text_white.svg";
import menuIcon from "@images/icon/menu.svg";
// import Weather from "@components/common/Weather";
import { reset, selectProfile } from "../../store/user";
import "./NavTooltip.scss";

function MainNavBar() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const Profile = useSelector(selectProfile);
  const { pathname } = useLocation();

  const activeClassName = (active) => {
    const prefix = "left_nav__link flex fs-18 btn--";
    return active ? `${prefix}active` : `${prefix}unactive`;
  };

  const logoutClick = () => {
    sessionStorage.clear();
    dispatch(reset());
    navigate("/");
  };

  const [openTool, setOpenTool] = useState(false);
  const [openMenu, setOpenMenu] = useState(false);

  const openTooltip = () => {
    setOpenTool(!openTool);
  };
  const openMobile = () => {
    setOpenMenu((e) => !e);
  };
  const moveEdit = () => {
    navigate("/infoedit");
    if (openTool === true) {
      setOpenTool(!openTool);
    }
    if (openMenu === true) {
      setOpenMenu(!openMenu);
    }
  };
  const moveShopping = () => {
    navigate("/shopping");
    if (openTool === true) {
      setOpenTool(!openTool);
    }
    if (openMenu === true) {
      setOpenMenu(!openMenu);
    }
  };
  const moveCommu = () => {
    navigate("/board");
    if (openTool === true) {
      setOpenTool(!openTool);
    }
    if (openMenu === true) {
      setOpenMenu(!openMenu);
    }
  };
  const moveCamp = () => {
    navigate("/camping");
    if (openTool === true) {
      setOpenTool(!openTool);
    }
    if (openMenu === true) {
      setOpenMenu(!openMenu);
    }
  };
  const movePlan = () => {
    navigate("/plan");
    if (openTool === true) {
      setOpenTool(!openTool);
    }
    if (openMenu === true) {
      setOpenMenu(!openMenu);
    }
  };
  const moveInfo = () => {
    window.open(
      "https://scratch-octopus-16f.notion.site/500f89ec3f964ea1bb07589d251f5fc8"
    );
    if (openTool === true) {
      setOpenTool(!openTool);
    }
    if (openMenu === true) {
      setOpenMenu(!openMenu);
    }
  };
  const moveMyFeed = () => {
    navigate("/mypage/myfeed");
    if (openTool === true) {
      setOpenTool(!openTool);
    }
    if (openMenu === true) {
      setOpenMenu(!openMenu);
    }
  };
  console.log(process.env.REACT_APP_KAKAO_AUTH_URL);

  return (
    <div className="wrapper flex align-center">
      <div
        id="MainNavBar"
        className={pathname === "/" ? "home_click" : "none_click"}
      >
        <div className="container">
          <nav className="container_inner flex">
            <Link to="/">
              <nav className="mainNav shBold fs-64">니방내방</nav>
            </Link>
          </nav>
        </div>
      </div>
    </div>
  );
}

export default MainNavBar;
