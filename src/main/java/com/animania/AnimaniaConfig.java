package com.animania;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = AnimaniaMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class AnimaniaConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.IntValue SALT_LICK_MAX_USES = BUILDER
            .comment("Maximum uses of the Salt Lick")
            .defineInRange("salt_lick_max_uses", 200, 1, Integer.MAX_VALUE);

    public static final ModConfigSpec SPEC = BUILDER.build();

    public static int saltLickMaxUses;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        saltLickMaxUses = SALT_LICK_MAX_USES.getAsInt();
    }
}
