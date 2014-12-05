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
@TypePrefix(value = "4", size = 8)
public class CONSTANT_Float implements CPInfo {

    @Bound
    private float value;
}