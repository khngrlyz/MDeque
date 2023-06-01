package project3;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MDeque<Integer> test = new MDeque<>();
		test.pushBack(1);
		//test.pushBack(2);
		test.pushBack(3);
		//test.pushMiddle(0);
		System.out.println(test.toString());
		//test.pushMiddle(9);
		System.out.println(test.toString());
		System.out.println(test.peekMiddle());
		System.out.println(test.toString());
	}

}
