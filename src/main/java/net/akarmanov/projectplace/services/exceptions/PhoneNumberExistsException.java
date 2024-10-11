package net.akarmanov.projectplace.services.exceptions;

public class PhoneNumberExistsException extends RuntimeException {
    public PhoneNumberExistsException(String phoneNumber) {
        super("Phone number " + phoneNumber + " already exists.");
    }
}
