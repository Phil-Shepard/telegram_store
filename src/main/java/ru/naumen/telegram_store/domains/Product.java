package ru.naumen.telegram_store.domains;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public Product(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("price") Long price,
            @JsonProperty("quantity") Long quantity) {
        this.name = name;
        this.description = description.getBytes(); // Convert description to byte[]
        this.price = price;
        this.quantity = quantity;
    }
}
