package com.matang28.restsqlexporter.services.core;

public interface ISecuredTableService {

    String encrypt(String object) throws IllegalArgumentException;

    String decrypt(String object) throws IllegalArgumentException;

}
