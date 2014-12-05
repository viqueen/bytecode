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
@TypePrefix(value = "18", size = 8)
public class CONSTANT_InvokeDynamic implements CPInfo {

    @BoundNumber(size = "16", byteOrder = BigEndian)
    private int bootstrapMethodAttrInde;
    @BoundNumber(size = "16", byteOrder = BigEndian)
    private int nameAndTypeIndex;
}