package animals;

import data.ColorData;

public abstract class AbsAnimal {
    private String name = "";

    private int age = -1;

    private int weight = -1;

    private ColorData color = null;

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public int getAge() {
        if (age > 20) {
    return -1;
        }
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ColorData getColor() {
        return color;
    }

    public void setColor(ColorData color) {
        this.color = color;
    }

    public void say() {
        System.out.println("Я говорю");
    }
    public void go() {
        System.out.println("Я иду");
    }
    public void drink() {
        System.out.println("Я пью");
    }
    public void eat() {
        System.out.println("Я ем");
    }
    @Override
    public String toString() {
        return String.format("Привет! меня зовут %s, мне %d %s, я вешу - %d кг, мой цвет - %s", name, age, getAgePadej(), weight, color.getName());
    }

    private String getAgePadej() {
        int ostatok = age % 10;

        if( ostatok == 0 || ostatok >= 5 || age >= 11) {
            return "лет";
        }
        if(ostatok == 1) {
            return "год";
        }

        return "года";
    }

}
