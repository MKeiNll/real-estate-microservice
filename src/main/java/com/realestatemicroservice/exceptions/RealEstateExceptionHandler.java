package com.realestatemicroservice.exceptions;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 
 * Maps exceptions to HTTP codes & reasons.
 * 
 */
@RestControllerAdvice
public class RealEstateExceptionHandler {

	public static final String PARAMETER_TYPE_MISMATCH_MESSAGE = "Request parameter(s) type mismatch.";
	public static final String PARAMETER_MISSING_MESSAGE = "Request parameter(s) missing.";
	public static final String PARAMETER_NOT_VALID_MESSAGE = "Request parameter(s) not valid.";
	public static final String PARAMETER_UNSATISFIED_CONDITION_MESSAGE = "Unsatisfied request parameter(s) condition(s).";
	public static final String OBJECT_DOES_NOT_EXIST_MESSAGE = "Object with specified id does not exist.";
	public static final String NO_FIELDS_SPECIFIED_MESSAGE = "At least one field to edit is required.";

	/**
	 * 
	 * Exception handler for MethodArgumentTypeMismatchException.
	 * <p>
	 * Returns status 400 (Bad request) with a reason
	 * {@link RealEstateExceptionHandler#PARAMETER_TYPE_MISMATCH_MESSAGE}
	 * 
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = PARAMETER_TYPE_MISMATCH_MESSAGE)
	public void handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
	}

	/**
	 * 
	 * Exception handler for MissingServletRequestParameterException.
	 * <p>
	 * Returns status 400 (Bad request) with a reason
	 * {@link RealEstateExceptionHandler#PARAMETER_MISSING_MESSAGE}
	 * 
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = PARAMETER_MISSING_MESSAGE)
	public void handleMethodArgumentNotValidException(MissingServletRequestParameterException ex) {
	}

	/**
	 * 
	 * Exception handler for MethodArgumentNotValidException,
	 * ConstraintViolationException & RollbackException
	 * <p>
	 * Returns status 400 (Bad request) with a reason
	 * {@link RealEstateExceptionHandler#PARAMETER_NOT_VALID_MESSAGE}
	 * 
	 */
	@ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class,
			RollbackException.class })
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = PARAMETER_NOT_VALID_MESSAGE)
	public void handleMethodArgumentNotValidException(Exception ex) {
	}

	/**
	 * 
	 * Exception handler for UnsatisfiedServletRequestParameterException.
	 * <p>
	 * Returns status 400 (Bad request) with a reason
	 * {@link RealEstateExceptionHandler#PARAMETER_UNSATISFIED_CONDITION_MESSAGE}
	 * 
	 */
	@ExceptionHandler(UnsatisfiedServletRequestParameterException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = PARAMETER_UNSATISFIED_CONDITION_MESSAGE)
	public void handleUnsatisfiedServletRequestParameterException(UnsatisfiedServletRequestParameterException ex) {
	}

	/**
	 * 
	 * Exception handler for RealEstateObjectDoesNotExistException.
	 * <p>
	 * Returns status 400 (Bad request) with a reason
	 * {@link RealEstateExceptionHandler#OBJECT_DOES_NOT_EXIST_MESSAGE}
	 * 
	 */
	@ExceptionHandler(RealEstateObjectDoesNotExistException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = OBJECT_DOES_NOT_EXIST_MESSAGE)
	public void handleRealEstateObjectDoesNotExistException(RealEstateObjectDoesNotExistException ex) {
	}

	/**
	 * 
	 * Exception handler for RealEstateObjectDoesNotExistException.
	 * <p>
	 * Returns status 400 (Bad request) with a reason
	 * {@link RealEstateExceptionHandler#NO_FIELDS_SPECIFIED_MESSAGE}
	 * 
	 */
	@ExceptionHandler(NoFieldsToEditSpecifiedException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = NO_FIELDS_SPECIFIED_MESSAGE)
	public void hs(NoFieldsToEditSpecifiedException ex) {
	}
}
