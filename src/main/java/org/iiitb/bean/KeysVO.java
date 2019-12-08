package org.iiitb.bean;


import javax.persistence.*;

@Entity
public class KeysVO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(unique = true)
    private Integer myKey;

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }

    private Integer used;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMyKey() {
        return myKey;
    }

    public void setMyKey(Integer myKey) {
        this.myKey = myKey;
    }


}
