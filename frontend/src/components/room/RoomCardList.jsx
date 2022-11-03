import React from "react";
import RoomCard from "./RoomCard";
import "./RoomCard.scss";

function RoomCardList() {
  return (
    <div className="roomCard_list flex">
      <RoomCard />
      <RoomCard />
      <RoomCard />
      <RoomCard />
      <RoomCard />
      <RoomCard />
    </div>
  );
}
export default RoomCardList;
