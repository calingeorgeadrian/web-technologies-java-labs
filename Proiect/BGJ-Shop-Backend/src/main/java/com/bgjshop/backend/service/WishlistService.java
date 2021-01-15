package com.bgjshop.backend.service;

import com.bgjshop.backend.domain.WishlistItem;
import com.bgjshop.backend.dto.PromotionDto;
import com.bgjshop.backend.dto.WishlistItemDto;
import com.bgjshop.backend.mapper.WishlistMapper;
import com.bgjshop.backend.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistMapper wishlistMapper;
    private final WishlistRepository wishlistRepository;
    private final PromotionService promotionService;

    @Autowired
    public WishlistService(WishlistMapper wishlistMapper, WishlistRepository wishlistRepository, PromotionService promotionService) {
        this.wishlistMapper = wishlistMapper;
        this.wishlistRepository = wishlistRepository;
        this.promotionService = promotionService;
    }

    public List<WishlistItemDto> get(String userId) {
        List<WishlistItemDto> items = wishlistRepository.get(userId);
        PromotionDto activePromotion = promotionService.getActive();

        if(activePromotion != null)
            items.forEach(game -> game.setNewPrice(game.getPrice() - game.getPrice() * activePromotion.getDiscount()/100));
        else
            items.forEach(game -> game.setNewPrice(game.getPrice()));

        return items;
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(WishlistItemDto request) {
        WishlistItem wishlistItem = wishlistMapper.toEntity(request);
        wishlistRepository.add(wishlistItem);
    }

    public void removeGame(String userId, Long gameId) {
        wishlistRepository.removeGame(userId, gameId);
    }

    public void clear(String userId) {
        wishlistRepository.clear(userId);
    }
}
