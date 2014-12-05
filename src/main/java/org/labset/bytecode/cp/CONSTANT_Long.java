/**
 * LabSET 2014
 */
package org.labset.bytecode.cp;

import org.codehaus.preon.annotation.Bound;
import org.codehaus.preon.annotation.TypePrefix;

/**
 * @author hasnaer
 *
 */
@TypePrefix(value = "5", size = 8)
public class CONSTANT_Long implements CPInfo {
    @Bound
    private long value;
}
