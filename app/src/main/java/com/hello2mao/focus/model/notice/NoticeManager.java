package com.hello2mao.focus.model.notice;

import com.hello2mao.focus.model.bean.NoticeBean;

public final class NoticeManager {

    /**
     * 消息变化时通知接口
     */
    public interface NoticeNotify {
        void onNoticeArrived(NoticeBean bean);
    }
}
