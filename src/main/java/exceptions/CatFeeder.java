package exceptions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CatFeeder {
    public static void main(String[] args) {
        try {
            feedCat("milk");
            feedCat("fish");
            feedCat("chocolate");
        }catch (CatFeedingException e){
            System.out.println(e.getMessage());
        }finally {
            System.out.println("**********************");
        }

    }

    public static void feedCat(String food) throws CatFeedingException{
        if(food.equals("chocolate")){
            throw new CatFeedingException("I do not want this ....");
        }
        System.out.println("Meow! I like it!!"+food);
    }


    @Test
    public void testFeeding() {

        try {
            feedCat("chocolate");
        }catch (CatFeedingException e){
            Assert.assertEquals(e.getMessage(),"I do not want this ....");

        }
    }
}
