package beans;

/**
 *
 * @author jhd29_000
 */
public class NewPosting {
    
    private String subject, comment;
    
    public String execute(){
        return "success";
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
