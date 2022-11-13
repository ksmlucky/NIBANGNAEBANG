import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { setProvider, setProviderId } from '@store/user';
import queryString from 'query-string';

function Oauth() {

  const dispatch = useDispatch();
  const [pvd, setPvd] = useState("");
  const url = queryString.parse(useLocation().search);
  // const updateProvider = () => dispatch(setProvider(url.provider));
  // const updateProviderId = () => dispatch(setProviderId(url.providerId));
  const user = useSelector(state => state.user);
  const navigate = useNavigate();
  const total = () => {
    setPvd(url.provider);
    dispatch(setProvider(url.provider));
    dispatch(setProviderId(url.providerId));
    console.log(user);
    if (url.provider !== undefined) {
      navigate("/login/joinname");
    }
  };
  useEffect(() => {
    total();
    // dispatch(setProvider(url.provider));
    // dispatch(setProviderId(url.providerId));
  }, [pvd]);
  return (
    <div className="oauth">
      ㅇㅇ
    </div>
  );
}
export default Oauth;