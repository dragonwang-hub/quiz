package com.twuc.shopping.Controller;

import com.twuc.shopping.Dto.Goods;
import com.twuc.shopping.Entity.GoodEtity;
import com.twuc.shopping.Repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
}
