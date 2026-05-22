package com.app.labo3.specimensbackend.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateSpecimenRequest {
    @NotBlank(message = "The specimen name cannot be empty.")
    private String name;

    @NotBlank(message = "The region of Hyrule must be specified.")
    private String region;

    @NotNull(message = "Danger level is required")
    private Integer dangerLevel;

    @NotNull(message = "You must specifiy if the specimen is friendly")
    private Boolean isFriendly;
}
