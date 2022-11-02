import React from "react";
import ShowCard from "./ShowCard";
import "./ShowCard.scss";

function ShowCardList() {
  return (
    <div className="showCard_list flex">
      <ShowCard />
      <ShowCard />
      <ShowCard />
      <ShowCard />
      <ShowCard />
      <ShowCard />
    </div>
  );
}
export default ShowCardList;
