package com.bresan.glass.examples.GestureDetectorExample;

import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;

public class GestureDetectorActivity extends Activity {
	private GestureDetector myGestureDetector;

	private TextView txtTitle;

	private int swipeDownCount;

	private static HashMap<Gesture, Integer> myGestures = new HashMap<Gesture, Integer>();
	static {
		myGestures.put(Gesture.TAP, R.string.tap);
		myGestures.put(Gesture.TWO_TAP, R.string.two_tap);
		myGestures.put(Gesture.THREE_TAP, R.string.three_tap);
		myGestures.put(Gesture.LONG_PRESS, R.string.long_press);
		myGestures.put(Gesture.TWO_LONG_PRESS, R.string.two_long_press);
		myGestures.put(Gesture.THREE_LONG_PRESS, R.string.three_long_press);
		myGestures.put(Gesture.SWIPE_LEFT, R.string.swipe_left);
		myGestures.put(Gesture.TWO_SWIPE_LEFT, R.string.two_swipe_left);
		myGestures.put(Gesture.SWIPE_RIGHT, R.string.swipe_right);
		myGestures.put(Gesture.TWO_SWIPE_RIGHT, R.string.two_swipe_right);
		myGestures.put(Gesture.SWIPE_UP, R.string.swipe_up);
		myGestures.put(Gesture.TWO_SWIPE_UP, R.string.two_swipe_up);
		myGestures.put(Gesture.SWIPE_DOWN, R.string.swipe_down);
		myGestures.put(Gesture.TWO_SWIPE_DOWN, R.string.two_swipe_down);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_main);

		txtTitle = (TextView) findViewById(R.id.title);
		txtTitle.setText("Touch on touchpad!");

		// Initialize the GestureDetector
		myGestureDetector = new GestureDetector(this);
		myGestureDetector.setBaseListener(new GestureListener());
		myGestureDetector.setFingerListener(new FingerGestureListener());
		myGestureDetector.setTwoFingerScrollListener(new TwoFingerGestureListener());
		myGestureDetector.setScrollListener(new ScrollListener());
	}

	@Override
	public boolean onGenericMotionEvent(MotionEvent event) {
		// Call the GestureDetector on any motion event
		if (null != myGestureDetector) {
			return myGestureDetector.onMotionEvent(event);
		}

		return false;
	}

	// this is our gesture listener, as the name says.
	// it captures the gestures and print them on title textView
	private class GestureListener implements GestureDetector.BaseListener {
		@Override
		public boolean onGesture(Gesture gesture) {

			txtTitle.setText(myGestures.get(gesture)); // set title with the gesture recognized

			if (Gesture.SWIPE_DOWN == gesture) {
				txtTitle.setText("Swipe down again to go back.");
				swipeDownCount++;

				return (swipeDownCount < 2);
			} else {
				swipeDownCount = 0;
			}

			return true;
		}
	}

	// Example of a listener for a finger gesture
	private class FingerGestureListener implements GestureDetector.FingerListener {
		@Override
		public void onFingerCountChanged(int i, int i2) {
		}
	}

	// Example of a listener for a two finger gesture
	private class TwoFingerGestureListener implements GestureDetector.TwoFingerScrollListener {
		@Override
		public boolean onTwoFingerScroll(float v, float v2, float v3) {
			return false;
		}
	}

	// Example of a listener for a scroll gesture
	private class ScrollListener implements GestureDetector.ScrollListener {
		@Override
		public boolean onScroll(float v, float v2, float v3) {
			return false;
		}
	}
}
