package steamcraft.client.gui;

import java.util.LinkedList;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.ResourceLocation;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.PDAEntry;
import boilerplate.common.PDAEntryList;

public class GuiPDA extends GuiScreen implements IProgressMeter
{
		private static final int field_146572_y = PDAEntryList.minDisplayColumn * 24 - 112;
	    private static final int field_146571_z = PDAEntryList.minDisplayRow * 24 - 112;
	    private static final int field_146559_A = PDAEntryList.maxDisplayColumn * 24 - 77;
	    private static final int field_146560_B = PDAEntryList.maxDisplayRow * 24 - 77;
	    private static final ResourceLocation guiTexture = new ResourceLocation(LibInfo.PREFIX + "textures/gui/computer.png");
	    protected GuiScreen field_146562_a;
	    protected int field_146555_f = 256;
	    protected int field_146557_g = 202;
	    protected int field_146563_h;
	    protected int field_146564_i;
	    protected float field_146570_r = 1.0F;
	    protected double field_146569_s;
	    protected double field_146568_t;
	    protected double field_146567_u;
	    protected double field_146566_v;
	    protected double field_146565_w;
	    protected double field_146573_x;
	    private int field_146554_D;
	    private StatFileWriter field_146556_E;
	    private boolean field_146558_F = true;
	    private static final String __OBFID = "CL_00000722";

	    private int currentPage = -1;
	    private GuiButton button;
	    private LinkedList<PDAEntry> pdaEntries = new LinkedList<PDAEntry>();
		@Override
		public void func_146509_g()
		{
			// TODO Auto-generated method stub

		}

