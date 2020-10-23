package com.twuc.shopping.Repository;

import com.twuc.shopping.Entity.GoodEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GoodRepository extends CrudRepository<GoodEntity, Integer> {
    List<GoodEntity> findAll();

    List<GoodEntity> findByName(String name);
}
