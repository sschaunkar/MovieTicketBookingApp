package com.moviebooking.udaan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moviebooking.udaan.dto.BookingRequestDto;
import com.moviebooking.udaan.enums.SeatCategory;
import com.moviebooking.udaan.repo.InMemoryDB;
import com.moviebooking.udaan.service.BookingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/booking")
public class BookingController {
	@Autowired	
	private InMemoryDB repo;
	
	@Autowired	
	private BookingService bookingService;
	
	@GetMapping("/{audi}")
	public ResponseEntity<?> getSeatByAudiAndCat(@PathVariable("audi") String audi, 
			@RequestParam(value = "timeSlot", required = false) String ts) {
		if(null == ts) {
			return new ResponseEntity<>(repo.getMultiaudi().get(audi), HttpStatus.OK);
		}
		return new ResponseEntity<>(repo.getMultiaudi().get(audi).get(ts), HttpStatus.OK);
	}
	
	@PostMapping("/book")
	public ResponseEntity<?> bookTicket(@RequestBody BookingRequestDto bookingRequest){
		log.info("request recived {}", bookingRequest );
		return new ResponseEntity<>(bookingService.book(bookingRequest), HttpStatus.OK);
		
	}
	
	@GetMapping("/history/{audi}")
	public ResponseEntity<?> showHistory(@PathVariable("audi") String audi, @RequestParam(value="timeSlot", required =false) String ts) {
		if(null == ts) {
			return new ResponseEntity<>(bookingService.fetchAllHistoryshowTime(audi), HttpStatus.OK);
		}
		return new ResponseEntity<>(bookingService.fetchAllHistoryshowTime(audi, ts), HttpStatus.OK);
	}
	
}
