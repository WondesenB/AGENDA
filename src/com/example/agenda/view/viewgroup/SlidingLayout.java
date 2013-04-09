package com.example.agenda.view.viewgroup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class SlidingLayout extends LinearLayout {

	// reference to groups contained in this view
	private View menu;
	private View content;

	// constants
	protected static final int menumargin = 115;

	public enum MenuState {
		CLOSED, Open, Opening, Closing
	};

	// position information attributes
	protected int currentContentOffset = 0;
	protected MenuState menuCurrentState = MenuState.CLOSED;

	// Animation object
	protected Scroller menuAnimationScroller = new Scroller(this.getContext(),
			new smootInterpolator());

	protected Runnable menuAnimationRunnable = new AnimationRunnable();
	protected Handler menuAnimationHandler = new Handler();

	// Animation constants
	private static final int menuAnimationDuration = 1000;
	private static final int menuAnimationPollingInterval = 16;

	@SuppressLint("NewApi")
	public SlidingLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public SlidingLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public SlidingLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		this.menu = this.getChildAt(0);
		this.content = this.getChildAt(1);
		this.menu.setVisibility(View.GONE);

	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		if (changed)
			this.calculateChildDimension();
		this.menu.layout(left, top, right - menumargin, bottom);
		this.content.layout(left + this.currentContentOffset, top, right
				+ this.currentContentOffset, bottom);
	}

	public void toggleMenu() {
		
		switch (this.menuCurrentState) {
		case CLOSED:
			this.menuCurrentState = MenuState.Opening;
			this.menu.setVisibility(View.VISIBLE);
			this.menuAnimationScroller.startScroll(0, 0, this.getMenuWidth(),
					0, menuAnimationDuration);
			/*
			 * this.currentContentOffset = this.getMenuWidth();
			 * this.content.offsetLeftAndRight(currentContentOffset);
			 */

			break;
		case Open:
			this.menuCurrentState = MenuState.Closing;
			this.menuAnimationScroller.startScroll(this.currentContentOffset,
					0, -this.currentContentOffset, 0, menuAnimationDuration);
			/*
			 * this.content.offsetLeftAndRight(-currentContentOffset);
			 * this.currentContentOffset = 0;
			 * 
			 * this.menu.setVisibility(View.GONE);
			 */
			break;
		default:
			return;
		}
		// this.invalidate();
		this.menuAnimationHandler.postDelayed(this.menuAnimationRunnable,
				menuAnimationPollingInterval);
	}

	private int getMenuWidth() {
		return this.menu.getLayoutParams().width;
	}

	private void calculateChildDimension() {
		this.content.getLayoutParams().height = this.getHeight();
		this.content.getLayoutParams().width = this.getWidth();

		this.menu.getLayoutParams().width = this.getWidth() - menumargin;
		this.menu.getLayoutParams().height = this.getHeight();
	}

	public void adjustContentPosition(boolean isAnimationOngoing) {
		// TODO Auto-generated method stub
		int scrolleroffset = this.menuAnimationScroller.getCurrX();
		this.content.offsetLeftAndRight(scrolleroffset
				- this.currentContentOffset);
		this.currentContentOffset = scrolleroffset;
		this.invalidate();
		if (isAnimationOngoing) {
			this.menuAnimationHandler.postDelayed(this.menuAnimationRunnable,
					menuAnimationPollingInterval);

		} else
			this.onMenuTransitionComplete();
	}

	private void onMenuTransitionComplete() {
		// TODO Auto-generated method stub
		switch (this.menuCurrentState) {
		case Opening:
			this.menuCurrentState = MenuState.Open;
			break;
		case Closing:
			this.menuCurrentState = MenuState.CLOSED;
			this.menu.setVisibility(View.VISIBLE);
			break;
		default:
			return;
		}
	}

	protected class smootInterpolator implements Interpolator {

		@Override
		public float getInterpolation(float t) {
			// TODO Auto-generated method stub
			return (float) Math.pow(t - 1, 5) + 1;
		}

	}

	protected class AnimationRunnable implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			boolean isAnimationOngoing = SlidingLayout.this.menuAnimationScroller
					.computeScrollOffset();
			SlidingLayout.this.adjustContentPosition(isAnimationOngoing);
		}

	}

}
