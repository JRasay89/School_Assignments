
public class FlipFlop {

    public static void job1(int x) throws InterruptedException {
    	for (int i = 0; i < 20; i++) {
    		System.out.print("Flip\n");
    		Thread.sleep(x);
    	}
    }
    
    public static void job2(int y) throws InterruptedException {
    	for (int i = 0; i < 20; i++) {
    		System.err.print("Flop\n");
    		Thread.sleep(y);
    	}
    }
    
	public static void main(String[] args){
		final int x;
		final int y;
		x = Integer.parseInt(args[0]);
		y = Integer.parseInt(args[1]);
		Thread t1 = new Thread() {
			public void run () {
				try {
					job1(x);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		Thread t2= new Thread() {
			public void run () {
				try {
					job2(y);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		t1.start();
		t2.start();
	}
}

