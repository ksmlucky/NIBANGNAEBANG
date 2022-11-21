import React, { useState, useEffect } from "react";
import { getRoomMap } from "../../apis/room";

function MapList({add, loc}) {
  const { kakao } = window;
  useEffect(() => {
    // console.log(loc.roomList[0].address);
    const container = document.getElementById('map');
    const options = {
      center: new kakao.maps.LatLng(35.203108, 126.808409),
      level: 3
    };
    // console.log(loc.roomList);
    const map = new kakao.maps.Map(container, options);
    for (let i = 0; i < loc.roomList.length; i++) {
      const geocoder = new kakao.maps.services.Geocoder();
      geocoder.addressSearch(loc.roomList[i].address, (result, status) => {
        // console.log(loc.roomList[i].roomId);
        // console.log(loc.roomList[i].name);
        if (status === kakao.maps.services.Status.OK) {
          const coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          const Marker = new kakao.maps.Marker({
            map: map,
            position: coords,
            title: loc.roomList[i].name,
            clickable: true,
          });
          kakao.maps.event.addListener(Marker, 'click', () => {
            window.open(`/room/detail/${loc.roomList[i].roomId}`);
          });
        }
      })
    };
  }, []);
  return (
    <div id="map" style={{
      width: "100vw", height: "100vh",
    }}></div>
  );
}
export default MapList;