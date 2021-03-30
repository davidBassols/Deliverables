package com.zerosmet.beer.catalogue.commons.exception;

public class WrongSearchValueException extends Exception{

	private static final long serialVersionUID = -1722959481988387296L;

	public WrongSearchValueException() {
		super();
	}
	
	public WrongSearchValueException(String errorMessage) {
        super(errorMessage);
    }
}
