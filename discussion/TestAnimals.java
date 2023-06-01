public class TestAnimals {
    public static void main(String[] args){
        Animal a = new Animal("Pluto", 10);
        Cat c = new Cat("Garfield", 6);
        Dog d = new Dog("Fido", 4);
        a.greet();  //Animal says: Huh?
        c.greet(); // Cat Garfield says: Mewo
        d.greet(); // Dog Fido says Woof!

        a = c;
        ((Cat) a).greet(); //Cat Garfield says: Mewo
        a.greet();   //Cat Garfield says: Mewo
        a = new Dog("Spot", 10);
        d = (Dog)a;
    }
}
