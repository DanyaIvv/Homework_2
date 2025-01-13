package exceptions;

import data.AnimalTypesData;

public class AnimalTypeNotSupportedException extends RuntimeException {

    public AnimalTypeNotSupportedException(AnimalTypesData animalTypesData) {
        super(String.format("Animal type %s not supported", animalTypesData.name()));
    }
}
