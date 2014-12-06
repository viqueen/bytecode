/**
 * LabSET 2014
 */

/**
 * @author hasnaer
 *
 */
public class SampleClass {

  private String firstName;
  private String lastName;
  private int age;
  private String city;
  private String[] friends;

  public SampleClass(String pFirstName, String pLastName, int pAge) {
    firstName = pFirstName;
    lastName = pLastName;
    age = pAge;
    friends = null;
  }

  public int sum(int a, int b) {
    return a + b;
  }

  public void setFirstName(String pFirstName){
    firstName = pFirstName;
  }
}
