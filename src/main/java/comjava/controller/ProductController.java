package comjava.controller;

import comjava.annotiation.GetMapping;
import comjava.annotiation.RequestMapping;
import comjava.annotiation.RestController;
import comjava.entity.Product;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/detail")
    public Product getProductDetail() {
        return new Product(1, "product name");
    }
}
