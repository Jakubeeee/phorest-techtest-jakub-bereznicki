package com.jakubeeee.product;

import com.jakubeeee.appointment.AppointmentEntity;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

import static com.jakubeeee.product.ProductDiscriminator.PURCHASE;

@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "PURCHASE")
public class PurchaseEntity extends ProductEntity {

    public PurchaseEntity(String identifier,
                          AppointmentEntity appointment,
                          String name,
                          BigDecimal price,
                          long loyaltyPoints) {
        super(PURCHASE, identifier, appointment, name, price, loyaltyPoints);
    }
}
