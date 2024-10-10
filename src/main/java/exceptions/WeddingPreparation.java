package exceptions;

import java.util.Random;

public class WeddingPreparation {
    public static void main(String[] args) {
        try {
            prepareAndGoToWedding();
        }catch (BirdPoopException e){
            System.out.println("Unexpected incident: "+ e.getMessage());
        }catch (RainException e){
            System.out.println("Whether issue "+ e.getMessage());
            System.out.println("Getting an umbrella before heading to the wedding");
        }finally {
            System.out.println("Preparation completed! All issues handled");
        }

    }

    public static void prepareAndGoToWedding() throws BirdPoopException, RainException {
        System.out.println("Put on the suit and started heading to the wedding ceremony...");
        boolean hasUmbrella = checkForUmbrella();
        if(!hasUmbrella){
            boolean isRaining = simulateRain();
            if(isRaining){
                throw new RainException("It started raining, but there is no umbrella");
            }
        }
        boolean isBirdPooped = simulateBirdIncident();
        if(isBirdPooped){
            throw new BirdPoopException("A bird just pooped on the suit!");
        }
        System.out.println("Arrived at the venue without issues");

    }

    public static boolean checkForUmbrella(){
        boolean hasUmbrella = new Random().nextInt(10) >3;
        System.out.println("Umbrella status: "+(hasUmbrella ? "Raining": "Clear sky"));
        return hasUmbrella;
    }

    public static boolean simulateRain(){
        boolean isRaining = new Random().nextInt(10) >4;
        System.out.println();
return true;
    }

    public static boolean simulateBirdIncident(){
      return Math.random() < 0.5;
    }
}
