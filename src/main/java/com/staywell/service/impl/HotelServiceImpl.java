package com.staywell.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.staywell.dto.request.HotelRequest;
import com.staywell.dto.request.UpdatePasswordRequest;
import com.staywell.dto.request.UpdateRequest;
import com.staywell.dto.response.HotelResponse;
import com.staywell.enums.HotelType;
import com.staywell.enums.Role;
import com.staywell.exception.CustomerException;
import com.staywell.exception.HotelException;
import com.staywell.entity.Address;
import com.staywell.entity.Customer;
import com.staywell.entity.Hotel;
import com.staywell.repository.CustomerDao;
import com.staywell.repository.HotelDao;
import com.staywell.service.HotelService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class HotelServiceImpl implements HotelService {

	private HotelDao hotelDao;
	private CustomerDao customerDao;

	@Override
	public HotelResponse registerHotel(HotelRequest hotelRequest) {

		log.info("Performing email validation");
		if (isEmailExists(hotelRequest.getHotelEmail())) {
			throw new HotelException("This email is already registered. Please use a different email to register.");
		}

		log.info("Verifying Hotel name");
		if (hotelWithNameAlreadyExitsInYourCity(hotelRequest.getName(), hotelRequest.getAddress())) {
			throw new HotelException("Hotel already exits in your city with name : " + hotelRequest.getName());
		}

		Hotel hotel = buildHotel(hotelRequest);
		hotelDao.save(hotel);

		log.info("Registration successfull");
		return buildHotelResponse(hotel);
	}

	@Override
	public String updateName(UpdateRequest updateRequest) {
		Hotel currentHotel = getCurrentLoggedInHotel();

		log.info("Verifying credentials");
		String password = new String(updateRequest.getPassword());

		hotelDao.setNameOfHotel(currentHotel.getHotelId(), updateRequest.getField());

		log.info("Updation successfull");
		return "Updated hotel name successfully";
	}

	@Override
	public String updatePassword(UpdatePasswordRequest updatePasswordRequest) {
		Hotel currentHotel = getCurrentLoggedInHotel();

		log.info("Verifying credentials");
		String currentPassword = new String(updatePasswordRequest.getCurrentPassword());

		log.info("Validating new password");
		String newPassword = new String(updatePasswordRequest.getNewPassword());
		if (!matchesRegex(newPassword)) {
			throw new CustomerException("New password validation failed!");
		}


		log.info("Updation successfull");
		return "Password updated successfully!";
	}

	@Override
	public String updatePhone(UpdateRequest updateRequest) {
		Hotel currentHotel = getCurrentLoggedInHotel();

		log.info("Verifying credentials");
		String password = new String(updateRequest.getPassword());

		hotelDao.setPhoneOfHotel(currentHotel.getHotelId(), updateRequest.getField());

		log.info("Updation successfull");
		return "Updated hotel phone successfully";
	}

	@Override
	public String updateTelephone(UpdateRequest updateRequest) {
		Hotel currentHotel = getCurrentLoggedInHotel();

		log.info("Verifying credentials");
		String password = new String(updateRequest.getPassword());

		hotelDao.setTelephoneOfHotel(currentHotel.getHotelId(), updateRequest.getField());

		log.info("Updation successfull");
		return "Updated hotel telephone successfully";
	}

	@Override
	public String updateHotelType(UpdateRequest updateRequest) {
		Hotel hotel = getCurrentLoggedInHotel();

		log.info("Verifying credentials");
		String password = new String(updateRequest.getPassword());

		hotelDao.setHotelType(hotel.getHotelId(), HotelType.valueOf(updateRequest.getField()));

		log.info("Updation successfull");
		return "Updated hotel type successfully";
	}

	@Override
	public HotelResponse getHotelById(Long id) {
		Optional<Hotel> optional = hotelDao.findById(id);
		if (optional.isPresent())
			return buildHotelResponse(optional.get());
		throw new HotelException("No hotel found with id " + id);
	}

	@Override
	public List<HotelResponse> getHotelsNearMe() {
		Customer customer = getCurrentLoggedInCustomer();
		System.out.println(111);
		List<Hotel> hotels = hotelDao.getHotelByCity(customer.getAddress().getCity());
		System.out.println(222);
		if (hotels.isEmpty())
			throw new HotelException("Hotels Not Found In Your Area!");
		return hotels.stream().map(this::buildHotelResponse).collect(Collectors.toList());
	}

	@Override
	public List<HotelResponse> getHotelsInCity(String city) {
		List<Hotel> hotels = hotelDao.getHotelByCity(city);
		if (hotels.isEmpty())
			throw new HotelException("Hotels Not Found In Your Area!");
		return hotels.stream().map(this::buildHotelResponse).collect(Collectors.toList());
	}

	private boolean isEmailExists(String email) {
		return customerDao.findByEmail(email).isPresent() || hotelDao.findByHotelEmail(email).isPresent();
	}

	private Hotel getCurrentLoggedInHotel() {
		//String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return hotelDao.findByHotelEmail(null).get();
	}

	private Customer getCurrentLoggedInCustomer() {
		//String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return customerDao.findByEmail(null).get();
	}

	private boolean hotelWithNameAlreadyExitsInYourCity(String name, Address address) {
		Optional<Hotel> opt = hotelDao.getHotelByNameAndCity(name, address.getCity());
		if (opt.isPresent())
			return true;
		return false;
	}

	private boolean matchesRegex(String input) {
		if(input.length()<8)
			return false;
		String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
		return Pattern.compile(regex).matcher(input).matches();
	}

	private Hotel buildHotel(HotelRequest hotelRequest) {
		return Hotel.builder()
				.name(hotelRequest.getName())
				.hotelPhone(hotelRequest.getHotelPhone())
				.hotelTelephone(hotelRequest.getHotelTelephone())
				.role(Role.ROLE_HOTEL)
				.hotelType(hotelRequest.getHotelType())
				.address(hotelRequest.getAddress())
				.amenities(hotelRequest.getAmenities())
				.rooms(new ArrayList<>()).reservations(new ArrayList<>()).feedbacks(new ArrayList<>())
				.build();
	}

	private HotelResponse buildHotelResponse(Hotel hotel) {
		return HotelResponse.builder()
				.hotelId(hotel.getHotelId())
				.name(hotel.getName())
				.hotelTelephone(hotel.getHotelTelephone())
				.address(hotel.getAddress())
				.hotelType(hotel.getHotelType())
				.amenities(hotel.getAmenities())
				.build();
	}
}
