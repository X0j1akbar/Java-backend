package uz.pdp.srmserver.service;

import org.springframework.data.domain.Pageable;
import uz.pdp.srmserver.payload.response.PageableResponse;

public interface BasicCrud<E, K, R> {

    E save(R req);

    E getByID(K id);

    PageableResponse<E> getAll(Pageable pageable);

    E deleteById(K id);

    E updateById(K id, R req);

}
