package com.ecommerce.project.controller;

import com.ecommerce.project.payload.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO createAddress(AddressDTO address);

    List<AddressDTO> retrieveAddresses();

    AddressDTO retrieveAddressesById(Long id);
}
