package com.bgjshop.backend.service;

import com.bgjshop.backend.domain.Code;
import com.bgjshop.backend.domain.Promotion;
import com.bgjshop.backend.dto.CodeDto;
import com.bgjshop.backend.dto.PromotionDto;
import com.bgjshop.backend.exceptions.EntityNotFoundException;
import com.bgjshop.backend.mapper.CodeMapper;
import com.bgjshop.backend.mapper.PromotionMapper;
import com.bgjshop.backend.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PromotionService {

    private final PromotionMapper promotionMapper;
    private final PromotionRepository promotionRepository;

    @Autowired
    public PromotionService(PromotionMapper promotionMapper, PromotionRepository promotionRepository) {
        this.promotionMapper = promotionMapper;
        this.promotionRepository = promotionRepository;
    }

    public PromotionDto get(Long id) {
        return promotionRepository.get(id).orElseThrow(()-> new EntityNotFoundException("Promotion"));
    }

    public PromotionDto getActive() {
        return promotionRepository.getActive();
    }

    public List<PromotionDto> getAll() {
        return promotionRepository.getAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void create(PromotionDto request) {
        Promotion promotion = promotionMapper.toEntity(request);
        promotionRepository.save(promotion);
    }

    public void update(PromotionDto request) {
        Promotion promotion = promotionMapper.toEntity(request);
        promotionRepository.update(promotion);
    }

    public void delete(Long id) {
        promotionRepository.delete(id);
    }
}
