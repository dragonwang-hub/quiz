package com.twuc.shopping.Controller;

import com.twuc.shopping.Dto.Goods;
import com.twuc.shopping.Entity.GoodEtity;
import com.twuc.shopping.Repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class Shopping {
    @Autowired
    GoodRepository goodRepository;

    @PostMapping("/addgoods")
    public ResponseEntity addGood(@RequestBody Goods good) {
        GoodEtity goodEtity = goodRepository.findByName(good.getName());
        if (goodEtity != null) {
            return ResponseEntity.badRequest().build();
        }
        GoodEtity build =
                GoodEtity.builder()
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
