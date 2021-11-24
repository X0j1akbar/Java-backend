package uz.pdp.srmserver.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import uz.pdp.srmserver.entitiy.Defect;
import uz.pdp.srmserver.entitiy.ProductWithAmount;
import uz.pdp.srmserver.entitiy.Shop;
import uz.pdp.srmserver.entitiy.User;
import uz.pdp.srmserver.payload.request.DefectRequest;
import uz.pdp.srmserver.payload.response.PageableResponse;
import uz.pdp.srmserver.repository.DefectRepository;
import uz.pdp.srmserver.repository.ProductWithAmountRepository;
import uz.pdp.srmserver.repository.ShopRepository;
import uz.pdp.srmserver.repository.UserRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DefectService implements BasicCrud<Defect, UUID, DefectRequest> {

    private final DefectRepository defectRepository;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final ProductWithAmountRepository productWithAmountRepository;

    @Override
    @Transactional
    public Defect save(DefectRequest req) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User seller = userRepository.findByUsername(username);
        Shop shop = shopRepository.findBySeller(seller).orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Seller shop not found!"));
        Defect defect = new Defect();
        defect.setShop(shop);

        return saveDefect(req, defect);
    }

    @Override
    public Defect getByID(UUID id) {
        return defectRepository.getById(id);
    }

    @Override
    public PageableResponse<Defect> getAll(Pageable pageable) {
        Page<Defect> salePage = defectRepository.findAll(pageable);
        return new PageableResponse<>(
                salePage.getContent(),
                salePage.getTotalElements()
        );
    }

    @Override
    public Defect deleteById(UUID id) {
        return null;
    }

    @Override
    @Transactional
    public Defect updateById(UUID id, DefectRequest req) {
        Defect defect = defectRepository.getById(id);
        for (ProductWithAmount productWithAmount : defect.getProductWithAmounts()) {
            removePWA(productWithAmount);
        }
        return saveDefect(req, defect);
    }

    private Defect saveDefect(DefectRequest req, Defect defect) {
        defect = defectRepository.save(defect);

        for (ProductWithAmount productWithAmount : req.getProductWithAmounts()) {
            createPWA(productWithAmount, defect);
        }
        return defect;
    }

    private void createPWA(ProductWithAmount req, Defect defect) {
        ProductWithAmount pwa = new ProductWithAmount();
        pwa.setDefect(defect);
        pwa.setProduct(req.getProduct());
        pwa.setAmount(req.getAmount());
        pwa.setPrice(req.getPrice());
        productWithAmountRepository.save(pwa);
    }

    private void removePWA(ProductWithAmount req) {
        productWithAmountRepository.delete(req);
    }

}
