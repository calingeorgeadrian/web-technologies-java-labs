package com.bgjshop.backend.service;

import com.bgjshop.backend.domain.Code;
import com.bgjshop.backend.domain.Promotion;
import com.bgjshop.backend.dto.CodeDto;
import com.bgjshop.backend.dto.GameDto;
import com.bgjshop.backend.dto.PromotionDto;
import com.bgjshop.backend.exceptions.DuplicateEntityException;
import com.bgjshop.backend.exceptions.EntityNotFoundException;
import com.bgjshop.backend.mapper.CodeMapper;
import com.bgjshop.backend.mapper.PromotionMapper;
import com.bgjshop.backend.repository.CodeRepository;
import com.bgjshop.backend.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CodeService {

    private final CodeMapper codeMapper;
    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeMapper codeMapper, CodeRepository codeRepository) {
        this.codeMapper = codeMapper;
        this.codeRepository = codeRepository;
    }

    public CodeDto get(String code) {
        return codeRepository.get(code).orElseThrow(()-> new EntityNotFoundException("Code"));
    }

    public List<CodeDto> getAll() {
        return codeRepository.getAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void addCode(CodeDto request) {
        Optional<CodeDto> codeDtoOptional = codeRepository.get(request.getCode());
        if(codeDtoOptional.isPresent())
            throw new DuplicateEntityException("Code " + request.getCode());
        Code code = codeMapper.toEntity(request);
        codeRepository.addCode(code);
    }

    public void deleteCode(String code) {
        codeRepository.deleteCode(code);
    }
}
