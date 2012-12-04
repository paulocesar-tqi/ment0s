package com.claro.sccweb.service;

import java.io.OutputStream;

public interface FTPService {

	public void setUserName(String userName);
	public void setPassword(String password);
	public void setHostName(String hostName);
	public void setPort(Integer port);
	public void setFolder(String folder);
		
	public void writeFile(OutputStream output, String fileName) throws ServiceException;
	
	public String fileExists(String fileName) throws ServiceException;
	
	public void writeFile(OutputStream output, String dir, String fileName) throws ServiceException;
	
	public String fileExists(String dir, String fileName) throws ServiceException;
	
}
