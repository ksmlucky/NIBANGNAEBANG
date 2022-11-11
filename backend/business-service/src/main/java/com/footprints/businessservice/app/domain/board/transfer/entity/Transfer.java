package com.footprints.businessservice.app.domain.board.transfer.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.footprints.businessservice.app.domain.board.article.entity.Article;
import com.footprints.businessservice.app.domain.board.transfer.dto.TransferDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;

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
     * 위치정보(이것도 아직 어떻게 넘길지 모르겠음),
     * + 사진 or Three.js 구현 위한 Json 파일
     */

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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public TransferDto toDto() {
        return TransferDto.builder()
                .transferId(id)
                .roomType(roomType)
                .buildingType(buildingType)
                .contractType(contractType)
                .deposit(deposit)
                .rent(rent)
                .startDate(startDate)
                .endDate(endDate)
                .address(address)
                .latitude(latitude)
                .longitude(longitude)
                .buildingNumber(buildingNumber)
                .unitNumber(unitNumber)
                .supplyArea(supplyArea)
                .leasableArea(leasableArea)
                .roomSize(roomSize)
                .totalFloor(totalFloor)
                .floor(floor)
                .heatingType(heatingType)
                .elevator(elevator)
                .parking(parking)
                .option(option)
                .build();
    }
}
