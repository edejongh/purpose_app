package net.tilt.purpose;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;


import net.tilt.purpose.data.QuoteData;
import net.tilt.purpose.data.QuoteListAsyncResponse;
import net.tilt.purpose.data.QuoteViewPagerAdapter;
import net.tilt.purpose.model.Quote;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private QuoteViewPagerAdapter quoteViewPagerAdapter;
    private ViewPager viewPager;
    private Quote quote = new Quote();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        quoteViewPagerAdapter = new QuoteViewPagerAdapter(getSupportFragmentManager(), getFragments());
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(quoteViewPagerAdapter);



        Button shareBtn = (Button) findViewById(R.id.shareBtn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            // Click event trigger here
                shareQuote();
            }
        });



    }

    private void shareQuote() {

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);

        sharingIntent.setType("text/plain");
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Good Vibrations");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, getCurrentQuote());
        //sharingIntent.putExtra(Intent.EXTRA_TEXT, "http://www.codeofaninja.com");
        startActivity(Intent.createChooser(sharingIntent, "Share quote!"));


    }

    private List<Fragment> getFragments() {
        final List<Fragment> fragmentList = new ArrayList<>();
        new QuoteData().getQuotes(new QuoteListAsyncResponse() {
            @Override
            public void dataDownloadFinished(ArrayList<Quote> quotes) {
                for (int i = 0; i < quotes.size(); i++) {
                    QuoteFragment quoteFragment =  QuoteFragment.newInstance(
                            quotes.get(i).getQuoteText(),
                            quotes.get(i).getQuoteAuthor());
                    fragmentList.add(quoteFragment);
                }

                quoteViewPagerAdapter.notifyDataSetChanged(); //Very important for redraw
            }
        });

        return fragmentList;
    }

    private String getCurrentQuote() {
        String currentQuote = "currentQuote";
        FragmentManager fm = getSupportFragmentManager();
        if (fm != null) {
            List<Fragment> fragments = fm.getFragments();
            for(int i = fragments.size() - 1; i >= 0; i--){
                //Kept getting the following quote...hence the "-1" below...that is for the current quote
                Fragment fragment = fragments.get(i - 1);
                if(fragment != null) {
                    // found the current fragment

                    // if you want to check for specific fragment class
                    if(fragment instanceof QuoteFragment) {
                        currentQuote = fragment.getArguments().getString("quote") + " ~ " + fragment.getArguments().getString("author");
                    }
                    break;
                }
            }
        }
        return currentQuote;
    }

}
