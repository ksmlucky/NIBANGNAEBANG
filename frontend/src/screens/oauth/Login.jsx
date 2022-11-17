import React from "react";
import Avatar from "@mui/material/Avatar";
import CssBaseline from "@mui/material/CssBaseline";
import Link from "@mui/material/Link";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import navLogo from "@images/logo/logo.svg";
import KakaoLogin from "@images/extra/kakao_login_medium_narrow.png";
import HttpsIcon from "@mui/icons-material/Https";
import "./Login.scss";

const REST_API_KEY = process.env.REACT_APP_REST_API_KEY;
const REDIRECT_URI = "http://j7c105.p.ssafy.io/oauth/kakao";
// const KAKAO_AUTH_URI = `https://kauth.kakao.com/oauth/authorize?client_id=${REST_API_KEY}&redirect_uri=${REDIRECT_URI}&response_type=code`;
const KAKAO_AUTH_URI = `https://k7c109.p.ssafy.io:8080/auth-service/oauth2/authorization/kakao`;

function Copyright(props) {
  return (
    <Typography
      variant="body2"
      color="text.secondary"
      align="center"
      {...props}
    >
      {"Copyright © "}
      <Link color="inherit" href="">
        니방내방
      </Link>{" "}
      {new Date().getFullYear()}
      {"."}
    </Typography>
  );
}

const theme = createTheme();

function Login() {
  const LoginProceed = `http://localhost:3000/login/joinname`;
  const { sessionStorage } = window;
  console.log(sessionStorage);
  return (
    <div className="container flex">
      <div className="login flex">
        <div className="login_title shBold fs-56">
          내 방, 잘 사고 잘 팔고 싶을 때
        </div>
        <div className="login_box flex">
          <HttpsIcon sx={{ fontSize: 80 }} className="login_box_lock" />
          <div className="login_box_logo shBold fs-60">니방내방</div>
          <div className="login_box_kakao1">
            <a href={KAKAO_AUTH_URI} aria-label="Kakao">
              <img src={KakaoLogin} alt="" />
            </a>
          </div>
          <div className="login_box_kakao2">
            <a href={LoginProceed} aria-label="Kakao">
              <img src={KakaoLogin} alt="" />
            </a>
          </div>
          <Copyright className="login_box_copyright" />
        </div>
      </div>
      {/* <ThemeProvider theme={theme}>
        <Container
          style={{
            border: "solid #6cc24a",
            marginTop: "100px",
            marginBottom: "30vh",
            boxShadow: "4px 4px 4px gray",
            backgroundColor: "#8bc34a",
          }}
          component="main"
          maxWidth="xs"
        >
          <CssBaseline />
          <Box
            sx={{
              marginTop: 8,
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            <Avatar sx={{ m: 1, bgcolor: "#212121" }}>
              <LockOutlinedIcon />
            </Avatar>
            <Box
              sx={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
              }}
            >
              <img
                style={{ width: "50%", margin: "10px" }}
                className="logo"
                title="!213"
                alt="logoImage"
                src={navLogo}
              />
            </Box>
            <Box component="form" noValidate sx={{ mt: 1 }}>
              <Box
                style={{ width: "100%" }}
                sx={{
                  display: "flex",
                  justifyContent: "center",
                  alignItems: "center",
                  marginTop: 3,
                  marginBottom: 2,
                }}
              >
                <a href={KAKAO_AUTH_URI} aria-label="Kakao">
                  <img style={{ width: "100%" }} src={KakaoLogin} alt="" />
                </a>
              </Box>
            </Box>
          </Box>
          <Copyright sx={{ mt: 6, mb: 4 }} />
        </Container>
      </ThemeProvider> */}
    </div>
  );
}

export default Login;
