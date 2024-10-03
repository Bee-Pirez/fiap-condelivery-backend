package br.com.condelivery.auth.service;

import br.com.condelivery.auth.feignClients.UserFeignClient;
import br.com.condelivery.auth.model.Resident;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;


@Service
public class AuthenticationResidentService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationResidentService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    public Resident findByEmail(String email) {
        Resident user = userFeignClient.findByEmail(email).getBody();
        if (user == null) {
            logger.error("Email not found: " + email);
            throw new IllegalArgumentException("Email not found");
        }
        logger.info("Email found: " + email);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Resident resident = userFeignClient.findByEmail(username).getBody();
        if(resident == null){
            logger.error("Email not found:" + username);
            throw new UsernameNotFoundException("Email not found");
        }
        logger.info("Email found:" + username);
        return resident;
    }

}
