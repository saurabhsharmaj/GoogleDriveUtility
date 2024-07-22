package com.ebit.googleutility.controller;

import com.ebit.googleutility.service.GoogleService;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("/drive")
public class GoogleDriveController {
    @Autowired
    private GoogleService googleService;
    @Autowired
    private Drive drive;

    @GetMapping({"/files"})
    public ResponseEntity<List<File>> listEverything() throws IOException, GeneralSecurityException {
        List<File> files = googleService.getAllFiles();
        return ResponseEntity.ok(files);
    }

    @GetMapping("/download/{folderId}")
    public String readAndDownload(@PathVariable(required = false) String folderId) throws IOException, GeneralSecurityException {
        try {
         //  String folderId = "177hbk2GWCmzzsjdw_eP2pAPi_sgEctpw";
            googleService.readAndSaveFile(folderId);
            return "File downloaded and zipped successfully";
        } catch (Exception e) {
            return "Error downloading file : " + e.getMessage();
        }
    }
}
