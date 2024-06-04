package ru.naumen.telegram_store.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.naumen.telegram_store.domains.Product;
import ru.naumen.telegram_store.domains.ProductsList;
import ru.naumen.telegram_store.repositories.ProductListRepository;
import ru.naumen.telegram_store.repositories.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ProductListService {
    private final ProductListRepository productListRepository;
    private final ProductRepository productRepository;

    public void addProduct(ProductsList productsList,long userId) throws IOException {
        for (ProductsList productsList1 : productListRepository.findAll()) {
            if (productsList1.getName_product().equals(productsList.getName_product())
                    && productsList1.getUserId().equals(userId)) {
                return;
            }
        }
        productListRepository.save(productsList);
    }

    public void deleteUserProducts(long userId) throws IOException {
        for (ProductsList product : productListRepository.findAll()) {
            if (product.getUserId().equals(userId)) {
                productListRepository.delete(product);
            }
        }
    }

    public ArrayList<Long> getAddNumbersProducts(Long userId) throws IOException {
        ArrayList<String> namesProducts = new ArrayList<>();
        for(ProductsList element : productListRepository.findAll()){
            if(element.getUserId().equals(userId)){
                namesProducts.add(element.getName_product());
            }
        }
        ArrayList<Long> numbersProducts = new ArrayList<>();
        for (String name : namesProducts) {
            for (Product element : productRepository.findAll()) {
                if (element.getName().equals(name)) {
                    numbersProducts.add(element.getId());
                }
            }
        }
        return numbersProducts;
    }

    public ArrayList<String> getAddNamesProducts(Long userId) throws IOException {
        ArrayList<String> namesProducts = new ArrayList<>();
        for(ProductsList element : productListRepository.findAll()){
            if(element.getUserId().equals(userId)){
                namesProducts.add(element.getName_product());
            }
        }
        return namesProducts;
    }

    public Long getQuantityProducts(Long userId, String productName) throws IOException {
        for (ProductsList productsList : productListRepository.findAll()){
            if (productsList.getUserId().equals(userId) && productsList.getName_product().equals(productName)) {
                return productsList.getQuantity();
            }
        }
        return Long.valueOf(1);
    }

}
