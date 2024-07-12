package hotel;

import java.util.ArrayList;
import java.util.List;

public class CheapestHotelFinder {
	
	public enum CustomerType {
		REGULAR,REWARD;
	}
	
	public enum DayOfTheWeek{
		WEEKEND,WEEKDAY;
	}
	
	public enum Hotel{
		MIAMI_BEACH("Miami Beach", 4),
	    MIAMI_DOWNTOWN("Miami Downtown", 3),
	    MIAMI_MIDTOWN("Miami Midtown", 5);
		
		private String hotelName;
		private int rating;
		
		Hotel(String hotelName,int rating) {
			this.hotelName = hotelName;
			this.rating = rating;
		}
		
		public String getHotelName(){
			return this.hotelName;
		}
		
		public int getRating(){
			return this.rating;
		}
	}
	
	public static class Rate{
		private final int weekdayRegular;
		private final int weekendRegular;
		private final int weekdayReward;
		private final int weekendReward;
		
		public Rate(int weekdayRegular,int weekendRegular,int weekdayReward,int weekendReward) {
			this.weekdayRegular = weekdayRegular;
			this.weekendRegular = weekendRegular;
			this.weekdayReward = weekdayReward;
			this.weekendReward = weekendReward;
		}
		
		public int getWeekdayRegular() {
			return weekdayRegular;
		}

		public int getWeekendRegular() {
			return weekendRegular;
		}

		public int getWeekdayReward() {
			return weekdayReward;
		}

		public int getWeekendReward() {
			return weekendReward;
		}
	}
	
	public static class HotelRate{
		private final Hotel hotel;
		private final Rate rate;
		
		public HotelRate(Hotel hotel,Rate rate) {
			this.hotel = hotel;
			this.rate = rate;
		}

		public Hotel getHotel() {
			return hotel;
		}

		public Rate getRate() {
			return rate;
		}
	}

	public static Hotel findCheapestHotel(List<HotelRate> listOfHotels, CustomerType customerType, DayOfTheWeek dotw) {
		Hotel cheapest = null;
		int cheapestRate = Integer.MAX_VALUE;
		
		for(HotelRate temp:listOfHotels) {
			int rate = getRate(temp,customerType,dotw);
			if(rate<cheapestRate) {
				cheapest = temp.getHotel();
				cheapestRate = rate;
			} else if(rate == cheapestRate) {
				if(temp.getHotel().getRating()>cheapest.getRating()) {
					cheapest = temp.getHotel();
				}
			}
		}
		return cheapest;
	}
	
	public static int getRate(HotelRate hotel,CustomerType customerType,DayOfTheWeek dotw) {
		Rate rate = hotel.getRate();
		if(dotw.equals(DayOfTheWeek.WEEKDAY)) {
			return customerType.equals(CustomerType.REGULAR)?rate.getWeekdayRegular():rate.getWeekdayReward();
		}else {
			return customerType.equals(CustomerType.REGULAR)?rate.getWeekendRegular():rate.getWeekendReward();
		}
	}
	
	public static void printMessage(Hotel cheapestHotel) {
		System.out.println("Cheapest hotel for you is: "+cheapestHotel.getHotelName()+" with a rating of "+cheapestHotel.getRating());
	}
	
	
	public static void main(String[] args) {
		List<HotelRate> listOfHotels = new ArrayList<>();
		listOfHotels.add(new HotelRate(Hotel.MIAMI_BEACH,new Rate(80,110,50,90)));
		listOfHotels.add(new HotelRate(Hotel.MIAMI_DOWNTOWN,new Rate(120,90,100,80)));
		listOfHotels.add(new HotelRate(Hotel.MIAMI_MIDTOWN,new Rate(100,150,70,130)));
		
		Hotel cheapestHotel = findCheapestHotel(listOfHotels,CustomerType.REGULAR,DayOfTheWeek.WEEKDAY);
		
		printMessage(cheapestHotel);
		
		cheapestHotel = findCheapestHotel(listOfHotels,CustomerType.REGULAR,DayOfTheWeek.WEEKEND);
		
		printMessage(cheapestHotel);

		cheapestHotel = findCheapestHotel(listOfHotels,CustomerType.REWARD,DayOfTheWeek.WEEKDAY);
		
		printMessage(cheapestHotel);
		
		cheapestHotel = findCheapestHotel(listOfHotels,CustomerType.REWARD,DayOfTheWeek.WEEKEND);
		
		printMessage(cheapestHotel);
	}
}
