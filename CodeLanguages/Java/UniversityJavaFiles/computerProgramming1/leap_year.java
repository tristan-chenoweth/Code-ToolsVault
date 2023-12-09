import java.util.Scanner;

public class leap_year{

	public static void main(String[] args)  {

		int year;
		int current_year = 2016;

		Scanner input = new Scanner(System.in);

		System.out.print("What year were you born? ");

		year = input.nextInt();

		System.out.println("You have encountered the following leap years in your lifetime: ");

		while(year <= current_year){

			if((year % 4 == 0 && year % 100 != 0) || year % 400 == 0){

				System.out.println(year);

			}
				year++;
		}
	}
}