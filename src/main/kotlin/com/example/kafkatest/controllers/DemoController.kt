package com.example.kafkatest.controllers

import com.example.kafkatest.requests.Product
import com.example.kafkatest.requests.ProductMessage
import com.example.kafkatest.services.ProductService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
@Service
class DemoController(private val productService: ProductService) {

    private val log = LoggerFactory.getLogger(DemoController::class.java)

    @GetMapping("/products")
    fun sayHello(@RequestParam(value = "myName", defaultValue = "World") name: String): String {

        return String.format("Hello $name!")
    }

    @PostMapping("/product")
    fun addProduct(@RequestBody product: Product): ResponseEntity<Any> {
        log.info("[DemoController]: add new product = $product")
        productService.sendMessage(ProductMessage(product, "add"))

        val savedProduct = productService.saveProduct(product)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct)
    }

    @DeleteMapping("/product/{id}")
    fun deleteProduct(@PathVariable id: String) {
        log.info("[DemoController]: delete product id = $id")
        val p = Product(id, "", 0.0)
        productService.sendMessage(ProductMessage(p, "delete"))

    }
}
