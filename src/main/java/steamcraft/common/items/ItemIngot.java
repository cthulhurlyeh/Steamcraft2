/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [3/18/14, 12:17]
 */
package steamcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemIngot.
 *
 * @author warlordjones
 */
public class ItemIngot extends Item
{

	/** The icon. */
	public IIcon[] icon = new IIcon[8];

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#getIconFromDamage(int)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(final int itemDamage)
	{
		return icon[itemDamage];
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#registerIcons(net.minecraft.client.renderer.texture.IIconRegister)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(final IIconRegister ir)
	{
		icon[0] = ir.registerIcon(LibInfo.PREFIX + "itemIngotAluminum");
		icon[1] = ir.registerIcon(LibInfo.PREFIX + "itemIngotCopper");
		icon[2] = ir.registerIcon(LibInfo.PREFIX + "itemIngotTin");
		icon[3] = ir.registerIcon(LibInfo.PREFIX + "itemIngotZinc");
		icon[4] = ir.registerIcon(LibInfo.PREFIX + "itemIngotBrass");
		icon[5] = ir.registerIcon(LibInfo.PREFIX + "itemIngotBronze");
		icon[6] = ir.registerIcon(LibInfo.PREFIX + "itemIngotSteel");
		icon[7] = ir.registerIcon(LibInfo.PREFIX + "itemIngotCastIron");
	}

	/**
	 * Instantiates a new item ingot.
	 */
	public ItemIngot()
	{
		super();
		setMaxStackSize(64);
		setHasSubtypes(true);
		setCreativeTab(Steamcraft.tabSC2);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#getSubItems(net.minecraft.item.Item, net.minecraft.creativetab.CreativeTabs, java.util.List)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(final Item item, final CreativeTabs tab,
			final List l)
	{
		for (int var4 = 0; var4 < 8; ++var4)
		{
			l.add(new ItemStack(ConfigItems.itemIngot, 1, var4));
		}
	}
}