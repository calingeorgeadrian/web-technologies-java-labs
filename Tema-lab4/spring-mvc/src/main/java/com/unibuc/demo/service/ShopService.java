package com.unibuc.demo.service;

import com.unibuc.demo.dto.ShopDto;
import com.unibuc.demo.mapper.ProductMapper;
import com.unibuc.demo.mapper.ShopMapper;
import com.unibuc.demo.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopService {

    private final ProductMapper productMapper;
    private final ShopMapper shopMapper;
    private final ShopRepository shopRepository;

    @Autowired
    public ShopService(ProductMapper productMapper, ShopMapper shopMapper, ShopRepository shopRepository) {
        this.productMapper = productMapper;
        this.shopMapper = shopMapper;
        this.shopRepository = shopRepository;
    }

    public List<ShopDto> getAllShop() {
        return shopRepository.getAll().stream().map(shopMapper::convertShopFrom).collect(Collectors.toList());
    }

    public ShopDto getShopByCui(String cui) {
        return shopMapper.convertShopFrom(shopRepository.getByCui(cui).get());
    }

    public void createShop(ShopDto shopDto) {
        this.shopRepository.createShop(this.shopMapper.convertShopFrom(shopDto));
    }

    public void updateShop(String cui, ShopDto shopDto) {
        this.shopRepository.updateShop(cui, this.shopMapper.convertShopFrom(shopDto));
    }

}
