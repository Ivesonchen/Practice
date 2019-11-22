//Question No.7

public class Rotation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {3, 8, 9, 7, 6};
		int[] result = solution(a, 5, 0);
		for(int ele : result) {
			System.out.println(ele);
		}
	}
	
	public static int[] solution(int a[], int n, int k) {
		if(k == n) {
			return a;
		} else if(k < n) {
			reverse(a, 0, n-1);
			reverse(a, 0, k - 1);
			reverse(a, k, n-1);
			return a;
		} else {
			return solution(a, n, k % n);
		}
		
	}
	
	public static int[] reverse(int a[], int start, int end) {
		while( start < end) {
			int temp = a[start];
			a[start] = a[end];
			a[end] = temp;
			start ++;
			end --;
		}
		return a;
	}
}
