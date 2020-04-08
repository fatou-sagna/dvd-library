/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Movie;
import java.util.List;

/**
 *
 * @author FatouSagnaWork
 */
public interface DvdLibraryDao {
    
    //Adds DVD by title
    Movie addMovie(String title, Movie movie)
    throws DvdLibraryDaoException;
    
    // Returns a string of arrays containing all DVDs
    List<Movie> getAllMovies()
    throws DvdLibraryDaoException;
    
    //Returns a particular DVD and the information associated with it
    public Movie getMovie(String title)
    throws DvdLibraryDaoException;
   
    public void editMovie(String title)
       throws DvdLibraryDaoException;

            
    //Removes a DVD from library
    Movie removeMovie(String title)
    throws DvdLibraryDaoException;
    // Edit movie
    
    //search movie
    public Movie displayDvd(String title)
    throws DvdLibraryDaoException;
}