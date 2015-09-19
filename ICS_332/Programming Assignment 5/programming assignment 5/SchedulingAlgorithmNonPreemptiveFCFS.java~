package schedulingalgorithms;

import schedulingsimulation.SchedulingAlgorithm;
import schedulingsimulation.SchedulingMechanisms;
import schedulingsimulation.SimulatedProcess;
import java.util.LinkedList;
import java.util.Queue;

public class SchedulingAlgorithmNonPreemptiveFCFS implements SchedulingAlgorithm {
	LinkedList<SimulatedProcess> readyQueue = new LinkedList<SimulatedProcess>();
	public SchedulingAlgorithmNonPreemptiveFCFS() {
		// nothing here
	}

		public void handleCPUBurstCompletionEvent(SimulatedProcess process) {
		readyQueue.remove(process);
		if (readyQueue.size() != 0) {
			SchedulingMechanisms.dispatchProcess(readyQueue.getFirst(), 0);
		}
		
	}

	public void handleExpiredTimeSliceEvent(SimulatedProcess process) {
		// Do nothing, just print out some messages
		System.out.println("["+SchedulingMechanisms.getDate()+"] Scheduler: Process "
				+ SchedulingMechanisms.getProcessName(process)
				+ "'s timeslice has expired");
		System.out.println("["+SchedulingMechanisms.getDate()+"] Scheduler: (I should probably do something about 						this)");
	}

	public void handleProcessReadyEvent(SimulatedProcess process) {
		if (readyQueue.isEmpty()) {
			readyQueue.addFirst(process);
		}
		else {
			//Adds at the beginning of the queue
			if (SchedulingMechanisms.getProcessArrivalDate(process) < SchedulingMechanisms.getProcessArrivalDate(readyQueue.getFirst())) {
				readyQueue.addFirst(process);
			}
			//Adds at the end of the queue
			else if (SchedulingMechanisms.getProcessArrivalDate(process) > SchedulingMechanisms.getProcessArrivalDate(readyQueue.getLast())) {
				readyQueue.addLast(process);
			}
			//Adds in between the readyQueue
			else {
				for (int i = 0; i < readyQueue.size(); i++) {
					if ((SchedulingMechanisms.getProcessArrivalDate(process) > SchedulingMechanisms.getProcessArrivalDate(readyQueue.get(i))) && 			(SchedulingMechanisms.getProcessArrivalDate(process) < SchedulingMechanisms.getProcessArrivalDate(readyQueue.get(i+1)))) {
						readyQueue.add(i+1, process);
						break;
					}
				}
			}
		}
		//readyQueue.addFirst(process);
		SimulatedProcess running = SchedulingMechanisms.getRunningProcess();
		if (running == null) {
		    	SchedulingMechanisms.dispatchProcess(readyQueue.getFirst(), 0);
		}
	}
}
