package ru.naumen.telegram_store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumen.telegram_store.domains.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
