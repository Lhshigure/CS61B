public class Cat extends Animal{
    public Cat(String name, int age){
        super(name, age);
        this.noise = "Meow";
    }
    @Override
    public String makeNoise(){
        if (age < 5){
            return noise.toUpperCase();
        }else{
            return noise;
        }
    }@Override
    public void greet(){
        System.out.println("Cat " + name + " say : " + makeNoise());
    }
}
