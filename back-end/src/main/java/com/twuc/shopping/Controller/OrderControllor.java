package com.twuc.shopping.Controller;

import com.twuc.shopping.Dto.Order;
import com.twuc.shopping.Entity.OrderEntity;
import com.twuc.shopping.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class OrderControllor {
    @Autowired
    OrderRepository orderRepository;

    @PostMapping("/order")
    public ResponseEntity addorder(@RequestBody Order order) {
        OrderEntity orderEntity = orderRepository.findByName(order.getName());
        if (orderEntity != null) {
            int Count = orderEntity.getCount() + 1;
            orderEntity.setCount(Count);
            orderRepository.save(orderEntity);
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


    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllGoods() {
        List<Order> orders = orderRepository.findAll().stream().map(
                item -> Order.builder()
                        .name(item.getName())
                        .price(item.getPrice())
                        .unit(item.getUnit())
                        .count(item.getCount())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(orders);
    }

    @Transactional
    @DeleteMapping("/orders")
    public ResponseEntity deleteOrder(@RequestParam String name) {
        orderRepository.deleteAllByName(name);
        return ResponseEntity.noContent().build();
    }
}
