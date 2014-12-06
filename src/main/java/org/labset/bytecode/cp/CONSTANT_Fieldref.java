/**
 * LabSET 2014
 */
package org.labset.bytecode.cp;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.TypePrefix;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * @author hasnaer
 *
 */
@TypePrefix(value = "9", size = 8)
public class CONSTANT_Fieldref implements CPInfo {

  @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
  private int classIndex;
  @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
  private int nameAndTypeIndex;

  public int getClassIndex() {
    return classIndex;
  }

  public int getNameAndTypeIndex() {
    return nameAndTypeIndex;
  }
}