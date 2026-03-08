package com.example.spring_jpa.repository;

import com.example.spring_jpa.dto.TopProductDTO;
import com.example.spring_jpa.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    @Query(
            value = "SELECT * FROM products WHERE product_category = :category",
            nativeQuery = true
    )
    List<Product> getProguctByCategory(@Param("category") String category);


    @Transactional
    @Modifying
    @Query(
            value = "UPDATE products SET discount = :discount  WHERE product_id = :id",
            nativeQuery = true
    )
    double setDiscountToProcut(
            @Param("id") String product_id,
            @Param("discount") double discount
    );


    @Transactional
    @Modifying
    @Query(
            value = "UPDATE products " +
                    "SET stock = stock + :increased_stock " +
                    "WHERE product_id = :id",
            nativeQuery = true
    )
    int increaseStockForProduct(
            @Param("id") String id,
            @Param("increased_stock") int increased_stock
    );


    @Transactional
    @Modifying
    @Query(
            value = "UPDATE products " +
                    "SET price = :price WHERE product_id = :id",
            nativeQuery = true
    )
    int updateProductPrice(
            @Param("id") String id ,
            @Param("price") double price
    );

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE products " +
                    "SET stock = stock - :quantity " +
                    "WHERE product_id = :id AND stock >= :quantity",
            nativeQuery = true
    )
    int decreaseStockIfAvailable(
            @Param("id") String id,
            @Param("quantity") int qty
    );

    @Transactional
    @Modifying
    @Query(
            value = "DELETE FROM products WHERE product_id = :id",
            nativeQuery = true
    )
    void removeProductBYId(
            @Param("id") String id
    );

    @Query(
            value = "SELECT stock FROM products WHERE product_id = :id ",
            nativeQuery = true
    )
    int getproductStockByid(
            @Param("id") String id
    );

    @Query(
            value = "SELECT product_name FROM products ",
            nativeQuery = true
    )
     List<String> getAllProductName();


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(
            value = """
        UPDATE products
        SET price = :price,
            discount = :discount,
            stock = :stock
        WHERE product_id = :id
        """,
            nativeQuery = true
    )
    int updateProductDetails(
            @Param("price") double price,
            @Param("discount") double discount,
            @Param("stock") int stock,
            @Param("id") String id
    );

    @Query("""
SELECT new com.example.spring_jpa.dto.TopProductDTO(
       oi.productId,
       oi.productName,
       SUM(oi.quantity)
)
FROM OrderItem oi
GROUP BY oi.productId, oi.productName
ORDER BY SUM(oi.quantity) DESC
""")
    List<TopProductDTO> findTopSellingProducts(org.springframework.data.domain.Pageable pageable);

}
