package vmmanager;

import vmsimulation.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * An "empty" class that is to be copied into VirtualMemoryManagerV0 (Question #2),
 * which is then copied into VirtualMemoryManagerV1 (Question #3), etc. The Java
 * package you turn in should contain all copies, since each is tested separately
 * using the SystemSimulator class. 
 * 
 * @author Henri Casanova
 *
 */
public class VirtualMemoryManagerV3 {
		
	MainMemory memory;
	BackingStore disk;
	Integer pageSize;
	BitwiseToolbox btb;
	int[] pageTable;   //The page table
	int[] dirtyTable; //-1 if not dirty, 0 if dirty
	LinkedList<Integer> frameTable; //The frame table
	int nextFrame = 0; //The frame to be use
	int numOfPages = 0; //The number of pages
	int numOfFrames = 0; //The number of frames
	int frameIndex = 0;
	int bitCounter = 0; //the bit number for memory
	int pageFault = 0; //The number of pageFaults
	int byteCount = 0; //The number of bytes transferred between disk and memory

        /**
         * Creates a virtual memory manager that manages a main memory and a disk,
         * using a given page size (in bytes) - To be implemented for all versions.
         * 
         * @param memory The main memory
         * @param disk The disk
         * @param pageSize The page size
         * @throws MemoryException If parameters are invalid
         */
        public VirtualMemoryManagerV3 (MainMemory memory,BackingStore disk,Integer pageSize) throws MemoryException {
                // TO IMPLEMENT: V0, V1, V2, V3, V4
		this.memory = memory;
		this.disk = disk;
		this.pageSize = pageSize;
		this.numOfPages = this.disk.size()/this.pageSize; //Getting the number of pages
		this.numOfFrames = this.memory.size()/this.pageSize; //Getting the number of frames
		this.pageTable = new int[numOfPages];
		this.dirtyTable = new int[numOfPages];
		this.frameTable = new LinkedList<Integer>();
		 //Initializing the pageTable with -1, for empty
		for(int i=0;i<pageTable.length;i++){
			pageTable[i]= -1;   
		}
		//Initializing the dirtyTable as not dirty(-1)
		for(int i = 0; i < dirtyTable.length; i++) {
			dirtyTable[i] = -1;
		}
		//Getting the bits from the memory
		int i = memory.size();
		while (i > 1) {
			i = i/2;
			this.bitCounter++;
		}
        }

               /**
         * Writes a byte to memory given a 32-bit integer whose low bits are
         * interpreted as a virtual address - To be implemented for all version.
         * 
         * @param fourByteInteger A 32-bit integer
         * @param value The byte Value written to memory at the relevant address
         * @throws MemoryException If there is an invalid access
         */
        public void writeByte(Integer fourByteInteger, byte value) throws MemoryException {
                // TO IMPLEMENT: V0, V1, V2, V3, V4
		int pageNumber = fourByteInteger/this.pageSize;
		byte[] values = disk.readPage(pageNumber);
		byte readValue; //The value to be return
		int address; //The address to be use
		int getFrame;
		if (this.pageTable[pageNumber] == -1) { //if the page is not in memory
			if (frameTable.size() == this.numOfFrames) { //If frame table is full
				if (this.dirtyTable[frameTable.peek()] == -1) {
					System.out.println("Evicting page " + frameTable.peek() + " (NOT DIRTY)");
					this.nextFrame = this.pageTable[frameTable.peek()];
				}
				else {
					System.out.println("Evicting page " + frameTable.peek());
					this.nextFrame = this.pageTable[frameTable.peek()];
					//Evicting page contents back
					getFrame = this.pageTable[frameTable.peek()];
					address = this.pageSize * getFrame;
					byte[] values2 = new byte[this.pageSize];
					for (int j = 0; j < this.pageSize; j++) { //Putting the evicted page contents back to disk
						values2[j] = memory.readByte(address);
						address++;
						this.byteCount++;
					}
					disk.writePage(frameTable.peek(), values2);
					dirtyTable[frameTable.peek()] = -1; //resets the dirty page
				}
				this.pageTable[frameTable.peek()] = -1;
				this.frameTable.remove();
			}
			System.out.println("Bringing page " + pageNumber + " into frame " + this.nextFrame);
			this.pageTable[pageNumber] = this.nextFrame; //Adding the frame in the page table
			frameTable.addLast(pageNumber);
			address = this.nextFrame * this.pageSize; //getting the physical adress
			for (int i = 0; i < this.pageSize; i++) {
				memory.writeByte(address, values[i]);
				address++;
				this.byteCount++;
			}
			address = (this.pageSize * this.nextFrame) + (fourByteInteger - (pageNumber * this.pageSize));//translating the address to physical
			System.out.println("RAM: " + this.btb.getBitString(address, bitCounter - 1) + " <-- " + value);
			memory.writeByte(address, value);
			this.nextFrame++;
			this.pageFault++;
			//Setting the pageTable to dirty
			if (this.dirtyTable[pageNumber] == -1) {
				this.dirtyTable[pageNumber] = 0; 
			}
		}
		else { //else the page is in memory
			System.out.println("Page " + pageNumber + " is in memory");
			getFrame = this.pageTable[pageNumber];
			address = (this.pageSize * getFrame) + (fourByteInteger - (pageNumber * this.pageSize));//translating the address to physical
			System.out.println("RAM: " + this.btb.getBitString(address, bitCounter - 1) + " <-- " + value);
			memory.writeByte(address, value);
			//Setting the pageTable to dirty
			if (this.dirtyTable[pageNumber] == -1) {
				this.dirtyTable[pageNumber] = 0; 
			}
		}
        }

        /**
         * Reads a byte from memory given a 32-bit integer whose low bits are
         * interpreted as a virtual address - To be implemented for all versions.
         * 
         * @param fourByteInteger A 32-bit integer
         * @return The byte value read from memory at the relevant address
         * @throws MemoryException If there is an invalid access
         */
        public Byte readByte(Integer fourByteInteger) throws MemoryException {
                // TO IMPLEMENT: V0, V1, V2, V3, V4
		int pageNumber = fourByteInteger/this.pageSize;
		byte[] values = disk.readPage(pageNumber);
		byte readValue; //The value to be return
		int address;//The address to be use
		int getFrame;
		if (this.pageTable[pageNumber] == -1) { //if the page is not in memory
			if (frameTable.size() == this.numOfFrames) {
				if (dirtyTable[frameTable.peek()] == -1) {
					System.out.println("Evicting page " + frameTable.peek() + " (NOT DIRTY)");
					this.nextFrame = this.pageTable[frameTable.peek()];
				}
				else {
					System.out.println("Evicting page " + frameTable.peek());
					this.nextFrame = this.pageTable[frameTable.peek()];
					getFrame = this.pageTable[frameTable.peek()];
					address = this.pageSize * getFrame;
					byte[] values2 = new byte[this.pageSize];
					for (int j = 0; j < this.pageSize; j++) { //Putting the evicted page contents back to disk
						values2[j] = memory.readByte(address);
						address++;
						this.byteCount++;
					}
					disk.writePage(frameTable.peek(), values2);
					dirtyTable[frameTable.peek()] = -1; //resets the dirty page
				}	
				this.pageTable[frameTable.peek()] = -1;
				this.frameTable.remove();
			}
			System.out.println("Bringing page " + pageNumber + " into frame " + this.nextFrame);
			this.pageTable[pageNumber] = this.nextFrame; //assigning the page to the next available frame 
			frameTable.addLast(pageNumber);
			address = this.nextFrame * this.pageSize; //getting the physical adress
			for (int i = 0; i < this.pageSize; i++) {
				memory.writeByte(address, values[i]);
				address++;
				this.byteCount++;
			}
			address = (this.pageSize * this.nextFrame) + (fourByteInteger - (pageNumber * this.pageSize));//translating the address to physical
			System.out.println("RAM: " + this.btb.getBitString(address, bitCounter - 1) + " --> " + 					    	    memory.readByte(address));
			readValue = memory.readByte(address);
			this.nextFrame++;
			this.pageFault++;
			
		}
		else {//else the page is in memory
			System.out.println("Page " + pageNumber + " is in memory");
			getFrame = this.pageTable[pageNumber]; //Getting the frame number in the page table
			address = (this.pageSize * getFrame) + (fourByteInteger - (pageNumber * this.pageSize)); //translating the address to physical
			System.out.println("RAM: " + this.btb.getBitString(address, bitCounter - 1) + " --> " + 					    	    memory.readByte(address));
			readValue = memory.readByte(address);
		}

		return readValue;
                
        }

        /**
         * Prints the content of memory as a sequence of lines 
         * with the format "binaryPhysicalAddress: byteValue" to System.out -
         * To be implemented for all versions.
         * 
         * @throws MemoryException If there is an invalid access
         */
        public void printMemoryContent() throws MemoryException {
                // TO IMPLEMENT: V0, V1, V2, V3, V4
		for (int i = 0; i < this.memory.size(); i++) {
			System.out.println(this.btb.getBitString(i, bitCounter - 1) + ": " + memory.readByte(i));
		}
        }

	/**
         * Prints the content of the disk as a sequence of lines
         * with the format "PAGE pageNumber: byteValue, byteValue, ... byteValue" to
         * System.out, where pageNumber is a virtual page number and
         * the list of byte values corresponds to all bytes
         * in a given page on disk - To be implemented in versions V1 and above. 
         *  
         * @throws MemoryException If there is an invalid access
         */
        public void printDiskContent() throws MemoryException {
                // TO IMPLEMENT: V1, V2, V3, V4
		byte[] values;
                for (int i = 0; i < numOfPages; i++) {
			values = disk.readPage(i);
			System.out.print("PAGE " +i+ ": ");
			for (int j = 0; j < values.length; j++) {
				if (j + 1 == values.length) {
					System.out.print(values[j]);
				}
				else {
					System.out.print(values[j] + ",");
				}
			}
			System.out.println();
		}
		
                return;
        }

        /**
         * Writes all pages currently in memory frames back to disk - 
         * To be implemented in versions V1 and above.
         * 
         * @throws MemoryException If there is an invalid access
         */
        // Method to write back all pages to disk
        public void writeBackAllPagesToDisk() throws MemoryException {
                // TO IMPLEMENT: V1, V2, V3, V4
		byte[] values = new byte[this.pageSize];
		int address;	
		int getFrame;
		for (int i = 0; i < this.pageTable.length; i++) {
			if (this.pageTable[i] == -1 || dirtyTable[i] == -1) { //Don't write back to page if not dirty
				continue;
			}
			else {
				getFrame = this.pageTable[i];
				address = this.pageSize * getFrame;
				for (int j = 0; j < this.pageSize; j++) {
					values[j] = memory.readByte(address);
					address++;
					this.byteCount++;
				}
				this.disk.writePage(i, values);
			}
		}

        }

        /**
         * Returns the number of page faults that have occurred to date - 
         * To be implemented in versions V1 and above.
         * 
         * @return Number of page faults
         */
        public int getPageFaultCount() {
                // TO IMPLEMENT: V1, V2, V3, V4
        

                return this.pageFault;
        }

        /**
         * Returns the number of bytes that have been transfered back and forth between the memory
         * and the disk to date -
         * To be implemented in versions V1 and above.
         * 
         * @return Number of bytes transferred
         */
        public int getTransferedByteCount() {
                // TO IMPLEMENT: V1, V2, V3, V4

                return this.byteCount;
        }
}
