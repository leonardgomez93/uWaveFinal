package com.example.leonardgomez.uwavefinal.uwavechat;

/**
 * Created by chrisoung on 3/24/18.
 */

public class UWaveChatMessage {

    private String id;
    private String name;
    private String text;
    private String photoUrl;
    private String imageUrl;


    public UWaveChatMessage() {

    }

    /*public UWaveChatMessage(String id, String name, String description, String photoUrl, String imageUrl){

        this.setUWaveChat(id, name, description, photoUrl, imageUrl);

    }

    public void setUWaveChat(String id, String name, String description, String photoUrl, String imageUrl) {
        this.setId(id);
        this.setDescription(name);
        this.setPhotoUrl(photoUrl);
        this.setImageUrl(imageUrl);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }*/

    public UWaveChatMessage(String text, String name, String photoUrl, String imageUrl) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
        this.imageUrl = imageUrl;
    }

    public String getId() {

        return this.id;
    }

    public void setId(String id) {

        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
