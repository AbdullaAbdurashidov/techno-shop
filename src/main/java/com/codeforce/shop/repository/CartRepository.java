package com.codeforce.shop.repository;

import com.codeforce.shop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {


    Cart getByUserIdAndProductId(String owner, String product);

    List<Cart> getByUserIdAndStatus(String userId, String status);

    @Transactional
    Long deleteByUserIdAndProductId(String owner, String product);

}
