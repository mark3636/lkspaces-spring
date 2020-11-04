package com.example.lkplaces.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MapMarkerDto {
    private Integer id;
    private BigDecimal lng;
    private BigDecimal lat;
    private String label;
    private String description;
    private PlaceTypeDto placeType;
}
