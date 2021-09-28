package com.moviebooking.udaan.service;

import java.util.List;

import com.moviebooking.udaan.dto.BookingRequestDto;
import com.moviebooking.udaan.dto.BookingResponseDto;
import com.moviebooking.udaan.model.BookingTicket;

public interface BookingService {

	BookingResponseDto book(BookingRequestDto bookingRequest);
	List<BookingTicket> fetchAllHistoryshowTime(String audi);
	List<BookingTicket> fetchAllHistoryshowTime(String audi, String timeslot);
}