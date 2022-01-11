package com.interteam.interpet.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.interteam.interpet.api.model.Animal;
import com.interteam.interpet.api.model.Application;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "offer")
public class Offer {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @SequenceGenerator(name="offer-seq-gen",sequenceName="OFFER_SEQ", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.AUTO, generator="offer-seq-gen")
    @Column(name = "id")
    private int id;
    @Column(name = "userid")
    private int userId;
    @Column(name = "price")
    private BigDecimal price;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "startdate")
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "enddate")
    private Date endDate;
    @Column(name = "text")
    private String text;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationdate")
    private Date creationDate;
    @OneToMany(targetEntity = Animal.class, mappedBy = "offer")
    private Set<Animal> animals;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(targetEntity = Application.class, mappedBy = "offer")
    private Set<Application> applications;
}
