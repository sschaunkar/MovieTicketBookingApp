package com.moviebooking.udaan.model;

import com.moviebooking.udaan.enums.SeatCategory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BookingSeat {

	private String seatno;
	private SeatCategory category;
	private boolean isBooked;
	
	
}
