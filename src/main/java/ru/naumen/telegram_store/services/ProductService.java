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

//    public Optional<Product> getProductByName(String name) {
//        return productRepository.find;
//    }

    public String getProductNameById(Long id) {
        return productRepository.findById(id).get().getName();
    }

    public Long getProductPriceById(Long id) {
        return productRepository.findById(id).get().getPrice();
    }


    public void AddProducts() {
        List<Product> products = Arrays.asList(
                new Product("Сметана", "вкусная сметана".getBytes(), Long.valueOf(90), Long.valueOf(20)),
                new Product("Лук", "вкусный лук".getBytes(), Long.valueOf(30), Long.valueOf(40)),
                new Product("Чай", "вкусный чай".getBytes(), Long.valueOf(100), Long.valueOf(50)),
                new Product("Горох", "вкусный горох".getBytes(), Long.valueOf(20), Long.valueOf(100)),
                new Product("Помидоры", "вкусные помидоры".getBytes(), Long.valueOf(150), Long.valueOf(100)),
                new Product("Огурцы", "вкусные огурцы".getBytes(), Long.valueOf(150), Long.valueOf(25))
        );
        productRepository.saveAll(products);

    }


    public List<Product> getAll(){
        return productRepository.findAll();
    }
}
