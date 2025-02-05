package factory;

import animals.AbsAnimal;
import animals.birds.Duck;
import animals.pets.Cat;
import animals.pets.Dog;
import data.AnimalTypesData;
import exceptions.AnimalTypeNotSupportedException;

public class FactoryAnimal {

    public AbsAnimal create(AnimalTypesData animalTypesData) {
        switch (animalTypesData) {
            case CAT: {
                return new Cat();
            }
            case DOG: {
                return new Dog();
            }
            case DUCK: {
                return new Duck();
            }

        }
        throw new AnimalTypeNotSupportedException(animalTypesData);
    }
}
