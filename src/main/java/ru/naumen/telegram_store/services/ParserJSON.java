package ru.naumen.telegram_store.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import ru.naumen.telegram_store.domains.Product;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ParserJSON {

    public static List<Product> parseProducts(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> products = null;

        try {
            JsonNode rootNode = objectMapper.readTree(new File(filePath));
            JsonNode productsNode = rootNode.path("products");
            products = objectMapper.readValue(productsNode.toString(), new TypeReference<List<Product>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }
}


