package com.bskyb.internettv.parental_control_service;

import com.bskyb.internettv.thirdparty.MovieService;
import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;

/**
 * Implementation of ParentalControlService. Encapsulates the ability to inform the caller if they can watch the 
 * movie they request based on parental control level
 * @author mheyward
 *
 */
public class ParentalControlServiceImpl implements ParentalControlService {
	
	private MovieService movieService;

	public ParentalControlServiceImpl(MovieService movieService) {
		this.movieService = movieService;
	}

	/**
	 * Method to inform the caller whether or not the user can watch the movie based on parental control level.
	 * @param customerParentalControlLevel String representing the parental control level for the customer
	 * @param movieId String representing the movie identifier required for MovieService
	 * @return true if customer can watch movie, false otherwise
	 */
	public boolean canWatchMovie(String customerParentalControlLevel, String movieId) throws TitleNotFoundException {

		String controlLevel = null;
		
		try {
			controlLevel = movieService.getParentalControlLevel(movieId);
		} catch(TechnicalFailureException e) {
			// Specification says customer cannot watch this movie if the MovieService throws a TechnicalFailureException
			// so printing the error and returning false
			e.printStackTrace();
			return false;
		}

		ParentalControlLevel parentalControlLevelFromService = getParentalControlLevelEnum(controlLevel);
		ParentalControlLevel parentalControlLevelFromCustomer = getParentalControlLevelEnum(customerParentalControlLevel);

		return parentalControlLevelFromService.ordinal() <= parentalControlLevelFromCustomer.ordinal();
	}
	
	/**
	 * Method to resolve ParentalControlLevel enum given string representation of parental guidance level.
	 * @param level String representation of parental control level
	 * @return ParentalControlLevel Enum representing parental control level
	 */
	private ParentalControlLevel getParentalControlLevelEnum(String level) {

		ParentalControlLevel[] parentalControlLevels = ParentalControlLevel.values();
		ParentalControlLevel matchedLevel = null;

		for (ParentalControlLevel parentalControlLevel : parentalControlLevels) {
			if (parentalControlLevel.getLevel().equals(level)) {
				matchedLevel = parentalControlLevel;
				break;
			}

		}
		return matchedLevel;

	}
	
	/**
	 * @return the movieService
	 */
	public MovieService getMovieService() {
		return movieService;
	}

	/**
	 * @param movieService the movieService to set
	 */
	public void setMovieService(MovieService movieService) {
		this.movieService = movieService;
	}
}
