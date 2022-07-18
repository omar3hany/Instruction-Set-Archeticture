
public class MemoryAccess {
	static String[] mainMemory = new String[1024];
	//static String[] cache = new String[64];
	static Cache cache=new Cache(64,mainMemory);
	static String ReadData2;
	static PipeLineRegister MemToWB = new PipeLineRegister();
	static String ALUresult;
	static char MemToReg;
	static char RegDst;
	static char RegWrite;
	static String Rt;
	static String Rd;
	

	public static int binToDec(String bin,int power) {
        if(bin.isEmpty()) {
            return 0;
        }
        else {
            char curr=bin.charAt(bin.length()-1);
            int x=(int)Math.pow(2,(int)power);
            int y=Integer.parseInt(String.valueOf(curr));
            return (x*y)+binToDec(bin.substring(0, bin.length()-1),++power);
        }
        }
	

	public static void MemAccessMethod(String ALUresult,String Rd,String Rt ,String ReadData2,String SignExtend,char ZeroFlag,
		String BranchAddressResult ,char  MemWrite ,char MemRead,char MemToReg,char RegDst,char RegWrite,char Branch,char Jump) {
		
		System.out.println("///////////////Memory Access Phase////////////////");
		//System.out.println(ALUresult);
		int address=0;
		if(Jump!='1') {
			 address = binToDec(ALUresult, 0);//aluresult int
		}
		if (MemWrite=='1') {
			System.out.println("Writen "+ ReadData2 +" in address " + address);
			
			cache.insert(address,ReadData2);
		//	mainMemory[address]= ALUresult;
		}
		if (MemRead=='1') {
			ReadData2 = cache.get(address);
			
			//MemoryAccess.ReadData2 = mainMemory[address];
			System.out.println("Read "+ ReadData2 +" from address " + address);
			
		}
		if (Branch=='1') 
			if ( Integer.parseInt(ALUresult,2)==1) {
				InstructionFetch.PC =  BranchAddressResult;
				System.out.println("New PC is  "+ BranchAddressResult);//
				InstructionFetch.PC = BranchAddressResult;


			}
		if (Jump=='1') 
		{
				
				InstructionFetch.PC =  BranchAddressResult;
				System.out.println("New PC is  "+ BranchAddressResult);//
			}
	
		MemoryAccess.ReadData2 = ReadData2;
		MemoryAccess.MemToReg = MemToReg;
		MemoryAccess.RegDst = RegDst;
		MemoryAccess.RegWrite = RegWrite;
		MemoryAccess.ALUresult = ALUresult;
		MemoryAccess.Rd = Rd;
		MemoryAccess.Rt = Rt;

		
		



	}
	public static void updateMEMtoWB() {
		MemToWB.r.put("ALUresult", ALUresult);
		MemToWB.r.put("ReadData2", ReadData2);
		MemToWB.r.put("MemToReg", MemToReg+"");		
		MemToWB.r.put("RegDst", RegDst+"");
		MemToWB.r.put("RegWrite", RegWrite+"");
		MemToWB.r.put("Rd", Rd);
		MemToWB.r.put("Rt", Rt);

		
	}
}
