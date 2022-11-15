/*global kakao*/
import React, { useEffect, useState } from "react";
import {useNavigate} from "react-router-dom";
import DaumPostcode from "react-daum-postcode";
// import Geocode from "react-geocode";

const Post = (props) => {
  const address = props.address;
  const setAddress = props.setAddress;
  const location = props.location;
  const navigate = useNavigate();
  const { sessionStorage } = window;
  // const { daum } = window;
  // const mapContainer = document.getElementById('myMap');
  // const options = {
  //   center: new daum.maps.LatLng(35.12, 129.1),
  //   level: 3
  // };
  // const map = new daum.maps.Map(mapContainer, options);
  // const geocoder = new daum.maps.services.Geocoder();
  // const marker = new daum.maps.Marker({
  //   position: new daum.maps.LatLng(37.537187, 127.005476),
  //   map: map
  // });
  // const test = () => {
  //   new daum.Postcode({
  //     oncomplete: function(data) {
  //       const addr = data.address;
  //       geocoder.addressSearch(data.address, (results, status) => {
  //         console.log(results);
  //         console.log(status);
  //       });
  //     }
  //   }).open();
  // };
    // if (status === kakao.maps.services.Status.OK) {
    //   const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
    //   // 결과값으로 받은 위치를 마커로 표시합니다
    //   const marker = new kakao.maps.Marker({
    //     map: map,
    //     position: coords
    //   });
    //   // 인포윈도우로 장소에 대한 설명을 표시합니다
    //   const infowindow = new kakao.maps.InfoWindow({
    //     content: '<div style="width:150px;color:red;text-align:center;padding:6px 0;">내가 썼지롱</div>'
    //   });
    //   infowindow.open(map, marker);
    //   // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
    //   map.setCenter(coords);
    // }
  // Geocode.setApiKey("AIzaSyDNOtwmwmq8Nmjr_S28axzs0R6XWgH85p4");
  // Geocode.setLanguage("ko");
  // Geocode.setRegion("ko");
  // Geocode.enableDebug();
  // const GetGeo = async (adr) => {
  //   Geocode.fromAddress(adr)
  //     .then(res => {
  //       console.log(res);
  //     })
  // };
  // GetGeo("광주 서구 상무공원로10");
  const onCompletePost = (data) => {
    console.log(data);
    setAddress(data.address);
    sessionStorage.setItem("location", data.address);
    navigate("/room/register");
  };

  const postCodeStyle = {
    display: "block",
    position: "absolute",
    top: "25%",
    left: "30%",
    width: "400px",
    height: "480px",
    padding: "7px", 
    zIndex: 100,
  };

  // const [loc, setLoc] = useState("");

  return (
    <>
      <DaumPostcode
        defaultQuery={location}
        style={postCodeStyle}
        autoClose
        onComplete={onCompletePost}
      />
      {/* <div id="myMap" /> */}
    </>
  );
};

export default Post;
