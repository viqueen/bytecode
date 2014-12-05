/**
 * LabSET 2014
 */
package org.labset.bytecode;

import static org.codehaus.preon.buffer.ByteOrder.BigEndian;

import org.codehaus.preon.annotation.BoundNumber;
import org.labset.bytecode.attr.AttributeInfo;

/**
 * @author hasnaer
 *
 */
abstract class ClassMemberInfo {
  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int accessFlags;
  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int nameIndex;
  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int descriptorIndex;
  @BoundNumber(size = "16", byteOrder = BigEndian)
  private int attributesCount;
  
  public abstract AttributeInfo[] getAttributes();
  
  public int getAccessFlags(){
    return accessFlags;
  }
  
  public int getNameIndex() {
    return nameIndex;
  }
  
  public int getDescriptorIndex() {
    return descriptorIndex;
  }
}
