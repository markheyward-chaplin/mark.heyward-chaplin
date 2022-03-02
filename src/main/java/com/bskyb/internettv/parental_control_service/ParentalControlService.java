package com.bskyb.internettv.parental_control_service;

import com.bskyb.internettv.thirdparty.TitleNotFoundException;

public interface ParentalControlService {
    boolean canWatchMovie(String customerParentalControlLevel, String movieId) throws TitleNotFoundException;
}
