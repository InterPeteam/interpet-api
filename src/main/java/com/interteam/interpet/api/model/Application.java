package com.interteam.interpet.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid", referencedColumnName = "user_id")
    private User user;

    @Column(name = "accepted")
    private boolean accepted;

    @JsonIgnore
    @ManyToOne(targetEntity = Offer.class)
    @JoinColumn(name = "offerid", referencedColumnName = "id")
    private Offer offer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creationdate")
    private Date creationDate;
}
