package com.hello2mao.focus.ui.news.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.hello2mao.focus.R;
import com.hello2mao.focus.theme.ColorUi.ColorUiInterface;
import com.hello2mao.focus.util.ViewAttributeUtil;

import java.util.ArrayList;
import java.util.List;

public class ColorTrackTabViewIndicator extends HorizontalScrollView implements ColorUiInterface {

    private Context mContext;
    private int mTabTextSize;
    private int mTabSelectedTextColor;
    private Paint mPaint = new Paint();
    private int mIndicatorColor;
    private String[] mTitles;
    private int mTabCount;
    private int mTabTextColor;
    private ColorTrackTabBack icallBack;
    private List<ColorTrackView> viewList = new ArrayList<ColorTrackView>();
    private float mTranslationX;
    private int mTabWidth;
    private int mAttr_textColor = -1;
    private int mAttr_selectedTextColor = -1;
    private int mAttr_background = -1;

    public int getTabWidth() {
        return mTabWidth;
    }

    public static final int MODE_SCROLLABLE = 0;

    public static final int MODE_FIXED = 1;
    private int mMode;
    private LinearLayout mTabStrip;

    public ColorTrackTabViewIndicator(Context context, AttributeSet attrs,
                                      int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTabViewIndicator);

        mIndicatorColor = ta.getColor(R.styleable.ColorTrackTabViewIndicator_ctTabIndicatorColor, -1);

        mTabTextColor = ta.getColor(R.styleable.ColorTrackTabViewIndicator_ctTabTextColor, Color.YELLOW);
        mAttr_textColor = ViewAttributeUtil.getAttributeValue(attrs, R.attr.ctTabTextColor);

        mTabSelectedTextColor = ta.getColor(R.styleable.ColorTrackTabViewIndicator_ctTabSelectedTextColor, Color.YELLOW);
        mAttr_selectedTextColor = ViewAttributeUtil.getAttributeValue(attrs, R.attr.ctTabSelectedTextColor);

        mTabTextSize = (int) ta.getDimension(R.styleable.ColorTrackTabViewIndicator_ctTabTextSize, sp2px(getContext(), 14));

        //自定义属性的background
        mAttr_background = ViewAttributeUtil.getBackgroundAttribute(attrs);

