package com.biprom.bram.backend.service.GridFS;

import com.biprom.bram.app.security.SecurityUtils;
import com.biprom.bram.backend.data.entity.mongodbEntities.DetailTicket;
import com.biprom.bram.backend.data.entity.mongodbEntities.ElektrMateriaal;
import com.biprom.bram.backend.data.entity.mongodbEntities.ImageEntity;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.vaadin.server.FileResource;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class GridFSService {

    @Value("${root}")
    String root;

    @Value("${inter}")
    String inter;

    GridFsTemplate gridFSTemplate;
    GridFsOperations operations;

    List<GridFSFile>receivedGridFSFiles = new ArrayList<GridFSFile>();
    DBObject metaData = new BasicDBObject(  );

    String returnedIdFromDB;
    List<String> idPictureFileList;
    List<String>idPDFFile = new ArrayList<>(  );
    List<FileResource> resourcesPics = new ArrayList<>();
    List<String> metaList = new ArrayList<>();
    List<File> filesPDF = new ArrayList<File>();
    DetailTicket detailTicket;
    List<ImageEntity>imageEntityList = new ArrayList<>(  );
    String userName = new String();


    @Autowired
    public GridFSService(GridFsTemplate gridFSTemplate,
                         GridFsOperations operations)  {

       this.gridFSTemplate = gridFSTemplate;
       this.operations = operations;
    }

    public void storeFileToMongoDB (String folder, String meta1, String meta2, String newFile, DetailTicket detailTicket, Boolean fotoOrNot){

        this.detailTicket = detailTicket;

        File file = new File( folder );
        FileInputStream inputStream = null;

        try {
            if(detailTicket.getPictureList()!= null){
                idPictureFileList = detailTicket.getPictureList();
            }
            else{
                idPictureFileList = new ArrayList<>(  );
            }
            if(detailTicket.getPdfList()!= null){
                idPDFFile = detailTicket.getPdfList();
            }
            else{
                idPDFFile = new ArrayList<>(  );
            }
            inputStream = new FileInputStream( file );
            metaData.put( meta1, meta2 );
            returnedIdFromDB = gridFSTemplate.store( inputStream, newFile, "image/png",metaData ).toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

            if (fotoOrNot == true) {
                idPictureFileList.add(returnedIdFromDB);
                detailTicket.setPictureList(idPictureFileList);
            }

            else if(fotoOrNot ==false){
                idPDFFile.add(returnedIdFromDB);
                detailTicket.setPdfList(idPDFFile);
            }

    }



    public void findFilesById (String id, String nameExternLogin){

        metaList.clear();
        resourcesPics.clear();
        imageEntityList.clear();

        if(nameExternLogin != null){
            userName = nameExternLogin;
        }
        else{
            userName = SecurityUtils.getUsername();
        }

        deleteFolderContent( new File(root + inter + "recPicFromDB"+userName));
        receivedGridFSFiles.clear();
        gridFSTemplate.find( new Query( Criteria.where( "_id" ).is( id ) )).into(receivedGridFSFiles);

        for (GridFSFile file : receivedGridFSFiles) {
            try {
                InputStream initialStream = operations.getResource(file.getFilename()).getInputStream();
                File targetFile = new File(root + inter +"recPicFromDB"+userName+inter + file.getFilename());
                java.nio.file.Files.copy(
                        initialStream,
                        targetFile.toPath(),
                        StandardCopyOption.REPLACE_EXISTING);

                IOUtils.closeQuietly(initialStream);

               // file.writeTo( "/data/kabba/amcal/recPicFromDB"+userName+"/" + file.getFilename() );
                resourcesPics.add( new FileResource( new File( root + inter + "recPicFromDB"+userName+inter+ file.getFilename() ) ) );
                metaList.add( file.getMetadata().get( "meta1" ).toString() );
                imageEntityList.add( new ImageEntity( root + inter + "recPicFromDB"+userName+inter+ file.getFilename(), file.getFilename(), file.getMetadata().get( "meta1" ).toString(), false ) );
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    public void findFilesForDetailTicket (DetailTicket detailTicket, String nameExternLogin){

        metaList.clear();
        this.detailTicket = detailTicket;

        if(nameExternLogin != null){
            userName = nameExternLogin;
        }
        else{
            userName = SecurityUtils.getUsername();
        }

        if(detailTicket.getPictureList()!= null){
            idPictureFileList = detailTicket.getPictureList();
        }
        else{
            idPictureFileList = new ArrayList<>(  );
        }

        deleteFolderContent( new File(root + inter + "recPicFromDB"+userName));

        resourcesPics.clear();
        imageEntityList.clear();

            if(detailTicket.getPictureList() != null) {

                for (String str : detailTicket.getPictureList()) {

                    receivedGridFSFiles.clear();
                    gridFSTemplate.find( new Query( Criteria.where( "_id" ).is( str ) )).into(receivedGridFSFiles);

                    for (GridFSFile file : receivedGridFSFiles) {
                        try {
                            InputStream initialStream = operations.getResource(file.getFilename()).getInputStream();
                            File targetFile = new File(root + inter + "recPicFromDB"+userName+inter + file.getFilename());
                            java.nio.file.Files.copy(
                                    initialStream,
                                    targetFile.toPath(),
                                    StandardCopyOption.REPLACE_EXISTING);

                            IOUtils.closeQuietly(initialStream);

                            // file.writeTo( "/data/kabba/amcal/recPicFromDB"+userName+"/" + file.getFilename() );
                            resourcesPics.add( new FileResource( new File( root + inter +  "recPicFromDB"+userName+inter+ file.getFilename() ) ) );
                            metaList.add( file.getMetadata().get( "meta1" ).toString() );
                            imageEntityList.add( new ImageEntity( root + inter + "recPicFromDB"+userName+inter+ file.getFilename(), file.getFilename(), file.getMetadata().get( "meta1" ).toString(), false ) );
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                }
            }

    }

    public void changeMetaData(ImageEntity imageEntity){

        Query query = new Query( Criteria.where( "filename" ).is( imageEntity.getName() ));
        gridFSTemplate.delete( query );

        FileInputStream inputStream = null ;
        try {
            inputStream = new FileInputStream( imageEntity.getPath() );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        DBObject newMetaData = new BasicDBObject(  );
        newMetaData.put( "meta1", imageEntity.getComment() );

        returnedIdFromDB = gridFSTemplate.store( inputStream, imageEntity.getName(), "image/png", newMetaData ).toString();

        idPictureFileList.add(returnedIdFromDB);
        detailTicket.setPictureList(idPictureFileList);

    }


    public void deleteImage(String filename){
        gridFSTemplate.delete( new Query( Criteria.where( "filename" ).is( filename ) ) );
    }

    public List<FileResource>getResourcesPics(){
        return resourcesPics;
    }

    public List<String> getMetaList() {
        return metaList;
    }

    public List<ImageEntity> getImageEntityList() {
        return imageEntityList;
    }

    public void deleteFolderContent(File folder) {

        File[] files = folder.listFiles();
        if(files!=null) { //some JVMs return null for empty dirs
            for(File f: files) {

                    f.delete();

            }
        }
    }

    public void storeFileToMongoDB (String folder, String meta1, String meta2, String newFile, ElektrMateriaal elektrMateriaal, Boolean fotoOrNot){

        File file = new File( folder );
        FileInputStream inputStream = null;

        try {
            if(elektrMateriaal.getImageList()!= null){
                idPictureFileList = elektrMateriaal.getImageList();
            }
            else{
                idPictureFileList = new ArrayList<>(  );
            }
            inputStream = new FileInputStream( file );
            metaData.put( meta1, meta2 );
            returnedIdFromDB = gridFSTemplate.store( inputStream, newFile, "image/png",metaData ).toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (fotoOrNot == true) {
            idPictureFileList.add(returnedIdFromDB);
            elektrMateriaal.setImageList(idPictureFileList);
        }

    }
}
