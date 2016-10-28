package game.core.net.my.model;

import game.core.net.action.IAction;

/**
 * 
 * @author nullzZ
 *
 */
public class MsgModel {
	private Object u;
	private int cmd;
	@SuppressWarnings("rawtypes")
	private IAction action;
	private Object msg;

	@SuppressWarnings("rawtypes")
	public MsgModel(Object u, int cmd, IAction action, Object msg) {
		this.u = u;
		this.cmd = cmd;
		this.action = action;
		this.msg = msg;
	}

	public Object getU() {
		return u;
	}

	public void setU(Object u) {
		this.u = u;
	}

	@SuppressWarnings("rawtypes")
	public IAction getAction() {
		return action;
	}

	@SuppressWarnings("rawtypes")
	public void setAction(IAction action) {
		this.action = action;
	}

	public Object getMsg() {
		return msg;
	}

	public void setMsg(Object msg) {
		this.msg = msg;
	}

	public int getCmd() {
		return cmd;
	}

	public void setCmd(int cmd) {
		this.cmd = cmd;
	}

}
