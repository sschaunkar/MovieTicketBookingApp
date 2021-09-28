package com.moviebooking.udaan.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.moviebooking.udaan.enums.SeatCategory;
import com.moviebooking.udaan.model.BookingSeat;
import com.moviebooking.udaan.model.BookingTicket;

import lombok.Getter;
import lombok.Setter;
@Service
public class InMemoryDB {
	@Getter
	@Setter
	private Map<String, Map<SeatCategory,List<BookingSeat>>> seatByTimeSlot;
	@Getter
	@Setter
	private Map<String,Map<String, Map<SeatCategory,List<BookingSeat>>>> multiaudi;
	@Getter
	@Setter
	private Map<String,List<BookingTicket>> ticketHistory;
	
	
	@PostConstruct
	public void init() {
		seatByTimeSlot = new HashMap<>();
		ticketHistory = new HashMap<>();
		multiaudi = new HashMap<>();
		// TODO: Iterate over timeslow and create seats for both the time slot
		List<BookingSeat> listOfFrontSeat = new ArrayList<>();
		for(int i=0; i<SeatCategory.FRONT.maxSeatAvailable();i++) {
			BookingSeat seat = BookingSeat.builder().category(SeatCategory.FRONT)
					.isBooked(false).seatno(i+"").build();
			listOfFrontSeat.add(seat);
		}
		List<BookingSeat> listOfBackSeat = new ArrayList<>();
		for(int i=0; i<SeatCategory.BACK.maxSeatAvailable();i++) {
			BookingSeat seat = BookingSeat.builder().category(SeatCategory.BACK)
			.isBooked(false).seatno(i+"").build();
			listOfBackSeat.add(seat);
			
		}
		Map<SeatCategory,List<BookingSeat>> mapOfSeatCategory = new HashMap<>();
		mapOfSeatCategory.put(SeatCategory.BACK, listOfBackSeat);
		mapOfSeatCategory.put(SeatCategory.FRONT, listOfFrontSeat);
		seatByTimeSlot.put("timeslot1", mapOfSeatCategory);
		seatByTimeSlot.put("timeslot2", mapOfSeatCategory);
		seatByTimeSlot.put("timeslot3", mapOfSeatCategory);
		multiaudi.put("audi0", seatByTimeSlot);
		
	}
	
	public void updateTicketHistory(Map<String,List<BookingTicket>> ticketHistory) {
		this.ticketHistory = ticketHistory;
	}
}
