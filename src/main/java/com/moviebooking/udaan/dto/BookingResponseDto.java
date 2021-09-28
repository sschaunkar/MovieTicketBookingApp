package com.moviebooking.udaan.dto;

import java.util.List;

import com.moviebooking.udaan.model.BookingSeat;
import com.moviebooking.udaan.model.BookingTicket;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class BookingResponseDto {
	private BookingTicket ticketdetails;
}
