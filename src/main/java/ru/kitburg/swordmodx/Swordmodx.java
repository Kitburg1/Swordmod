package ru.kitburg.swordmodx;


import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import org.openjdk.nashorn.internal.ir.Block;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import ru.kitburg.swordmodx.block.ModBlocks;
import ru.kitburg.swordmodx.item.ModItems;


@Mod(Swordmodx.MOD_ID)
public class Swordmodx {

    public static final String MOD_ID = "swordmodx";

    private static final Logger LOGGER = LogUtils.getLogger();

    public Swordmodx(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.FIRESWORD.get());
        }
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.FIRE_INGOT.get());
            event.accept(ModItems.MAGMA_INGOT.get());
            event.accept(ModItems.NETHERITE_NUGGETS.get());
//            event.accept((ItemLike) ModBlocks.FIRE_BLOCK);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                // Здесь можно зарегистрировать дополнительные клиентские ресурсы
                // Например, специальные рендереры для предметов

                // В NeoForge 1.20+ обычно модели предметов регистрируются автоматически
                // благодаря системе автоматической загрузки моделей и текстур
                // Но если возникают проблемы, можно использовать ручную регистрацию
            });
            LOGGER.info("Регистрация ресурсов клиента для мода {}", MOD_ID);
        }
    }
}

