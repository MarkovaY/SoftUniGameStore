package com.example.softunigamestore.models.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class GameAddDto {

//    As an admin, you have the option to add/edit/delete games to the catalog.
//    AddGame|<title>|<price>|<size>|<trailer>|<thumbnailURL>|<description>|<releaseDate>
//    EditGame|<id>|<values> - A game should be edited in case of valid id. Otherwise, print appropriate message.

    private String title;
    private BigDecimal price;
    private Double size;
    private String trailer;
    private String thumbnailURL;
    private String description;
    private LocalDate releaseDate;

    public GameAddDto() {
    }

    public GameAddDto(String title, BigDecimal price, Double size, String trailer, String thumbnailURL, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnailURL = thumbnailURL;
        this.description = description;
        this.releaseDate = releaseDate;
    }

//    Title – has to begin with an uppercase letter and must have length between 3 and 100 symbols (inclusively).
    @Pattern(regexp = "^[A-Z].{2,100}$", message = "Enter valid title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    Price – must be a positive number.
    @DecimalMin(value = "0", message = "Enter valid price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

//    Size – must be a positive number.
//    @Min(0)
    @Positive(message = "Enter valid size")
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

//    Trailer – only videos from YouTube are allowed. Only their ID, which is a string of exactly 11 characters, should be saved to the database.
//    For example, if the URL to the trailer is https://www.youtube.com/watch?v=edYCtaNueQY, the required part that must be saved into the database is edYCtaNueQY.
//    That would be always the last 11 characters from the provided URL.
    @Size(min = 11, max = 11)
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

//    Thumbnail URL – it should be a plain text starting with http://, https://
    @Pattern(regexp = "^(http|https)://.*$", message = "Enter valid thumbnail URL")
    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    @Size(min = 20, message = "Enter valid description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
