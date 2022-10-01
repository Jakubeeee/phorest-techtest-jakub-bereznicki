package com.jakubeeee.product;

import com.jakubeeee.appointment.AppointmentEntity;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

import static com.jakubeeee.product.ProductDiscriminator.SERVICE;

@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "SERVICE")
public class ServiceEntity extends ProductEntity {

    public ServiceEntity(String identifier,
                         AppointmentEntity appointment,
                         String name,
                         BigDecimal price,
                         long loyaltyPoints) {
        super(SERVICE, identifier, appointment, name, price, loyaltyPoints);
    }
}