	    /*public GuiPDA(GuiScreen p_i45026_1_, StatFileWriter p_i45026_2_)
	    {
	        this.field_146562_a = p_i45026_1_;
	        this.field_146556_E = p_i45026_2_;
	        short short1 = 141;
	        short short2 = 141;
	        this.field_146569_s = this.field_146567_u = this.field_146565_w = (double)(PDAEntryList.openInventory.displayColumn * 24 - short1 / 2 - 12);
	        this.field_146568_t = this.field_146566_v = this.field_146573_x = (double)(PDAEntryList.openInventory.displayRow * 24 - short2 / 2);
	        Iterator iterator = Item.itemRegistry.iterator();

	        while (iterator.hasNext())
	        {
	            Item item = (Item)iterator.next();

	            if (item == null)
	            {
	                continue;
	            }

	            for (CreativeTabs tab : item.getCreativeTabs())
	            {
	                if (tab == Steamcraft.tabSC2)
	                {
	                    new PDAEntry(EnumEntryType.ITEMS, StatCollector.translateToLocal(item.getUnlocalizedName() + ".name"), StatCollector.translateToLocal(item.getUnlocalizedName() + ".documentation")).register();
	                }
	            }
	        }
	    }

	    /**
	     * Adds the buttons (and other controls) to the screen in question.

	    public void initGui()
	    {
	        this.mc.getNetHandler().addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus.EnumState.REQUEST_STATS));
	        this.buttonList.clear();
	        this.buttonList.add(new GuiOptionButton(1, this.width / 2 + 24, this.height / 2 + 74, 80, 20, I18n.format("gui.done", new Object[0])));
	        this.buttonList.add(button = new GuiButton(2, (width - field_146555_f) / 2 + 24, height / 2 + 74, 125, 20, PDAPage.getTitle(currentPage)));

	        for(int i = 0; i < pdaEntries.size(); i++)
	        {
	        	PDAEntry entry = pdaEntries.get(i);
	        	if(entry == null) { continue; }

	        	this.buttonList.add(new GuiButton(3 + i, (width - field_146555_f) / 2 + i, height / 2 + i, 20, 20, PDAEntry.getName()));
	        }
	    }

	    protected void actionPerformed(GuiButton p_146284_1_)
	    {
	        if (!this.field_146558_F)
	        {
	            if (p_146284_1_.id == 1)
	            {
	                this.mc.displayGuiScreen(this.field_146562_a);
	            }

	            if (p_146284_1_.id == 2)
	            {
	                currentPage++;
	                if (currentPage >= PDAPage.getPDAPages().size())
	                {
	                    currentPage = -1;
	                }
	                button.displayString = PDAPage.getTitle(currentPage);
	            }
	        }
	    }

	    /**
	     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).

	    protected void keyTyped(char p_73869_1_, int p_73869_2_)
	    {
	        if (p_73869_2_ == this.mc.gameSettings.keyBindInventory.getKeyCode())
	        {
	            this.mc.displayGuiScreen((GuiScreen)null);
	            this.mc.setIngameFocus();
	        }
	        else
	        {
	            super.keyTyped(p_73869_1_, p_73869_2_);
	        }
	    }

	    /**
	     * Draws the screen and all the components in it.

	    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	    {
	        if (this.field_146558_F)
	        {
	            this.drawDefaultBackground();
	            this.drawCenteredString(this.fontRendererObj, I18n.format("multiplayer.downloadingStats", new Object[0]), this.width / 2, this.height / 2, 16777215);
	            this.drawCenteredString(this.fontRendererObj, field_146510_b_[(int)(Minecraft.getSystemTime() / 150L % (long)field_146510_b_.length)], this.width / 2, this.height / 2 + this.fontRendererObj.FONT_HEIGHT * 2, 16777215);
	        }
	        else
	        {
	            int k;

	            if (Mouse.isButtonDown(0))
	            {
	                k = (this.width - this.field_146555_f) / 2;
	                int l = (this.height - this.field_146557_g) / 2;
	                int i1 = k + 8;
	                int j1 = l + 17;

	                if ((this.field_146554_D == 0 || this.field_146554_D == 1) && p_73863_1_ >= i1 && p_73863_1_ < i1 + 224 && p_73863_2_ >= j1 && p_73863_2_ < j1 + 155)
	                {
	                    if (this.field_146554_D == 0)
	                    {
	                        this.field_146554_D = 1;
	                    }
	                    else
	                    {
	                        this.field_146567_u -= (double)((float)(p_73863_1_ - this.field_146563_h) * this.field_146570_r);
	                        this.field_146566_v -= (double)((float)(p_73863_2_ - this.field_146564_i) * this.field_146570_r);
	                        this.field_146565_w = this.field_146569_s = this.field_146567_u;
	                        this.field_146573_x = this.field_146568_t = this.field_146566_v;
	                    }

	                    this.field_146563_h = p_73863_1_;
	                    this.field_146564_i = p_73863_2_;
	                }
	            }
	            else
	            {
	                this.field_146554_D = 0;
	            }

	            k = Mouse.getDWheel();
	            float f4 = this.field_146570_r;

	            if (k < 0)
	            {
	                this.field_146570_r += 0.25F;
	            }
	            else if (k > 0)
	            {
	                this.field_146570_r -= 0.25F;
	            }

	            this.field_146570_r = MathHelper.clamp_float(this.field_146570_r, 1.0F, 2.0F);

	            if (this.field_146570_r != f4)
	            {
	                float f6 = f4 - this.field_146570_r;
	                float f5 = f4 * (float)this.field_146555_f;
	                float f1 = f4 * (float)this.field_146557_g;
	                float f2 = this.field_146570_r * (float)this.field_146555_f;
	                float f3 = this.field_146570_r * (float)this.field_146557_g;
	                this.field_146567_u -= (double)((f2 - f5) * 0.5F);
	                this.field_146566_v -= (double)((f3 - f1) * 0.5F);
	                this.field_146565_w = this.field_146569_s = this.field_146567_u;
	                this.field_146573_x = this.field_146568_t = this.field_146566_v;
	            }

	            if (this.field_146565_w < (double)field_146572_y)
	            {
	                this.field_146565_w = (double)field_146572_y;
	            }

	            if (this.field_146573_x < (double)field_146571_z)
	            {
	                this.field_146573_x = (double)field_146571_z;
	            }

	            if (this.field_146565_w >= (double)field_146559_A)
	            {
	                this.field_146565_w = (double)(field_146559_A - 1);
	            }

	            if (this.field_146573_x >= (double)field_146560_B)
	            {
	                this.field_146573_x = (double)(field_146560_B - 1);
	            }

	            this.drawDefaultBackground();
	            this.func_146552_b(p_73863_1_, p_73863_2_, p_73863_3_);
	            GL11.glDisable(GL11.GL_LIGHTING);
	            GL11.glDisable(GL11.GL_DEPTH_TEST);
	            this.drawStrings();
	            GL11.glEnable(GL11.GL_LIGHTING);
	            GL11.glEnable(GL11.GL_DEPTH_TEST);
	        }
	    }

	    public void func_146509_g()
	    {
	        if (this.field_146558_F)
	        {
	            this.field_146558_F = false;
	        }
	    }

	    /**
	     * Called from the main game loop to update the screen.

	    public void updateScreen()
	    {
	        if (!this.field_146558_F)
	        {
	            this.field_146569_s = this.field_146567_u;
	            this.field_146568_t = this.field_146566_v;
	            double d0 = this.field_146565_w - this.field_146567_u;
	            double d1 = this.field_146573_x - this.field_146566_v;

	            if (d0 * d0 + d1 * d1 < 4.0D)
	            {
	                this.field_146567_u += d0;
	                this.field_146566_v += d1;
	            }
	            else
	            {
	                this.field_146567_u += d0 * 0.85D;
	                this.field_146566_v += d1 * 0.85D;
	            }
	        }
	    }

	    protected void drawStrings()
	    {
	        int i = (this.width - this.field_146555_f) / 2;
	        int j = (this.height - this.field_146557_g) / 2;

	    }

	    protected void func_146552_b(int p_146552_1_, int p_146552_2_, float p_146552_3_)
	    {
	        int k = MathHelper.floor_double(this.field_146569_s + (this.field_146567_u - this.field_146569_s) * (double)p_146552_3_);
	        int l = MathHelper.floor_double(this.field_146568_t + (this.field_146566_v - this.field_146568_t) * (double)p_146552_3_);

	        if (k < field_146572_y)
	        {
	            k = field_146572_y;
	        }

	        if (l < field_146571_z)
	        {
	            l = field_146571_z;
	        }

	        if (k >= field_146559_A)
	        {
	            k = field_146559_A - 1;
	        }

	        if (l >= field_146560_B)
	        {
	            l = field_146560_B - 1;
	        }

	        int i1 = (this.width - this.field_146555_f) / 2;
	        int j1 = (this.height - this.field_146557_g) / 2;
	        int k1 = i1 + 16;
	        int l1 = j1 + 17;
	        this.zLevel = 0.0F;
	        GL11.glDepthFunc(GL11.GL_GEQUAL);
	        GL11.glPushMatrix();
	        GL11.glTranslatef((float)k1, (float)l1, -200.0F);
	        GL11.glScalef(1.0F / this.field_146570_r, 1.0F / this.field_146570_r, 0.0F);
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        GL11.glDisable(GL11.GL_LIGHTING);
	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
	        int i2 = k + 288 >> 4;
	        int j2 = l + 288 >> 4;
	        int k2 = (k + 288) % 16;
	        int l2 = (l + 288) % 16;
	        boolean flag = true;
	        boolean flag1 = true;
	        boolean flag2 = true;
	        boolean flag3 = true;
	        boolean flag4 = true;
	        Random random = new Random();
	        float f1 = 16.0F / this.field_146570_r;
	        float f2 = 16.0F / this.field_146570_r;
	        int i3;
	        int j3;
	        int k3;

	        for (i3 = 0; (float)i3 * f1 - (float)l2 < 155.0F; ++i3)
	        {
	            float f3 = 0.6F - (float)(j2 + i3) / 25.0F * 0.3F;
	            GL11.glColor4f(f3, f3, f3, 1.0F);
	        }

	        GL11.glEnable(GL11.GL_DEPTH_TEST);
	        GL11.glDepthFunc(GL11.GL_LEQUAL);
	        this.mc.getTextureManager().bindTexture(guiTexture);
	        int i4;
	        int j4;
	        int l4;

	        List<PDAEntry> pdaList = (currentPage == -1 ? pdaEntries : PDAPage.getPDAPage(currentPage).getPDAs());
	        for (i3 = 0; i3 < pdaList.size(); ++i3)
	        {
	            PDAEntry pda1 = pdaList.get(i3);

	            if (pda1.parentPDA != null && pdaList.contains(pda1.parentPDA))
	            {
	                j3 = pda1.displayColumn * 24 - k + 11;
	                k3 = pda1.displayRow * 24 - l + 11;
	                l4 = pda1.parentPDA.displayColumn * 24 - k + 11;
	                int l3 = pda1.parentPDA.displayRow * 24 - l + 11;
	                boolean flag5 = this.field_146556_E.hasPDAUnlocked(pda1);
	                boolean flag6 = this.field_146556_E.canUnlockPDA(pda1);
	                i4 = this.field_146556_E.func_150874_c(pda1);

	                if (i4 <= 4)
	                {
	                    j4 = -16777216;

	                    if (flag5)
	                    {
	                        j4 = -6250336;
	                    }
	                    else if (flag6)
	                    {
	                        j4 = -16711936;
	                    }

	                    this.drawHorizontalLine(j3, l4, k3, j4);
	                    this.drawVerticalLine(l4, k3, l3, j4);

	                    if (j3 > l4)
	                    {
	                        this.drawTexturedModalRect(j3 - 11 - 7, k3 - 5, 114, 234, 7, 11);
	                    }
	                    else if (j3 < l4)
	                    {
	                        this.drawTexturedModalRect(j3 + 11, k3 - 5, 107, 234, 7, 11);
	                    }
	                    else if (k3 > l3)
	                    {
	                        this.drawTexturedModalRect(j3 - 5, k3 - 11 - 7, 96, 234, 11, 7);
	                    }
	                    else if (k3 < l3)
	                    {
	                        this.drawTexturedModalRect(j3 - 5, k3 + 11, 96, 241, 11, 7);
	                    }
	                }
	            }
	        }

	        PDAEntry pda = null;
	        RenderItem renderitem = new RenderItem();
	        float f4 = (float)(p_146552_1_ - k1) * this.field_146570_r;
	        float f5 = (float)(p_146552_2_ - l1) * this.field_146570_r;
	        RenderHelper.enableGUIStandardItemLighting();
	        GL11.glDisable(GL11.GL_LIGHTING);
	        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
	        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
	        int i5;
	        int j5;

	        for (l4 = 0; l4 < pdaList.size(); ++l4)
	        {
	            PDAEntry pda2 = (PDAEntry)pdaList.get(l4);
	            i5 = pda2.displayColumn * 24 - k;
	            j5 = pda2.displayRow * 24 - l;

	            if (i5 >= -24 && j5 >= -24 && (float)i5 <= 224.0F * this.field_146570_r && (float)j5 <= 155.0F * this.field_146570_r)
	            {
	                i4 = this.field_146556_E.func_150874_c(pda2);
	                float f6;

	                if (this.field_146556_E.hasPDAUnlocked(pda2))
	                {
	                    f6 = 0.75F;
	                    GL11.glColor4f(f6, f6, f6, 1.0F);
	                }
	                else if (this.field_146556_E.canUnlockPDA(pda2))
	                {
	                    f6 = 1.0F;
	                    GL11.glColor4f(f6, f6, f6, 1.0F);
	                }
	                else if (i4 < 3)
	                {
	                    f6 = 0.3F;
	                    GL11.glColor4f(f6, f6, f6, 1.0F);
	                }
	                else if (i4 == 3)
	                {
	                    f6 = 0.2F;
	                    GL11.glColor4f(f6, f6, f6, 1.0F);
	                }
	                else
	                {
	                    if (i4 != 4)
	                    {
	                        continue;
	                    }

	                    f6 = 0.1F;
	                    GL11.glColor4f(f6, f6, f6, 1.0F);
	                }

	                this.mc.getTextureManager().bindTexture(guiTexture);

	                if (pda2.getSpecial())
	                {
	                    this.drawTexturedModalRect(i5 - 2, j5 - 2, 26, 202, 26, 26);
	                }
	                else
	                {
	                    this.drawTexturedModalRect(i5 - 2, j5 - 2, 0, 202, 26, 26);
	                }

	                if (!this.field_146556_E.canUnlockPDA(pda2))
	                {
	                    f6 = 0.1F;
	                    GL11.glColor4f(f6, f6, f6, 1.0F);
	                    renderitem.renderWithColor = false;
	                }

	                GL11.glEnable(GL11.GL_LIGHTING);
	                GL11.glEnable(GL11.GL_CULL_FACE);
	                renderitem.renderItemAndEffectIntoGUI(this.mc.fontRenderer, this.mc.getTextureManager(), pda2.theItemStack, i5 + 3, j5 + 3);
	                GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	                GL11.glDisable(GL11.GL_LIGHTING);

	                if (!this.field_146556_E.canUnlockPDA(pda2))
	                {
	                    renderitem.renderWithColor = true;
	                }

	                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

	                if (f4 >= (float)i5 && f4 <= (float)(i5 + 22) && f5 >= (float)j5 && f5 <= (float)(j5 + 22))
	                {
	                    pda = pda2;
	                }
	            }
	        }

	        GL11.glDisable(GL11.GL_DEPTH_TEST);
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glPopMatrix();
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        this.mc.getTextureManager().bindTexture(guiTexture);
	        this.drawTexturedModalRect(i1, j1, 0, 0, this.field_146555_f, this.field_146557_g);
	        this.zLevel = 0.0F;
	        GL11.glDepthFunc(GL11.GL_LEQUAL);
	        GL11.glDisable(GL11.GL_DEPTH_TEST);
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        super.drawScreen(p_146552_1_, p_146552_2_, p_146552_3_);

	        if (pda != null)
	        {
	            String s1 = pda.func_150951_e().getUnformattedText();
	            String s2 = pda.getDescription();
	            i5 = p_146552_1_ + 12;
	            j5 = p_146552_2_ - 4;
	            i4 = this.field_146556_E.func_150874_c(pda);

	            if (!this.field_146556_E.canUnlockPDA(pda))
	            {
	                String s;
	                int k4;

	                if (i4 == 3)
	                {
	                    s1 = I18n.format("pda.unknown", new Object[0]);
	                    j4 = Math.max(this.fontRendererObj.getStringWidth(s1), 120);
	                    s = (new ChatComponentTranslation("pda.requires", new Object[] {pda.parentPDA.func_150951_e()})).getUnformattedText();
	                    k4 = this.fontRendererObj.splitStringWidth(s, j4);
	                    this.drawGradientRect(i5 - 3, j5 - 3, i5 + j4 + 3, j5 + k4 + 12 + 3, -1073741824, -1073741824);
	                    this.fontRendererObj.drawSplitString(s, i5, j5 + 12, j4, -9416624);
	                }
	                else if (i4 < 3)
	                {
	                    j4 = Math.max(this.fontRendererObj.getStringWidth(s1), 120);
	                    s = (new ChatComponentTranslation("pda.requires", new Object[] {pda.parentPDA.func_150951_e()})).getUnformattedText();
	                    k4 = this.fontRendererObj.splitStringWidth(s, j4);
	                    this.drawGradientRect(i5 - 3, j5 - 3, i5 + j4 + 3, j5 + k4 + 12 + 3, -1073741824, -1073741824);
	                    this.fontRendererObj.drawSplitString(s, i5, j5 + 12, j4, -9416624);
	                }
	                else
	                {
	                    s1 = null;
	                }
	            }
	            else
	            {
	                j4 = Math.max(this.fontRendererObj.getStringWidth(s1), 120);
	                int k5 = this.fontRendererObj.splitStringWidth(s2, j4);

	                if (this.field_146556_E.hasPDAUnlocked(pda))
	                {
	                    k5 += 12;
	                }

	                this.drawGradientRect(i5 - 3, j5 - 3, i5 + j4 + 3, j5 + k5 + 3 + 12, -1073741824, -1073741824);
	                this.fontRendererObj.drawSplitString(s2, i5, j5 + 12, j4, -6250336);

	                if (this.field_146556_E.hasPDAUnlocked(pda))
	                {
	                    this.fontRendererObj.drawStringWithShadow(I18n.format("pda.taken", new Object[0]), i5, j5 + k5 + 4, -7302913);
	                }
	            }

	            if (s1 != null)
	            {
	                this.fontRendererObj.drawStringWithShadow(s1, i5, j5, this.field_146556_E.canUnlockPDA(pda) ? (pda.getSpecial() ? -128 : -1) : (pda.getSpecial() ? -8355776 : -8355712));
	            }
	        }

	        GL11.glEnable(GL11.GL_DEPTH_TEST);
	        GL11.glEnable(GL11.GL_LIGHTING);
	        RenderHelper.disableStandardItemLighting();
	    }

	    /**
	     * Returns true if this GUI should pause the game when it is displayed in single-player

	    public boolean doesGuiPauseGame()
	    {
	        return !this.field_146558_F;
	    }*/
}
