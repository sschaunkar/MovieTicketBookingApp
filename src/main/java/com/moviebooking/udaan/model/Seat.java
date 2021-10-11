package com.moviebooking.udaan.model;

import java.util.Objects;

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
public class Seat {

	private String seatno;
	private SeatCategory category;
	private boolean isBooked;
	@Override
	public int hashCode() {
		return Objects.hash(category, isBooked, seatno);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		return category == other.category && isBooked == other.isBooked && Objects.equals(seatno, other.seatno);
	}
	
	
}
