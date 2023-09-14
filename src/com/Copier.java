package com;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Copier {
 
    public static void main(String[] args) throws IOException {
    	
    	Copier.testNavigation("C:\\fotos_celu", "C:\\all_photos");
    }
   
    public static void testNavigation(String SOURCE, String DEST) {
    	File destFolder = new File(DEST);
    	if(destFolder.mkdir()) { // If true: create folder, else: folder exists
    		System.out.println("Created the dest folder");
    	}
    	
    	// I create a directory with my path.
        File directory = new File(SOURCE);
        
        if(!directory.exists() || !directory.isDirectory()) {
            System.out.println("The directory does not exist");        	
        }
        // Initialize a List to store my photos!!!
        List<File> photosList = new ArrayList<>();
        File[] filesAndDirsArray = directory.listFiles();

        // Add my pics to the List of pics
        if(filesAndDirsArray != null) {
            for(File folderOfPhotos : filesAndDirsArray) {
            	File[] photos = folderOfPhotos.listFiles();		
        		if(photos.length > 0) { // This is just in case the folder is empty
            		for(File photo : photos) {
            			photosList.add(photo);
            		}                			
        		}
            }
        }
        // Loop through my pics
        try {
	        for (File ph : photosList) {
	        	File destPhoto = new File(DEST + "\\" + ph.getName());
	        	// Actual copy!
	        	Files.copy(ph.toPath(), destPhoto.toPath(), StandardCopyOption.REPLACE_EXISTING);
	        }
        } catch(Exception e) {
        	System.out.println(e.getMessage());
        }
    }
}