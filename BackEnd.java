import java.util.*;

/**
 * Backend storage and maintenance for the PetHealth application. Facade-ish.
 *
 * @author rowanholop
 * @version 11.23.2020
 */
public class BackEnd {
    List<Pet> pets;
    /**
     * Constructor for objects of class BackEnd
     */
    public BackEnd()
    {
        pets = new ArrayList<Pet>();
    }
    /**
     * Populates data by deserialzing.
     */
    public void loadData() {
        if (Functions.deserialize() != null) {
            for (Pet p : Functions.deserialize()) {
                pets.add(p);
            }
        }
    }
    /**
     * Facade implementation.
     * 
     * @param Pet pet to add to backend storage.
     */
    public boolean addPet(Object petType, String name, String birthday) {
        try {
            pets.add(Functions.addPet(petType, name, birthday));
            return true;
        }
        catch (Exception e) 
        {
            return false;
        }
    }
    /**
     * Return the current list of pets.
     * 
     * @return List<Pet> list of pets
     */
    public List<Pet> getPets() {
        return pets;
    }
    /**
     * Facade implementation for closing the window. Seralizes the data to a local file.
     */
    public void close()
    {
        Functions.serialize(pets);
    }
    /**
     * Facade implementation.
     * 
     * @param Pet pet to remove from backend storage.
     */
    public void deletePet(Pet p) {
        pets.remove(p);
    }
    /**
     * Facade implementation.
     * 
     * @param Pet pet whose health needs to be updated
     * @param String name of symptom
     */
    public void updatePetHealth(Pet p, String symptom) {
        int x = pets.indexOf(p);
        pets.get(x).log(symptom);
    }
    /**
     * Facade implementation.
     * 
     * @param Pet pet whose health needs to be updated
     * @param String date of symptom
     * @param String name of symptom
     */
    public void updatePetHealth(Pet p, String date, String symptom) {
        int x = pets.indexOf(p);
        pets.get(x).log(date, symptom);
    }
    /**
     * Facade implementation.
     * 
     * @param Pet pet whose health should be cleeared
     */
    public void clearPetHealth(Pet p){
        int x = pets.indexOf(p);
        pets.get(x).clearHealth();
    }
}
