package ru.kitburg.swordmodx.item;


import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.SimpleTier;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import ru.kitburg.swordmodx.Swordmodx;



import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Swordmodx.MOD_ID);


    public static final Tier FIRE_TIER = new SimpleTier(
            // The tag that determines what blocks this tool cannot break. See below for more information.
            BlockTags.NEEDS_DIAMOND_TOOL,
            // Determines the durability of the tier.
            // Stone is 131, iron is 250.
            1000,
            // Determines the mining speed of the tier. Unused by swords.
            // Stone uses 4, iron uses 6.
            5f,
            // Determines the attack damage bonus. Different tools use this differently. For example, swords do (getAttackDamageBonus() + 4) damage.
            // Stone uses 1, iron uses 2, corresponding to 5 and 6 attack damage for swords, respectively; our sword does 5.5 damage now.
            7f,
            // Determines the enchantability of the tier. This represents how good the enchantments on this tool will be.
            // Gold uses 22, we put copper slightly below that.
            20,
            // Determines the repair ingredient of the tier. Use a supplier for lazy initializing.
            () -> Ingredient.of(Tags.Items.RODS_BLAZE)
    );



    public static final Supplier<SwordItem> FIRESWORD = ITEMS.register("firesword", () -> new SwordItem(
            // The tier to use.
            FIRE_TIER,
            // The item properties. We don't need to set the durability here because TieredItem handles that for us.
            new Item.Properties().attributes(
                    // There are `createAttributes` methods in either the class or subclass of each DiggerItem
                    SwordItem.createAttributes(
                            // The tier to use.
                            FIRE_TIER,
                            // The type-specific attack damage bonus. 3 for swords, 1.5 for shovels, 1 for pickaxes, varying for axes and hoes.
                            8,
                            // The type-specific attack speed modifier. The player has a default attack speed of 4, so to get to the desired
                            // value of 1.6f, we use -2.4f. -2.4f for swords, -3f for shovels, -2.8f for pickaxes, varying for axes and hoes.
                            -2.4f

                            )


            )
    ));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}