package com.example.lkplaces.web.dto;

import com.example.lkplaces.jpa.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PostDto {
    private Integer id;
    private String label;
    private String description;
    private LocalDateTime date;
    private EnumStatus status;
    private PlaceTypeDto placeType;
    private UserDto author;
    private MapMarkerDto mapMarker;
}
