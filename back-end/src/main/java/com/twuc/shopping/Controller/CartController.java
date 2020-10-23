package com.twuc.shopping.Controller;

import com.twuc.shopping.Dto.Goods;
import com.twuc.shopping.Entity.CartEntity;
import com.twuc.shopping.Exception.CommonException;
import com.twuc.shopping.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/carts/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addGoodToCart(@RequestBody Goods good){
        cartService.addGoodToCart(good);
    }

    @DeleteMapping("/carts/delete/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGoodFromCart(@PathVariable String name) throws CommonException {
        cartService.deleteGoodFromCart(name);
    }

    @DeleteMapping("/carts/clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearUpCart() throws CommonException {
        cartService.clearUpCart();
    }
}
