package uz.pdp.srmserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.srmserver.entitiy.Category;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.CategoryDto;
import uz.pdp.srmserver.repository.CategoryRepository;
import uz.pdp.srmserver.utils.CommonUtills;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse saveOrEdit(CategoryDto dto) {
        try {
            Category category = new Category();
            if (dto.getId() != null) {
                category = categoryRepository.getById(dto.getId());
            }
            if (dto.getParentDto() != null&&dto.getParentDto().getId()>0) {
                category.setParent(categoryRepository.getById(dto.getParentDto().getId()));
            }

            category.setName(dto.getName());
            category.setActive(dto.getId() == null || category.isActive());
            category.setDescription(dto.getDescription());
            categoryRepository.save(category);
            return new ApiResponse(dto.getId() != null ? "Edited" : "Saved", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse("Error", false);
    }

    public ApiResponse allByPageable(Integer page, Integer size, String search) throws IllegalAccessException {
        List<Category> categories = new ArrayList<>();
        long totalElements = 0;
        if (!search.equals("all")) {
            categories = categoryRepository.findAllByNameContainingIgnoringCase(search);
        } else {
            if (size > 0) {
                Page<Category> categoryPage = categoryRepository.findAll(CommonUtills.getPageableByIdDesc(page, size));
                categories = categoryPage.getContent();
                totalElements = categoryPage.getTotalElements();
            } else {
                categories = categoryRepository.findAll();
            }
        }
        return new ApiResponse(true, "CategoryPage", categories.stream().map(this::getCategoryDto).collect(Collectors.toList()), totalElements);
    }

    public ApiResponse getAll(){
        List<Category> products=new ArrayList();

        products=categoryRepository.findAll();

        return new ApiResponse(true, "Product", products.stream().map(this::getCategoryDto).collect(Collectors.toList()));
    }

    public CategoryDto getCategoryDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());
        dto.setActive(category.isActive());
        if (category.getParent() != null) {
            dto.setParentDto(getCategoryDto(category.getParent()));
        }
        return dto;
    }

    public ApiResponse changeActive(Integer id) {
        Category byId = categoryRepository.getById(id);
        byId.setActive(!byId.isActive());
        categoryRepository.save(byId);
        return new ApiResponse(byId.isActive() ? "Activated" : "Blocked", true);
    }



    public ApiResponse remove(Integer id) {
        categoryRepository.deleteById(id);
        return new ApiResponse("Ok",true);
    }
}
