package com.twuc.shopping.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orderofcart")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderOfCartEntity {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "VARCHAR(13)")
    private String id;

    @OneToMany(mappedBy = "order")
    private List<CartEntity> cartEntities;
}
