package com.sj.resources.user;

import com.sj.model.Content;
import com.sj.model.UserModel;
import com.sj.repository.ContentRepository;
import com.sj.service.UserService;
import org.bson.types.ObjectId;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

@Path("/content")
public class ContentResource {

    public static final String uploadingdir = System.getProperty("user.dir") + "/uploadingdir/";

    @Autowired
    private UserService userService;


    @POST
    @Path("/file")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({MediaType.APPLICATION_JSON})
    public Response uploadFile( @FormDataParam("file") byte[] fileByte,
                              @FormDataParam("file") FormDataContentDisposition fileMetaData) throws IOException {
        Content content = userService.saveContent(fileByte, fileMetaData.getFileName(), fileMetaData.getType());

        return Response.status(201).entity(content).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findById(@PathParam("id") ObjectId id) {
        Content content = userService.getContentFromMongo(id);
        if (content != null) {
            return Response.status(200).entity(content).build();
        } else {
            return Response.status(404).entity("The content with the id " + id + " does not exist").build();
        }
    }

    @GET
    @Path("{id}/{searchString}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response findByIdOrSearchString(@PathParam("id") ObjectId id,@PathParam("searchString") String searchString) {
        List<Content> contents = userService.getContentFromElasticSearch(id, searchString);
        if (contents != null) {
            return Response.status(200).entity(contents).build();
        } else {
            return Response.status(404).entity("The content with the id " + id + " does not exist").build();
        }
    }
}
