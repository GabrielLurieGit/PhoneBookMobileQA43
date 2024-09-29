package helpers;

import enums.ContactField;
import models.Contact;

public class ContactGenerator {

    private static Contact createContact(boolean correctContact, String incorrectValue, ContactField contactField){

        String name = NameAndLastNameGenerator.generateName();
        String lastName = NameAndLastNameGenerator.generateLastName();
        String email = EmailGenerator.generateEmail(5,5,3);
        String phoneNumber = PhoneNumberGenerator.generatePhoneNumber();
        String address = AddressGenerator.generateAddress();
        String description = "Description";
        if(correctContact && contactField !=null){
            switch (contactField){
                case NAME :
                    name = incorrectValue;
                    break;
                case LAST_NAME:
                    lastName = incorrectValue;
                    break;
                case EMAIL:
                    email = incorrectValue;
                    break;
                case PHONE:
                    phoneNumber = incorrectValue;
                    break;
                case ADDRESS:
                    address = incorrectValue;
                    break;
                case DESCRIPTION:
                    description = incorrectValue;
                    break;
                default: throw new IllegalArgumentException("Wrong field name:"+contactField);
            }
        }
        return new Contact(name, lastName,  phoneNumber, email, address, description);
    }

    public static Contact createValidContact(){
        return createContact(false, null, null);
    }
    public static Contact createInvalidContact(String incorrectValue, ContactField contactField){
        return createContact(true,incorrectValue, contactField);
    }

    public static void main(String[] args) {
        System.out.println("Valid contact:" + createValidContact());
        System.out.println("Invalid contact : "+ createInvalidContact("wrongemail", ContactField.EMAIL));
    }
}