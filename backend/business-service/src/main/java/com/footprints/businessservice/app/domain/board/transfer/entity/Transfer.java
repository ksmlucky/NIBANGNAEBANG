package com.footprints.businessservice.app.domain.board.transfer.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.footprints.businessservice.app.domain.board.article.entity.Article;
import com.footprints.businessservice.app.domain.board.transfer.dto.TransferDto;
import com.footprints.businessservice.app.domain.board.transfer.dto.TransferRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;

/**
 * @author Geun 
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private Long id;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    /**
     * 양도 신청한 사람
     */
    private String tenant;

    /**
     * 주소 (지번, 도로명 주소)
     */
    private String address;

    /**
     * 상세 주소 (건물명, 동, 호수)
     */
    private String detailAddress;

    /**
     * 양도 형태
     * - 완전 양도 : 완전히 양도할 집인지?
     * - 기간 양도 : 기간 내에 단기간만 양도할 집인지?
     */
    @Enumerated(EnumType.STRING)
    private TransferType transferType;

    /**
     * 협의로 결정 (계약 기간을 만나서 정하는 경우)
     */
    private Boolean meetAndDecide;

    /**
     * 계약 시작일 (yyyy-MM-dd)
     */
    private LocalDate startDate;

    /**
     * 계약 종료일 (yyyy-MM-dd)
     */
    private LocalDate endDate;

    /**
     * 계약 종류 (전세, 월세)
     */
    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    /**
     * 방 종류 (원룸, 투룸, 기타)
     */
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    /**
     * 보증금 (단위 : 원)
     */
    private Long deposit;

    /**
     * 월세 (단위 : 원)
     * - 계약 종류가 월세인 경우
     */
    private Integer monthlyRent;

    /**
     * 관리비가 있는가?
     */
    private Boolean maintenanceType;

    /**
     * 관리비 (단위 : 원)
     */
    private Integer maintenanceFee;

    /**
     * 방 크기 (단위 : 평)
     */
    private Integer roomSize;

    /**
     * 엘리베이터 유무
     */
    private Boolean elevator;

    /**
     * 주차 유무
     */
    private Boolean parking;

    /**
     * 건물 층수
     */
    private Integer totalFloor;

    /**
     * 방의 층수
     */
    private Integer floor;

    /**
     * 옵션 (방에 대한 추가 정보 입력란)
     */
    private String options;

    /**
     * 양도 진행 상태(READY, ONGOING, COMPLETE)
     */
    @Enumerated(EnumType.STRING)
    private TransferStatus transferStatus;

    /**
     * Transfer Entity 를 Dto로 변환하기 위한 메서드
     * @return TransferDto
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public TransferDto toDto() {
        return TransferDto.builder()
                .transferId(id)
                .tenant(tenant)
                .address(address)
                .detailAddress(detailAddress)
                .transferType(transferType)
                .meetAndDecide(meetAndDecide)
                .startDate(startDate)
                .endDate(endDate)
                .contractType(contractType)
                .roomType(roomType)
                .deposit(deposit)
                .monthlyRent(monthlyRent)
                .maintenanceType(maintenanceType)
                .maintenanceFee(maintenanceFee)
                .roomSize(roomSize)
                .elevator(elevator)
                .parking(parking)
                .totalFloor(totalFloor)
                .floor(floor)
                .options(options)
                .transferStatus(transferStatus)
                .build();
    }

    /**
     * 양도 신청자의 닉네임을 저장하는 연관관계 메서드
     * @param nickname 양도 신청자의 닉네임 (현재 로그인한 회원의 닉네임)
     */
    public void updateTenant(String nickname) {
        tenant = nickname;
    }

    /**
     * 양도 게시글의 양도 상태를 저장하는 연관관계 메서드
     * @param status 양도 상태(READY, ONGOING, COMPLETE)
     */
    public void updateStatus(TransferStatus status) {
        transferStatus = status;
    }

    /**
     * 양도 게시글 수정 양식에 따라 양도 게시글 수정하는 연관관계 메서드
     * @param request 수정 요청시 작성하는 양식 데이터
     */
    public void updateTransfer(TransferRequest request) {
        tenant = request.getTenant();
        address = request.getAddress();
        detailAddress = request.getDetailAddress();
        transferType = request.getTransferType();
        meetAndDecide = request.getMeetAndDecide();
        startDate = request.getStartDate();
        endDate = request.getEndDate();
        contractType = request.getContractType();
        roomType = request.getRoomType();
        deposit = request.getDeposit();
        monthlyRent = request.getMonthlyRent();
        maintenanceType = request.getMaintenanceType();
        maintenanceFee = request.getMaintenanceFee();
        roomSize = request.getRoomSize();
        elevator = request.getElevator();
        parking = request.getParking();
        totalFloor = request.getTotalFloor();
        floor = request.getFloor();
        options = request.getOptions();
        transferStatus = request.getTransferStatus();
    }
}
