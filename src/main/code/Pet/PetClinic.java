package code.Pet;

public class PetClinic {
    Pet firstAnimal = new Pet();
    Pet secondAnimal = new Pet("Cat", 8.8);
    Pet thirdAnimal = new Pet("Owl", "Bucklya", 5, 1.5);

    public void displayAllPets() {
        firstAnimal.displayInfo();
        secondAnimal.displayInfo();
        thirdAnimal.displayInfo();

        System.out.println("Общее количество животных: 3");
    }

}
