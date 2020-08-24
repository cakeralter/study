package cc.caker.boot.repo.po;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @author wangyl
 * @date 2019/6/20
 * @description
 */
@Data
@ToString
@Document("anime")
public class Anime implements Serializable {

    private static final long serialVersionUID = -1470407051251817022L;
    private Integer animeId;
    private String animeTitle;
    private String imageUrl;
    private String searchKeyword;
    private Boolean isOnAir;
    private Integer airDay;
    private Boolean isFavorited;
    private Boolean isRestricted;
    private Double rating;
}
