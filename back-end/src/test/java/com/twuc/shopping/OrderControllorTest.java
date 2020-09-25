package com.twuc.shopping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.Dto.Goods;
import com.twuc.shopping.Dto.Order;
import com.twuc.shopping.Entity.GoodEtity;
import com.twuc.shopping.Entity.OrderEntity;
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

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class OrderControllorTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    OrderRepository orderRepository;
    @Test
    public void shouldAddNewOrderWhenNotHaveThisGoodOrder() throws Exception {
        Order order = Order.builder()
                .name("可乐")
                .price(3)
                .unit("瓶")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(order);

        mockMvc.perform(post("/order")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        List<OrderEntity> all = orderRepository.findAll();
        assertNotNull(all);
        assertEquals(all.size(), 1);
        assertEquals(all.get(0).getName(), "可乐");
        assertEquals(all.get(0).getPrice(), 3);
        assertEquals(all.get(0).getUnit(), "瓶");
        assertEquals(all.get(0).getCount(), 1);
    }
    @Test
    public void shouldAddOrderCountWhenHaveThisGoodOrder() throws Exception {
        OrderEntity orderEntity = OrderEntity.builder()
                .name("可乐")
                .price(3)
                .unit("瓶")
                .count(3)
                .build();
        orderRepository.save(orderEntity);
        Order order = Order.builder()
                .name("可乐")
                .price(3)
                .unit("瓶")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(order);

        mockMvc.perform(post("/order")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        List<OrderEntity> all = orderRepository.findAll();
        assertNotNull(all);
        assertEquals(all.size(), 1);
        assertEquals(all.get(0).getName(), "可乐");
        assertEquals(all.get(0).getPrice(), 3);
        assertEquals(all.get(0).getUnit(), "瓶");
        assertEquals(all.get(0).getCount(), 4);
    }

    @Test
    public void shouldGetAllOrdersWhenShowOrderPage() throws Exception {
        OrderEntity orderEntity_1 = OrderEntity.builder()
                .name("雪碧")
                .price(3)
                .unit("瓶")
                .count(10)
                .build();
        OrderEntity orderEntity_2 = OrderEntity.builder()
                .name("可乐")
                .price(4)
                .unit("瓶")
                .count(3)
                .build();

        List<OrderEntity> orderEntityList = new ArrayList<>();
        orderEntityList.add(orderEntity_1);
        orderEntityList.add(orderEntity_2);
        orderRepository.saveAll(orderEntityList);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("雪碧")))
                .andExpect(jsonPath("$[0].price", is(3)))
                .andExpect(jsonPath("$[0].count", is(10)))
                .andExpect(jsonPath("$[1].name", is("可乐")))
                .andExpect(jsonPath("$[1].price", is(4)))
                .andExpect(jsonPath("$[1].count", is(3)));
    }

}
