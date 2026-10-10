package am.trainings.palmeto_pizza.util;

public class Validator {
    private Validator(){}

    public static boolean validateName(String name){
        if(name == null) return false;
        return name.length() > 4 && name.length() < 20;
    }
}
