package br.com.condelivery.auth.service;

import br.com.condelivery.auth.feignClients.UserFeignClient;
import br.com.condelivery.auth.model.Condominium;
import br.com.condelivery.auth.model.Resident;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationCondoService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(AuthenticationResidentService.class);

    @Autowired
    private UserFeignClient userFeignClient;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || username.trim().isEmpty()) {
            logger.error("Tentativa de login com username vazio ou nulo");
            throw new UsernameNotFoundException("Username cannot be null or empty");
        }

        Condominium condominium;
        try {
            condominium = userFeignClient.findCondominiumByEmail(username).getBody();
        } catch (Exception e) {
            logger.error("Erro ao tentar buscar residente pelo Feign Client para o email: {}", username, e);
            throw new UsernameNotFoundException("Erro ao buscar usuário: " + e.getMessage());
        }

        if (condominium == null) {
            logger.warn("Email não encontrado: {}", username);
            throw new UsernameNotFoundException("Email não encontrado");
        }

        logger.info("Residente autenticado com sucesso: {}", username);
        return condominium;
    }

}
