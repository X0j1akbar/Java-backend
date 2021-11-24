package uz.pdp.exesises.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.exesises.entity.Product;
import uz.pdp.exesises.payload.ApiResponse;
import uz.pdp.exesises.payload.ProductDto;
import uz.pdp.exesises.repository.CategoryRepository;
import uz.pdp.exesises.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse saveOrEdit(ProductDto dto){
        Product product=new Product();
        try {
            if (dto.getId()!=null){
                product=productRepository.getById(dto.getId());
            }
            if (dto.getCategoryId()!=null){
                product.setCategory(categoryRepository.getById(dto.getCategoryId()));
            }
            product.setDescription(dto.getDescription());
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            product.setPhoto(dto.getPhoto());

            productRepository.save(product);

            return new ApiResponse(dto.getId()!=null?"edited":"saved",true);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("error",false);
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

    public ProductDto getById(Integer id){
      return  getProductDto(productRepository.getById(id));
    }




    public ProductDto getProductDto(Product product){
        ProductDto dto=new ProductDto();

        dto.setDescription(product.getDescription());
        if (product.getCategory()!=null){
            dto.setCategory(product.getCategory());
        }

        dto.setName(product.getName());
        dto.setPhoto(product.getPhoto());
        dto.setPrice(product.getPrice());
        dto.setId(product.getId());
        return dto;
    }

}
