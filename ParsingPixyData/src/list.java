import java.util.ArrayList;

public class list {
	public static int x = 0;
	public static int y = 0;
	public static int test = 0;
	public static ArrayList<Integer> list = new ArrayList<Integer>();

	public list() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] array = new int[10];
//		boolean blah = false;
//		if(test == 0)blah = true;
//		while(blah){
//			test = test + 10;
//			list.add(test);
//			for(int i = 0; i < list.size(); i++){
//				System.out.print(list.get(i) + " ");
//			}
//			System.out.println();
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
////			if(test == 10){
////				blah = false;
//			}
		array();
		}
	
	public static void array(){
		boolean blah = false;
		if(list.size() >= 0)blah = true;
		list.add(0, 85);
		list.add(1, -86);
		list.add(2, 85);
		list.add(3, -86);
		while(blah){
			test = test + 10;
			list.add(1);
			if(list.size() >= 9 && list.size() <= 12){
				list.add(test);
			}
				for(int i = 0; i < list.size(); i++){
					System.out.print(list.get(i) + " ");
				}
			
			System.out.println();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
		
	}


