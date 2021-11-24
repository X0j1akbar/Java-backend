package uz.malga.logisticcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.malga.logisticcompany.entity.*;
import uz.malga.logisticcompany.payload.ApiResponse;
import uz.malga.logisticcompany.payload.DazvolSaleDto;
import uz.malga.logisticcompany.payload.TirSaleDto;
import uz.malga.logisticcompany.repository.*;
import uz.malga.logisticcompany.utils.CommonUtills;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TirSaleService {

    @Autowired
    TirRepository tirRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TirSaleRepository tirSaleRepository;

    @Autowired
    CompanyRepository companyRepository;

    public ApiResponse saveOrEdit(TirSaleDto dto){
        TirSale dazvolSale=new TirSale();
        List<Tir>tirs=new ArrayList<>();
        try {
            for (UUID dazvolId : dto.getTirIds()) {
                Tir byId = tirRepository.getById(dazvolId);
                byId.setList(byId.getList());
                byId.setFromDate(byId.getFromDate());
                byId.setTirNumber(byId.getTirNumber());
                byId.setCompany(byId.getCompany());
                byId.setExperienceDate(byId.getExperienceDate());
                byId.setActive(false);
                tirRepository.save(byId);
                tirs.add(byId);
            }
            dazvolSale.setTirs(tirs);
            dazvolSale.setDate(new Date(new java.util.Date().getTime()));
            dazvolSale.setCarNumber(dto.getCarNumber());
            dazvolSale.setCompany(companyRepository.getById(dto.getCompanyId()));
            dazvolSale.setUserName(dto.getUserName());
            tirSaleRepository.save(dazvolSale);
            return new ApiResponse("Sotildi!",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }

    public ApiResponse allByPageable(Integer page, Integer size, String search,Long companyId) throws IllegalAccessException {
        List<TirSale> dazvols = new ArrayList<>();
        long totalElements = 0;
        if (!search.equals("all")) {
            dazvols = tirSaleRepository.findAllByCarNumberContainingIgnoringCase(search);
        }else if (companyId!=0){
            Page<TirSale> dazvolSales=tirSaleRepository.findAllByCompanyId(companyId,CommonUtills.getPageableByIdDesc(page,size));
            dazvols=dazvolSales.getContent();
            totalElements=dazvolSales.getTotalElements();
        }else {
            if (size > 0) {
                Page<TirSale> dazvolPage = tirSaleRepository.findAll(CommonUtills.getPageableByIdDesc(page, size));
                dazvols = dazvolPage.getContent();
                totalElements = dazvolPage.getTotalElements();
            } else {
                dazvols = tirSaleRepository.findAll();
            }
        }
        return new ApiResponse(true, "Dazvol", dazvols.stream().map(this::getDazvolSaleDto).collect(Collectors.toList()), totalElements);
    }

    public ApiResponse delete(List<UUID> id){
        try {
            tirSaleRepository.deleteAllById(id);
            return new ApiResponse("deleted",true);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }


    public TirSaleDto getDazvolSaleDto(TirSale dazvolSale){
        TirSaleDto dto=new TirSaleDto();
        dto.setCustomer(userRepository.getById(dazvolSale.getCreatedBy()));
        dto.setCompany(dazvolSale.getCompany());
        dto.setTirs(dazvolSale.getTirs());
        dto.setDate(dazvolSale.getDate());
        dto.setCarNumber(dazvolSale.getCarNumber());
        dto.setUserName(dazvolSale.getUserName());
        return dto;

    }

}



