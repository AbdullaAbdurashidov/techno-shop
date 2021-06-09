package com.codeforce.shop.service.impl;

import com.codeforce.shop.domain.User;
import com.codeforce.shop.dto.*;
import com.codeforce.shop.dto.composite.PagingDto;
import com.codeforce.shop.dto.composite.UserTableDto;
import com.codeforce.shop.mapper.UserAddressMapper;
import com.codeforce.shop.mapper.UserMapper;
import com.codeforce.shop.mapper.UserPaymentMapper;
import com.codeforce.shop.repository.UserAddressRepository;
import com.codeforce.shop.repository.UserPaymentRepository;
import com.codeforce.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private HashService idService;

    private UserMapper userMapper;

    private UserAddressMapper userAddressMapper;

    private UserPaymentMapper userPaymentMapper;

    public UserService(UserMapper userMapper,
                       UserAddressMapper userAddressMapper,
                       UserPaymentMapper userPaymentMapper){
        this.userMapper = userMapper;
        this.userAddressMapper = userAddressMapper;
        this.userPaymentMapper = userPaymentMapper;
    }

    public UserDTO getUserByEmailAndPassword(String email, String password) {
        User item = userRepository.findByUserNameAndPassword(email,password);
        if (item!=null)
            return userMapper.toDto(item);
        else
            return null;
    }

    public List<UserTableDto> findAllUserTableDto() {
        List<UserDTO> userList = userMapper.toDto(userRepository.findAll());
        List<UserTableDto> userTableDtoList = new ArrayList<>();
        userList.stream().forEach(x->{
            UserTableDto userTableDto = new UserTableDto();
            userTableDto.setFileDto(fileService.getFileByOwnerHashID(x.getId()));
            userTableDto.setUserDto(x);
            userTableDtoList.add(userTableDto);
        });

        return userTableDtoList;
    }

    public List<UserDTO> findAll(){
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    public UserDTO findByUsername(String username){
        return userMapper.toDto(userRepository.findByUserName(username));
    }

    public UserDTO getUserById(String Id){
       return userMapper.toDto(userRepository.findById(Id));
    }

    public UserTableDto getUserTableByHashID(String Id){
        UserTableDto item = null;
        UserDTO userDto = getUserById(Id);
        FileDTO fileDto = fileService.getFileByOwnerHashID(Id);
        if(userDto!=null){
            item = new UserTableDto(userDto, fileDto);
        }
        return item;
    }

    public PagingDto getUserPagingDto(int page, int length, String orderColumn, String orderType, String search){
        PagingDto pagingDto = new PagingDto();
        List<UserTableDto> dtoList = findAllUserTableDto();
        pagingDto.setRecordsTotal(dtoList.size());
        pagingDto.setRecordsFiltered(dtoList.size());
        List<UserTableDto> resultList = new ArrayList<>();
        int start = (page-1)*length;
        int end = length-1;
        for( int i=0; i< dtoList.size(); i++){
            if(i>=start && i<=end)
                resultList.add(dtoList.get(i));
        }
        pagingDto.setData(resultList);
        return pagingDto;
    }

    public UserDTO saveUser(UserDTO userDTO) {
        if(userRepository.findByUserName(userDTO.getUserName())==null){
            String hashID = idService.getHashID();
            User user = userMapper.toEntity(userDTO);
            user.setId(hashID);
            user.setCreatedAt(LocalDate.now());
            userRepository.save(user);
            return userDTO;
        }else{
            return null;
        }
    }

    public UserAddressDTO saveUserAddress(UserAddressDTO userAddressDTO, String userId){
        if(getUserById(userId)!=null){
            userAddressDTO.setUserId(userId);
            userAddressRepository.save(userAddressMapper.toEntity(userAddressDTO));
            return userAddressDTO;
        }
        else{
            return null;
        }
    }

    public UserPaymentDTO saveUserPayment(UserPaymentDTO userPaymentDTO, String userId){
        if(getUserById(userId)!=null){
            userPaymentDTO.setUserId(userId);
            userPaymentRepository.save(userPaymentMapper.toEntity(userPaymentDTO));
            return userPaymentDTO;
        }
        else{
            return null;
        }
    }

}
