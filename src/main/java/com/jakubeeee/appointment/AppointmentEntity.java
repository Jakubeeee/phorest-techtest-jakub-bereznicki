package com.jakubeeee.appointment;

import com.jakubeeee.client.ClientEntity;
import com.jakubeeee.misc.JpaEntity;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "APPOINTMENTS")
public class AppointmentEntity extends JpaEntity {

    @Column(name = "IDENTIFIER", unique = true, nullable = false, updatable = false)
    private String identifier;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", nullable = false, updatable = false)
    private ClientEntity client;

    @Column(name = "START_TIME", nullable = false, updatable = false)
    private Instant lastName;

    @Column(name = "END_TIME", nullable = false, updatable = false)
    private Instant email;

}
