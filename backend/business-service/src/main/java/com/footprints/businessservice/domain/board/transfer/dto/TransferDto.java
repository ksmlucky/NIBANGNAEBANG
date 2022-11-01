package com.footprints.businessservice.domain.board.transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransferDto {

    private Long transferId;

    private String roomType;

    private String buildingType;

    private String contractType;

    private Integer deposit;

    private Integer rent;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer buildingNumber;

    private Integer unitNumber;

    private Integer supplyArea;

    private Integer leasableArea;

    private Integer roomSize;

    private Integer totalFloor;

    private Integer floor;

    private String heatingType;

    private Boolean elevator;

    private Boolean parking;

    private String option;

}
