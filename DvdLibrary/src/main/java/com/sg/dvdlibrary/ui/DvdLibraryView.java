/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Movie;
import java.util.List;

/**
 *
 * @author FatouSagnaWork
 */
public class DvdLibraryView {
    
    private String title;
    
    private UserIO io;
    public DvdLibraryView(UserIO io) {
    this.io = io;
}
    
    //Main menu for user selection
    public int printMenuAndGetSelection(){
        io.print("Main Menu");
        io.print("1. Add DVD");
        io.print("2. Delete DVD");
        io.print("3. Edit a DVD");
        io.print("4. Show all DVD's"); 
        io.print("5. Search by Title");
        io.print("6. Exit ");
        
        return io.readInt("Please select from the above choice." , 1, 6);
    }
    //Code to prompt user to create/add new movie information
    public Movie getNewMovieInformation() {
        String title = io.readString("Please enter movie title");
        String releaseDate = io.readString("Please enter realease date(MM/DD/YYYY)");
        String mpaaRating = io.readString("Please enter MPAA rating(G, PG, PG-13, R)");
        String director = io.readString("Please enter director's name");
        String studio = io.readString("Please enter studio name");
        String userRatingOrNote = io.readString("Enter your rating/notes");        
    //This code stores all of the info for the movie that the user enters when user is creating/adding a new movie
        Movie currentMovie = new Movie(title);
        currentMovie.setReleaseDate(releaseDate);
        currentMovie.setMpaaRating(mpaaRating);
        currentMovie.setDirector(director);
        currentMovie.setStudio(studio);
        currentMovie.setUserRatingOrNote(userRatingOrNote);
        return currentMovie;
    }
    //Message displayed when we are adding a DVD
    public void displayMovieBanner() {
    io.print("=== Add DVD ===");
    }
    
    //Message displayed after we have added a DVD
    public void displayCreateSuccessBanner() {
    io.readString(
            "DVD successfully created.  Please press enter to continue");
    }        
    
    // Code for displaying DVD list with info
    public void displayMovieList(List<Movie> movieList) {
        for(Movie currentMovie : movieList){
            io.print(currentMovie.getTitle() + ": "
                    + currentMovie.getReleaseDate() + ": "
                    + currentMovie.getMpaaRating() + ": "
                    + currentMovie.getDirector() + ": "
                    + currentMovie.getStudio() + ": "
                    +currentMovie.getUserRatingOrNote());
        }
        io.readString("Please press enter to continue. ");
    }
    
    public void displayDisplayAllBanner() {
    io.print("=== Display All DVDs ===");
    }
    
    // Code for displaying a DVD and it's information with a rule for what to display 
    //, and lets user know that it doesnt exist on file if title doesnt match DVD in library
    public void displayDisplayMovieBanner() {
        io.print("=== Display DVD Information ===");
    }
    
    public String getMovieTitle() {
        return io.readString("Please enter the DVD title.");
    }
    
    public void displayMovie(Movie movie) {
        if(movie != null) {
            io.print(movie.getTitle());
            io.print(movie.getReleaseDate());
            io.print(movie.getMpaaRating());
            io.print(movie.getDirector());
            io.print(movie.getStudio());
            io.print(movie.getUserRatingOrNote());
            io.print("");
        } else {
            io.print("That DVD is not on file.");
        }
        io.readString("Please press enter to continue.");
    }
    
    //Method for displaying banner. lets user know what they are to delete a DVD
    public void displayRemoveMovieBanner() {
        io.print("=== Delete DVD ===");
    }
    public void displayRemoveSuccessBanner() {
        io.print("DVD successfully removed. Please press enter to continue.");
    }
    
    
    //Method for displaying banner lets user know that they are editing a movie
    //And also code to edit movie
    public void displayEditMovieBanner(){
        io.print("=== Edit DVD ===");
    }   
    public void displayEditSuccessBanner(){
        io.print("DVD successfully edited. Please press enter to continue");
    }
       
    //Search code
    public void displaySearchMovieBanner(){
        io.print("=== Search DVD===");
    }
    
    public void displaySearchSuccessBanner(){
        io.print("Here is your search result.");
    }
    
     public String SearchMovie() {
	return io.readString("Enter the title of the DVD you want to see.");
    }
  public void displayDvd(Movie movie) {
	io.print("Title          - " + movie.getTitle());
	io.print("Rlease Date    - " + movie.getReleaseDate());
	io.print("MPAA Rating    - " + movie.getMpaaRating());
	io.print("Director Name  - " + movie.getDirector());
	io.print("Studio         - " + movie.getStudio());
	io.print("Note           - " + movie.getUserRatingOrNote());
	io.print("");
    }
 //Edit Movie
        public void displayDVDEditMenu() {
	io.print("1. Edit Release Date");
	io.print("2. Edit MPAA Rating");
	io.print("3. Edit Director Name");
	io.print("4. Edit Studio");
	io.print("5. Edit User Rating or Note");
	io.print("6. Exit");
    }
        public int getOptionDvdEditMenu() {
	return io.readInt("Choose an option.");
    }
        public void displayDVDEditInfo(String info) {
	io.print("The current information of this dvd is: ");
	io.print(info);
    }
         public String getDataDVDEdit(Movie movie, String propertyToEdit) {
	return io.readString("Enter a new " + propertyToEdit + ".");
    }
    
    
    public void displaySaveFileSuccessBanner(){
        io.print("You have successfully saved your library to the file.");
    }
   
    
    public void displayExitBanner() {
        io.print("Good Bye!");
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unkown Command!");
    } 

    public void displayMessageDVDNotFound() {
        io.print("Dvd not found!");
    }
    
    public void displayErrorMessage(String errorMsg) {
    io.print("=== ERROR ===");
    io.print(errorMsg);
}
}