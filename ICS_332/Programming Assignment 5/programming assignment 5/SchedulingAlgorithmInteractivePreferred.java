package schedulingalgorithms;

import schedulingsimulation.SchedulingAlgorithm;
import schedulingsimulation.SchedulingMechanisms;
import schedulingsimulation.SimulatedProcess;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class SchedulingAlgorithmInteractivePreferred implements SchedulingAlgorithm {
	LinkedList<SimulatedProcess> interactiveQueue = new LinkedList<SimulatedProcess>();
	LinkedList<SimulatedProcess> noninteractiveQueue = new LinkedList<SimulatedProcess>();
	boolean interactive = true;
	
	public SchedulingAlgorithmInteractivePreferred() {
		// nothing here
	}

	public void handleCPUBurstCompletionEvent(SimulatedProcess process) {
		if (interactiveQueue.contains(process)) { //If interactive contains the process
			interactiveQueue.remove(process);
		}
		else { //If process is not interactive
			noninteractiveQueue.remove(process);
		}
		if (!interactiveQueue.isEmpty()) {
			SchedulingMechanisms.dispatchProcess(interactiveQueue.peek(), 10);
		}
		else {
			SchedulingMechanisms.dispatchProcess(noninteractiveQueue.peek(), 10);
		}	
		
		
	}

	public void handleExpiredTimeSliceEvent(SimulatedProcess process) {
		if (interactiveQueue.contains(process)) {
			interactiveQueue.remove(process);
			interactive = false;
		}
		else {
			noninteractiveQueue.remove(process);
			interactive = false;
		}
		if (!interactiveQueue.isEmpty()) {
			SchedulingMechanisms.dispatchProcess(interactiveQueue.peek(), 10);
		}
		else {
			SchedulingMechanisms.dispatchProcess(noninteractiveQueue.peek(), 10);
		}	
	}

	public void handleProcessReadyEvent(SimulatedProcess process) {
		if (interactive) {
			interactiveQueue.addLast(process);
		}
		else {
			interactive = true;
			noninteractiveQueue.addLast(process);
		}

		if (!interactiveQueue.isEmpty()) {
			SchedulingMechanisms.dispatchProcess(interactiveQueue.peek(), 10);
		}
		else {
			SchedulingMechanisms.dispatchProcess(noninteractiveQueue.peek(), 10);
		}
	}
}
