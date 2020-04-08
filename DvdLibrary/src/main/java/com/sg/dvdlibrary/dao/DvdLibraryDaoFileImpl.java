/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Movie;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 *
 * @author FatouSagnaWork
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {
    
//Constant for file name and for the colons
    public static final String FILE_NAME = "dvdfile.txt";
    public static final String DELIMITER = "::";
   
    private Map<String, Movie> movies = new HashMap<>();
    private Movie movie;

   
    
   
   
// ************************************************
//Unmarshal code to translate  the lines of text 
   private Movie unmarshallMovie(String movieAsText){
   String[] movieTokens = movieAsText.split(DELIMITER);
   String title = movieTokens[0];
   Movie movieFromFile = new Movie(title);
// Index 1 - Release date
    movieFromFile.setReleaseDate(movieTokens[1]);

// Index 2 - Rating
    movieFromFile.setMpaaRating(movieTokens[2]);

// Index 3 - Directors name
    movieFromFile.setDirector(movieTokens[3]);
    
// Index 4 - Studio
    movieFromFile.setStudio(movieTokens[4]);
    
//Index 5 - User rating or note
    movieFromFile.setUserRatingOrNote(movieTokens[5]);

// We have now created a movie! Return it!
    return movieFromFile;
   }
   
   
//**********************************************************
//Marshall
private String marshallMovie(Movie aMovie){
    String movieAsText = aMovie.getTitle() + DELIMITER;
     movieAsText += aMovie.getReleaseDate() + DELIMITER;
     movieAsText += aMovie.getMpaaRating() + DELIMITER;
     movieAsText += aMovie.getDirector() + DELIMITER;
     movieAsText += aMovie.getStudio() + DELIMITER;
     movieAsText +=aMovie.getUserRatingOrNote();
    return movieAsText;
}

    
//Code to load movie  
 //************************************************  
    public void loadMovie() throws DvdLibraryDaoException{ 
    Scanner scanner;       
    try {
        scanner = new Scanner(
        new BufferedReader(
                new FileReader(FILE_NAME)));
    } catch (FileNotFoundException e) {
        throw new DvdLibraryDaoException(
                "-_- Could not load roster data into memory.",e);
    }
     String currentLine;
     Movie currentMovie;
     while(scanner.hasNextLine()) {
         currentLine = scanner.nextLine();
         currentMovie = unmarshallMovie(currentLine);
         movies.put(currentMovie.getTitle(), currentMovie);
     }
     scanner.close();    
    }

//****************************************************

//Write Movies. this code writes the movies on file
   
    public void writeMovie() throws DvdLibraryDaoException{
    PrintWriter out;
  
    
    try{
        out = new PrintWriter(new FileWriter(FILE_NAME));
    } catch (IOException e) {
        throw new DvdLibraryDaoException(
        "could not save DVD.", e);    
    }
    String movieAsText;
    List<Movie> movieList = this.getAllMovies();
    for (Movie currentMovie : movieList) {
    movieAsText = marshallMovie(currentMovie);
    out.println(movieAsText);

    out.flush();
    }

    out.close();
    } 


    
    @Override
    public Movie addMovie(String title, Movie movie) throws DvdLibraryDaoException {
    loadMovie();
    Movie newMovie = movies.put(title, movie);
    writeMovie();
    return newMovie;
    }
 
    @Override
    public List<Movie> getAllMovies() throws DvdLibraryDaoException{
    loadMovie();
    return new ArrayList<Movie>(movies.values());
    }

    
    @Override
    public Movie removeMovie(String title) throws DvdLibraryDaoException{
    loadMovie();
    Movie removedMovie = movies.remove(title);
    writeMovie();
    return removedMovie;
    }     
    
  
    @Override
    public Movie displayDvd(String title) throws DvdLibraryDaoException{
    loadMovie();
    return movies.get(title);
    }

    @Override
    public Movie getMovie(String title) throws DvdLibraryDaoException{
    loadMovie();
    return movies.get(title);
    }

    @Override
    public void editMovie(String title) throws DvdLibraryDaoException {
    movies.get(title);
    
    writeMovie();
    }

}