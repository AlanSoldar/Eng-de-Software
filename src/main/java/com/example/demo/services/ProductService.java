package com.example.demo.services;

import com.example.demo.entities.Product;
import com.example.demo.entities.Product;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> findAllProducts(Pageable page) {

        Page<Product> products = productRepository.findAll(page);
        System.out.println("retornando todos os produtos da pagina " + page.getPageNumber());

        if (products.isEmpty()) {
            throw new NotFoundException("No products were found");
        }

        return products;

    }

    public Product findProductById(Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("product not found"));
        System.out.println("retornando produto com id = " + id.toString());

        return product;

    }

    public List<Product> findProductByName(String nome) {

        List<Product> products = productRepository.findByNome(nome);
        System.out.println("retornando lista de produtos com o nome = " + nome);

        return products;

    }

    public void saveProduct(Product product) {

        productRepository.save(product);
        System.out.println("produto salvo");

    }

    public void deleteProductById(Long id) {

        productRepository.deleteById(id);
        System.out.println("deletando produto com id = " + id.toString());

    }
}
