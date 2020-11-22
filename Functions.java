
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

public class Functions {
    public static Pet addPet(Object petName, String name, String date) throws ParseException {
        Pet myPet;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");
        Date birthday = sdf.parse(date);
        TypesOfPets animal = TypesOfPets.valueOf(petName.toString());
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
                break;
            case Cat:
                myPet = new Cat(name, birthday);
                break;
            default:
                myPet = new Other(name, birthday);
                break;
            }
        return myPet;
    }
}