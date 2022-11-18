package com.oitsjustjose.headdons;

import com.oitsjustjose.headdons.common.events.DaveBlockBreakEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Constants.MODID)
public class HEAddons {
    private static HEAddons instance;
    public final Logger LOGGER = LogManager.getLogger();
    public Registry REGISTRY = new Registry();

    public HEAddons() {
        instance = this;
        REGISTRY.init();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new DaveBlockBreakEvent());

        REGISTRY.ITEM_REGISTRY.register(FMLJavaModLoadingContext.get().getModEventBus());

        this.configSetup();
    }

    public static HEAddons getInstance() {
        return instance;
    }

    public void setup(final FMLCommonSetupEvent ignoredEvent) {
    }

    private void configSetup() {
    }
}