/**
 * LabSET 2014
 */
package org.labset.bytecode.source;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.labset.bytecode.FieldInfo;
import org.labset.bytecode.Opcode;
import org.labset.bytecode.Utils;
import org.labset.bytecode.attr.Code;
import org.labset.bytecode.attr.LineNumberTable;
import org.labset.bytecode.attr.LocalVariableTable;
import org.labset.bytecode.cp.CONSTANT_Fieldref;
import org.labset.bytecode.cp.CONSTANT_Utf8;
import org.labset.bytecode.cp.CPInfo;
import org.labset.bytecode.node.*;

/**
 * @author hasnaer
 *
 */
public class StatementBuilder {

  private final CPInfo[] constantPool;
  private final Code code;
  private final int start;
  private final int end;

  private final List<BaseNode> statements;
  private final Stack<BaseNode> stack;

  private final LocalVariableTable lvt;
  private final LineNumberTable lnt;

  private final byte[] byteCode;

  private boolean executed;

  public StatementBuilder(final CPInfo[] pConstantPool, final Code pCode) {
    this(pConstantPool, pCode, 0, pCode.getCode().length);
  }

  public StatementBuilder(final CPInfo[] pConstantPool, final Code pCode,
      final int pStart, final int pEnd) {
    constantPool = pConstantPool;
    code = pCode;
    start = pStart;
    end = pEnd;

    statements = new ArrayList<>();
    stack = new Stack<>();

    lvt = pCode.getLocalVariableTable();
    lnt = Utils.getAttribute(pCode.getAttributes(), LineNumberTable.class)
        .get();

    byteCode = code.getCode();
  }

  public List<BaseNode> build() throws Exception {
    if (!executed) {

      int i = start;
      while (i < end) {
        i = consume(i, byteCode[i] & 0xFF);
      }
      executed = true;
    }
    return statements;
  }

  private int consume(int pPos, int pOpcode) throws Exception {
    switch (pOpcode) {
      case Opcode.BIPUSH:
        return consumeBIPUSH(pPos);
      case Opcode.ISTORE_0:
      case Opcode.ISTORE_1:
      case Opcode.ISTORE_2:
      case Opcode.ISTORE_3:
        return consumeSTOREVARIABLE(pPos, pOpcode - Opcode.ISTORE_0);
      case Opcode.DSTORE_0:
      case Opcode.DSTORE_1:
      case Opcode.DSTORE_2:
      case Opcode.DSTORE_3:
        return consumeSTOREVARIABLE(pPos, pOpcode - Opcode.DSTORE_0);
      case Opcode.FSTORE_0:
      case Opcode.FSTORE_1:
      case Opcode.FSTORE_2:
      case Opcode.FSTORE_3:
        return consumeSTOREVARIABLE(pPos, pOpcode - Opcode.FSTORE_0);
      case Opcode.LSTORE_0:
      case Opcode.LSTORE_1:
      case Opcode.LSTORE_2:
      case Opcode.LSTORE_3:
        return consumeSTOREVARIABLE(pPos, pOpcode - Opcode.LSTORE_0);
      case Opcode.ASTORE_0:
      case Opcode.ASTORE_1:
      case Opcode.ASTORE_2:
      case Opcode.ASTORE_3:
        return consumeSTOREVARIABLE(pPos, pOpcode - Opcode.ASTORE_0);
      case Opcode.ISTORE:
      case Opcode.LSTORE:
      case Opcode.FSTORE:
      case Opcode.DSTORE:
      case Opcode.ASTORE:
        return consumeSTOREVARIABLE(pPos + 1, byteCode[pPos + 1]);

      case Opcode.ILOAD_0:
      case Opcode.ILOAD_1:
      case Opcode.ILOAD_2:
      case Opcode.ILOAD_3:
        return consumeLOADVARIABLE(pPos, pOpcode - Opcode.ILOAD_0);

      case Opcode.FLOAD_0:
      case Opcode.FLOAD_1:
      case Opcode.FLOAD_2:
      case Opcode.FLOAD_3:
        return consumeLOADVARIABLE(pPos, pOpcode - Opcode.FLOAD_0);

      case Opcode.DLOAD_0:
      case Opcode.DLOAD_1:
      case Opcode.DLOAD_2:
      case Opcode.DLOAD_3:
        return consumeLOADVARIABLE(pPos, pOpcode - Opcode.DLOAD_0);

      case Opcode.LLOAD_0:
      case Opcode.LLOAD_1:
      case Opcode.LLOAD_2:
      case Opcode.LLOAD_3:
        return consumeLOADVARIABLE(pPos, pOpcode - Opcode.LLOAD_0);

      case Opcode.ALOAD_0:
      case Opcode.ALOAD_1:
      case Opcode.ALOAD_2:
      case Opcode.ALOAD_3:
        return consumeLOADVARIABLE(pPos, pOpcode - Opcode.ALOAD_0);

      case Opcode.ILOAD:
      case Opcode.LLOAD:
      case Opcode.DLOAD:
      case Opcode.FLOAD:
        return consumeLOADVARIABLE(pPos + 1, byteCode[pPos + 1]);

      case Opcode.IADD:
      case Opcode.FADD:
      case Opcode.DADD:
      case Opcode.LADD:
        return consumeOPERATION(pPos, Operation.Type.ADD);

      case Opcode.ARETURN:
      case Opcode.IRETURN:
      case Opcode.DRETURN:
      case Opcode.FRETURN:
      case Opcode.LRETURN:
        return consumeRETURNVALUE(pPos);

      case Opcode.PUTFIELD:
        return consumePUTFIELD(pPos);

      case Opcode.RETURN:
        return consumeRETURN(pPos);
        
      case Opcode.NEW:
        return consumeNEW(pPos);
      case Opcode.DUP:
        return consumeDUP(pPos);
        
      case Opcode.LDC:
        return consumeLDC(pPos, byteCode[pPos + 1]);
      default:
        throw new Exception(String.format("unknown opcode %s", pOpcode));
    }
  }

