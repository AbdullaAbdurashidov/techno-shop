package com.codeforce.shop.repository;

import com.codeforce.shop.domain.HashId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface HashRepository extends JpaRepository<HashId,Long> {

    @Transactional
    @Modifying
    @Query(value = "update hash_id set hash=:hash where id=:id", nativeQuery = true)
    void setHashIDbyID(@Param("id") Long id, @Param("hash") String hash);


}
