package uz.pdp.srmserver.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import uz.pdp.srmserver.entitiy.*;
import uz.pdp.srmserver.entitiy.enums.PayStatus;
import uz.pdp.srmserver.payload.request.SaleRequest;
import uz.pdp.srmserver.payload.response.PageableResponse;
import uz.pdp.srmserver.repository.*;
import uz.pdp.srmserver.utils.DataGetterUtils;

import java.util.UUID;

@Service
@AllArgsConstructor
public class SaleService implements BasicCrud<Sale, UUID, SaleRequest> {

    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final ShopRepository shopRepository;
    private final ProductWithAmountRepository productWithAmountRepository;
    private final PaymentRepository paymentRepository;
    private final DataGetterUtils dataGetterUtils;

    @Override
    @Transactional
    public Sale save(SaleRequest req) {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User seller = userRepository.findByUsername(username);
        Customer customer = req.getCustomerId() != null ? customerRepository.findById(req.getCustomerId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Customer not found!")) : null;
        Shop shop = shopRepository.findBySeller(seller).orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Seller shop not found!"));
        Sale sale = new Sale();
        sale.setDescription(req.getDescription());
        sale.setCustomer(customer);
        sale.setShop(shop);

        return saveSale(req, sale);
    }

    @Override
    public Sale getByID(UUID id) {
        return saleRepository.getById(id);
    }

    @Override
    public PageableResponse<Sale> getAll(Pageable pageable) {
        Page<Sale> salePage = saleRepository.findAll(pageable);
        return new PageableResponse<>(
                salePage.getContent(),
                salePage.getTotalElements()
        );
    }

    @Override
    public Sale deleteById(UUID id) {
        return null;
    }

    @Override
    @Transactional
    public Sale updateById(UUID id, SaleRequest req) {
        Sale sale = saleRepository.getById(id);
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User seller = userRepository.findByUsername(username);
        sale.setSeller(seller);
        for (ProductWithAmount productWithAmount : sale.getProductWithAmounts()) {
            removePWA(productWithAmount);
        }
        for (Payment payment : sale.getPayments()) {
            removePayment(payment);
        }

        return saveSale(req, sale);
    }

    private Sale saveSale(SaleRequest req, Sale sale) {
        sale.setPayStatus(getPayStatus(req));
        sale.setReport(dataGetterUtils.getCurrentReport(sale.getShop()));
        sale = saleRepository.save(sale);

        for (ProductWithAmount productWithAmount : req.getProductWithAmounts()) {
            createPWA(productWithAmount, sale);
        }
        for (Payment payment : req.getPayments()) {
            createPayment(payment, sale);
        }

        return sale;
    }

    private void createPWA(ProductWithAmount req, Sale sale) {
        ProductWithAmount pwa = new ProductWithAmount();
        pwa.setSale(sale);
        pwa.setProduct(req.getProduct());
        pwa.setAmount(req.getAmount());
        pwa.setPrice(req.getPrice());
        productWithAmountRepository.save(pwa);
    }

    private void removePWA(ProductWithAmount req) {
        productWithAmountRepository.delete(req);
    }

    private void createPayment(Payment req, Sale sale) {
        Payment payment = new Payment();
        payment.setSale(sale);
        payment.setPaySum(req.getPaySum());
        payment.setPayType(req.getPayType());
        paymentRepository.save(payment);
    }

    private void removePayment(Payment payment) {
        paymentRepository.delete(payment);
    }

    private PayStatus getPayStatus(SaleRequest req) {
        double totalSum = 0;
        for (ProductWithAmount productWithAmount : req.getProductWithAmounts()) {
            totalSum += productWithAmount.getAmount() * productWithAmount.getPrice();
        }
        double totalPaid = 0;
        for (Payment payment : req.getPayments()) {
            totalPaid += payment.getPaySum();
        }

        if (totalPaid == 0)
            return (PayStatus.UNPAID);
        else if (totalPaid < totalSum)
            return (PayStatus.PARTLY_PAID);
        else
            return (PayStatus.PAID);
    }
}
