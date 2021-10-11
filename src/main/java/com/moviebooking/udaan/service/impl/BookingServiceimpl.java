package com.moviebooking.udaan.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebooking.udaan.dto.BookingRequestDto;
import com.moviebooking.udaan.dto.BookingResponseDto;
import com.moviebooking.udaan.enums.ErrorCode;
import com.moviebooking.udaan.enums.SeatCategory;
import com.moviebooking.udaan.exception.BaseUnCheckedException;
import com.moviebooking.udaan.model.Seat;
import com.moviebooking.udaan.model.Ticket;
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

		final Map<String, Map<SeatCategory, List<Seat>>> allSeats = repo.getMultiaudi().get(bookingRequest.getAudi());

		List<Seat> bookedSeats;
		validateRequest(bookingRequest, allSeats);

		bookedSeats = new ArrayList<>();
		final BookingResponseDto response;
		boolean isduplicate = isDuplicate(bookingRequest);
		if(isduplicate) {
			throw new BaseUnCheckedException(ErrorCode.DUPLICATE_SEATS); 
		} else {
			bookingRequest.getRequestedSeatsByCategory().entrySet().forEach(requestedSeats -> {
				List<Seat> allCatSeats = allSeats.get(bookingRequest.getTimeSlot()).get(requestedSeats.getKey());
				for (int i = 0; i < allCatSeats.size(); i++) {
					for (int j = 0; j < requestedSeats.getValue().size(); j++) {			
						if (allCatSeats.get(i).getSeatno().equals(requestedSeats.getValue().get(j).getSeatno())) {
							allCatSeats.get(i).setBooked(true);
							Seat tempSeat = Seat.builder()
									.category(requestedSeats.getValue().get(j).getCategory()).isBooked(true)
									.seatno(requestedSeats.getValue().get(j).getSeatno()).build();
							bookedSeats.add(tempSeat);



						}
					}
				}
			});
		}
		

		Ticket generatedTicket = generateTicket(bookedSeats, bookingRequest.getTimeSlot());
		addinHistory(bookingRequest.getAudi(),generatedTicket);
		response = BookingResponseDto.builder().ticketdetails(generatedTicket).build();
		return response;


	}
	
	public Boolean isDuplicate(BookingRequestDto bookingrequest) {
		Set<String> seat = new HashSet<String>();
	    final AtomicBoolean isDup = new AtomicBoolean(false);
	    final AtomicInteger counter = new AtomicInteger(0);
		bookingrequest.getRequestedSeatsByCategory().entrySet().forEach(item->{
			counter.incrementAndGet();
			for(int i=0;i<item.getValue().size();i++) {
				if(!seat.add(item.getValue().get(i).getSeatno())) {
					isDup.compareAndExchange(false, true);
				}
			}
			
		});
		System.out.println(counter.get());
		return isDup.get();
	}

	private void addinHistory(String showTime, Ticket generatedTicket) {
		// TODO Auto-generated method stub
		final Map<String,List<Ticket>> ticketRecord = repo.getTicketHistory();
		List<Ticket> ticketList = ticketRecord.get(showTime);
		if(null == ticketRecord.get(showTime)) {
			ticketList = new ArrayList<>();
		}
		ticketList.add(generatedTicket);
		ticketRecord.put(showTime, ticketList);
		repo.updateTicketHistory(ticketRecord);
	}

	private Ticket generateTicket(List<Seat> bookedSeats, String timeSlot) {
		// TODO Auto-generated method stub
		Ticket tempticket;
		tempticket = Ticket.builder().ticketNo(new BigDecimal(new Random().nextInt(1000) + 50)).timeSlot(timeSlot).bookingSeat(bookedSeats)
				.billAmount(new BigDecimal(new Random().nextInt(100) + 100)).build();
		return tempticket;
	}

	public List<Ticket> fetchAllHistoryshowTime(String audi){	
		return repo.getTicketHistory().get(audi);
	}

	public List<Ticket> fetchAllHistoryshowTime(String audi, String timeslot){	
		return repo.getTicketHistory().get(audi);
	}

	private void validateRequest(BookingRequestDto bookingRequest,
			final Map<String, Map<SeatCategory, List<Seat>>> allSeatsMap) {
		bookingRequest.getRequestedSeatsByCategory().entrySet().forEach(requestedSeats -> {
			List<Seat> allCatSeats = allSeatsMap.get(bookingRequest.getTimeSlot()).get(requestedSeats.getKey());
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
