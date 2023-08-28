package com.samir.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;
import java.util.Map;

@Entity
@Table(name = "coding_questions")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CodingQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "realm", length = 100, nullable = false)
    private String realm;

    @NotNull
    @Column(name = "from_target_exp", nullable = false)
    private Long fromTargetExp;

    @NotNull
    @Column(name = "to_target_exp", nullable = false)
    private Long toTargetExp;

    @NotNull
    @Column(name = "difficulty", nullable = false)
    private Long difficulty;

    @NotNull
    @Column(name = "question", length = 2000, nullable = false)
    private String question;


    @Column(name = "code_snippet", length = 2000)
    private String codeSnippet;

    @ElementCollection
    @CollectionTable(name = "options", joinColumns = @JoinColumn(name = "option_id"))
    @MapKeyColumn(name = "option_name") // Column name for option names
    @Column(name = "option_value") // Column name for option values
    private Map<String, String> options;

    @NotNull
    @Column(name = "answer", length = 1000, nullable = false)
    private String answer;
}
