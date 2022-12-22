package uz.gullbozor.gullbozor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import uz.gullbozor.gullbozor.apiResponse.ApiResponse;
import uz.gullbozor.gullbozor.apiResponse.UserData;
import uz.gullbozor.gullbozor.dto.*;
import uz.gullbozor.gullbozor.entity.CompanyOwner;
import uz.gullbozor.gullbozor.entity.SmsToken;
import uz.gullbozor.gullbozor.entity.UserEntity;
import uz.gullbozor.gullbozor.entity.enums.RoleName;
import uz.gullbozor.gullbozor.repository.ComOwnerRepo;
import uz.gullbozor.gullbozor.repository.RoleRepo;
import uz.gullbozor.gullbozor.repository.SmsRepo;
import uz.gullbozor.gullbozor.repository.UserRepo;
import uz.gullbozor.gullbozor.security.JwtProvider;
import uz.gullbozor.gullbozor.verifysms.PhoneVerificationService;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class AuthService implements UserDetailsService {


    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ComOwnerRepo comOwnerRepo;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    PhoneVerificationService phoneVerificationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private SmsRepo smsRepo;

    public UserData checkPhoneNumber(CheckPhoneNumberDto checkPhoneNumberDto) {
        if (userRepo.existsByUsername(checkPhoneNumberDto.getPhoneNumber()) ){
            Optional<UserEntity> optionalUserEntity = userRepo.findByUsername(checkPhoneNumberDto.getPhoneNumber());
            UserEntity userEntity = optionalUserEntity.get();
            try {
                Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        userEntity.getUsername(),
                        userEntity.getUsernameTest()
                ));
                System.out.println(authenticate);
                UserEntity user = (UserEntity) authenticate.getPrincipal();
                String token = jwtProvider.generateToken(user.getUsername(), user.getRoles());
                return new UserData(true,token,user.getId());
            } catch (BadCredentialsException badCredentialsException) {
                return new UserData("telefon raqam yoki parol xato",false);
            }

        }
        return new UserData("Bu telefon raqam ro'yxatdan o'tmagan",false);
    }

//    public UserData login(Login login) {
//        try {
//            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                    login.getPhoneNumber(),
//                    login.getUserName()
//            ));
//            System.out.println(authenticate);
//            UserEntity user = (UserEntity) authenticate.getPrincipal();
//            String token = jwtProvider.generateToken(user.getUsernameTest(), user.getRoles());
//            return new UserData(true,token,user.getId());
//        } catch (BadCredentialsException badCredentialsException) {
//            return new UserData("telefon raqam yoki parol xato",false);
//        }
//    }

    public ApiResponse registerUser(RegisterDto registerDto) {
        if (registerDto.getSecretKey().equals("abdulqosimakanaikenjaqizi"))

      if (userRepo.existsByUsername(registerDto.getPhoneNumber())) {
          return new ApiResponse("Bu telefon raqam ro'yxatdan o'tgan",false);
        }

        Date date = new Date();

              UserEntity user = new UserEntity();
              user.setCreateAt(date.getTime());
              user.setSurname(registerDto.getSurname());

              user.setUsername(registerDto.getPhoneNumber());      //username - telnomer
              user.setPassword(passwordEncoder.encode(registerDto.getUserName()));
              user.setUsernameTest(registerDto.getUserName());     // usernameTest - username

              user.setShopId(registerDto.getShopId());
              if (registerDto.getPhoneNumber().equals("+998977169686")){
                  user.setRoles(Collections.singleton(roleRepo.findByName(RoleName.ROLE_ADMIN)));
              }else {
                  user.setRoles(Collections.singleton(roleRepo.findByName(RoleName.ROLE_USER)));
              }
              user.setEnabled(true);

        UserEntity save = userRepo.save(user);

        return new ApiResponse(save);
    }

    public ApiResponse registerCompanyOwner(RegisterCompanyOwner registerCompanyOwner) {
        if (registerCompanyOwner.getSecretKey().equals("abdulqosimakanaikenjaqizi"))

            if (userRepo.existsByUsername(registerCompanyOwner.getPhoneNumber())) {
                return new ApiResponse("Bu telefon raqam ro'yxatdan o'tgan",false);
            }

        Date date = new Date();

        CompanyOwner companyOwner = new CompanyOwner();
        companyOwner.setCreateAt(date.getTime());
        companyOwner.setSurname(registerCompanyOwner.getSurname());

        companyOwner.setUsername(registerCompanyOwner.getPhoneNumber());      //username - telnomer
        companyOwner.setPassword(passwordEncoder.encode(registerCompanyOwner.getUserName()));
        companyOwner.setUsernameTest(registerCompanyOwner.getUserName());     // usernameTest - username

        companyOwner.setShopId(registerCompanyOwner.getShopId());
        if (registerCompanyOwner.getPhoneNumber().equals("+998977169686")){
            companyOwner.setRoles(Collections.singleton(roleRepo.findByName(RoleName.ROLE_ADMIN)));
        }else {
            companyOwner.setRoles(Collections.singleton(roleRepo.findByName(RoleName.ROLE_USER)));
        }
        companyOwner.setEnabled(true);

        CompanyOwner save = comOwnerRepo.save(companyOwner);

        return new ApiResponse(save);
    }


    public ApiResponse createSmsToken(SmsTokenDto smsTokenDto) {
            if (smsRepo.existsBySmsToken(smsTokenDto.getSmsToken())) {
                return new ApiResponse("Bunday token mavjud",false);
            }
        SmsToken smsToken = new SmsToken();
        Date date = new Date();
        smsToken.setCreateAt(date.getTime());
            smsToken.setSmsToken(smsTokenDto.getSmsToken());
        SmsToken save = smsRepo.save(smsToken);
        return new ApiResponse(save);
    }

    public ApiResponse editSmsToken(SmsTokenDto smsTokenDto, Long id) {

        if (!smsRepo.existsById(id)) {
            return new ApiResponse("Bunday id lik token topilmadi",false);
        }

            if (smsRepo.existsBySmsToken(smsTokenDto.getSmsToken())) {
                return new ApiResponse("Bunday token mavjud",false);
            }

        Optional<SmsToken> optionalSmsToken = smsRepo.findById(id);
        SmsToken smsToken = optionalSmsToken.get();
            smsToken.setSmsToken(smsTokenDto.getSmsToken());
        SmsToken save = smsRepo.save(smsToken);
        return new ApiResponse(save);
    }

    public ApiResponse getSmsToken(Long key, Long id) {

        if ( key != 4343245366788986756L ){
            return new ApiResponse("password noto'g'ri",false);
        }
        if (!smsRepo.existsById(id)) {
            return new ApiResponse("Bu id lik token topilmadi",false);
        }
        Optional<SmsToken> optionalSmsToken = smsRepo.findById(id);
        SmsToken smsToken = optionalSmsToken.get();

        Long generatedNumber = ThreadLocalRandom.current().nextLong(10000,99999);

        return new ApiResponse(smsToken.getSmsToken(), generatedNumber);
    }


    @Override
    public UserDetails loadUserByUsername(String  username) throws UsernameNotFoundException {

//        Optional<UserEntity> optionalUserEntity = userRepo.findByUserName(username);
//        if (optionalUserEntity.isPresent()) {
//            return optionalUserEntity.get();
//        }
//        else {
//            throw new UsernameNotFoundException(username + "Not found");
//        }

        return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + "Not found"));
    }
}
