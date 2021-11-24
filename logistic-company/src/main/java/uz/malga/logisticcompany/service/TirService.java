package uz.malga.logisticcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.malga.logisticcompany.entity.Dazvol;
import uz.malga.logisticcompany.entity.Tir;
import uz.malga.logisticcompany.payload.ApiResponse;
import uz.malga.logisticcompany.payload.TirDto;
import uz.malga.logisticcompany.repository.CompanyRepository;
import uz.malga.logisticcompany.repository.TirRepository;
import uz.malga.logisticcompany.utils.CommonUtills;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TirService {


    @Autowired
    TirRepository tirRepository;

    @Autowired
    CompanyRepository companyRepository;

    public ApiResponse save(TirDto dto){
        try {

            if (dto.getTirNumber2()!=null){
                int m1=Integer.parseInt(dto.getTirNumber());
                int m2=Integer.parseInt(dto.getTirNumber2());
                List<Tir> tirs=new ArrayList<>();
                for (int i=m1; i<=m2; i++){
                    Tir tir=new Tir();
                    tir.setFromDate(new Date(new java.util.Date().getTime()));
                    tir.setTirNumber(i+"");
                    tir.setCompany(companyRepository.getById(dto.getCompanyId()));
                    tir.setList(dto.getList());
                    tir.setActive(true);
                    tir.setExperienceDate(dto.getExperienceDate());
                    tirs.add(tir);
                }
                tirRepository.saveAll(tirs);

            }else {

                Tir tir=new Tir();

                if (dto.getId()!=null){

                    tir.setActive(dto.isActive());
                    tir.setCompany(companyRepository.getById(dto.getCompanyId()));
                    tir.setTirNumber(dto.getTirNumber());
                    tir.setExperienceDate(dto.getExperienceDate());
                    tir.setList(dto.getList());

                    tirRepository.save(tir);
                }
            }
            return new ApiResponse("saved", true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }


    public ApiResponse allByPageable(Integer page, Integer size, String search,boolean active,Long companyId) throws IllegalAccessException {
        List<Tir> tirs = new ArrayList<>();
        long totalElements = 0;
        if (!search.equals("all")) {
            tirs = tirRepository.findAllByTirNumberContainingIgnoringCase(search);
        }else if (companyId!=0){
            tirs = tirRepository.findAllByActiveAndCompanyId(active,companyId);
        }else {
            if (size > 0) {
                Page<Tir> dazvolPage = tirRepository.findAll(CommonUtills.getPageableByCreatedAtDesc(page, size));
                tirs = dazvolPage.getContent();
                totalElements = dazvolPage.getTotalElements();
            } else {
                tirs = tirRepository.findAll();
            }
        }
        return new ApiResponse(true, "Dazvol", tirs.stream().map(this::getTirDto).collect(Collectors.toList()), totalElements);
    }

    public TirDto getOne(UUID id){
        return getTirDto(tirRepository.getById(id));
    }

    public ApiResponse getAllByActive(Long companyId){
        return new ApiResponse(true,"ok",tirRepository.findAllByActiveAndCompanyId(true,companyId));
    }

    public ApiResponse delete(UUID id){
        try {
            tirRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error", false);
    }

    public ApiResponse changeActive(UUID id) {
        Tir byId = tirRepository.getById(id);
        byId.setActive(!byId.isActive());
        tirRepository.save(byId);
        return new ApiResponse(byId.isActive() ? "Activated" : "Blocked", true);
    }

    public TirDto getTirDto(Tir tir){
        TirDto dto=new TirDto();

        dto.setActive(tir.isActive());
        dto.setCompany(tir.getCompany());
        dto.setList(tir.getList());
        dto.setTirNumber(tir.getTirNumber());
        dto.setFromDate(tir.getFromDate());
        dto.setId(tir.getId());
        dto.setExperienceDate(tir.getExperienceDate());
        return dto;
    }

}
