public class whileWithModulusOdd{
	public static void main(String[] args) {

		int i;
		i = 2;

		while(i < 100){
			if (i % 2 == 0 && i % 5 != 0){
				System.out.println(i);
			}
		i++;
		}
	}
}