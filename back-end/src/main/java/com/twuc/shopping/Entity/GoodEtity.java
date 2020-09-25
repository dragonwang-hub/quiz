package com.twuc.shopping.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "goods")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodEtity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int price;

    private String unit;

    private String imgUrl;
}
