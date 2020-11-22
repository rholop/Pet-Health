
/**
 * Write a description of class Main here.
 *
 * @author melmccord
 * @version (a version number or a date)
 */
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
class Main {
    public static void main(String[] args) throws ParseException {
        Pet myPet = new AddAPet().addPet();
        UpdateHealthLog.updateHealth(myPet);
    }

}
class AddAPet {
    public Pet addPet() throws ParseException {
        Pet myPet;
        String species = "None";
        Scanner sc = new Scanner(System.in);
        System.out.println("What kind of pet do you have?");
        TypesOfPets animal = TypesOfPets.valueOf(sc.next());
        System.out.println("What is their name?");
        String name = sc.next();
        System.out.println("Finally, what is their birthday? (Enter MM-DD-YY)");
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");
        Date birthday = sdf.parse(sc.next());
        sc.close();
        switch (animal) {
            case Dog: 
                myPet = new Dog(name, birthday);
                break;
            case Fish:
                myPet = new Fish(name, birthday);
                break;
            case Ferret:
                myPet = new Ferret(name, birthday);
                break;
            case Bird:
                myPet = new Bird(name, birthday);
                break;
            case Rabbit:
                myPet = new Rabbit(name, birthday);
                break;
            case Reptile:
                myPet = new Reptile(name, birthday);
            default:
                myPet = new Cat(name, birthday);
                break;
            }
        return myPet;
    }
}