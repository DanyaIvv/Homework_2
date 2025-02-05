package dataobj;

public class AnimalObj {
    private String name;
    private int age;
    private String type;

    public AnimalObj(String name, int age, String type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Имя: " + name + ", Возраст: " + age + ", Тип: " + type;
    }
}
