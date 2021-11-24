package uz.pdp.srmserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.pdp.srmserver.entitiy.GivenSalary;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.GivenSalaryDto;
import uz.pdp.srmserver.repository.GivenSalaryRepository;
import uz.pdp.srmserver.repository.UserRepository;
import uz.pdp.srmserver.utils.CommonUtills;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GivenSalaryService {

    @Autowired
    GivenSalaryRepository givenSalaryRepository;

    @Autowired
    UserRepository userRepository;

    public ApiResponse save(GivenSalaryDto dto){

        try {
            GivenSalary givenSalary=new GivenSalary();
            givenSalary.setPayType(dto.getPayType());
            givenSalary.setSum(dto.getSum());
            givenSalary.setEmployee(userRepository.getById(dto.getEmployeeId()));
            givenSalary.setApproved(dto.isApproved());

            givenSalaryRepository.save(givenSalary);
            return new ApiResponse("Saved",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }

    public ApiResponse edit(GivenSalaryDto dto, UUID id){
        try {
            GivenSalary givenSalary=givenSalaryRepository.getById(id);

            givenSalary.setSum(dto.getSum());
            givenSalary.setPayType(dto.getPayType());
            givenSalary.setEmployee(userRepository.getById(dto.getEmployeeId()));
            givenSalary.setApproved(dto.isApproved());

            givenSalaryRepository.save(givenSalary);
            return new ApiResponse("Edited",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }

    public ApiResponse getAll(Integer page, Integer size, String startDate, String endDate,Boolean approved){
     try {
         Page<GivenSalary>givenSalaryPage=givenSalaryRepository.findAllByApprovedAndCreatedAtBetween(approved, CommonUtills.getDateFromString(startDate),CommonUtills.getDateFromString(endDate),CommonUtills.getPageableByCreatedAtDesc(page, size));
         return new ApiResponse(true,"ok",givenSalaryPage.getContent().stream().map(this::getGivenSalaryDto).collect(Collectors.toList()),givenSalaryPage.getTotalElements());

     }catch (Exception e){
         e.printStackTrace();
     }
     return new ApiResponse("Error",false);
    }

    public GivenSalary getOne(UUID id){
        return givenSalaryRepository.getById(id);
    }

    public ApiResponse delete(UUID id){

        try {
            givenSalaryRepository.deleteById(id);
            return new ApiResponse("Deleted!",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }

    public GivenSalaryDto getGivenSalaryDto(GivenSalary givenSalary){
        GivenSalaryDto dto=new GivenSalaryDto();

        dto.setApproved(givenSalary.isApproved());
        dto.setEmployee(givenSalary.getEmployee());
        dto.setSum(givenSalary.getSum());
        dto.setPayType(givenSalary.getPayType());
        dto.setId(givenSalary.getId());
        return dto;
    }

}
