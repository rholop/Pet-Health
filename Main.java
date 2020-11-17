
/**
 * Write a description of class Main here.
 *
 * @author melmccord
 * @version (a version number or a date)
 */
import java.util.Date;

class Main {
    public static void main(String[] args) {
        Pet p = new Ferret("Mallory", new Date());
        p.display();
        p.updateHealth("Cold");
        Symptom s = new Symptom("Cold");
        System.out.println(p.myHealth.getHealth());
    }
}
