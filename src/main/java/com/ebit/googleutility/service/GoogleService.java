package com.ebit.googleutility.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zeroturnaround.zip.ZipUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class GoogleService {
	@Autowired
	private Drive drive;

	public String readGoogleDrive() {
		// Connection Google Drive -> username + passworkd = token [login / security]
		// How to read file
		// How to download
		// How to build zip
		// How to upload to any server.
		//TODO: 
		return "OK#ReadFile";
	}

	public List<File> getAllFiles() throws IOException, GeneralSecurityException {
		// Print the names and IDs for up to 10 files.
		FileList result = drive.files().list()
				.setPageSize(10)
             // .setQ("'" + "177hbk2GWCmzzsjdw_eP2pAPi_sgEctpw" + "' in parents")
				.setFields("nextPageToken, files(id, name)")
				.execute();
		return result.getFiles();
	}


	public void readAndSaveFile(String folderId) throws IOException, GeneralSecurityException {
		// Print the names and IDs for up to 10 files.
		FileList result = drive.files().list()
				//.setPageSize(10)
				.setQ("'" + folderId + "' in parents")
				.setFields("nextPageToken, files(id, name)")
				.execute();

		List<File> files = result.getFiles();
		if (files == null || files.isEmpty()) {
			System.out.println("No files found.");
		} else {
			Path downloadDir = Paths.get("downloaded_files");
			Files.createDirectories(downloadDir);

			for (File file : files) {
				String fileId = file.getId();
				String fileName = file.getName();
				System.out.printf("Downloading %s (%s)\n", fileName, fileId);

				Path filePath = downloadDir.resolve(fileName);
				try (FileOutputStream outputStream = new FileOutputStream(filePath.toFile())) {
					drive.files().get(fileId)
							.executeMediaAndDownloadTo(outputStream);
				}
			}
			Path zipPath = Paths.get("downloaded_files.zip");
			ZipUtil.pack(downloadDir.toFile(), zipPath.toFile());
			System.out.printf("Files downloaded and zipped to %s\n", zipPath.toString());
		}
	}
}