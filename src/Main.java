import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {

    /**
     * @param data принимает входящий поток байтов
     *             Сначала идет количество объектов Animal (число типа int)
     *             Далее подряд указанное количество сериализованных объектов типа Animal
     * @return массив десериализованных объектов типа Animal[]
     */
    public static Animal[] deserializeAnimalArray(byte[] data) {
        Animal[] animals;
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            int count = ois.readInt();
            animals = new Animal[count];
            for (int i = 0; i < count; i++) {
                animals[i] = (Animal) ois.readObject();
            }
        } catch (ClassNotFoundException | IOException | ClassCastException | NegativeArraySizeException e) {
            throw new IllegalArgumentException();
        }
        return animals;
    }

    public static void main(String[] args) throws IOException {

        Animal cat = new Animal("cat");
        Animal dog = new Animal("dog");
        Animal cow = new Animal("cow");

        Animal[] animals = new Animal[]{cat, dog, cow};
        for (Animal value : animals) {
            System.out.println(value.getName());
        }
        System.out.println();
    }
}
