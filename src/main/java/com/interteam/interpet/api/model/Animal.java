package com.interteam.interpet.api.model;

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
@Table(name = "animal")
public class Animal {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @SequenceGenerator(name="animal-seq-gen",sequenceName="ANIMAL_SEQ", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.AUTO, generator="animal-seq-gen")
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "gender")
    private int gender;
    @Column(name = "size")
    private int size;
    @Column(name = "nature")
    private int nature;
    @JsonIgnore
    @ManyToOne(targetEntity = Offer.class)
    @JoinColumn(name="offerid", referencedColumnName = "id")
    private Offer offer;
}
