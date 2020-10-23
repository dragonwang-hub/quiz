package com.twuc.shopping.Repository;

import com.twuc.shopping.Entity.CartEntity;
import com.twuc.shopping.Entity.GoodEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface CartRepository extends CrudRepository<CartEntity, Integer> {
    List<CartEntity> findAll();

    List<CartEntity> findByName(String name);

    @Transactional
    void deleteByName(String name);
}
