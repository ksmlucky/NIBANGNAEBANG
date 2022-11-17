/*global kakao*/
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import DaumPostcode from "react-daum-postcode";

const Post = (props) => {
  const address = props.address;
  const setAddress = props.setAddress;
  //   const location = props.location;
  const navigate = useNavigate();
  const { sessionStorage } = window;
  const onCompletePost = (data) => {
    console.log(data);
    setAddress(data.address);
    sessionStorage.setItem("location", data.address);
    // navigate("/room/register");
  };

  const postCodeStyle = {
    display: "block",
    position: "absolute",
    top: "25%",
    left: "15%",
    width: "400px",
    height: "480px",
    padding: "7px",
    zIndex: 100,
  };

  // const [loc, setLoc] = useState("");

  return (
    <>
      <DaumPostcode
        // defaultQuery={location}
        style={postCodeStyle}
        autoClose
        onComplete={onCompletePost}
      />
      {/* <div id="myMap" /> */}
    </>
  );
};

export default Post;
