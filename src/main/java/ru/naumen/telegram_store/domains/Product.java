package ru.naumen.telegram_store.domains;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private byte[] description;

    @Column(name = "price")
    private Long price;

    @Column(name = "quantity")
    private Long quantity;

    public Product(String name, byte[] description, Long price, Long quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {

    }
}
