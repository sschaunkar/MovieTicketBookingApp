package com.moviebooking.udaan.dto;

import java.util.List;
import java.util.Map;

import com.moviebooking.udaan.enums.SeatCategory;
import com.moviebooking.udaan.model.Seat;

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
public class BookingRequestDto {

	private Map<SeatCategory, List<Seat>> requestedSeatsByCategory;
	private String audi;
	private String timeSlot;
}