  private int consumeBIPUSH(int pPos) {
    int value = byteCode[pPos + 1];
    stack.push(Variable.create("B", Integer.toString(value), false));
    return pPos + 2;
  }

  private int consumeLOADVARIABLE(int pPos, int pIndex) {
    Variable variable = lvt.getVariable(pIndex);
    stack.push(variable);
    return pPos + 1;
  }

  private int consumeOPERATION(int pPos, Operation.Type pType) {
    ValueNode right = (ValueNode) stack.pop();
    ValueNode left = (ValueNode) stack.pop();
    stack.push(new Operation(left, right, pType));
    return pPos + 1;
  }

  private int consumeRETURNVALUE(int pPos) {
    statements.add(new ReturnNode((ValueNode) stack.pop()));
    return pPos + 1;
  }

  private int consumePUTFIELD(int pPos) {
    int index = unsignedShortAt(pPos + 1);
    CONSTANT_Fieldref fieldRef = Utils.getCPInfo(constantPool, index,
        CONSTANT_Fieldref.class).get();
    String className = Utils.getClassName(constantPool,
        fieldRef.getClassIndex());
    Variable variable = Utils.getFieldVariable(constantPool,
        fieldRef.getNameAndTypeIndex());
    FieldNode field = new FieldNode(variable.getDescriptor(),
        variable.getValue(), className, false);

    ValueNode value = (ValueNode) stack.pop();
    BaseNode node = stack.pop();

    statements.add(new AssignNode(new InvocationNode(node, field), value,
        AssignNode.Type.NONFIRST));

    return pPos + 3;
  }

  private int unsignedShortAt(int pPos) {
    return ((byteCode[pPos] & 0xFF) << 8) + (byteCode[pPos + 1] & 0xFF);
  }

  private int consumeSTOREVARIABLE(int pPos, int pIndex) {
    throw new UnsupportedOperationException("NYI");
  }

  private int consumeRETURN(int pPos) {
    return pPos + 1;
  }

  private int consumeNEW(int pPos) {
    throw new UnsupportedOperationException("NYI");
  }

  private int consumeDUP(int pPos) {
    throw new UnsupportedOperationException("NYI");
  }

  private int consumeLDC(int pPos, int pIndex) {
    throw new UnsupportedOperationException("NYI");
  }

}