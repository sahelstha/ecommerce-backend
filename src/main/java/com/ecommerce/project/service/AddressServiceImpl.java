package com.ecommerce.project.service;

import com.ecommerce.project.controller.AddressService;
import com.ecommerce.project.exceptions.APIException;
import com.ecommerce.project.exceptions.ResourceNotFoundException;
import com.ecommerce.project.model.Address;
import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import com.ecommerce.project.repositories.AddressRepository;
import com.ecommerce.project.repositories.UserRepository;
import com.ecommerce.project.util.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthUtil  authUtil;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AddressDTO createAddress(AddressDTO addressDto) {
        User user = authUtil.loggedInUser();

        Address address = modelMapper.map(addressDto, Address.class);

        if(user.getAddresses().contains(address)) {
            throw new APIException("The address already exists");
        }

        List<Address> addresses = user.getAddresses();
        addresses.add(address);
        user.setAddresses(addresses);
        userRepository.save(user);

        address.setUser(user);
        Address savedAddress = addressRepository.save(address);

        return modelMapper.map(savedAddress, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> retrieveAddresses() {
        List<Address> addresses = addressRepository.findAll();

        return addresses.stream().map(address ->
            modelMapper.map(address, AddressDTO.class)
        ).toList();
    }

    @Override
    public AddressDTO retrieveAddressesById(Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Address", "addressId", addressId));

        return modelMapper.map(address, AddressDTO.class);
    }

    @Override
    public List<AddressDTO> retrieveAddressesOfUser(User user) {
        List<Address> addresses = user.getAddresses();
        return addresses.stream()
                .map(address ->
                        modelMapper.map(address, AddressDTO.class))
                .toList();
    }

    @Override
    public AddressDTO updateAddressById(Long addressId, AddressDTO addressDTO) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(()-> new ResourceNotFoundException("Address", "addressId", addressId));

        Address updatedAddress = modelMapper.map(addressDTO, Address.class);

        address.setBuildingName(updatedAddress.getBuildingName());
        address.setCity(updatedAddress.getCity());
        address.setState(updatedAddress.getState());
        address.setStreet(updatedAddress.getStreet());
        address.setCountry(updatedAddress.getCountry());
        address.setPincode(updatedAddress.getPincode());

        addressRepository.save(address);
        return modelMapper.map(address, AddressDTO.class);
    }
}
