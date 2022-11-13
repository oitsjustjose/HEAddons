package com.oitsjustjose.headdons.common.Item;

import cofh.core.util.helpers.AugmentDataHelper;
import cofh.lib.util.constants.NBTTags;
import cofh.thermal.lib.common.ThermalFlags;
import cofh.thermal.lib.item.AugmentItem;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.oitsjustjose.headdons.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class CustomIntegralComponent extends AugmentItem {

    public CustomIntegralComponent(float AugmentModifier) {
        super(new Item.Properties().tab(CreativeModeTab.TAB_MISC),
                AugmentDataHelper.builder()
                        .type(NBTTags.TAG_AUGMENT_TYPE_UPGRADE)
                        .mod(NBTTags.TAG_AUGMENT_BASE_MOD, AugmentModifier)
                        .build());
        this.setShowInGroups(ThermalFlags.getFlag(ThermalFlags.FLAG_UPGRADE_AUGMENTS));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
        var comp = new TranslatableComponent("item." + Constants.MODID + "." + this.getRegistryName().getPath() + ".desc");
        try {
            var resolved = comp.resolve(null, null, 0);
            components.add(resolved);
        } catch(CommandSyntaxException ex) {
            // NOOP
        }
    }
}
