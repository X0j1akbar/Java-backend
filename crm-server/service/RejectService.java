package uz.pdp.srmserver.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import uz.pdp.srmserver.entitiy.ProductWithAmount;
import uz.pdp.srmserver.entitiy.Reject;
import uz.pdp.srmserver.entitiy.Shop;
import uz.pdp.srmserver.entitiy.User;
import uz.pdp.srmserver.payload.request.RejectRequest;
import uz.pdp.srmserver.payload.response.PageableResponse;
import uz.pdp.srmserver.repository.ProductWithAmountRepository;
import uz.pdp.srmserver.repository.RejectRepository;
import uz.pdp.srmserver.repository.ShopRepository;
import uz.pdp.srmserver.repository.UserRepository;
import uz.pdp.srmserver.utils.DataGetterUtils;

import java.util.UUID;

@Service
@AllArgsConstructor
public class RejectService implements BasicCrud<Reject, UUID, RejectRequest> {

    private final RejectRepository rejectRepository;
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final ProductWithAmountRepository productWithAmountRepository;
    private final DataGetterUtils dataGetterUtils;

    @Override
    @Transactional
    public Reject save(RejectRequest req) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User seller = userRepository.findByUsername(username);
        Shop shop = shopRepository.findBySeller(seller).orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Seller shop not found!"));
        Reject reject = new Reject();
        reject.setShop(shop);

        return saveReject(req, reject);
    }

    @Override
    public Reject getByID(UUID id) {
        return rejectRepository.getById(id);
    }

    @Override
    public PageableResponse<Reject> getAll(Pageable pageable) {
        Page<Reject> salePage = rejectRepository.findAll(pageable);
        return new PageableResponse<>(
                salePage.getContent(),
                salePage.getTotalElements()
        );
    }

    @Override
    public Reject deleteById(UUID id) {
        return null;
    }

    @Override
    @Transactional
    public Reject updateById(UUID id, RejectRequest req) {
        Reject reject = rejectRepository.getById(id);
        for (ProductWithAmount productWithAmount : reject.getProductWithAmounts()) {
            removePWA(productWithAmount);
        }
        return saveReject(req, reject);
    }

    public Reject setApproved(UUID id, Boolean approved) {
        Reject reject = rejectRepository.getById(id);
        reject.setApproved(approved);
        return rejectRepository.save(reject);
    }

    private Reject saveReject(RejectRequest req, Reject reject) {
        reject.setDescription(req.getDescription());
        reject = rejectRepository.save(reject);
        reject.setReport(dataGetterUtils.getCurrentReport(reject.getShop()));

        for (ProductWithAmount productWithAmount : req.getProductWithAmounts()) {
            createPWA(productWithAmount, reject);
        }
        return reject;
    }

    private void createPWA(ProductWithAmount req, Reject reject) {
        ProductWithAmount pwa = new ProductWithAmount();
        pwa.setReject(reject);
        pwa.setProduct(req.getProduct());
        pwa.setAmount(req.getAmount());
        pwa.setPrice(req.getPrice());
        productWithAmountRepository.save(pwa);
    }

    private void removePWA(ProductWithAmount req) {
        productWithAmountRepository.delete(req);
    }

}
