package ru.naumen.telegram_store.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.naumen.telegram_store.domains.Order;
import ru.naumen.telegram_store.domains.ProductsList;
import ru.naumen.telegram_store.repositories.OrderRepository;
import ru.naumen.telegram_store.repositories.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductListService productListService;

    public void addOrder(long chatId) throws IOException {
        ArrayList<ProductsList> addProducts = productListService.getProductsListByUserId(chatId);
        for (ProductsList productsList: addProducts) {
            orderRepository.save(new Order(productsList.getId(),
                    productsList.getUserId(),
                    productsList.getName(),
                    productsList.getName_product().getBytes(),
                    productsList.getPrice()));
        }
    }

    public void deleteUserProducts(long userId) throws IOException {
        for (Order order : orderRepository.findAll()) {
            if (order.getUserId().equals(userId)) {
                orderRepository.delete(order);
            }
        }
    }
}
