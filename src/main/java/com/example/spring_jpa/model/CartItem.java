package com.example.spring_jpa.model;



import jakarta.persistence.*;


@Entity
@Table(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;


     @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.PERSIST)
     @JoinColumn(name = "cart_id")
     private Cart cart;

    @Column(nullable = false)
    private int quantity;


    public CartItem() {
    }

    public CartItem(int id , Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Transient
    public double getTotalPrice() {
        if (product == null) return 0.0;
        return (product.getPrice() * product.getDiscount() )* quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", productId=" + (product != null ? product.getId() : "null") +
                ", quantity=" + quantity +
                '}';
    }
}