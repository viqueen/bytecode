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
@TypePrefix(value = "16", size = 8)
public class CONSTANT_MethodType implements CPInfo {
    @BoundNumber(size = "16", byteOrder = BigEndian)
    private int descriptorIndex;
}