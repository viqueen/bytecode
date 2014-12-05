/**
 * LabSET 2014
 */
package org.labset.bytecode.attr;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.buffer.ByteOrder;

/**
 * @author hasnaer
 *
 */
public abstract class AttributeInfo {

  @BoundNumber(size = "32", byteOrder = ByteOrder.BigEndian)
  private int attributeLength;

}