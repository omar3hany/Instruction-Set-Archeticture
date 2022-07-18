
public class InstructionFetch {
	static String PC ;
	static String[] instructionMemory = new String[1024];
	static String fetchedInst;
	static PipeLineRegister IFtoID = new PipeLineRegister();
	
	
	public static void InstFetch(String PC) {
		int address = Integer.parseInt(PC,2);
		fetchedInst = instructionMemory[address/4];
		InstructionFetch.PC =extendto32(PC);
		System.out.println();
		System.out.println("/////////////Fetching PHASE/////////////////");
		System.out.println("fetched instruction is " + fetchedInst);
		System.out.println("Next PC is " + ProgCount());
		InstructionFetch.PC = ProgCount();

		
	}
	public static void updateIFtoID() {
		InstructionFetch.IFtoID.r.put("PC",  InstructionFetch.ProgCount());
		InstructionFetch.IFtoID.r.put("fetchedInst",  InstructionFetch.fetchedInst);
	}
	public static String extendto32(String PC) {
		String res = PC;
		while(res.length() <32)
			res = "0" + res ;
		return res;
		
	}
	public static String ProgCount() {
		int x = Integer.parseInt(PC, 2);
		x = x + 4 ;
		return extendto32(Integer.toBinaryString(x));
		
	}
	public static void fill() {
		
			//instructionMemory[i]= "10001110000010010000000000000000";
			instructionMemory[7]= "00000010000100001000010000100000";//add
			instructionMemory[1]= "00001010000110000000000000001100";//sw
			instructionMemory[2]= "00001000101010000000000000001100";//lw
			instructionMemory[3]= "00000110010100101000000000000101";//ORi
			instructionMemory[4]= "00010100011000101010000000000000";//slt
			instructionMemory[5]= "00011011001000001100100000000000";//srl
			instructionMemory[0]= "00010110000000000000000000000001";//jump
			instructionMemory[6]= "00001111111111111000000000000001";//addi
			//instructionMemory[0]= "00010000000101001000000000000011";

		}
	}
	


