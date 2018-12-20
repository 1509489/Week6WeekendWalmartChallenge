package com.pixelart.week6weekendwalmartchallenge;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.matcher.BoundedMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestUtil {

    static String getStringFromFile(Context context, String filepath)
            throws IOException {
        InputStream inputStream = context.getResources().getAssets()
                .open(filepath);
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(inputStream));
        final StringBuilder stringBuilder = new StringBuilder();

        String line;

        while((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        bufferedReader.close();
        inputStream.close();

        return stringBuilder.toString();
    }

    public static Matcher<View> atPosition(final int position, final Matcher<View> matcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {

            @Override
            public void describeTo(Description description) {
                description.appendText("Has Item at Position: " + position +" ");
                matcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(RecyclerView recyclerView) {
                RecyclerView.ViewHolder viewHolder = recyclerView
                        .findViewHolderForAdapterPosition(position);
                return viewHolder != null && matcher.matches(viewHolder.itemView);
            }
        };
    }


    public static class RecyclerViewItemCountAssertion implements ViewAssertion {

        private final int itemCount;

        public RecyclerViewItemCountAssertion(int itemCount) {
            this.itemCount = itemCount;
        }


        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) {
                throw noViewFoundException;
            }

            final RecyclerView.Adapter adapter = ((RecyclerView) view)
                    .getAdapter();

            Assert.assertThat(adapter.getItemCount(),
                    org.hamcrest.Matchers.is(itemCount));
        }
    }
}
