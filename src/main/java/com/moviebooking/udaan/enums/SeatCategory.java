package com.moviebooking.udaan.enums;

public enum SeatCategory {

	FRONT {
		@Override
		public int maxSeatAvailable() {
			return 5;
		}
	},
	BACK {
		@Override
		public int maxSeatAvailable() {
			return 5;
		}
	};

	public abstract int maxSeatAvailable();
	
}
