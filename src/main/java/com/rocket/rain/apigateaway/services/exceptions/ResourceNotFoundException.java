package com.rocket.rain.apigateaway.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Object id){
        super("Resource not Found. id: "+id);

    }
}
