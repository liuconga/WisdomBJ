package com.liucong.wisdombj.bean;

public class TopicNews {

    /**
     * comment : true
     * commentlist : /10007/comment_1.json
     * commenturl : http://zhbj.qianlong.com/client/user/newComment/35301
     * id : 35301
     * pubdate : 2014-04-0814:24
     * title : 蜗居生活
     * topimage : /10007/1452327318UU91.jpg
     * type : news
     * url : /10007/724D6A55496A11726628.html
     */

    private boolean comment;
    private String commentlist;
    private String commenturl;
    private int id;
    private String pubdate;
    private String title;
    private String topimage;
    private String type;
    private String url;

    public boolean isComment() {
        return comment;
    }

    public void setComment(boolean comment) {
        this.comment = comment;
    }

    public String getCommentlist() {
        return commentlist;
    }

    public void setCommentlist(String commentlist) {
        this.commentlist = commentlist;
    }

    public String getCommenturl() {
        return commenturl;
    }

    public void setCommenturl(String commenturl) {
        this.commenturl = commenturl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTopimage() {
        return topimage;
    }

    public void setTopimage(String topimage) {
        this.topimage = topimage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
