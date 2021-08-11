package com.example.rindus;

import com.example.rindus.entity.*;
import com.example.rindus.model.*;

import java.util.ArrayList;
import java.util.List;

public final class TestUtils {

    public static Album createDummyAlbum() {
        Album album = new Album();

        album.setId(1);
        album.setTitle("album");
        album.setUserId(1);

        return album;
    }

    public static AlbumRequest createDummyAlbumRequest() {
        AlbumRequest album = new AlbumRequest();

        album.setId(1);
        album.setTitle("album");
        album.setUserId(1);

        return album;
    }

    public static AlbumRequest createInvalidAlbumRequest() {
        AlbumRequest album = new AlbumRequest();

        album.setId(0);
        album.setTitle("album");
        album.setUserId(1);

        return album;
    }

    public static List<Album> createDummyAlbumList() {

        List<Album> albums = new ArrayList<>();
        albums.add(createDummyAlbum());
        albums.add(createDummyAlbum());
        albums.add(createDummyAlbum());
        albums.add(createDummyAlbum());

        return albums;
    }

    public static Comment createDummyComment() {
        Comment comment = new Comment();

        comment.setId(1);
        comment.setBody("Body");
        comment.setEmail("nicoyamin@test.com");
        comment.setPostId(1);
        return comment;
    }

    public static CommentsRequest createDummyCommentRequest() {
        CommentsRequest comment = new CommentsRequest();

        comment.setId(1);
        comment.setBody("Body");
        comment.setEmail("nicoyamin@test.com");
        comment.setPostId(1);
        return comment;
    }

    public static CommentsRequest createInvalidCommentRequest() {
        CommentsRequest comment = new CommentsRequest();

        comment.setId(1);
        comment.setBody("Body");
        comment.setEmail("invalid email");
        comment.setPostId(1);
        return comment;
    }

    public static List<Comment> createDummyCommentList() {

        List<Comment> comments = new ArrayList<>();
        comments.add(createDummyComment());
        comments.add(createDummyComment());
        comments.add(createDummyComment());
        comments.add(createDummyComment());

        return comments;
    }

    public static PhotoRequest createDummyPhotoRequest() {
        PhotoRequest photo = new PhotoRequest();

        photo.setId(1);
        photo.setAlbumId(1);
        photo.setTitle("title");
        photo.setThumbnailUrl("https://via.placeholder.com/600/771796");
        photo.setUrl("https://via.placeholder.com/600/771796");
        return photo;
    }

    public static PhotoRequest createInvalidPhotoRequest() {
        PhotoRequest photo = new PhotoRequest();

        photo.setId(1);
        photo.setAlbumId(1);
        photo.setTitle("title");
        photo.setThumbnailUrl("https://via.placeholder.com/600/771796");
        photo.setUrl("Invalid Url");
        return photo;
    }

    public static PostRequest createDummyPostRequest() {
        PostRequest post = new PostRequest();

        post.setBody("body");
        post.setId(1);
        post.setTitle("title");
        post.setUserId(1);

        return post;
    }

    public static PostRequest createInvalidPostRequest() {
        PostRequest post = new PostRequest();

        post.setBody("body");
        post.setId(0);
        post.setTitle("title");
        post.setUserId(1);

        return post;
    }

    public static TodoRequest createDummyTodoRequest() {
        TodoRequest todo = new TodoRequest();

        todo.setId(1);
        todo.setTitle("title");
        todo.setUserId(1);
        todo.setCompleted(false);

        return todo;
    }

    public static TodoRequest createInvalidTodoRequest() {
        TodoRequest todo = new TodoRequest();

        todo.setId(0);
        todo.setTitle("title");
        todo.setUserId(1);
        todo.setCompleted(false);

        return todo;
    }

    public static UserRequest createDummyUserRequest() {
        UserRequest user = new UserRequest();

        Coordinates coord = new Coordinates();
        coord.setLat("12.12");
        coord.setLng("-12.12");

        Address address = new Address();
        address.setCity("city");
        address.setStreet("street");
        address.setSuite("suite");
        address.setZipcode("1234");
        address.setGeo(coord);

        Company company = new Company();
        company.setName("company");
        company.setCatchPhrase("Catchphrase");
        company.setBs("bs");

        user.setId(1);
        user.setName("name");
        user.setEmail("email@test.com");
        user.setPhone("123456");
        user.setUsername("username");
        user.setAddress(address);
        user.setCompany(company);

        return user;
    }

    public static UserRequest createInvalidUserRequest() {
        UserRequest user = new UserRequest();

        Coordinates coord = new Coordinates();
        coord.setLat("12.12");
        coord.setLng("-12.12");

        Address address = new Address();
        address.setCity("city");
        address.setStreet("street");
        address.setSuite("suite");
        address.setZipcode("1234");
        address.setGeo(coord);

        Company company = new Company();
        company.setName("company");
        company.setCatchPhrase("Catchphrase");
        company.setBs("bs");

        user.setId(0);
        user.setName("name");
        user.setEmail("email@test.com");
        user.setPhone("123456");
        user.setUsername("username");
        user.setAddress(address);
        user.setCompany(company);

        return user;
    }

}
