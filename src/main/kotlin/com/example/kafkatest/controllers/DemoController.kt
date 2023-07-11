package com.example.kafkatest.controllers

import com.example.kafkatest.requests.Product
import com.example.kafkatest.requests.ProductMessage
import com.example.kafkatest.services.ProductService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class DemoController(private val productService: ProductService) {

    private val log = LoggerFactory.getLogger(DemoController::class.java)

    @GetMapping("/products")
    fun getAllProducts(): ResponseEntity<List<Product>> = ResponseEntity.ok(productService.getAllProducts())

    // TODO: catch nullpointerexception
    @GetMapping("/product/{id}")
    fun getProductbyId(@PathVariable id: String): ResponseEntity<Product> = ResponseEntity.ok(productService.getProductById(id))

    // TODO:
    @PostMapping("/product")
    fun addProduct(@RequestBody product: Product): ResponseEntity<Any> {
        log.info("[DemoController]: add new product = $product")
        productService.sendMessage(ProductMessage(product, "add"))

        val savedProduct = productService.saveProduct(product)
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct)
    }

    // TODO: Check if id exists
    @DeleteMapping("/product/{id}")
    fun deleteProduct(@PathVariable id: String) {
        log.info("[DemoController]: delete product id = $id")
        val product = productService.getProductById(id)

        productService.sendMessage(ProductMessage(product, "delete"))
        productService.deleteProduct(id)
    }
}
