package kr.dj.hnu.multi.ccal;

/**
 * 이벤트 객체
 * title    =>  이벤트 제목
 * content  =>  이벤트 내용
 * date     =>  이벤트 날짜
 * month    =>  이벤트 월
 * year     =>  이벤트 년도
 */

public class Event {
    private String title;
    private String content;
    private String date;
    private String month;
    private String year;

    public Event(String title, String content, String date, String month, String year) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
