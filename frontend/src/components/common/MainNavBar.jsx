// import React, { useState, useEffect } from "react";
import React, { useState } from "react";
import { Link, NavLink, useLocation, useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import "./MainNavBar.scss";
import logo from "@images/logo/logo.svg";
import dummyicon from "@images/icon/dummyicon.jpg";
import menuIcon from "@images/icon/menu.svg";
import Box from "@mui/material/Box";
import Avatar from "@mui/material/Avatar";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import ListItemIcon from "@mui/material/ListItemIcon";
import Divider from "@mui/material/Divider";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import Tooltip from "@mui/material/Tooltip";
import PersonAdd from "@mui/icons-material/PersonAdd";
import Settings from "@mui/icons-material/Settings";
import Logout from "@mui/icons-material/Logout";
import OtherHousesIcon from "@mui/icons-material/OtherHouses";
import FaceIcon from "@mui/icons-material/Face";
import Diversity3Icon from "@mui/icons-material/Diversity3";
import EmailIcon from "@mui/icons-material/Email";
import HomeIcon from "@mui/icons-material/Home";
import CropFreeIcon from "@mui/icons-material/CropFree";
import { reset, selectProfile } from "../../store/user";
import TemporaryDrawer from "./Exam";
import "./NavTooltip.scss";

function MainNavBar() {
  const { sessionStorage } = window;
  const AT = sessionStorage.getItem("accessToken");
  const userNickname = sessionStorage.getItem("userNickname");
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const Profile = useSelector(selectProfile);
  const { pathname } = useLocation();
  const activeClassName = (active) => {
    const prefix = "left_nav__link flex fs-18 btn--";
    return active ? `${prefix}active` : `${prefix}unactive`;
  };
  // const logoutClick = () => {
  //   sessionStorage.clear();
  //   dispatch(reset());
  //   navigate("/");
  // };
  const clickProfile = () => {
    navigate("/changenickname");
  };
  const clickMyRoom = () => {
    navigate("/room/mine");
  };
  const clickLogout = () => {
    sessionStorage.clear();
    dispatch(reset());
    // navigate("/");
    navigate("/login");
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
  // console.log(process.env.REACT_APP_KAKAO_AUTH_URL);
  const [anchorEl, setAnchorEl] = useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };
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
            {/* <TemporaryDrawer /> */}
            {AT && (
            <div className="navButton flex">
              {/* <button type="button" className="navButton_plus">
                <CropFreeIcon sx= {{ width: 58, height: 58, color: "#2C4B48" }} />
              </button> */}
              <TemporaryDrawer />
              {/* <React.Fragment> */}
              <>
                <Box
                  sx={{
                    display: "flex",
                    alignItems: "center",
                    textAlign: "center",
                  }}
                >
                  <Tooltip title="내 정보">
                    <IconButton
                      onClick={handleClick}
                      size="small"
                      sx={{ ml: 2 }}
                      aria-controls={open ? "account-menu" : undefined}
                      aria-haspopup="true"
                      aria-expanded={open ? "true" : undefined}
                    >
                      <Avatar
                        sx={{
                          width: 48,
                          height: 48,
                          backgroundColor: "#2C4B48",
                          fontSize: 30,
                          fontFamily: "SeoulHangangB",
                        }}
                      >
                        {userNickname[0]}
                      </Avatar>
                      {/* <OtherHousesIcon sx={{color: "#2C4B48", fontSize: 64}} /> */}
                    </IconButton>
                  </Tooltip>
                </Box>
                <Menu
                  anchorEl={anchorEl}
                  id="account-menu"
                  open={open}
                  onClose={handleClose}
                  onClick={handleClose}
                  PaperProps={{
                    elevation: 0,
                    sx: {
                      overflow: "visible",
                      filter: "drop-shadow(0px 2px 8px rgba(0,0,0,0.32))",
                      mt: 1.5,
                      "& .MuiAvatar-root": {
                        width: 32,
                        height: 32,
                        ml: -0.5,
                        mr: 1,
                      },
                      "&:before": {
                        content: '""',
                        display: "block",
                        position: "absolute",
                        top: 0,
                        right: 14,
                        width: 10,
                        height: 10,
                        bgcolor: "background.paper",
                        transform: "translateY(-50%) rotate(45deg)",
                        zIndex: 0,
                      },
                    },
                  }}
                  transformOrigin={{ horizontal: "right", vertical: "top" }}
                  anchorOrigin={{ horizontal: "right", vertical: "bottom" }}
                >
                  {/* <MenuItem>
                    <Avatar /> Profile
                  </MenuItem>
                  <MenuItem>
                    <Avatar /> My account
                  </MenuItem>
                  <Divider />
                  <MenuItem>
                    <ListItemIcon>
                      <PersonAdd fontSize="small" />
                    </ListItemIcon>
                    Add another account
                  </MenuItem>
                  <MenuItem>
                    <ListItemIcon>
                      <Settings fontSize="small" />
                    </ListItemIcon>
                    Settings
                  </MenuItem>*/}
                  <MenuItem
                    sx={{
                      color: "#2C4B48",
                      fontSize: 32,
                      fontFamily: "SeoulHangangB",
                    }}
                    onClick={clickProfile}
                  >
                    <FaceIcon />
                    &nbsp;내 프로필
                  </MenuItem>
                  <MenuItem
                    sx={{
                      color: "#2C4B48",
                      fontSize: 32,
                      fontFamily: "SeoulHangangB",
                    }}
                    onClick={clickMyRoom}
                  >
                    <HomeIcon />
                    &nbsp;내 양도글
                  </MenuItem>
                  <MenuItem
                    sx={{
                      color: "#2C4B48",
                      fontSize: 32,
                      fontFamily: "SeoulHangangB",
                    }}
                  >
                    <Diversity3Icon />
                    &nbsp;내 반띵
                  </MenuItem>
                  <MenuItem
                    sx={{
                      color: "#2C4B48",
                      fontSize: 32,
                      fontFamily: "SeoulHangangB",
                    }}
                  >
                    <EmailIcon />
                    &nbsp;내 쪽지
                  </MenuItem>
                  <Divider />
                  <MenuItem
                    sx={{
                      color: "#2C4B48",
                      fontSize: 32,
                      fontFamily: "SeoulHangangB",
                    }}
                    onClick={clickLogout}
                  >
                    {/* <ListItemIcon> */}
                    <Logout />
                    {/* </ListItemIcon> */}
                    &nbsp;로그아웃
                  </MenuItem>
                </Menu>
              </>
              {/* </ React.Fragment> */}
            </div>
            )}
          </nav>
        </div>
      </div>
    </div>
  );
}

export default MainNavBar;
