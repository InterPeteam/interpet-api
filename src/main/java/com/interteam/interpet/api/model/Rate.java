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
@Table(name = "rates")
public class Rate {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    @Column(name = "rate_id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    private Long publisherId;
    private int rate;
    private String description;

    public Rate() {
    }
}
