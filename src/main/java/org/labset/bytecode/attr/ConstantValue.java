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
public class ConstantValue extends AttributeInfo {

    @BoundNumber(size = "16", byteOrder = ByteOrder.BigEndian)
    private int constantValueIndex;

}
