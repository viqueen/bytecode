/**
 * LabSET 2014
 */
package org.labset.bytecode.attr;

import static org.codehaus.preon.buffer.ByteOrder.BigEndian;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.preon.annotation.BoundList;
import org.codehaus.preon.annotation.BoundNumber;
import org.labset.bytecode.node.Variable;

/**
 * @author hasnaer
 *
 */
public class LocalVariableTable extends AttributeInfo {

  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int localVariableTableLength;

  @BoundList(size = "localVariableTableLength")
  private Entry[] entries;

  private Map<Integer, Variable> variables;
  
  public LocalVariableTable() {
    variables = new HashMap<>();
  }
  
  public static class Entry {
    @BoundNumber(size = "16", byteOrder = BigEndian)
    private int startPc;
    @BoundNumber(size = "16", byteOrder = BigEndian)
    private int length;
    @BoundNumber(size = "16", byteOrder = BigEndian)
    private int nameIndex;
    @BoundNumber(size = "16", byteOrder = BigEndian)
    private int descriptorIndex;
    @BoundNumber(size = "16", byteOrder = BigEndian)
    private int index;
    
    public int getNameIndex() {
      return nameIndex;
    }
    
    public int getDescriptorIndex() {
      return descriptorIndex;
    }
    
    public int getIndex() {
      return index;
    }
  }
  
  public Entry[] getEntries() {
    return entries;
  }
  
  public Variable getVariable(int pIndex) {
    return variables.get(pIndex);
  }
  
  public Map<Integer, Variable> getVariables() {
    return variables;
  }
}