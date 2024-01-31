package com.example.Film.database2;

import jakarta.persistence.*;

@Entity
@Table(name = "language")
public class Language {
    @Id
    @Column(name = "language_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int languageID;

    public Language(){}

    public Language(int languageID, String languageName){
        this.languageID = languageID;
        this.languageName = languageName;
    }

    @Column(name = "name")
    private String languageName;

    public int getLanguageID() {
        return languageID;
    }

    public void setLanguageID(int languageID) {
        this.languageID = languageID;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
}