        mTabWidth = (int) ta.getDimension(R.styleable.ColorTrackTabViewIndicator_ctTabWidth, -1);
        mMode = ta.getInt(R.styleable.ColorTrackTabViewIndicator_ctMode, MODE_FIXED);
        ta.recycle();
        if (mIndicatorColor != -1) {
            this.mPaint.setColor(mIndicatorColor);
            this.mPaint.setStrokeWidth(2.0F);
        }
        mTabStrip = new LinearLayout(context);
        mTabStrip.setOrientation(LinearLayout.HORIZONTAL);
        mTabStrip.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(mTabStrip);
    }

    public ColorTrackTabViewIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTabViewIndicator(Context context) {
        this(context, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (this.mTabCount != 0 && mTabWidth == -1) {
            this.mTabWidth = (w / this.mTabCount);
        }
    }

    public void setupViewPager(ViewPager vp) {
        if (vp == null) return;

        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int prePostiion = -1;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (mIndicatorColor != -1)
                tabScrolled(position, positionOffset);
                View tabView = mTabStrip.getChildAt(position);
                int x = (int) (tabView.getX() - viewList.get(position).getMeasuredWidth());
                x = calculateScrollXForTab(position, positionOffset);
                smoothScrollTo(x, 0);
            }

            @Override
            public void onPageSelected(int position) {
                if (prePostiion != -1) {
                    ColorTrackView colorTrackView = viewList.get(prePostiion);
                    colorTrackView.setProgress(0f);
                }
                prePostiion = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private int calculateScrollXForTab(int position, float positionOffset) {
        if (mMode == MODE_SCROLLABLE) {
            final View selectedChild = mTabStrip.getChildAt(position);
            final View nextChild = position + 1 < mTabStrip.getChildCount()
                    ? mTabStrip.getChildAt(position + 1)
                    : null;
            final int selectedWidth = selectedChild != null ? selectedChild.getWidth() : 0;
            final int nextWidth = nextChild != null ? nextChild.getWidth() : 0;
            int i = selectedChild.getLeft()
                    + ((int) ((selectedWidth + nextWidth) * positionOffset * 0.5f))
                    + (selectedChild.getWidth() / 2)
                    - (getWidth() / 2);
            mTranslationX=getWidth()/2+i-mTabWidth/2;
            return i;
        }
        return 0;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (mIndicatorColor != -1) {
            canvas.save();
            canvas.translate(mTranslationX, getHeight() - 2);
            canvas.drawLine(0.0F, 0.0F, mTabWidth, 0.0F, this.mPaint);
            canvas.restore();
        }
    }

    public void tabScrolled(int position, float positionOffset) {

        if (positionOffset == 0.0F) {
            return;
        }
        ColorTrackView currentTrackView =  this.viewList
                .get(position);
        ColorTrackView nextTrackView =  this.viewList
                .get(position + 1);
        currentTrackView.setDirection(1);
        currentTrackView.setProgress(1.0F - positionOffset);
        preColorTrackView = currentTrackView;
        nextTrackView.setDirection(0);
        nextTrackView.setProgress(positionOffset);
        invalidate();
    }

    public void tabSelected(int position) {
         this.viewList.get(position).setProgress(1.0F);
    }

    public void setTitles(String[] titles, ColorTrackTabBack callBack) {
        this.mTitles = titles;
        this.mTabCount = titles.length;
        this.icallBack = callBack;
        generateTitleView();
    }

    private ColorTrackView preColorTrackView;

    private void generateTitleView() {
        if (mTabStrip.getChildCount() > 0) {
            mTabStrip.removeAllViews();
        }
//        setWeightSum(mTitles.length);
        for (int i = 0; i < mTitles.length; i++) {
            LinearLayout tabLayout = new LinearLayout(getContext());
            tabLayout.setOrientation(LinearLayout.HORIZONTAL);
            tabLayout.setGravity(Gravity.CENTER);
            tabLayout.setTag(Integer.valueOf(i));
            tabLayout.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (preColorTrackView != null) {
                        preColorTrackView.setProgress(0F);
                    }
                    int index = (Integer) v.getTag();
                    ColorTrackView colorTrackView = (ColorTrackView) ((LinearLayout) mTabStrip.getChildAt(index))
                            .getChildAt(0);
                    colorTrackView.setProgress(1F);
                    if (icallBack != null) {
                        icallBack.onClickButton(index, colorTrackView);
                    }
                    preColorTrackView = colorTrackView;
                    calculateScrollXForTab(index,0);
                    invalidate();

                }
            });

            ColorTrackView colorTrackView = new ColorTrackView(getContext());
            LayoutParams params = null;
            if (mMode == MODE_FIXED) {
                int widthPixels = mContext.getResources().getDisplayMetrics().widthPixels;
                params = new LayoutParams(mTabWidth == -1 ? widthPixels / mTitles.length : mTabWidth, -2);
            } else {
                params = new LayoutParams(mTabWidth == -1 ? -2 : mTabWidth, -2);
            }

            colorTrackView.setLayoutParams(params);
            colorTrackView.setTag(Integer.valueOf(i));
            colorTrackView.setText(this.mTitles[i]);
            colorTrackView.setTextOriginColor(mTabTextColor);
            colorTrackView.setTextChangeColor(mTabSelectedTextColor);
            colorTrackView.setTextSize(mTabTextSize);
            if (i == 0) {
                colorTrackView.setProgress(1.0F);
                preColorTrackView = colorTrackView;
            }
            tabLayout.addView(colorTrackView);
            viewList.add(colorTrackView);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
//            layoutParams.weight = 1.0F;
            tabLayout.setLayoutParams(layoutParams);
            mTabStrip.addView(tabLayout);
        }
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setTheme(Resources.Theme themeId) {
        for (int i = 0; i < viewList.size(); i++) {
            ColorTrackView colorTrackView = viewList.get(i);
            if (mAttr_textColor != -1) {
                TypedArray ta = themeId.obtainStyledAttributes(new int[]{mAttr_textColor});
                int resourceId = ta.getColor(0, 0);
                ta.recycle();
                colorTrackView.setTextOriginColor(resourceId);
            }
            if (mAttr_selectedTextColor != -1) {
                TypedArray ta = themeId.obtainStyledAttributes(new int[]{mAttr_selectedTextColor});
                int resourceId = ta.getColor(0, 0);
                ta.recycle();
                colorTrackView.setTextChangeColor(resourceId);
            }


        }
        if (mAttr_background != -1) {
            ViewAttributeUtil.applyBackgroundDrawable(this, themeId, mAttr_background);
        }
//        ViewAttributeUtil.applyTextColor(this, themeId, mTabTextColor);
    }

    public interface ColorTrackTabBack {
        void onClickButton(Integer position, ColorTrackView colorTrackView);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public ColorTrackView getTabAt(int position) {
        return viewList.get(position);
    }
}
