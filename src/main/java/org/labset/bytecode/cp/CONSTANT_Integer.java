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
@TypePrefix(value = "3", size = 8)
public class CONSTANT_Integer implements CPInfo {

    @Bound
    private int value;
}
