package ru.naumen.telegram_store.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.naumen.telegram_store.domains.Product;
import ru.naumen.telegram_store.domains.ProductsList;
import ru.naumen.telegram_store.repositories.ProductListRepository;
import ru.naumen.telegram_store.repositories.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductListService {

//    private final ProductListRepository shoppingListRepository;
//
//    public void addProduct(ProductsList shoppingList) throws IOException {
//        for (ProductsList productsList : shoppingListRepository.findAll()) {
//            if (productsList.getNumber_recipe().equals(shoppingList.getNumber_recipe())) {
//                return;
//            }
//        }
//        shoppingListRepository.save(shoppingList);
//    }
//
//    public ArrayList<Long> getAddNumbersRecipes(Long userId) throws IOException {
//        ArrayList<Long> numberResipes = new ArrayList<>();
//        for(ShoppingList element : shoppingListRepository.findAll()){
//            if(element.getUserId().equals(userId)){
//                numberResipes.add(element.getNumber_recipe());
//            }
//        }
//        return numberResipes;
//    }

}
