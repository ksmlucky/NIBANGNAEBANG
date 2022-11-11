package com.footprints.businessservice.app.domain.board.transfer.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TransferRequest {

    /**
     * 방 종류
     */
    private String roomType;

    /**
     * 건물 종류
     */
    private String buildingType;

    /**
     * 계약 종류
     */
    private String contractType;

    /**
     * 보증금
     */
    private Integer deposit;

    /**
     * 집세
     */
    private Integer rent;

    /**
     * 계약 시작일
     */
    private LocalDate startDate;

    /**
     * 계약 종료일
     */
    private LocalDate endDate;

    private String address;

    private Double latitude;

    private Double longitude;

    private String buildingNumber;

    private String unitNumber;

    /**
     * 공급 면적
     */
    private Integer supplyArea;

    /**
     * 전용 면적
     */
    private Integer leasableArea;

    /**
     * 방 크기
     */
    private Integer roomSize;

    /**
     * 건물 층수
     */
    private Integer totalFloor;

    /**
     * 방의 층수
     */
    private Integer floor;

    /**
     * 난방 종류
     */
    private String heatingType;

    /**
     * 엘리베이터 유무
     */
    private Boolean elevator;

    /**
     * 주차 유무
     */
    private Boolean parking;

    /**
     * 옵션
     */
    private String option;
}
