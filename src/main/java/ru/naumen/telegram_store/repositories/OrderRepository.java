package ru.naumen.telegram_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.naumen.telegram_store.domains.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
