package game.core.rpg.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import game.core.model.AbsRole;
import game.core.rpg.manager.Cooldown;
import game.core.rpg.map.Position;

public abstract class Person extends AbsRole {

	private long uid;
	// 模板Id
	protected int modelId;
	// 创建时间
	protected long createTime;
	// 所在线服务器
	protected int serverLine;
	// 地图
	protected int mapId;
	// 坐标
	protected Position position;
	// 方向
	protected byte direction;
	// 移动路径
	protected List<Position> roads = new ArrayList<Position>();
	// 移动耗时时间
	protected int cost = 0;
	// 移动上一步时间
	protected long prevStep = 0;
	// 速度
	protected int speed;

	// 冷却列表
	protected HashMap<Integer, Cooldown> cooldowns = new HashMap<>();

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getServerLine() {
		return serverLine;
	}

	public void setServerLine(int serverLine) {
		this.serverLine = serverLine;
	}

	public int getMapId() {
		return mapId;
	}

	public void setMapId(int mapId) {
		this.mapId = mapId;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public byte getDirection() {
		return direction;
	}

	public void setDirection(byte direction) {
		this.direction = direction;
	}

	public List<Position> getRoads() {
		return roads;
	}

	public void setRoads(List<Position> roads) {
		this.roads = roads;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public long getPrevStep() {
		return prevStep;
	}

	public void setPrevStep(long prevStep) {
		this.prevStep = prevStep;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public HashMap<Integer, Cooldown> getCooldowns() {
		return cooldowns;
	}

}
