
public class WriteBack {
	public static void writeBackMethod(String ALUresult,String Rd,String Rt, String ReadData,char MemToReg, char RegDst,char RegWrite) {
		System.out.println();
		System.out.println("/////////////WRITE BACK PHASE///////////////");
		if(RegWrite=='1')
		{
		int destination ;
		if (RegDst=='1') 
			destination = Integer.parseInt(Rd,2);
		else 
			destination = Integer.parseInt(Rt,2);
		if(MemToReg=='1') {
			InstructionDecode.registers[destination]=ReadData;
			System.out.println("Writen "+ ReadData +" in register " + destination);
		}
		else {
			InstructionDecode.registers[destination]=ALUresult;
			System.out.println("Writen "+ ALUresult +" in register " + destination);

			}
		}
	}
			

}
