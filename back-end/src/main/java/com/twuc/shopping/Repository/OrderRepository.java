package com.twuc.shopping.Repository;

import com.twuc.shopping.Entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity,Integer> {
    List<OrderEntity> findAll();
    OrderEntity findByName(String name);
    @Transactional
    void deleteAllByName(String name);

}
