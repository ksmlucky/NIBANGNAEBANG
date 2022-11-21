import React, { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import DatePicker from "react-datepicker";
import ImageUploader from 'react-images-upload';
import RoomSearch from "@components/room/InRoomSearch";
import Checkbox from "@mui/material/Checkbox";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";
import Box from "@mui/material/Box";
import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";
import NativeSelect from "@mui/material/NativeSelect";
import AddAPhotoIcon from "@mui/icons-material/AddAPhoto";
import thinkIcon from "@images/extra/think.png";
import contractIcon from "@images/extra/contract.png";
import keyboardIcon from "@images/extra/keyboard.png";
import { registerRoom } from "../../apis/room";
import moment from "moment";
import "react-datepicker/dist/react-datepicker.css";
import "./RoomRegister.scss";

export function changeFormat(date, format) {
  //moment 변환을 함수로 미리 빼 두어서 사용.
  return moment(date).format(format);
}

function Questions(props) {
  const { sessionStorage } = window;
  const [pictures, setPictures] = useState([]);
  const onDrop = picture => {
    setPictures([...pictures, picture]);
  };
  // console.log(pictures[pictures.length-1]);
  const [popup, setPopup] = useState(false);
  const [loc, setLoc] = useState("");
  const [count, setCount] = useState(0);
  const defaultLoc = sessionStorage.getItem("location");
  // console.log(defaultLoc);
  if (loc === "" && count < 3) {
    setLoc(defaultLoc);
    setCount(count + 1);
  }
  const { id, getId } = props;
  const ques = [
    {
      subtitle: "간편하게 양도 신청하세요",
      title: "내방 상세정보 입력하기",
      img: keyboardIcon,
    },
    {
      subtitle: "거의 다 왔어요!",
      title: "내방 상세정보 입력하기",
      img: contractIcon,
    },
    {
      subtitle: "거래가 쉬워질 거에요",
      title: "내방 추가정보 입력하기",
      img: thinkIcon,
    },
  ];
  const roughRef = useRef();
  const detailRef = useRef();
  const areaRef = useRef();
  const buildingRef = useRef();
  const floorRef = useRef();
  const extraRef = useRef();
  const photoRef = useRef();
  const [image, setImage] = useState({
    image_file: "",
    preview_URL: "",
  });
  const [imageUrl, setImageUrl] = useState("");
  const handleUpload = (e) => {
    e.preventDefault();
    if (e.target.files[0]) {
      URL.revokeObjectURL(image.preview_URL);
      const preview_URL = URL.createObjectURL(e.target.files[0]);
      // console.log(preview_URL);
      setImage(() => ({
        image_file: e.target.files[0],
        preview_URL: preview_URL,
      }));
      // console.log(image);
      const storageRef = storage.ref("detail/test/");
      const imageRef = storageRef.child(e.target.files[0].name);
      const upLoadTask = imageRef.put(e.target.files[0]);
      upLoadTask.on(
        "state_changed",
        (snapshot) => {
          // console.log("snapshot", snapshot);
          const percent =
            (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
          // console.log(percent + "% done");
        },
        (error) => {
          // console.log("err", error);
        },
        () => {
          upLoadTask.snapshot.ref.getDownloadURL().then((downloadURL) => {
            // console.log("File available at", downloadURL);
            setImageUrl(downloadURL);
            // console.log(downloadURL);
          });
        }
      );
    }
  };
  const uploadImg = (e) => {
    e.preventDefault();
    photoRef.current.click();
  };
  const [type, setType] = useState("완전양도");
  const [full, setFull] = useState(true);
  const [part, setPart] = useState(false);
  const [checked, setChecked] = useState(false);
  const [eleChecked, setEleChecked] = useState(false);
  const handleChange = (event) => {
    setChecked(event.target.checked);
  };
  const handleEle = (event) => {
    setEleChecked(event.target.checked);
  };
  const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate] = useState(new Date());
  const [conType, setConType] = useState("월세");
  const [conJ, setConJ] = useState(false);
  const [conW, setConW] = useState(true);
  const feeRef = useRef();
  const [feeCheck, setFeeCheck] = useState(false);
  const handleFee = (event) => {
    setFeeCheck(event.target.checked);
  };
  const confeeRef = useRef();
  const monfeeRef = useRef();
  const [fee, setFee] = useState(0);
  const roomtypeRef = useRef();
  const onClickFull = () => {
    setFull(!full);
    setPart(false);
    if (type === "완전양도") {
      setType("");
    } else {
      setType("완전양도");
    }
  };
  const onClickPart = () => {
    setPart(!part);
    setFull(false);
    if (type === "기간양도") {
      setType("");
    } else {
      setType("기간양도");
    }
  };
  const onClickJ = () => {
    setConJ(!conJ);
    setConW(false);
    if (conType === "전세") {
      setConType("");
    } else {
      setConType("전세");
    }
  };
  const onClickW = () => {
    setConW(!conW);
    setConJ(false);
    if (conType === "월세") {
      setConType("");
    } else {
      setConType("월세");
    }
  };
  // const total = () => {
  // };
  // console.log(roughRef.current.value);
  const navigate = useNavigate();
  const [address, setAddress] = useState("");
  const [detailAddress, setDetailAddress] = useState("");
  const [maintenanceFee, setMaintenanceFee] = useState(0);
  const [deposit, setDeposit] = useState(0);
  const [monthlyRent, setMonthlyRent] = useState(0);
  const [roomType, setRoomType] = useState("원룸");
  const [roomSize, setRoomSize] = useState(0);
  const [totalFloor, setTotalFloor] = useState(0);
  const [floor, setFloor] = useState(0);
  // const [options, setOptions] = useState("");
  const onClickForward = async () => {
    if (id === 2) {
      if (pictures === []) {
        window.alert("이미지를 등록해주세요")
      }
      else {
        const formData = new FormData();
        const commonRequest = {
          articleRequest: {
            category: "transfer",
          },
          transferRequest: {
            address: address,
            detailAddress: detailAddress,
            transferType: type,
            meetAndDecide: checked,
            startDate: startDate,
            endDate: endDate,
            contractType: conType,
            roomType: roomType,
            deposit: deposit,
            monthlyRent: monthlyRent,
            maintenanceType: feeCheck,
            maintenanceFee: maintenanceFee,
            roomSize: roomSize,
            elevator: eleChecked,
            parking: false,
            totalFloor: totalFloor,
            floor: floor,
            options: extraRef.current.value,
          },
        };
        const files = pictures[pictures.length-1]
        // console.log(commonRequest);
        sessionStorage.setItem("location", "");
        formData.append(
          "request",
          new Blob([JSON.stringify(commonRequest)], {
            type: "application/json",
          })
        );
        // {files.length !== 0 && files.map((file) => (
        //   formData.append("file", file)
        // ))
        // }
        if (files.length !== 0) {
          for (let i = 0; i < files.length; i++) {
            formData.append("file", files[i])
          }
        }
        try {
          const res = await registerRoom(formData);
          // console.log(res);
          navigate(`/room/detail/${res.data}`);
          // navigate("/room/detail");
          // console.log(address);
          // console.log(detailAddress);
          // console.log(type);
          // console.log(checked);
          // console.log(startDate);
          // console.log(endDate);
          // console.log(conType);
          // console.log(deposit);
          // console.log(monthlyRent);
          // console.log(feeCheck);
          // console.log(maintenanceFee);
          // console.log(roomType);
          // console.log(roomSize);
          // console.log(eleChecked);
          // console.log(totalFloor);
          // console.log(floor);
          // console.log(extraRef.current.value);
        } catch (err) {
          console.log(err);
        }
      }
    } else if (id === 1) {
      if (conType === "전세" && confeeRef.current.value !== "" && areaRef.current.value !== "" && buildingRef !== "" && floorRef !== "" && feeCheck === true && feeRef.current.value !== "") {
        // console.log(conType);
        setDeposit(confeeRef.current.value);
        setMonthlyRent(0);
        // console.log(feeCheck);
        setMaintenanceFee(feeRef.current.value);
        setRoomType(roomtypeRef.current.value);
        setRoomSize(areaRef.current.value);
        // console.log(eleChecked);
        setTotalFloor(buildingRef.current.value);
        setFloor(floorRef.current.value);
        getId(id + 1);
      } else if (conType === "월세" && confeeRef.current.value !== "" && areaRef.current.value !== "" && buildingRef !== "" && floorRef !== "" && monfeeRef.current.value !== "" && feeCheck === true && feeRef.current.value !== "") {
        setDeposit(confeeRef.current.value);
        setMonthlyRent(monfeeRef.current.value);
        setMaintenanceFee(feeRef.current.value);
        setRoomType(roomtypeRef.current.value);
        setRoomSize(areaRef.current.value);
        // console.log(eleChecked);
        setTotalFloor(buildingRef.current.value);
        setFloor(floorRef.current.value);
        getId(id + 1);
      } else if (conType === "전세" && confeeRef.current.value !== "" && areaRef.current.value !== "" && buildingRef !== "" && floorRef !== "" && feeCheck === false) {
        setDeposit(confeeRef.current.value);
        setMonthlyRent(0);
        // console.log(feeCheck);
        setMaintenanceFee(0);
        setRoomType(roomtypeRef.current.value);
        setRoomSize(areaRef.current.value);
        // console.log(eleChecked);
        setTotalFloor(buildingRef.current.value);
        setFloor(floorRef.current.value);
        getId(id + 1);
      } else if (conType === "월세" && confeeRef.current.value !== "" && areaRef.current.value !== "" && buildingRef !== "" && floorRef !== "" && monfeeRef.current.value !== "" && feeCheck === false) {
        setDeposit(confeeRef.current.value);
        setMonthlyRent(monfeeRef.current.value);
        setMaintenanceFee(0);
        setRoomType(roomtypeRef.current.value);
        setRoomSize(areaRef.current.value);
        // console.log(eleChecked);
        setTotalFloor(buildingRef.current.value);
        setFloor(floorRef.current.value);
        getId(id + 1);
      } else {
        window.alert("내용을 입력해주세요.");
        // console.log(monfeeRef.current.value);
      }
    } else if (id === 0) {
      if (roughRef.current.value !== "" && detailRef.current.value !== "" && type !== "") {
        setAddress(roughRef.current.value);
        setDetailAddress(detailRef.current.value);
        // console.log(type);
        // console.log(checked);
        const sDate = changeFormat(startDate, "yyyy-MM-DD");
        const eDate = changeFormat(endDate, "yyyy-MM-DD");
        setStartDate(sDate);
        setEndDate(eDate);
        getId(id + 1);
      } else {
        window.alert("내용을 입력해주세요");
      }
    }
  };
  const onClickBackward = () => {
    getId(id - 1);
  };
  return (
    <div className="roomRegister flex">
      <div className="roomRegister_subtitle shBold fs-40">
        {ques[id].subtitle}
      </div>
      <div className="roomRegister_title flex shBold fs-56">
        {ques[id].title}
        <img src={ques[id].img} alt="" />
      </div>
      <div className="roomRegister_box flex justify-center">
        {id === 0 && (
          <div className="roomRegister_box_p1">
            <div className="roomRegister_box_p1_address">
              <div className="roomRegister_box_p1_address_title flex shBold fs-26">
                주소&nbsp;&nbsp;&nbsp;&nbsp;
                <button
                  type="button"
                  className="roomRegister_box_p1_address_title_btn shBold fs-22"
                  onClick={() => {
                    setPopup(!popup);
                    sessionStorage.setItem("location", "");
                  }}
                >
                  검색하기
                </button>
                {popup && (
                  <RoomSearch
                    address={loc}
                    setAddress={setLoc}
                    // location={roughRef.current.value}
                  />
                )}
              </div>
              <div className="div roomRegister_box_p1_address_input flex">
                <input
                  type="text"
                  className="roomRegister_box_p1_address_input_rough shBold fs-24"
                  ref={roughRef}
                  placeholder="주소를 작성해줘"
                  defaultValue={loc}
                />
                <input
                  type="text"
                  className="roomRegister_box_p1_address_input_detail shBold fs-24"
                  ref={detailRef}
                  placeholder="상세주소!"
                />
              </div>
            </div>
            <div className="roomRegister_box_p1_type">
              <div className="roomRegister_box_p1_type_title shBold fs-26">
                양도형태
              </div>
              <div className="roomRegister_box_p1_type_btns flex">
                {full && (
                  <button
                    type="button"
                    className="roomRegister_box_p1_type_btns_btn_strong shBold fs-24"
                    onClick={onClickFull}
                  >
                    완전양도
                  </button>
                )}
                {!full && (
                  <button
                    type="button"
                    className="roomRegister_box_p1_type_btns_btn shBold fs-24"
                    onClick={onClickFull}
                  >
                    완전양도
                  </button>
                )}
                {part && (
                  <button
                    type="button"
                    className="roomRegister_box_p1_type_btns_btn_strong shBold fs-24"
                    onClick={onClickPart}
                  >
                    기간양도
                  </button>
                )}
                {!part && (
                  <button
                    type="button"
                    className="roomRegister_box_p1_type_btns_btn shBold fs-24"
                    onClick={onClickPart}
                  >
                    기간양도
                  </button>
                )}
              </div>
            </div>
            <div className="roomRegister_box_p1_term">
              <div className="roomRegister_box_p1_term_title flex shBold fs-26">
                계약기간
                <div className="roomRegister_box_p1_term_title_condition shBold fs-22">
                  협의로 결정
                  <Checkbox
                    checked={checked}
                    onChange={handleChange}
                    inputProps={{ "aria-label": "controlled" }}
                  />
                </div>
              </div>
              {!checked && (
                <div className="roomRegister_box_p1_term_calender flex shBold fs-24">
                  <DatePicker
                    selected={startDate}
                    onChange={(date) => setStartDate(date)}
                    selectsStart
                    startDate={startDate}
                    endDate={endDate}
                    className="roomRegister_box_p1_term_calender_cal shBold fs-24"
                  />
                  <DatePicker
                    selected={endDate}
                    onChange={(date) => setEndDate(date)}
                    selectsEnd
                    startDate={startDate}
                    endDate={endDate}
                    minDate={startDate}
                    className="roomRegister_box_p1_term_calender_cal shBold fs-24"
                  />
                </div>
              )}
              {checked && (
                <div className="roomRegister_box_p1_calender">
                  <DatePicker disabled />
                  <DatePicker disabled />
                </div>
              )}
            </div>
          </div>
        )}
        {id === 1 && (
          <div className="roomRegister_box_p2">
            <div className="roomRegister_box_p2_cont">
              <div className="roomRegister_box_p2_cont_title shBold fs-26">
                계약 종류
              </div>
              <div className="roomRegister_box_p2_cont_btns flex shBold fs-22">
                {conJ && (
                  <button
                    type="button"
                    className="roomRegister_box_p2_cont_btns_btn_strong shBold fs-24"
                    onClick={onClickJ}
                  >
                    전세
                  </button>
                )}
                {!conJ && (
                  <button
                    type="button"
                    className="roomRegister_box_p2_cont_btns_btn shBold fs-24"
                    onClick={onClickJ}
                  >
                    전세
                  </button>
                )}
                {conW && (
                  <button
                    type="button"
                    className="roomRegister_box_p2_cont_btns_btn_strong shBold fs-24"
                    onClick={onClickW}
                  >
                    월세
                  </button>
                )}
                {!conW && (
                  <button
                    type="button"
                    className="roomRegister_box_p2_cont_btns_btn shBold fs-24"
                    onClick={onClickW}
                  >
                    월세
                  </button>
                )}
                <Checkbox
                  checked={feeCheck}
                  onChange={handleFee}
                  inputProps={{ "aria-label": "controlled" }}
                />
                관리비&nbsp;&nbsp;&nbsp;
                {feeCheck && (
                  <div className="roomRegister_box_p2_cont_btns_fee shBold fs-22">
                    <input
                      type="text"
                      className="roomRegister_box_p2_cont_btns_fee_input shBold fs-22"
                      ref={feeRef}
                    />
                    만원
                  </div>
                )}
              </div>
              <div className="roomRegister_box_p2_cont_inp flex shBold fs-22">
                보증금&nbsp;&nbsp;
                <input
                  type="text"
                  className="roomRegister_box_p2_cont_inp_input shBold fs-22"
                  ref={confeeRef}
                />
                만원&nbsp;&nbsp;&nbsp;
                {conW && (
                  <div className="roomRegister_box_p2_cont_inp_conw">
                    월세&nbsp;&nbsp;
                    <input
                      type="text"
                      className="roomRegister_box_p2_cont_inp_conw_input shBold fs-22"
                      ref={monfeeRef}
                    />
                    만원
                  </div>
                )}
              </div>
            </div>
            <div className="roomRegister_box_p2_room flex">
              <div className="roomRegister_box_p2_room_opt">
                <div className="roomRegister_box_p2_room_opt_title shBold fs-26">
                  방 형태
                </div>
                <Box sx={{ minWidth: 120, maxWidth: 180 }}>
                  <FormControl fullWidth>
                    <InputLabel
                      variant="standard"
                      htmlFor="uncontrolled-native"
                    >
                      room
                    </InputLabel>
                    <NativeSelect
                      inputRef={roomtypeRef}
                      defaultValue={"원룸"}
                      inputProps={{
                        name: "room",
                        id: "uncontrolled-native",
                      }}
                      sx={{ fontFamily: "SeoulHangangB", fontSize: 22 }}
                    >
                      <option value={"원룸"} className="shBold fs-22">
                        원룸
                      </option>
                      <option value={"투룸"} className="shBold fs-22">
                        투룸
                      </option>
                      <option value={"기타"} className="shBold fs-22">
                        기타
                      </option>
                    </NativeSelect>
                  </FormControl>
                </Box>
              </div>
              <div className="roomRegister_box_p2_room_area shBold fs-22">
                <div className="roomRegister_box_p2_room_area_title shBold fs-26">
                  방 크기
                </div>
                <input
                  type="text"
                  className="roomRegister_box_p2_room_area_input shBold fs-22"
                  ref={areaRef}
                />
                평
              </div>
            </div>
            <div className="roomRegister_box_p2_floor">
              <div className="roomRegister_box_p2_floor_title shBold fs-26">
                방 층수
              </div>
              <div className="roomRegister_box_p2_floor_ele shBold fs-22">
                엘리베이터 있음
                <Checkbox
                  checked={eleChecked}
                  onChange={handleEle}
                  inputProps={{ "aria-label": "controlled" }}
                />
              </div>
              <div className="roomRegister_box_p2_floor_inps flex shBold fs-22">
                건물층수&nbsp;
                <input type="text" ref={buildingRef} className="shBold fs-22" />
                층 &nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp; 방 층수&nbsp;
                <input type="text" ref={floorRef} className="shBold fs-22" />층
              </div>
            </div>
          </div>
        )}
        {id === 2 && (
          <div className="roomRegister_box_p3">
            <div className="roomRegister_box_p3_extra">
              <div className="roomRegister_box_p3_extra_title shBold fs-26">
                추가정보를 입력해주세요
              </div>
              <textarea
                type="textarea"
                placeholder="방에 대한 추가정보를 입력하면 거래가 더 쉽게 이루어질 수 있어요"
                className="roomRegister_box_p3_extra_box shBold fs-22"
                ref={extraRef}
              />
            </div>
            <div className="roomRegister_box_p3_photo">
              <div className="roomRegister_box_p3_photo_title shBold fs-26">
                방 사진을 등록해주세요
              </div>
              {/* <div className="roomRegister_box_p3_photo_desc flex shBold fs-22">
                가로방향 사진을 등록해주세요
              </div> */}
              <div className="roomRegister_box_p3_photo_con flex">
                {/* <input
                  type="file"
                  accept=".jpg, .jpeg, .png, .JPG, .JPEG, .PNG"
                  className="roomRegister_box_p3_photo_con_imginput"
                  ref={photoRef}
                  onChange={handleUpload}
                />
                <button
                  type="button"
                  className="roomRegister_box_p3_photo_con_img flex"
                  onClick={uploadImg}
                >
                  <AddAPhotoIcon sx={{ fontSize: 80 }} />
                </button> */}
                <div className="roomRegister_box_p3_photo_con_upload">
                  <ImageUploader
                    withIcon={false}
                    onChange={onDrop}
                    imgExtension={[".jpg", ".gif", ".png", ".gif"]}
                    maxFileSize={5242880}
                    // accept={image}
                    buttonText="이미지를 선택해주세요"
                    withPreview
                  />
                </div>
                <div className="roomRegister_box_p3_photo_con_desc flex shBold fs-20">
                  가로방향<br/>
                  이미지로<br/>
                  등록해<br />
                  주세요<br/>
                </div>
              </div>
            </div>
          </div>
        )}
        {id === 0 && (
          <div className="roomRegister_box_btn flex justify-center">
            <button
              type="button"
              className="roomRegister_box_btn_forward"
              onClick={onClickForward}
            >
              <ArrowForwardIosIcon sx={{ fontSize: 52 }} />
            </button>
          </div>
        )}
        {id !== 0 && (
          <div className="roomRegister_box_btn flex justify-space-between">
            <button
              type="button"
              className="roomRegister_box_btn_forward"
              onClick={onClickBackward}
            >
              <ArrowBackIosNewIcon sx={{ fontSize: 52 }} />
            </button>
            <button
              type="button"
              className="roomRegister_box_btn_forward"
              onClick={onClickForward}
            >
              <ArrowForwardIosIcon sx={{ fontSize: 52 }} />
            </button>
          </div>
        )}
      </div>
    </div>
  );
}
export default Questions;
