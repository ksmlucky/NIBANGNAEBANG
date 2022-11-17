import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getRoomDetail } from "../../apis/room";
import tmpImg from "@images/room.png";
import "./RoomDetail.scss";

function RoomDetail() {
  const { id } = useParams();
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
  const getDetail = async () => {
    const res = await getRoomDetail(id);
    console.log(res);
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
  };
  useEffect(() => {
    getDetail();
  }, []);
  return (
    <div className="container flex">
      <div className="roomDetail flex">
        <div className="roomDetail_photo flex">
          <img src={tmpImg} alt="방 이미지" />
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
            <div className="roomDetail_contents_upper_writer shBold fs-28">
              {writer}
            </div>
          </div>
          <div className="roomDetail_contents_body flex">
            <div className="roomDetail_contents_body_detail flex">
              <div className="roomDetail_contents_body_detail_info flex shBold fs-22">
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
                    계약기간 {startDate} ~ {endDate}
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
                  {options}
                </div>
              )}
              {options === "" && (
                <div className="roomDetail_contents_body_detail_plus flex shBold fs-28">
                  지금 핫한 매물!
                </div>
              )}
            </div>
            <div className="roomDetail_contents_body_map flex">지도 띄우기</div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default RoomDetail;
