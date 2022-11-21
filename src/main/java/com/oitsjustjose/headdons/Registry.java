package com.oitsjustjose.headdons;

import cofh.thermal.lib.common.ThermalAugmentRules;
import com.oitsjustjose.headdons.common.Item.CustomIntegralComponent;
import com.oitsjustjose.headdons.common.Item.PortableStoneCutterItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registry {
    public final DeferredRegister<Item> ITEM_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MODID);

    public void init() {
        ITEM_REGISTRY.register("desh_integral_components", () -> {
            var item = new CustomIntegralComponent(5.0F);
            ThermalAugmentRules.flagUniqueAugment(item);
            return item;
        });
        ITEM_REGISTRY.register("ostrum_integral_components", () -> {
            var item = new CustomIntegralComponent(6.0F);
            ThermalAugmentRules.flagUniqueAugment(item);
            return item;
        });
        ITEM_REGISTRY.register("calorite_integral_components", () -> {
            var item = new CustomIntegralComponent(7.0F);
            ThermalAugmentRules.flagUniqueAugment(item);
            return item;
        });

        ITEM_REGISTRY.register("portable_stonecutter", PortableStoneCutterItem::new);
    }
}
