package com.interteam.interpet.api.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Builder
@ToString
@AllArgsConstructor
@Setter
@Getter
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    @Column(name = "city_id")
    private Long id;
    private String name;

    public City() {}

    public City(String name) {
        this.name = name;
    }
}
