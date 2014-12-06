/**
 * LabSET 2014
 */
package org.labset.bytecode;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import org.labset.bytecode.attr.AttributeInfo;
import org.labset.bytecode.cp.CPInfo;

/**
 * @author hasnaer
 *
 */
public class Utils {

  public static <T extends CPInfo> Optional<T> getCPInfo(
      final CPInfo[] pConstantPool, int pIndex, Class<T> pType) {
    Object constant = pConstantPool[pIndex - 1];
    if (pType.isInstance(constant)) {
      return Optional.of(pType.cast(constant));
    }
    return Optional.empty();
  }

  public static <T extends AttributeInfo> Optional<T> getAttribute(
      final AttributeInfo[] pAttributes, Class<T> pType) {
    for (AttributeInfo attribute : pAttributes) {
      if (pType.isInstance(attribute)) {
        return Optional.of(pType.cast(attribute));
      }
    }
    return Optional.empty();
  }

  private static final Pattern descriptors = Pattern
      .compile("(\\[)?(B|C|D|F|I|J|S|Z|V|(L.*?;))");

  public static List<String> getParamTypes(String pDescriptor) {
    List<String> types = new ArrayList<>();
    int startIndex = pDescriptor.indexOf("(");
    int endIndex = pDescriptor.indexOf(")");
    Matcher matcher = descriptors.matcher(pDescriptor.substring(startIndex + 1,
        endIndex));
    while (matcher.find()) {
      types.add(matcher.group());
    }
    return types;
  }
}