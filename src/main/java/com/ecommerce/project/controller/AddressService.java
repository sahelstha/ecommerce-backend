package com.ecommerce.project.controller;

import com.ecommerce.project.model.Address;
import com.ecommerce.project.payload.AddressDTO;

public interface AddressService {
    AddressDTO createAddress(AddressDTO address);
}
