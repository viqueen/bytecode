/**
 * LabSET 2014
 */
package org.labset.bytecode;

import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import org.apache.commons.io.IOUtils;
import org.codehaus.preon.Codec;
import org.codehaus.preon.Codecs;
import org.codehaus.preon.DecodingException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.labset.bytecode.cp.CONSTANT_Utf8;
import org.labset.bytecode.cp.CPInfo;

/**
 * @author hasnaer
 *
 */
public class ClassFileTestCase {

  private Codec<ClassFile> codec;
  private static byte[] byteCode;

  @BeforeClass
  public static void load() throws IOException {
    try (InputStream stream = ClassFileTestCase.class
        .getResourceAsStream("/SampleClass.class")) {
      byteCode = IOUtils.toByteArray(stream);
    }
  }

  @Before
  public void setUp() {
    codec = Codecs.create(ClassFile.class);
  }

  @Test
  public void testDecode() throws DecodingException {
    ClassFile classFile = Codecs.decode(codec, byteCode);
    CPInfo[] constantPool = classFile.getConstantPool();
    
    Assert.assertEquals(4, classFile.getFields().length);
    Assert.assertEquals(1, classFile.getMethods().length);
    
    
    FieldInfo field = classFile.getFields()[0];
    Assert.assertEquals(
        "firstName",
        ConstantPool
            .get(constantPool, field.getNameIndex(), CONSTANT_Utf8.class).get()
            .getValue());
  }
}