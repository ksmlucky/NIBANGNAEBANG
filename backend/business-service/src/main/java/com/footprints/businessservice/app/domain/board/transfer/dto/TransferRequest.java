package com.footprints.businessservice.app.domain.board.transfer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.footprints.businessservice.app.domain.board.article.entity.Article;
import com.footprints.businessservice.app.domain.board.transfer.entity.ContractType;
import com.footprints.businessservice.app.domain.board.transfer.entity.RoomType;
import com.footprints.businessservice.app.domain.board.transfer.entity.Transfer;
import com.footprints.businessservice.app.domain.board.transfer.entity.TransferType;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferRequest {

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
    private ContractType contractType;

    /**
     * 방 종류 (원룸, 투룸, 기타)
     */
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


    public Transfer toEntity(Article article) {
        return Transfer.builder()
                .roomType(roomType)
                .contractType(contractType)
                .address(address)
                .elevator(elevator)
                .deposit(deposit)
                .startDate(startDate)
                .endDate(endDate)
                .floor(floor)
                .monthlyRent(monthlyRent)
                .options(options)
                .parking(parking)
                .roomSize(roomSize)
                .totalFloor(totalFloor)
                .article(article)
                .build();
    }
}
