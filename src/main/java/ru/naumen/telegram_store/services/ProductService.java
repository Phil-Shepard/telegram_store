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

    public String getProductNameById(Long id) {
        return productRepository.findById(id).get().getName();
    }

    public Long getProductPriceById(Long id) {
        return productRepository.findById(id).get().getPrice();
    }

    public void AddProducts() {
        ParserJSON parser = new ParserJSON();
        List<Product> products1 = parser.parseProducts(
                "C:\\Users\\Phil_\\IdeaProjects\\telegram_store\\telegram_store\\src\\main\\resources\\products.json");
        productRepository.saveAll(products1);

    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }
}
