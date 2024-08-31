package com.example.kanban.enitity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class UnderTask extends Task {
    @JoinColumn(name = "epic_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Epic epic;


}

