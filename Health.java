
/**
 * Write a description of class Health here.
 *
 * @author melmccord
 * @version 11/14/2020
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
enum HealthTypes {
    Allergies,
    Behavioral,
    Eating
}
public class Health {
    Pet myPet;
    public Health(Pet p) {
        myPet = p;
    }
   public void setHealth(String s) {

   }

    public List<String> getHealth() {
        return null;
    }
}
class BehavioralHealth extends Health {
    private Pet p;
    private List<String> symptoms;
    public BehavioralHealth(Pet p) {
        super(p);
    }
    public void setHealth(String s) {
        symptoms.add(s);
    }
    public List<String> getHealth() {
        return symptoms;
    }
}
class Main {
    public static void main(String[] args) {
        Pet p = new Ferret("Mallory", new Date());

    }
}