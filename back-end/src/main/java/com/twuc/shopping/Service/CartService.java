package com.twuc.shopping.Service;

import com.twuc.shopping.Dto.Goods;
import com.twuc.shopping.Entity.CartEntity;
import com.twuc.shopping.Entity.GoodEntity;
import com.twuc.shopping.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public void addGoodToCart(Goods good) {
        List<CartEntity> cartEntityList = cartRepository.findByName(good.getName());
        CartEntity cartEntity;
        if (cartEntityList.size() == 0) {
            cartEntity = CartEntity.builder()
                    .name(good.getName())
                    .count(1)
                    .build();

        } else {
            cartEntity = cartEntityList.get(0);
            cartEntity.setCount(cartEntity.getCount()+1);
        }
        cartRepository.save(cartEntity);
    }
}
