package com.app.labo3.specimensbackend.services.impl;

import com.app.labo3.specimensbackend.domain.dto.request.CreateSpecimenRequest;
import com.app.labo3.specimensbackend.domain.dto.request.UpdateSpecimenRequest;
import com.app.labo3.specimensbackend.domain.dto.response.PageableResponse;
import com.app.labo3.specimensbackend.domain.dto.response.SpecimenResponse;
import com.app.labo3.specimensbackend.domain.dto.entities.Specimen;
import com.app.labo3.specimensbackend.common.mappers.SpecimenMapper;
import com.app.labo3.specimensbackend.exceptions.ResourceNotFoundException;
import com.app.labo3.specimensbackend.repositories.SpecimenRepository;
import com.app.labo3.specimensbackend.services.SpecimenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecimenServiceImpl implements SpecimenService {

    private final SpecimenRepository specimenRepository;
    private final SpecimenMapper specimenMapper;

    @Override
    @Transactional
    public SpecimenResponse createSpecimen(CreateSpecimenRequest request) {
        Specimen specimen = specimenMapper.toEntityCreate(request);
        return specimenMapper.toDto(specimenRepository.save(specimen));
    }

    @Override
    @Transactional(readOnly = true)
    public PageableResponse<SpecimenResponse> getAllSpecimens(int page, int size, String sortBy, String sortOrder) {
        Sort sort = sortOrder.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Specimen> specimenPage = specimenRepository.findAll(pageable);

        if (specimenPage.isEmpty()) {
            throw new ResourceNotFoundException("No specimens are registered in Hyrule records");
        }

        return specimenMapper.toPageableResponse(specimenPage);
    }

    @Override
    @Transactional(readOnly = true)
    public SpecimenResponse getSpecimenById(UUID id) {
        return specimenMapper.toDto(specimenRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Specimen not found in Sheikah Slate records"))
        );
    }

    @Override
    @Transactional
    public SpecimenResponse updateSpecimen(UUID id, UpdateSpecimenRequest request) {
        this.getSpecimenById(id);
        return specimenMapper.toDto(specimenRepository.save(specimenMapper.toEntityUpdate(request, id)));
    }

    @Override
    @Transactional
    public SpecimenResponse deleteSpecimen(UUID id) {
        SpecimenResponse existSpecimen = this.getSpecimenById(id);
        specimenRepository.deleteById(id);
        return existSpecimen;
    }
}