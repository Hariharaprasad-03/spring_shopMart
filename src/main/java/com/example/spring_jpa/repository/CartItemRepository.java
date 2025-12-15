package com.example.spring_jpa.repository;
import com.example.spring_jpa.model.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    @Transactional
    @Modifying
    @Query(
            value = "DELETE  FROM cart_items WHERE item_id :id",
            nativeQuery = true
    )
    void removeCartItemById(@Param("id") long id );

    @Transactional
    @Modifying
    @Query(
            value ="DELETE  FROM cart_items WHERE cart_id = :cart_id ",
            nativeQuery = true
    )
    void deleteCartItemByCartId(@Param("cart_id") String cartId);
}
