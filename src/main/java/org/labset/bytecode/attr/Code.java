/**
 * LabSET 2014
 */
package org.labset.bytecode.attr;

import static org.codehaus.preon.buffer.ByteOrder.BigEndian;

import org.codehaus.preon.annotation.*;
import org.codehaus.preon.annotation.Choices.Choice;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * @author hasnaer
 *
 */
public class Code extends AttributeInfo {

  @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
  private int maxStack;
  
  @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
  private int maxLocals;
  
  @BoundNumber(size = "32", byteOrder = ByteOrder.BigEndian)
  private int codeLength;
  
  @BoundList(size = "codeLength")
  private byte[] code;
  
  @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
  private int exceptionTableLength;
  
  @BoundList(size = "exceptionTableLength")
  private Exceptions.Entry[] exceptionTable;
  
  @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
  private int attributesCount;
  
  @BoundList(size = "attributesCount", selectFrom = @Choices(prefixSize = 16, byteOrder = BigEndian, alternatives = {
      @Choice(condition = "outer.outer.constantPool[prefix-1].value=='LineNumberTable'", type = LineNumberTable.class),
      @Choice(condition = "outer.outer.constantPool[prefix-1].value=='LocalVariableTable'", type = LocalVariableTable.class) }))
  private AttributeInfo[] attributes;

  private LocalVariableTable localVariableTable;
  
  public byte[] getCode() {
    return code;
  }

  public AttributeInfo[] getAttributes() {
    return attributes;
  }
  
  public int getAttributesCount() {
    return attributesCount;
  }
  
  public LocalVariableTable getLocalVariableTable(){
    return localVariableTable;
  }
  
  public void setLocalVariableTable(LocalVariableTable pLvt) {
    localVariableTable = pLvt;
  }
  
 
}
