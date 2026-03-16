package com.ecommerce.project.controller;

import com.ecommerce.project.model.User;
import com.ecommerce.project.payload.AddressDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface AddressService {
    AddressDTO createAddress(AddressDTO address);

    List<AddressDTO> retrieveAddresses();

    AddressDTO retrieveAddressesById(Long id);

    List<AddressDTO> retrieveAddressesOfUser(User user);

    AddressDTO updateAddressById(Long addressId, @Valid AddressDTO addressDTO);

    AddressDTO deleteAddressById(Long addressId);
}
