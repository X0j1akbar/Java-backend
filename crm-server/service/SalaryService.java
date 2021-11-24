package uz.pdp.srmserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.srmserver.entitiy.Salary;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.SalaryDto;
import uz.pdp.srmserver.repository.SalaryRepository;
import uz.pdp.srmserver.repository.UserRepository;
import uz.pdp.srmserver.utils.CommonUtills;
import java.text.ParseException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SalaryService {

    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    UserRepository userRepository;


    public ApiResponse save(SalaryDto dto) {

        try {
            Salary salary = new Salary();
            salary.setUser(userRepository.getById(dto.getUserId()));
            salary.setToDate(dto.getToDate());
            salary.setFromDate(dto.getFromDate());
            salary.setSales(dto.getSales());
            salary.setTotalSalary(dto.getTotalSalary());
            salary.setShopKpi(dto.getShopKpi());
            salaryRepository.save(salary);
            return new ApiResponse( "Saved", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse("Error", false);
    }

    public ApiResponse edit(UUID id,SalaryDto dto){

        try {
            Salary salary=salaryRepository.getById(id);
            salary.setToDate(dto.getToDate());
            salary.setUser(userRepository.getById(dto.getUserId()));
            salary.setFromDate(dto.getFromDate());
            salary.setSales(dto.getSales());
            salary.setTotalSalary(dto.getTotalSalary());
            salary.setShopKpi(dto.getShopKpi());
            salaryRepository.save(salary);
            return new ApiResponse("Edited" , true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse("Error", false);
    }

    public ApiResponse getAll(Integer page, Integer size, String startDate, String endDate) throws ParseException, IllegalAccessException {
        try {
            Page<Salary> salaryPage = salaryRepository.findAllByCreatedAtBetween(CommonUtills.getDateFromString(startDate), CommonUtills.getDateFromString(endDate), CommonUtills.getPageableByCreatedAtDesc(page, size));
            return new ApiResponse(true, "Ok", salaryPage.getContent().stream().map(this::getSalaryDto).collect(Collectors.toList()), salaryPage.getTotalElements());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("error",false);
    }

    public Salary getOne(UUID id) {
        return salaryRepository.getById(id);
    }

    public ApiResponse delete(UUID id) {

        try {
            salaryRepository.deleteById(id);
            return new ApiResponse("Deleted!", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse("Error", false);
    }

    public SalaryDto getSalaryDto(Salary salary) {

        SalaryDto dto = new SalaryDto();
        dto.setSales(salary.getSales());
        dto.setTotalSalary(salary.getTotalSalary());
        dto.setFromDate(salary.getFromDate());
        dto.setToDate(salary.getToDate());
        dto.setShopKpi(salary.getShopKpi());
        dto.setUser(salary.getUser());
        dto.setId(salary.getId());
        return dto;
    }

}
