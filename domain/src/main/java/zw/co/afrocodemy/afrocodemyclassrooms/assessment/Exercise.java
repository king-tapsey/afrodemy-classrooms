package zw.co.afrocodemy.afrocodemyclassrooms.assessment;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import zw.co.afrocodemy.afrocodemyclassrooms.topic.Topic;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Exercise extends Assessment{
    @ManyToOne
    @JoinColumn(name = "topic_id")
    protected Topic topic;
}
