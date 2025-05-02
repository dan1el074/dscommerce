package br.com.devsuperior.dscommerce.controllers;

import br.com.devsuperior.dscommerce.dto.ProductDto;
import br.com.devsuperior.dscommerce.dto.ProductMinDto;
import br.com.devsuperior.dscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDto>> findAll(Pageable pageable) {
        Page<ProductDto> dtos = productService.findAll(pageable);
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        ProductDto dto = productService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<Page<ProductMinDto>> searchByName(@RequestParam(defaultValue = "") String name, Pageable pageable) {
        Page<ProductMinDto> dtos = productService.searchByName(name, pageable);
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<ProductDto> insert(@Valid @RequestBody ProductDto dto) {
        dto = productService.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDto> update(@Valid @RequestBody ProductDto dto, @PathVariable Long id) {
        dto = productService.update(dto, id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
