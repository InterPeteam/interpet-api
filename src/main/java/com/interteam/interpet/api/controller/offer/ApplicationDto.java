package com.interteam.interpet.api.controller.offer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "application")
public class ApplicationDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    @ManyToOne(targetEntity = OfferDto.class)
    @JoinColumn(name="offerid", referencedColumnName = "id")
    private int offerId;
    @Column(name = "userid")
    private int userId;
    @Column(name = "accepted")
    private boolean accepted;
}
