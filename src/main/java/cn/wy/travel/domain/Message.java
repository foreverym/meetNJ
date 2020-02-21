package cn.wy.travel.domain;

public class Message {

    private int mid;
    private int rid;
    private int uid;
    private String message;
    private String mimage;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMimage() {
        return mimage;
    }

    public void setMimage(String mimage) {
        this.mimage = mimage;
    }

    public Message(int mid, int rid, int uid, String message, String mimage) {
        this.mid = mid;
        this.rid = rid;
        this.uid = uid;
        this.message = message;
        this.mimage = mimage;
    }

    @Override
    public String toString() {
        return "Message{" +
                "mid=" + mid +
                ", rid=" + rid +
                ", uid=" + uid +
                ", message='" + message + '\'' +
                ", mimage='" + mimage + '\'' +
                '}';
    }
}
