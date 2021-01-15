package com.bgjshop.backend.service;

import com.bgjshop.backend.domain.CartItem;
import com.bgjshop.backend.dto.CartItemDto;
import com.bgjshop.backend.dto.PromotionDto;
import com.bgjshop.backend.mapper.CartMapper;
import com.bgjshop.backend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    private final CartMapper cartMapper;
    private final CartRepository cartRepository;
    private final PromotionService promotionService;

    @Autowired
    public CartService(CartMapper cartMapper, CartRepository cartRepository, PromotionService promotionService) {
        this.cartMapper = cartMapper;
        this.cartRepository = cartRepository;
        this.promotionService = promotionService;
    }

    public List<CartItemDto> get(String userId) {
        List<CartItemDto> items =  cartRepository.get(userId);
        PromotionDto activePromotion = promotionService.getActive();

        if(activePromotion != null)
            items.forEach(game -> game.setNewPrice(game.getPrice() - game.getPrice() * activePromotion.getDiscount()/100));
        else
            items.forEach(game -> game.setNewPrice(game.getPrice()));

        return items;
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(CartItemDto request) {
        CartItem cartItem = cartMapper.toEntity(request);
        cartRepository.add(cartItem);
    }

    public void updateItem(CartItemDto request) {
        if(request.getQuantity() == 0)
            this.removeGame(request.getUserId(), request.getGameId());
        else {
            CartItem cartItem = cartMapper.toEntity(request);
            cartRepository.updateItem(cartItem);
        }
    }

    public void removeGame(String userId, Long gameId) {
        cartRepository.removeGame(userId, gameId);
    }

    public void empty(String userId) {
        cartRepository.empty(userId);
    }
}
