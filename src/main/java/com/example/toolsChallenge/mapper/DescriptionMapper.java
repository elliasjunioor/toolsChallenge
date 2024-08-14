package com.example.toolsChallenge.mapper;

import com.example.toolsChallenge.domain.Description;
import com.example.toolsChallenge.dto.ReceiveDescriptionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DescriptionMapper {

    Description toEntity(ReceiveDescriptionDTO receiveDescriptionDTO);



}
