package CSCI_e10.unit_3b;

public class SeasonTest {

	public static String season(int month, int day){
		String response = "impossible";
		
		if(month > 12 || day > 31){
			return response;
		}
		
		if(month == 2 && day > 28) {
			return response;
		}
		
		if(month == 4 || month == 6 || month == 9 || month == 11) {
			if( day > 30 ) {
				return response;
			}
		}
		
		if( (month > 11 && day > 15) || (month < 4 && day <= 15) ){
			return "winter";
		}
		
		if( (month > 2 && day > 15) || (month < 7 && day <= 15) ){
			return "spring";
		}
		
		if( (month > 5 && day > 15) || (month < 10 && day <= 15) ){
			return "summer";
		}
		
		if( (month > 8 && day > 15) || (month < 13 && day <= 15) ){
			return "fall";
		}
		
		return response;
	}
	
	public static void main(String[] args) {
		
		System.out.println("January 1 is " + season(1,1));
		System.out.println("March 1 is " + season(3,1));
		System.out.println("March 15 is " + season(3,15));
		System.out.println("March 16 is " + season(3,16));
		System.out.println("June 1 is " + season(6,1));
		System.out.println("Oct 1 is " + season(10,1));
		System.out.println("feb 29 is " + season(2,29));
		System.out.println("July 1 is " + season(7,1));
		
	}

}
