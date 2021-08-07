package com.example.rindus.controller;


import com.example.rindus.entity.Photo;
import com.example.rindus.model.PhotoRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Api(value="photos")
public interface PhotoApi {
    @ApiOperation(value = "Get photos", nickname = "getPhotos", response = Photo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 204, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "No photos found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/photos", method= RequestMethod.GET)
    ResponseEntity<List<Photo>> getPhotos(@ApiParam(value="Extract data to JSON file")
                                          @Valid @RequestParam(required=false) boolean extractJson,
                                          @ApiParam(value="Extract data to XML file")
                                          @Valid @RequestParam(required=false) boolean extractXml);

    @ApiOperation(value = "Create a new Photo", nickname = "postPhoto", response = Photo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "New Photo created successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 204, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/photos", produces={"application/JSON"}, method= RequestMethod.POST)
    ResponseEntity<Photo> postPhoto(@ApiParam(value="Data for new Photo")
                                        @Valid @RequestBody(required=true) PhotoRequest request);

    @ApiOperation(value = "Update Photo or create if not found", nickname = "putPhoto", response = Photo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Photo found and updated successfully"),
            @ApiResponse(code = 201, message = "Photo not found, so it was created successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 204, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/photos", produces={"application/JSON"}, method= RequestMethod.PUT)
    ResponseEntity<Photo> putPhoto(@ApiParam(value="Photo data with updated information")
                                       @Valid @RequestBody(required=true) PhotoRequest request);

    @ApiOperation(value = "Patch Photo", nickname = "patchPhoto", response = Photo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Photo updated successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 204, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Photo not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/photos", produces={"application/JSON"}, method= RequestMethod.PATCH)
    ResponseEntity<Photo> patchPhoto(@ApiParam(value="Photo data with updated information")
                                         @Valid @RequestBody(required=true) PhotoRequest request);


    @ApiOperation(value = "Delete Photo", nickname = "deletePhoto", response = Photo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Photo deleted successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 204, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Photo not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/photos", produces={"application/JSON"}, method= RequestMethod.DELETE)
    ResponseEntity deletePhoto(@ApiParam(value="Id of the Photo to delete")
                                 @Valid @RequestParam(required=true) int photoId);
}
