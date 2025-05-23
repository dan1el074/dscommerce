package br.com.devsuperior.dscommerce.services;

import br.com.devsuperior.dscommerce.dto.CategoryDto;
import br.com.devsuperior.dscommerce.dto.ProductDto;
import br.com.devsuperior.dscommerce.dto.ProductMinDto;
import br.com.devsuperior.dscommerce.entitites.Category;
import br.com.devsuperior.dscommerce.entitites.Product;
import br.com.devsuperior.dscommerce.repositories.ProductRepository;
import br.com.devsuperior.dscommerce.repositories.projections.ProductMinProjection;
import br.com.devsuperior.dscommerce.services.exceptions.DatabaseException;
import br.com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<ProductMinDto> findAll(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductMinDto::new);
    }

    @Transactional(readOnly = true)
    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return new ProductDto(product);
    }

    @Transactional(readOnly = true)
    public Page<ProductMinDto> searchByName(String nome, Pageable pageable) {
        Page<ProductMinProjection> projections = productRepository.searchByName(nome, pageable);
        return projections.map(ProductMinDto::new);
    }

    @Transactional
    public ProductDto insert(ProductDto dto) {
        Product product = new Product();
        dtoToEntity(dto, product);
        product = productRepository.save(product);
        return new ProductDto(product);
    }

    @Transactional
    public ProductDto update(ProductDto dto, Long id) {
        try {
            Product product = productRepository.getReferenceById(id);
            dtoToEntity(dto, product);
            product = productRepository.save(product);
            return new ProductDto(product);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException();
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!productRepository.existsById(id)) {
            throw new ResourceNotFoundException();
        }
        try {
            productRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException();
        }
    }

    private void dtoToEntity(ProductDto dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());

        entity.getCategories().clear();
        for (CategoryDto categoryDto : dto.getCategories()) {
            Category category = new Category();
            category.setId(categoryDto.getId());
            entity.getCategories().add(category);
        }
    }
}
