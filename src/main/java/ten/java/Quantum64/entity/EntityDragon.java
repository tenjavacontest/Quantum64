package ten.java.Quantum64.entity;

import net.minecraft.server.v1_7_R1.EntityEnderDragon;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import ten.java.Quantum64.Main;

public class EntityDragon extends EntityEnderDragon {

	final String player;

	private boolean onGround = false;
	private final short[] counter = new short[3];
	private boolean directionChanged = false;

	Main plugin;
	byte upDown = 0;
	boolean br = true;
	boolean ignoreSneaking = false;
	double fl = 0.0D;

	public EntityDragon(Main plugin, Player player, net.minecraft.server.v1_7_R1.World world) {
		super(world);
		this.plugin = plugin;
		this.player = player.getName();
	}

	public void c() {

		if (this.passenger == null) {
			if (!this.onGround) {
				double myY = this.locY - this.plugin.dragonSpeed;
				int imyY = (int) myY;
				if (imyY < 1)
					this.onGround = true;
				if ((imyY < 1) || ((imyY < this.world.getWorld().getMaxHeight()) && (this.world.getWorld().getBlockAt((int) this.locX, imyY, (int) this.locZ).getType() != Material.AIR)))
					this.onGround = true;
				else
					setPosition(this.locX, myY, this.locZ);
			}
			// I think I need to do something here for the dragon's rotation?();
			return;
		}
		this.onGround = false;

		Player p = this.plugin.getServer().getPlayerExact(this.player);
		if (p == null) {
			return;
		}

		if (this.ignoreSneaking) {
			int tmp202_201 = 1;
			short[] tmp202_198 = this.counter;
			if ((tmp202_198[tmp202_201] = (short) (tmp202_198[tmp202_201] + 1)) > 10) {
				this.counter[1] = 0;
				this.ignoreSneaking = false;
			}
		} else if (p.isSneaking()) {
			if (!this.br)
				this.br = true;
			else
				this.br = false;
			this.ignoreSneaking = true;
		}

		if (this.br) {
			return;
		}
		if (this.fl > 0.0D) {
			if (this.counter[2] > 600) {
				this.fl -= 0.1D;
				this.counter[2] = 0;
			} else {
				int tmp319_318 = 2;
				short[] tmp319_315 = this.counter;
				tmp319_315[tmp319_318] = ((short) (tmp319_315[tmp319_318] + 1));
			}
		} else
			this.counter[2] = 0;

		double myX = this.locX;
		double myY = this.locY;
		double myZ = this.locZ;

		if (!this.directionChanged) {
			Location loc = p.getEyeLocation();
			double oldYaw = this.yaw;
			this.yaw = (loc.getYaw() + 180.0F);
			while (this.yaw > 360.0F)
				this.yaw -= 360.0F;
			while (this.yaw < 0.0F) {
				this.yaw += 360.0F;
			}
			if ((this.yaw < 22.5F) || (this.yaw > 337.5D))
				this.yaw = 0.0F;
			else if (this.yaw < 67.5F)
				this.yaw = 45.0F;
			else if (this.yaw < 112.5F)
				this.yaw = 90.0F;
			else if (this.yaw < 157.5F)
				this.yaw = 135.0F;
			else if (this.yaw < 202.5F)
				this.yaw = 180.0F;
			else if (this.yaw < 247.5F)
				this.yaw = 225.0F;
			else if (this.yaw < 292.5F)
				this.yaw = 270.0F;
			else {
				this.yaw = 315.0F;
			}
			if (loc.getPitch() < -45.0F)
				this.upDown = 2;
			else if (loc.getPitch() > 45.0F)
				this.upDown = 1;
			else {
				this.upDown = 0;
			}
			if ((oldYaw != this.yaw) || (myY != this.locY)) {
				this.directionChanged = true;
			}

			if (this.directionChanged) {
				if (this.counter[0] < 60) {
					int tmp678_677 = 0;
					short[] tmp678_674 = this.counter;
					tmp678_674[tmp678_677] = ((short) (tmp678_674[tmp678_677] + 1));
				} else {
					this.counter[0] = 0;
					this.directionChanged = false;
				}
			}
		}

		double dragonSpeed = this.plugin.dragonSpeed + this.fl;
		if (this.upDown == 1)
			myY -= dragonSpeed;
		else if (this.upDown == 2) {
			myY += dragonSpeed;
		}
		if ((this.yaw < 22.5F) || (this.yaw > 337.5F)) {
			myZ -= dragonSpeed;
		} else if (this.yaw < 67.5F) {
			double halfSpeed = dragonSpeed / 2.0D;
			myZ -= halfSpeed;
			myX += halfSpeed;
		} else if (this.yaw < 112.5F) {
			myX += dragonSpeed;
		} else if (this.yaw < 157.5F) {
			double halfSpeed = dragonSpeed / 2.0D;
			myX += halfSpeed;
			myZ += halfSpeed;
		} else if (this.yaw < 202.5F) {
			myZ += dragonSpeed;
		} else if (this.yaw < 247.5F) {
			double halfSpeed = dragonSpeed / 2.0D;
			myZ += halfSpeed;
			myX -= halfSpeed;
		} else if (this.yaw < 292.5D) {
			myX -= dragonSpeed;
		} else {
			double halfSpeed = dragonSpeed / 2.0D;
			myX -= halfSpeed;
			myZ -= halfSpeed;
		}

		setPosition(myX, myY, myZ);
		// I need to do something else here();
	}
}
