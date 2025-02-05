package animals.pets;

import animals.AbsAnimal;
import data.ColorData;

    public class Dog extends AbsAnimal {
        @Override
        public void say() {
            System.out.println("Гав!");
        }
    }