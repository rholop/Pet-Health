import java.util.*;

/**
 * Write a description of class BackEnd here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BackEnd
{
    Serialize s;
    Deserialize d;
    List<Pet> pets;
    /**
     * Constructor for objects of class BackEnd
     */
    public BackEnd()
    {
        s = new Serialize();
        d = new Deserialize();
        pets = new ArrayList<Pet>();
    }
    
    
    /**
     * 
     */
    public void loadData() {
        if (d.run() != null) {
            for (Pet p : d.run()) {
                pets.add(p);
            }
        }
    }
    
    public void addPet(Pet p) {
        pets.add(p);
    }
    
    /**
     * 
     */
    public List<Pet> getPets() {
        return pets;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void close()
    {
        s.run(pets);
    }
    
    public void deletePet(Pet p) {
        pets.remove(p);
    }
    
    public void updatePetHealth(Pet p, String symptom) {
        int x = pets.indexOf(p);
        Pet petToUpdate = pets.get(x);
        petToUpdate.log(symptom);
    }
    
    public void updatePetHealth(Pet p, String date, String symptom) {
        int x = pets.indexOf(p);
        Pet petToUpdate = pets.remove(x);
        petToUpdate.log(date, symptom);
        pets.add(petToUpdate);
    }
}
