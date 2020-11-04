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
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_post")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "label", nullable = false)
    private String label;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumStatus status;
    @ManyToOne
    @JoinColumn(name = "id_place_type", nullable = false)
    private PlaceType placeType;
    @ManyToOne
    @JoinColumn(name = "id_author", nullable = false)
    private User author;
    @ManyToOne
    @JoinColumn(name = "id_map_marker")
    private MapMarker mapMarker;
}
