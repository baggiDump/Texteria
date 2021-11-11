# VimeWorld-Texteria-SRC
Решил, что многим будет полезно слить данную штуку с вайма как Texteria, я видел, что есть исходный код VimeWorld, но там не полностью была Texteria, т.к.  там была лишь клиентская часть. Поэтому я решил слить всю для вас Texteria, вы можете его подделать ввиде мода как клиент-сервер. Так же и для обычного клиента майнкрафт, который не Forge
Для начала вы сами должны вырезать из Texteria чего вам там вообще не нужно для вашего сервера. так же и utils 
Далее. Чтобы поставить Texteria для Forge используйте события так же для Forge мода есть вещь такая как mixin думаю сами разберётесь.
Если вам лень вы так же можете пересобрать клиент и пропатчить ниже описано

Следующий будет показан код, что куда и как патчить классы Minecraft.

Для начала все я доставал из 1.8.8. Поэтому отличия выше 1.8.9 будут. Следовательно вам необходимо разобраться самим. Но скажу так, что отличая не критично!

1. Minecraft.java - Главный класс
1) private void startGame() throws LWJGLException, IOException
после: this.renderGlobal.makeEntityOutlineShader();
вставить: Texteria.instance.init(this);

2)public void loadWorld(WorldClient worldClientIn, String loadingMessage)
 после this.theWorld = worldClientIn;
 вставить: Texteria.instance.onWorldChanged();
 после: this.saveLoader.flushCache(); this.thePlayer = null;
 вставить: Texteria.instance.handleDisconnect();
 
 3)public void shutdownMinecraftApplet()
 после: catch (Throwable var5){}
 вставить: Texteria.instance.stop();
 
 4)public void runTick() throws IOException
 после: while (Mouse.next())
 добавить: (проверку на нажатие мышки) if (!Texteria.instance.handleMouseEvent()) {}
 
 5)Тот же метод public void runTick() throws IOExceptio
после while (Keyboard.next())
вставить: (проверку на нажатие клав.) if (!Texteria.instance.handleKeyboardEvent()){}

2. EntityRenderer.java - Рендер сущностей
1) public void func_181560_a(float p_181560_1_, long p_181560_2_) (1.8.8 в 1.12.2 смотрите сами исход может по другому называться)
после: throw new ReportedException(crashreport); и двух }}
вставить: Texteria.instance.gui.render(GuiRenderLayer.SCREEN);

2) private void renderWorldPass(int pass, float partialTicks, long finishTimeNano)
после: if (Reflector.ForgeHooksClient_setRenderPass.exists() && !this.debugView) (OptiFine)
вставить: Texteria.instance.world.render(frustum);

3. EffectRenderer.java - Рендерит эффекты
1) public void updateEffects()
после: this.particleEmitters.removeAll(arraylist);
вставить: Texteria.instance.world.tickParticles();

2)public void renderParticles(Entity entityIn, float partialTicks)
после: tessellator.draw(); и 3-х }}}
вставить: Texteria.instance.world.renderParticles(partialTicks);

4. NetHandlerPlayClient.java - отвечает за часть Network
1) public void handleCustomPayload(S3FPacketCustomPayload packetIn)
после: разных проверок надо добавить
вставить: else if ("Texteria".equals(packetIn.getChannelName())) {Texteria.instance.handleTexteriaPacket(packetIn); }

5. GuiScreen.java - отвечает за граф. интерфейс
1)public void handleMouseInput() throws IOException
Здесь мы просто стандартно хватаем события Mouse 
после: int k = Mouse.getEventButton();
ставить: Texteria.instance.handleMouseEvent();

6. GuiOverlayDebug.java - Отвечает за отрисовку надписей при нажатие F3
1)protected List<String> call()
после: String s1 = "";
вставить:  Element2D element2d = Texteria.instance.gui.getElement("vn.s");
 if (element2d instanceof Text)
 {
     s1 = ", " + ((Text)element2d).text[0];
 }
TexteriaWorld texteriaworld = Texteria.instance.world;
            ArrayList<String> arraylist = Lists.newArrayList(new String[] {"Minecraft 1.8.8 (VimeWorld: " + this.mc.getSession().getUsername() + s1 + ")", this.mc.debug, this.mc.renderGlobal.getDebugInfoRenders(), this.mc.renderGlobal.getDebugInfoEntities(), "Particles: " + this.mc.effectRenderer.getStatistics(), "Texteria: " + texteriaworld.visibleElementsCount + "/" + texteriaworld.elements.getAll().size() + " world, " + Texteria.instance.gui.visibleElementsCount + "/" + Texteria.instance.gui.byId.size() + " gui, " + texteriaworld.boxes.dynamicCount() + "d/" + texteriaworld.boxes.tickingCount() + "t/" + texteriaworld.boxes.count() + " boxes, " + texteriaworld.visibleParticlesCount + "/" + texteriaworld.particles.size() + " particles", "", String.format("XYZ: %.3f / %.3f / %.3f", new Object[]{Double.valueOf(this.mc.getRenderViewEntity().posX), Double.valueOf(this.mc.getRenderViewEntity().getEntityBoundingBox().minY), Double.valueOf(this.mc.getRenderViewEntity().posZ)}), String.format("Block: %d %d %d", new Object[]{Integer.valueOf(blockpos.n()), Integer.valueOf(blockpos.o()), Integer.valueOf(blockpos.p())}), String.format("Chunk: %d %d %d in %d %d %d", new Object[]{Integer.valueOf(blockpos.n() & 15), Integer.valueOf(blockpos.o() & 15), Integer.valueOf(blockpos.p() & 15), Integer.valueOf(blockpos.n() >> 4), Integer.valueOf(blockpos.o() >> 4), Integer.valueOf(blockpos.p() >> 4)}), String.format("Facing: %s (%s) (%.1f / %.1f)", new Object[]{enumfacing, s, Float.valueOf(MathHelper.wrapAngleTo180_float(entity.rotationYaw)), Float.valueOf(MathHelper.wrapAngleTo180_float(entity.rotationPitch))})});

7. GuiContainer.java - рисует
1) public void drawScreen(int mouseX, int mouseY, float partialTicks)
после: GlStateManager.popMatrix();
вставить: Texteria.instance.gui.render(GuiRenderLayer.BEFORE_TOOLTIP);

8. EntityPlayerSP.java - а то подтипа класс игрока 
1) public void jump()
после:  super.jump();
вставляем: Texteria.instance.world.boxes.playerJump(this);


Спасибо всем, кто читал. 
Связаться со мной вы можете по VK либо дискорду: xfrutelacode#5610
