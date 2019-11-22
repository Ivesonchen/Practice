//Question No.6
public class Sequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[args.length];
		for(int i = 0; i < args.length; i++) {
			arr[i] = Integer.parseInt(args[i]);
		}
		boolean flag = false;
		
		for(int i = 0; i < arr.length - 2; i++) {
			if(arr[i] == 1 && arr[i + 1] == 2 && arr[i + 2] == 3) flag = true;
		}
		
		System.out.println("Is there 1,2,3 sequence in the array? " + flag);
	}
}