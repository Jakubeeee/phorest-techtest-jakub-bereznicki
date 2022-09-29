package com.jakubeeee.client;

import com.jakubeeee.misc.JpaEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import static javax.persistence.EnumType.STRING;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENTS")
public class ClientEntity extends JpaEntity {

    @Column(name = "IDENTIFIER", nullable = false, updatable = false)
    private String identifier;

    @Column(name = "FIRST_NAME", nullable = false, updatable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, updatable = false)
    private String lastName;

    @Column(name = "EMAIL", nullable = false, updatable = false)
    private String email;

    @Column(name = "PHONE", nullable = false, updatable = false)
    private String phone;

    @Enumerated(value = STRING)
    @Column(name = "GENDER", nullable = false, updatable = false)
    private Gender gender;

    @Column(name = "BANNED", nullable = false, updatable = false)
    private boolean banned;

}
