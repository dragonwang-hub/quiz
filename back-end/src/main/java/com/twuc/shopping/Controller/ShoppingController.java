package com.twuc.shopping.Controller;

import com.twuc.shopping.Dto.Goods;
import com.twuc.shopping.Entity.GoodEntity;
import com.twuc.shopping.Repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class ShoppingController {
    @Autowired
    GoodRepository goodRepository;

    @PostMapping("/addgood")
    public ResponseEntity addGood(@RequestBody Goods good) {
        List<GoodEntity> goodEntityList = goodRepository.findByName(good.getName());
        if (goodEntityList.size() > 0) {
            return ResponseEntity.badRequest().build();
        }
        GoodEntity build =
                GoodEntity.builder()
                        .name(good.getName())
                        .price(good.getPrice())
                        .unit(good.getUnit())
                        .imgUrl(good.getImgUrl())
                        .build();
        goodRepository.save(build);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/goods")
    public ResponseEntity<List<Goods>> getAllGoods() {
        List<Goods> goods = goodRepository.findAll().stream().map(
                item -> Goods.builder()
                        .name(item.getName())
                        .price(item.getPrice())
                        .unit(item.getUnit())
                        .imgUrl(item.getImgUrl())
                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(goods);
    }
}
