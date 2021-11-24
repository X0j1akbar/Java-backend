package uz.pdp.srmserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.srmserver.entitiy.Customer;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.CustomerDto;
import uz.pdp.srmserver.repository.CustomerRepository;
import uz.pdp.srmserver.repository.UserRepository;
import uz.pdp.srmserver.utils.CommonUtills;

import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    UserRepository userRepository;

    public ApiResponse save(CustomerDto dto) {

        try {

            Customer customer = new Customer();
            customer.setAddress(dto.getAddress());
            customer.setLat(dto.getLat());
            customer.setActive(dto.isActive());
            customer.setDescription(dto.getDescription());
            customer.setName(dto.getName());

            customerRepository.save(customer);
            return new ApiResponse("Saved", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse("error", false);
    }

    public ApiResponse edit(Integer id, CustomerDto dto){
        try {
            Customer customer = customerRepository.getById(id);
            customer.setLat(dto.getLat());
            customer.setActive(dto.isActive());
            customer.setDescription(dto.getDescription());
            customer.setName(dto.getName());
            customer.setAddress(dto.getAddress());

            customerRepository.save(customer);
            return new ApiResponse("Edited", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse("error", false);
    }


    public Customer getOne(Integer id) {
        return customerRepository.getById(id);
    }

    public ApiResponse delete(Integer id) {
        try {
            customerRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse("Error!", false);
    }


    public ApiResponse getAll(Integer page, Integer size, Boolean active) throws IllegalAccessException {

        Page<Customer> customerPage = customerRepository.findByActive(active, CommonUtills.simplePageable(page, size));
        return new ApiResponse(true, "OK", customerPage.getContent().stream().map(this::getCustomerDto).collect(Collectors.toList()), customerPage.getTotalElements());

    }


    public CustomerDto getCustomerDto(Customer customer) {

        CustomerDto dto = new CustomerDto();
        dto.setDescription(customer.getDescription());
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setAddress(customer.getAddress());
        dto.setLon(customer.getLon());
        dto.setLat(customer.getLat());
        dto.setPhoneNumber(dto.getPhoneNumber());
        dto.setActive(dto.isActive());
        return dto;
    }

}
