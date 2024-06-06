package ru.naumen.telegram_store.domains;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private byte[] description;

    @Column(name = "price")
    private Long price;

    public Order(Long id, Long userId, String name, byte[] description, Long price) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Order() {

    }
}
