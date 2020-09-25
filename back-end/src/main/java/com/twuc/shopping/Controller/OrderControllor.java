package com.twuc.shopping.Controller;

import com.twuc.shopping.Dto.Order;
import com.twuc.shopping.Entity.GoodEtity;
import com.twuc.shopping.Entity.OrderEntity;
import com.twuc.shopping.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllor {
    @Autowired
    OrderRepository orderRepository;

    @PostMapping("/order")
    public ResponseEntity addorder(@RequestBody Order order) {
        OrderEntity orderEntity = orderRepository.findByName(order.getName());
        if (orderEntity != null) {
            int preCount = orderEntity.getCount();
            orderEntity.setCount(preCount + 1);
            return ResponseEntity.created(null).build();
        }
        OrderEntity build =
                OrderEntity.builder()
                        .name(order.getName())
                        .price(order.getPrice())
                        .unit(order.getUnit())
                        .count(1)
                        .build();
        orderRepository.save(build);
        return ResponseEntity.created(null).build();
    }
}
