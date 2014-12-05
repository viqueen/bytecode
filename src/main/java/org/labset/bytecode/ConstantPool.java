/**
 * LabSET 2014
 */
package org.labset.bytecode;

import java.util.Optional;

import org.labset.bytecode.cp.CPInfo;

/**
 * @author hasnaer
 *
 */
public class ConstantPool {

  public static <T> Optional<T> get(final CPInfo[] pConstantPool, int pIndex,
      Class<T> pType) {
    Object constant = pConstantPool[pIndex - 1];
    if (pType.isInstance(constant)) {
      return Optional.of(pType.cast(constant));
    }
    return Optional.empty();
  }
  
}