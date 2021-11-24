package uz.pdp.exesises.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.exesises.entity.Category;
import uz.pdp.exesises.entity.Product;
import uz.pdp.exesises.payload.ApiResponse;
import uz.pdp.exesises.payload.CategoryDto;
import uz.pdp.exesises.repository.CategoryRepository;
import uz.pdp.exesises.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    public ApiResponse saveOrEdit(CategoryDto dto) {
        Category category = new Category();
        try {
            if (dto.getId() != null) {
                category = categoryRepository.getById(dto.getId());
            }
            category.setName(dto.getName());

            categoryRepository.save(category);
            return new ApiResponse(dto.getId() != null ? "edited" : "saved", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse("error", false);
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public CategoryDto getCategoryByProductId(Integer productId) {
        Product product = productRepository.getById(productId);
        return getCategoryDto(product.getCategory());
    }

    public CategoryDto getCategoryDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setName(category.getName());
        dto.setId(category.getId());
        return dto;
    }

}
