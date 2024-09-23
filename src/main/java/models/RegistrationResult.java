package models;

import screens.ContactListScreen;

public class RegistrationResult {
    private boolean success;
    private String errorMessage;
    private ContactListScreen contactListScreen;

    public RegistrationResult(boolean success, String errorMessage, ContactListScreen contactListScreen) {
        this.success = success;
        this.errorMessage = errorMessage;
        this.contactListScreen = contactListScreen;
    }

    public boolean isSuccess(){
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ContactListScreen getContactListScreen() {
        return contactListScreen;
    }
}
