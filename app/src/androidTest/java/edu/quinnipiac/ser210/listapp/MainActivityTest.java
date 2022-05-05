package edu.quinnipiac.ser210.listapp;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isClosed;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.Gravity;

import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void onNavigationItemSelected() {
    onView(withId(R.id.newbutton)).perform(click());
    }
    @Test
    public void onSupportNavigateUp() {

        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT))) // Left Drawer should be closed.
                .perform(DrawerActions.open()); // Open Drawer

        onView(withId(R.id.backgroundblue)).perform(click());
    }
    
}