package com.liverpool.configuration.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import com.liverpool.configuration.beans.ResponseData;


public class BaseController<T> {

    public ResponseEntity<ResponseData> success(T t, HttpStatus status, @Nullable String message) {
        ResponseData resp = new ResponseData();
        resp.setBody(t);
        resp.setMessage(message);
        resp.setStatusCode(status.value());
        resp.setSuccess(true);
        return ResponseEntity.ok(resp);
    }

    public ResponseEntity<ResponseData> failure(T t, HttpStatus status, @Nullable String message) {
        ResponseData resp = new ResponseData();
        resp.setBody(t);
        resp.setMessage(message);
        resp.setStatusCode(status.value());
        resp.setSuccess(false);
        return ResponseEntity.ok(resp);
    }

}
