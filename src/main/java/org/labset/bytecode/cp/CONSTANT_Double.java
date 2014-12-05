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
@TypePrefix(value = "6", size = 8)
public class CONSTANT_Double implements CPInfo {
    @Bound
    private double value;
}