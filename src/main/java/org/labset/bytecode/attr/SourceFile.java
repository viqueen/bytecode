/**
 * LabSET 2014
 */
package org.labset.bytecode.attr;

import static org.codehaus.preon.buffer.ByteOrder.BigEndian;

import org.codehaus.preon.annotation.BoundNumber;

/**
 * @author hasnaer
 *
 */
public class SourceFile extends AttributeInfo {
    @BoundNumber(size = "16", byteOrder = BigEndian)
    private int sourceFileIndex;
}