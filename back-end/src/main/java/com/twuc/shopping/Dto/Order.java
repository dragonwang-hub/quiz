package com.twuc.shopping.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @NotNull
    private String name;
    @NotNull
    private int price;
    @NotNull
    private int count;
    @NotNull
    private String unit;
}
