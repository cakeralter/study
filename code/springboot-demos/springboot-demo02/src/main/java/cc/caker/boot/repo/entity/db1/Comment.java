package cc.caker.boot.repo.entity.db1;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author cakeralter
 */
@ToString
@Getter
@Setter
@Entity
@Table(name = "t_comments")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1882859494521376023L;
    @Id
    @Column(name = "coid", nullable = false, unique = true)
    @GenericGenerator(name = "uid", strategy = "org.hibernate.id.IncrementGenerator")
    @GeneratedValue(generator = "uid")
    private Integer id;
    @Column(name = "cid", nullable = false)
    private Integer cid;
    @Column(name = "created", nullable = false)
    private Long created;
    @Column(name = "author")
    private String author;
    @Column(name = "author_id")
    private Integer authorId;
    @Column(name = "owner_id")
    private Integer ownerId;
    @Column(name = "mail")
    private String mail;
    @Column(name = "url")
    private String url;
    @Column(name = "ip")
    private String ip;
    @Column(name = "agent")
    private String agent;
    @Column(name = "content")
    private String content;
    @Column(name = "type")
    private String type;
    @Column(name = "status")
    private String status;
    @Column(name = "parent")
    private Integer parent;
}
