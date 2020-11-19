
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
        Pet myPet;
        Scanner sc = new Scanner(System.in);
        System.out.println("What kind of pet do you have?");
        TypesOfPets animal = TypesOfPets.valueOf(sc.next());
        System.out.println("What is their name?");
        String name = sc.next();
        System.out.println("Finally, what is their birthday? (Enter MM/DD/YY)");
        SimpleDateFormat sdf = new SimpleDateFormat("MM/DD/YY");
        Date birthday = sdf.parse(sc.next());
        sc.close();
        switch(animal) {
            case Cat -> myPet = new Cat(name, birthday);
            case Dog -> myPet = new Bird(name, birthday);
            case Fish -> myPet = new Fish(name, birthday);
            case Ferret -> myPet = new Ferret(name, birthday);
            case Bird -> myPet = new Bird(name, birthday);
            case Rabbit -> myPet = new Rabbit(name, birthday);
            case Reptile -> {
                System.out.println("What species is your pet?");
                sc = new Scanner(System.in);
                String species = sc.next();
                sc.close();
                myPet = new Reptile(name, birthday, species);
            }
            default -> myPet = new Cat(name, birthday);
        }
        System.out.println("My pet" + myPet);

    }
}
