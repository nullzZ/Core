package game.core.db.dto;

import game.core.cach.AbsRecord;

public class Role extends AbsRecord {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.uid
     *
     * @mbggenerated
     */
    private Long uid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.uid
     *
     * @return the value of role.uid
     *
     * @mbggenerated
     */
    public Long getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.uid
     *
     * @param uid the value for role.uid
     *
     * @mbggenerated
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }
}