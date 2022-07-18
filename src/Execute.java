
public class Execute {
	static String ALUresult;
	static char ZeroFlag;
	static String BranchAddressResult;
	static String ReadData2;
	static String PC;
	static PipeLineRegister EXtoMEM = new PipeLineRegister();
	static char MemRead;
	static char MemWrite;
	static char Branch;
	static char MemToReg;
	static char RegDst;
	static char RegWrite;
	static char Jump;
	static String SignExtend;
	static String Rt;
	static String Rd;
	
	
	
	public static void ExecuteMethod(String ALUOp,String Rd,String Rt,String opCode,char ALUSrc, String ReadData1 , String ReadData2 ,String SignExtend,char  MemWrite ,char MemRead,
			char Branch,char MemToReg, char RegDst,char RegWrite, char Jump , String PC) {
		
		//if(Jump=='0') {
		if(ALUSrc=='1')
			ReadData2 = SignExtend;
		
		switch(ALUOp) {
		case"00":ALUEvaluator("0010", ReadData1, ReadData2);break;
		case"01":ALUEvaluator("0110", ReadData1, ReadData2);break;
		case"10":ALUEvaluator(opCode, ReadData1, ReadData2);break;
		
		}
		int sign = Integer.parseInt(SignExtend, 2)*4;
		int pc = Integer.parseInt(PC, 2);
		Execute.PC = PC;
		BranchAddressResult = InstructionFetch.extendto32(Integer.toBinaryString(sign + pc));//

		System.out.println("BranchAddressResult is " + BranchAddressResult);
		System.out.println("Next PC is " + PC);
		Execute.MemWrite = MemWrite;
		Execute.MemRead = MemRead;
		Execute.Branch = Branch;
		Execute.MemToReg = MemToReg;
		Execute.RegDst = RegDst;
		Execute.RegWrite = RegWrite;
		Execute.SignExtend = SignExtend;
		Execute.Rd = Rd;
		Execute.Rt = Rt;
		Execute.ReadData2 = ReadData2;
		Execute.Jump=Jump;

		

		

	

		
		
	}
	public static void output(String Op, int Operand1 , int Operand2 , char zero , int output) {
			ALUresult = InstructionFetch.extendto32(Integer.toBinaryString(output));
			ZeroFlag = zero;
			System.out.println();
			System.out.println("/////////////////EXECUTE PHASE///////////////////");
			System.out.println("Aluresult is " + ALUresult);
			System.out.println("Zero flag is " + ZeroFlag);
			
			
			
			
			/*System.out.println("Operation Name :" + Op + "\n"
					+"1st Operand : " + Integer.toBinaryString(Operand1) + "\n"
					+"2nd Operand : " + Integer.toBinaryString(Operand2) + "\n"
					+"Output : " + Integer.toBinaryString(output) + "\n"
					+"Z Flag Value : " + ZeroFlag);*/
	
			
		}
	public static void updateEXtoMEM() {
		Execute.EXtoMEM.r.put("ALUresult", ALUresult);
		Execute.EXtoMEM.r.put("ZeroFlag", ZeroFlag+"");
		Execute.EXtoMEM.r.put("PC", PC);
		Execute.EXtoMEM.r.put("BranchAddressResult", BranchAddressResult);
		Execute.EXtoMEM.r.put("ReadData2", ReadData2);
		Execute.EXtoMEM.r.put("MemRead", MemRead+"");
		Execute.EXtoMEM.r.put("Branch", Branch+"");
		Execute.EXtoMEM.r.put("MemWrite", MemWrite+"");

		Execute.EXtoMEM.r.put("MemToReg", MemToReg+"");
		Execute.EXtoMEM.r.put("RegDst", RegDst+"");
		Execute.EXtoMEM.r.put("RegWrite", RegWrite+"");
		Execute.EXtoMEM.r.put("Jump", Jump+"");
		Execute.EXtoMEM.r.put("SignExtend", SignExtend);
		Execute.EXtoMEM.r.put("Rt", Rt);
		Execute.EXtoMEM.r.put("Rd", Rd);

	}
	public static void ALUEvaluator ( String Op, String Operand1 , String Operand2 )
	{
		int first = Integer.parseInt(Operand1,2);
		int second = Integer.parseInt(Operand2,2);		
		switch(Op) 
		{

		case"0000":output("AND", first, second, ((first & second)==0) ? '1' : '0' , (first & second) );break;	
		case"0001":output("OR",  first, second, ((first | second)==0) ? '1' : '0' , (first | second)  );break;
		case"0010":output("add", first, second, ((first + second)==0) ? '1' : '0' , (first + second) );break;
		case"0110":output("sub", first, second, ((first - second)==0) ? '1' : '0' , (first - second) );break;
		case"0111":output("slt", first, second, (((first <second)? 1 : 0)==0) ? '1' : '0' , ((first <second)? 1 : 0));break;
		case"1000":output("bne", first, second, (((first != second)? 1 : 0)==0) ? '1' : '0' , ((first != second)? 1 : 0));break;
		case"1001":output("bgt", first, second, (((first > second)? 1 : 0)==0) ? '1' : '0' , ((first >second)? 1 : 0));break;
		case"1010":output("mult",first, second, ((first * second)==0) ? '1' : '0' , (first * second) );break;
		case"1011":output("sll", first, second, ((first << second)==0) ? '1' : '0' , (first << second) );break;
		case"1100":output("srl", first, second, ((first >> second)==0) ? '1' : '0' , (first >> second) );break;

		
		case"100000":ALUEvaluator("0010", Operand1, Operand2);break;
		case"100010":ALUEvaluator("0110", Operand1, Operand2);break;
		case"100100":ALUEvaluator("0000", Operand1, Operand2);break;
		case"100101":ALUEvaluator("0001", Operand1, Operand2);break;
		case"101010":ALUEvaluator("0111", Operand1, Operand2);break;
		case"0000010":ALUEvaluator("1010", Operand1, Operand2);break;
		case"0000011":ALUEvaluator("0001", Operand1, Operand2); ;break;
		case"0000110":ALUEvaluator("0000", Operand1, Operand2);break;
		case"0001000":ALUEvaluator("1000", Operand1, Operand2);break;
		case"0001001":ALUEvaluator("1001", Operand1, Operand2);break;
		case"0001010":ALUEvaluator("0111", Operand1, Operand2);break;
		case"0001100":ALUEvaluator("1011", Operand1, Operand2);break;
		case"0001101":ALUEvaluator("1100", Operand1, Operand2);break;



		
		
		
		
		}
		
	}
	

}
