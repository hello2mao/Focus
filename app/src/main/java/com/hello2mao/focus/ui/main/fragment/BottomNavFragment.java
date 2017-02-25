package com.hello2mao.focus.ui.main.fragment;

import android.content.Context;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hello2mao.focus.R;
import com.hello2mao.focus.base.SimpleFragment;
import com.hello2mao.focus.model.bean.NoticeBean;
import com.hello2mao.focus.model.notice.NoticeManager;
import com.hello2mao.focus.ui.explore.fragment.ExploreMainFragment;
import com.hello2mao.focus.ui.main.widget.BottomNavButton;
import com.hello2mao.focus.ui.me.fragment.MeMainFragment;
import com.hello2mao.focus.ui.news.fragment.NewsMainFragment;
import com.hello2mao.focus.ui.video.fragment.VideoMainFragment;

import net.oschina.common.widget.drawable.shape.BorderShape;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class BottomNavFragment extends SimpleFragment implements View.OnClickListener,
        NoticeManager.NoticeNotify {

    @BindView(R.id.nav_item_news)
    BottomNavButton navNews;
    @BindView(R.id.nav_item_video)
    BottomNavButton navVideo;
    @BindView(R.id.nav_item_explore)
    BottomNavButton navExplore;
    @BindView(R.id.nav_item_me)
    BottomNavButton navMe;
    @BindView(R.id.nav_item_tweet_pub)
    ImageView navTweetPub;

    private Context context;
    private FragmentManager fragmentManager;
    private BottomNavButton currentButton;
    private int containerId;
    private OnNavigationReselectListener listener;

    public BottomNavFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_bottom_nav;
    }

    @Override
    protected void initEventAndData() {

        ShapeDrawable lineDrawable = new ShapeDrawable(new BorderShape(new RectF(0, 1, 0, 0)));
        lineDrawable.getPaint().setColor(getResources().getColor(R.color.list_divider_color));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{
                new ColorDrawable(getResources().getColor(R.color.white)),
                lineDrawable
        });
        root.setBackground(layerDrawable);

        navNews.initView(R.drawable.tab_icon_news, R.string.mian_tab_names_news,
                NewsMainFragment.class);
        navVideo.initView(R.drawable.tab_icon_video, R.string.mian_tab_names_video,
                VideoMainFragment.class);
        navExplore.initView(R.drawable.tab_icon_explore, R.string.mian_tab_names_explore,
                ExploreMainFragment.class);
        navMe.initView(R.drawable.tab_icon_me, R.string.mian_tab_names_me,
                MeMainFragment.class);
    }

    public void setup(Context context, FragmentManager fragmentManager, int containerId,
               OnNavigationReselectListener listener) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
        this.listener = listener;

        // do clear
        clearOldFragment();
        // 默认打开
        doSelect(navNews);
    }

    /**
     * 删除除自身之外的fragment
     */
    private void clearOldFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (transaction == null || fragments == null || fragments.size() == 0) {
            return;
        }
        boolean doCommit = false;
        for (Fragment fragment : fragments) {
            if (fragment != this) {
                transaction.remove(fragment);
                doCommit = true;
            }
        }
        if (doCommit) {
            transaction.commitNow();
        }
    }

    @OnClick({
            R.id.nav_item_news,
            R.id.nav_item_video,
            R.id.nav_item_tweet_pub,
            R.id.nav_item_explore,
            R.id.nav_item_me})
    @Override
    public void onClick(View view) {
        if (view instanceof BottomNavButton) {
            // 当图标被点击的时候
            doSelect((BottomNavButton) view);
        } else if (view.getId() == R.id.nav_item_tweet_pub) {
            // TODO:
            Toast.makeText(context, "Tweet", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 处理图标点击事件
     * @param newButton 被点击的图标
     */
    private void doSelect(BottomNavButton newButton) {
        BottomNavButton oldButton = null;
        if (currentButton != null) {
            oldButton = currentButton;
            if (oldButton == newButton) {
                // 点击相同的图标，则调用监听的动作
                onReselect(oldButton);
                return;
            }
            // 原来的设置非选中
            oldButton.setSelected(false);
        }
        // 当前点击的设置选中
        newButton.setSelected(true);
        if (oldButton == null) {
            oldButton = newButton;
        }
        // 切换到被点击图标对应的Fragment
        doTabChanged(oldButton, newButton);
        // 保存当前选中的
        currentButton = newButton;
    }

    /**
     * // 切换到被点击图标对应的Fragment
     * @param oldButton 原来图标
     * @param newButton 本次被点击图标
     */
    private void doTabChanged(BottomNavButton oldButton, BottomNavButton newButton) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if (oldButton != null) {
            if (oldButton.getFragment() != null) {
                ft.detach(oldButton.getFragment());
            }
        }
        if (newButton != null) {
            if (newButton.getFragment() == null) {
                Fragment fragment = Fragment.instantiate(context,
                        newButton.getClx().getName(), null);
                ft.add(containerId, fragment, newButton.getTag());
                newButton.setFragment(fragment);
            } else {
                ft.attach(newButton.getFragment());
            }
        }
        ft.commit();
    }

    /**
     * 点击相同的图标，则调用监听的动作
     * @param bottomNavButton 图标
     */
    private void onReselect(BottomNavButton bottomNavButton) {
        if (listener != null) {
            listener.onReselect(bottomNavButton);
        }
    }

    public interface OnNavigationReselectListener {
        void onReselect(BottomNavButton bottomNavButton);
    }

    @Override
    public void onNoticeArrived(NoticeBean bean) {
        navMe.showRedDot(bean.getAllCount());
    }
}
