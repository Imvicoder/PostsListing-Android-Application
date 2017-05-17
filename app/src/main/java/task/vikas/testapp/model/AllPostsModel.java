package task.vikas.testapp.model;

/**
 * Created by Descent-Vikas on 11-02-2017.
 */

public class AllPostsModel {
    private String dp;
    private String email;
    private String post;
    private String posted_at;
    private String likes;

    public AllPostsModel(String dp,String email,String post,String postedAt,String likes){
        this.setDp(dp);
        this.setEmail(email);
        this.setPost(post);
        this.setPostedAt(postedAt);
        this.setLikes(likes);
    }
    public String getDp() {
        return "http://connectindia.esy.es/task/"+dp;
    }

    public void setDp(String dp) {
        this.dp = dp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPostedAt() {
        return posted_at;
    }

    public void setPostedAt(String postedAt) {
        this.posted_at = postedAt;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }


}
