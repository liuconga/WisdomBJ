package com.liucong.wisdombj.bean;

import java.util.List;

public class NewsChannelDataBean {

    /**
     * countcommenturl : http://zhbj.qianlong.com/client/content/countComment/
     * more : /10007/list_2.json
     * news : []
     * title : 北京
     * topic : []
     * topnews : []
     */

    private String countcommenturl;
    private String more;
    private String title;
    private List<ListNews> news;
    private List<?> topic;
    private List<TopicNews> topnews;

    public String getCountcommenturl() {
        return countcommenturl;
    }

    public void setCountcommenturl(String countcommenturl) {
        this.countcommenturl = countcommenturl;
    }

    public String getMore() {
        return more;
    }

    public void setMore(String more) {
        this.more = more;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ListNews> getNews() {
        return news;
    }

    public void setNews(List<ListNews> news) {
        this.news = news;
    }

    public List<?> getTopic() {
        return topic;
    }

    public void setTopic(List<?> topic) {
        this.topic = topic;
    }

    public List<TopicNews> getTopnews() {
        return topnews;
    }

    public void setTopnews(List<TopicNews> topnews) {
        this.topnews = topnews;
    }
}
