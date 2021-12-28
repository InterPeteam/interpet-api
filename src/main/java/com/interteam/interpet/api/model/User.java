package com.interteam.interpet.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Builder
@ToString
@AllArgsConstructor
@Setter
@Getter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "inc")
    @GenericGenerator(name = "inc", strategy = "increment")
    @Column(name = "user_id")
    private Long id;
    private String name;
    private String surname;
    @Email
    private String email;
    @JsonIgnore
    private String password;
    private String phone;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_city")
    private City city;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_role")
    private Role role;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Rate> rates;

    public User() {}
}
