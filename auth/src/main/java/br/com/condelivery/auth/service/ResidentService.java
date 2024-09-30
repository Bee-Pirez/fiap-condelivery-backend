package br.com.condelivery.auth.service;

import br.com.condelivery.auth.feignClients.UserFeignClient;
import br.com.condelivery.auth.model.Resident;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;


@Service
public class ResidentService {

    private static Logger logger = LoggerFactory.getLogger(ResidentService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    public Resident findByEmail(String email){
        Resident resident = userFeignClient.findByEmail(email).getBody();
        if(resident == null){
            logger.error("Email not found:" + email);
            throw new IllegalArgumentException("Email not found");
        }
        logger.info("Email found:" + email);
        return resident;
    }
}
