package com.example.schoolmanagementsystem.Service;

import com.example.schoolmanagementsystem.Api.ApiException;
import com.example.schoolmanagementsystem.DTO.AddressDTO;
import com.example.schoolmanagementsystem.Model.Address;
import com.example.schoolmanagementsystem.Model.Teacher;
import com.example.schoolmanagementsystem.Repository.AddressRepository;
import com.example.schoolmanagementsystem.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final TeacherRepository teacherRepository;

    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    public void addAddress(AddressDTO addressDTO) {
        Teacher teacher = teacherRepository.findTeacherById(addressDTO.getTeacher_id());

        if (teacher == null)
            throw new ApiException("Teacher not found");

        Address address = new Address(null, addressDTO.getArea(), addressDTO.getStreet(), addressDTO.getBuildingNumber(), teacher);

        addressRepository.save(address);
    }

    public void updateAddress(AddressDTO addressDTO) {
        Address address = addressRepository.findAddressById(addressDTO.getTeacher_id());

        if (address == null)
            throw new ApiException("Address not found");

        address.setArea(addressDTO.getArea());
        address.setStreet(addressDTO.getStreet());
        address.setBuildingNumber(addressDTO.getBuildingNumber());

        addressRepository.save(address);
    }

    public void deleteAddress(Integer id) {
        Address address = addressRepository.findAddressById(id);

        if (address == null)
            throw new ApiException("Address not found");

        addressRepository.delete(address);
    }
}
