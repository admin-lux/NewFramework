package com.newframework.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by GaoQian on 2017/3/1.
 */
@Entity
public class UsefulWord {
    @Id
    @Unique
    private String wordId;
    @Property
    private String usefulWord;
    @Property
    private String docId;
    @Property
    private int useCount;
    @Generated(hash = 1749689957)
    public UsefulWord(String wordId, String usefulWord, String docId,
                      int useCount) {
        this.wordId = wordId;
        this.usefulWord = usefulWord;
        this.docId = docId;
        this.useCount = useCount;
    }
    @Generated(hash = 115992271)
    public UsefulWord() {
    }
    public String getWordId() {
        return this.wordId;
    }
    public void setWordId(String wordId) {
        this.wordId = wordId;
    }
    public String getUsefulWord() {
        return this.usefulWord;
    }
    public void setUsefulWord(String usefulWord) {
        this.usefulWord = usefulWord;
    }
    public String getDocId() {
        return this.docId;
    }
    public void setDocId(String docId) {
        this.docId = docId;
    }
    public int getUseCount() {
        return this.useCount;
    }
    public void setUseCount(int useCount) {
        this.useCount = useCount;
    }
    


}
