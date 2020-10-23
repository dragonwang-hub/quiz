package com.twuc.shopping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.Dto.Goods;
import com.twuc.shopping.Dto.Order;
import com.twuc.shopping.Entity.CartEntity;
import com.twuc.shopping.Entity.OrderEntity;
import com.twuc.shopping.Entity.OrderOfCartEntity;
import com.twuc.shopping.Repository.CartRepository;
import com.twuc.shopping.Repository.OrderOfCartRepository;
import com.twuc.shopping.Repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CartControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderOfCartRepository orderRepository;

    @Test
    public void shouldAddNewGoodToCartWhenCartNotHaveThisGood() throws Exception {
        Goods good = Goods.builder()
                .name("可乐")
                .price(3)
                .unit("瓶")
                .imgUrl("./coco")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(good);

        mockMvc.perform(post("/carts/add")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        List<CartEntity> all = cartRepository.findAll();
        assertNotNull(all);
        assertEquals(all.size(), 1);
        assertEquals(all.get(0).getName(), "可乐");
        assertEquals(all.get(0).getCount(), 1);
    }

    @Test
    public void shouldAddGoodCountToCartWhenCartHaveThisGood() throws Exception {
        Goods good = Goods.builder()
                .name("可乐")
                .price(3)
                .unit("瓶")
                .imgUrl("./coco")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(good);

        CartEntity cartEntity = CartEntity.builder()
                .name("可乐")
                .count(2)
                .build();
        cartRepository.save(cartEntity);

        mockMvc.perform(post("/carts/add")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        List<CartEntity> all = cartRepository.findAll();
        assertNotNull(all);
        assertEquals(all.size(), 1);
        assertEquals(all.get(0).getName(), "可乐");
        assertEquals(all.get(0).getCount(), 3);
    }

    @Test
    public void shouldDeleteGoodFromCartWhenCartHaveThisGood() throws Exception {
        CartEntity cartEntity = CartEntity.builder()
                .name("coco")
                .count(2)
                .build();
        cartRepository.save(cartEntity);

        mockMvc.perform(delete("/carts/delete/coco"))
                .andExpect(status().isNoContent());

        List<CartEntity> all = cartRepository.findAll();
        assertNotNull(all);
        assertEquals(all.size(), 0);
    }

    @Test
    public void shouldClearUpCartWhenCartHaveGood() throws Exception {
        CartEntity cartEntity_1 = CartEntity.builder()
                .name("coco")
                .count(2)
                .build();
        CartEntity cartEntity_2 = CartEntity.builder()
                .name("sprint")
                .count(2)
                .build();
        List<CartEntity> cartEntityList = new ArrayList<>();
        cartEntityList.add(cartEntity_1);
        cartEntityList.add(cartEntity_2);
        cartRepository.saveAll(cartEntityList);
        List<CartEntity> all = cartRepository.findAll();
        assertNotNull(all);
        assertEquals(all.size(), 2);

        mockMvc.perform(delete("/carts/clear"))
                .andExpect(status().isNoContent());
        all = cartRepository.findAll();
        assertNotNull(all);
        assertEquals(all.size(), 0);
    }

    @Test
    public void shouldSubmitOrderWhenCartHaveGoods() throws Exception {
        CartEntity cartEntity_1 = CartEntity.builder()
                .name("coco")
                .count(2)
                .build();
        CartEntity cartEntity_2 = CartEntity.builder()
                .name("sprint")
                .count(2)
                .build();
        List<CartEntity> cartEntityList = new ArrayList<>();
        cartEntityList.add(cartEntity_1);
        cartEntityList.add(cartEntity_2);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cartEntityList);

        mockMvc.perform(post("/carts")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        List<OrderOfCartEntity> all = (List<OrderOfCartEntity>) orderRepository.findAll();
        assertNotNull(all);
        assertEquals(all.size(), 1);

    }
}
