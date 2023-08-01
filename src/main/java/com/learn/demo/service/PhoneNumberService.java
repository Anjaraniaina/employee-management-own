package com.learn.demo.service;

import com.learn.demo.repository.PhoneNumberRepository;
import com.learn.demo.repository.entity.PhoneNumberEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PhoneNumberService {
    private PhoneNumberRepository phoneNumberRepository;

    public PhoneNumberEntity getById(Long id){
        Optional<PhoneNumberEntity> phoneNumber = phoneNumberRepository.findById(id);
        if(phoneNumber.isPresent()){
            return phoneNumber.get();
        } else {
            throw new RuntimeException();
        }
    }

    public PhoneNumberEntity addPhoneNumber(PhoneNumberEntity phoneNumber){
        return phoneNumberRepository.save(phoneNumber);
    }

    public List<PhoneNumberEntity> getAllPhoneNumber(){
        return phoneNumberRepository.findAll();
    }

    public List<PhoneNumberEntity> createOrUpdatePhoneNumbers(List<PhoneNumberEntity> toUpdate){
        return phoneNumberRepository.saveAll(toUpdate);
    }

//    public boolean isUnique(List<String> phoneNumbers){
//        try {
//            phoneNumbers.forEach((phoneNumber)-> {
//                if (!Objects.equals(phoneNumberRepository.filterPhoneNumber(phoneNumber),List.of())){
//                    throw new RuntimeException();
//                }
//            });
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return true;
//    }

        public String imageFileToBase64(MultipartFile file) throws IOException {
        log.info("Encoded Base 64 image");
        byte [] fileByte = file.getBytes();
        System.out.println(Base64.getEncoder().encodeToString(fileByte));
        return Base64.getEncoder().encodeToString(fileByte);
    }

//    public List<PhoneNumberEntity> filter(SearchForm searchForm) {
//        if(searchForm.getHiringDate() != null){
//            if (!Objects.equals(searchForm.getSex(), "All"))
//                return phoneNumberRepository.findPhoneNumberByHiringAndDepartureDateAndSex(
//                        searchForm.getHiringDate().toString(),
//                        searchForm.getDepartureDate().toString(),
//                        searchForm.getSex(),
//                        searchForm.getOrder()
//                );
//            else
//                return phoneNumberRepository.findAllPhoneNumberByHiringAndDepartureDate(
//                        searchForm.getHiringDate().toString(),
//                        searchForm.getDepartureDate().toString(),
//                        searchForm.getOrder()
//                );
//        } else if(!Objects.equals(searchForm.getFilterBy(), null) && Objects.nonNull(searchForm.getKeyword())){
//            switch (searchForm.getFilterBy()) {
//                case "firstName" -> {
//                    if (!Objects.equals(searchForm.getSex(), "All"))
//                        return phoneNumberRepository.findPhoneNumberByFirstNameAndSex(searchForm.getKeyword(), searchForm.getSex(), searchForm.getOrder());
//                    else
//                        return phoneNumberRepository.findAllPhoneNumberByFirstName(searchForm.getKeyword(), searchForm.getOrder());
//
//                }
//                case "lastName" -> {
//                    if (!Objects.equals(searchForm.getSex(), "All"))
//                        return phoneNumberRepository.findPhoneNumberByLastNameAndSex(searchForm.getKeyword(), searchForm.getSex(), searchForm.getOrder());
//                    else
//                        return phoneNumberRepository.findAllPhoneNumberByLastName(searchForm.getKeyword(), searchForm.getOrder());
//
//                }
//                case "function" -> {
//                    if (!Objects.equals(searchForm.getSex(), "All"))
//                        return phoneNumberRepository.findPhoneNumberByFunctionAndSex(searchForm.getKeyword(), searchForm.getSex(), searchForm.getOrder());
//                    else
//                        return phoneNumberRepository.findAllPhoneNumberByFunction(searchForm.getKeyword(), searchForm.getOrder());
//                }
//            }
//        }
//        System.out.println(searchForm.getOrder());
//        return phoneNumberRepository.findAllPhoneNumberOrderedById(searchForm.getOrder());
//    }
}
