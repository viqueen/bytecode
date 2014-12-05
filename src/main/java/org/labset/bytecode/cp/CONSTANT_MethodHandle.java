/**
 * LabSET 2014
 */
package org.labset.bytecode.cp;

import static org.codehaus.preon.buffer.ByteOrder.BigEndian;

import org.codehaus.preon.annotation.BoundNumber;
import org.codehaus.preon.annotation.TypePrefix;

/**
 * @author hasnaer
 *
 */
@TypePrefix(value = "15", size = 8)
public class CONSTANT_MethodHandle implements CPInfo {

    @BoundNumber(size = "8", byteOrder = BigEndian)
    private int referenceKind;
    @BoundNumber(size = "16", byteOrder = BigEndian)
    private int referenceIndex;

}