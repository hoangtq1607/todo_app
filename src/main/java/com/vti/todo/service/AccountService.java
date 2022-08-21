package com.vti.todo.service;

import com.vti.todo.dto.request.LoginRequest;
import com.vti.todo.dto.request.RegisterAccountRequest;
import com.vti.todo.dto.request.ResetPasswordRequest;
import com.vti.todo.dto.response.JwtResponse;
import com.vti.todo.entity.AccountEntity;
import com.vti.todo.entity.OtpAccount;
import com.vti.todo.repository.AccountRepository;
import com.vti.todo.repository.OtpAccountRepository;
import com.vti.todo.security.JwtTokenProvider;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private SendMailService sendMailService;

    public AccountEntity registerNewAccount(RegisterAccountRequest registerAccountRequest) {
        AccountEntity account = new AccountEntity();
        account.setEmail(registerAccountRequest.getEmail());
        account.setFullName(registerAccountRequest.getFullName());
        account.setPassword(registerAccountRequest.getPassword());
        Locale locale = LocaleContextHolder.getLocale();
        account.setLang(locale.getLanguage());
        accountRepository.save(account);
        return account;
    }

    public JwtResponse login(LoginRequest loginRequest) {
        try {
            AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            String token = jwtTokenProvider.createToken(authenticate.getName(), authenticate.getAuthorities());
            return new JwtResponse(token);
        } catch (Exception e) {
            return new JwtResponse();
        }
    }

    @Autowired
    OtpAccountRepository otpAccountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void forgotPassword(String email) {

        AccountEntity account = accountRepository.findByEmail(email);

        if (account != null) {

            Random rd = new SecureRandom();
            int random = rd.nextInt(999_999);// random tu 0 -> 999_999
            String randomStr = String.format("%06d", random);

            OtpAccount otp = new OtpAccount();
            otp.setOtp(randomStr);
            otp.setEmail(email);
            otp.setExpire(LocalDateTime.now().plusMinutes(30));
            otpAccountRepository.save(otp);

            sendMailService.sendForgotPassword(account.getEmail(), randomStr, account.getLang());
        }
    }

    @Transactional
    public ResponseEntity<?> resetPassword(ResetPasswordRequest request) {
        OtpAccount otp = otpAccountRepository.findByEmailAndOtp(request.getEmail(), request.getOtp());
        if (otp != null && otp.getExpire().isAfter(LocalDateTime.now())) { //nếu otp tồn tại và chưa hết hạn (> now)
            AccountEntity accountEntity = accountRepository.findByEmail(request.getEmail());
            accountEntity.setPassword(passwordEncoder.encode(request.getPassword()));
            accountRepository.save(accountEntity);
            otpAccountRepository.delete(otp);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
