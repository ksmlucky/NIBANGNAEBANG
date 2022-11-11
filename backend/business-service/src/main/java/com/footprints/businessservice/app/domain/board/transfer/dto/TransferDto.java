package com.footprints.businessservice.app.domain.board.transfer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransferDto {

    private Long transferId;

    private String roomType;

    private String buildingType;

    private String contractType;

    private Integer deposit;

    private Integer rent;

    private LocalDate startDate;

    private LocalDate endDate;

    private String address;

    private Double latitude;

    private Double longitude;

    private String buildingNumber;

    private String unitNumber;

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
