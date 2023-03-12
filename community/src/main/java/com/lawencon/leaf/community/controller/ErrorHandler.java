package com.lawencon.leaf.community.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lawencon.leaf.community.pojo.PojoErrorRes;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<?> handleRuntimeEx(RuntimeException ex) {
		ex.printStackTrace();
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		final List<String> errors = new ArrayList<>();

		e.getBindingResult().getAllErrors().forEach(error -> {
			errors.add(error.getDefaultMessage());
		});

		final PojoErrorRes<List<String>> res = new PojoErrorRes<>();
		res.setMessage(errors);

		return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
	}

}
