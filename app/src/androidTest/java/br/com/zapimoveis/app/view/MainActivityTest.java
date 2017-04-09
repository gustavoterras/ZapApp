package br.com.zapimoveis.app.view;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.zapimoveis.app.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.action_filter), withContentDescription("Filtro"), isDisplayed()));
        actionMenuItemView.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Preço"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.filter_content_layout),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Preço")));

        ViewInteraction textView2 = onView(
                allOf(withText("Ordenar"),
                        childAtPosition(
                                allOf(withId(R.id.filter_content_layout),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                                1)),
                                2),
                        isDisplayed()));
        textView2.check(matches(withText("Ordenar")));

        ViewInteraction textView3 = onView(
                allOf(withText("Quartos"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.filter_recycler_view),
                                        0),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("Quartos")));

        ViewInteraction textView4 = onView(
                allOf(withText("Banheiros"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.filter_recycler_view),
                                        1),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("Banheiros")));

        ViewInteraction textView5 = onView(
                allOf(withText("Garagem"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.filter_recycler_view),
                                        2),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("Garagem")));

        ViewInteraction textView6 = onView(
                allOf(withText("Área Total"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.filter_recycler_view),
                                        3),
                                0),
                        isDisplayed()));
        textView6.check(matches(withText("Área Total")));

        ViewInteraction textView7 = onView(
                allOf(withText("Área Total"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.filter_recycler_view),
                                        3),
                                0),
                        isDisplayed()));
        textView7.check(matches(withText("Área Total")));

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.filter_recycler_view),
                        withParent(withId(R.id.filter_content_layout)),
                        isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView8 = onView(
                allOf(withText("4"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView8.check(matches(withText("4")));

        ViewInteraction textView9 = onView(
                allOf(withText("4"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView9.check(matches(withText("4")));

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.action_filter), withContentDescription("Filtro"), isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.recycler_view), isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
