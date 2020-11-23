
/**
 * Functions required for operating the backend.
 *
 * @author melmccord
 * @author rowanholop
 * @version (a version number or a date)
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

public class Functions {
    public static Pet addPet(Object petType, String name, String date) throws ParseException {
        Pet myPet;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");
        Date birthday = sdf.parse(date);
        TypesOfPets animal = TypesOfPets.valueOf(petType.toString());
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

    public static List<Pet> deserialize() {
        try {
            FileInputStream fileIn = new FileInputStream("/tmp/pet.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            List<Pet> petList = (ArrayList<Pet>) in.readObject();
            in.close();
            fileIn.close();
            return petList;
        } catch (IOException i) {
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Pet class not found");
            return null;
        }
    }

    public static void serialize(List<Pet> petList) {
        try {
            FileOutputStream fileOut =
                new FileOutputStream("/tmp/pet.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(petList);
            out.close();
            fileOut.close();
        } 
        catch (IOException i) {
            i.printStackTrace();
        }
    }

}