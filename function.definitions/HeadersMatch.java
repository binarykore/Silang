//TODO write a description for this script
//@author Jaede Sy
//@category HeadersMatch
//@keybinding 
//@menupath 
//@toolbar 

import ghidra.app.script.GhidraScript;
import ghidra.app.cmd.function.ApplyFunctionSignatureCmd;
import ghidra.program.model.mem.*;
import ghidra.program.model.lang.*;
import ghidra.program.model.pcode.*;
import ghidra.program.model.util.*;
import ghidra.program.model.reloc.*;
import ghidra.program.model.data.*;
import ghidra.program.model.block.*;
import ghidra.program.model.symbol.*;
import ghidra.program.model.scalar.*;
import ghidra.program.model.listing.*;
import ghidra.program.model.address.*;

public class HeadersMatch extends GhidraScript {

    public void run() throws Exception {
	var dmgr = currentProgram.getDataTypeManager();
	var symTable = currentProgram.getSymbolTable();
	var iter = dmgr.getAllDataTypes();
	while (iter.hasNext()) {
		var type = iter.next();
		if (!(type instanceof FunctionDefinition))
			continue;
		// println(type.getName());
		var matches = symTable.getGlobalSymbols(type.getName());

		println("match found: " + type.getName());

		for (var sym : matches) {
			ApplyFunctionSignatureCmd cmd = new ApplyFunctionSignatureCmd(sym.getAddress(),
					(FunctionDefinition) type, SourceType.IMPORTED);
			cmd.applyTo(currentProgram);
		}
	}

    }

}
