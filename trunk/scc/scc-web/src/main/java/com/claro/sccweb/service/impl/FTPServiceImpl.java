package com.claro.sccweb.service.impl;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;

import com.claro.sccweb.service.AbstractService;
import com.claro.sccweb.service.FTPService;
import com.claro.sccweb.service.ServiceException;

public class FTPServiceImpl extends AbstractService implements FTPService {

	private String userName;
	private String password;
	private String hostName;
	private Integer port;
	private String folder;
	
	public void setFolder(String folder) {
		this.folder = folder;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	 
	public void writeFile(OutputStream output, String fileName) throws ServiceException {
		FTPClient client = null;
		try {
			if (fileExists(fileName) != null)
				{
				client = connect();				
				client.retrieveFile(fileName, output);
				}
		} catch (Exception e)
			{
			throw new ServiceException(e.getMessage(), e);
			}
		
	}
	 
	public String fileExists(String fileName) throws ServiceException {
		FTPClient client = null;
		String name = fileName;
		try {
			client = connect();
			int found = client.list(name);
			if (found == 0)
				{
				name = fileName+".gz";
				found = client.list(name);
				}
			if (found > 0)
				return name;
			else
				return null;
		} catch (Exception  e)
			{
			throw new ServiceException(e.getMessage(), e);
			}finally{
				if (client != null)
					try {
						client.disconnect();
					} catch (IOException e) {}
			}
	}
	
	private FTPClient connect() throws Exception
	{
		FTPClient client = new FTPClient();
		client.connect(this.hostName);
		client.login(this.userName, this.password);
		client.changeWorkingDirectory(this.folder);
		client.setFileType(FTPClient.ASCII_FILE_TYPE);
		return client;
	}
	
}
