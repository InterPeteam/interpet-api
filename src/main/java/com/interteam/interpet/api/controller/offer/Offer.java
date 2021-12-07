package com.interteam.interpet.api.controller.offer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "offer")
public class Offer {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column(name = "userid")
    private int userId;
    @Column(name = "price")
    private float price;
    @Column(name = "startdate")
    private String startDate;
    @Column(name = "enddate")
    private String endDate;
    @Column(name = "text")
    private String text;
    @Column(name = "creationdate")
    private String creationDate;
    @OneToMany(targetEntity = Animal.class, mappedBy = "offer")
    private Set<Animal> animals;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(targetEntity = Application.class, mappedBy = "offer")
    private Set<Application> applications;
}
