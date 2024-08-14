package com.example.toolsChallenge.mapper;

import com.example.toolsChallenge.domain.PaymentMethod;
import com.example.toolsChallenge.dto.PaymentMethodDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentMethodMapper {

    PaymentMethodDTO toDto(PaymentMethod paymentMethod);

    PaymentMethod toEntity(PaymentMethodDTO paymentMethodDTO);
}
