import React, { useEffect } from "react";
import Box from "@mui/material/Box";
import IconButton from "@mui/material/IconButton";
import SearchIcon from "@mui/icons-material/Search";
import TextField from "@mui/material/TextField";
import "./TrsList.scss";
import KakaoMap from "../../components/KakaoMap";

function TrsList() {
  return (
    <div className="container">
      <div className="trsList flex">
        <div className="trsList_map flex">
          <div className="trsList_map_title flex justify-center">
            <KakaoMap></KakaoMap>
          </div>
        </div>
        <div className="trsList_detail flex justify-center">
          <div className="trsList_detail_search">
            <IconButton type="submit" sx={{ p: "10px" }} aria-label="search">
              <SearchIcon />
            </IconButton>
            <Box
              component="form"
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
          </div>
        </div>
      </div>
    </div>
  );
}
export default TrsList;
