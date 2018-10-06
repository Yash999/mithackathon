package project.yashwanth.hackathonapp;

public class locationTemplate {
    String aid;
    String atime;
    String addr;
    String alevel;
    String anov;
    public locationTemplate(String info[]){
        this.aid=info[0];
        this.atime=info[1];
        this.addr=info[2];
        this.alevel=info[3];
        this.anov=info[4];
    }
}
