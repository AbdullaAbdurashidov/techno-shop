package com.codeforce.shop.service.impl;

import com.codeforce.shop.domain.HashId;
import com.codeforce.shop.repository.HashRepository;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HashService {

    @Autowired
    private HashRepository idRepository;

    private final Hashids hashids=new Hashids(getClass().getName(),8);

    public String getHashID(){
        HashId obj=new HashId();
        obj.setHashID("0");
        HashId obj2 = idRepository.save(obj);
        if(obj2.getID()!=null) {
            Long ID=obj2.getID();
            String hashID=hashids.encode(ID);
            idRepository.setHashIDbyID(ID,hashID);
            return hashID;
        }
        else
        {
            return null;
        }
    }

}
