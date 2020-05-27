package com.sascar.filter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    private Integer vehicleId;
    private Integer clientId;
    private Integer driverId;
}