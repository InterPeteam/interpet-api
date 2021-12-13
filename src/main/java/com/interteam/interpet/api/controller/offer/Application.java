package com.interteam.interpet.api.controller.offer;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Application {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @SequenceGenerator(name="app-seq-gen",sequenceName="APP_SEQ", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.AUTO, generator="app-seq-gen")
    private int id;

    @Column(name = "userid")
    private int userId;

    @Column(name = "accepted")
    private boolean accepted;

    @JsonIgnore
    @ManyToOne(targetEntity = Offer.class)
    @JoinColumn(name = "offerid", referencedColumnName = "id")
    private Offer offer;
}
