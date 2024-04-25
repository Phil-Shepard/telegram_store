package ru.naumen.telegram_store.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.naumen.telegram_store.domains.Product;
import ru.naumen.telegram_store.repositories.ProductRepository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public void AddProducts() {
        List<Product> products = Arrays.asList(
                new Product("Сметана", "вкусная сметана".getBytes(), Long.valueOf(123), Long.valueOf(2)),
                new Product("Лук", "вкусный лук".getBytes(), Long.valueOf(213), Long.valueOf(42)),
                new Product("Чай", "вкусный чай".getBytes(), Long.valueOf(6546), Long.valueOf(312)),
                new Product("Горох", "вкусный горох".getBytes(), Long.valueOf(213132), Long.valueOf(312132)),
                new Product("Помидоры", "вкусные помидоры".getBytes(), Long.valueOf(342), Long.valueOf(31212)),
                new Product("Огурцы", "вкусные огурцы сметана".getBytes(), Long.valueOf(43), Long.valueOf(25))
        );
        productRepository.saveAll(products);

    }


    public List<Product> getAll(){
        return productRepository.findAll();
    }
}
