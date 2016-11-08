package game.core.cach;

/**
 * @author nullzZ
 *
 */
public class AbsRecord {

	protected Long uid;
	private CachFlag flag;
	private String daoName;

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public CachFlag getFlag() {
		return flag;
	}

	public void setFlag(CachFlag flag) {
		this.flag = flag;
	}

	public String getDaoName() {
		return daoName;
	}

	public void setDaoName(String daoName) {
		this.daoName = daoName;
	}

}
