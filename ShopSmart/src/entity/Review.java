package com.hexaware.entity;

public class Review {
    private int rating;  // User rating from 1 to 5
    private String comment;  // Review comment

    // Constructor to initialize rating and comment
    public Review(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    // Getters and setters
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    // toString() method to return a string representation of the review
    @Override
    public String toString() {
        return "Review{" +
                "rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
