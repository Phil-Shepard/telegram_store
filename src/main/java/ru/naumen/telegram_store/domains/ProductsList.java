package ru.naumen.telegram_store.domains;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product_lists")
public class ProductsList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_list_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "name_product")
    private String name_product;

    @Column(name = "quantity")
    private Long quantity;

    public ProductsList(Long userId, String name, String name_product, Long quantity) {
        this.userId = userId;
        this.name = name;
        this.name_product = name_product;
        this.quantity = quantity;
    }

    public ProductsList() {

    }
}
