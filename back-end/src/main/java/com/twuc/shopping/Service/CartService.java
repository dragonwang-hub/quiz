package com.twuc.shopping.Service;

import com.twuc.shopping.Dto.Goods;
import com.twuc.shopping.Entity.CartEntity;
import com.twuc.shopping.Entity.GoodEntity;
import com.twuc.shopping.Entity.OrderOfCartEntity;
import com.twuc.shopping.Exception.CommonException;
import com.twuc.shopping.Repository.CartRepository;
import com.twuc.shopping.Repository.OrderOfCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderOfCartRepository orderRepository;

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
            cartEntity.setCount(cartEntity.getCount() + 1);
        }
        cartRepository.save(cartEntity);
    }


    public void deleteGoodFromCart(String name) throws CommonException {
        List<CartEntity> cartEntityList = cartRepository.findByName(name);
        if (cartEntityList.size() > 0) {
            cartRepository.deleteByName(name);
        } else {
            throw new CommonException("删除购物车商品异常，请刷新页面");
        }
    }

    public void clearUpCart() throws CommonException {
        List<CartEntity> cartEntityList = cartRepository.findAll();
        if (cartEntityList.size() > 0) {
            cartRepository.deleteAll();
        } else {
            throw new CommonException("购物车已清空！");
        }
    }

    public void cartsToOrder(List<CartEntity> cartEntityList) throws CommonException {
        if (cartEntityList.size() > 0) {
            OrderOfCartEntity orderOfCartEntity = OrderOfCartEntity.builder()
                    .cartEntities(cartEntityList)
                    .build();
            orderRepository.save(orderOfCartEntity);
        }else{
            throw new CommonException("购物车无货品，请先添加货品至购物车！");
        }
    }
}
