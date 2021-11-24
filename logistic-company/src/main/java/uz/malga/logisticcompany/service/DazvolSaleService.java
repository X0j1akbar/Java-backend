package uz.malga.logisticcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.malga.logisticcompany.entity.Dazvol;
import uz.malga.logisticcompany.entity.DazvolSale;
import uz.malga.logisticcompany.entity.User;
import uz.malga.logisticcompany.payload.ApiResponse;
import uz.malga.logisticcompany.payload.DazvolSaleDto;
import uz.malga.logisticcompany.repository.CompanyRepository;
import uz.malga.logisticcompany.repository.DazvolRepository;
import uz.malga.logisticcompany.repository.DazvolSaleRepository;
import uz.malga.logisticcompany.repository.UserRepository;
import uz.malga.logisticcompany.utils.CommonUtills;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DazvolSaleService {

    @Autowired
    DazvolSaleRepository dazvolSaleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DazvolRepository dazvolRepository;

    @Autowired
    CompanyRepository companyRepository;

    public ApiResponse saveOrEdit(DazvolSaleDto dto){
//        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User seller = userRepository.findByUsername(username);
        DazvolSale dazvolSale=new DazvolSale();
        List<Dazvol>dazvols=new ArrayList<>();
        try {
            for (UUID dazvolId : dto.getDazvolIds()) {
                Dazvol byId = dazvolRepository.getById(dazvolId);
                byId.setDazvolName(byId.getDazvolName());
                byId.setFromDate(byId.getFromDate());
                byId.setDazvolNumber(byId.getDazvolNumber());
                byId.setCompany(byId.getCompany());
                byId.setExperienceDate(byId.getExperienceDate());
                byId.setActive(false);
                dazvolRepository.save(byId);
                dazvols.add(byId);
            }
            dazvolSale.setDazvols(dazvols);
            dazvolSale.setDate(new Date(new java.util.Date().getTime()));
            dazvolSale.setCarNumber(dto.getCarNumber());
            dazvolSale.setCompany(companyRepository.getById(dto.getCompanyId()));
//            dazvolSale.setCustomer(seller);
            dazvolSale.setUserName(dto.getUserName());
            dazvolSaleRepository.save(dazvolSale);
            return new ApiResponse("Sotildi!",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }

    public ApiResponse allByPageable(Integer page, Integer size, String search,Long companyId) throws IllegalAccessException {
        List<DazvolSale> dazvols = new ArrayList<>();
        long totalElements = 0;
        if (!search.equals("all")) {
            dazvols = dazvolSaleRepository.findAllByCarNumberContainingIgnoringCase(search);
        }else if (companyId!=0){
            Page<DazvolSale> dazvolSales=dazvolSaleRepository.findAllByCompanyId(companyId,CommonUtills.getPageableByIdDesc(page,size));
            dazvols=dazvolSales.getContent();
            totalElements=dazvolSales.getTotalElements();
        }else {
            if (size > 0) {
                Page<DazvolSale> dazvolPage = dazvolSaleRepository.findAll(CommonUtills.getPageableByIdDesc(page, size));
                dazvols = dazvolPage.getContent();
                totalElements = dazvolPage.getTotalElements();
            } else {
                dazvols = dazvolSaleRepository.findAll();
            }
        }
        return new ApiResponse(true, "Dazvol", dazvols.stream().map(this::getDazvolSaleDto).collect(Collectors.toList()), totalElements);
    }

    public ApiResponse delete(List<UUID> id){
        try {
            dazvolSaleRepository.deleteAllById(id);
            return new ApiResponse("deleted",true);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }


    public DazvolSaleDto getDazvolSaleDto(DazvolSale dazvolSale){
        DazvolSaleDto dto=new DazvolSaleDto();
        dto.setCompany(dazvolSale.getCompany());
        dto.setList(dazvolSale.getDazvols());
        dto.setDate(dazvolSale.getDate());
        dto.setCustomer(userRepository.getById(dazvolSale.getCreatedBy()));
        dto.setCarNumber(dazvolSale.getCarNumber());
        dto.setUserName(dazvolSale.getUserName());
        return dto;

    }

}



