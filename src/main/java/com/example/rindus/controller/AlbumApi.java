package com.example.rindus.controller;

import com.example.rindus.entity.Album;
import com.example.rindus.entity.Comment;
import com.example.rindus.exception.ResourceFormatException;
import com.example.rindus.model.AlbumRequest;
import com.example.rindus.model.CommentsRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.List;

@Api(value="albums")
public interface AlbumApi {
    @ApiOperation(value = "Get albums", nickname = "getAlbums", response = Album.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "No comments found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/albums", method= RequestMethod.GET)
    ResponseEntity<List<Album>> getAlbums(@ApiParam(value="Extract data to JSON file")
                                          @Valid @RequestParam(required=false, defaultValue = "false") boolean extractJson,
                                          @ApiParam(value="Extract data to XML file")
                                          @Valid @RequestParam(required=false, defaultValue = "false") boolean extractXml) throws IOException;

    @ApiOperation(value = "Create a new album", nickname = "postAlbum", response = Album.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "New Album created successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/albums", produces={"application/JSON"}, method= RequestMethod.POST)
    ResponseEntity<Album> postAlbum(@ApiParam(value="Data for new album")
                                        @Valid @RequestBody(required=true) AlbumRequest request) throws UnexpectedException, ResourceFormatException;

    @ApiOperation(value = "Update album or create if not found", nickname = "putAlbum", response = Album.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Album found and updated successfully"),
            @ApiResponse(code = 201, message = "Album not found, so it was created successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/albums", produces={"application/JSON"}, method= RequestMethod.PUT)
    ResponseEntity<Album> putAlbum(@ApiParam(value="Album data with updated information")
                                       @Valid @RequestBody(required=true) AlbumRequest request) throws UnexpectedException, ResourceFormatException;

    @ApiOperation(value = "Patch album", nickname = "patchAlbum", response = Album.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Album updated successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Album not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/albums", produces={"application/JSON"}, method= RequestMethod.PATCH)
    ResponseEntity<Album> patchAlbum(@ApiParam(value="Album data with updated information")
                                         @Valid @RequestBody(required=true) AlbumRequest request) throws UnexpectedException, ResourceFormatException;


    @ApiOperation(value = "Delete album", nickname = "deleteAlbum", response = Album.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Album deleted successfully"),
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Album not found"),
            @ApiResponse(code = 500, message = "Application failed to process the request")
    })
    @RequestMapping(value="/albums", produces={"application/JSON"}, method= RequestMethod.DELETE)
    ResponseEntity deleteAlbum(@ApiParam(value="Id of the album to delete")
                                 @Valid @RequestParam(required=true) int albumId) throws UnexpectedException;
}
