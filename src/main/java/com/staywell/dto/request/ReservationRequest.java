package com.staywell.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.staywell.dto.Payment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {

	@FutureOrPresent
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate checkinDate;

	@FutureOrPresent
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate checkoutDate;

	@Min(1)
	private Integer noOfPerson;

	@Valid
	private Payment payment;

}
