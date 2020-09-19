package com.biprom.bram.backend.Upload;

import com.vaadin.ui.Upload;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class LineBreakCounter implements Upload.Receiver {
    private int counter;
    private int total;
    private boolean sleep;
    private  File file;

    //private String root = "/Users/bramvandenberghe/amcal";
    private String root = "C:\\dynimc\\";

    //private String inter = "/";
    private String inter = "\\";

    // Callback method to begin receiving the upload.
    @Override
    public OutputStream receiveUpload(String filename,
                                      String MIMEType) {
        FileOutputStream fos = null; // Output stream to write to
        file = new File(root + inter +"uplPicToDB"+getUserName()+ inter + filename);
        try {
            // Open the file for writing.
            fos = new FileOutputStream(file);
        } catch (final java.io.FileNotFoundException e) {
            // Error while opening the file. Not reported here.
            e.printStackTrace();
            return null;
        }

        return fos; // Return the output stream to write to
    }



    public int getLineBreakCount() {
        return counter;
    }

    public void setSlow(boolean value) {
        sleep = value;
    }

    public String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return  currentPrincipalName;
    }
}


