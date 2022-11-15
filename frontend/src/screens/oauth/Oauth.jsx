import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { setProvider, setProviderId } from "@store/user";
import queryString from "query-string";
import "./Spinner.scss";

function Oauth() {
  const dispatch = useDispatch();
  const [pvd, setPvd] = useState("");
  const url = queryString.parse(useLocation().search);
  // const updateProvider = () => dispatch(setProvider(url.provider));
  // const updateProviderId = () => dispatch(setProviderId(url.providerId));
  const user = useSelector((state) => state.user);
  const navigate = useNavigate();
  const total = () => {
    setPvd(url.provider);
    dispatch(setProvider(url.provider));
    dispatch(setProviderId(url.providerId));
    console.log(url.accessToken);
    console.log(url.refreshToken);
    console.log(url);
    // console.log(user);
    if (url.provider !== undefined) {
      if (url.isExisted === "true") {
        sessionStorage.setItem("refreshToken", url.refreshToken);
        sessionStorage.setItem("accessToken", url.accessToken);
        // sessionStorage.setItem("userNickname", nickname);
        navigate("/");
      } else {
        navigate("/login/joinname");
      }
    }
  };
  useEffect(() => {
    total();
    // dispatch(setProvider(url.provider));
    // dispatch(setProviderId(url.providerId));
  }, [pvd]);
  return (
    <div className="container flex align-center justify-center">
      <div className="spinner">
        <span className="spinner-inner-1"> </span>
        <span className="spinner-inner-2"> </span>
        <span className="spinner-inner-3"> </span>
      </div>
    </div>
  );
}
export default Oauth;
