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


    public Product(String name, byte[] description, Long price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product() {

    }

    @JsonCreator
    public Product(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("price") Long price) {
        this.name = name;
        this.description = description.getBytes();
        this.price = price;
    }
}
