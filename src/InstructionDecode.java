
public class InstructionDecode {
	static String opCode;
	static String Rt;
	static String Rs;
	static String Rd;
	static String op2;
	static String IJ;
	static String II;
	static char RegDst;
	static char RegWrite;
	static char ALUSrc;
	static char PCSrc;
	static char MemRead;
	static char MemWrite;
	static char MemToReg;
	static char Jump;
	static String ALUOp;
	static String readData1;
	static String readData2;
	static String[] registers = new String[32];
	static String SignExtend;
	static String shift;
	static String PC;
	static PipeLineRegister IDtoEX = new PipeLineRegister();;
	

	
	public static void InstDecode(String instruction,String Pc) {
		//System.out.println(Integer.parseInt(InstructionFetch.PC, 2));
		opCode = getOpcode(instruction);
		Rs = getRs(instruction);
		Rt = getRt(instruction);
		Rd = getRd(instruction);
		II = getImmediate(instruction);
		op2 = getOp2(instruction);
		shift = getShift(instruction);
		readData1 = registers[Integer.parseInt(Rs,2)];
		readData2 = registers[Integer.parseInt(Rt,2)];
		SignExtend=SignExtend(II);
		PC = Pc;
		ContUnit(opCode);
		System.out.println();
		System.out.println("/////////////////DECODE PHASE/////////////");
		System.out.println("opcode is " + opCode);
		System.out.println("Read data 1  is " + readData1);
		System.out.println("Read data 2  is " + readData2);
		System.out.println("Immediate value  is " + II);
		System.out.println("Next PC is " + Pc);
		System.out.println("Sign extend is " + SignExtend);
		System.out.println("RegDst is " + RegDst);
		System.out.println("RegWrite is " + RegWrite);
		System.out.println("ALUSrc is " + ALUSrc);
		System.out.println("PCSrc is " + PCSrc);
		System.out.println("MemRead is " + MemRead);
		System.out.println("MemWrite is " + MemWrite);
		System.out.println("MemToReg is " + MemToReg);
		System.out.println("ALUOp is " + ALUOp);
		
		
		



	
		
		
	}
	public static void updateIDtoEX() {
		InstructionDecode.IDtoEX.r.put("opCode", InstructionDecode.opCode);
		InstructionDecode.IDtoEX.r.put("readData1", InstructionDecode.readData1);
		InstructionDecode.IDtoEX.r.put("readData2", InstructionDecode.readData2);
		InstructionDecode.IDtoEX.r.put("SignExtend", InstructionDecode.SignExtend);
		InstructionDecode.IDtoEX.r.put("RegDst", InstructionDecode.RegDst+"");
		InstructionDecode.IDtoEX.r.put("RegWrite", InstructionDecode.RegWrite+"");
		InstructionDecode.IDtoEX.r.put("ALUSrc", InstructionDecode.ALUSrc+"");
		InstructionDecode.IDtoEX.r.put("Branch", InstructionDecode.PCSrc+"");
		InstructionDecode.IDtoEX.r.put("MemRead", InstructionDecode.MemRead+"");
		InstructionDecode.IDtoEX.r.put("MemWrite", InstructionDecode.MemWrite+"");
		InstructionDecode.IDtoEX.r.put("ALUOp", InstructionDecode.ALUOp);
		InstructionDecode.IDtoEX.r.put("PC", InstructionDecode.PC);
		InstructionDecode.IDtoEX.r.put("Jump", InstructionDecode.Jump+"");
		InstructionDecode.IDtoEX.r.put("MemToReg", InstructionDecode.MemToReg+"");
		InstructionDecode.IDtoEX.r.put("Rt", Rt);
		InstructionDecode.IDtoEX.r.put("Rd", Rd);
	}
	public static String SignExtend(String immediate) {
		String res = immediate;
		while(res.length() <32)
			res = immediate.charAt(0) + res ;
		return res;
		
	}
	public static void ContUnit(String OpCode) {
		switch(opCode) {
		case "0000000":RegDst = '1' ; RegWrite = '1' ;ALUSrc = '0'; PCSrc = '0';MemRead = '0';MemWrite = '0';MemToReg = '0';ALUOp =  "01";Jump = '0';break;//subtract
		case "0000001":RegDst = '1' ; RegWrite = '1' ;ALUSrc = '0'; PCSrc = '0';MemRead = '0';MemWrite = '0';MemToReg = '0';ALUOp =  "00";Jump = '0';break;//addition
		case "0000010":RegDst = '1' ; RegWrite = '1' ;ALUSrc = '0'; PCSrc = '0';MemRead = '0';MemWrite = '0';MemToReg = '0';ALUOp =  "10";Jump = '0';break;//multiplication
		case "0000011":RegDst = '0' ; RegWrite = '1' ;ALUSrc = '1'; PCSrc = '0';MemRead = '0';MemWrite = '0';MemToReg = '0';ALUOp =  "10";Jump = '0';break;//OR immmediate
		case "0000100":RegDst = '0' ; RegWrite = '1' ;ALUSrc = '1'; PCSrc = '0';MemRead = '1';MemWrite = '0';MemToReg = '1';ALUOp =  "00";Jump = '0';break;//LW
		case "0000101":RegDst = 'x' ; RegWrite = '0' ;ALUSrc = '1'; PCSrc = '0';MemRead = '0';MemWrite = '1';MemToReg = 'x';ALUOp =  "00";Jump = '0';break;//SW
		case "0000110":RegDst = '1' ; RegWrite = '1' ;ALUSrc = '0'; PCSrc = '0';MemRead = '0';MemWrite = '0';MemToReg = '0';ALUOp =  "10";Jump = '0';break;//And
		case "0000111":RegDst = '0' ; RegWrite = '1' ;ALUSrc = '1'; PCSrc = '0';MemRead = '0';MemWrite = '0';MemToReg = '0';ALUOp =  "00";Jump = '0';break;//AddI
		case "0001000":RegDst = 'x' ; RegWrite = '0' ;ALUSrc = '0'; PCSrc = '1';MemRead = '0';MemWrite = '0';MemToReg = 'x';ALUOp =  "10";Jump = '0';break;//bne
		case "0001001":RegDst = 'x' ; RegWrite = '0' ;ALUSrc = '0'; PCSrc = '1';MemRead = '0';MemWrite = '0';MemToReg = 'x';ALUOp =  "10";Jump = '0';break;//bgt
		case "0001010":RegDst = '1' ; RegWrite = '1' ;ALUSrc = '0'; PCSrc = '0';MemRead = '0';MemWrite = '0';MemToReg = '0';ALUOp =  "10";Jump = '0';break;//slt
		case "0001011":RegDst = 'x' ; RegWrite = 'x' ;ALUSrc = 'x'; PCSrc = '0';MemRead = 'x';MemWrite = 'x';MemToReg = 'x';ALUOp =  "xx";Jump = '1';break;//jump
		case "0001100":RegDst = '1' ; RegWrite = '1' ;ALUSrc = '0'; PCSrc = '0';MemRead = '0';MemWrite = '0';MemToReg = '0';ALUOp =  "10";Jump = '0';break;//sll
		case "0001101":RegDst = '1' ; RegWrite = '1' ;ALUSrc = '0'; PCSrc = '0';MemRead = '0';MemWrite = '0';MemToReg = '0';ALUOp =  "10";Jump = '0';break;//srl
		



		
	/*	
		case "100011": RegDst = '0' ; RegWrite = '1' ;ALUSrc = '1'; PCSrc = '0';MemRead = '1';MemWrite = '0';MemToReg = '1';ALUOp =  "00";break;
		case "101011": RegDst = 'x' ; RegWrite = '0' ;ALUSrc = '1'; PCSrc = '0';MemRead = '0';MemWrite = '1';MemToReg = 'x';ALUOp =  "00";break;
		case "000100": RegDst = 'x' ; RegWrite = '0' ;ALUSrc = '0'; PCSrc = '1';MemRead = '0';MemWrite = '0';MemToReg = 'x';ALUOp =  "01";break;
		case "000000": RegDst = '1' ; RegWrite = '1' ;ALUSrc = '0'; PCSrc = '0';MemRead = '0';MemWrite = '0';MemToReg = '0';ALUOp =  "10";break;*/
		}
		
	}
	
	
	public static String getOpcode(String instruction) {
		String opcode = instruction.substring(0, 7);
		return opcode;	
	}
	public static String getShift(String instruction) {
		String opcode = instruction.substring(22, 27);
		return opcode;	
	}
	public static String getOp2(String instruction) {
		String funct = instruction.substring(27, 32);
		return funct;	
	}
	public static String getRs(String instruction) {
		String funct = instruction.substring(7, 12);
		return funct;	
	}
	public static String getRt(String instruction) {
		String funct = instruction.substring(12, 17);
		return funct;	
	}
	public static String getRd(String instruction) {
		String funct = instruction.substring(17, 22);
		return funct;	
	}
	public static String getImmediate(String instruction) {
		String funct = instruction.substring(22, 32);
		return funct;	
	}
	


	
	
	

}

 