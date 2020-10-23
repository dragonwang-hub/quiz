package com.twuc.shopping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twuc.shopping.Dto.Goods;
import com.twuc.shopping.Entity.GoodEntity;
import com.twuc.shopping.Repository.GoodRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ShoppingControllerControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    GoodRepository goodRepository;

    @Test
    public void shouldAddGoodWhenNotExist() throws Exception {
        Goods good = Goods.builder()
                .name("可乐")
                .price(3)
                .unit("瓶")
                .imgUrl("./coco")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(good);

        mockMvc.perform(post("/addgood")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        List<GoodEntity> all = goodRepository.findAll();
        assertNotNull(all);
        assertEquals(all.size(), 1);
        assertEquals(all.get(0).getName(), "可乐");
        assertEquals(all.get(0).getPrice(), 3);
        assertEquals(all.get(0).getUnit(), "瓶");
        assertEquals(all.get(0).getImgUrl(), "./coco");
    }

    @Test
    public void shouldAddGoodFailedWhenNameExist() throws Exception {
        Goods good = Goods.builder()
                .name("可乐")
                .price(3)
                .unit("瓶")
                .imgUrl("./coco")
                .build();

        GoodEntity goodEntity = GoodEntity.builder()
                .name("可乐")
                .price(4)
                .unit("听")
                .imgUrl("./coco")
                .build();
        goodRepository.save(goodEntity);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(good);

        mockMvc.perform(post("/addgood")
                .content(json).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        List<GoodEntity> all = goodRepository.findAll();
        assertNotNull(all);
        assertEquals(all.size(), 1);
        assertEquals(all.get(0).getName(), "可乐");
        assertEquals(all.get(0).getPrice(), 4);
        assertEquals(all.get(0).getUnit(), "听");
        assertEquals(all.get(0).getImgUrl(), "./coco");
    }

    @Test
    public void shouldGetAllGoodWhenShowHomePage() throws Exception {
        GoodEntity goodEntity_1 = GoodEntity.builder()
                .name("雪碧")
                .price(3)
                .unit("瓶")
                .imgUrl("./coco")
                .build();
        GoodEntity goodEntity_2 = GoodEntity.builder()
                .name("可乐")
                .price(4)
                .unit("听")
                .imgUrl("./coco")
                .build();
        List<GoodEntity> goodEntityList = new ArrayList<>();
        goodEntityList.add(goodEntity_1);
        goodEntityList.add(goodEntity_2);
        goodRepository.saveAll(goodEntityList);

        mockMvc.perform(get("/goods"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("雪碧")))
                .andExpect(jsonPath("$[0].price", is(3)))
                .andExpect(jsonPath("$[1].name", is("可乐")))
                .andExpect(jsonPath("$[1].price", is(4)));
    }

}
