package com.app.labo3.specimensbackend.services;

import com.app.labo3.specimensbackend.domain.dto.response.*;
import com.app.labo3.specimensbackend.domain.dto.request.*;

import java.util.UUID;

public interface SpecimenService {
    SpecimenResponse createSpecimen(CreateSpecimenRequest request);

    PageableResponse<SpecimenResponse> getAllSpecimens(int page, int size, String sortBy, String sortOrder);

    SpecimenResponse getSpecimenById(UUID id);
    SpecimenResponse updateSpecimen(UUID id, UpdateSpecimenRequest request);
    SpecimenResponse deleteSpecimen(UUID id);
}
