package com.zerosmet.beer.catalogue.commons.exception;

public class WrongComparissonException extends Exception{

	private static final long serialVersionUID = -1722959481988387296L;

	public WrongComparissonException() {
		super();
	}
	
	public WrongComparissonException(String errorMessage) {
        super(errorMessage);
    }
}
