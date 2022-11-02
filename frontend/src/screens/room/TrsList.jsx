import React, { useEffect, useState } from "react";
import Box from "@mui/material/Box";
import IconButton from "@mui/material/IconButton";
import SearchIcon from "@mui/icons-material/Search";
import TextField from "@mui/material/TextField";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";

import "./TrsList.scss";
import KakaoMap from "@components/KakaoMap";

function TrsList() {
  const [room, setRoom] = useState("모두");
  const [town, setTown] = useState("");
  const handleChange = (e) => {
    setRoom(e.target.value);
  };
  return (
    <div className="container">
      <div className="trsList flex">
        <div className="trsList_map flex">
          <div className="trsList_map_title flex justify-center">
            <KakaoMap />
          </div>
        </div>
        <div className="trsList_detail flex justify-center">
          <div className="trsList_detail_search flex">
            <div className="trsList_detail_search_btn">
              <div className="trsList_detail_search_btn_space" />
              <IconButton
                type="submit"
                sx={{ p: "4px" }}
                aria-label="search"
                className="trsList_detail_search_btn_icon"
              >
                <SearchIcon sx={{ fontSize: "24px" }} />
              </IconButton>
            </div>
            <Box
              component="form"
              className="trsList_detail_search_box"
              sx={{
                "& .MuiTextField-root": { m: 1 },
              }}
              noValidate
              autoComplete="off"
            >
              {/* <div> */}
              <TextField
                id="standard-search"
                label="동네 검색"
                type="search"
                variant="standard"
              />
              {/* </div> */}
            </Box>
            <div className="trsList_detail_search_select">
              <div className="trsList_detail_search_select_space" />
              <FormControl sx={{ m: 1, minWidth: 100 }} size="small">
                <InputLabel id="select-room-small">방 형태</InputLabel>
                <Select
                  labelId="select-room-small"
                  id="select-room-small"
                  value={room}
                  label="방 형태"
                  onChange={handleChange}
                >
                  {/* <MenuItem value="">
                    <em>None</em>
                  </MenuItem> */}
                  <MenuItem value={"모두"}>모두</MenuItem>
                  <MenuItem value={"원룸"}>원룸</MenuItem>
                  <MenuItem value={"투룸"}>투룸</MenuItem>
                  <MenuItem value={"기타"}>기타</MenuItem>
                </Select>
              </FormControl>
            </div>
          </div>
          <div className="divide" />
        </div>
      </div>
    </div>
  );
}
export default TrsList;
