package com.bskyb.internettv.parental_control_service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bskyb.internettv.parental_control_service.ParentalControlServiceImpl;
import com.bskyb.internettv.thirdparty.MovieService;
import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ParentalControlServiceTest {
	
	private static final String EIGHTEEN = "18";
	private static final String TWELVE = "12";
	private static final String PG = "PG";
	private static final String MOVIE_ID = "movieId";

	@InjectMocks ParentalControlServiceImpl testObj;
	
	@Mock MovieService movieServiceMock;

	@Before
	public void setUp() throws Exception {
		testObj = new ParentalControlServiceImpl(movieServiceMock);
	}

	@Test
	public void testCanWatchMovieReturnsTrueWhenCustomerParentalLevelIsLessThanMovieServiceParentalLevel() throws Exception {
		when(movieServiceMock.getParentalControlLevel(MOVIE_ID)).thenReturn(PG);
		
		boolean result = testObj.canWatchMovie(TWELVE, MOVIE_ID);
		
		assertTrue(result);
	}

	@Test
	public void testCanWatchMovieReturnsTrueWhenCustomerParentalLevelIsEqualToMovieServiceParentalLevel() throws Exception {
		when(movieServiceMock.getParentalControlLevel(MOVIE_ID)).thenReturn(TWELVE);

		boolean result = testObj.canWatchMovie(TWELVE, MOVIE_ID);

		assertTrue(result);
	}

	@Test
	public void testCanWatchMovieReturnsFalseWhenCustomerParentalLevelIsGreaterThanMovieServiceParentalLevel() throws Exception {
		when(movieServiceMock.getParentalControlLevel(MOVIE_ID)).thenReturn(EIGHTEEN);

		boolean result = testObj.canWatchMovie(TWELVE, MOVIE_ID);

		assertFalse(result);
	}

	@Test(expected = TitleNotFoundException.class)
	public void testCanWatchMovieThrowsTitleNotFoundExceptionWhenMovieIsNotFoundByMovieService() throws Exception {
		when(movieServiceMock.getParentalControlLevel(MOVIE_ID)).thenThrow(new TitleNotFoundException("The movie service could not find the given movie"));

		testObj.canWatchMovie(TWELVE, MOVIE_ID);
	}

	@Test
	public void testCanWatchServiceReturnsFalseIfMovieServiceThrowsTechnicalFailureException() throws Exception {
		when(movieServiceMock.getParentalControlLevel(MOVIE_ID)).thenThrow(new TechnicalFailureException());
		
		boolean result = testObj.canWatchMovie(TWELVE, MOVIE_ID);
		
		assertFalse(result);
	}
}
