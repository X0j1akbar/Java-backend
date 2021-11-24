package uz.pdp.srmserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.srmserver.entitiy.Attechment;
import uz.pdp.srmserver.entitiy.Product;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.ProductDto;
import uz.pdp.srmserver.repository.AttachmentRepository;
import uz.pdp.srmserver.repository.CategoryRepository;
import uz.pdp.srmserver.repository.ProductRepository;
import uz.pdp.srmserver.utils.CommonUtills;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    public ApiResponse save(ProductDto productDto){
        Product product=new Product();
        List<Attechment> attechments=new ArrayList<>();
        attechments.add(attachmentRepository.getById(productDto.getPhotoId()));
        try {
           if (productDto.getId()!=null){
               product=productRepository.getById(productDto.getId());
           }
            product.setActive(productDto.isActive());
            product.setCategory(categoryRepository.getById(productDto.getCategoryId()));
            product.setDescription(productDto.getDescription());
            product.setExpired(productDto.isExpired());
            product.setName(productDto.getName());
            product.setPhotos(attechments);
            product.setIncomePrice(productDto.getIncomePrice());
            product.setNorma(productDto.getNorma());
            product.setSalePrice(productDto.getSalePrice());

            productRepository.save(product);
            return new ApiResponse(productDto.getId() != null ? "Edited" : "Saved", true);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }


    public Product getOne(UUID id){
        return productRepository.getById(id);
    }

    public ApiResponse delete(UUID id){
        try {
            productRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("error!",false);
    }


    public ApiResponse getAll(int page, int size,String search) throws IllegalAccessException {
        List<Product> products = new ArrayList<>();
        long totalElements = 0;
        if (!search.equals("all")) {
            products = productRepository.findAllByNameContainingIgnoringCase(search);
        } else {
            if (size > 0) {
                Page<Product> categoryPage = productRepository.findAll(CommonUtills.getPageableByIdDesc(page, size));
                products = categoryPage.getContent();
                totalElements = categoryPage.getTotalElements();
            } else {
                products = productRepository.findAll();
            }
        }
        return new ApiResponse(true, "Product Page", products.stream().map(this::getProductDto).collect(Collectors.toList()), totalElements);
    }


    public ApiResponse changeActive(UUID id) {
        Product byId = productRepository.getById(id);
        byId.setActive(!byId.isActive());
        productRepository.save(byId);
        return new ApiResponse(byId.isActive() ? "Activated" : "Blocked", true);
    }
    public ApiResponse changeExpired(UUID id) {
        Product byId = productRepository.getById(id);
        byId.setExpired(!byId.isExpired());
        productRepository.save(byId);
        return new ApiResponse(byId.isActive() ? "Activated" : "Blocked", true);
    }
    public ProductDto getProductDto(Product product){
        ProductDto productDto=new ProductDto();
        productDto.setActive(product.isActive());
        if (product.getPhotos()!=null){
            productDto.setPhotoId(product.getPhotos().get(0).getId());
        }
        productDto.setCategory(CommonUtills.getCategoryDto(product.getCategory()));
        productDto.setDescription(product.getDescription());
        productDto.setExpired(product.isExpired());
        productDto.setName(product.getName());
        productDto.setIncomePrice(product.getIncomePrice());
        productDto.setNorma(product.getNorma());
        productDto.setSalePrice(product.getSalePrice());
        productDto.setId(product.getId());

        return productDto;
    }


}
