package uz.pdp.srmserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.srmserver.entitiy.Bonus;
import uz.pdp.srmserver.entitiy.User;
import uz.pdp.srmserver.payload.ApiResponse;
import uz.pdp.srmserver.payload.BonusDto;
import uz.pdp.srmserver.repository.BonusRepository;
import uz.pdp.srmserver.repository.UserRepository;
import uz.pdp.srmserver.utils.CommonUtills;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BonusService {

    @Autowired
    BonusRepository bonusRepository;

    @Autowired
    UserRepository userRepository;

    public ApiResponse save(BonusDto dto){

        try {
            Bonus bonus=new Bonus();
            String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(username);
            bonus.setUser(user);
            bonus.setBonusSum(dto.getBonusSum());
            bonus.setDescription(dto.getDescription());
            bonus.setApproved(dto.isApproved());

            bonusRepository.save(bonus);

            return new ApiResponse("Saved",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }

    public ApiResponse edit(UUID id,BonusDto dto){
        Bonus bonus=bonusRepository.getById(id);
        try {
            String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userRepository.findByUsername(username);
            bonus.setUser(user);
            bonus.setDescription(bonus.getDescription());
            bonus.setApproved(dto.isApproved());
            bonus.setBonusSum(dto.getBonusSum());

            bonusRepository.save(bonus);

            return new ApiResponse("Edited",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }

    public ApiResponse getAll(int page, int size, String startDate, String endDate, Boolean approved) throws IllegalAccessException {
        try {
            Page<Bonus> bonusPage = bonusRepository.findAllByApprovedAndCreatedAtBetween(approved,CommonUtills.getDateFromString(startDate),CommonUtills.getDateFromString(endDate),CommonUtills.getPageableByCreatedAtDesc(page, size));
            return new ApiResponse(true,"Ok",bonusPage.getContent().stream().map(this::getBonusDto).collect(Collectors.toList()),bonusPage.getTotalElements());
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }

    public Bonus getOne(UUID id){
        return bonusRepository.getById(id);
    }

    public ApiResponse delete(UUID id){
        try {
            bonusRepository.deleteById(id);
            return new ApiResponse("Deleted!",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }

    public BonusDto getBonusDto(Bonus bonus){
        BonusDto dto = new BonusDto();
        dto.setId(bonus.getId());
        dto.setUser(bonus.getUser());
        dto.setBonusSum(bonus.getBonusSum());
        dto.setDescription(bonus.getDescription());
        dto.setApproved(bonus.isApproved());
        return dto;
    }



}
