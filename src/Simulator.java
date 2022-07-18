import java.util.LinkedList;
import java.util.Queue;

public class Simulator {
		static Queue<String> fetchQueue= new LinkedList<String>();
	
	public static void main(String[] args) {
		InstructionFetch.fill();
		for(int i = 0 ; i <32;i++)
			InstructionDecode.registers[i] = InstructionFetch.extendto32(Integer.toBinaryString(i));
		
			InstructionDecode.registers[9] = "00000000000000000000000000000000";
			MemoryAccess.mainMemory[0]="11111111111111110000000000000000";
			MemoryAccess.mainMemory[Integer.parseInt("0000000011111111", 2)] = "HEY";
			
			Queue<String> decode = new LinkedList<String>();
			Queue<String> execute = new LinkedList<String>();
			Queue<String> memory = new LinkedList<String>();
			Queue<String> registers = new LinkedList<String>();
			for(int i = 0 ; i <MemoryAccess.mainMemory.length;i++) {
				MemoryAccess.mainMemory[i]="00000000000000000000000000000000";
			}

			
		
				fetchQueue.add("00000000000000000000000000000000");
			

			


			
			int i = 1;
		
			while(true) {
				System.out.println();
				System.out.println("CYCLE " + i);
				
			String pc = fetchQueue.poll();
			if(pc!=null) {
				InstructionFetch.InstFetch(pc);
			}
			String pcdecode = decode.poll();
			if(pcdecode!=null) {
				InstructionDecode.InstDecode(InstructionFetch.IFtoID.r.get("fetchedInst"),InstructionFetch.IFtoID.r.get("PC"));
				}
			String pcexecute = execute.poll();
			if(pcexecute!=null) {
				Execute.ExecuteMethod(InstructionDecode.IDtoEX.r.get("ALUOp"),InstructionDecode.IDtoEX.r.get("Rd"),InstructionDecode.IDtoEX.r.get("Rt"),InstructionDecode.IDtoEX.r.get("opCode"),InstructionDecode.IDtoEX.r.get("ALUSrc").charAt(0),
						InstructionDecode.IDtoEX.r.get("readData1"), InstructionDecode.IDtoEX.r.get("readData2") ,
						InstructionDecode.IDtoEX.r.get("SignExtend"),InstructionDecode.IDtoEX.r.get("MemWrite").charAt(0)   ,InstructionDecode.IDtoEX.r.get("MemRead").charAt(0),
						InstructionDecode.IDtoEX.r.get("Branch").charAt(0)  ,InstructionDecode.IDtoEX.r.get("MemToReg").charAt(0) ,
						InstructionDecode.IDtoEX.r.get("RegDst").charAt(0) ,InstructionDecode.IDtoEX.r.get("RegWrite").charAt(0),  
						InstructionDecode.IDtoEX.r.get("Jump").charAt(0) ,InstructionDecode.IDtoEX.r.get("PC") );
				}
			String pcmemory = memory.poll();
			if(pcmemory!=null) {
				MemoryAccess.MemAccessMethod(Execute.EXtoMEM.r.get("ALUresult"),Execute.EXtoMEM.r.get("Rd"),Execute.EXtoMEM.r.get("Rt"), Execute.EXtoMEM.r.get("ReadData2"), 
						Execute.EXtoMEM.r.get("SignExtend"), Execute.EXtoMEM.r.get("ZeroFlag").charAt(0), Execute.EXtoMEM.r.get("BranchAddressResult"),
						Execute.EXtoMEM.r.get("MemWrite").charAt(0), Execute.EXtoMEM.r.get("MemRead").charAt(0),
						Execute.EXtoMEM.r.get("MemToReg").charAt(0) ,Execute.EXtoMEM.r.get("RegDst").charAt(0) ,
						Execute.EXtoMEM.r.get("RegWrite").charAt(0) , Execute.EXtoMEM.r.get("Branch").charAt(0),Execute.EXtoMEM.r.get("Jump").charAt(0));

				}
			String pcewrite = registers.poll();
			if(pcewrite!=null) {
				WriteBack.writeBackMethod(MemoryAccess.MemToWB.r.get("ALUresult"),MemoryAccess.MemToWB.r.get("Rd") ,MemoryAccess.MemToWB.r.get("Rt"),
						MemoryAccess.MemToWB.r.get("ReadData2"), 
						MemoryAccess.MemToWB.r.get("MemToReg").charAt(0) , MemoryAccess.MemToWB.r.get("RegDst").charAt(0),
						MemoryAccess.MemToWB.r.get("RegWrite").charAt(0));
				}
			
			if(pc!=null)
				decode.add(pc);
			if(pcdecode!=null)
				execute.add(pcdecode);
			if(pcexecute!=null)
				memory.add(pcexecute);
			if(pcmemory!=null)
				registers.add(pcmemory);
			
			if(pcmemory==null&&pc==null&&pcexecute==null&&pcmemory==null)
				break;
			
			if(InstructionFetch.instructionMemory[Integer.parseInt(InstructionFetch.PC,2)/4]!=null)
				fetchQueue.add(InstructionFetch.PC);

			
			
			//update iftoid
			InstructionFetch.updateIFtoID();
			
			//update idtoex
			InstructionDecode.updateIDtoEX();
			
			//update extomem
			Execute.updateEXtoMEM();
			
			//update memtowb
			MemoryAccess.updateMEMtoWB();
			
			i++;
			}
			
			/* for(int j=0;j<32;j++) {
		            System.out.println("reg"+j+": "+InstructionDecode.registers[j]);
		        }
*/
		
		}
		
		
		

		
		
	}

