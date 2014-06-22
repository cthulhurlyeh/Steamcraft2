/**
 * This class was created by <MrArcane111> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 1, 2014, 12:54:18 PM]
 */
package steamcraft.common.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import steamcraft.common.blocks.machine.BlockSteamBoiler;
import steamcraft.common.items.ItemCanister;

// TODO: Auto-generated Javadoc
/**
 * The Class TileSteamBoiler.
 * 
 * @author Decebaldecebal
 */
public class TileSteamBoiler extends TileEntityMachine implements IFluidHandler
{

	/** The Constant steamPerTick. */
	private static final int steamPerTick = 20; // how much steam it produces
												// per tick
	/** The Constant waterPerTick. */
	private static final int waterPerTick = 30; // how much water it uses per
	// tick

	/** The furnace burn time. */
	public int furnaceBurnTime = 0;

	/** The current item burn time. */
	public int currentItemBurnTime = 0;

	/** The water tank. */
	public FluidTank waterTank;

	/** The steam tank. */
	public FluidTank steamTank;

	/**
	 * Instantiates a new tile steam boiler.
	 */
	public TileSteamBoiler()
	{
		super((byte) 3);

		this.waterTank = new FluidTank(new FluidStack(FluidRegistry.WATER, 0), 5000);
		this.steamTank = new FluidTank(new FluidStack(FluidRegistry.getFluid("steam"), 0), 10000);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * steamcraft.common.tiles.TileEntityMachine#readFromNBT(net.minecraft.nbt
	 * .NBTTagCompound)
	 */
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		this.furnaceBurnTime = tag.getShort("BurnTime");
		this.currentItemBurnTime = tag.getShort("ItemTime");
		this.steamTank.setFluid(new FluidStack(FluidRegistry.getFluid("steam"), tag.getShort("steamLevel")));
		this.waterTank.setFluid(new FluidStack(FluidRegistry.getFluid("water"), tag.getShort("waterLevel")));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * steamcraft.common.tiles.TileEntityMachine#writeToNBT(net.minecraft.nbt
	 * .NBTTagCompound)
	 */
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		tag.setShort("BurnTime", (short) this.furnaceBurnTime);
		tag.setShort("ItemTime", (short) this.currentItemBurnTime);
		tag.setShort("steamLevel", (short) this.steamTank.getFluidAmount());
		tag.setShort("waterLevel", (short) this.waterTank.getFluidAmount());
	}

	/**
	 * Gets the burn time remaining scaled.
	 * 
	 * @param par1
	 *            the par1
	 * @return the burn time remaining scaled
	 */
	public int getBurnTimeRemainingScaled(int par1)
	{
		if (this.currentItemBurnTime == 0)
		{
			this.currentItemBurnTime = 200;
		}

		return (this.furnaceBurnTime * par1) / this.currentItemBurnTime;
	}

	/**
	 * Checks if is burning.
	 * 
	 * @return true, if is burning
	 */
	public boolean isBurning()
	{
		return this.furnaceBurnTime > 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.tileentity.TileEntity#updateEntity()
	 */
	@Override
	public void updateEntity()
	{
		super.updateEntity();

		boolean var1 = this.furnaceBurnTime > 0;
		boolean var2 = false;

		if (!this.worldObj.isRemote)
		{
			if (this.inventory[1] != null)
			{
				FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(this.inventory[1]);

				if ((liquid != null) && (this.waterTank.fill(new FluidStack(FluidRegistry.getFluid("water"), liquid.amount), false) == liquid.amount))
				{
					if (liquid.getFluid() == FluidRegistry.WATER)
					{
						this.waterTank.fill(new FluidStack(FluidRegistry.getFluid("water"), liquid.amount), true);

						if (this.inventory[1].stackSize > 1)
						{
							this.inventory[1].stackSize--;
						}
						else
						{
							this.inventory[1] = this.inventory[1].getItem().getContainerItem(this.inventory[1]);
						}
					}
				}
			}
			if (inventory[2] != null && inventory[2].getItem() instanceof ItemCanister)
			{
				ItemCanister canister = (ItemCanister) inventory[2].getItem();
				if (steamTank.getFluidAmount() > 0 && canister.getFluidAmount(inventory[2]) != ItemCanister.MAX_STEAM)
				{
					canister.fill(inventory[2], new FluidStack(FluidRegistry.getFluid("steam"), steamPerTick), true);
					this.steamTank.drain(steamPerTick, true);
				}
			}

			if ((this.getItemBurnTime() > 0) && (this.furnaceBurnTime == 0) && (this.waterTank.getFluidAmount() >= waterPerTick)
					&& (this.steamTank.fill(new FluidStack(FluidRegistry.getFluid("steam"), steamPerTick), false) > 0))
			{
				this.currentItemBurnTime = this.furnaceBurnTime = this.getItemBurnTime() / 4;

				if (this.inventory[0].stackSize == 1)
				{
					this.inventory[0] = this.inventory[0].getItem().getContainerItem(this.inventory[0]);
				}
				else
				{
					--this.inventory[0].stackSize;
				}
			}

			if ((this.furnaceBurnTime > 0) && (this.waterTank.getFluidAmount() >= waterPerTick) && (this.steamTank.getFluidAmount() <= 10000))
			{
				this.steamTank.fill(new FluidStack(FluidRegistry.getFluid("steam"), steamPerTick), true);
				this.furnaceBurnTime--;
				this.waterTank.drain(waterPerTick, true);
			}
			else
			{
				this.furnaceBurnTime = 0;
			}

			if (var1 != (this.furnaceBurnTime > 0))
			{
				var2 = true;
				BlockSteamBoiler.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if (var2)
		{
			this.markDirty();
		}
	}

	/**
	 * Gets the item burn time.
	 * 
	 * @return the item burn time
	 */
	private int getItemBurnTime()
	{
		if (this.inventory[0] == null)
		{
			return 0;
		}

		return TileEntityFurnace.getItemBurnTime(this.inventory[0]);
	}

	/**
	 * Gets the scaled water level.
	 * 
	 * @param i
	 *            the i
	 * @return the scaled water level
	 */
	public int getScaledWaterLevel(int i)
	{
		return this.waterTank.getFluid() != null ? (int) (((float) this.waterTank.getFluid().amount / (float) (this.waterTank.getCapacity())) * i)
				: 0;
	}

	/**
	 * Gets the scaled steam level.
	 * 
	 * @param i
	 *            the i
	 * @return the scaled steam level
	 */
	public int getScaledSteamLevel(int i)
	{
		return this.steamTank.getFluid() != null ? (int) (((float) this.steamTank.getFluid().amount / (float) (this.steamTank.getCapacity())) * i)
				: 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * steamcraft.common.tiles.TileEntityMachine#getAccessibleSlotsFromSide(int)
	 */
	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return new int[] { 0, 1 };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see steamcraft.common.tiles.TileEntityMachine#canInsertItem(int,
	 * net.minecraft.item.ItemStack, int)
	 */
	@Override
	public boolean canInsertItem(int par1, ItemStack itemstack, int par3)
	{
		if ((par1 == 1) && FluidContainerRegistry.isContainer(itemstack))
		{
			return true;
		}
		if ((par1 == 0) && (TileEntityFurnace.getItemBurnTime(itemstack) > 0))
		{
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see steamcraft.common.tiles.TileEntityMachine#canExtractItem(int,
	 * net.minecraft.item.ItemStack, int)
	 */
	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see steamcraft.common.tiles.TileEntityMachine#isItemValidForSlot(int,
	 * net.minecraft.item.ItemStack)
	 */
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		if ((i == 0) || (i == 1))
		{
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraftforge.fluids.IFluidHandler#fill(net.minecraftforge.common
	 * .util.ForgeDirection, net.minecraftforge.fluids.FluidStack, boolean)
	 */
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		if (resource.getFluid() == FluidRegistry.WATER)
		{
			return this.waterTank.fill(resource, doFill);
		}

		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraftforge.fluids.IFluidHandler#drain(net.minecraftforge.common
	 * .util.ForgeDirection, net.minecraftforge.fluids.FluidStack, boolean)
	 */
	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		if ((resource == null) || !resource.isFluidEqual(this.steamTank.getFluid()))
		{
			return null;
		}
		return this.steamTank.drain(resource.amount, doDrain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraftforge.fluids.IFluidHandler#drain(net.minecraftforge.common
	 * .util.ForgeDirection, int, boolean)
	 */
	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return this.steamTank.drain(maxDrain, doDrain);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraftforge.fluids.IFluidHandler#canFill(net.minecraftforge.common
	 * .util.ForgeDirection, net.minecraftforge.fluids.Fluid)
	 */
	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		if (fluid == FluidRegistry.WATER)
		{
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraftforge.fluids.IFluidHandler#canDrain(net.minecraftforge.common
	 * .util.ForgeDirection, net.minecraftforge.fluids.Fluid)
	 */
	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		if (fluid == FluidRegistry.getFluid("steam"))
		{
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraftforge.fluids.IFluidHandler#getTankInfo(net.minecraftforge
	 * .common.util.ForgeDirection)
	 */
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { this.steamTank.getInfo(), this.waterTank.getInfo() };
	}
}
