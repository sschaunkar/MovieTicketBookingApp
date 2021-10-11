package com.moviebooking.udaan.service;

import java.util.List;

import com.moviebooking.udaan.dto.BookingRequestDto;
import com.moviebooking.udaan.dto.BookingResponseDto;
import com.moviebooking.udaan.model.Ticket;

public interface BookingService {

	BookingResponseDto book(BookingRequestDto bookingRequest);
	List<Ticket> fetchAllHistoryshowTime(String audi);
	List<Ticket> fetchAllHistoryshowTime(String audi, String timeslot);
}