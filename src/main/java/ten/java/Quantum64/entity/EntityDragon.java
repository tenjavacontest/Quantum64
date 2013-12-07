package ten.java.Quantum64.entity;

import java.util.HashMap;
import java.util.HashSet;
import net.minecraft.server.v1_7_R1.DamageSource;
import net.minecraft.server.v1_7_R1.EntityComplexPart;
import net.minecraft.server.v1_7_R1.EntityEnderDragon;
import net.minecraft.server.v1_7_R1.MathHelper;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_4_5.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import ten.java.Quantum64.Main;

public class EntityDragon extends EntityEnderDragon {

	final String player;

	private boolean onGround = false;
	private final short[] counter = new short[3];
	private boolean directionChanged = false;

	Main plugin;
	byte upDown = 0;
	boolean brI = true;
	boolean ignoreSneaking = false;
	double fl = 0.0D;

	public EntityDragon(Main plugin, Player player, net.minecraft.server.v1_7_R1.World world) {
		super(world);
		this.plugin = plugin;
		this.player = player.getName();
	}

	/**
	 * This is some CB code needed to make the entity
	 */
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
			d();
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
			if (!this.brI)
				this.brI = true;
			else
				this.brI = false;
			this.ignoreSneaking = true;
		}

		if (this.brI) {
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
		d();
	}

	private void d() {
		this.aN = this.yaw;
		this.bq.width = this.bq.length = 3.0F;
		this.bs.width = this.bs.length = 2.0F;
		this.bt.width = this.bt.length = 2.0F;
		this.bu.width = this.bu.length = 2.0F;
		this.br.length = 3.0F;
		this.br.width = 5.0F;
		this.bv.length = 2.0F;
		this.bv.width = 4.0F;
		this.bw.length = 3.0F;
		this.bw.width = 4.0F;
		float f1 = (float) (this.b(5, 1.0F)[1] - this.b(10, 1.0F)[1]) * 10.0F / 180.0F * 3.1415927F;
		float f2 = MathHelper.cos(f1);
		float f9 = -MathHelper.sin(f1);
		float f10 = this.yaw * 3.1415927F / 180.0F;
		float f11 = MathHelper.sin(f10);
		float f12 = MathHelper.cos(f10);

		this.br.h();
		this.br.setPositionRotation(this.locX + (double) (f11 * 0.5F), this.locY, this.locZ - (double) (f12 * 0.5F), 0.0F, 0.0F);
		this.bv.h();
		this.bv.setPositionRotation(this.locX + (double) (f12 * 4.5F), this.locY + 2.0D, this.locZ + (double) (f11 * 4.5F), 0.0F, 0.0F);
		this.bw.h();
		this.bw.setPositionRotation(this.locX - (double) (f12 * 4.5F), this.locY + 2.0D, this.locZ - (double) (f11 * 4.5F), 0.0F, 0.0F);

		double[] adouble = this.b(5, 1.0F);
		double[] adouble1 = this.b(0, 1.0F);

		float f3 = MathHelper.sin(this.yaw * 3.1415927F / 180.0F - this.bg * 0.01F);
		float f13 = MathHelper.cos(this.yaw * 3.1415927F / 180.0F - this.bg * 0.01F);

		this.bq.h();
		this.bq.setPositionRotation(this.locX + (double) (f3 * 5.5F * f2), this.locY + (adouble1[1] - adouble[1]) * 1.0D + (double) (f9 * 5.5F), this.locZ - (double) (f13 * 5.5F * f2), 0.0F, 0.0F);

		for (int j = 0; j < 3; ++j) {
			EntityComplexPart entitycomplexpart = null;

			if (j == 0) {
				entitycomplexpart = this.bs;
			}

			if (j == 1) {
				entitycomplexpart = this.bt;
			}

			if (j == 2) {
				entitycomplexpart = this.bu;
			}

			double[] adouble2 = this.b(12 + j * 2, 1.0F);
			float f14 = this.yaw * 3.1415927F / 180.0F + (float) MathHelper.g(adouble2[0] - adouble[0]) * 3.1415927F / 180.0F * 1.0F;
			float f15 = MathHelper.sin(f14);
			float f16 = MathHelper.cos(f14);
			float f17 = 1.5F;
			float f18 = (float) (j + 1) * 2.0F;

			entitycomplexpart.h();
			entitycomplexpart.setPositionRotation(this.locX - (double) ((f11 * f17 + f15 * f18) * f2), this.locY + (adouble2[1] - adouble[1]) * 1.0D - (double) ((f18 + f17) * f9) + 1.5D, this.locZ + (double) ((f12 * f17 + f16 * f18) * f2), 0.0F, 0.0F);
		}

	}

}
