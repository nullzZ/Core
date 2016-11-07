package game.core.cach;

/**
 * @author nullzZ
 *
 */
public class AbsRecord {

	protected Long uid;
	protected CachFlag flag;

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

}
