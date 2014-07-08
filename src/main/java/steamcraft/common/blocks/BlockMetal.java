/**
 * This class was created by BrassGoggledCoders modding team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 */
package steamcraft.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.lib.LibInfo;
import boilerplate.steamapi.IChiselable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockMetal.
 * 
 * @author warlordjones
 */
public class BlockMetal extends Block implements IChiselable
{

	/** The icon. */
	private final IIcon[] icon = new IIcon[8];

	/** The powered. */
	private boolean powered;

	/*
	 * (non-Javadoc)
	 * @see net.minecraft.block.Block#registerBlockIcons(net.minecraft.client.renderer .texture.IIconRegister)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister ir)
	{
		this.icon[0] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockAluminum");
		this.icon[1] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockCopper");
		this.icon[2] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockTin");
		this.icon[3] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockZinc");
		this.icon[4] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockBrass");
		this.icon[5] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockBronze");
		this.icon[6] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockSteel");
		this.icon[7] = ir.registerIcon(LibInfo.PREFIX + "metal/" + "blockCastIron");
	}

	/*
	 * (non-Javadoc)
	 * @see net.minecraft.block.Block#getIcon(int, int)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int side, final int metadata)
	{
		return this.icon[metadata];
	}

	/**
	 * Instantiates a new block metal.
	 */
	public BlockMetal()
	{
		super(Material.iron);
		this.setBlockName("blockMetal");
		this.setHardness(3.0F);
		this.setResistance(10.0F);
		this.setStepSound(Block.soundTypeMetal);
		this.setTickRandomly(true);
		this.setCreativeTab(Steamcraft.tabSC2);

		if (this.powered)
			this.setLightLevel(0.98F);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(final Item item, final CreativeTabs tab, final List l)
	{
		for (int var4 = 0; var4 < this.icon.length; ++var4)
			l.add(new ItemStack(ConfigBlocks.blockMetal, 1, var4));
	}

	@Override
	public Block getChiseledVariant()
	{
		return ConfigBlocks.blockEngraved;
	}

	@Override
	public int getChiseledVariantMeta()
	{
		return -1;
	}
}
