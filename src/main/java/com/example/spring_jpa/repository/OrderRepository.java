package com.example.spring_jpa.repository;

import com.example.spring_jpa.dto.TopCustomerDTO;
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

    @Query (
            value = """
                SELECT * FROM order_table 
                WHERE status = 'PROCESSING' OR payment_status = 'PENDING'
                """,
            nativeQuery = true
    )
    List<Order>getPendingOrders();

    @Query(
            value = """
                SELECT COUNT(*)
                FROM order_table
                """,
            nativeQuery = true
    )
    int getCount();


    @Query("""
SELECT new com.example.spring_jpa.dto.TopCustomerDTO(
    o.user.id,
    o.user.name,
    COUNT(o)
)
FROM Order o
GROUP BY o.user.id, o.user.name
ORDER BY COUNT(o) DESC
""")
    List<TopCustomerDTO> findTopCustomers(org.springframework.data.domain.Pageable pageable);


    // Daily Order Count
    @Query(value = """
        SELECT DATE(order_date) as day,
               COUNT(*) as totalOrders
        FROM orders
        GROUP BY DATE(order_date)
        ORDER BY day
    """, nativeQuery = true)
    List<Object[]> findDailyOrders();

}
