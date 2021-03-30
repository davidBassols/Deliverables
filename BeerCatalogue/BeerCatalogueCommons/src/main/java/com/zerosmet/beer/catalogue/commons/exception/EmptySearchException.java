package com.zerosmet.beer.catalogue.commons.exception;

public class EmptySearchException extends Exception{

	private static final long serialVersionUID = -1722959481988387296L;

	public EmptySearchException() {
		super();
	}
	
	public EmptySearchException(String errorMessage) {
        super(errorMessage);
    }
}
