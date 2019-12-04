package net.tilt.purpose.data;

import net.tilt.purpose.model.Quote;

import java.util.ArrayList;

public interface QuoteListAsyncResponse {

    void dataDownloadFinished(ArrayList<Quote> quotes);
}
