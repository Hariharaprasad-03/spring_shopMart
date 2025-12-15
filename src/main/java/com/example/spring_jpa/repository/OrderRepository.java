package com.example.spring_jpa.repository;

import com.example.spring_jpa.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {

    @Query(
            value = "SELECT * FROM order_table WHERE user_id_fk = :user_id",
            nativeQuery = true
    )
    List<Order>getAllOrderOfUser(
            @Param("user_id") String user_id
    );

}
