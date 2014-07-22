package steamcraft.common.items.electric;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import steamcraft.common.items.tools.ItemModTool;
import boilerplate.common.IEnergyItem;

public class ItemElectricTool extends ItemModTool implements IEnergyItem
{
	private Random random = new Random();
	protected int maxEnergy = 0;
	protected short maxReceive = 0;
	protected short maxSend = 0;
	protected int energyPerBlock = 0;

	protected ItemElectricTool(float damage, ToolMaterial toolMat, Block[] blockArray)
	{
		super(damage, toolMat, blockArray);
		this.maxEnergy = maxEnergy * 1000;
		this.maxReceive = (short) maxReceive;
		this.maxSend = (short) maxSend;
		this.setMaxStackSize(1);
		this.setMaxDamage(20);
		this.setHasSubtypes(false);
	}
	@SuppressWarnings("all")
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		list.add(this.getUnchargedItem());
		list.add(this.getChargedItem());
	}

	public ItemStack getUnchargedItem()
	{
		ItemStack uncharged = new ItemStack(this, 1, 20);

		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("energy", 0);

		uncharged.setTagCompound(tag);

		return uncharged.copy();
	}

	public ItemStack getChargedItem()
	{
		ItemStack charged = new ItemStack(this, 1, 0);

		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("energy", this.maxEnergy);

		charged.setTagCompound(tag);

		return charged.copy();
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityplayer, List list, boolean flag)
	{
		list.add("Energy: " + this.getEnergyStored(stack) / 1000 + "k / " + this.maxEnergy / 1000 + "k");
		list.add("Transfer(in/out): " + this.maxReceive + " / " + this.maxSend);
	}

	@Override
	public void onCreated(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer)
	{
		stack = this.getUnchargedItem();
	}

	public void setEnergy(ItemStack stack, int energy)
	{
		NBTTagCompound tag = stack.getTagCompound();

		if (energy < 0)
			energy = 0;

		if (energy > this.maxEnergy)
			energy = this.maxEnergy;

		stack.setItemDamage(20 - energy * 20 / this.maxEnergy);

		tag.setInteger("energy", energy);

		stack.setTagCompound(tag);
	}

	@Override
	public int receiveEnergy(ItemStack itemStack, int maxReceive, boolean simulate)
	{
		int received = Math.min(this.maxEnergy - this.getEnergyStored(itemStack), maxReceive);
		received = Math.min(received, this.maxReceive);

		if (!simulate)
			this.setEnergy(itemStack, this.getEnergyStored(itemStack) + received);

		return received;
	}

	@Override
	public int extractEnergy(ItemStack itemStack, int maxExtract, boolean simulate)
	{
		int extracted = Math.min(this.getEnergyStored(itemStack), maxExtract);
		extracted = Math.min(extracted, this.maxSend);

		if (!simulate)
			this.setEnergy(itemStack, this.getEnergyStored(itemStack) - extracted);

		return extracted;
	}

	@Override
	public int getEnergyStored(ItemStack itemStack)
	{
		return itemStack.getTagCompound().getInteger("energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack container)
	{
		return this.maxEnergy;
	}
	public short getMaxSend()
	{
		return this.maxSend;
	}

}
