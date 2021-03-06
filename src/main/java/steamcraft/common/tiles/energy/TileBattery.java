
package steamcraft.common.tiles.energy;

import boilerplate.api.IEnergyItem;
import boilerplate.api.IOpenableGUI;
import boilerplate.common.baseclasses.BaseTileWithInventory;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.client.gui.GuiBattery;
import steamcraft.common.tiles.container.ContainerBattery;

/**
 * @author decebaldecebal
 *
 */
public class TileBattery extends BaseTileWithInventory implements IEnergyHandler, IOpenableGUI
{
	private static int initialEnergy = 50000;
	private static short initialTransferRate = 40;

	private byte ticksSinceUpdate = 0;

	public int totalEnergy = 0;
	public int maxEnergy = 0;
	public short transferRate = initialTransferRate;

	public EnergyStorage buffer = new EnergyStorage(initialEnergy, initialTransferRate);

	public TileBattery()
	{
		super(4);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.buffer.readFromNBT(tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		this.buffer.writeToNBT(tag);
	}

	@SideOnly(Side.CLIENT)
	public int getEnergyScaled(int par1)
	{
		return (this.totalEnergy + this.buffer.getEnergyStored()) / ((this.maxEnergy + initialEnergy) / 1000) / par1;
	}

	@Override
	public void updateEntity()
	{
		if (!this.worldObj.isRemote)
		{
			this.ticksSinceUpdate++;

			if (this.ticksSinceUpdate > 50)
			{
				this.ticksSinceUpdate = 0;
				this.updateEnergyFromInv();
			}

			if (this.buffer.getEnergyStored() > 0)
				this.chargeItems();

			/*
			 * short outputEnergy = (short)
			 * this.extractEnergy(ForgeDirection.UNKNOWN, this.transferRate,
			 * true);
			 *
			 * if(outputEnergy > 0) for(ForgeDirection direction :
			 * EnumSet.allOf(ForgeDirection.class)) if(outputEnergy > 0) {
			 * TileEntity tileEntity = this.worldObj.getTileEntity(this.xCoord -
			 * direction.offsetX, this.yCoord - direction.offsetY, this.zCoord -
			 * direction.offsetZ);
			 *
			 * if(tileEntity instanceof IEnergyReceiver) outputEnergy -=
			 * this.extractEnergy(ForgeDirection.UNKNOWN, ((IEnergyReceiver)
			 * tileEntity).receiveEnergy(direction.getOpposite(), outputEnergy,
			 * false), false); } else break;
			 */
		}
	}

	private void chargeItems()
	{
		for (ItemStack stack : this.inventory)
			if (stack != null)
			{
				IEnergyItem item = (IEnergyItem) stack.getItem();

				this.buffer.modifyEnergyStored(-item.receiveEnergy(stack, this.buffer.getEnergyStored(), false));

				if (this.buffer.getEnergyStored() <= 0)
					break;
			}
	}

	public void updateEnergyFromInv()
	{
		this.maxEnergy = 0;
		this.totalEnergy = 0;
		this.transferRate = initialTransferRate;

		for (ItemStack stack : this.inventory)
			if (stack != null)
			{
				IEnergyItem item = (IEnergyItem) stack.getItem();

				this.maxEnergy += item.getMaxEnergyStored(stack);
				this.totalEnergy += item.getEnergyStored(stack);
				this.transferRate += item.getMaxSend();
			}

		this.buffer.setMaxTransfer(this.transferRate);
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return this.buffer.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		int usedEnergy = maxExtract;
		maxExtract -= this.buffer.extractEnergy(maxExtract, simulate);

		if (maxExtract != 0)
			for (ItemStack stack : this.inventory)
				if (stack != null)
				{
					IEnergyItem item = (IEnergyItem) stack.getItem();

					if (maxExtract > 0)
						maxExtract -= item.extractEnergy(stack, maxExtract, simulate);
					else
						break;
				}

		usedEnergy -= maxExtract;

		return usedEnergy;
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return this.buffer.getEnergyStored() + this.totalEnergy;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return this.buffer.getMaxEnergyStored() + this.maxEnergy;
	}

	@Override
	public String getInventoryName()
	{
		return "Battery Bank";
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiBattery(player.inventory, (TileBattery) world.getTileEntity(x, y, z));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerBattery(player.inventory, (TileBattery) world.getTileEntity(x, y, z));
	}
}
