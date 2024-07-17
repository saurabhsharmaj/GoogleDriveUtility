package com.ebit.googleutility.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebit.googleutility.common.GoogleDriveDownload;

@Service
public class GoogleService {

	@Autowired
	GoogleDriveDownload googleDriveDownload;
	
	public String readGoogleDrive(String filename) throws Exception {
		// Connection Google Drive -> username + passworkd = token [login / security]
		// How to read file
		// How to download
		// How to build zip
		// How to upload to any server.
		//TODO:
		googleDriveDownload.readAndSaveFile(filename);
		return "OK#ReadFile";
	}

}
