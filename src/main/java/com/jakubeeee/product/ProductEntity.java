package com.jakubeeee.product;

import com.jakubeeee.appointment.AppointmentEntity;
import com.jakubeeee.misc.JpaEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@Table(name = "PRODUCTS")
public abstract class ProductEntity extends JpaEntity {

    @Column(name = "TYPE", nullable = false, insertable = false, updatable = false)
    private ProductDiscriminator type;

    @Column(name = "IDENTIFIER", unique = true, nullable = false, updatable = false)
    private String identifier;

    @ManyToOne
    @JoinColumn(name = "APPOINTMENT_ID", nullable = false, updatable = false)
    private AppointmentEntity appointment;

    @Column(name = "NAME", nullable = false, updatable = false)
    private String name;

    @Column(name = "PRICE", nullable = false, updatable = false)
    private BigDecimal price;

    @Column(name = "LOYALTY_POINTS", nullable = false, updatable = false)
    private long loyaltyPoints;

}
