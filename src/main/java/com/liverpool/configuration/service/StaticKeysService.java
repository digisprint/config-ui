package com.liverpool.configuration.service;

import java.util.List;

import com.liverpool.configuration.beans.StaticKeys;

public interface StaticKeysService {
	
	//static key CRUD operations
	public void createStaticKey(StaticKeys staticKey);
	public void updateStaticKey(StaticKeys staticKey);
	public List<StaticKeys> getAllStaticKeys();
	public StaticKeys getStaticKeyByKey(String key);
	public void deleteStaticKey(String key);
	
}
