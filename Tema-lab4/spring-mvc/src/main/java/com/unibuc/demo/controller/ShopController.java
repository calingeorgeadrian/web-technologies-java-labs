package com.unibuc.demo.controller;

import com.unibuc.demo.dto.ShopDto;
import com.unibuc.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shops")
public class ShopController {

    private final ShopService shopService;

    @Autowired
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping()
    public String getAll(Model model) {
        List<ShopDto> shopDtos = shopService.getAllShop();
        model.addAttribute("shopDtos", shopDtos);
        return "view-shops";
    }

    @GetMapping("/{CUI}")
    public String getShopByCui(@PathVariable("CUI") String cui, Model model) {
        ShopDto shopDto = shopService.getShopByCui(cui);
        model.addAttribute("shopDto", shopDto);
        return "view-shop";
    }

    @GetMapping("/add")
    public String viewCreate(ShopDto shopDto, Model model) {
        model.addAttribute("shopDto", shopDto);
        return "add-shop";
    }

    @PostMapping("/create")
    public String createShop(ShopDto shopDto, Model model){
        shopService.createShop(shopDto);
        List<ShopDto> shopDtos = shopService.getAllShop();
        model.addAttribute("shopDtos", shopDtos);
        return "view-shops";
    }

    @PutMapping("/update/{CUI}")
    public String updateShop (@PathVariable("CUI") String cui, @RequestBody ShopDto shopDto, Model model){
        shopService.updateShop(cui, shopDto);
        ShopDto shop = shopService.getShopByCui(cui);
        model.addAttribute("shopDto", shop);
        return "view-shop";
    }

    //Tema:
    //1.Implement the createShop method and the add-shop view.
    //2.Create an end-point to update(put) the shop (including adding new products) .

}
