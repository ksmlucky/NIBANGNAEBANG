import React from 'react'
import { Paper, Button } from '@mui/material'
import "./Item.scss";

function Item(props) {
  console.log(props);
  return (
      <Paper className="paper">
        <div className="img">
          <img src={props.item.url} alt="이미지" />
        </div>
        {/* <h2>{props.id}</h2>
        <p>{props.url}</p> */}
        {/* <Button className="CheckButton" /> */}
            {/* Check it out!
        </Button> */}
      </Paper>
  )
}
export default Item;