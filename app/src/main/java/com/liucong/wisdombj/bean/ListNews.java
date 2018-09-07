package com.liucong.wisdombj.bean;

public class ListNews {

    /**
     * comment : true
     * commentlist : /10007/comment_1.json
     * commenturl : http://zhbj.qianlong.com/client/user/newComment/35319
     * id : 35311
     * listimage : /10007/2078369924F9UO.jpg
     * pubdate : 2014-10-1113:18
     * title : 网上大讲堂第368期预告：义务环保人人有责
     * type : news
     * url : /10007/724D6A55496A11726628.html
     */

    private boolean comment;
    private String commentlist;
    private String commenturl;
    private int id;
    private String listimage;
    private String pubdate;
    private String title;
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

    public String getListimage() {
        return listimage;
    }

    public void setListimage(String listimage) {
        this.listimage = listimage;
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

    @Override
    public String toString() {
        return "ListNews{" +
                "comment=" + comment +
                ", commentlist='" + commentlist + '\'' +
                ", commenturl='" + commenturl + '\'' +
                ", id=" + id +
                ", listimage='" + listimage + '\'' +
                ", pubdate='" + pubdate + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
