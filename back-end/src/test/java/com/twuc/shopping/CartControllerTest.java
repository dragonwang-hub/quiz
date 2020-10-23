package com.twuc.shopping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.Dto.Goods;
import com.twuc.shopping.Dto.Order;
import com.twuc.shopping.Entity.CartEntity;
import com.twuc.shopping.Entity.OrderEntity;
import com.twuc.shopping.Repository.CartRepository;
import com.twuc.shopping.Repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CartControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    CartRepository cartRepository;

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
}
