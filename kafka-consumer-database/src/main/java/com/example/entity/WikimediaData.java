package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wikimedia_recent_changes")
@Getter
@Setter // Use @Getter & @Setter form lombok to auto create getter & setter methods
public class WikimediaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob // Use Lob to store large data, like this wikimedia stream
    private String wikiEventData;
}
