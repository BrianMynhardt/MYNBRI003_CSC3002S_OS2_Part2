package dishWashS;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class WetDishRack {
	// add variables
	Semaphore mutex = new Semaphore(1);;
	Semaphore consumer;
	Semaphore producer;
	int[] dishes;
	int dish;

	WetDishRack(int rackSize) {
	    // add correct code
		dishes = new int[rackSize];
		consumer = new Semaphore(0);
		producer = new Semaphore(rackSize);
	}
	
	public void addDish(int dish_id)  throws InterruptedException {
		// add correct code here
		producer.acquire();
		mutex.acquire();

		int i = 0;
		while(dishes[i] != 0){
			i++;
		}
		dishes[i]=dish_id;

		mutex.release();
		consumer.release();


	}
	
	public int removeDish() throws InterruptedException {
		// replace with correct code

		consumer.acquire();
		mutex.acquire();

		for (int i = 0; i <dishes.length ; i++) {
			if(dishes[i] != 0){
				dish = dishes[i];
				dishes[i]=0;
			}
		}
		mutex.release();
		producer.release();
		return dish;
	}
	
}



