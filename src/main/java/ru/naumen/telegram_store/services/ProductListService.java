package ru.naumen.telegram_store.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.naumen.telegram_store.domains.ProductsList;
import ru.naumen.telegram_store.repositories.ProductListRepository;

import java.io.IOException;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class ProductListService {
    private final ProductListRepository productListRepository;

    public void addProduct(ProductsList productsList) throws IOException {
        for (ProductsList productsList1 : productListRepository.findAll()) {
            if (productsList1.getName_product().equals(productsList.getName_product())) {
                return;
            }
        }
        productListRepository.save(productsList);
    }

    public ArrayList<Long> getAddNumbersProducts(Long userId) throws IOException {
        ArrayList<Long> numbersProducts = new ArrayList<>();
        for(ProductsList element : productListRepository.findAll()){
            if(element.getUserId().equals(userId)){
                numbersProducts.add(element.getId());
            }
        }
        return numbersProducts;
    }

}
