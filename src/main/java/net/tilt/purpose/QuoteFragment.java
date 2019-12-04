package net.tilt.purpose;


import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {

    String quoteAndAuth = "TEST STRING";

    public QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View quoteView = inflater.inflate(R.layout.fragment_quote, container, false);
        TextView quoteText = quoteView.findViewById(R.id.quoteText);
        TextView quoteAuthor = quoteView.findViewById(R.id.quoteAuthor);
        CardView cardView = quoteView.findViewById(R.id.cardView);

        String quote = getArguments().getString("quote");
        String author = getArguments().getString("author");




        int colours[] = new int[] {R.color.md_blue_400, R.color.md_light_blue_400,
                R.color.md_blue_600, R.color.md_blue_grey_200, R.color.md_blue_300,
                R.color.md_indigo_800, R.color.md_blue_500, R.color.md_blue_700,
                R.color.md_blue_900, R.color.md_indigo_700, R.color.md_indigo_900};

        quoteText.setText(quote);
        quoteAuthor.setText("~" + author);



        //Adding here to share Quote and Auth
        //setQuoteAndAuthor(quote + " ~ " + author);

        //calls getRandomQuoteColours for random BG as per colours [] above
        cardView.setBackgroundResource(getRandomQuoteColours(colours));

        return quoteView;
    }

    public static final QuoteFragment newInstance(String quote, String author) {
        QuoteFragment quoteFragment = new QuoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString("quote", quote);
        bundle.putString("author", author);

        quoteFragment.setArguments(bundle);



        return quoteFragment;
    }

    int getRandomQuoteColours(int[] colourArray) {
        int colour;
        int quotesArrayLength = colourArray.length;

        int randomNumber = ThreadLocalRandom.current().nextInt(quotesArrayLength);

        colour = colourArray[randomNumber];

        return colour;
    }





}
