package zw.co.afrocodemy.afrocodemyclassrooms.assessment;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MultipleChoiceAssessmentQuestion extends AssessmentQuestion{
    @OneToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected Set<PossibleAnswer> possibleAnswers;
    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected PossibleAnswer answer;
}
