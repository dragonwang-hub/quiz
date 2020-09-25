package com.twuc.shopping.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Goods {
    @NotNull
    private String name;
    @NotNull
    private int price;
    @NotNull
    private String unit;
    @NotNull
    private String imgUrl;
}
