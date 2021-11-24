package uz.malga.logisticcompany.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.malga.logisticcompany.entity.Company;
import uz.malga.logisticcompany.entity.Dazvol;
import uz.malga.logisticcompany.entity.DazvolsName;
import uz.malga.logisticcompany.payload.ApiResponse;
import uz.malga.logisticcompany.payload.DazvolDto;
import uz.malga.logisticcompany.repository.CompanyRepository;
import uz.malga.logisticcompany.repository.DazvolRepository;
import uz.malga.logisticcompany.repository.DazvolsNameRepository;
import uz.malga.logisticcompany.utils.CommonUtills;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DazvolService {

    @Autowired
    DazvolRepository dazvolRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    DazvolsNameRepository dazvolsNameRepository;

    public ApiResponse saveOrEdit(DazvolDto dto){
        try {

            if (dto.getDazvolNumber2()!=null){
                int m1=Integer.parseInt(dto.getDazvolNumber());
                int m2=Integer.parseInt(dto.getDazvolNumber2());
                System.out.println(m2);
                List<Dazvol>dazvols=new ArrayList<>();
                for (int i=m1; i<=m2; i++){
                    Dazvol dazvol=new Dazvol();
                    dazvol.setDazvolNumber(""+i);
                    dazvol.setActive(true);
                    dazvol.setFromDate(new Date(new java.util.Date().getTime()));
                    dazvol.setCompany(companyRepository.getById(dto.getCompanyId()));
                    dazvol.setDazvolName(dazvolsNameRepository.getById(dto.getDazvolNameId()));
                    dazvol.setExperienceDate(dto.getExperienceDate());
                    dazvols.add(dazvol);
                }
                dazvolRepository.saveAll(dazvols);

            }else {
                Dazvol dazvol=new Dazvol();
                if (dto.getId() != null) {
                    dazvol = dazvolRepository.getById(dto.getId());
                }

                dazvol.setDazvolNumber(dto.getDazvolNumber());
                dazvol.setActive(true);
                dazvol.setFromDate(new Date(new java.util.Date().getTime()));
                dazvol.setCompany(companyRepository.getById(dto.getCompanyId()));
                dazvol.setDazvolName(dazvolsNameRepository.getById(dto.getDazvolNameId()));
                dazvol.setExperienceDate(dto.getExperienceDate());
                dazvolRepository.save(dazvol);
            }
            return new ApiResponse("saved", true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }

    public ApiResponse allByPageable(Integer page, Integer size, String search,boolean active,Long companyId) throws IllegalAccessException {
        List<Dazvol>dazvols=new ArrayList<>();
        long totalElements=0;
        if (!search.equals("all")) {
            dazvols = dazvolRepository.findAllByDazvolNumberContainingIgnoringCase(search);
        }else if (companyId!=0){
            Page<Dazvol> dazvolPage = dazvolRepository.findAllByActiveAndCompanyId(CommonUtills.getPageableByIdDesc(page,size),active,companyId);
            dazvols = dazvolPage.getContent();
            totalElements = dazvolPage.getTotalElements();
        }else {
            if (size > 0) {
                Page<Dazvol> dazvolPage = dazvolRepository.findAll(CommonUtills.getPageableByIdDesc(page, size));
                dazvols = dazvolPage.getContent();
                totalElements = dazvolPage.getTotalElements();
            } else {
                dazvols = dazvolRepository.findAll();
            }
        }
        return new ApiResponse(true, "Dazvol", dazvols.stream().map(this::getDazvolDto).collect(Collectors.toList()),totalElements);
    }

    public ApiResponse getAllByActive(Long companyId, Long dazvolNameId ){
        return new ApiResponse(true,"ok",dazvolRepository.findAllByActiveAndCompanyIdAndDazvolNameId(true,companyId,dazvolNameId));
    }

    public List<DazvolsName> getAllDazvolsName(){
        return dazvolsNameRepository.findAll();
    }

    public List<Company> getAllCompany(){
        return companyRepository.findAll();
    }

    public Dazvol getOne(UUID id){
        return dazvolRepository.getById(id);
    }

    public ApiResponse sum(Long companyId){
        Company company = companyRepository.getById(companyId);
        List<Integer>dazvolPrice=new ArrayList<>();
        for (DazvolsName dazvolsName : dazvolsNameRepository.findAll()) {
        dazvolPrice.add((dazvolRepository.findAllByCompanyAndDazvolName(company,dazvolsName)).size());
        }

        return new ApiResponse(true,"Succes",dazvolPrice);
    }

    public ApiResponse delete(UUID id){
        try {
            dazvolRepository.deleteById(id);
            return new ApiResponse("Deleted",true);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ApiResponse("Error",false);
    }
    public ApiResponse changeActive(UUID id) {
        Dazvol byId = dazvolRepository.getById(id);
        byId.setActive(!byId.isActive());
        dazvolRepository.save(byId);
        return new ApiResponse(byId.isActive() ? "Activated" : "Blocked", true);
    }

    public DazvolDto getDazvolDto(Dazvol dazvol){
        DazvolDto dto=new DazvolDto();
        dto.setActive(dazvol.isActive());
        dto.setDate(dazvol.getFromDate());
        dto.setDazvolNumber(dazvol.getDazvolNumber());
        dto.setCompany(dazvol.getCompany());
        dto.setName(dazvol.getDazvolName().getAuthority());
        dto.setExperienceDate(dazvol.getExperienceDate());
        dto.setId(dazvol.getId());
        return dto;
    }

}
