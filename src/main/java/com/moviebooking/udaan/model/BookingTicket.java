package com.moviebooking.udaan.model;

import java.math.BigDecimal;
import java.util.List;

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
public class BookingTicket {
	
  private BigDecimal ticketNo;
  private List<BookingSeat> bookingSeat;
  private BigDecimal billAmount;
  private String timeSlot;
//  private String showName;
  
}
