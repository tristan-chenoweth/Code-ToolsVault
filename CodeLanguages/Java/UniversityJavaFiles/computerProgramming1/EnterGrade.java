import java.util.Scanner;
	public class EnterGrade {
		public static void main (String []args){

			System.out.println ("Enter your score: ");

			Scanner input = new Scanner (System.in);

			double marks = input.nextDouble();

			if (marks>=90){
				System.out.println("A");
			}
			else if (marks>=80){
				System.out.println("B");
			}
			else if (marks>=70){
				System.out.println("C");
			}
			else if (marks>=60){
				System.out.println("D");
			}
			else {
				System.out.println("F");
			}


			if (marks>=80){
				System.out.println("Congrats");
			}
			else {
				System.out.println("You can do better than that you fucking bumbass motherfucker...you shithead");
			}

		}
	}
