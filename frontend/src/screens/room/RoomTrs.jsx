import React, { useState } from "react";
import Radio from "@material-ui/core/Radio";
import RadioGroup from "@material-ui/core/RadioGroup";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import FormControl from "@material-ui/core/FormControl";
import FormLabel from "@material-ui/core/FormLabel";
import Button from "@mui/material/Button";
import AddAPhotoIcon from "@mui/icons-material/AddAPhoto";
import "./RoomTrs.scss";

function RoomTrs() {
  const [room, setRoom] = useState("");
  console.log(room);
  const [building, setBuilding] = useState("");
  const [contract, setContract] = useState("");
  const [term, setTerm] = useState("");
  const [address, setAddress] = useState("");
  const onChangeRoom = (e) => {
    setRoom(e.target.value);
  };
  const onChangeBuilding = (e) => {
    setBuilding(e.target.value);
  };
  const onChangeContract = (e) => {
    setContract(e.target.value);
  };
  return (
    <div className="container flex">
      <div className="roomTrs flex">
        <div className="roomTrs_title notoBold fs-40">방 양도하기</div>
        <div className="roomTrs_option flex">
          <div className="roomTrs_option_title flex justify-center notoMid fs-18">
            매물 조건
          </div>
          <div className="roomTrs_option_room flex">
            <div className="roomTrs_option_room_title flex justify-center notoMid fs-16">
              방 종류
            </div>
            <div className="roomTrs_option_room_boxes flex"></div>
          </div>
          <div className="roomTrs_option_building flex">
            <div className="roomTrs_option_building_title flex justify-center notoMid fs-16">
              건물 종류
            </div>
            <div className="roomTrs_option_building_boxes flex">2</div>
          </div>
          <div className="roomTrs_option_contract flex">
            <div className="roomTrs_option_contract_title flex justify-center notoMid fs-16">
              계약 종류
            </div>
            <div className="roomTrs_option_contract_boxes flex">3</div>
          </div>
          <div className="roomTrs_option_long flex">
            <div className="roomTrs_option_long_title flex justify-center notoMid fs-16">
              계약 기간
            </div>
            <div className="roomTrs_option_long_boxes flex">4</div>
          </div>
        </div>
        <div className="roomTrs_loc flex">
          <div className="roomTrs_loc_title flex justify-center notoMid fs-18">
            위치 정보
          </div>
          <div className="roomTrs_loc_address flex">
            <div className="roomTrs_loc_address_title flex justify-center notoMid fs-16">
              주소
            </div>
            <div className="roomTrs_loc_address_boxes flex">
              <div className="roomTrs_loc_address_boxes_info notoReg fs-14">
                <div className="roomTrs_loc_address_boxes_info_title">
                  도로명, 건물명, 지번에 대해 검색할 수 있습니다.
                </div>
                <div className="roomTrs_loc_address_boxes_info_search notoReg fs-14">
                  <input
                    type="text"
                    className="roomTrs_loc_address_boxes_info_search_input"
                    placeholder="예)서구 치평동, 상무공원로10"
                  />
                  <Button
                    variant="contained"
                    sx={{
                      backgroundColor: "#505050",
                      // fontSize: 20,
                      fontFamily: "NotoSansRegular",
                    }}
                  >
                    주소검색
                  </Button>
                </div>
                <div className="roomTrs_loc_address_boxes_info_result notoReg fs-14">
                  {address}
                </div>
                <div className="roomTrs_loc_address_boxes_info_detail flex">
                  <div className="roomTrs_loc_address_boxes_info_detail_dong flex">
                    <input
                      type="text"
                      className="roomTrs_loc_address_boxes_info_detail_dong_input"
                      placeholder="예)102동"
                    />
                    <div className="roomTrs_loc_address_boxes_info_detail_dong_txt flex justify-center notoReg fs-14">
                      동
                    </div>
                  </div>
                  <div className="roomTrs_loc_address_boxes_info_detail_ho flex">
                    <input
                      type="text"
                      className="roomTrs_loc_address_boxes_info_detail_ho_input"
                      placeholder="예)203호"
                    />
                    <div className="roomTrs_loc_address_boxes_info_detail_ho_txt flex justify-center notoReg fs-14">
                      호
                    </div>
                  </div>
                </div>
              </div>
              <div className="roomTrs_loc_address_boxes_map"></div>
            </div>
          </div>
        </div>
        <div className="roomTrs_info flex">
          <div className="roomTrs_info_title flex justify-center notoMid fs-18">
            추가 정보
          </div>
          <div className="roomTrs_info_con flex">
            <div className="roomTrs_info_con_left flex">
              <div className="roomTrs_info_con_left_top flex">
                <div className="roomTrs_info_con_left_top_title flex">
                  <div className="roomTrs_info_con_left_top_title_big notoMid fs-16">
                    방 크기
                  </div>
                  <div className="roomTrs_info_con_left_top_title_small notoReg fs-14">
                    (1평=3.3058㎡)
                  </div>
                </div>
                <div className="roomTrs_info_con_left_top_boxes flex">
                  <div className="roomTrs_info_con_left_top_boxes_up flex">
                    1
                  </div>
                  <div className="roomTrs_info_con_left_top_boxes_down flex">
                    2
                  </div>
                </div>
              </div>
              <div className="roomTrs_info_con_left_mid flex">
                <div className="roomTrs_info_con_left_mid_title flex notoMid fs-16">
                  난방 종류
                </div>
                <div className="roomTrs_info_con_left_mid_boxes flex">3</div>
              </div>
              <div className="roomTrs_info_con_left_bot flex">
                <div className="roomTrs_info_con_left_bot_title flex notoMid fs-16">
                  옵션
                </div>
                <div className="roomTrs_info_con_left_bot_boxes flex">4</div>
              </div>
            </div>
            <div className="roomTrs_info_con_right flex">
              <div className="roomTrs_info_con_right_top flex">
                <div className="roomTrs_info_con_right_top_title flex notoMid fs-16">
                  건물 층수
                </div>
                <div className="roomTrs_info_con_right_top_boxes flex">
                  <div className="roomTrs_info_con_right_top_boxes_up flex">
                    5
                  </div>
                  <div className="roomTrs_info_con_right_top_boxes_down flex">
                    6
                  </div>
                </div>
              </div>
              <div className="roomTrs_info_con_right_mid flex">
                <div className="roomTrs_info_con_right_mid_title flex notoMid fs-16">
                  엘레베이터
                </div>
                <div className="roomTrs_info_con_right_mid_boxes flex">7</div>
              </div>
              <div className="roomTrs_info_con_right_bot flex">
                <div className="roomTrs_info_con_right_bot_title flex notoMid fs-16">
                  주차
                </div>
                <div className="roomTrs_info_con_right_bot_boxes flex">8</div>
              </div>
            </div>
          </div>
        </div>
        <div className="roomTrs_photo">
          <div className="roomTrs_photo_title flex justify-center notoMid fs-18">
            사진 등록
          </div>
          <div className="roomTrs_photo_con flex justify-center">
            <div className="roomTrs_photo_con_box flex justify-center">
              <AddAPhotoIcon color="disabled" sx={{ fontSize: 120 }} />
              <div className="roomTrs_photo_con_box_title notoReg fs-18">
                사진 최소 3장 이상, 최대 10장까지 등록하시길 권장합니다.
              </div>
              <Button
                variant="contained"
                sx={{
                  backgroundColor: "#505050",
                  // fontSize: 20,
                  fontFamily: "NotoSansRegular",
                }}
              >
                사진 추가하기
              </Button>
            </div>
          </div>
        </div>
        <div className="roomTrs_btns flex">
          <Button
            variant="outlined"
            sx={{
              color: "#505050",
              // fontSize: 26,
              fontFamily: "NotoSansRegular",
            }}
          >
            작성취소
          </Button>
          <Button
            variant="contained"
            sx={{
              backgroundColor: "#505050",
              // fontSize: 26,
              fontFamily: "NotoSansRegular",
            }}
          >
            등록하기
          </Button>
        </div>
      </div>
    </div>
  );
}
export default RoomTrs;
