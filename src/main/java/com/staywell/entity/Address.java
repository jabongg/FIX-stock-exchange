package com.staywell.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@NotNull
	private String streetAddress;
	@NotNull  @Size(min = 4)
    private String city;
	@NotNull
    private String state;
	@NotNull
    private String postalCode;
	@NotNull
    private String country;

}
