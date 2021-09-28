package com.moviebooking.udaan.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebooking.udaan.dto.BookingRequestDto;
import com.moviebooking.udaan.dto.BookingResponseDto;
import com.moviebooking.udaan.enums.ErrorCode;
import com.moviebooking.udaan.enums.SeatCategory;
import com.moviebooking.udaan.exception.BaseUnCheckedException;
import com.moviebooking.udaan.model.BookingSeat;
import com.moviebooking.udaan.model.BookingTicket;
import com.moviebooking.udaan.repo.InMemoryDB;
import com.moviebooking.udaan.service.BookingService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingServiceimpl implements BookingService {

	@Autowired
	private InMemoryDB repo;

	@Override
	public BookingResponseDto book(BookingRequestDto bookingRequest) {

		final Map<String, Map<SeatCategory, List<BookingSeat>>> allSeats = repo.getMultiaudi().get(bookingRequest.getAudi());
		
		List<BookingSeat> bookedSeats;
		
		validateRequest(bookingRequest, allSeats);

		bookedSeats = new ArrayList<>();
		final BookingResponseDto response;
		bookingRequest.getRequestedSeatsByCategory().entrySet().forEach(requestedSeats -> {
			List<BookingSeat> allCatSeats = allSeats.get(bookingRequest.getTimeSlot()).get(requestedSeats.getKey());
			for (int i = 0; i < allCatSeats.size(); i++) {
				for (int j = 0; j < requestedSeats.getValue().size(); j++) {
					if (allCatSeats.get(i).getSeatno().equals(requestedSeats.getValue().get(j).getSeatno())) {
						allCatSeats.get(i).setBooked(true);
						BookingSeat tempSeat = BookingSeat.builder()
								.category(requestedSeats.getValue().get(j).getCategory()).isBooked(true)
								.seatno(requestedSeats.getValue().get(j).getSeatno()).build();
						bookedSeats.add(tempSeat);
					}
				}
			}
		});
		
		BookingTicket generatedTicket = generateTicket(bookedSeats, bookingRequest.getTimeSlot());
		addinHistory(bookingRequest.getAudi(),generatedTicket);
		response = BookingResponseDto.builder().ticketdetails(generatedTicket).build();
		return response;
		

	}
	
	private void addinHistory(String showTime, BookingTicket generatedTicket) {
		// TODO Auto-generated method stub
		final Map<String,List<BookingTicket>> ticketRecord = repo.getTicketHistory();
		List<BookingTicket> ticketList = ticketRecord.get(showTime);
		if(null == ticketRecord.get(showTime)) {
			ticketList = new ArrayList<>();
			}
		ticketList.add(generatedTicket);
		ticketRecord.put(showTime, ticketList);
		repo.updateTicketHistory(ticketRecord);
	}

	private BookingTicket generateTicket(List<BookingSeat> bookedSeats, String timeSlot) {
		// TODO Auto-generated method stub
		BookingTicket tempticket;
		tempticket = BookingTicket.builder().ticketNo(new BigDecimal(new Random().nextInt(1000) + 50)).timeSlot(timeSlot).bookingSeat(bookedSeats)
				.billAmount(new BigDecimal(new Random().nextInt(100) + 100)).build();
		return tempticket;
	}

	public List<BookingTicket> fetchAllHistoryshowTime(String audi){	
		return repo.getTicketHistory().get(audi);
	}
	
	public List<BookingTicket> fetchAllHistoryshowTime(String audi, String timeslot){	
		return repo.getTicketHistory().get(audi);
	}

	private void validateRequest(BookingRequestDto bookingRequest,
			final Map<String, Map<SeatCategory, List<BookingSeat>>> allSeatsMap) {
		bookingRequest.getRequestedSeatsByCategory().entrySet().forEach(requestedSeats -> {
			List<BookingSeat> allCatSeats = allSeatsMap.get(bookingRequest.getTimeSlot()).get(requestedSeats.getKey());
			int exists = 0;
			for (int i = 0; i < allCatSeats.size(); i++) {
				
				for (int j = 0; j < requestedSeats.getValue().size(); j++) {
					if (allCatSeats.get(i).getSeatno().equals(requestedSeats.getValue().get(j).getSeatno())) {
						if (allCatSeats.get(i).isBooked()) {
							throw new BaseUnCheckedException(ErrorCode.ALREADY_BOOKED);
						}
						exists++;
					}
				}
			}
			if(exists!=requestedSeats.getValue().size()) {
				 throw new BaseUnCheckedException(ErrorCode.NOT_AVAILABLE);
			}
		});
	}

}
