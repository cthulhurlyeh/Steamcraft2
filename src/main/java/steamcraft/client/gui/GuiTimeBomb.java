
package steamcraft.client.gui;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import boilerplate.client.BaseContainerGui;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import steamcraft.common.init.InitPackets;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.packets.TimeBombPacket;
import steamcraft.common.tiles.TileTimeBomb;
import steamcraft.common.tiles.container.ContainerTimeBomb;

/**
 * @author warlordjones
 *
 */
public class GuiTimeBomb extends BaseContainerGui
{

	private static ResourceLocation guitexture = new ResourceLocation(ModInfo.PREFIX + "textures/gui/timebomb.png");

	private GuiTextField text;

	private EntityPlayer player;
	private TileTimeBomb bomb;

	public GuiTimeBomb(InventoryPlayer inv, TileTimeBomb tile)
	{
		super(new ContainerTimeBomb(inv, tile));
		this.bomb = tile;
		this.player = inv.player;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	{
		this.fontRendererObj.drawString("HH:MM (No colon)", this.xSize - 2, this.ySize - 110, 4210752);
		this.fontRendererObj.drawString("Time Bomb", this.xSize - 45, this.ySize - 119, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

		this.mc.renderEngine.bindTexture(guitexture);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
	}

	@Override
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.text = new GuiTextField(this.fontRendererObj, i + 55, j + 38, 80, 12);
		this.text.setTextColor(-1);
		this.text.setDisabledTextColour(-1);
		this.text.setEnableBackgroundDrawing(false);
		this.text.setMaxStringLength(4);
	}

	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	public void keyTyped(char c, int pos)
	{
		if (!Character.isDigit(c))
			this.text.setText("");

		if (this.text.textboxKeyTyped(c, pos))
			this.updateTime();

		super.keyTyped(c, pos);
	}

	private void updateTime()
	{
		String s = "0000";

		if (this.text != null)
			s = this.text.getText();
		if (s.length() == 4)
			InitPackets.network.sendToServer(
					new TimeBombPacket(Integer.parseInt(s), this.bomb.xCoord, this.bomb.yCoord, this.bomb.zCoord, this.player.dimension));
	}

	/**
	 * Called when the mouse is clicked.
	 */
	@Override
	protected void mouseClicked(int p_73864_1_, int p_73864_2_, int p_73864_3_)
	{
		super.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
		this.text.mouseClicked(p_73864_1_, p_73864_2_, p_73864_3_);
	}

	/**
	 * Draws the screen and all the components in it.
	 */
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		this.text.drawTextBox();
		this.text.setText(String.valueOf(this.bomb.getTime()));
	}
}
