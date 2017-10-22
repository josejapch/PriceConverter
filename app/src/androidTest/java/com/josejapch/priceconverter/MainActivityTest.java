package com.josejapch.priceconverter;


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
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * @author josejapch
 * Test instrumentados sobre la clase MainActivity generados con la función "Record Espresso Test"
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.importe), withText("0"),
                        withParent(allOf(withId(R.id.datos_layout),
                                withParent(withId(R.id.importe_layout)))),
                        isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.importe), withText("0"),
                        withParent(allOf(withId(R.id.datos_layout),
                                withParent(withId(R.id.importe_layout)))),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("0160"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.importe), withText("0160"),
                        withParent(allOf(withId(R.id.datos_layout),
                                withParent(withId(R.id.importe_layout)))),
                        isDisplayed()));
        appCompatEditText3.perform(pressImeActionButton());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.iva_list),
                        withParent(allOf(withId(R.id.datos_layout),
                                withParent(withId(R.id.importe_layout)))),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.text1), withText("IVA General (21%)"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.iva_list),
                        withParent(allOf(withId(R.id.datos_layout),
                                withParent(withId(R.id.importe_layout)))),
                        isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("IVA Reducido (10%)"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatSpinner3 = onView(
                allOf(withId(R.id.iva_list),
                        withParent(allOf(withId(R.id.datos_layout),
                                withParent(withId(R.id.importe_layout)))),
                        isDisplayed()));
        appCompatSpinner3.perform(click());

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(android.R.id.text1), withText("IVA superreducido (4%)"), isDisplayed()));
        appCompatTextView3.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.descuento), withText("0"),
                        withParent(allOf(withId(R.id.datosDes_layout),
                                withParent(withId(R.id.decuento_layout)))),
                        isDisplayed()));
        appCompatEditText4.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.descuento), withText("0"),
                        withParent(allOf(withId(R.id.datosDes_layout),
                                withParent(withId(R.id.decuento_layout)))),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("010"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.origen), withText("0"),
                        withParent(allOf(withId(R.id.datosCambio_layout),
                                withParent(withId(R.id.change_layout)))),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("01"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner4 = onView(
                allOf(withId(R.id.change_list),
                        withParent(allOf(withId(R.id.datosCambio_layout),
                                withParent(withId(R.id.change_layout)))),
                        isDisplayed()));
        appCompatSpinner4.perform(click());

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(android.R.id.text1), withText("Euro"), isDisplayed()));
        appCompatTextView4.perform(click());

        ViewInteraction appCompatSpinner5 = onView(
                allOf(withId(R.id.change_list),
                        withParent(allOf(withId(R.id.datosCambio_layout),
                                withParent(withId(R.id.change_layout)))),
                        isDisplayed()));
        appCompatSpinner5.perform(click());

        ViewInteraction appCompatTextView5 = onView(
                allOf(withId(android.R.id.text1), withText("Libra"), isDisplayed()));
        appCompatTextView5.perform(click());

        ViewInteraction appCompatSpinner6 = onView(
                allOf(withId(R.id.change_list),
                        withParent(allOf(withId(R.id.datosCambio_layout),
                                withParent(withId(R.id.change_layout)))),
                        isDisplayed()));
        appCompatSpinner6.perform(click());

        ViewInteraction appCompatTextView6 = onView(
                allOf(withId(android.R.id.text1), withText("Dolar"), isDisplayed()));
        appCompatTextView6.perform(click());

        ViewInteraction appCompatSpinner7 = onView(
                allOf(withId(R.id.change_list),
                        withParent(allOf(withId(R.id.datosCambio_layout),
                                withParent(withId(R.id.change_layout)))),
                        isDisplayed()));
        appCompatSpinner7.perform(click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.origen), withText("01"),
                        withParent(allOf(withId(R.id.datosCambio_layout),
                                withParent(withId(R.id.change_layout)))),
                        isDisplayed()));
        appCompatEditText7.perform(pressImeActionButton());

        ViewInteraction linearLayout = onView(
                allOf(withId(R.id.importe_layout),
                        childAtPosition(
                                allOf(withId(R.id.main_layout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                0),
                        isDisplayed()));
        linearLayout.check(matches(isDisplayed()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.textview_importe), withText("Importe"),
                        childAtPosition(
                                allOf(withId(R.id.importe_layout),
                                        childAtPosition(
                                                withId(R.id.main_layout),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Importe")));

        ViewInteraction editText = onView(
                allOf(withId(R.id.importe), withText("0160"),
                        childAtPosition(
                                allOf(withId(R.id.datos_layout),
                                        childAtPosition(
                                                withId(R.id.importe_layout),
                                                1)),
                                0),
                        isDisplayed()));
        editText.check(matches(withText("0160")));

        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.text1), withText("IVA superreducido (4%)"),
                        childAtPosition(
                                allOf(withId(R.id.iva_list),
                                        childAtPosition(
                                                withId(R.id.datos_layout),
                                                1)),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("IVA superreducido (4%)")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.p_noiva), withText("Importe sin IVA:153.85 €"),
                        childAtPosition(
                                allOf(withId(R.id.importe_layout),
                                        childAtPosition(
                                                withId(R.id.main_layout),
                                                0)),
                                2),
                        isDisplayed()));
        textView3.check(matches(withText("Importe sin IVA:153.85 €")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.textView_Descuento), withText("Descuento (%)"),
                        childAtPosition(
                                allOf(withId(R.id.decuento_layout),
                                        childAtPosition(
                                                withId(R.id.main_layout),
                                                1)),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("Descuento (%)")));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.descuento), withText("010"),
                        childAtPosition(
                                allOf(withId(R.id.datosDes_layout),
                                        childAtPosition(
                                                withId(R.id.decuento_layout),
                                                1)),
                                0),
                        isDisplayed()));
        editText2.check(matches(withText("010")));

        ViewInteraction textView5 = onView(
                allOf(withId(R.id.p_descuento), withText("144.0 €"),
                        childAtPosition(
                                allOf(withId(R.id.datosDes_layout),
                                        childAtPosition(
                                                withId(R.id.decuento_layout),
                                                1)),
                                1),
                        isDisplayed()));
        textView5.check(matches(withText("144.0 €")));

        ViewInteraction linearLayout2 = onView(
                allOf(withId(R.id.decuento_layout),
                        childAtPosition(
                                allOf(withId(R.id.main_layout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        linearLayout2.check(matches(isDisplayed()));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.textview_change), withText("Cambio de moneda"),
                        childAtPosition(
                                allOf(withId(R.id.change_layout),
                                        childAtPosition(
                                                withId(R.id.main_layout),
                                                2)),
                                0),
                        isDisplayed()));
        textView6.check(matches(withText("Cambio de moneda")));

        ViewInteraction linearLayout3 = onView(
                allOf(withId(R.id.change_layout),
                        childAtPosition(
                                allOf(withId(R.id.main_layout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                2),
                        isDisplayed()));
        linearLayout3.check(matches(isDisplayed()));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.origen), withText("01"),
                        childAtPosition(
                                allOf(withId(R.id.datosCambio_layout),
                                        childAtPosition(
                                                withId(R.id.change_layout),
                                                1)),
                                0),
                        isDisplayed()));
        editText3.check(matches(withText("01")));

        ViewInteraction textView7 = onView(
                allOf(withId(android.R.id.text1), withText("Dolar"),
                        childAtPosition(
                                allOf(withId(R.id.change_list),
                                        childAtPosition(
                                                withId(R.id.datosCambio_layout),
                                                1)),
                                0),
                        isDisplayed()));
        textView7.check(matches(withText("Dolar")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.libra), withText("0.76 £"),
                        childAtPosition(
                                allOf(withId(R.id.chResult_layout),
                                        childAtPosition(
                                                withId(R.id.change_layout),
                                                2)),
                                0),
                        isDisplayed()));
        textView8.check(matches(withText("0.76 £")));

        ViewInteraction textView9 = onView(
                allOf(withId(R.id.euro), withText("0.85 €"),
                        childAtPosition(
                                allOf(withId(R.id.chResult_layout),
                                        childAtPosition(
                                                withId(R.id.change_layout),
                                                2)),
                                1),
                        isDisplayed()));
        textView9.check(matches(withText("0.85 €")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.dolar), withText("1.0 $"),
                        childAtPosition(
                                allOf(withId(R.id.chResult_layout),
                                        childAtPosition(
                                                withId(R.id.change_layout),
                                                2)),
                                2),
                        isDisplayed()));
        textView10.check(matches(withText("1.0 $")));

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
