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

    /**
     * 동
     */
    private Integer buildingNumber;

    /**
     * 호
     */
    private Integer unitNumber;

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
    public TransferDto toDto(Transfer transfer) {
        return TransferDto.builder()
                .transferId(transfer.getId())
                .roomType(transfer.getRoomType())
                .buildingType(transfer.getBuildingType())
                .contractType(transfer.getContractType())
                .deposit(transfer.getDeposit())
                .rent(transfer.getRent())
                .startDate(transfer.getStartDate())
                .endDate(transfer.getEndDate())
                .buildingNumber(transfer.getBuildingNumber())
                .unitNumber(transfer.getUnitNumber())
                .supplyArea(transfer.getSupplyArea())
                .leasableArea(transfer.getLeasableArea())
                .roomSize(transfer.getRoomSize())
                .totalFloor(transfer.getTotalFloor())
                .floor(transfer.getFloor())
                .heatingType(transfer.getHeatingType())
                .elevator(transfer.getElevator())
                .parking(transfer.getParking())
                .option(transfer.getOption())
                .build();
    }
}
