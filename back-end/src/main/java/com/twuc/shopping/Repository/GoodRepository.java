package com.twuc.shopping.Repository;

import com.twuc.shopping.Entity.GoodEtity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GoodRepository extends CrudRepository<GoodEtity, Integer> {
    List<GoodEtity> findAll();

    GoodEtity findByName(String name);
}
