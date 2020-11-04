package com.example.lkplaces.jpa.entity;

import com.example.lkplaces.jpa.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "tbl_map_marker")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class MapMarker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "lng", nullable = false)
    private BigDecimal lng;
    @Column(name = "lat", nullable = false)
    private BigDecimal lat;
    @Column(name = "label", nullable = false)
    private String label;
    @Column(name = "description")
    private String description;
    @Enumerated(EnumType.STRING)
    private EnumStatus status;
    @ManyToOne
    @JoinColumn(name = "id_place_type")
    private PlaceType placeType;
}
