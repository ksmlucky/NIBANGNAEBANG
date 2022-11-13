import React from "react";
import RoomsCard from "./RoomsCard";
import "./RoomCard.scss";

function RoomCardsList() {
  return (
    <div className="roomsCard_list flex">
      <RoomsCard />
      <RoomsCard />
      <RoomsCard />
      <RoomsCard />
      <RoomsCard />
      <RoomsCard />
    </div>
  );
}
export default RoomCardsList;
