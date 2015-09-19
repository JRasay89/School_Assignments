package schedulingalgorithms;

import schedulingsimulation.SchedulingAlgorithm;
import schedulingsimulation.SchedulingMechanisms;
import schedulingsimulation.SimulatedProcess;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class SchedulingAlgorithmRoundRobin implements SchedulingAlgorithm {
	LinkedList<SimulatedProcess> readyQueue = new LinkedList<SimulatedProcess>();
	public SchedulingAlgorithmRoundRobin() {
		// nothing here
	}

	public void handleCPUBurstCompletionEvent(SimulatedProcess process) {
		readyQueue.remove(process);
		if (!readyQueue.isEmpty()) {
			SchedulingMechanisms.dispatchProcess(readyQueue.peek(), 10);
		}
		
		
	}

	public void handleExpiredTimeSliceEvent(SimulatedProcess process) {
		readyQueue.remove(process);
		SchedulingMechanisms.dispatchProcess(readyQueue.peek(), 10);
	}

	public void handleProcessReadyEvent(SimulatedProcess process) {
		readyQueue.addLast(process);
		SimulatedProcess running = SchedulingMechanisms.getRunningProcess();
		if (running == null) {
			SchedulingMechanisms.dispatchProcess(process, 10);
		}
	}
}
