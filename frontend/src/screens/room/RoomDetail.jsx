import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Carousel from 'react-material-ui-carousel';
import styled from 'styled-components';
import { v4 } from "uuid";
import { getRoomDetail, deleteRoomDetail } from "../../apis/room";
import Item from "../../components/common/Item";
import MapContainer from "../../components/room/MapContainer";
// import ImgItem from "../../components/common/ImgItem";
// import tmpImg from "@images/room.png";
import "./RoomDetail.scss";

function RoomDetail() {
  const { id } = useParams();
  const { sessionStorage } = window;
  const navigate = useNavigate();
  const [userNickname, setUserNickname] = useState("");
  // const Wrapper = styled.div`
  //   display: block;
  //   height: auto;
  // `
  const [writer, setWriter] = useState("");
  const [transferType, setTransferType] = useState("");
  const [address, setAddress] = useState("");
  const [detailAddress, setDetailAddress] = useState("");
  const [meetAndDecide, setMeetAndDecide] = useState("");
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [contractType, setContractType] = useState("");
  const [deposit, setDeposit] = useState(0);
  const [monthlyRent, setMonthlyRent] = useState(0);
  const [maintenanceType, setMaintenanceType] = useState("");
  const [maintenanceFee, setMaintenanceFee] = useState(0);
  const [roomType, setRoomType] = useState("");
  const [roomSize, setRoomSize] = useState(0);
  const [totalFloor, setTotalFloor] = useState(0);
  const [floor, setFloor] = useState(0);
  const [elevator, setElevator] = useState("");
  const [options, setOptions] = useState("");
  const [imgList, setImgList] = useState([]);
  const getDetail = async () => {
    const res = await getRoomDetail(id);
    setImgList(res.data.images);
    setWriter(res.data.writer);
    setAddress(res.data.categoryDetail.transfer.address);
    setDetailAddress(res.data.categoryDetail.transfer.detailAddress);
    setTransferType(res.data.categoryDetail.transfer.transferType);
    setMeetAndDecide(res.data.categoryDetail.transfer.meetAndDecide);
    setStartDate(res.data.categoryDetail.transfer.startDate);
    setEndDate(res.data.categoryDetail.transfer.endDate);
    setContractType(res.data.categoryDetail.transfer.contractType);
    setDeposit(res.data.categoryDetail.transfer.deposit);
    setMonthlyRent(res.data.categoryDetail.transfer.monthlyRent);
    setMaintenanceType(res.data.categoryDetail.transfer.maintenanceType);
    setMaintenanceFee(res.data.categoryDetail.transfer.maintenanceFee);
    setRoomType(res.data.categoryDetail.transfer.roomType);
    setRoomSize(res.data.categoryDetail.transfer.roomSize);
    setTotalFloor(res.data.categoryDetail.transfer.totalFloor);
    setFloor(res.data.categoryDetail.transfer.floor);
    setElevator(res.data.categoryDetail.transfer.elevator);
    setOptions(res.data.categoryDetail.transfer.options);
    setUserNickname(sessionStorage.getItem("userNickname"));
  };
  // console.log(imgList);
  useEffect(() => {
    getDetail();
  }, [userNickname]);
  const deleteRoom = async () => {
    const check = window.confirm("정말로 삭제하시겠습니까?");
    if (check) {
      try {
        const res = await deleteRoomDetail(id);
        if (res.message === '성공') {
          window.alert("정상적으로 삭제되었습니다.");
          navigate("/room/roomlist");
        }
      } catch {
        navigate("/room/roomlist");
      }
    }
  };
  const toList = () => {
    navigate("/room/roomlist");
  }
  // const [cindex, setCindex] = useState(0);
  return (
    <div className="container flex">
      <div className="roomDetail flex">
        <div className="roomDetail_photo flex">
          {imgList !== [] &&
            <Carousel className="roomDetail_photo_car">
              {
                imgList.map((item, i) => <Item key={i} item={item} />)
              }
            </Carousel>
          }
          {/* <img src={tmpImg} alt="방 이미지" /> */}
        </div>
        <div className="roomDetail_contents flex">
          <div className="roomDetail_contents_top flex">
            <div className="roomDetail_contents_top_contract">
              {contractType === "월세" && (
                <div className="roomDetail_contents_top_contract_txt shBold fs-36">
                  월세 {monthlyRent}만원
                  <br />
                  보증금 {deposit}만원
                </div>
              )}
              {contractType === "전세" && (
                <div className="roomDetail_contents_top_contract_txt shBold fs-36">
                  전세 {deposit}만원
                  <br />
                  *보증금 100% 반환!*
                </div>
              )}
            </div>
            <div className="roomDetail_contents_top_loc flex shBold fs-24">
              <div className="roomDetail_contents_top_loc_ad">{address}</div>
              <div className="roomDetail_contents_top_loc_room">
                {roomType} {roomSize}평
              </div>
            </div>
            {/* <div className="roomDetail_contents_top_area shBold fs-26">
            </div> */}
          </div>
          <div className="roomDetail_contents_upper flex">
            <div className="roomDetail_contents_upper_tt shBold fs-28">
              {transferType}
            </div>
            {userNickname !== writer &&
              (<div className="roomDetail_contents_upper_btns flex">
                <button type="button" className="roomDetail_contents_upper_btns_writer shBold fs-28">
                  {writer}
                </button>
                <button type="button" className="roomDetail_contents_upper_btns_toList shBold fs-28" onClick={toList}>
                  목록으로
                </button>
              </div>)
            }
            {userNickname === writer &&
              (<div className="roomDetail_contents_upper_btns flex">
                <button type="button" className="roomDetail_contents_upper_btns_writer shBold fs-28" onClick={deleteRoom}>
                  글 삭제하기
                </button>
                <button type="button" className="roomDetail_contents_upper_btns_toList shBold fs-28" onClick={toList}>
                  목록으로
                </button>
              </div>)
            }
          </div>
          <div className="roomDetail_contents_body flex">
            <div className="roomDetail_contents_body_detail flex">
              <div className="roomDetail_contents_body_detail_info flex shBold fs-22">
                <div className="roomDetail_contents_body_detail_info_top flex shBold fs-24">
                  {'<'} Options {'>'}
                </div>
                {maintenanceType === true && (
                  <div className="roomDetail_contents_body_detail_info_maint">
                    관리비 {maintenanceFee}만원
                  </div>
                )}
                {maintenanceType === false && (
                  <div className="roomDetail_contents_body_detail_info_maint">
                    관리비 없음
                  </div>
                )}
                {meetAndDecide === true && (
                  <div className="roomDetail_contents_body_detail_info_mad">
                    계약기간
                    <br />
                    협의가능!
                  </div>
                )}
                {meetAndDecide === false && (
                  <div className="roomDetail_contents_body_detail_info_mad">
                    계약기간 {startDate} <br /> ~ {endDate}
                  </div>
                )}
                건물 층수 {totalFloor}층
                <br />방 층수 {floor}층
                <br />
                {elevator === true && (
                  <div className="roomDetail_contents_body_detail_info_el">
                    엘리베이터 있음!
                  </div>
                )}
              </div>
              {options !== "" && (
                <div className="roomDetail_contents_body_detail_plus flex shBold fs-22">
                  <div className="roomDetail_contents_body_detail_plus_top flex shBold fs-24">
                    {'<'} Plus {'>'}
                  </div>
                  {options}
                </div>
              )}
              {options === "" && (
                <div className="roomDetail_contents_body_detail_plus flex shBold fs-28">
                  지금 핫한 매물!
                </div>
              )}
            </div>
            <div className="roomDetail_contents_body_map flex">
              {address !== "" &&
                <MapContainer loc={{ address }}/>
              }
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default RoomDetail;
