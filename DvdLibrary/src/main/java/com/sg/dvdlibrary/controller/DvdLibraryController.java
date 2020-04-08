/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.dao.DvdLibraryDao;
import com.sg.dvdlibrary.dao.DvdLibraryDaoException;
import com.sg.dvdlibrary.dto.Movie;
import com.sg.dvdlibrary.ui.DvdLibraryView;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author FatouSagnaWork
 */

public class DvdLibraryController {
    
      DvdLibraryView view;
      DvdLibraryDao dao;

    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
    this.dao = dao;
    this.view = view;
    }

   Scanner e = new Scanner(System.in);
   
   int wrongInputCounter = 0;

    public void run() {      
        boolean keepGoing = true;
        int menuSelection = 0;
        int wrongInputcounter;
        try {
        while (keepGoing) {
            
            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    createMovie();
                    break;
                case 2:
                    removeMovie();
                    break;
                case 3:
                    editMovie();
                    break;
                case 4:
                    listMovies();
                    break;
                case 5:
                    displayDvd();
                    break;
                case 6:
                    System.exit(0);
                default:
                    unknownCommand();
                }

        }
        exitMessage();
    } catch (DvdLibraryDaoException e) {
        view.displayErrorMessage(e.getMessage());
    }
}

    //Moved method to get menu selection from the switch to here
    private int getMenuSelection() {
    return view.printMenuAndGetSelection();
    }
    
    //This method is what DvdLibraryView orchestrates when creating  a DVD
    private void createMovie() throws DvdLibraryDaoException {
        view.displayMovieBanner();
        Movie newMovie = view.getNewMovieInformation();
        dao.addMovie(newMovie.getTitle(), newMovie);
        view.displayCreateSuccessBanner();
    }
    
    //This method is what DvdLibraryView orachestrates when listing DVDs
    private void listMovies() throws DvdLibraryDaoException {
        view.displayDisplayAllBanner();
        List<Movie> movieList = dao.getAllMovies();
        view.displayMovieList(movieList);
    }

    
    private void removeMovie() throws DvdLibraryDaoException {
        view.displayRemoveMovieBanner();
        String title = view.getMovieTitle();
        dao.removeMovie(title);
        view.displayRemoveSuccessBanner();
    }
    
      private void displayDvd () throws DvdLibraryDaoException  {
      view.displaySearchMovieBanner();
      String title = view.SearchMovie();
      Movie movie = dao.getMovie(title);
    
      if (movie == null){
          view.displayMessageDVDNotFound();
      }
      else {
          view.displayDvd(movie);
      }
    }
    
    private void editMovie() throws DvdLibraryDaoException {
        int MenuSelection;
        String title;
        view.displayEditMovieBanner();
        title = view.getMovieTitle();
        Movie movie = dao.getMovie(title);
        if (movie == null) {
            view.displayMessageDVDNotFound();
            return;
        }
        do{
            view.displayEditMovieBanner();
            view.displayDVDEditMenu();
            MenuSelection = view.getOptionDvdEditMenu();
            if (MenuSelection == 6) {
                break;
            }
            else if (MenuSelection >= 1 && MenuSelection <= 5) {
                String newInfo;
                switch(MenuSelection) {
                    case 1:
                        view.displayDVDEditInfo(movie.getReleaseDate());
                        dao.removeMovie(title);
                        newInfo = view.getDataDVDEdit(movie, "What is the new release date?");
                        movie.setReleaseDate(newInfo);
                        dao.addMovie(title, movie);
                        view.displayEditSuccessBanner();
                        break;
                    
                    case 2:
                        view.displayDVDEditInfo(movie.getMpaaRating());
                        newInfo = view.getDataDVDEdit(movie, "What is the MPAA Rating?");
                        dao.removeMovie(title);
                        movie.setMpaaRating(newInfo);
                        dao.addMovie(title, movie);
                        view.displayEditSuccessBanner();
                        break;
                        
                    case 3: 
                        view.displayDVDEditInfo(movie.getDirector());
                        dao.removeMovie(title);
                        newInfo = view.getDataDVDEdit(movie, "What is new director's name?");
                        movie.setDirector(newInfo);
                        dao.addMovie(title, movie);
                        view.displayEditSuccessBanner();
                        break;
                    
                    case 4:
                        view.displayDVDEditInfo(movie.getMpaaRating());
                        dao.removeMovie(title);
                        newInfo = view.getDataDVDEdit(movie, "What is the new Studio?");
                        movie.setStudio(newInfo);
                        dao.addMovie(title, movie);
                        view.displayEditSuccessBanner();
                        break;
                    case 5:
                        view.displayDVDEditInfo(movie.getUserRatingOrNote());
                        dao.removeMovie(title);
                        newInfo = view.getDataDVDEdit(movie, "What is the new note?");
                        movie.setUserRatingOrNote(newInfo);
                        dao.addMovie(title, movie);
                        view.displayEditSuccessBanner();
                        break;
                }
            }
            else if(wrongInputCounter >= 5 || MenuSelection ==6){
                wrongInputCounter = 0;
                break;                
            }
            else {
                view.displayUnknownCommandBanner();
                wrongInputCounter++;
            }
        }
            while(true);
        
        }
   
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
     private void exitMessage() {
        view.displayExitBanner();
    }
}