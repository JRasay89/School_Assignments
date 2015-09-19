package schedulingalgorithms;

import schedulingsimulation.SchedulingAlgorithm;
import schedulingsimulation.SchedulingMechanisms;
import schedulingsimulation.SimulatedProcess;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class SchedulingAlgorithmPreemptiveFCFS implements SchedulingAlgorithm {
	LinkedList<SimulatedProcess> readyQueue = new LinkedList<SimulatedProcess>();
	public SchedulingAlgorithmPreemptiveFCFS() {
		// nothing here
	}

	public void handleCPUBurstCompletionEvent(SimulatedProcess process) {
		int index = readyQueue.indexOf(process);
		readyQueue.remove(process);
		if (readyQueue.size() != 0) {
			SchedulingMechanisms.dispatchProcess(readyQueue.get(index), 0);
			
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
		else if (readyQueue.contains(process)) {
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
					//If there are process with same arrival
					else if (SchedulingMechanisms.getProcessArrivalDate(process) == SchedulingMechanisms.getProcessArrivalDate(readyQueue.get(i))) {
						while (i < readyQueue.size() && SchedulingMechanisms.getProcessArrivalDate(process) == SchedulingMechanisms.getProcessArrivalDate(readyQueue.get(i))) {
							i++;
 						}
						if (i == readyQueue.size()) {
							readyQueue.addLast(process);
						}
						else {
							readyQueue.add(i, process);
						}
						break;
					}
				}
			}
		}
		SimulatedProcess running = SchedulingMechanisms.getRunningProcess();
		if (running == null) {
		    	SchedulingMechanisms.dispatchProcess(readyQueue.getFirst(), 0);
		}
		else {
			if (SchedulingMechanisms.getProcessArrivalDate(process) < SchedulingMechanisms.getProcessArrivalDate(running)) {
				SchedulingMechanisms.preemptRunningProcess();
				SchedulingMechanisms.dispatchProcess(readyQueue.getFirst(), 0);
			}
		}
	}
}
